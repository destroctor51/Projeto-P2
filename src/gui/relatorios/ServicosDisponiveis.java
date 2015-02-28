package gui.relatorios;

import gui.Sistema;
import gui.components.Calendario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import core.tempo.Periodo;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;

public class ServicosDisponiveis extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel errorLabel = new JLabel("errorLabel");
	private JPanel panel_2 = new JPanel();

	private JPanel panel = new JPanel();
	private double porcentagemDisponivel = 0.0;
	private JTextArea relatorio = new JTextArea();
	private final Calendario calendario = new Calendario();
	public ServicosDisponiveis(final JPanel tela) {

		setName("Relatorio de servicos disponiveis");

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 81, 261, 156, 118, 0 };
		gridBagLayout.rowHeights = new int[] { 262, 165, 56, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.VERTICAL;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 217, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);
		
				JButton btnGerarRelatorio = new JButton("Gerar relatorio");
				btnGerarRelatorio.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (calendario.getSelecao() != null) {
							relatorio.setText(geraRelatorio(calendario.getSelecao()));
							errorLabel.setVisible(false);
							geraGrafico();
						} else {
							errorLabel.setForeground(Color.RED);
							errorLabel.setVisible(true);
							errorLabel.setText("Selecione um periodo.");
						}
					}
				});
						
								JLabel lblPeriodo = new JLabel("Periodo:");
								GridBagConstraints gbc_lblPeriodo = new GridBagConstraints();
								gbc_lblPeriodo.insets = new Insets(0, 0, 5, 0);
								gbc_lblPeriodo.gridx = 0;
								gbc_lblPeriodo.gridy = 2;
								panel_1.add(lblPeriodo, gbc_lblPeriodo);
				
						GridBagConstraints gbc_calendario = new GridBagConstraints();
						gbc_calendario.insets = new Insets(0, 0, 5, 0);
						gbc_calendario.gridx = 0;
						gbc_calendario.gridy = 3;
						panel_1.add(calendario, gbc_calendario);
				GridBagConstraints gbc_btnGerarRelatorio = new GridBagConstraints();
				gbc_btnGerarRelatorio.gridx = 0;
				gbc_btnGerarRelatorio.gridy = 4;
				panel_1.add(btnGerarRelatorio, gbc_btnGerarRelatorio);

		panel_2.setBorder(new CompoundBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Relatorio", TitledBorder.LEADING, TitledBorder.TOP, null, null), new EmptyBorder(10, 10, 10, 10)));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 2;
		gbc_panel_2.gridy = 0;
		add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 212, 0 };
		gbl_panel_2.rowHeights = new int[] { 15, 0 };
		gbl_panel_2.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		GridBagConstraints gbc_relatorio = new GridBagConstraints();
		gbc_relatorio.fill = GridBagConstraints.BOTH;
		gbc_relatorio.gridx = 0;
		gbc_relatorio.gridy = 0;
		relatorio.setEditable(false);
		panel_2.add(relatorio, gbc_relatorio);

		panel.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED,
				null, null, null, null), new EmptyBorder(0, 0, 0, 0)));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(0, 0));
						GridBagConstraints gbc_errorLabel = new GridBagConstraints();
						gbc_errorLabel.insets = new Insets(0, 0, 0, 5);
						gbc_errorLabel.gridx = 1;
						gbc_errorLabel.gridy = 2;
						add(errorLabel, gbc_errorLabel);
				
						errorLabel.setVisible(false);
		
				JButton btnVoltar = new JButton("Voltar");
				GridBagConstraints gbc_btnVoltar = new GridBagConstraints();
				gbc_btnVoltar.anchor = GridBagConstraints.EAST;
				gbc_btnVoltar.insets = new Insets(0, 0, 0, 5);
				gbc_btnVoltar.gridx = 2;
				gbc_btnVoltar.gridy = 2;
				add(btnVoltar, gbc_btnVoltar);
				btnVoltar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Sistema.setTela(tela);
					}
				});
	}

	private String geraRelatorio(Periodo periodo) {

		String dataAtual = new SimpleDateFormat("dd/MM/yyyy").format(Calendar
				.getInstance().getTime());
		String data = periodo.toString();

		String texto = "";

		texto += "Data: " + dataAtual + "\n";
		texto += "Relatorio sobre servicos disponiveis\n";
		texto += "Periodo: " + data + "\n\n";

		int babasTotal = Sistema.getHotel().getBabas().size();
		int babasDisponiveis = Sistema.getHotel().getBabasDisponiveis(periodo)
				.size();
		texto += "Babysitters disponiveis: " + babasDisponiveis + "\n";

		int carrosTotal = Sistema.getHotel().getCarros().size();
		int carrosDisponiveis = Sistema.getHotel()
				.getCarrosDisponiveis(periodo).size();
		texto += "Carros disponiveis: " + carrosDisponiveis + "\n";

		int quartosTotal = Sistema.getHotel().getQuartos().size();
		int quartosDisponiveis = Sistema.getHotel()
				.getQuartosDisponiveis(periodo).size();
		texto += "Quartos disponiveis: " + quartosDisponiveis + "\n";

		int camasTotal = Sistema.getHotel().getCamas().size();
		int camasDisponiveis = Sistema.getHotel().getCamasDisponiveis(periodo)
				.size();
		texto += "Camas Extras disponiveis: " + camasDisponiveis + "\n\n";

		int servicosTotal = babasTotal + carrosTotal + quartosTotal
				+ camasTotal;
		int servicosDisponiveis = babasDisponiveis + carrosDisponiveis
				+ quartosDisponiveis + camasDisponiveis;

		texto += "Numero total de servicos: " + servicosTotal + "\n";
		texto += "Servicos disponiveis: " + servicosDisponiveis + "\n";

		porcentagemDisponivel = geraPorcentagem(servicosTotal,
				servicosDisponiveis);

		return texto;
	}

	private double geraPorcentagem(double total, double valor) {
		try {
			return (valor * 100) / (total);
		} catch (Exception e) {
			return 0;
		}
	}

	private void geraGrafico() {

		DefaultPieDataset pieDataset = new DefaultPieDataset();

		pieDataset.setValue("Indisponiveis", 100.0 - porcentagemDisponivel);
		pieDataset.setValue("Disponiveis", porcentagemDisponivel);

		JFreeChart chart = ChartFactory.createPieChart3D(
				"Servicos Disponiveis", // Title
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
}
