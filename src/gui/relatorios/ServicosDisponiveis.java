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
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import core.tempo.Periodo;

public class ServicosDisponiveis extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JLabel errorLabel = new JLabel("<error>");

	private JPanel panel = new JPanel();
	private double porcentagemDisponivel = 0.0;
	private JTextArea relatorio = new JTextArea();
	private final Calendario calendario = new Calendario();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JPanel panel_2 = new JPanel();
	private final JPanel panel_3 = new JPanel();
	public ServicosDisponiveis(final JPanel tela) {

		setName("Relatorio de servicos disponiveis");

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0};
		gridBagLayout.rowHeights = new int[] {30, 0, 200, 0};
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 1.0, 1.0 };
		setLayout(gridBagLayout);

		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 10, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
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

		JButton btnGerarRelatorio = new JButton("Gerar relatorio");
		btnGerarRelatorio.addActionListener(new ActionListener() {
			@Override
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
		errorLabel.setIcon(new ImageIcon(ServicosDisponiveis.class.getResource("/gui/images/error.png")));
		errorLabel.setForeground(Color.RED);

		JButton btnVoltar = new JButton("Voltar");
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
