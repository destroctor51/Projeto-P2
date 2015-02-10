package gui.hospede;

import gui.Sistema;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JButton;

import core.hotel.Hospede;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VisualizaHospede extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 * 
	 * @param hospede
	 */
	public VisualizaHospede(Hospede hospede) {

		JButton btnNewButton = new JButton("Retornar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					PesquisaHospede.getDlm1().clear();
					Sistema.setTela(new PesquisaHospede());
			}
		});

		JPanel panel = new JPanel();

		JPanel panel_1 = new JPanel();

		JPanel panel_2 = new JPanel();

		JPanel panel_3 = new JPanel();

		JPanel panel_4 = new JPanel();

		JPanel panel_5 = new JPanel();

		JPanel panel_6 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE)
						.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(47)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_6, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panel_5, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panel_4, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(47)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
								.addComponent(panel_3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE))))
					.addGap(68))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(24)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);

		JLabel lblEmail = new JLabel("E-mail :      ");
		panel_6.add(lblEmail);

		JTextArea textArea_6 = new JTextArea();
		textArea_6.setColumns(40);
		panel_6.add(textArea_6);
		textArea_6.setText(hospede.getEmail());

		JLabel lblNewLabel = new JLabel("Telefone :  ");
		panel_5.add(lblNewLabel);

		JTextArea textArea_5 = new JTextArea();
		textArea_5.setColumns(40);
		panel_5.add(textArea_5);
		textArea_5.setText(hospede.getTelefone());

		JLabel lblEndereo = new JLabel("Endere\u00E7o :");
		panel_4.add(lblEndereo);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setColumns(40);
		panel_4.add(textArea_1);
		textArea_1.setText(hospede.getEndereco());

		JLabel lblCidade = new JLabel("Cidade :    ");
		panel_3.add(lblCidade);

		JTextArea textArea = new JTextArea();
		textArea.setColumns(40);
		panel_3.add(textArea);
		textArea.setText(hospede.getCidade());

		JLabel lblCpf = new JLabel("Cpf :         ");
		panel_2.add(lblCpf);

		JTextArea textArea_4 = new JTextArea();
		textArea_4.setColumns(40);
		panel_2.add(textArea_4);
		textArea_4.setText(hospede.getCpf());

		JLabel lblNome = new JLabel("Nome :     ");
		panel_1.add(lblNome);

		JTextArea textArea_3 = new JTextArea();
		textArea_3.setColumns(40);
		panel_1.add(textArea_3);
		textArea_3.setText(hospede.getNome());

		JLabel lblVisualizarHspede = new JLabel("Visualiza H\u00F3spede");
		lblVisualizarHspede.setFont(new Font("Tahoma", Font.BOLD, 22));
		panel.add(lblVisualizarHspede);
		setLayout(groupLayout);

	}
}
