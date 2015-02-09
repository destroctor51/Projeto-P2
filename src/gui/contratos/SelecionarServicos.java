package gui.contratos;

import hotel.Contrato;
import interfaces.Alugavel;
import interfaces.Pagavel;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import servicos.alugaveis.Babysitter;
import servicos.alugaveis.CamaExtra;
import servicos.devolviveis.Carro;
import servicos.devolviveis.Quarto;
import servicos.devolviveis.TipoCarro;
import servicos.devolviveis.TipoQuarto;
import tempo.Periodo;

import javax.swing.LayoutStyle.ComponentPlacement;

import gui.Menu;
import gui.Sistema;
import gui.components.Calendario;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.JList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JTextPane;
import javax.swing.JCheckBox;
import javax.swing.JLayeredPane;

public class SelecionarServicos extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Contrato contrato;
	private Alugavel pagavel;
	private List<Babysitter> babasAlugadas = new ArrayList<>();
	private List<Carro> carrosAlugados = new ArrayList<>();
	private List<CamaExtra> camasAlugadas = new ArrayList<>();
	private List<Periodo> pbabasAlugadas = new ArrayList<>();
	private List<Periodo> pcarrosAlugados = new ArrayList<>();
	private List<Periodo> pcamasAlugadas = new ArrayList<>();
	private List<Boolean> tanque = new ArrayList<>();
	private List<Boolean> seguro = new ArrayList<>();
	private JLabel ErrorLabel;
	private JList<Object> list;
	private Calendario calendario;
	private JComboBox<Object> comboBox_1;
	private JComboBox<Object> comboBox;
	private JLabel lblTipoDeCarro;
	private JTextPane textPane;
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox chckbxNewCheckBox_1;
	private JLayeredPane layeredPane;
	private JScrollPane scrollPane_1;
	private JList<Object> list_1;
	private DefaultListModel<Object> lista1 = new DefaultListModel<>();
	private DefaultListModel<Object> lista2 = new DefaultListModel<>();
	private JLabel lblHoraDeIncio;
	private JComboBox<Object> comboBox_2;
	private JComboBox<Object> comboBox_3;
	private JLabel lblHoraDeSada;

	/**
	 * Create the panel.
	 */
	public SelecionarServicos(Contrato contrato) {
		setForeground(Color.BLACK);
		this.contrato = contrato;

		JPanel panel = new JPanel();

		JLabel lblSelecionarServios = new JLabel("Selecionar Serviços");
		lblSelecionarServios.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(lblSelecionarServios);

		ErrorLabel = new JLabel("");
		ErrorLabel.setForeground(Color.RED);
		ErrorLabel.setVisible(false);
		ErrorLabel.setIcon(new ImageIcon(SelecionarServicos.class
				.getResource("/gui/images/error.png")));

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				concluir();
			}
		});

		JButton btnCancelar = new JButton("Retornar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(new AtualizarContratos());
			}
		});

		comboBox_1 = new JComboBox<Object>();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox_1.getSelectedItem().equals("Carro")) {
					lblHoraDeIncio.setVisible(false);
					lblHoraDeSada.setVisible(false);
					comboBox_2.setVisible(false);
					comboBox_3.setVisible(false);

					layeredPane.setLayer(scrollPane_1, -1);
					layeredPane.setLayer(list_1, -1);
					scrollPane_1.setVisible(false);
					list_1.setVisible(false);

					lblTipoDeCarro.setVisible(true);
					comboBox.setVisible(true);
					chckbxNewCheckBox.setVisible(true);
					chckbxNewCheckBox_1.setVisible(true);
				} else if (comboBox_1.getSelectedItem().equals("CamaExtra")) {
					lblHoraDeIncio.setVisible(false);
					lblHoraDeSada.setVisible(false);
					comboBox_2.setVisible(false);
					comboBox_3.setVisible(false);

					layeredPane.setLayer(scrollPane_1, 1);
					layeredPane.setLayer(list_1, 1);
					scrollPane_1.setVisible(true);
					list_1.setVisible(true);

					lblTipoDeCarro.setVisible(false);
					comboBox.setVisible(false);
					chckbxNewCheckBox.setVisible(false);
					chckbxNewCheckBox_1.setVisible(false);
				} else {
					lblHoraDeIncio.setVisible(true);
					lblHoraDeSada.setVisible(true);
					comboBox_2.setVisible(true);
					comboBox_3.setVisible(true);

					layeredPane.setLayer(scrollPane_1, -1);
					layeredPane.setLayer(list_1, -1);
					scrollPane_1.setVisible(false);
					list_1.setVisible(false);

					lblTipoDeCarro.setVisible(false);
					comboBox.setVisible(false);
					chckbxNewCheckBox.setVisible(false);
					chckbxNewCheckBox_1.setVisible(false);
				}
				checaDisponibilidade();
			}
		});
		comboBox_1.setModel(new DefaultComboBoxModel<Object>(new String[] {
				"Babysitter", "Carro", "CamaExtra" }));

		JLabel lblServioDesejado = new JLabel("Serviço desejado :");

		calendario = new Calendario();
		calendario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checaDisponibilidade();
			}
		});

		JLabel lblPeriodoDesejado = new JLabel("Periodo desejado :");

		JLabel lblDisponibilidade = new JLabel("Disponibilidade :");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		JButton btnRemoverServio = new JButton("Remover serviço");
		btnRemoverServio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removerServico();
			}
		});

		JButton btnSelecionarServio = new JButton("Selecionar serviço");
		btnSelecionarServio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selecionarServico();
			}
		});

		textPane = new JTextPane();
		textPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		textPane.setEditable(false);
		textPane.setBackground(Color.RED);

		layeredPane = new JLayeredPane();

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				scrollPane,
																				GroupLayout.DEFAULT_SIZE,
																				696,
																				Short.MAX_VALUE)
																		.addContainerGap())
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								panel,
																								GroupLayout.DEFAULT_SIZE,
																								699,
																								Short.MAX_VALUE)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												lblPeriodoDesejado)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												calendario,
																												GroupLayout.PREFERRED_SIZE,
																												GroupLayout.DEFAULT_SIZE,
																												GroupLayout.PREFERRED_SIZE)
																										.addGap(24)
																										.addGroup(
																												groupLayout
																														.createParallelGroup(
																																Alignment.TRAILING)
																														.addGroup(
																																groupLayout
																																		.createSequentialGroup()
																																		.addComponent(
																																				lblDisponibilidade)
																																		.addPreferredGap(
																																				ComponentPlacement.RELATED)
																																		.addComponent(
																																				textPane,
																																				GroupLayout.DEFAULT_SIZE,
																																				201,
																																				Short.MAX_VALUE))
																														.addGroup(
																																groupLayout
																																		.createSequentialGroup()
																																		.addComponent(
																																				btnSelecionarServio)
																																		.addPreferredGap(
																																				ComponentPlacement.RELATED)))))
																		.addGap(9))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				lblServioDesejado)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				comboBox_1,
																				GroupLayout.PREFERRED_SIZE,
																				201,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18)
																		.addComponent(
																				layeredPane,
																				GroupLayout.DEFAULT_SIZE,
																				335,
																				Short.MAX_VALUE)
																		.addContainerGap())
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				ErrorLabel,
																				GroupLayout.DEFAULT_SIZE,
																				486,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				btnCancelar)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				btnConfirmar)
																		.addContainerGap())
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				btnRemoverServio)
																		.addContainerGap(
																				559,
																				Short.MAX_VALUE)))));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addComponent(panel,
												GroupLayout.PREFERRED_SIZE, 34,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(1)
																		.addComponent(
																				layeredPane,
																				GroupLayout.PREFERRED_SIZE,
																				96,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(33)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addComponent(
																								lblDisponibilidade)
																						.addComponent(
																								textPane,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								lblServioDesejado)
																						.addComponent(
																								comboBox_1,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE))
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addGap(93)
																										.addComponent(
																												lblPeriodoDesejado))
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addGap(12)
																										.addGroup(
																												groupLayout
																														.createParallelGroup(
																																Alignment.TRAILING)
																														.addComponent(
																																calendario,
																																GroupLayout.PREFERRED_SIZE,
																																GroupLayout.DEFAULT_SIZE,
																																GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																btnSelecionarServio))))))
										.addGap(19)
										.addComponent(scrollPane,
												GroupLayout.DEFAULT_SIZE, 105,
												Short.MAX_VALUE)
										.addGap(12)
										.addComponent(btnRemoverServio)
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createParallelGroup(
																				Alignment.BASELINE)
																		.addComponent(
																				btnConfirmar)
																		.addComponent(
																				btnCancelar))
														.addComponent(
																ErrorLabel,
																GroupLayout.PREFERRED_SIZE,
																23,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));

		chckbxNewCheckBox_1 = new JCheckBox("Seguro");
		chckbxNewCheckBox_1.setBounds(122, 61, 76, 23);
		layeredPane.add(chckbxNewCheckBox_1);
		chckbxNewCheckBox_1.setVisible(false);
		layeredPane.setLayer(chckbxNewCheckBox_1, 0);

		chckbxNewCheckBox = new JCheckBox("Tanque cheio");
		chckbxNewCheckBox.setBounds(122, 38, 120, 23);
		layeredPane.add(chckbxNewCheckBox);
		chckbxNewCheckBox.setVisible(false);
		layeredPane.setLayer(chckbxNewCheckBox, 0);

		lblTipoDeCarro = new JLabel("Tipo de carro :");
		lblTipoDeCarro.setBounds(12, 17, 102, 15);
		layeredPane.add(lblTipoDeCarro);

		comboBox = new JComboBox<Object>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checaDisponibilidade();
			}
		});
		comboBox.setBounds(126, 12, 198, 24);
		layeredPane.add(comboBox);
		comboBox.setVisible(false);
		comboBox.setModel(new DefaultComboBoxModel<Object>(TipoCarro.values()));

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		scrollPane_1.setVisible(false);
		layeredPane.setLayer(scrollPane_1, -1);
		scrollPane_1.setBounds(12, 12, 323, 84);
		layeredPane.add(scrollPane_1);

		list_1 = new JList<Object>();
		list_1.setVisible(false);
		scrollPane_1.setViewportView(list_1);
		lblTipoDeCarro.setVisible(false);

		List<Quarto> quartos = new ArrayList<>();

		for (Pagavel servico : contrato.getServicos()) {
			if (servico instanceof Quarto
					&& (!((Quarto) servico).getTipo().equals(
							TipoQuarto.EXECUTIVO_TRIPLO))
					&& (!((Quarto) servico).getTipo().equals(
							TipoQuarto.PRESIDENCIAL)))
				quartos.add((Quarto) servico);
		}

		for (Quarto quarto : quartos) {
			lista2.addElement(quarto);
		}

		list_1.setModel(lista2);

		lblHoraDeIncio = new JLabel("Hora de início :");
		layeredPane.setLayer(lblHoraDeIncio, 2);
		lblHoraDeIncio.setBounds(12, 17, 113, 15);
		layeredPane.add(lblHoraDeIncio);

		comboBox_2 = new JComboBox<Object>();
		comboBox_2.setModel(new DefaultComboBoxModel<Object>(new String[] {
				"00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
				"10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
				"20", "21", "22", "23" }));
		layeredPane.setLayer(comboBox_2, 2);
		comboBox_2.setBounds(132, 12, 76, 24);
		layeredPane.add(comboBox_2);

		comboBox_3 = new JComboBox<Object>();
		comboBox_3.setModel(new DefaultComboBoxModel<Object>(new String[] {
				"00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
				"10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
				"20", "21", "22", "23" }));
		layeredPane.setLayer(comboBox_3, 2);
		comboBox_3.setBounds(132, 56, 76, 24);
		layeredPane.add(comboBox_3);

		lblHoraDeSada = new JLabel("Hora de saída :");
		lblHoraDeSada.setBounds(12, 61, 113, 15);
		layeredPane.add(lblHoraDeSada);

		list = new JList<Object>();
		scrollPane.setViewportView(list);
		setLayout(groupLayout);

	}

	private void selecionarServico() {
		Periodo periodo = calendario.getSelecao();

		if (periodo == null) {
			ErrorLabel.setText("Periodo ainda não escolhido.");
			ErrorLabel.setVisible(true);
			return;
		}

		else if (textPane.getBackground().equals(Color.RED)) {
			ErrorLabel.setText("Serviço não disponível.");
			ErrorLabel.setVisible(true);
			return;
		}

		if (pagavel instanceof Carro) {
			carrosAlugados.add((Carro) pagavel);
			pcarrosAlugados.add(periodo);
			tanque.add(chckbxNewCheckBox.isSelected());
			seguro.add(chckbxNewCheckBox_1.isSelected());
		} else if (pagavel instanceof CamaExtra) {
			camasAlugadas.add((CamaExtra) pagavel);
			pcamasAlugadas.add(periodo);
			Quarto quartosel = (Quarto) list_1.getSelectedValue();

			if (quartosel == null) {
				ErrorLabel.setText("Nenhum quarto foi selecionado.");
				ErrorLabel.setVisible(true);
				return;
			}

		} else {
			int inicio = Integer.parseInt(comboBox_2.getSelectedItem()
					.toString());
			int saida = Integer.parseInt(comboBox_3.getSelectedItem()
					.toString());

			if (periodo.getInicio().get(Calendar.DAY_OF_MONTH) != periodo
					.getFim().get(Calendar.DAY_OF_MONTH)
					|| periodo.getInicio().get(Calendar.MONTH) != periodo
							.getFim().get(Calendar.MONTH)
					|| periodo.getInicio().get(Calendar.YEAR) != periodo
							.getFim().get(Calendar.YEAR)) {
				ErrorLabel
						.setText("Babá deve ser alugada apenas um dia a cada vez.");
				ErrorLabel.setVisible(true);
				return;
			} else if (inicio == saida) {
				ErrorLabel
						.setText("Hora de início e de saída não podem ser iguais.");
				ErrorLabel.setVisible(true);
				return;
			} else if (inicio > saida) {
				ErrorLabel
						.setText("Hora de início deve ser menor que a de saída.");
				ErrorLabel.setVisible(true);
				return;
			} else if (saida - inicio > 8) {
				ErrorLabel
						.setText("Mesma babá não pode ser contratada por mais de 8 horas no mesmo dia.");
				ErrorLabel.setVisible(true);
				return;
			}

			periodo.getInicio().set(Calendar.HOUR_OF_DAY, inicio);
			periodo.getFim().set(Calendar.HOUR_OF_DAY, saida);

			babasAlugadas.add((Babysitter) pagavel);
			pbabasAlugadas.add(periodo);
		}

		lista1.addElement(pagavel);
		list.setModel(lista1);
		checaDisponibilidade();
		ErrorLabel.setVisible(false);
	}

	private void removerServico() {
		if (list.getSelectedValue() != null) {
			if (list.getSelectedValue() instanceof Carro) {
				int index = carrosAlugados.indexOf(list.getSelectedValue());
				carrosAlugados.remove(list.getSelectedValue());
				pcarrosAlugados.remove(index);
				tanque.remove(index);
				seguro.remove(index);
			} else if (list.getSelectedValue() instanceof CamaExtra) {
				int index = camasAlugadas.indexOf(list.getSelectedValue());
				camasAlugadas.remove(list.getSelectedValue());
				pcamasAlugadas.remove(index);
			} else {
				int index = babasAlugadas.indexOf(list.getSelectedValue());
				babasAlugadas.remove(list.getSelectedValue());
				pbabasAlugadas.remove(index);
			}

			lista1.removeElement(list.getSelectedValue());
			checaDisponibilidade();
			ErrorLabel.setVisible(false);
		} else {
			ErrorLabel.setText("Nenhum serviço foi escolhido.");
			ErrorLabel.setVisible(true);
			return;
		}
	}

	private void concluir() {
		if (lista1.isEmpty()) {
			ErrorLabel.setText("Nenhum serviço foi escolhido.");
			ErrorLabel.setVisible(true);
			return;
		}

		for (Babysitter b : babasAlugadas) {
			int index = babasAlugadas.indexOf(b);
			Periodo periodo = pbabasAlugadas.get(index);
			b.aluga(periodo);
			contrato.adicionaServico(b);
		}

		for (int i = 0; i < carrosAlugados.size(); i++) {
			Periodo periodo = pbabasAlugadas.get(i);
			carrosAlugados.get(i).aluga(periodo, tanque.get(i), seguro.get(i));
			contrato.adicionaServico(carrosAlugados.get(i));
		}

		for (CamaExtra c : camasAlugadas) {
			int index = camasAlugadas.indexOf(c);
			Periodo periodo = pcamasAlugadas.get(index);
			c.aluga(periodo);
			contrato.adicionaServico(c);
		}

		Sistema.setTela(new Menu());
		ErrorLabel.setVisible(false);
	}

	private void checaDisponibilidade() {
		boolean disponivel = true;
		Periodo periodo = calendario.getSelecao();
		if (periodo != null) {
			String servico = (String) comboBox_1.getSelectedItem();

			if (servico.equals("Babysitter")) {
				for (Babysitter baba : Sistema.getHotel().getBabas()) {
					disponivel = true;

					for (Periodo p : baba.getHistorico()) {
						if (p.entraEmConflito(periodo)) {
							disponivel = false;
							break;
						}
					}
					
					for (int i = 0; i < babasAlugadas.size(); i++) {
						if (babasAlugadas.get(i).equals(baba) && pbabasAlugadas.get(i).entraEmConflito(periodo)) {
							disponivel = false;
							break;
						}
					}

					if (disponivel) {
						pagavel = baba;
						break;
					}
				}
			}

			else if (servico.equals("Carro")) {
				if (Sistema.getHotel().getCarrosDisponiveis(
						periodo).size() == 0)
					disponivel = false;
				
				for (Carro carro : Sistema.getHotel().getCarrosDisponiveis(
						periodo)) {
					disponivel = true;

					if (!(carro.getTipo().equals(comboBox.getSelectedItem()))) {
						disponivel = false;
						break;
					}
					
					for (int i = 0; i < carrosAlugados.size(); i++) {
						if (carrosAlugados.get(i).equals(carro) && pcarrosAlugados.get(i).entraEmConflito(periodo)) {
							disponivel = false;
							break;
						}
					}

					if (disponivel) {
						pagavel = carro;
						break;
					}

				}
			}

			else {
				if (Sistema.getHotel().getCamasDisponiveis(
						periodo).size() == 0)
					disponivel = false;
				
				for (CamaExtra cama : Sistema.getHotel().getCamasDisponiveis(
						periodo)) {
					disponivel = true;
					
					for (int i = 0; i < camasAlugadas.size(); i++) {
						if (camasAlugadas.get(i).equals(cama) && pcamasAlugadas.get(i).entraEmConflito(periodo)) {
							disponivel = false;
							break;
						}
					}
					
					if (disponivel) {
					pagavel = cama;
					break;
					}

				}
			}

			if (disponivel)
				textPane.setBackground(Color.GREEN);
			else {
				pagavel = null;
				textPane.setBackground(Color.RED);
			}
		}

		else {
			pagavel = null;
			textPane.setBackground(Color.RED);
		}
	}
}
