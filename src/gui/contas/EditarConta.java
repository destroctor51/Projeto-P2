package gui.contas;

import gui.Sistema;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import utils.Internet;
import core.login.Conta;
import core.login.Permissao;

/**
 * Tela que permite a edicao de contas de usuario no sistema.
 *
 * @author Victor Andrade de Almeida
 */
public class EditarConta extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final String HIDDEN = String.format("%010d", 0).replace("0", "\u0007");

	private JTextField textField;
	private JComboBox<Permissao> comboBox;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JTextField textField_1;
	private JTextField textField_2;

	public EditarConta(final Conta conta, final JPanel telaAnterior) {
		setName("Editar Conta");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.25, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 0.25, 1.0};
		setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		panel.setBorder(new CompoundBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)), new EmptyBorder(0, 10, 0, 10)));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 10, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWidths = new int[] {0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0};
		gbl_panel.rowWeights = new double[]{1.0, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 1.0};
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel_1 = new JLabel("Nome de usu\u00ED¡rio:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 10, 10);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		textField = new JTextField(conta.getID());
		textField.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 10, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Permiss\u00E3o:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 10, 10);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		comboBox = new JComboBox<>();
		comboBox.setFocusable(false);
		comboBox.setModel(new DefaultComboBoxModel<>(Permissao.values()));
		comboBox.setSelectedItem(conta.getPermissao());
		comboBox.setEnabled(false);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 10, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 2;
		panel.add(comboBox, gbc_comboBox);

		JLabel lblNewLabel_3 = new JLabel("Nome real:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 10, 10);
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 3;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);

		textField_1 = new JTextField(conta.getNome());
		textField_1.setEditable(false);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 10, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 3;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("E-mail:");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 10, 10);
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 4;
		panel.add(lblNewLabel_4, gbc_lblNewLabel_4);

		textField_2 = new JTextField(conta.getEmail());
		textField_2.setEditable(false);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 10, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 4;
		panel.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Senha antiga:");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 10, 10);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 5;
		panel.add(lblNewLabel_5, gbc_lblNewLabel_5);

		passwordField = new JPasswordField(HIDDEN);
		passwordField.setEditable(false);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 10, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 5;
		panel.add(passwordField, gbc_passwordField);

		JLabel lblNewLabel_6 = new JLabel("Nova senha:");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 10);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 6;
		panel.add(lblNewLabel_6, gbc_lblNewLabel_6);

		passwordField_1 = new JPasswordField(HIDDEN);
		passwordField_1.setEditable(false);
		GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
		gbc_passwordField_1.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_1.gridx = 1;
		gbc_passwordField_1.gridy = 6;
		panel.add(passwordField_1, gbc_passwordField_1);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.NORTH;
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 2;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {200, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0.0};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);

		final JLabel lblNewLabel = new JLabel("<erro>");
		lblNewLabel.setVisible(false);
		lblNewLabel.setIcon(new ImageIcon(EditarConta.class.getResource("/gui/images/error.png")));
		lblNewLabel.setForeground(Color.RED);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);

		btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setMaximumSize(new Dimension(75, 23));
		btnNewButton_1.setMinimumSize(new Dimension(75, 23));
		btnNewButton_1.setPreferredSize(new Dimension(75, 23));
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(btnNewButton_1.getText().equals("Voltar"))
					Sistema.setTela(telaAnterior);

				else if(btnNewButton_1.getText().equals("Cancelar")) {
					comboBox.setSelectedItem(conta.getPermissao());
					textField_1.setText(conta.getNome());
					textField_2.setText(conta.getEmail());

					comboBox.setEnabled(false);
					textField_1.setEditable(false);
					textField_2.setEditable(false);
					passwordField.setEditable(false);
					passwordField_1.setEditable(false);
					passwordField.setText(HIDDEN);
					passwordField_1.setText(HIDDEN);
					btnNewButton.setText("Editar");
					btnNewButton_1.setText("Voltar");
					lblNewLabel.setVisible(false);
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 10);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 0;
		panel_1.add(btnNewButton_1, gbc_btnNewButton_1);

		btnNewButton = new JButton("Editar");
		btnNewButton.setFocusable(false);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(btnNewButton.getText().equals("Editar")) {
					comboBox.setEnabled(true);
					textField_1.setEditable(true);
					textField_2.setEditable(true);
					passwordField.setEditable(true);
					passwordField_1.setEditable(true);
					btnNewButton.setText("Confirmar");
					btnNewButton_1.setText("Cancelar");
					passwordField.setText("");
					passwordField_1.setText("");
				}

				else if(btnNewButton.getText().equals("Confirmar")) {
					boolean success = true;
					char[] lastPassword = passwordField.getPassword();
					char[] newPassword = passwordField_1.getPassword();

					if(textField_1.getText().length() < 3) {
						lblNewLabel.setText("Nome real menor que 3 caract\u00E9res");
						lblNewLabel.setVisible(true);
						success = false;
					}

					else if(!Internet.isEmailValido(textField_2.getText())) {
						lblNewLabel.setText("Email inv\u00E1lido");
						lblNewLabel.setVisible(true);
						success = false;
					}

					else if(lastPassword.length > 0 || newPassword.length > 0) {
						if(newPassword.length < 5) {
							lblNewLabel.setText("Nova senha menor que 5 caract\u00E9res");
							lblNewLabel.setVisible(true);
							success = false;
						}

						else if(!conta.setSenha(new String(lastPassword), new String(newPassword))) {
							lblNewLabel.setText("Senha errada");
							lblNewLabel.setVisible(true);
							success = false;
						}
					}

					if(success) {
						conta.setPermissao((Permissao) comboBox.getSelectedItem());
						conta.setNome(textField_1.getText());
						conta.setEmail(textField_2.getText());

						if(Sistema.getUsuario().equals(conta))
							Sistema.fazLogin(conta);

						comboBox.setEnabled(false);
						textField_1.setEditable(false);
						textField_2.setEditable(false);
						passwordField.setEditable(false);
						passwordField_1.setEditable(false);
						passwordField.setText(HIDDEN);
						passwordField_1.setText(HIDDEN);
						btnNewButton.setText("Editar");
						btnNewButton_1.setText("Voltar");
						lblNewLabel.setVisible(false);
					}

					else {
						passwordField.setText("");
						passwordField_1.setText("");
					}

					for(int i=0; i<lastPassword.length; i++)
						lastPassword[i] = 0;
					for(int i=0; i<newPassword.length; i++)
						newPassword[i] = 0;
				}
			}
		});
		btnNewButton.setMaximumSize(new Dimension(79, 23));
		btnNewButton.setMinimumSize(new Dimension(79, 23));
		btnNewButton.setPreferredSize(new Dimension(79, 23));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 0;
		panel_1.add(btnNewButton, gbc_btnNewButton);
	}
}