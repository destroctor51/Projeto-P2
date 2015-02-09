package gui.contratos;

import gui.Menu;
import gui.Sistema;
import hotel.Contrato;
import hotel.EstadoDeContrato;
import hotel.Hospede;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
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

public class CheckOut extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static Hospede hospede;
	private static Contrato contrato;
	private JTextField NameField;
	private JTextField cartaoField;
	private JButton SearchButton;
	private JLabel ErrorLabel;
	private DefaultListModel<Object> lista = new DefaultListModel<>();
	private DefaultListModel<Object> lista1 = new DefaultListModel<>();
	private DefaultListModel<Object> lista2 = new DefaultListModel<>();
	private JList<Object> list_2;
	private JList<Object> list_3;
	private DefaultListModel<Object> lista3 = new DefaultListModel<>();
	private JList<Object> list;
	private JList<Object> list_1;

	/**
	 * Create the panel.
	 */
	public CheckOut() {

		JPanel panel = new JPanel();

		JLabel lblRealizaCheckOut = new JLabel("Realiza Check out");
		lblRealizaCheckOut.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(lblRealizaCheckOut);

		JPanel panel_1 = new JPanel();

		JLabel label_1 = new JLabel("Procurar Hóspede :");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(label_1);

		NameField = new JTextField();
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

		list_2 = new JList<Object>();

		list_3 = new JList<Object>();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
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
										.addComponent(list_3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
										.addComponent(list_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
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
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
						.addGap(10)
						.addComponent(list_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(label_2)
						.addGap(12)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
						.addGap(9)
						.addComponent(list_3, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addGap(12)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnConfirmar)
										.addComponent(btnCancelar))
										.addComponent(ErrorLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
										.addContainerGap())
				);

		BufferedImage buttonIcon = null;
		try {
			buttonIcon = ImageIO.read(new File(RealizaReserva.class.getResource("/gui/images/search1.png").toURI()));
		} catch (IOException e) {
		} catch (URISyntaxException e) {
		}
		SearchButton = new JButton(new ImageIcon(buttonIcon));
		SearchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pesquisaHospede();
			}
		});
		SearchButton.setBorder(BorderFactory.createEmptyBorder());
		SearchButton.setContentAreaFilled(false);
		panel_1.add(SearchButton);

		list_1 = new JList<Object>();
		list_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Contrato cont = (Contrato) list_1.getSelectedValue();
				if (cont != null)
					setContrato(cont);
			}
		});
		scrollPane_1.setViewportView(list_1);

		list = new JList<Object>();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Hospede hosp = (Hospede) list.getSelectedValue();
				if (hosp != null) {
					setHospede(hosp);
					preencheContratos(hosp);
				}
			}
		});
		scrollPane.setViewportView(list);
		setLayout(groupLayout);

	}

	private void pesquisaHospede() {
		try {
			lista.clear();
			String nome = NameField.getText();

			if (nome.equals("")) {
				ErrorLabel.setText("Digite um nome.");
				ErrorLabel.setVisible(true);
				return;
			}

			for (Hospede hosp : Sistema.getHotel().getHospedes()) {
				if (checaSemelhanca(nome, hosp.getNome()))
					lista.addElement(hosp);
			}

			list.setModel(lista);
			ErrorLabel.setVisible(false);
		} catch (IllegalArgumentException e) {
			ErrorLabel.setText("Nome inválido.");
			ErrorLabel.setVisible(true);
		}
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

			if (!(contrato.realizarCheckOut(cartao))) {
				ErrorLabel.setText("Pelo menos um serviço alugado ainda não foi devolvido.");
				ErrorLabel.setVisible(true);
				return;
			}

			contrato.realizarCheckOut(cartao);
			ErrorLabel.setVisible(false);
			Sistema.setTela(new Menu());
		} catch (IllegalArgumentException e) {
			ErrorLabel.setText("Cartão inválido.");
			ErrorLabel.setVisible(true);
		}

	}

	private void setHospede(Hospede hosp) {
		lista2.clear();
		lista3.clear();
		contrato = null;

		if (hosp == null)
			throw new IllegalArgumentException();
		hospede = hosp;
		lista2.addElement(hospede);
		list_2.setModel(lista2);
	}

	private void setContrato(Contrato cont) {
		lista3.clear();

		if (cont == null)
			throw new IllegalArgumentException();
		contrato = cont;
		lista3.addElement(contrato);
		list_3.setModel(lista3);
	}

	private void preencheContratos(Hospede hosp) {
		lista1.clear();
		Iterator<Contrato> contratos = hosp.getContratos();

		while (contratos.hasNext()) {
			Contrato contrato = contratos.next();

			if (contrato.getEstado().equals(EstadoDeContrato.ABERTO))
				lista1.addElement(contrato);
		}
		list_1.setModel(lista1);
	}

	private boolean checaSemelhanca(String palavra, String str) {
		for (int i = 0; i <= str.length() - palavra.length(); i++)
			if (str.substring(i, i + palavra.length()).toLowerCase().equals(palavra.toLowerCase()))
				return true;

		return false;
	}
}
