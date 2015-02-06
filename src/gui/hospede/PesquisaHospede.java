package gui.hospede;

import gui.Menu;
import gui.Sistema;
import hotel.Hospede;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
import javax.swing.ListModel;

public class PesquisaHospede extends JPanel {
	private JTextField tf_nome;
	protected String nome;
	private static JList list;
	private static JList list_1;
	private JLabel ErrorLabel;
	private static DefaultListModel dlm = new DefaultListModel();
	private static DefaultListModel dlm1 = new DefaultListModel();

	/**
	 * Create the panel.
	 *
	 * @throws Exception
	 */
	public PesquisaHospede() {

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				excluirHospede();
			}
		});

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (dlm1.size() == 0) {
					ErrorLabel.setText("Selecione um hospede.");
					ErrorLabel.setVisible(true);
					return;
				}
				Sistema.setTela(new AtualizaHospede((Hospede) dlm1
						.getElementAt(0)));
				ErrorLabel.setVisible(false);
			}
		});

		JPanel panel = new JPanel();

		JPanel panel_1 = new JPanel();

		JScrollPane scrollPane = new JScrollPane();

		JScrollPane scrollPane_1 = new JScrollPane();

		JLabel lblHspedeSelecionado = new JLabel("H\u00F3spede Selecionado :");

		JButton btnVisualizar = new JButton("Visualizar");
		btnVisualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (dlm1.size() == 0) {
					ErrorLabel.setText("Selecione um hospede.");
					ErrorLabel.setVisible(true);
					return;
				}
				Sistema.setTela(new VisualizaHospede((Hospede) dlm1
						.getElementAt(0)));
				ErrorLabel.setVisible(false);
			}
		});

		ErrorLabel = new JLabel("");
		ErrorLabel.setVisible(false);
		ErrorLabel.setIcon(new ImageIcon(PesquisaHospede.class
				.getResource("/gui/images/error.png")));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
		.setHorizontalGroup(groupLayout
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						Alignment.TRAILING,
						groupLayout
						.createSequentialGroup()
						.addGroup(
								groupLayout
								.createParallelGroup(
										Alignment.TRAILING)
										.addGroup(
												Alignment.LEADING,
												groupLayout
												.createSequentialGroup()
												.addGap(28)
												.addComponent(
														ErrorLabel,
														GroupLayout.PREFERRED_SIZE,
														342,
														GroupLayout.PREFERRED_SIZE))
														.addGroup(
																Alignment.LEADING,
																groupLayout
																.createSequentialGroup()
																.addContainerGap()
																.addGroup(
																		groupLayout
																		.createParallelGroup(
																				Alignment.LEADING)
																				.addComponent(
																						scrollPane_1,
																						Alignment.TRAILING,
																						GroupLayout.DEFAULT_SIZE,
																						696,
																						Short.MAX_VALUE)
																						.addComponent(
																								scrollPane,
																								Alignment.TRAILING,
																								GroupLayout.DEFAULT_SIZE,
																								696,
																								Short.MAX_VALUE)
																								.addComponent(
																										panel,
																										Alignment.TRAILING,
																										GroupLayout.DEFAULT_SIZE,
																										696,
																										Short.MAX_VALUE)
																										.addComponent(
																												panel_1,
																												Alignment.TRAILING,
																												GroupLayout.DEFAULT_SIZE,
																												696,
																												Short.MAX_VALUE)
																												.addGroup(
																														Alignment.TRAILING,
																														groupLayout
																														.createSequentialGroup()
																														.addComponent(
																																btnExcluir,
																																GroupLayout.PREFERRED_SIZE,
																																89,
																																GroupLayout.PREFERRED_SIZE)
																																.addPreferredGap(
																																		ComponentPlacement.RELATED)
																																		.addComponent(
																																				btnAtualizar)
																																				.addPreferredGap(
																																						ComponentPlacement.UNRELATED)
																																						.addComponent(
																																								btnVisualizar))
																																								.addComponent(
																																										lblHspedeSelecionado,
																																										Alignment.TRAILING))))
																																										.addContainerGap()));
		groupLayout
		.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
						groupLayout
						.createSequentialGroup()
						.addComponent(panel,
								GroupLayout.PREFERRED_SIZE, 45,
								GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(panel_1,
										GroupLayout.PREFERRED_SIZE, 36,
										GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(scrollPane,
												GroupLayout.PREFERRED_SIZE, 159,
												GroupLayout.PREFERRED_SIZE)
												.addGap(22)
												.addComponent(lblHspedeSelecionado)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(scrollPane_1,
														GroupLayout.PREFERRED_SIZE, 23,
														GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addGroup(
																groupLayout
																.createParallelGroup(
																		Alignment.BASELINE)
																		.addComponent(btnExcluir)
																		.addComponent(btnAtualizar)
																		.addComponent(btnVisualizar))
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(ErrorLabel,
																				GroupLayout.PREFERRED_SIZE, 23,
																				GroupLayout.PREFERRED_SIZE).addGap(78)));

		list_1 = new JList();
		scrollPane_1.setViewportView(list_1);

		list = new JList();
		list.addMouseListener(new doubleClick9(list));
		scrollPane.setViewportView(list);

		JLabel lblHspede = new JLabel("H\u00F3spede :");
		panel_1.add(lblHspede);

		tf_nome = new JTextField();
		panel_1.add(tf_nome);
		tf_nome.setColumns(40);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pesquisaHospede();
			}
		});
		panel_1.add(btnPesquisar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(new Menu());
			}
		});
		panel_1.add(btnCancelar);

		JLabel lblPesquisarHospde = new JLabel("Pesquisar Hosp\u00E9de");
		lblPesquisarHospde.setFont(new Font("Tahoma", Font.BOLD, 22));
		panel.add(lblPesquisarHospde);
		setLayout(groupLayout);
	}

	private boolean checaSemelhanca(String palavra, String str) {
		for (int i = 0; i <= str.length() - palavra.length(); i++)
			if (str.substring(i, i + palavra.length()).equals(palavra))
				return true;

		return false;
	}

	private void pesquisaHospede() {
		dlm.clear();
		dlm1.clear();
		String nome = tf_nome.getText();

		if (nome.equals("")) {
			ErrorLabel.setText("Digite um nome.");
			ErrorLabel.setVisible(true);
			return;
		}

		for (Hospede hosp : Sistema.getHotel().getHospedes()) {
			if (checaSemelhanca(nome, hosp.getNome())) {
				dlm.addElement(hosp);
			}
		}

		list.setModel(dlm);
		ErrorLabel.setVisible(false);
	}

	public static void selecionaHospede(Hospede hospede) {
		dlm1.clear();
		dlm1.addElement(hospede);
		list_1.setModel(dlm1);
	}

	private void excluirHospede() {
		if (dlm1.size() == 0) {
			ErrorLabel.setText("Selecione um hospede.");
			ErrorLabel.setVisible(true);
			return;
		}
		Sistema.getHotel().removeHospede((Hospede) dlm1.getElementAt(0));
		dlm1.clear();
		dlm.clear();
		ErrorLabel.setVisible(false);
	}

	public static DefaultListModel getDlm1() {
		return dlm1;
	}
}

class doubleClick9 extends MouseAdapter {
	protected JList list;

	public doubleClick9(JList l) {
		list = l;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			int index = list.locationToIndex(e.getPoint());
			ListModel dlm = list.getModel();
			Object item = dlm.getElementAt(index);
			list.ensureIndexIsVisible(index);
			PesquisaHospede.selecionaHospede((Hospede) item);
		}
	}

}