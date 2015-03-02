package gui.contratos;

import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import gui.Menu;
import gui.Sistema;
import gui.components.SuperTextField;
import gui.relatorios.FaturamentoHospede;

import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JList;

import core.hotel.EstadoDeContrato;
import core.hotel.Hospede;
import core.hotel.Contrato;

import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import utils.Filtro;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.awt.Component;

public class CheckInOut extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Hospede hospede;
	private Contrato contrato;
	private SuperTextField NameField;
	private JList<Hospede> listaHospede;
	private JList<Contrato> list;
	private JLabel ErrorLabel;
	private JTextField cartaoField;


	/**
	 * Create the panel.
	 */
	public CheckInOut() {
		this.setName("Realizar Check in / Check Out");
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 39, 135, 34, 142, 0, 30, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.SOUTH;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		
		JLabel label = new JLabel("Procurar Hóspede :");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(label);
		
		NameField = new SuperTextField() {
			private static final long serialVersionUID = 1L;
			@Override
			protected void textChanged(String text) {
				Filtro.exibeFiltrado(text, Sistema.getHotel().getHospedes(), listaHospede);
			}
		};
		NameField.setColumns(37);
		panel.add(NameField);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);
		
		listaHospede = new JList<Hospede>();
		listaHospede.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Hospede hosp = listaHospede.getSelectedValue();
				if (hosp != null) {
					setHospede(hosp);
					preencheContratos(hosp);
				}
			}
		});
		scrollPane.setViewportView(listaHospede);
		
		JLabel label_1 = new JLabel("Contratos:");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.SOUTHWEST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 3;
		add(label_1, gbc_label_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 4;
		add(scrollPane_1, gbc_scrollPane_1);
		
		list = new JList<Contrato>();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Contrato cont = list.getSelectedValue();
				if (cont != null)
					setContrato(cont);
			}
		});
		scrollPane_1.setViewportView(list);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.SOUTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 5;
		add(panel_1, gbc_panel_1);
		
		JLabel label_2 = new JLabel("Cartão de crédito :");
		panel_1.add(label_2);
		
		cartaoField = new JTextField();
		cartaoField.setColumns(20);
		panel_1.add(cartaoField);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 6;
		add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] {0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[] {0};
		gbl_panel_2.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0};
		gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		ErrorLabel = new JLabel("Observacoes");
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
		button_2.setAlignmentX(Component.RIGHT_ALIGNMENT);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(new Menu(true));
			}
		});
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.anchor = GridBagConstraints.SOUTHEAST;
		gbc_button_2.insets = new Insets(0, 0, 0, 5);
		gbc_button_2.gridx = 1;
		gbc_button_2.gridy = 0;
		panel_2.add(button_2, gbc_button_2);
		
		JButton button_1 = new JButton("Check In");
		button_1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				concluirCheckIn();
			}
		});
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.anchor = GridBagConstraints.SOUTHEAST;
		gbc_button_1.insets = new Insets(0, 0, 0, 5);
		gbc_button_1.gridx = 2;
		gbc_button_1.gridy = 0;
		panel_2.add(button_1, gbc_button_1);
		
		JButton button = new JButton("Check Out");
		button.setAlignmentX(Component.RIGHT_ALIGNMENT);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkOut();
			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.SOUTHEAST;
		gbc_button.gridx = 3;
		gbc_button.gridy = 0;
		panel_2.add(button, gbc_button);
		
		preencheJList();
	}
	
	private void concluirCheckIn() {
		try {
			String cartao = cartaoField.getText();

			if (hospede == null) {
				ErrorLabel.setText("Hóspede ainda não escolhido.");
				ErrorLabel.setVisible(true);
				return;
			}

			else if (contrato == null) {
				ErrorLabel.setText("Contrato ainda não escolhido.");
				ErrorLabel.setVisible(true);
				return;
			}
			
			else if (!(contrato.getEstado().equals(EstadoDeContrato.PENDENTE))) {
				ErrorLabel.setText("Selecione um contrato pendente.");
				ErrorLabel.setVisible(true);
				return;
			}

			else if (!(Hospede.verificaCartao(cartao)) || cartao.equals("") || !(contrato.getCartao().equals(cartao))) {
				ErrorLabel.setText("Cartão invalido.");
				ErrorLabel.setVisible(true);
				return;
			}

			contrato.realizarCheckIn(cartao);
			ErrorLabel.setVisible(false);
			Sistema.setTela(new Menu(true));
		} catch (IllegalArgumentException e) {
			ErrorLabel.setText("Cartão inválido.");
			ErrorLabel.setVisible(true);
		}

	}
	
	private void checkOut() {
		try {
			String cartao = cartaoField.getText();

			if (hospede == null) {
				ErrorLabel.setText("Hóspede ainda não escolhido.");
				ErrorLabel.setVisible(true);
				return;
			}

			else if (contrato == null) {
				ErrorLabel.setText("Contrato ainda não escolhido.");
				ErrorLabel.setVisible(true);
				return;
			}
			
			else if (!(contrato.getEstado().equals(EstadoDeContrato.ABERTO))) {
				ErrorLabel.setText("Selecione um contrato aberto.");
				ErrorLabel.setVisible(true);
				return;
			}

			else if (!(Hospede.verificaCartao(cartao)) || cartao.equals("") || !(contrato.getCartao().equals(cartao))) {
				ErrorLabel.setText("Cartão invalido.");
				ErrorLabel.setVisible(true);
				return;
			}

			if (!(contrato.realizarCheckOut(cartao, new GregorianCalendar()))) {
				ErrorLabel.setText("Pelo menos um serviço alugado ainda não foi devolvido.");
				ErrorLabel.setVisible(true);
				return;
			}

			contrato.realizarCheckOut(cartao, new GregorianCalendar());
			ErrorLabel.setVisible(false);
			Sistema.setTela(new FaturamentoHospede(this,contrato,hospede));
		} catch (IllegalArgumentException e) {
			ErrorLabel.setText("Cartão inválido.");
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

	private void setHospede(Hospede hosp) {
		contrato = null;

		if (hosp == null)
			throw new IllegalArgumentException();
		hospede = hosp;
	}

	private void setContrato(Contrato cont) {
		if (cont == null)
			throw new IllegalArgumentException();
		contrato = cont;
	}

	private void preencheContratos(Hospede hosp) {
		DefaultListModel<Contrato> dml = new DefaultListModel<>();

		for(Contrato c : hosp.getContratos())
			if(!(c.getEstado().equals(EstadoDeContrato.FECHADO)))
				dml.addElement(c);
		list.setModel(dml);
	}

}
