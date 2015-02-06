package gui.contratos;

import gui.Menu;
import gui.Sistema;
import gui.components.Calendario;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import servicos.devolviveis.Quarto;
import servicos.devolviveis.TipoQuarto;
import tempo.Estacao;
import tempo.Periodo;


public class RealizaReserva extends JPanel {
	private static final long serialVersionUID = 5463443942126778065L;
	private JTextField NameField;
	private JTextField cardField;
	private JTable table;
	private JButton btnSearch_1;
	private Hospede hospede;
	private Calendario calendario;
	private Periodo estadia;
	private JList<Object> list_1;

	private JLabel ErrorLabel;
	private DefaultListModel<Object> lista = new DefaultListModel<>();

	/**
	 * Create the panel.
	 */
	public RealizaReserva() {
		setVisible(false);

		JPanel panel = new JPanel();

		JPanel panel_1 = new JPanel();

		JPanel panel_2 = new JPanel();
		JList<String> list = new JList<>();

		JPanel panel_3 = new JPanel();

		JPanel panel_4 = new JPanel();

		ErrorLabel = new JLabel("");
		ErrorLabel.setForeground(Color.RED);
		ErrorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		ErrorLabel.setVisible(false);
		ErrorLabel.setIcon(new ImageIcon(RealizaReserva.class.getResource("/gui/images/error.png")));

		JPanel panel_5 = new JPanel();

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE)
										.addContainerGap())
										.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
																.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
																		.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE)
																		.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(ComponentPlacement.RELATED))
																		.addGroup(groupLayout.createSequentialGroup()
																				.addComponent(ErrorLabel, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
																				.addGap(282)
																				.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(ComponentPlacement.RELATED))
																				.addGroup(groupLayout.createSequentialGroup()
																						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
																								.addGroup(groupLayout.createSequentialGroup()
																										.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE)
																										.addGap(6)))
																										.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)))
																										.addGap(12))
																										.addGroup(groupLayout.createSequentialGroup()
																												.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE)
																												.addContainerGap())))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(58)
										.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)))
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
														.addGroup(groupLayout.createSequentialGroup()
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE))
																.addGroup(groupLayout.createSequentialGroup()
																		.addGap(100)
																		.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																		.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
																		.addPreferredGap(ComponentPlacement.UNRELATED)
																		.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(ComponentPlacement.UNRELATED)
																		.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
																				.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																				.addComponent(ErrorLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
																				.addContainerGap())
				);

		table = new JTable();
		scrollPane.setViewportView(table);

		DefaultTableModel tablem = new DefaultTableModel(
				new Object[][] {
						{"PRESIDENCIAL", 0},
						{"LUXO_SIMPLES", 0},
						{"LUXO_DUPLO", 0},
						{"LUXO_TRIPLO", 0},
						{"EXECUTIVO_SIMPLES", 0},
						{"EXECUTIVO_DUPLO", 0},
						{"EXECUTIVO_TRIPLO", 0},
				},
				new String[] {
						"Tipo de quarto", "Quantidade dispon\u00EDvel"
				}
				) {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, Integer.class
			};
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};


		table.setModel(tablem);
		table.getColumnModel().getColumn(0).setPreferredWidth(453);
		table.getColumnModel().getColumn(1).setPreferredWidth(172);
		table.setBorder(null);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

		list_1 = new JList<Object>();
		list_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Hospede hosp = (Hospede) list_1.getSelectedValue();
				if (hosp != null)
					setHospede(hosp);
			}
		});
		scrollPane_1.setViewportView(list_1);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(new Menu(true));
			}
		});
		panel_5.add(btnCancelar);
		btnCancelar.setHorizontalAlignment(SwingConstants.RIGHT);

		JButton btnConfirmar = new JButton("Continuar");
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					continuar();
				} catch (Exception e) {
				}
			}
		});
		panel_5.add(btnConfirmar);
		btnConfirmar.setHorizontalAlignment(SwingConstants.RIGHT);

		JButton checkButton = new JButton("Checar Disponibilidade");
		panel_4.add(checkButton);
		checkButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				checarDisponibilidade();
			}
		});

		JLabel lblEstacao = new JLabel("Estadia :");
		panel_3.add(lblEstacao);

		calendario = new Calendario();
		panel_3.add(calendario);

		JLabel lblCartoDeCrdito = new JLabel("Cartão de crédito :");
		panel_2.add(lblCartoDeCrdito);

		cardField = new JTextField();
		panel_2.add(cardField);
		cardField.setColumns(20);

		JLabel lblProcurarHspede = new JLabel("Procurar Hóspede :");
		lblProcurarHspede.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(lblProcurarHspede);

		NameField = new JTextField();
		panel_1.add(NameField);
		NameField.setColumns(45);

		BufferedImage buttonIcon = null;
		try {
			buttonIcon = ImageIO.read(new File(RealizaReserva.class.getResource("/gui/images/search1.png").toURI()));
		} catch (IOException e) {
		} catch (URISyntaxException e) {
		}
		btnSearch_1 = new JButton(new ImageIcon(buttonIcon));
		btnSearch_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pesquisaHospede();
			}
		});
		btnSearch_1.setBorder(BorderFactory.createEmptyBorder());
		btnSearch_1.setContentAreaFilled(false);
		panel_1.add(btnSearch_1);

		JLabel lblNewLabel = new JLabel("Realiza Reserva");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(lblNewLabel);
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
			list_1.setModel(lista);
			ErrorLabel.setVisible(false);
		} catch (IllegalArgumentException e) {
			ErrorLabel.setText("Nome inválido.");
			ErrorLabel.setVisible(true);
		}
	}

	private void checarDisponibilidade() {
		estadia = calendario.getSelecao();
		String cartao = cardField.getText();

		if (hospede == null) {
			ErrorLabel.setText("Hóspede ainda não escolhido.");
			ErrorLabel.setVisible(true);
			return;
		}
		else if (estadia == null) {
			ErrorLabel.setText("Estação não foi selecionada.");
			ErrorLabel.setVisible(true);
			return;
		}
		else if (!(Hospede.verificaCartao(cartao)) || cartao.equals("")) {
			ErrorLabel.setText("Cartão invalido.");
			ErrorLabel.setVisible(true);
			return;
		}
		ErrorLabel.setVisible(false);

		int pres = 0, ls = 0, ld = 0, lt = 0, es = 0, ed = 0, et = 0;
		for (Quarto quarto : Sistema.getHotel().getQuartosDisponiveis(estadia)) {
			if (quarto.getTipo().equals(TipoQuarto.PRESIDENCIAL)) {
				pres++;
				table.setValueAt(pres, 0, 1);
			}
			else if (quarto.getTipo().equals(TipoQuarto.LUXO_SIMPLES)) {
				ls++;
				table.setValueAt(ls, 1, 1);
			}
			else if (quarto.getTipo().equals(TipoQuarto.LUXO_DUPLO)) {
				ld++;
				table.setValueAt(ld, 2, 1);
			}
			else if (quarto.getTipo().equals(TipoQuarto.LUXO_TRIPLO)) {
				lt++;
				table.setValueAt(lt, 3, 1);
			}
			else if (quarto.getTipo().equals(TipoQuarto.EXECUTIVO_SIMPLES)) {
				es++;
				table.setValueAt(es, 4, 1);
			}
			else if (quarto.getTipo().equals(TipoQuarto.EXECUTIVO_DUPLO)) {
				ed++;
				table.setValueAt(ed, 5, 1);
			}
			else if (quarto.getTipo().equals(TipoQuarto.EXECUTIVO_TRIPLO)) {
				et++;
				table.setValueAt(et, 6, 1);
			}
		}
	}

	private void continuar() throws Exception {
		estadia = calendario.getSelecao();
		String cartao = cardField.getText();

		if (hospede == null) {
			ErrorLabel.setText("Hóspede ainda não escolhido.");
			ErrorLabel.setVisible(true);
			return;
		}
		else if (estadia == null) {
			ErrorLabel.setText("Estação não foi selecionada.");
			ErrorLabel.setVisible(true);
			return;
		}
		else if (!(Hospede.verificaCartao(cartao)) || cartao.equals("")) {
			ErrorLabel.setText("Cartão invalido.");
			ErrorLabel.setVisible(true);
			return;
		}
		ErrorLabel.setVisible(false);

		double tarifa = 1;

		Iterator<Estacao> estacoes = Sistema.getHotel().getTarifas();

		while (estacoes.hasNext()) {
			Estacao est = estacoes.next();

			if (est.entraEmConflito(estadia)) {
				tarifa = est.getTarifa();
				break;
			}
		}

		Sistema.setTela(new SelecionarQuartos(estadia, hospede, cartao, tarifa));
	}

	private void setHospede(Hospede hosp) {
		if (hosp == null)
			throw new IllegalArgumentException();
		hospede = hosp;
	}

	private boolean checaSemelhanca(String palavra, String str) {
		for (int i = 0; i <= str.length() - palavra.length(); i++)
			if (str.substring(i, i + palavra.length()).toLowerCase().equals(palavra.toLowerCase()))
				return true;

		return false;
	}
}

