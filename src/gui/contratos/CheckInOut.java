package gui.contratos;

import gui.Menu;
import gui.Sistema;
import gui.components.SuperTextField;
import gui.relatorios.FaturamentoHospede;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

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
import javax.swing.SwingConstants;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import utils.Filtro;
import core.hotel.Contrato;
import core.hotel.EstadoDeContrato;
import core.hotel.Hospede;

public class CheckInOut extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private SuperTextField NameField;
	private JList<Hospede> listaHospede;
	private JList<Contrato> list;
	private JLabel ErrorLabel;
	private JTextField cartaoField;


	/**
	 * Create the panel.
	 */
	public CheckInOut() {
		this.setName("Check In / Check Out");

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 400, 0};
		gridBagLayout.rowHeights = new int[] {0, 120, 0, 120, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.25, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 0.25, 0.25, 0.25, 1.0};
		setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.SOUTH;
		gbc_panel.insets = new Insets(0, 0, 10, 5);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0, 0};
		gbl_panel.rowHeights = new int[] {0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0};
		gbl_panel.rowWeights = new double[]{0.0};
		panel.setLayout(gbl_panel);

		JLabel label = new JLabel("Procurar H\u00F3spede :");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 0, 10);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel.add(label, gbc_label);

		NameField = new SuperTextField() {
			private static final long serialVersionUID = 1L;
			@Override
			protected void textChanged(String text) {
				Filtro.exibeFiltrado(text, Sistema.getHotel().getHospedes(), listaHospede);
			}
		};
		NameField.setColumns(37);
		GridBagConstraints gbc_NameField = new GridBagConstraints();
		gbc_NameField.fill = GridBagConstraints.BOTH;
		gbc_NameField.gridx = 1;
		gbc_NameField.gridy = 0;
		panel.add(NameField, gbc_NameField);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);

		listaHospede = new JList<Hospede>();
		listaHospede.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaHospede.setVisibleRowCount(-1);
		listaHospede.setFixedCellWidth(100);
		listaHospede.setFocusable(false);
		listaHospede.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Hospede hosp = listaHospede.getSelectedValue();
				if(hosp != null) preencheContratos(hosp);
			}
		});
		scrollPane.setViewportView(listaHospede);

		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.anchor = GridBagConstraints.SOUTH;
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 2;
		add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] {0, 0, 0};
		gbl_panel_3.rowHeights = new int[] {0};
		gbl_panel_3.columnWeights = new double[]{0.0, 1.0, 0.0};
		gbl_panel_3.rowWeights = new double[]{0.0};
		panel_3.setLayout(gbl_panel_3);

		JLabel label_1 = new JLabel("Contratos:");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 0, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 0;
		panel_3.add(label_1, gbc_label_1);

		JLabel label_2 = new JLabel("Cart\u00E3o de cr\u00E9dito :");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.EAST;
		gbc_label_2.insets = new Insets(0, 0, 0, 10);
		gbc_label_2.gridx = 1;
		gbc_label_2.gridy = 0;
		panel_3.add(label_2, gbc_label_2);

		cartaoField = new JTextField();
		GridBagConstraints gbc_cartaoField = new GridBagConstraints();
		gbc_cartaoField.gridx = 2;
		gbc_cartaoField.gridy = 0;
		panel_3.add(cartaoField, gbc_cartaoField);
		cartaoField.setColumns(20);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 10, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 3;
		add(scrollPane_1, gbc_scrollPane_1);

		list = new JList<Contrato>();
		list.setVisibleRowCount(-1);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFixedCellWidth(100);
		list.setFocusable(false);
		scrollPane_1.setViewportView(list);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.anchor = GridBagConstraints.NORTH;
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 4;
		add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] {200, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[] {0};
		gbl_panel_2.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0};
		gbl_panel_2.rowWeights = new double[]{0.0};
		panel_2.setLayout(gbl_panel_2);

		ErrorLabel = new JLabel("<erro>");
		ErrorLabel.setVisible(false);
		ErrorLabel.setForeground(Color.RED);
		ErrorLabel.setIcon(new ImageIcon(CheckInOut.class.getResource("/gui/images/error.png")));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_2.add(ErrorLabel, gbc_lblNewLabel);

		JButton button_2 = new JButton("Voltar");
		button_2.setFocusable(false);
		button_2.setAlignmentX(Component.RIGHT_ALIGNMENT);
		button_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(new Menu(true));
			}
		});
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.anchor = GridBagConstraints.EAST;
		gbc_button_2.insets = new Insets(0, 0, 0, 10);
		gbc_button_2.gridx = 1;
		gbc_button_2.gridy = 0;
		panel_2.add(button_2, gbc_button_2);

		JButton button_1 = new JButton("Check In");
		button_1.setFocusable(false);
		button_1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				concluirCheckIn();
			}
		});
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.insets = new Insets(0, 0, 0, 10);
		gbc_button_1.gridx = 2;
		gbc_button_1.gridy = 0;
		panel_2.add(button_1, gbc_button_1);

		JButton button = new JButton("Check Out");
		button.setFocusable(false);
		button.setAlignmentX(Component.RIGHT_ALIGNMENT);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				checkOut();
			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.gridx = 3;
		gbc_button.gridy = 0;
		panel_2.add(button, gbc_button);

		addAncestorListener(new AncestorListener() {
			@Override
			public void ancestorAdded(AncestorEvent arg0) {
				NameField.setText("");
				cartaoField.setText("");
				preencheJList();
				preencheContratos(null);
			}
			@Override
			public void ancestorMoved(AncestorEvent arg0) {}
			@Override
			public void ancestorRemoved(AncestorEvent arg0) {}
		});
	}

	private void concluirCheckIn() {
		try {
			String cartao = cartaoField.getText();
			Hospede hospede = listaHospede.getSelectedValue();
			Contrato contrato = list.getSelectedValue();

			if (hospede == null) {
				ErrorLabel.setText("H\u00F3spede ainda n\u00E3o escolhido");
				ErrorLabel.setVisible(true);
				return;
			}

			else if (contrato == null) {
				ErrorLabel.setText("Contrato ainda n\u00E3o escolhido");
				ErrorLabel.setVisible(true);
				return;
			}

			else if (!(contrato.getEstado().equals(EstadoDeContrato.PENDENTE))) {
				ErrorLabel.setText("Selecione um contrato pendente");
				ErrorLabel.setVisible(true);
				return;
			}

			else if (!(Hospede.verificaCartao(cartao)) || cartao.equals("") || !(contrato.getCartao().equals(cartao))) {
				ErrorLabel.setText("Cart\u00E3o inv\u00E1lido");
				ErrorLabel.setVisible(true);
				return;
			}

			contrato.realizarCheckIn(cartao);
			ErrorLabel.setVisible(false);
			Sistema.setTela(this);
		} catch (IllegalArgumentException e) {
			ErrorLabel.setText("Cart\u00E3o inv\u00E1lido");
			ErrorLabel.setVisible(true);
		}

	}

	private void checkOut() {
		try {
			String cartao = cartaoField.getText();
			Hospede hospede = listaHospede.getSelectedValue();
			Contrato contrato = list.getSelectedValue();

			if (hospede == null) {
				ErrorLabel.setText("H\u00F3spede ainda n\u00E3o escolhido");
				ErrorLabel.setVisible(true);
				return;
			}

			else if (contrato == null) {
				ErrorLabel.setText("Contrato ainda n\u00E3o escolhido");
				ErrorLabel.setVisible(true);
				return;
			}

			else if (!(contrato.getEstado().equals(EstadoDeContrato.ABERTO))) {
				ErrorLabel.setText("Selecione um contrato aberto");
				ErrorLabel.setVisible(true);
				return;
			}

			else if (!(Hospede.verificaCartao(cartao)) || cartao.equals("") || !(contrato.getCartao().equals(cartao))) {
				ErrorLabel.setText("Cart\u00E3o inv\u00E1lido");
				ErrorLabel.setVisible(true);
				return;
			}

			if (!(contrato.realizarCheckOut(cartao, new GregorianCalendar()))) {
				ErrorLabel.setText("Algum servi\u00E7o ainda n\u00E3o foi devolvido");
				ErrorLabel.setVisible(true);
				return;
			}

			contrato.realizarCheckOut(cartao, new GregorianCalendar());
			ErrorLabel.setVisible(false);
			Sistema.setTela(new FaturamentoHospede(this, contrato, hospede));
		} catch (IllegalArgumentException e) {
			ErrorLabel.setText("Cart\u00E3o inv\u00E1lido");
			ErrorLabel.setVisible(true);
		}

	}

	private void preencheJList(){
		List<Hospede> elementos = new LinkedList<>();
		for(Hospede hospede: Sistema.getHotel().getHospedes())
			elementos.add(hospede);

		Filtro.ordenaToString(elementos);
		Filtro.preencheJList(elementos, listaHospede);
	}

	private void preencheContratos(Hospede hosp) {
		DefaultListModel<Contrato> dml = new DefaultListModel<>();
		if(hosp != null) for(Contrato c : hosp.getContratos())
			if(!(c.getEstado().equals(EstadoDeContrato.FECHADO)))
				dml.addElement(c);
		list.setModel(dml);
	}

}
