package gui.relatorios;

import gui.Sistema;
import gui.components.Calendario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import core.hotel.Opiniao;
import core.tempo.Periodo;

public class OpinioesSobreHotel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JLabel errorLabel = new JLabel("<erro>");

	private JPanel panel = new JPanel();
	private JTextArea relatorio = new JTextArea();

	private int ruim = 0;
	private int poderiaSerMelhor = 0;
	private int medio = 0;
	private int bom = 0;
	private int otimo = 0;
	private Double media = 0.0;
	private final Calendario calendario = new Calendario();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JPanel panel_2 = new JPanel();
	private final JPanel panel_3 = new JPanel();
	public OpinioesSobreHotel(final JPanel tela) {

		setName("Relat\u00F3rio de opini\u00F5es");

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0};
		gridBagLayout.rowHeights = new int[] {30, 0, 200, 0};
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 1.0, 1.0 };
		setLayout(gridBagLayout);

		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 10, 5);
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 1;
		add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] {0, 200};
		gbl_panel_2.rowHeights = new int[]{0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 1.0};
		gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 0, 10);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panel_2.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {0};
		gbl_panel_1.rowHeights = new int[] {0, 0, 0};
		gbl_panel_1.columnWeights = new double[] { 0.0 };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0 };
		panel_1.setLayout(gbl_panel_1);

		JButton btnGerarRelatorio = new JButton("Gerar relat\u00F3rio");
		btnGerarRelatorio.setFocusable(false);
		btnGerarRelatorio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (calendario.getSelecao() != null) {
					relatorio.setText(geraRelatorio(calendario.getSelecao()));
					errorLabel.setVisible(false);
					geraGrafico(calendario.getSelecao());
				} else {
					errorLabel.setVisible(true);
					errorLabel.setText("Selecione um per\u00EDodo");
				}
			}
		});

		JLabel lblPeriodo = new JLabel("Per\u00EDodo:");
		GridBagConstraints gbc_lblPeriodo = new GridBagConstraints();
		gbc_lblPeriodo.anchor = GridBagConstraints.WEST;
		gbc_lblPeriodo.gridx = 0;
		gbc_lblPeriodo.gridy = 0;
		panel_1.add(lblPeriodo, gbc_lblPeriodo);

		GridBagConstraints gbc_calendario = new GridBagConstraints();
		gbc_calendario.insets = new Insets(0, 0, 10, 0);
		gbc_calendario.gridx = 0;
		gbc_calendario.gridy = 1;
		panel_1.add(calendario, gbc_calendario);
		GridBagConstraints gbc_btnGerarRelatorio = new GridBagConstraints();
		gbc_btnGerarRelatorio.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGerarRelatorio.gridx = 0;
		gbc_btnGerarRelatorio.gridy = 2;
		panel_1.add(btnGerarRelatorio, gbc_btnGerarRelatorio);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		panel_2.add(scrollPane, gbc_scrollPane);
		scrollPane.setBorder(new TitledBorder(null, "Relat\u00F3rio", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		relatorio.setBackground(UIManager.getColor("Panel.background"));
		scrollPane.setViewportView(relatorio);
		relatorio.setEditable(false);

		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 10, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 2;
		add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(0, 0));

		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.anchor = GridBagConstraints.NORTH;
		gbc_panel_3.insets = new Insets(0, 0, 0, 5);
		gbc_panel_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 3;
		add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] {200, 0};
		gbl_panel_3.rowHeights = new int[] {0};
		gbl_panel_3.columnWeights = new double[]{0.0, 1.0};
		gbl_panel_3.rowWeights = new double[]{0.0};
		panel_3.setLayout(gbl_panel_3);
		GridBagConstraints gbc_errorLabel = new GridBagConstraints();
		gbc_errorLabel.anchor = GridBagConstraints.WEST;
		gbc_errorLabel.insets = new Insets(0, 0, 0, 5);
		gbc_errorLabel.gridx = 0;
		gbc_errorLabel.gridy = 0;
		panel_3.add(errorLabel, gbc_errorLabel);
		errorLabel.setForeground(Color.RED);
		errorLabel.setIcon(new ImageIcon(OpinioesSobreHotel.class.getResource("/gui/images/error.png")));

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFocusable(false);
		GridBagConstraints gbc_btnVoltar = new GridBagConstraints();
		gbc_btnVoltar.anchor = GridBagConstraints.EAST;
		gbc_btnVoltar.gridx = 1;
		gbc_btnVoltar.gridy = 0;
		panel_3.add(btnVoltar, gbc_btnVoltar);
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Sistema.setTela(tela);
			}
		});

		errorLabel.setVisible(false);
	}

	private String geraRelatorio(Periodo periodo) {

		resetarNotas();

		String dataAtual = new SimpleDateFormat("dd/MM/yyyy").format(Calendar
				.getInstance().getTime());
		String data = periodo.toString();

		String texto = "";

		texto += "Data: " + dataAtual + "\n";
		texto += "Relat\u00F3rio de opini\u00F5es sobre o hotel\n";
		texto += "Per\u00EDodo: " + data + "\n\n";

		setNotas(periodo);

		texto += "Nota 1 - ruim: " + ruim + "\n";
		texto += "Nota 2 - pode melhorar: " + poderiaSerMelhor + "\n";
		texto += "Nota 3 - m\u00E9dio: " + medio + "\n";
		texto += "Nota 4 - bom: " + bom + "\n";
		texto += "Nota 5 - \u00F3timo: " + otimo + "\n\n";

		calculaMedia();
		texto += "M\u00E9dia: " + media;

		return texto;
	}

	private void resetarNotas() {
		ruim = poderiaSerMelhor = medio = bom = otimo = 0;
		media = 0.0;
	}

	private void calculaMedia() {
		media = ((ruim) + (poderiaSerMelhor * 2) + (medio * 3) + (bom * 4) + (otimo * 5))/((ruim + poderiaSerMelhor + medio + bom + otimo) * 1.0);
		if(media.equals(Double.NaN))
			media = 0.0;
	}

	private void setNotas(Periodo periodo) {
		List<Opiniao> opinioes = filtraOpinioes(periodo);

		for (Opiniao opiniao : opinioes) {
			switch (opiniao.getNota()) {
			case (1):
				ruim++;
			break;
			case (2):
				poderiaSerMelhor++;
			break;
			case (3):
				medio++;
			break;
			case (4):
				bom++;
			break;
			case (5):
				otimo++;
			break;
			}
		}

	}

	private List<Opiniao> filtraOpinioes(Periodo periodo) {

		List<Opiniao> resultado = new ArrayList<>();

		for (Opiniao op : Sistema.getHotel().getOpinioes())
			if (periodo.contem(op.getData()))
				resultado.add(op);

		return resultado;
	}

	@SuppressWarnings("deprecation")
	private void geraGrafico(Periodo periodo) {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.setValue(ruim, "Quantidade", "Ruim(1)");
		dataset.setValue(poderiaSerMelhor, "Quantidade", "Pode melhorar(2)");
		dataset.setValue(medio, "Quantidade", "M\u00E9dio(3)");
		dataset.setValue(bom, "Quantidade", "Bom(4)");
		dataset.setValue(otimo, "Quantidade", "\u00D3timo(5)");

		JFreeChart chart = ChartFactory.createBarChart(
				"Opini\u00F5es a respeito do hotel: " + periodo, "Nota",
				"Quantidade", dataset, PlotOrientation.VERTICAL, false, true,
				false);

		ChartPanel chartPanel1 = new ChartPanel(chart);
		panel.removeAll();
		panel.add(chartPanel1, BorderLayout.CENTER);
		panel.validate();

		final CategoryPlot plot = chart.getCategoryPlot();

		class CustomRenderer extends BarRenderer {
			private static final long serialVersionUID = 1L;

			private Paint[] colors;

			public CustomRenderer(final Paint[] colors) {
				this.colors = colors;
			}

			@Override
			public Paint getItemPaint(final int row, final int column) {
				return this.colors[column % this.colors.length];
			}
		}

		final CategoryItemRenderer renderer = new CustomRenderer(
				new Paint[] {Color.red, Color.orange,
						Color.yellow,Color.blue,Color.green}
				);
		renderer.setItemLabelsVisible(true);
		final ItemLabelPosition p = new ItemLabelPosition(
				ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER, 45.0
				);
		renderer.setPositiveItemLabelPosition(p);
		plot.setRenderer(renderer);
	}

}
