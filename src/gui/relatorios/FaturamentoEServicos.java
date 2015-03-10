package gui.relatorios;

import gui.Sistema;
import gui.relatorios.strategy.Estrategia;
import gui.relatorios.strategy.Faturamento;
import gui.relatorios.strategy.Servicos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import core.tempo.Estacao;


public class FaturamentoEServicos extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JLabel errorLabel = new JLabel("<erro>");

	private JPanel panel = new JPanel();

	private final JTextArea relatorio = new JTextArea();

	private final JButton btnEstacoes = new JButton(
			"Relat\u00F3rio da esta\u00E7\u00E3o");
	private final JButton btnRelatorioAnual = new JButton(
			"Relat\u00F3rio anual");
	private final JButton btnRelatorioMensal = new JButton(
			"Relat\u00F3rio mensal");

	private final JComboBox<String> cbRelatorioMensal = new JComboBox<>();
	private final JComboBox<Estacao> cbRelatorioEstacoes = new JComboBox<>();
	private final JComboBox<Integer> cbRelatorioAnual = new JComboBox<>();

	private final Calendar dataAtual = new GregorianCalendar();
	private final JRadioButton checkFaturamento = new JRadioButton(
			"Faturamento");
	private final JRadioButton checkServicos = new JRadioButton("Servi\u00E7os");

	private Estrategia estrategia = new Faturamento();

	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final JPanel panel_2 = new JPanel();
	private final JPanel panel_3 = new JPanel();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JPanel panel_4 = new JPanel();

	public FaturamentoEServicos(final JPanel tela) {

		setName("Relat\u00F3rio de faturamento");

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {-4, 909, 0};
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
		panel_1.setBorder(new EmptyBorder(14, 0, 0, 0));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.insets = new Insets(0, 0, 0, 10);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panel_2.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {0};
		gbl_panel_1.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[] { 1.0 };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0 };
		panel_1.setLayout(gbl_panel_1);

		GridBagConstraints gbc_cbFaturaAnual = new GridBagConstraints();
		gbc_cbFaturaAnual.insets = new Insets(0, 0, 5, 0);
		gbc_cbFaturaAnual.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbFaturaAnual.gridx = 0;
		gbc_cbFaturaAnual.gridy = 0;
		cbRelatorioAnual.setFocusable(false);
		panel_1.add(cbRelatorioAnual, gbc_cbFaturaAnual);
		btnRelatorioAnual.setFocusable(false);

		btnRelatorioAnual.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (cbRelatorioAnual.getSelectedIndex() != -1) {
					relatorio.setText(estrategia.geraRelatorioAnual(cbRelatorioAnual));
					errorLabel.setVisible(false);
					estrategia.geraGraficoAnual(panel,cbRelatorioAnual);
				} else {
					errorLabel.setVisible(true);
					errorLabel.setText("Selecione um ano");
				}
			}
		});

		GridBagConstraints gbc_btnFaturaAnual = new GridBagConstraints();
		gbc_btnFaturaAnual.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnFaturaAnual.insets = new Insets(0, 0, 10, 0);
		gbc_btnFaturaAnual.gridx = 0;
		gbc_btnFaturaAnual.gridy = 1;
		panel_1.add(btnRelatorioAnual, gbc_btnFaturaAnual);

		GridBagConstraints gbc_cbFaturaMensal = new GridBagConstraints();
		gbc_cbFaturaMensal.insets = new Insets(0, 0, 5, 0);
		gbc_cbFaturaMensal.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbFaturaMensal.gridx = 0;
		gbc_cbFaturaMensal.gridy = 2;
		cbRelatorioMensal.setFocusable(false);
		panel_1.add(cbRelatorioMensal, gbc_cbFaturaMensal);
		btnRelatorioMensal.setFocusable(false);

		btnRelatorioMensal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (cbRelatorioMensal.getSelectedIndex() != -1) {
					relatorio.setText(estrategia.geraRelatorioMensal(cbRelatorioMensal,(int)cbRelatorioAnual.getSelectedItem()));
					errorLabel.setVisible(false);
					estrategia.geraGraficoMensal(panel,cbRelatorioMensal);
				} else {
					errorLabel.setVisible(true);
					errorLabel.setText("Selecione um m\u00EAs");
				}
			}
		});
		GridBagConstraints gbc_btnGerarRelatorio = new GridBagConstraints();
		gbc_btnGerarRelatorio.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGerarRelatorio.insets = new Insets(0, 0, 10, 0);
		gbc_btnGerarRelatorio.gridx = 0;
		gbc_btnGerarRelatorio.gridy = 3;
		panel_1.add(btnRelatorioMensal, gbc_btnGerarRelatorio);

		GridBagConstraints gbc_cbFaturaEstacoes = new GridBagConstraints();
		gbc_cbFaturaEstacoes.insets = new Insets(0, 0, 5, 0);
		gbc_cbFaturaEstacoes.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbFaturaEstacoes.gridx = 0;
		gbc_cbFaturaEstacoes.gridy = 4;
		cbRelatorioEstacoes.setFocusable(false);
		panel_1.add(cbRelatorioEstacoes, gbc_cbFaturaEstacoes);

		// set cbFaturaAnual
		for (int i = 2014; i <= dataAtual.get(Calendar.YEAR); i++) {
			cbRelatorioAnual.addItem(i);
		}

		// set cbFaturaEstacoes
		Iterator<Estacao> it = Sistema.getHotel().getTarifas();
		while (it.hasNext()) {
			cbRelatorioEstacoes.addItem(it.next());
		}

		// set cbFaturaMensal
		String[] e = { "Janeiro", "Fevereiro", "Mar\u00E7o", "Abril", "Maio",
				"Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro",
		"Dezembro" };
		ComboBoxModel<String> meses = new DefaultComboBoxModel<>(e);

		GridBagConstraints gbc_btnEstacoes = new GridBagConstraints();
		gbc_btnEstacoes.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEstacoes.insets = new Insets(0, 0, 10, 0);
		gbc_btnEstacoes.gridx = 0;
		gbc_btnEstacoes.gridy = 5;
		btnEstacoes.setFocusable(false);
		btnEstacoes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (cbRelatorioEstacoes.getSelectedIndex() != -1) {
					relatorio
					.setText(estrategia.geraRelatorioEstacao((Estacao)cbRelatorioEstacoes
							.getSelectedItem(), (int)cbRelatorioAnual.getSelectedItem()));
					errorLabel.setVisible(false);
					estrategia.geraGraficoEstacao(panel,cbRelatorioEstacoes);
				} else {
					errorLabel.setVisible(true);
					errorLabel.setText("Selecione uma esta\u00E7\u00E3o");
				}
			}
		});
		panel_1.add(btnEstacoes, gbc_btnEstacoes);
		cbRelatorioAnual.setSelectedItem(dataAtual.get(Calendar.YEAR));
		cbRelatorioMensal.setModel(meses);

		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 6;
		panel_1.add(panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{0, 0, 0};
		gbl_panel_4.rowHeights = new int[]{0, 0};
		gbl_panel_4.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		buttonGroup.add(checkFaturamento);
		GridBagConstraints gbc_checkFaturamento = new GridBagConstraints();
		gbc_checkFaturamento.anchor = GridBagConstraints.WEST;
		gbc_checkFaturamento.insets = new Insets(0, 0, 0, 5);
		gbc_checkFaturamento.gridx = 0;
		gbc_checkFaturamento.gridy = 0;
		checkFaturamento.setFocusable(false);
		panel_4.add(checkFaturamento, gbc_checkFaturamento);
		checkFaturamento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				atualizaTela(new Faturamento());
			}
		});
		checkFaturamento.setSelected(true);
		buttonGroup.add(checkServicos);
		GridBagConstraints gbc_checkServicos = new GridBagConstraints();
		gbc_checkServicos.anchor = GridBagConstraints.WEST;
		gbc_checkServicos.gridx = 1;
		gbc_checkServicos.gridy = 0;
		checkServicos.setFocusable(false);
		panel_4.add(checkServicos, gbc_checkServicos);
		checkServicos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				atualizaTela(new Servicos());
			}
		});
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		panel_2.add(scrollPane, gbc_scrollPane);
		scrollPane.setBorder(new TitledBorder(null, "Relat\u00F3rio", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		relatorio.setBorder(new EmptyBorder(0, 10, 0, 10));
		scrollPane.setViewportView(relatorio);
		relatorio.setBackground(UIManager.getColor("Panel.background"));
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
		gbc_panel_3.insets = new Insets(0, 0, 0, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 3;
		add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] {200, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 1.0};
		gbl_panel_3.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		GridBagConstraints gbc_errorLabel = new GridBagConstraints();
		gbc_errorLabel.anchor = GridBagConstraints.WEST;
		gbc_errorLabel.insets = new Insets(0, 0, 0, 5);
		gbc_errorLabel.gridx = 0;
		gbc_errorLabel.gridy = 0;
		errorLabel.setIcon(new ImageIcon(FaturamentoEServicos.class.getResource("/gui/resources/error.png")));
		errorLabel.setForeground(Color.RED);
		panel_3.add(errorLabel, gbc_errorLabel);

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

	private void atualizaTela(Estrategia estrategia) {

		this.estrategia = estrategia;
		if (estrategia instanceof Servicos)
			setName("Relat\u00F3rio de servicos dispon\u00EDveis");
		else if(estrategia instanceof Faturamento)
			setName("Relat\u00F3rio de Faturamento");
	}
}
