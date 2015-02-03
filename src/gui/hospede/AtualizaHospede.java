package gui.hospede;

import gui.Sistema;
import hotel.Hospede;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

public class AtualizaHospede extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private Hospede hospede;
	private JTextField textField_4;
	private JTextField textField_5;
	private JLabel ErrorLabel;

	/**
	 * Create the panel.
	 */
	public AtualizaHospede(Hospede hospede) {
		this.hospede = hospede;

		JPanel panel = new JPanel();

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					atualizarHospede();
				} catch (Exception e1) {
				}
				PesquisaHospede.getDlm1().clear();
				Sistema.setTela(new PesquisaHospede());
			}
		});

		ErrorLabel = new JLabel("");
		ErrorLabel.setVisible(false);
		ErrorLabel.setIcon(new ImageIcon(AtualizaHospede.class
				.getResource("/gui/images/error.png")));

		JPanel panel_1 = new JPanel();

		JPanel panel_2 = new JPanel();

		JPanel panel_3 = new JPanel();

		JPanel panel_4 = new JPanel();

		JPanel panel_5 = new JPanel();

		JPanel panel_6 = new JPanel();

		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PesquisaHospede.getDlm1().clear();
				Sistema.setTela(new PesquisaHospede());
			}
		});

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(panel, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addComponent(ErrorLabel, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE))
							.addGap(6)
							.addComponent(btnAtualizar))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(30)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_5, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
								.addComponent(panel_4, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
								.addComponent(panel_1, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
								.addComponent(panel_2, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
								.addComponent(panel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
								.addComponent(panel_6, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addGap(29)
					.addComponent(btnNewButton)
					.addGap(30))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(24)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(ErrorLabel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
							.addGap(32))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAtualizar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton))
							.addGap(16)))
					.addGap(8))
		);

		JLabel lblEndereo = new JLabel("Endere\u00E7o :");
		panel_6.add(lblEndereo);

		textField_1 = new JTextField();
		panel_6.add(textField_1);
		textField_1.setColumns(40);
		textField_1.setText(hospede.getEndereco());

		JLabel lblCidade = new JLabel("Cidade :     ");
		panel_5.add(lblCidade);

		textField = new JTextField();
		panel_5.add(textField);
		textField.setColumns(40);
		textField.setText(hospede.getCidade());

		JLabel lblEmail = new JLabel("E-mail :      ");
		panel_4.add(lblEmail);

		textField_3 = new JTextField();
		panel_4.add(textField_3);
		textField_3.setColumns(40);
		textField_3.setText(hospede.getEmail());

		JLabel lblNewLabel = new JLabel("Telefone :");
		panel_3.add(lblNewLabel);

		textField_2 = new JTextField();
		panel_3.add(textField_2);
		textField_2.setColumns(40);
		textField_2.setText(hospede.getTelefone());

		JLabel lblCpf = new JLabel("Cpf :          ");
		panel_2.add(lblCpf);

		textField_4 = new JTextField();
		panel_2.add(textField_4);
		textField_4.setColumns(40);
		textField_4.setText(hospede.getCpf());
		textField_4.setEditable(false);

		JLabel lblNome = new JLabel("Nome :      ");
		panel_1.add(lblNome);

		textField_5 = new JTextField();
		panel_1.add(textField_5);
		textField_5.setColumns(40);
		textField_5.setText(hospede.getNome());
		textField_5.setEditable(false);

		JLabel lblNewLabel_1 = new JLabel("Atualiza H\u00F3spede");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		panel.add(lblNewLabel_1);
		setLayout(groupLayout);

	}

	private void atualizarHospede() throws Exception {
		String email = textField_3.getText();

		if (email.equals("")) {
			ErrorLabel.setText("Email inv�lido.");
			ErrorLabel.setVisible(true);
			return;
		}

		hospede.setEmail(email);
		ErrorLabel.setVisible(false);

		String endereco = textField_1.getText();

		if (endereco.equals("")) {
			ErrorLabel.setText("Endere�o inv�lido.");
			ErrorLabel.setVisible(true);
			return;
		}

		hospede.setEndereco(endereco);
		ErrorLabel.setVisible(false);

		String cidade = textField.getText();

		if (cidade.equals("")) {
			ErrorLabel.setText("Cidade inv�lida.");
			ErrorLabel.setVisible(true);
			return;
		}

		hospede.setCidade(cidade);
		ErrorLabel.setVisible(false);

		String telefone = textField_2.getText();

		if (telefone.equals("")) {
			ErrorLabel.setText("Telefone inv�lido.");
			ErrorLabel.setVisible(true);
			return;
		}

		hospede.setTelefone(telefone);
		ErrorLabel.setVisible(false);
	}

	
}
