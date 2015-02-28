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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import core.tempo.Estacao;


public class FaturamentoEServicos extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel errorLabel = new JLabel("errorLabel");
	private JPanel panel_2 = new JPanel();

	private JPanel panel = new JPanel();

	private final JTextArea relatorio = new JTextArea();

	private final JButton btnEstacoes = new JButton(
			"Relatorio de faturamento da estacao");
	private final JButton btnRelatorioAnual = new JButton(
			"Relatorio de faturamento anual");
	private final JButton btnRelatorioMensal = new JButton(
			"Relatorio de faturamento mensal");

	private final JComboBox<String> cbRelatorioMensal = new JComboBox<>();
	private final JComboBox<Estacao> cbRelatorioEstacoes = new JComboBox<>();
	private final JComboBox<Integer> cbRelatorioAnual = new JComboBox<>();

	private final Calendar dataAtual = new GregorianCalendar();
	private final JRadioButton checkFaturamento = new JRadioButton(
			"Faturamento");
	private final JRadioButton checkServicos = new JRadioButton("Servicos");

	private Estrategia estrategia = new Faturamento();

	private final ButtonGroup buttonGroup = new ButtonGroup();

	public FaturamentoEServicos(final JPanel tela) {

		setName("Relatorio de Faturamento");

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 261, 156, 0, 0, 40, 0 };
		gridBagLayout.rowHeights = new int[] { 332, 165, 56, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 277, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		GridBagConstraints gbc_cbFaturaAnual = new GridBagConstraints();
		gbc_cbFaturaAnual.insets = new Insets(0, 0, 5, 0);
		gbc_cbFaturaAnual.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbFaturaAnual.gridx = 0;
		gbc_cbFaturaAnual.gridy = 1;
		panel_1.add(cbRelatorioAnual, gbc_cbFaturaAnual);

		btnRelatorioAnual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cbRelatorioAnual.getSelectedIndex() != -1) {
					relatorio.setText(estrategia.geraRelatorioAnual(cbRelatorioAnual));
					errorLabel.setVisible(false);
					estrategia.geraGraficoAnual(panel,cbRelatorioAnual);
				} else {
					errorLabel.setForeground(Color.RED);
					errorLabel.setVisible(true);
					errorLabel.setText("Selecione um ano.");
				}
			}
		});

		GridBagConstraints gbc_btnFaturaAnual = new GridBagConstraints();
		gbc_btnFaturaAnual.insets = new Insets(0, 0, 5, 0);
		gbc_btnFaturaAnual.gridx = 0;
		gbc_btnFaturaAnual.gridy = 2;
		panel_1.add(btnRelatorioAnual, gbc_btnFaturaAnual);

		GridBagConstraints gbc_cbFaturaMensal = new GridBagConstraints();
		gbc_cbFaturaMensal.insets = new Insets(0, 0, 5, 0);
		gbc_cbFaturaMensal.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbFaturaMensal.gridx = 0;
		gbc_cbFaturaMensal.gridy = 4;
		panel_1.add(cbRelatorioMensal, gbc_cbFaturaMensal);

		btnRelatorioMensal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cbRelatorioMensal.getSelectedIndex() != -1) {
					relatorio.setText(estrategia.geraRelatorioMensal(cbRelatorioMensal,(int)cbRelatorioAnual.getSelectedItem()));
					errorLabel.setVisible(false);
					estrategia.geraGraficoMensal(panel,cbRelatorioMensal);
				} else {
					errorLabel.setForeground(Color.RED);
					errorLabel.setVisible(true);
					errorLabel.setText("Selecione um mes.");
				}
			}
		});
		GridBagConstraints gbc_btnGerarRelatorio = new GridBagConstraints();
		gbc_btnGerarRelatorio.insets = new Insets(0, 0, 5, 0);
		gbc_btnGerarRelatorio.gridx = 0;
		gbc_btnGerarRelatorio.gridy = 5;
		panel_1.add(btnRelatorioMensal, gbc_btnGerarRelatorio);

		GridBagConstraints gbc_cbFaturaEstacoes = new GridBagConstraints();
		gbc_cbFaturaEstacoes.insets = new Insets(0, 0, 5, 0);
		gbc_cbFaturaEstacoes.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbFaturaEstacoes.gridx = 0;
		gbc_cbFaturaEstacoes.gridy = 7;
		panel_1.add(cbRelatorioEstacoes, gbc_cbFaturaEstacoes);

		GridBagConstraints gbc_btnEstacoes = new GridBagConstraints();
		gbc_btnEstacoes.insets = new Insets(0, 0, 5, 0);
		gbc_btnEstacoes.gridx = 0;
		gbc_btnEstacoes.gridy = 8;
		btnEstacoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cbRelatorioEstacoes.getSelectedIndex() != -1) {
					relatorio
							.setText(estrategia.geraRelatorioEstacao((Estacao)cbRelatorioEstacoes
									.getSelectedItem(), (int)cbRelatorioAnual.getSelectedItem()));
					errorLabel.setVisible(false);
					estrategia.geraGraficoEstacao(panel,cbRelatorioEstacoes);
				} else {
					errorLabel.setForeground(Color.RED);
					errorLabel.setVisible(true);
					errorLabel.setText("Selecione uma estacao.");
				}
			}
		});
		panel_1.add(btnEstacoes, gbc_btnEstacoes);

		panel_2.setBorder(new CompoundBorder(new TitledBorder(new LineBorder(
				new Color(184, 207, 229)), "Relatorio", TitledBorder.LEADING,
				TitledBorder.TOP, null, null), new EmptyBorder(10, 10, 10, 10)));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 3;
		gbc_panel_2.gridy = 0;
		add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 264, 0 };
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
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(0, 0));
		GridBagConstraints gbc_errorLabel = new GridBagConstraints();
		gbc_errorLabel.insets = new Insets(0, 0, 0, 5);
		gbc_errorLabel.gridx = 2;
		gbc_errorLabel.gridy = 2;
		add(errorLabel, gbc_errorLabel);

		errorLabel.setVisible(false);

		JButton btnVoltar = new JButton("Voltar");
		GridBagConstraints gbc_btnVoltar = new GridBagConstraints();
		gbc_btnVoltar.insets = new Insets(0, 0, 0, 5);
		gbc_btnVoltar.gridx = 3;
		gbc_btnVoltar.gridy = 2;
		add(btnVoltar, gbc_btnVoltar);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Sistema.setTela(tela);
			}
		});

		// set cbFaturaAnual
		for (int i = 2014; i <= dataAtual.get(Calendar.YEAR); i++) {
			cbRelatorioAnual.addItem(i);
		}
		cbRelatorioAnual.setSelectedItem(dataAtual.get(Calendar.YEAR));

		// set cbFaturaEstacoes
		Iterator<Estacao> it = Sistema.getHotel().getTarifas();
		while (it.hasNext()) {
			cbRelatorioEstacoes.addItem(it.next());
		}

		// set cbFaturaMensal
		String[] e = { "Janeiro", "Fevereiro", "Marco", "Abril", "Maio",
				"Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro",
				"Dezembro" };
		ComboBoxModel<String> meses = new DefaultComboBoxModel<>(e);
		cbRelatorioMensal.setModel(meses);

		GridBagConstraints gbc_checkFaturamento = new GridBagConstraints();
		gbc_checkFaturamento.insets = new Insets(0, 0, 5, 0);
		gbc_checkFaturamento.gridx = 0;
		gbc_checkFaturamento.gridy = 9;
		buttonGroup.add(checkFaturamento);
		checkFaturamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atualizaTela(new Faturamento());
			}
		});
		checkFaturamento.setSelected(true);
		panel_1.add(checkFaturamento, gbc_checkFaturamento);

		GridBagConstraints gbc_checkServicos = new GridBagConstraints();
		gbc_checkServicos.gridx = 0;
		gbc_checkServicos.gridy = 10;
		buttonGroup.add(checkServicos);
		checkServicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizaTela(new Servicos());
			}
		});
		panel_1.add(checkServicos, gbc_checkServicos);
	}

	private void atualizaTela(Estrategia estrategia) {

		this.estrategia = estrategia;
		if (estrategia instanceof Servicos) {
			btnEstacoes.setText("Relatorio de servicos da estacao");
			btnRelatorioAnual.setText("Relatorio de servicos anual");
			btnRelatorioMensal.setText("Relatorio de servicos mensal");
			setName("Relatorio de servicos disponiveis");
		} else if(estrategia instanceof Faturamento){
			btnEstacoes.setText("Relatorio de faturamento da estacao");
			btnRelatorioAnual.setText("Relatorio de faturamento anual");
			btnRelatorioMensal.setText("Relatorio de faturamento mensal");
			setName("Relatorio de Faturamento");
			
		}
	}
}
