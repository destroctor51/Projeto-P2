package gui.relatorios.strategy;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import core.tempo.Estacao;

public abstract class Estrategia {

	protected double[] meses = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	protected double ano = 0;
	protected double mes = 0;
	protected double estacao = 0;

	protected double baba = 0;
	protected double cama = 0;
	protected double quarto = 0;
	protected double carro = 0;
	protected double restaurante = 0;

	public abstract String geraRelatorioAnual(JComboBox<?> cb);
	public abstract String geraRelatorioMensal(JComboBox<?> cb, int anoRelatorio);
	public abstract String geraRelatorioEstacao(Estacao estacao, int anoRelatorio);

	public abstract void geraGraficoAnual(JPanel panel,JComboBox<?> cb);
	public abstract void geraGraficoMensal(JPanel panel,JComboBox<?> cb);
	public abstract void geraGraficoEstacao(JPanel panel, JComboBox<?> cb);

	protected double geraPorcentagem(double total, double valor) {
		try {
			return (valor * 100) / (total);
		} catch (Exception e) {
			return 0;
		}
	}

	protected void resetarVariaveis() {
		for (int i = 0; i < 12; i++) {
			meses[i] = 0;
		}

		ano = 0;
		mes = 0;
		estacao = 0;

		baba = 0;
		cama = 0;
		quarto = 0;
		carro = 0;
		restaurante = 0;
	}

	protected void geraGraficoPizzaPadrao(JPanel panel, JComboBox<?> cb, String titulo, double total) {

		DefaultPieDataset pieDataset = new DefaultPieDataset();

		pieDataset.setValue("Bab\u00E1s", geraPorcentagem(total, baba));
		pieDataset.setValue("Camas", geraPorcentagem(total, cama));
		pieDataset.setValue("Carros", geraPorcentagem(total, carro));
		pieDataset.setValue("Quartos", geraPorcentagem(total, quarto));
		pieDataset.setValue("Restaurantes", geraPorcentagem(total, restaurante));

		JFreeChart chart = ChartFactory.createPieChart3D(
				titulo + cb.getSelectedItem(), // Title
				pieDataset, // Dataset
				true, // Show legend
				true, // Use tooltips
				false // Configure chart to generate URLs?
				);

		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);

		ChartPanel chartPanel1 = new ChartPanel(chart);
		panel.removeAll();
		panel.add(chartPanel1, BorderLayout.CENTER);
		panel.validate();
	}

	public void geraGraficoLinhaPadrao(JPanel panel, JComboBox<?> cb,String titulo, String coluna) {

		String[] mes = { "Janeiro", "Fevereiro", "Mar\u00E7o", "Abril", "Maio",
				"Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" };

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < 12; i++){
			dataset.addValue(meses[i], coluna , mes[i]);
		}
		JFreeChart chart = ChartFactory.createLineChart(
				titulo + cb.getSelectedItem(), "M\u00EAs", coluna ,
				dataset);
		ChartPanel chartPanel1 = new ChartPanel(chart);
		panel.removeAll();
		panel.add(chartPanel1, BorderLayout.CENTER);
		panel.validate();

	}

}
