package gui.contratos;

import gui.Menu;
import gui.Sistema;
import gui.components.SuperTextField;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import utils.Filtro;
import core.hotel.Contrato;
import core.hotel.EstadoDeContrato;
import core.hotel.Hospede;

public class CheckIn extends JPanel {
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
	public CheckIn() {

		this.setName("Realizar Check in");

		JPanel panel = new JPanel();

		JPanel panel_1 = new JPanel();

		JLabel label = new JLabel("Procurar Hóspede :");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(label);

		NameField = new SuperTextField() {
			private static final long serialVersionUID = 1L;
			@Override
			protected void textChanged(String text) {
				Filtro.exibeFiltrado(text, Sistema.getHotel().getHospedes(), listaHospede);
			}
		};
		NameField.setColumns(37);
		panel_1.add(NameField);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		ErrorLabel = new JLabel("");
		ErrorLabel.setForeground(Color.RED);
		ErrorLabel.setVisible(false);
		ErrorLabel.setIcon(new ImageIcon(CheckIn.class.getResource("/gui/images/error.png")));

		JLabel lblContratos = new JLabel("Contratos:");

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				concluirCheckIn();
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(new Menu(true));
			}
		});

		JPanel panel_2 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
								.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
								.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
								.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
										.addComponent(ErrorLabel, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 197, Short.MAX_VALUE)
										.addComponent(btnCancelar)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(btnConfirmar))
										.addComponent(panel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
										.addComponent(lblContratos))
										.addContainerGap())
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
						.addGap(12)
						.addComponent(lblContratos)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnConfirmar)
										.addComponent(btnCancelar))
										.addComponent(ErrorLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
										.addContainerGap())
				);

		JLabel lblCartoDeCrdito = new JLabel("Cartão de crédito :");
		panel_2.add(lblCartoDeCrdito);

		cartaoField = new JTextField();
		panel_2.add(cartaoField);
		cartaoField.setColumns(20);

		list = new JList<>();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Contrato cont = list.getSelectedValue();
				if (cont != null)
					setContrato(cont);
			}
		});
		scrollPane_1.setViewportView(list);

		listaHospede = new JList<>();
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
		setLayout(groupLayout);
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
			if(c.getEstado().equals(EstadoDeContrato.PENDENTE))
				dml.addElement(c);
		list.setModel(dml);
	}
}