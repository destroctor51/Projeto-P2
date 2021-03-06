package gui.contratos;

import gui.Menu;
import gui.Sistema;
import gui.components.CalendarioAlugavel;
import gui.components.SuperTextField;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;

import utils.Filtro;
import core.hotel.Contrato;
import core.hotel.Hospede;
import core.servicos.devolviveis.Quarto;
import core.tempo.Estacao;
import core.tempo.Periodo;

public class RealizarReserva extends JPanel {
	private static final long serialVersionUID = 1L;
	private SuperTextField NameField;
	private JTextField cardField;
	private Hospede hospede;
	private Periodo estadia;
	private JList<Hospede> list_1;

	private JLabel ErrorLabel;
	private CalendarioAlugavel calendarioAlugavel;
	private JSpinner spinner;
	private JSpinner spinner_1;
	private JSpinner spinner_2;
	private JSpinner spinner_3;
	private JSpinner spinner_4;
	private JSpinner spinner_5;
	private JSpinner spinner_6;

	/**
	 * Create the panel.
	 */
	public RealizarReserva() {

		this.setName("Realizar Reserva");

		addAncestorListener(new AncestorListener() {
			@Override
			public void ancestorAdded(AncestorEvent arg0) {
				Filtro.exibeFiltrado(NameField.getText(), Sistema.getHotel()
						.getHospedes(), list_1);
			}

			@Override
			public void ancestorMoved(AncestorEvent arg0) {
			}

			@Override
			public void ancestorRemoved(AncestorEvent arg0) {
			}
		});
		setVisible(false);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {30, 400, 30};
		gridBagLayout.rowHeights = new int[] {0, 120, 0, 0, 0};
		gridBagLayout.columnWeights = new double[] { 1.0, 0.25, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.25, 0.1, 0.0, 1.0 };
		setLayout(gridBagLayout);

		JPanel panel_1 = new JPanel();
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 1.0 };
		gbl_panel_1.rowWeights = new double[] { 0.0 };
		panel_1.setLayout(gbl_panel_1);

		JLabel lblProcurarHspede = new JLabel("Procurar H\u00F3spede:");
		lblProcurarHspede.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblProcurarHspede = new GridBagConstraints();
		gbc_lblProcurarHspede.anchor = GridBagConstraints.EAST;
		gbc_lblProcurarHspede.insets = new Insets(0, 0, 0, 10);
		gbc_lblProcurarHspede.gridx = 0;
		gbc_lblProcurarHspede.gridy = 0;
		panel_1.add(lblProcurarHspede, gbc_lblProcurarHspede);

