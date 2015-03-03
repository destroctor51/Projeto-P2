package gui.hospede;

import gui.Sistema;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.MaskFormatter;

import utils.Internet;
import core.hotel.Hospede;

public class AtualizaHospede extends JPanel {

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
	private JLabel ErrorLabel;

	private boolean editavel;
	JButton btnAtualizar = new JButton("Editar");
	JButton btnVoltar = new JButton("Voltar");

	/**
	 * Create the panel.
	 */
	public AtualizaHospede(Hospede hospede, final JPanel telaAnterior) {

		this.hospede = hospede;
		this.editavel = false;

		JPanel panel = new JPanel();
		panel.setName("Atualiza H\u00F3spede");

		btnAtualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (!editavel)
					atualizaEstadoDaTela(true);
				else if (editavel) {
					try {
						if(atualizarHospede())
							Sistema.setTela(telaAnterior);
					} catch (Exception e1) {

					}
				}
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

		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (editavel)
					atualizaEstadoDaTela(false);
				else
					Sistema.setTela(telaAnterior);
			}
		});

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
		.setHorizontalGroup(groupLayout
				.createParallelGroup(Alignment.TRAILING)
				.addGroup(
						groupLayout
						.createSequentialGroup()
						.addGroup(
								groupLayout
								.createParallelGroup(
										Alignment.LEADING)
										.addGroup(
												groupLayout
												.createSequentialGroup()
												.addContainerGap()
												.addComponent(
														panel,
														GroupLayout.DEFAULT_SIZE,
														696,
														Short.MAX_VALUE))
														.addGroup(
																groupLayout
																.createSequentialGroup()
																.addContainerGap()
																.addComponent(
																		panel_1,
																		GroupLayout.DEFAULT_SIZE,
																		696,
																		Short.MAX_VALUE))
																		.addGroup(
																				groupLayout
																				.createSequentialGroup()
																				.addGap(12)
																				.addGroup(
																						groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																								.addComponent(
																										panel_4,
																										Alignment.TRAILING,
																										GroupLayout.DEFAULT_SIZE,
																										696,
																										Short.MAX_VALUE)
																										.addComponent(
																												panel_5,
																												Alignment.TRAILING,
																												GroupLayout.DEFAULT_SIZE,
																												696,
																												Short.MAX_VALUE)
																												.addComponent(
																														panel_6,
																														GroupLayout.DEFAULT_SIZE,
																														696,
																														Short.MAX_VALUE)))
																														.addGroup(
																																groupLayout
																																.createSequentialGroup()
																																.addGap(12)
																																.addGroup(
																																		groupLayout
																																		.createParallelGroup(
																																				Alignment.LEADING)
																																				.addComponent(
																																						panel_2,
																																						Alignment.TRAILING,
																																						GroupLayout.DEFAULT_SIZE,
																																						696,
																																						Short.MAX_VALUE)
																																						.addComponent(
																																								panel_3,
																																								GroupLayout.DEFAULT_SIZE,
																																								696,
																																								Short.MAX_VALUE)))
																																								.addGroup(
																																										groupLayout
																																										.createSequentialGroup()
																																										.addContainerGap()
																																										.addComponent(
																																												ErrorLabel,
																																												GroupLayout.PREFERRED_SIZE,
																																												321,
																																												GroupLayout.PREFERRED_SIZE)
																																												.addPreferredGap(
																																														ComponentPlacement.RELATED,
																																														175,
																																														Short.MAX_VALUE)
																																														.addComponent(
																																																btnVoltar)
																																																.addPreferredGap(
																																																		ComponentPlacement.RELATED)
																																																		.addComponent(
																																																				btnAtualizar)))
																																																				.addContainerGap()));
		groupLayout
		.setVerticalGroup(groupLayout
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						groupLayout
						.createSequentialGroup()
						.addComponent(panel,
								GroupLayout.PREFERRED_SIZE, 41,
								GroupLayout.PREFERRED_SIZE)
								.addGap(24)
								.addComponent(panel_1,
										GroupLayout.PREFERRED_SIZE, 48,
										GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
												.addComponent(panel_2,
														GroupLayout.PREFERRED_SIZE, 50,
														GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(
																ComponentPlacement.RELATED)
																.addComponent(panel_3,
																		GroupLayout.PREFERRED_SIZE, 46,
																		GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																				.addComponent(panel_4,
																						GroupLayout.PREFERRED_SIZE, 47,
																						GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(
																								ComponentPlacement.UNRELATED)
																								.addComponent(panel_5,
																										GroupLayout.PREFERRED_SIZE, 47,
																										GroupLayout.PREFERRED_SIZE)
																										.addGap(12)
																										.addComponent(panel_6,
																												GroupLayout.PREFERRED_SIZE, 44,
																												GroupLayout.PREFERRED_SIZE)
																												.addPreferredGap(
																														ComponentPlacement.RELATED, 47,
																														Short.MAX_VALUE)
																														.addGroup(
																																groupLayout
																																.createParallelGroup(
																																		Alignment.LEADING)
																																		.addComponent(
																																				ErrorLabel,
																																				GroupLayout.PREFERRED_SIZE,
																																				26,
																																				GroupLayout.PREFERRED_SIZE)
																																				.addGroup(
																																						groupLayout
																																						.createParallelGroup(
																																								Alignment.BASELINE)
																																								.addComponent(
																																										btnAtualizar,
																																										GroupLayout.PREFERRED_SIZE,
																																										26,
																																										GroupLayout.PREFERRED_SIZE)
																																										.addComponent(
																																												btnVoltar)))
																																												.addContainerGap()));

		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		panel_6.add(lblEndereo);

		tfEndereco = new JTextField();
		panel_6.add(tfEndereco);
		tfEndereco.setColumns(40);
		tfEndereco.setText(hospede.getEndereco());
		JLabel lblCidade = new JLabel("Cidade:");
		panel_5.add(lblCidade);

		tfCidade = new JTextField();
		panel_5.add(tfCidade);
		tfCidade.setColumns(40);
		tfCidade.setText(hospede.getCidade());

		JLabel lblEmail = new JLabel("E-mail:");
		panel_4.add(lblEmail);

		tfEmail = new JTextField();
		panel_4.add(tfEmail);
		tfEmail.setColumns(40);
		tfEmail.setText(hospede.getEmail());

		JLabel lblNewLabel = new JLabel("Telefone:");
		panel_3.add(lblNewLabel);

		try {
			ftmTelefone = new MaskFormatter("(##) ####-####");
		} catch (ParseException e2) {
			ErrorLabel.setText("Telefone inv\u00E1lido");
			ErrorLabel.setVisible(true);
		}

		tfTelefone = new JFormattedTextField(ftmTelefone);
		panel_3.add(tfTelefone);
		tfTelefone.setColumns(40);
		tfTelefone.setText(hospede.getTelefone());

		ftmTelefone.setValidCharacters("0123456789");

		JLabel lblCpf = new JLabel("CPF:");
		panel_2.add(lblCpf);

		try {
			ftmCpf = new MaskFormatter("###.###.###-##");
		} catch (ParseException e2) {
			ErrorLabel.setText("CPF inv\u00E1lido");
			ErrorLabel.setVisible(true);
		}

		tfCpf = new JFormattedTextField(ftmCpf);
		panel_2.add(tfCpf);
		tfCpf.setColumns(40);
		tfCpf.setText(hospede.getCpf());
		tfCpf.setEditable(false);

		ftmCpf.setValidCharacters("0123456789");

		JLabel lblNome = new JLabel("Nome:");
		panel_1.add(lblNome);

		tfNome = new JTextField();
		panel_1.add(tfNome);
		tfNome.setColumns(40);
		tfNome.setText(hospede.getNome());
		tfNome.setEditable(false);

		setTextFields(false);
		setLayout(groupLayout);

	}


	private void atualizaEstadoDaTela(boolean b) {
		editavel = b;
		setTextFields(b);

		if (b){
			btnVoltar.setText("Cancelar");
			btnAtualizar.setText("Atualizar");
		}
		else{
			btnVoltar.setText("Voltar");
			btnAtualizar.setText("Editar");
		}
	}

	private void setTextFields(boolean b) {
		tfCidade.setEditable(b);
		tfEndereco.setEditable(b);
		tfTelefone.setEditable(b);
		tfEmail.setEditable(b);
	}

	private boolean atualizarHospede() throws Exception {

		String telefone = tfTelefone.getText();

		String email = tfEmail.getText();

		if (!Internet.isEmailValido(email)) {
			ErrorLabel.setText("E-mail inv\u00E1lido");
			ErrorLabel.setVisible(true);
			return false;
		}

		ErrorLabel.setVisible(false);

		String cidade = tfCidade.getText();

		if (cidade.equals("") || cidade.length() < 3) {
			ErrorLabel.setText("Cidade inv\u00E1lida");
			ErrorLabel.setVisible(true);
			return false;
		}

		String endereco = tfEndereco.getText();

		if (endereco.equals("") || endereco.length() < 3) {
			ErrorLabel.setText("Endere\u00E7o inv\u00E1lido");
			ErrorLabel.setVisible(true);
			return false;
		}

		hospede.setEmail(email);
		hospede.setEndereco(endereco);
		hospede.setCidade(cidade);
		hospede.setTelefone(telefone);
		ErrorLabel.setVisible(false);
		return true;
	}
}