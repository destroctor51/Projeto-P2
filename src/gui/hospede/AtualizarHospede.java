package gui.hospede;

import gui.Sistema;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import utils.Internet;
import core.hotel.Hospede;

public class AtualizarHospede extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField tfCidade;
	private JTextField tfEndereco;
	private JFormattedTextField tfTelefone;
	private MaskFormatter ftmTelefone;
	private JFormattedTextField tfCpf;
	private MaskFormatter ftmCpf;
	private JTextField tfEmail;
	private Hospede hospede;
	private JTextField tfNome;
	private JLabel errorLabel;

	private boolean editavel;
	JButton btnAtualizar = new JButton("Editar");
	JButton btnVoltar = new JButton("Voltar");

	public AtualizarHospede(Hospede hospede, final JPanel telaAnterior) {

		setName("Atualiza H\u00F3spede");

		this.hospede = hospede;
		this.editavel = false;

		try {
			ftmTelefone = new MaskFormatter("(##) ####-####");
		} catch (ParseException e2) {
			errorLabel.setText("Telefone inv\u00E1lido");
			errorLabel.setVisible(true);
		}

		ftmTelefone.setValidCharacters("0123456789");

		try {
			ftmCpf = new MaskFormatter("###.###.###-##");
		} catch (ParseException e2) {
			errorLabel.setText("CPF inv\u00E1lido");
			errorLabel.setVisible(true);
		}

		ftmCpf.setValidCharacters("0123456789");

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.25, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 0.25, 1.0};
		setLayout(gridBagLayout);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new CompoundBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(10, 20, 10, 20)));
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {0};
		gbl_panel_1.rowHeights = new int[] {0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0};
		gbl_panel_1.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		panel_1.setLayout(gbl_panel_1);

		JLabel lblNome = new JLabel("Nome:");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 10);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		panel_1.add(lblNome, gbc_lblNome);

		tfNome = new JTextField();
		GridBagConstraints gbc_tfNome = new GridBagConstraints();
		gbc_tfNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNome.insets = new Insets(0, 0, 5, 0);
		gbc_tfNome.gridx = 1;
		gbc_tfNome.gridy = 0;
		panel_1.add(tfNome, gbc_tfNome);
		tfNome.setColumns(10);
		tfNome.setText(hospede.getNome());
		tfNome.setEditable(false);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 0, 10, 5);
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1;
		add(panel_1, gbc_panel_1);

		JLabel lblCpf = new JLabel("CPF:");
		GridBagConstraints gbc_lblCpf = new GridBagConstraints();
		gbc_lblCpf.anchor = GridBagConstraints.EAST;
		gbc_lblCpf.insets = new Insets(0, 0, 5, 10);
		gbc_lblCpf.gridx = 0;
		gbc_lblCpf.gridy = 1;
		panel_1.add(lblCpf, gbc_lblCpf);

		tfCpf = new JFormattedTextField(ftmCpf);
		GridBagConstraints gbc_tfCpf = new GridBagConstraints();
		gbc_tfCpf.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfCpf.insets = new Insets(0, 0, 5, 0);
		gbc_tfCpf.gridx = 1;
		gbc_tfCpf.gridy = 1;
		panel_1.add(tfCpf, gbc_tfCpf);
		tfCpf.setColumns(10);
		tfCpf.setText(hospede.getCpf());
		tfCpf.setEditable(false);

		JLabel lblNewLabel = new JLabel("Telefone:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 10);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);

		tfTelefone = new JFormattedTextField(ftmTelefone);
		tfTelefone.setEditable(false);
		GridBagConstraints gbc_tfTelefone = new GridBagConstraints();
		gbc_tfTelefone.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfTelefone.insets = new Insets(0, 0, 5, 0);
		gbc_tfTelefone.gridx = 1;
		gbc_tfTelefone.gridy = 2;
		panel_1.add(tfTelefone, gbc_tfTelefone);
		tfTelefone.setColumns(10);
		tfTelefone.setText(hospede.getTelefone());

		JLabel lblEmail = new JLabel("E-mail:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 10);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 3;
		panel_1.add(lblEmail, gbc_lblEmail);

		tfEmail = new JTextField();
		tfEmail.setEditable(false);
		GridBagConstraints gbc_tfEmail = new GridBagConstraints();
		gbc_tfEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfEmail.insets = new Insets(0, 0, 5, 0);
		gbc_tfEmail.gridx = 1;
		gbc_tfEmail.gridy = 3;
		panel_1.add(tfEmail, gbc_tfEmail);
		tfEmail.setColumns(10);
		tfEmail.setText(hospede.getEmail());
		JLabel lblCidade = new JLabel("Cidade:");
		GridBagConstraints gbc_lblCidade = new GridBagConstraints();
		gbc_lblCidade.anchor = GridBagConstraints.EAST;
		gbc_lblCidade.insets = new Insets(0, 0, 5, 10);
		gbc_lblCidade.gridx = 0;
		gbc_lblCidade.gridy = 4;
		panel_1.add(lblCidade, gbc_lblCidade);

		tfCidade = new JTextField();
		tfCidade.setEditable(false);
		GridBagConstraints gbc_tfCidade = new GridBagConstraints();
		gbc_tfCidade.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfCidade.insets = new Insets(0, 0, 5, 0);
		gbc_tfCidade.gridx = 1;
		gbc_tfCidade.gridy = 4;
		panel_1.add(tfCidade, gbc_tfCidade);
		tfCidade.setColumns(10);
		tfCidade.setText(hospede.getCidade());

		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		GridBagConstraints gbc_lblEndereo = new GridBagConstraints();
		gbc_lblEndereo.anchor = GridBagConstraints.EAST;
		gbc_lblEndereo.insets = new Insets(0, 0, 0, 10);
		gbc_lblEndereo.gridx = 0;
		gbc_lblEndereo.gridy = 5;
		panel_1.add(lblEndereo, gbc_lblEndereo);

		tfEndereco = new JTextField();
		tfEndereco.setEditable(false);
		GridBagConstraints gbc_tfEndereco = new GridBagConstraints();
		gbc_tfEndereco.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfEndereco.gridx = 1;
		gbc_tfEndereco.gridy = 5;
		panel_1.add(tfEndereco, gbc_tfEndereco);
		tfEndereco.setColumns(10);
		tfEndereco.setText(hospede.getEndereco());

		JPanel panel_6 = new JPanel();
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.anchor = GridBagConstraints.NORTH;
		gbc_panel_6.insets = new Insets(0, 0, 0, 5);
		gbc_panel_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_6.gridx = 1;
		gbc_panel_6.gridy = 2;
		add(panel_6, gbc_panel_6);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[] {200, 0, 0};
		gbl_panel_6.rowHeights = new int[] {0};
		gbl_panel_6.columnWeights = new double[]{0.0, 1.0, 0.0};
		gbl_panel_6.rowWeights = new double[]{0.0};
		panel_6.setLayout(gbl_panel_6);

		errorLabel = new JLabel("<erro>");
		errorLabel.setForeground(Color.RED);
		GridBagConstraints gbc_errorLabel = new GridBagConstraints();
		gbc_errorLabel.anchor = GridBagConstraints.WEST;
		gbc_errorLabel.insets = new Insets(0, 0, 0, 5);
		gbc_errorLabel.gridx = 0;
		gbc_errorLabel.gridy = 0;
		panel_6.add(errorLabel, gbc_errorLabel);
		errorLabel.setVisible(false);
		errorLabel.setIcon(new ImageIcon(AtualizarHospede.class
				.getResource("/gui/images/error.png")));
		GridBagConstraints gbc_btnVoltar = new GridBagConstraints();
		gbc_btnVoltar.anchor = GridBagConstraints.EAST;
		gbc_btnVoltar.insets = new Insets(0, 0, 0, 10);
		gbc_btnVoltar.gridx = 1;
		gbc_btnVoltar.gridy = 0;
		btnVoltar.setPreferredSize(new Dimension(75, 23));
		btnVoltar.setMinimumSize(new Dimension(75, 23));
		btnVoltar.setMaximumSize(new Dimension(75, 23));
		btnVoltar.setFocusable(false);
		panel_6.add(btnVoltar, gbc_btnVoltar);
		GridBagConstraints gbc_btnAtualizar = new GridBagConstraints();
		gbc_btnAtualizar.gridx = 2;
		gbc_btnAtualizar.gridy = 0;
		btnAtualizar.setMaximumSize(new Dimension(79, 23));
		btnAtualizar.setMinimumSize(new Dimension(79, 23));
		btnAtualizar.setPreferredSize(new Dimension(79, 23));
		btnAtualizar.setFocusable(false);
		panel_6.add(btnAtualizar, gbc_btnAtualizar);

		btnAtualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!editavel) atualizaEstadoDaTela(true);
				else if(atualizarHospede()) atualizaEstadoDaTela(false);
			}
		});

		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(editavel) atualizaEstadoDaTela(false);
				else Sistema.setTela(telaAnterior);
			}
		});
	}


	private void atualizaEstadoDaTela(boolean b) {
		editavel = b;

		tfCidade.setEditable(b);
		tfEndereco.setEditable(b);
		tfTelefone.setEditable(b);
		tfEmail.setEditable(b);

		tfCidade.setText(hospede.getCidade());
		tfEndereco.setText(hospede.getEndereco());
		tfTelefone.setText(hospede.getTelefone());
		tfEmail.setText(hospede.getEmail());
		errorLabel.setVisible(false);

		if(b) {
			btnVoltar.setText("Cancelar");
			btnAtualizar.setText("Atualizar");
		}

		else {
			btnVoltar.setText("Voltar");
			btnAtualizar.setText("Editar");
		}
	}

	private boolean atualizarHospede() {
		String telefone = tfTelefone.getText();
		String email = tfEmail.getText();
		String cidade = tfCidade.getText();
		String endereco = tfEndereco.getText();

		if (!Pattern.matches("\\(\\d{2}\\) \\d{4}-\\d{4}", telefone)){
			errorLabel.setText("Telefone inv\u00E1lido");
			errorLabel.setVisible(true);
			return false;
		}

		if (!Internet.isEmailValido(email)) {
			errorLabel.setText("E-mail inv\u00E1lido");
			errorLabel.setVisible(true);
			return false;
		}

		if (cidade.length() < 3) {
			errorLabel.setText("Cidade inv\u00E1lida");
			errorLabel.setVisible(true);
			return false;
		}

		if (endereco.length() < 3) {
			errorLabel.setText("Endere\u00E7o inv\u00E1lido");
			errorLabel.setVisible(true);
			return false;
		}

		hospede.setEmail(email);
		hospede.setEndereco(endereco);
		hospede.setCidade(cidade);
		hospede.setTelefone(telefone);
		return true;
	}
}