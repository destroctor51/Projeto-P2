package gui.hospede;

import gui.Menu;
import gui.Sistema;

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

import core.hotel.Hospede;

public class PesquisaHospede extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tf_nome;
	protected String nome;
	private static JList<Hospede> list;
	private static JList<Hospede> list_1;
	private JLabel ErrorLabel;
	private static DefaultListModel<Hospede> dlm = new DefaultListModel<Hospede>();
	private static DefaultListModel<Hospede> dlm1 = new DefaultListModel<Hospede>();

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
		
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Sistema.setTela(new Menu());
					}
				});
		
				list_1 = new JList<Hospede>();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(list_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(ErrorLabel, GroupLayout.PREFERRED_SIZE, 342, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnCancelar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnVisualizar, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAtualizar))
						.addComponent(lblHspedeSelecionado, Alignment.LEADING))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
					.addGap(22)
					.addComponent(lblHspedeSelecionado)
					.addGap(7)
					.addComponent(list_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnAtualizar)
							.addComponent(btnVisualizar)
							.addComponent(btnExcluir)
							.addComponent(btnCancelar))
						.addComponent(ErrorLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(108))
		);

		list = new JList<Hospede>();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = list.locationToIndex(e.getPoint());
				Object item = dlm.getElementAt(index);
				selecionaHospede((Hospede) item);
			}
		});
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
		
		JButton btnCadastrar = new JButton("Cadastrar");
		panel_1.add(btnCadastrar);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(new CadastraHospede());
				
			}
		});

		JLabel lblPesquisarHospde = new JLabel("Pesquisar Hosp\u00E9de");
		lblPesquisarHospde.setFont(new Font("Tahoma", Font.BOLD, 22));
		panel.add(lblPesquisarHospde);
		setLayout(groupLayout);
	}

	private boolean checaSemelhanca(String palavra, String str) {
		for (int i = 0; i <= str.length() - palavra.length(); i++)
			if (str.substring(i, i + palavra.length()).toLowerCase().equals(palavra.toLowerCase()))
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

	private void selecionaHospede(Hospede hospede) {
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

	public static DefaultListModel<Hospede> getDlm1() {
		return dlm1;
	}
}