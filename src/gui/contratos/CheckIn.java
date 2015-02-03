package gui.contratos;

import gui.Sistema;
import hotel.Contrato;
import hotel.EstadoDeContrato;
import hotel.Hospede;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JLabel;
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
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JList;

public class CheckIn extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Hospede hospede;
	private Contrato contrato;
	private JTextField NameField;
	private JButton SearchButton;
	private DefaultListModel<Object> lista = new DefaultListModel<>();
	private DefaultListModel<Object> lista1 = new DefaultListModel<>();
	private DefaultListModel<Object> lista2 = new DefaultListModel<>();
	private DefaultListModel<Object> lista3 = new DefaultListModel<>();
	private JList<Object> listaHospede;
	private JList<Object> list;
	private JLabel ErrorLabel;
	private JTextField cartaoField;
	private JList<Object> list_1;
	private JList<Object> list_2;
	
	/**
	 * Create the panel.
	 */
	public CheckIn() {
		
		JPanel panel = new JPanel();
		
		JLabel lblRealizaCheckIn = new JLabel("Realiza Check in");
		lblRealizaCheckIn.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(lblRealizaCheckIn);
		
		JPanel panel_1 = new JPanel();
		
		JLabel label = new JLabel("Procurar Hóspede :");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(label);
		
		NameField = new JTextField();
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
			public void actionPerformed(ActionEvent arg0) {
				concluirCheckIn();
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		
		JPanel panel_2 = new JPanel();
		
		list_1 = new JList<Object>();
		
		list_2 = new JList<Object>();
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
						.addComponent(list_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
						.addComponent(panel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
						.addComponent(list_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
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
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(list_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(lblContratos)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(list_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
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
		
		list = new JList<Object>();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Contrato cont = (Contrato) list.getSelectedValue();
				if (cont != null)
					setContrato(cont);
			}
		});
		scrollPane_1.setViewportView(list);
		
		listaHospede = new JList<Object>();
		listaHospede.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Hospede hosp = (Hospede) listaHospede.getSelectedValue();
				if (hosp != null) {
					setHospede(hosp);
					preencheContratos(hosp);
				}
			}
		});
		scrollPane.setViewportView(listaHospede);
		
		BufferedImage buttonIcon = null;
		try {
			buttonIcon = ImageIO.read(new File(RealizaReserva.class.getResource("/gui/images/search1.png").toURI()));
		} catch (IOException e) {
		} catch (URISyntaxException e) {
		}
		
		SearchButton = new JButton(new ImageIcon(buttonIcon));
		SearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pesquisaHospede();
			}
		});
		SearchButton.setBorder(BorderFactory.createEmptyBorder());
		SearchButton.setContentAreaFilled(false);
		panel_1.add(SearchButton);
		setLayout(groupLayout);
		
		try {
			Sistema.getHotel().adicionaHospede("Arthur bla", "99999999", "01234567890", "email@gmail.com", "Campina Grande", "Rua Qualquer, 10");
			Sistema.getHotel().adicionaHospede("Arthurf", "99999998", "79805274373", "email1@gmail.com", "Campina Grande", "Rua Qualquer, 11");
		} catch (Exception e) {
		}
		for (Hospede hosp : Sistema.getHotel().getHospedes()) {
			if (hosp.getNome().equals("Arthur bla")) {
				try {
					hosp.realizarReserva("376411112222331", 100);
				} catch (Exception e) {
				}
				break;
			}
		}
	
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
			
			listaHospede.setModel(lista);
			ErrorLabel.setVisible(false);
		} catch (IllegalArgumentException e) {
			ErrorLabel.setText("Nome inválido.");
			ErrorLabel.setVisible(true);
		}
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
			JOptionPane.showMessageDialog(null,"Check in realizado com sucesso!");
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
		list_1.setModel(lista2);
	}	
	
	private void setContrato(Contrato cont) {
		lista3.clear();
		
		if (cont == null)
			throw new IllegalArgumentException();
		contrato = cont;
		
		lista3.addElement(contrato);
		list_2.setModel(lista3);
	}
	
	private void preencheContratos(Hospede hosp) {
		lista1.clear();
		Iterator<Contrato> contratos = hosp.getContratos(); 
		
		while (contratos.hasNext()) {
			Contrato contrato = contratos.next();
			
			if (contrato.getEstado().equals(EstadoDeContrato.PENDENTE))
				lista1.addElement(contrato); 
		}
		list.setModel(lista1);
	}
	
	private boolean checaSemelhanca(String palavra, String str) {
		for (int i = 0; i <= str.length() - palavra.length(); i++)
			if (str.substring(i, i + palavra.length()).toLowerCase().equals(palavra.toLowerCase()))
				return true;

		return false;
	}
}