		NameField = new SuperTextField() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void textChanged(String text) {
				Filtro.exibeFiltrado(text, Sistema.getHotel().getHospedes(),
						list_1);
			}
		};
		GridBagConstraints gbc_NameField = new GridBagConstraints();
		gbc_NameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_NameField.anchor = GridBagConstraints.NORTH;
		gbc_NameField.gridx = 1;
		gbc_NameField.gridy = 0;
		panel_1.add(NameField, gbc_NameField);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.SOUTH;
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.insets = new Insets(0, 0, 10, 5);
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		list_1 = new JList<>();
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_1.setVisibleRowCount(-1);
		list_1.setFixedCellWidth(100);
		list_1.setFocusable(false);
		list_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Hospede hosp = list_1.getSelectedValue();
				if (hosp != null)
					setHospede(hosp);
			}
		});
		scrollPane_1.setViewportView(list_1);
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.insets = new Insets(0, 0, 10, 5);
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 1;
		add(scrollPane_1, gbc_scrollPane_1);

		JPanel panel_5 = new JPanel();
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[] { 200, 0, 0 };
		gbl_panel_5.rowHeights = new int[] { 0 };
		gbl_panel_5.columnWeights = new double[] { 0.0, 1.0, 0.0 };
		gbl_panel_5.rowWeights = new double[] { 0.0 };
		panel_5.setLayout(gbl_panel_5);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFocusable(false);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(new Menu(true));
			}
		});

		ErrorLabel = new JLabel("<erro>");
		GridBagConstraints gbc_ErrorLabel = new GridBagConstraints();
		gbc_ErrorLabel.anchor = GridBagConstraints.WEST;
		gbc_ErrorLabel.insets = new Insets(0, 0, 0, 5);
		gbc_ErrorLabel.gridx = 0;
		gbc_ErrorLabel.gridy = 0;
		panel_5.add(ErrorLabel, gbc_ErrorLabel);
		ErrorLabel.setForeground(Color.RED);
		ErrorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		ErrorLabel.setVisible(false);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.anchor = GridBagConstraints.SOUTH;
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 2;
		add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 1.0, 0.0 };
		gbl_panel_2.rowWeights = new double[] { 0.0 };
		panel_2.setLayout(gbl_panel_2);

		JLabel lblNewLabel_1 = new JLabel("Reserva dos quartos:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel_2.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JLabel lblCartoDeCrdito = new JLabel("Cart\u00E3o de cr\u00E9dito:");
		GridBagConstraints gbc_lblCartoDeCrdito = new GridBagConstraints();
		gbc_lblCartoDeCrdito.anchor = GridBagConstraints.EAST;
		gbc_lblCartoDeCrdito.insets = new Insets(0, 0, 0, 10);
		gbc_lblCartoDeCrdito.gridx = 1;
		gbc_lblCartoDeCrdito.gridy = 0;
		panel_2.add(lblCartoDeCrdito, gbc_lblCartoDeCrdito);

		cardField = new JTextField();
		cardField.setColumns(20);
		GridBagConstraints gbc_cardField = new GridBagConstraints();
		gbc_cardField.fill = GridBagConstraints.HORIZONTAL;
		gbc_cardField.gridx = 2;
		gbc_cardField.gridy = 0;
		panel_2.add(cardField, gbc_cardField);

		JPanel panel = new JPanel();
		panel.setBorder(new CompoundBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(5, 5, 5, 5)));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 10, 5);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 3;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0, 0, 200};
		gbl_panel.rowHeights = new int[] {0};
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0 };
		gbl_panel.rowWeights = new double[] { 1.0 };
		panel.setLayout(gbl_panel);

		calendarioAlugavel = new CalendarioAlugavel();
		GridBagConstraints gbc_calendarioAlugavel = new GridBagConstraints();
		gbc_calendarioAlugavel.insets = new Insets(0, 0, 0, 5);
		gbc_calendarioAlugavel.anchor = GridBagConstraints.EAST;
		gbc_calendarioAlugavel.gridx = 0;
		gbc_calendarioAlugavel.gridy = 0;
		panel.add(calendarioAlugavel, gbc_calendarioAlugavel);

		calendarioAlugavel.initCategoria("quartop", 0);
		calendarioAlugavel.initCategoria("quartoes", 0);
		calendarioAlugavel.initCategoria("quartoed", 0);
		calendarioAlugavel.initCategoria("quartoet", 0);
		calendarioAlugavel.initCategoria("quartols", 0);
		calendarioAlugavel.initCategoria("quartold", 0);
		calendarioAlugavel.initCategoria("quartolt", 0);

		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 2;
		gbc_panel_3.gridy = 0;
		panel.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] {0, 50};
		gbl_panel_3.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
		gbl_panel_3.columnWeights = new double[] { 1.0, 0.0 };
		gbl_panel_3.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				1.0 };
		panel_3.setLayout(gbl_panel_3);

		JLabel lblNewLabel = new JLabel("Presidenciais:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 10);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_3.add(lblNewLabel, gbc_lblNewLabel);

		spinner = new JSpinner();
		spinner.setFocusable(false);
		spinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				int num = (int) spinner.getValue();
				calendarioAlugavel.setRequisito("quartop", num);
			}
		});
		spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0),
				null, new Integer(1)));
		spinner.setEditor(new JSpinner.DefaultEditor(spinner));
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner.insets = new Insets(0, 0, 5, 0);
		gbc_spinner.gridx = 1;
		gbc_spinner.gridy = 0;
		panel_3.add(spinner, gbc_spinner);

		JLabel lblQuartosLuxoSimples = new JLabel("Luxo Simples:");
		GridBagConstraints gbc_lblQuartosLuxoSimples = new GridBagConstraints();
		gbc_lblQuartosLuxoSimples.anchor = GridBagConstraints.WEST;
		gbc_lblQuartosLuxoSimples.insets = new Insets(0, 0, 5, 10);
		gbc_lblQuartosLuxoSimples.gridx = 0;
		gbc_lblQuartosLuxoSimples.gridy = 1;
		panel_3.add(lblQuartosLuxoSimples, gbc_lblQuartosLuxoSimples);

		spinner_1 = new JSpinner();
		spinner_1.setFocusable(false);
		spinner_1.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				int num = (int) spinner_1.getValue();
				calendarioAlugavel.setRequisito("quartols", num);
			}
		});
		spinner_1.setModel(new SpinnerNumberModel(new Integer(0),
				new Integer(0), null, new Integer(1)));
		spinner_1.setEditor(new JSpinner.DefaultEditor(spinner_1));
		GridBagConstraints gbc_spinner_1 = new GridBagConstraints();
		gbc_spinner_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_1.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_1.gridx = 1;
		gbc_spinner_1.gridy = 1;
		panel_3.add(spinner_1, gbc_spinner_1);

		JLabel lblQuartosLuxoDuplo = new JLabel("Luxo Duplo:");
		GridBagConstraints gbc_lblQuartosLuxoDuplo = new GridBagConstraints();
		gbc_lblQuartosLuxoDuplo.anchor = GridBagConstraints.WEST;
		gbc_lblQuartosLuxoDuplo.insets = new Insets(0, 0, 5, 10);
		gbc_lblQuartosLuxoDuplo.gridx = 0;
		gbc_lblQuartosLuxoDuplo.gridy = 2;
		panel_3.add(lblQuartosLuxoDuplo, gbc_lblQuartosLuxoDuplo);

		spinner_2 = new JSpinner();
		spinner_2.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				int num = (int) spinner_2.getValue();
				calendarioAlugavel.setRequisito("quartold", num);
			}
		});
		spinner_2.setModel(new SpinnerNumberModel(new Integer(0),
				new Integer(0), null, new Integer(1)));
		spinner_2.setEditor(new JSpinner.DefaultEditor(spinner_2));
		GridBagConstraints gbc_spinner_2 = new GridBagConstraints();
		gbc_spinner_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_2.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_2.gridx = 1;
		gbc_spinner_2.gridy = 2;
		panel_3.add(spinner_2, gbc_spinner_2);

		JLabel lblQuartosLuxoTriplo = new JLabel("Luxo Triplo:");
		GridBagConstraints gbc_lblQuartosLuxoTriplo = new GridBagConstraints();
		gbc_lblQuartosLuxoTriplo.anchor = GridBagConstraints.WEST;
		gbc_lblQuartosLuxoTriplo.insets = new Insets(0, 0, 5, 10);
		gbc_lblQuartosLuxoTriplo.gridx = 0;
		gbc_lblQuartosLuxoTriplo.gridy = 3;
		panel_3.add(lblQuartosLuxoTriplo, gbc_lblQuartosLuxoTriplo);

		spinner_3 = new JSpinner();
		spinner_3.setFocusable(false);
		spinner_3.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				int num = (int) spinner_3.getValue();
				calendarioAlugavel.setRequisito("quartolt", num);
			}
		});
		spinner_3.setModel(new SpinnerNumberModel(new Integer(0),
				new Integer(0), null, new Integer(1)));
		spinner_3.setEditor(new JSpinner.DefaultEditor(spinner_3));
		GridBagConstraints gbc_spinner_3 = new GridBagConstraints();
		gbc_spinner_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_3.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_3.gridx = 1;
		gbc_spinner_3.gridy = 3;
		panel_3.add(spinner_3, gbc_spinner_3);

		JLabel lblQuartosExecutivoSimples = new JLabel(
				"Executivo Simples:");
		GridBagConstraints gbc_lblQuartosExecutivoSimples = new GridBagConstraints();
		gbc_lblQuartosExecutivoSimples.anchor = GridBagConstraints.WEST;
		gbc_lblQuartosExecutivoSimples.insets = new Insets(0, 0, 5, 10);
		gbc_lblQuartosExecutivoSimples.gridx = 0;
		gbc_lblQuartosExecutivoSimples.gridy = 4;
		panel_3.add(lblQuartosExecutivoSimples, gbc_lblQuartosExecutivoSimples);

		spinner_4 = new JSpinner();
		spinner_4.setFocusable(false);
		spinner_4.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				int num = (int) spinner_4.getValue();
				calendarioAlugavel.setRequisito("quartoes", num);
			}
		});
		spinner_4.setModel(new SpinnerNumberModel(new Integer(0),
				new Integer(0), null, new Integer(1)));
		spinner_4.setEditor(new JSpinner.DefaultEditor(spinner_4));
		GridBagConstraints gbc_spinner_4 = new GridBagConstraints();
		gbc_spinner_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_4.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_4.gridx = 1;
		gbc_spinner_4.gridy = 4;
		panel_3.add(spinner_4, gbc_spinner_4);

		JLabel lblQuartosExecutivoDuplo = new JLabel("Executivo Duplo:");
		GridBagConstraints gbc_lblQuartosExecutivoDuplo = new GridBagConstraints();
		gbc_lblQuartosExecutivoDuplo.anchor = GridBagConstraints.WEST;
		gbc_lblQuartosExecutivoDuplo.insets = new Insets(0, 0, 5, 10);
		gbc_lblQuartosExecutivoDuplo.gridx = 0;
		gbc_lblQuartosExecutivoDuplo.gridy = 5;
		panel_3.add(lblQuartosExecutivoDuplo, gbc_lblQuartosExecutivoDuplo);

		spinner_5 = new JSpinner();
		spinner_5.setFocusable(false);
		spinner_5.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				int num = (int) spinner_5.getValue();
				calendarioAlugavel.setRequisito("quartoed", num);
			}
		});
		spinner_5.setModel(new SpinnerNumberModel(new Integer(0),
				new Integer(0), null, new Integer(1)));
		spinner_5.setEditor(new JSpinner.DefaultEditor(spinner_5));
		GridBagConstraints gbc_spinner_5 = new GridBagConstraints();
		gbc_spinner_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_5.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_5.gridx = 1;
		gbc_spinner_5.gridy = 5;
		panel_3.add(spinner_5, gbc_spinner_5);

		JLabel lblQuartosExecutivoTriplo = new JLabel(
				"Executivo Triplo:");
		GridBagConstraints gbc_lblQuartosExecutivoTriplo = new GridBagConstraints();
		gbc_lblQuartosExecutivoTriplo.anchor = GridBagConstraints.WEST;
		gbc_lblQuartosExecutivoTriplo.insets = new Insets(0, 0, 0, 10);
		gbc_lblQuartosExecutivoTriplo.gridx = 0;
		gbc_lblQuartosExecutivoTriplo.gridy = 6;
		panel_3.add(lblQuartosExecutivoTriplo, gbc_lblQuartosExecutivoTriplo);

		spinner_6 = new JSpinner();
		spinner_6.setFocusable(false);
		spinner_6.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				int num = (int) spinner_6.getValue();
				calendarioAlugavel.setRequisito("quartoet", num);
			}
		});
		spinner_6.setModel(new SpinnerNumberModel(new Integer(0),
				new Integer(0), null, new Integer(1)));
		spinner_6.setEditor(new JSpinner.DefaultEditor(spinner_6));
		GridBagConstraints gbc_spinner_6 = new GridBagConstraints();
		gbc_spinner_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_6.gridx = 1;
		gbc_spinner_6.gridy = 6;
		panel_3.add(spinner_6, gbc_spinner_6);
		ErrorLabel.setIcon(new ImageIcon(RealizarReserva.class
				.getResource("/gui/resources/error.png")));
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.anchor = GridBagConstraints.EAST;
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 10);
		gbc_btnCancelar.gridx = 1;
		gbc_btnCancelar.gridy = 0;
		panel_5.add(btnCancelar, gbc_btnCancelar);
		btnCancelar.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.anchor = GridBagConstraints.NORTH;
		gbc_panel_5.insets = new Insets(0, 0, 0, 5);
		gbc_panel_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_5.gridx = 1;
		gbc_panel_5.gridy = 4;
		add(panel_5, gbc_panel_5);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFocusable(false);
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					continuar();
				} catch (Exception e) {
				}
			}
		});
		GridBagConstraints gbc_btnConfirmar = new GridBagConstraints();
		gbc_btnConfirmar.gridx = 2;
		gbc_btnConfirmar.gridy = 0;
		panel_5.add(btnConfirmar, gbc_btnConfirmar);
		btnConfirmar.setHorizontalAlignment(SwingConstants.RIGHT);
		int quantp = 0, quantls = 0, quantld = 0, quantlt = 0, quantes = 0, quanted = 0, quantet = 0;

		for (Quarto q : Sistema.getHotel().getQuartos()) {
			switch (q.getTipo()) {
			case PRESIDENCIAL:
				calendarioAlugavel.adicionaElemento("quartop", q);
				quantp++;
				break;
			case LUXO_SIMPLES:
				calendarioAlugavel.adicionaElemento("quartols", q);
				quantls++;
				break;
			case LUXO_DUPLO:
				calendarioAlugavel.adicionaElemento("quartold", q);
				quantld++;
				break;
			case LUXO_TRIPLO:
				calendarioAlugavel.adicionaElemento("quartolt", q);
				quantlt++;
				break;
			case EXECUTIVO_SIMPLES:
				calendarioAlugavel.adicionaElemento("quartoes", q);
				quantes++;
				break;
			case EXECUTIVO_DUPLO:
				calendarioAlugavel.adicionaElemento("quartoed", q);
				quanted++;
				break;
			case EXECUTIVO_TRIPLO:
				calendarioAlugavel.adicionaElemento("quartoet", q);
				quantet++;
				break;
			}
		}
		spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0),
				new Integer(quantp), new Integer(1)));
		spinner_1.setModel(new SpinnerNumberModel(new Integer(0),
				new Integer(0), new Integer(quantls), new Integer(1)));
		spinner_2.setModel(new SpinnerNumberModel(new Integer(0),
				new Integer(0), new Integer(quantld), new Integer(1)));
		spinner_3.setModel(new SpinnerNumberModel(new Integer(0),
				new Integer(0), new Integer(quantlt), new Integer(1)));
		spinner_4.setModel(new SpinnerNumberModel(new Integer(0),
				new Integer(0), new Integer(quantes), new Integer(1)));
		spinner_5.setModel(new SpinnerNumberModel(new Integer(0),
				new Integer(0), new Integer(quanted), new Integer(1)));
		spinner_6.setModel(new SpinnerNumberModel(new Integer(0),
				new Integer(0), new Integer(quantet), new Integer(1)));
		preencheJList();
	}

	private void preencheJList() {
		List<Hospede> elementos = new LinkedList<>();
		for (Hospede hospede : Sistema.getHotel().getHospedes())
			elementos.add(hospede);

		Filtro.ordenaToString(elementos);
		Filtro.preencheJList(elementos, list_1);
	}

	private void continuar() throws Exception {
		estadia = calendarioAlugavel.getSelecao();
		String cartao = cardField.getText();

		if (list_1.getSelectedIndex() == -1) {
			ErrorLabel.setText("H\u00F3spede ainda n\u00E3o escolhido");
			ErrorLabel.setVisible(true);
			return;
		} else if (!(Hospede.verificaCartao(cartao)) || cartao.equals("")) {
			ErrorLabel.setText("Cart\u00E3o inv\u00E1lido");
			ErrorLabel.setVisible(true);
			return;
		} else if (estadia == null) {
			ErrorLabel.setText("Estadia n\u00E3o foi selecionada");
			ErrorLabel.setVisible(true);
			return;
		}

		int p = (int) spinner.getValue(), ls = (int) spinner_1.getValue(), ld = (int) spinner_2
				.getValue(), lt = (int) spinner_3.getValue(), es = (int) spinner_4
				.getValue(), ed = (int) spinner_5.getValue(), et = (int) spinner_6
				.getValue();

		if (p + ls + ld + lt + es + ed + et == 0) {
			ErrorLabel.setText("Nenhum quarto foi escolhido");
			ErrorLabel.setVisible(true);
			return;
		}

		Estacao estacao = Sistema.getHotel().procuraEstacao(estadia);
		hospede.realizarReserva(cartao, estacao);

		Contrato contrato = null;
		Iterator<Contrato> contratos = hospede.getContratos().iterator();
		while (contratos.hasNext()) contrato = contratos.next();

		ErrorLabel.setVisible(false);

		for (Quarto q : Sistema.getHotel().getQuartosDisponiveis(estadia)) {
			switch (q.getTipo()) {
			case PRESIDENCIAL:
				if (p > 0) {
					q.aluga(estadia);
					contrato.adicionaServico(q);
					q.cancela();
					p--;
				}
				break;
			case LUXO_SIMPLES:
				if (ls > 0) {
					q.aluga(estadia);
					contrato.adicionaServico(q);
					q.cancela();
					ls--;
				}
				break;
			case LUXO_DUPLO:
				if (ld > 0) {
					q.aluga(estadia);
					contrato.adicionaServico(q);
					q.cancela();
					ld--;
				}
				break;
			case LUXO_TRIPLO:
				if (lt > 0) {
					q.aluga(estadia);
					contrato.adicionaServico(q);
					q.cancela();
					lt--;
				}
				break;
			case EXECUTIVO_SIMPLES:
				if (es > 0) {
					q.aluga(estadia);
					contrato.adicionaServico(q);
					q.cancela();
					es--;
				}
				break;
			case EXECUTIVO_DUPLO:
				if (ed > 0) {
					q.aluga(estadia);
					contrato.adicionaServico(q);
					q.cancela();
					ed--;
				}
				break;
			case EXECUTIVO_TRIPLO:
				if (et > 0) {
					q.aluga(estadia);
					contrato.adicionaServico(q);
					q.cancela();
					et--;
				}
				break;
			}
		}

		Sistema.setTela(new Menu(true));
	}

	private void setHospede(Hospede hosp) {
		if (hosp == null)
			throw new IllegalArgumentException();
		hospede = hosp;
	}

}
