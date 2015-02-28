package gui.contratos;

import gui.Menu;
import gui.Sistema;
import gui.components.SuperTextField;
import gui.relatorios.FaturamentoHospede;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.GregorianCalendar;
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

public class CheckOut extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static Hospede hospede;
	private static Contrato contrato;
	private SuperTextField NameField;
	private JTextField cartaoField;
	private JLabel ErrorLabel;
	private JList<Hospede> list;
	private JList<Contrato> list_1;

	/**
	 * Create the panel.
	 */
	public CheckOut() {

		this.setName("Realizar Check out");

		JPanel panel = new JPanel();

		JPanel panel_1 = new JPanel();

		JLabel label_1 = new JLabel("Procurar Hóspede :");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(label_1);

		NameField = new SuperTextField() {
			private static final long serialVersionUID = 1L;
			@Override
			protected void textChanged(String text) {
				Filtro.exibeFiltrado(text, Sistema.getHotel().getHospedes(), list);
			}
		};
		NameField.setColumns(37);
		panel_1.add(NameField);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		JLabel label_2 = new JLabel("Contratos:");

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		JPanel panel_2 = new JPanel();

		JLabel label_3 = new JLabel("Cartão de crédito :");
		panel_2.add(label_3);

		cartaoField = new JTextField();
		cartaoField.setColumns(20);
		panel_2.add(cartaoField);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				concluirCheckOut();
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(new Menu(true));
			}
		});

		ErrorLabel = new JLabel("");
		ErrorLabel.setForeground(Color.RED);
		ErrorLabel.setVisible(false);
		ErrorLabel.setIcon(new ImageIcon(CheckOut.class.getResource("/gui/images/error.png")));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
								.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
								.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
								.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
										.addComponent(ErrorLabel, GroupLayout.PREFERRED_SIZE, 315, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
										.addComponent(btnCancelar)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(btnConfirmar))
										.addComponent(panel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
										.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
										.addContainerGap())
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
						.addGap(12)
						.addComponent(label_2)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnConfirmar)
										.addComponent(btnCancelar))
										.addComponent(ErrorLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
										.addContainerGap())
				);

		list_1 = new JList<>();
		list_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Contrato cont = list_1.getSelectedValue();
				if (cont != null)
					setContrato(cont);
			}
		});
		scrollPane_1.setViewportView(list_1);

		list = new JList<>();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Hospede hosp = list.getSelectedValue();
				if (hosp != null) {
					setHospede(hosp);
					preencheContratos(hosp);
				}
			}
		});
		scrollPane.setViewportView(list);
		setLayout(groupLayout);
		preencheJList();
	}

	private void concluirCheckOut() {
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

	private void preencheJList(){
		List<Hospede> elementos = new LinkedList<>();
		for(Hospede hospede: Sistema.getHotel().getHospedes())
			elementos.add(hospede);

		Filtro.ordenaToString(elementos);
		Filtro.preencheJList(elementos, list);
	}

	private void preencheContratos(Hospede hosp) {
		DefaultListModel<Contrato> dml = new DefaultListModel<>();

		for(Contrato c : hosp.getContratos())
			if(c.getEstado().equals(EstadoDeContrato.ABERTO))
				dml.addElement(c);
		list_1.setModel(dml);
	}
}
