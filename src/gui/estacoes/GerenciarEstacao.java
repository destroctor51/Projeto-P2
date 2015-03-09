package gui.estacoes;

import gui.Sistema;
import gui.components.CalendarioEstacao;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import core.tempo.Estacao;
import core.tempo.Periodo;

public class GerenciarEstacao extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField textField;
	private JTextField textField_1;
	private CalendarioEstacao calendario;
	private DefaultListModel<Periodo> lista = new DefaultListModel<>();
	private JLabel ErrorLabel;
	private JList<Periodo> list;

	private Estacao backup;

	public GerenciarEstacao() {
		init(new Estacao("", 1), "Adicionar esta\u00E7\u00E3o");
	}

	public GerenciarEstacao(Estacao estacao) {
		backup = estacao;
		init((Estacao) estacao.clone(), "Gerenciar esta\u00E7\u00E3o");
	}

	private void init(final Estacao estacao, String titulo) {
		setName(titulo);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.5, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.5, 1.0 };
		setLayout(gridBagLayout);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.SOUTH;
		gbc_panel_1.insets = new Insets(0, 0, 10, 5);
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {0, 0};
		gbl_panel_1.rowHeights = new int[] {0, 0};
		gbl_panel_1.columnWeights = new double[] { 0.0, 1.0 };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0 };
		panel_1.setLayout(gbl_panel_1);

		JLabel lblNomeDaEstao = new JLabel("Nome:");
		GridBagConstraints gbc_lblNomeDaEstao = new GridBagConstraints();
		gbc_lblNomeDaEstao.anchor = GridBagConstraints.EAST;
		gbc_lblNomeDaEstao.insets = new Insets(0, 0, 10, 10);
		gbc_lblNomeDaEstao.gridx = 0;
		gbc_lblNomeDaEstao.gridy = 0;
		panel_1.add(lblNomeDaEstao, gbc_lblNomeDaEstao);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 10, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		panel_1.add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblTarifaDaEstao = new JLabel("Tarifa (%):");
		GridBagConstraints gbc_lblTarifaDaEstao = new GridBagConstraints();
		gbc_lblTarifaDaEstao.anchor = GridBagConstraints.EAST;
		gbc_lblTarifaDaEstao.insets = new Insets(0, 0, 0, 10);
		gbc_lblTarifaDaEstao.gridx = 0;
		gbc_lblTarifaDaEstao.gridy = 1;
		panel_1.add(lblTarifaDaEstao, gbc_lblTarifaDaEstao);

		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		panel_1.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.insets = new Insets(0, 0, 10, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 1.0 };
		gbl_panel.rowWeights = new double[] { 0.0 };
		panel.setLayout(gbl_panel);

		calendario = new CalendarioEstacao(estacao);
		GridBagConstraints gbc_calendario = new GridBagConstraints();
		gbc_calendario.insets = new Insets(0, 0, 0, 10);
		gbc_calendario.anchor = GridBagConstraints.NORTHWEST;
		gbc_calendario.gridx = 0;
		gbc_calendario.gridy = 0;
		panel.add(calendario, gbc_calendario);

		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 0, 10);
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 0;
		panel.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);

		JButton btnAdicionarPeriodio = new JButton(">>");
		GridBagConstraints gbc_btnAdicionarPeriodio = new GridBagConstraints();
		gbc_btnAdicionarPeriodio.insets = new Insets(0, 0, 10, 0);
		gbc_btnAdicionarPeriodio.gridx = 0;
		gbc_btnAdicionarPeriodio.gridy = 0;
		panel_3.add(btnAdicionarPeriodio, gbc_btnAdicionarPeriodio);
		btnAdicionarPeriodio.setFocusable(false);

		JButton btnRemoverPeriodo = new JButton("<<");
		GridBagConstraints gbc_btnRemoverPeriodo = new GridBagConstraints();
		gbc_btnRemoverPeriodo.gridx = 0;
		gbc_btnRemoverPeriodo.gridy = 1;
		panel_3.add(btnRemoverPeriodo, gbc_btnRemoverPeriodo);
		btnRemoverPeriodo.setFocusable(false);
		btnRemoverPeriodo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(list.isSelectionEmpty()) return;

				Periodo p = list.getSelectedValue();
				estacao.removePeriodo(p);
				calendario.atualizaDias();
				lista.remove(list.getSelectedIndex());

				ErrorLabel.setVisible(false);
			}
		});
		btnAdicionarPeriodio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Periodo periodo = calendario.getSelecao();

				if (periodo == null) return;
				ErrorLabel.setVisible(false);

				lista.addElement(periodo);
				list.setModel(lista);
				estacao.addPeriodo(periodo);
				calendario.atualizaDias();
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 0;
		panel.add(scrollPane, gbc_scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		list = new JList<Periodo>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setVisibleRowCount(-1);
		list.setFocusable(false);
		list.setFixedCellWidth(100);
		scrollPane.setViewportView(list);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.anchor = GridBagConstraints.NORTH;
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 2;
		add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 200, 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 1.0, 0.0 };
		gbl_panel_2.rowWeights = new double[] { 0.0 };
		panel_2.setLayout(gbl_panel_2);

		ErrorLabel = new JLabel("<erro>");
		ErrorLabel.setIcon(new ImageIcon(GerenciarEstacao.class
				.getResource("/gui/resources/error.png")));
		ErrorLabel.setForeground(Color.RED);
		ErrorLabel.setVisible(false);
		GridBagConstraints gbc_lblobservaes = new GridBagConstraints();
		gbc_lblobservaes.anchor = GridBagConstraints.WEST;
		gbc_lblobservaes.insets = new Insets(0, 0, 0, 5);
		gbc_lblobservaes.gridx = 0;
		gbc_lblobservaes.gridy = 0;
		panel_2.add(ErrorLabel, gbc_lblobservaes);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFocusable(false);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(new Estacoes());
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.anchor = GridBagConstraints.EAST;
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 10);
		gbc_btnCancelar.gridx = 1;
		gbc_btnCancelar.gridy = 0;
		panel_2.add(btnCancelar, gbc_btnCancelar);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFocusable(false);
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				double tarifa;
				String nome = textField.getText();

				if (nome.isEmpty()) {
					ErrorLabel.setText("Nome inv\u00EDlido");
					ErrorLabel.setVisible(true);
					return;
				}

				try {
					NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
					Number number = format.parse(textField_1.getText());
					tarifa = number.doubleValue() / 100;
				} catch (ParseException ex) {
					ErrorLabel.setText("Valor de tarifa inv\u00E1lido");
					ErrorLabel.setVisible(true);
					return;
				}

				estacao.setId(nome);
				estacao.setTarifa(tarifa);

				Sistema.getHotel().removeEstacao(backup);
				Sistema.getHotel().adicionaEstacao(estacao);
				Sistema.setTela(new Estacoes());
			}
		});
		GridBagConstraints gbc_btnConfirmar = new GridBagConstraints();
		gbc_btnConfirmar.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnConfirmar.gridx = 2;
		gbc_btnConfirmar.gridy = 0;
		panel_2.add(btnConfirmar, gbc_btnConfirmar);

		DecimalFormat df = new DecimalFormat("0.00");

		textField.setText(estacao.getId());
		textField_1.setText(df.format(estacao.getTarifa() * 100));

		for(Periodo p : estacao.getPeriodos()) {
			lista.addElement(p);
			list.setModel(lista);
		}

		calendario.atualizaDias();
	}

}