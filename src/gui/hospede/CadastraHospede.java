package gui.hospede;

import gui.Sistema;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import core.hotel.Hospede;

public class CadastraHospede extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfNome;
	private JTextField tfTelefone;
	private JTextField tfCpf;
	private JTextField tfEmail;
	private JTextField tfCidade;
	private JTextField tfEndereco;
	private JLabel errorLabel;

	/**
	 * Create the panel.
	 */
	public CadastraHospede(final JPanel tela) {
		setName("Cadastra Hóspede\n");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[] { 1.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 0.0 };
		setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(20, 0, 10, 0));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0 };
		gbl_panel.columnWeights = new double[] { 0.0 };
		gbl_panel.rowWeights = new double[] {};
		panel.setLayout(gbl_panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(10, 20, 10, 20));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.VERTICAL;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 500, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 50, 50, 50, 50, 50, 50, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel label_1 = new JLabel("Nome :");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 0;
		panel_1.add(label_1, gbc_label_1);

		tfNome = new JTextField();
		tfNome.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		panel_1.add(tfNome, gbc_textField);

		JLabel label_2 = new JLabel("Telefone :");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.EAST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 1;
		panel_1.add(label_2, gbc_label_2);

		tfTelefone = new JTextField();
		tfTelefone.setColumns(10);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		panel_1.add(tfTelefone, gbc_textField_1);

		JLabel label_3 = new JLabel("CPF :");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.anchor = GridBagConstraints.EAST;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 2;
		panel_1.add(label_3, gbc_label_3);

		tfCpf = new JTextField();
		tfCpf.setColumns(10);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 2;
		panel_1.add(tfCpf, gbc_textField_2);

		JLabel label_4 = new JLabel("Email :");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.anchor = GridBagConstraints.EAST;
		gbc_label_4.insets = new Insets(0, 0, 5, 5);
		gbc_label_4.gridx = 0;
		gbc_label_4.gridy = 3;
		panel_1.add(label_4, gbc_label_4);

		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 3;
		panel_1.add(tfEmail, gbc_textField_3);

		JLabel label_5 = new JLabel("Cidade :");
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.anchor = GridBagConstraints.EAST;
		gbc_label_5.insets = new Insets(0, 0, 5, 5);
		gbc_label_5.gridx = 0;
		gbc_label_5.gridy = 4;
		panel_1.add(label_5, gbc_label_5);

		tfCidade = new JTextField();
		tfCidade.setColumns(10);
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 4;
		panel_1.add(tfCidade, gbc_textField_4);

		JLabel label_6 = new JLabel("Endere\u00E7o :");
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.anchor = GridBagConstraints.EAST;
		gbc_label_6.insets = new Insets(0, 0, 5, 5);
		gbc_label_6.gridx = 0;
		gbc_label_6.gridy = 5;
		panel_1.add(label_6, gbc_label_6);

		tfEndereco = new JTextField();
		tfEndereco.setColumns(10);
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 1;
		gbc_textField_5.gridy = 5;
		panel_1.add(tfEndereco, gbc_textField_5);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(20, 100, 20, 30));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.anchor = GridBagConstraints.SOUTH;
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 2;
		add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 1.0, 0.0, 0.0 };
		gbl_panel_2.rowWeights = new double[] { 0.0 };
		panel_2.setLayout(gbl_panel_2);

		errorLabel = new JLabel("");
		errorLabel.setVisible(false);
		errorLabel.setForeground(Color.RED);
		errorLabel.setIcon(new ImageIcon(CadastraHospede.class
				.getResource("/gui/images/error.png")));
		GridBagConstraints gbc_lblError = new GridBagConstraints();
		gbc_lblError.anchor = GridBagConstraints.WEST;
		gbc_lblError.insets = new Insets(0, 0, 0, 5);
		gbc_lblError.gridx = 0;
		gbc_lblError.gridy = 0;
		panel_2.add(errorLabel, gbc_lblError);

		JButton btnCancela = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancela = new GridBagConstraints();
		gbc_btnCancela.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancela.gridx = 1;
		gbc_btnCancela.gridy = 0;
		panel_2.add(btnCancela, gbc_btnCancela);
		btnCancela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(tela);
			}
		});

		JButton btnConfirma = new JButton("Confirmar");
		GridBagConstraints gbc_btnConfirma = new GridBagConstraints();
		gbc_btnConfirma.gridx = 2;
		gbc_btnConfirma.gridy = 0;
		panel_2.add(btnConfirma, gbc_btnConfirma);
		btnConfirma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if(cadastra())
						Sistema.setTela(tela);
				} catch (Exception e1) {
					errorLabel.setText(e1.getMessage());
					errorLabel.setVisible(true);
				}

			}
		});

	}

	private boolean cadastra() throws Exception {

		String nome = tfNome.getText();
		if (nome.equals("") || nome.length() < 3) {
			errorLabel.setText("Nome invalido.");
			errorLabel.setVisible(true);
			return false;
		}
		
		String telefone = tfTelefone.getText();

		if (telefone.equals("") || !checaValorString(telefone)) {
			errorLabel.setText("Telefone invalido.");
			errorLabel.setVisible(true);
			return false;
		}
		
		String cpf = tfCpf.getText();

		if (cpf.equals("") || !checaValorString(cpf)|| !Hospede.verificaCpf(cpf)) {
			errorLabel.setText("Cpf invalido.");
			errorLabel.setVisible(true);
			return false;
		}

		String email = tfEmail.getText();

		if (email.equals("") || email.length() < 3) {
			errorLabel.setText("Email invalido.");
			errorLabel.setVisible(true);
			return false;
		}

		String cidade = tfCidade.getText();

		if (cidade.equals("") || cidade.length() < 3) {
			errorLabel.setText("Cidade invalida.");
			errorLabel.setVisible(true);
			return false;
		}

		String endereco = tfEndereco.getText();

		if (endereco.equals("") || endereco.length() < 3) {
			errorLabel.setText("Endereco invalido.");
			errorLabel.setVisible(true);
			return false;
		}
		
		Sistema.getHotel().adicionaHospede(nome, telefone, cpf, email, cidade,
				endereco);
		errorLabel.setVisible(false);
		return true;
	}

	private boolean checaValorString(String str) {
		for (int i = 0; i < str.length(); i++)
			if ("0123456789".lastIndexOf(str.charAt(i)) == -1)
				return false;
		return true;
	}
}