package gui.contratos;

import gui.Sistema;
import gui.components.CalendarioAlugavel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import core.hotel.Contrato;
import core.interfaces.Alugavel;
import core.interfaces.Pagavel;
import core.servicos.alugaveis.Babysitter;
import core.servicos.alugaveis.CamaExtra;
import core.servicos.devolviveis.Carro;
import core.servicos.devolviveis.Quarto;
import core.servicos.devolviveis.TipoCarro;
import core.servicos.devolviveis.TipoQuarto;
import core.tempo.Periodo;

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
	private List<Alugavel> clones = new ArrayList<>();
	private JLabel ErrorLabel;
	private JList<Object> list;
	private CalendarioAlugavel calendario;
	private JComboBox<Object> comboBox_1;
	private JComboBox<Object> comboBox;
	private JLabel lblTipoDeCarro;
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
	public SelecionarServicos(Contrato contrato, final JPanel tela) {
		
		this.setName("Selecionar Servicos");
		
		setForeground(Color.BLACK);
		this.contrato = contrato;

		JPanel panel = new JPanel();

		ErrorLabel = new JLabel("");
		ErrorLabel.setForeground(Color.RED);
		ErrorLabel.setVisible(false);
		ErrorLabel.setIcon(new ImageIcon(SelecionarServicos.class
				.getResource("/gui/images/error.png")));

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				concluir(tela);
			}
		});

		JButton btnCancelar = new JButton("Voltar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(tela);
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
					calendario.setRequisito("babysitter", 0);
					calendario.setRequisito("cama", 0);
					calendario.setMultiplos(true);
					
					if (comboBox.getSelectedItem().equals(TipoCarro.EXECUTIVO)) {
						calendario.setRequisito("carroe", 1);
						calendario.setRequisito("carrol", 0);
					}
					else {
						calendario.setRequisito("carrol", 1);
						calendario.setRequisito("carroe", 0);
					}

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
					calendario.setRequisito("babysitter", 0);
					calendario.setRequisito("carroe", 0);
					calendario.setRequisito("carrol", 0);
					calendario.setRequisito("cama", 1);
					calendario.setMultiplos(true);

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
					calendario.setRequisito("babysitter", 1);
					calendario.setRequisito("carroe", 0);
					calendario.setRequisito("carrol", 0);
					calendario.setRequisito("cama", 0);
					calendario.setMultiplos(false);

					layeredPane.setLayer(scrollPane_1, -1);
					layeredPane.setLayer(list_1, -1);
					scrollPane_1.setVisible(false);
					list_1.setVisible(false);

					lblTipoDeCarro.setVisible(false);
					comboBox.setVisible(false);
					chckbxNewCheckBox.setVisible(false);
					chckbxNewCheckBox_1.setVisible(false);
				}
			}
		});
		comboBox_1.setModel(new DefaultComboBoxModel<Object>(new String[] {
				"Babysitter", "Carro", "CamaExtra" }));

		JLabel lblServioDesejado = new JLabel("Serviço desejado :");

		calendario = new CalendarioAlugavel();

		JLabel lblPeriodoDesejado = new JLabel("Periodo desejado :");

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

		layeredPane = new JLayeredPane();

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblPeriodoDesejado)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(calendario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
									.addComponent(btnRemoverServio)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnSelecionarServio)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGap(9))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblServioDesejado)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(ErrorLabel, GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCancelar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnConfirmar)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblServioDesejado)
								.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(93)
									.addComponent(lblPeriodoDesejado))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(12)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
											.addComponent(btnSelecionarServio)
											.addComponent(btnRemoverServio))
										.addComponent(calendario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))))
					.addGap(19)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnConfirmar)
							.addComponent(btnCancelar))
						.addComponent(ErrorLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);

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
				if (comboBox.getSelectedItem().equals(TipoCarro.EXECUTIVO)) {
					calendario.setRequisito("carroe", 1);
					calendario.setRequisito("carrol", 0);
				}
				else {
					calendario.setRequisito("carrol", 1);
					calendario.setRequisito("carroe", 0);
				}
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
		
		calendario.initCategoria("babysitter", 1);
		for (Babysitter b : Sistema.getHotel().getBabas()) {
			Alugavel a = (Alugavel) b.clone();
			clones.add(a);
			calendario.adicionaElemento("babysitter", a);
		}
		calendario.initCategoria("carroe", 0);
		calendario.initCategoria("carrol", 0);
		for (Carro c : Sistema.getHotel().getCarros()) {
			switch (c.getTipo()) {
			case EXECUTIVO:
				Alugavel a = (Alugavel) c.clone();
				clones.add(a);
				calendario.adicionaElemento("carroe", a);
				break;
			case LUXO:
				Alugavel a1 = (Alugavel) c.clone();
				clones.add(a1);
				calendario.adicionaElemento("carrol", a1);
				break;
			}
		}
		calendario.initCategoria("cama", 0);
		for (CamaExtra ce : Sistema.getHotel().getCamas()) {
			Alugavel a = (Alugavel) ce.clone();
			clones.add(a);
			calendario.adicionaElemento("cama", a);
		}
		calendario.setMultiplos(false);

	}

	private void selecionarServico() {
		Periodo periodo = calendario.getSelecao();

		if (periodo == null) {
			ErrorLabel.setText("Periodo ainda não escolhido.");
			ErrorLabel.setVisible(true);
			return;
		}

		if (comboBox_1.getSelectedItem().equals("Carro")) {
			boolean disp;
			for (Carro c : Sistema.getHotel().getCarrosDisponiveis(periodo)) {
				disp = true;
				if ((comboBox.getSelectedItem().equals(TipoCarro.EXECUTIVO) &&
					c.getTipo().equals(TipoCarro.EXECUTIVO)) || 
					(comboBox.getSelectedItem().equals(TipoCarro.LUXO) &&
					c.getTipo().equals(TipoCarro.LUXO))) {
					
					for (int i = 0; i < carrosAlugados.size(); i++) {
 						if (carrosAlugados.get(i).equals(c) && pcarrosAlugados.get(i).entraEmConflito(periodo)) {
 							disp = false;
 							break;
 						}
 					}
 
 					if (disp) {
 						pagavel = c;
 						break;
 					}
				}
			}
		}
		else if (comboBox_1.getSelectedItem().equals("CamaExtra")) {
			boolean disp;
			for (CamaExtra cama : Sistema.getHotel().getCamasDisponiveis(
						periodo)) {
				disp = true;
					
				for (int i = 0; i < camasAlugadas.size(); i++) {
					if (camasAlugadas.get(i).equals(cama) && pcamasAlugadas.get(i).entraEmConflito(periodo)) {
						disp = false;
						break;
					}
				}
					
				if (disp) {
					pagavel = cama;
					break;
				}

			}
		}
		else {
			boolean disp;
			for (Babysitter b : Sistema.getHotel().getBabas()) {
				disp = true;
				for (Periodo p : b.getHistorico()) {
					if (p.entraEmConflito(periodo))
						disp = false;
						break;
				}
				
				for (int i = 0; i < babasAlugadas.size(); i++) {
					if (babasAlugadas.get(i).equals(b) && pbabasAlugadas.get(i).entraEmConflito(periodo)) {
						disp = false;
						break;
					}
				}

				if (disp) {
					pagavel = b;
					break;
				}
			}
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

			if (inicio == saida) {
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

			periodo.getFim().set(Calendar.DAY_OF_MONTH, periodo.getFim().get(Calendar.DAY_OF_MONTH) - 1);
			periodo.getInicio().set(Calendar.HOUR_OF_DAY, inicio);
			periodo.getFim().set(Calendar.HOUR_OF_DAY, saida);
			
			babasAlugadas.add((Babysitter) pagavel);
			pbabasAlugadas.add(periodo);
		}
		
		for (Alugavel a : clones) {
			if (a.equals(pagavel)) {
				a.aluga(periodo);
				if (a instanceof Carro)
					((Carro) a).cancela();
				break;
			}
		}

		lista1.addElement(pagavel);
		list.setModel(lista1);
		ErrorLabel.setVisible(false);
		
		calendario.atualizaDias();
	}

	private void removerServico() {
		if (list.getSelectedValue() != null) {
			if (list.getSelectedValue() instanceof Carro) {
				int index = carrosAlugados.indexOf(list.getSelectedValue());
				carrosAlugados.remove(list.getSelectedValue());
				pcarrosAlugados.remove(index);
				tanque.remove(index);
				seguro.remove(index);
				
				Alugavel remov = null;
				for (Alugavel a : clones) {
					if (a.equals(list.getSelectedValue())) {
						remov = a;
						break;
					}
				}
				
				clones.remove(remov);
				Alugavel clone = (Alugavel) ((Alugavel) list.getSelectedValue()).clone();
				for (int i = 0; i < carrosAlugados.size(); i++) {
					if (carrosAlugados.get(i).equals(clone)) {
						clone.aluga(pcarrosAlugados.get(i));
						((Carro) clone).cancela();
					}
				}
				clones.add(clone);
				
				if (((Carro) clone).getTipo().equals(TipoCarro.EXECUTIVO)) {						
					calendario.removeCategoria("carroe");
					calendario.initCategoria("carroe", 1);
					for (Alugavel a : clones) {
						if (a instanceof Carro && ((Carro) a).getTipo().equals(TipoCarro.EXECUTIVO))
							calendario.adicionaElemento("carroe", a);
					}
				}
				else {
					calendario.removeCategoria("carrol");
					calendario.initCategoria("carrol", 1);
					for (Alugavel a : clones) {
						if (a instanceof Carro && ((Carro) a).getTipo().equals(TipoCarro.LUXO))
							calendario.adicionaElemento("carrol", a);
					}
				}
				
			} else if (list.getSelectedValue() instanceof CamaExtra) {
				int index = camasAlugadas.indexOf(list.getSelectedValue());
				camasAlugadas.remove(list.getSelectedValue());
				pcamasAlugadas.remove(index);
				
				Alugavel remov = null;
				for (Alugavel a : clones) {
					if (a.equals(list.getSelectedValue())) {
						remov = a;
						break;
					}
				}
				
				clones.remove(remov);
				Alugavel clone = (Alugavel) ((Alugavel) list.getSelectedValue()).clone();
				for (int i = 0; i < camasAlugadas.size(); i++) {
					if (camasAlugadas.get(i).equals(clone)) {
						clone.aluga(pcamasAlugadas.get(i));
					}
				}
				clones.add(clone);
				
				calendario.removeCategoria("cama");
				calendario.initCategoria("cama", 1);
				for (Alugavel a : clones) {
					if (a instanceof CamaExtra)
						calendario.adicionaElemento("cama", a);
				}
				
			} else {
				int index = babasAlugadas.indexOf(list.getSelectedValue());
				babasAlugadas.remove(list.getSelectedValue());
				pbabasAlugadas.remove(index);
				
				Alugavel remov = null;
				for (Alugavel a : clones) {
					if (a.equals(list.getSelectedValue())) {
						remov = a;
						break;
					}
				}
				
				clones.remove(remov);
				Alugavel clone = (Alugavel) ((Alugavel) list.getSelectedValue()).clone();
				for (int i = 0; i < babasAlugadas.size(); i++) {
					if (babasAlugadas.get(i).equals(clone)) {
						clone.aluga(pbabasAlugadas.get(i));
					}
				}
				clones.add(clone);
				
				calendario.removeCategoria("babysitter");
				calendario.initCategoria("babysitter", 1);
				for (Alugavel a : clones) {
					if (a instanceof Babysitter)
						calendario.adicionaElemento("babysitter", a);
				}
				
			}

			lista1.removeElement(list.getSelectedValue());
			ErrorLabel.setVisible(false);
		} else {
			ErrorLabel.setText("Nenhum serviço foi escolhido.");
			ErrorLabel.setVisible(true);
			return;
		}
		
		calendario.atualizaDias();
	}

	private void concluir(JPanel tela) {
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
			Periodo periodo = pcarrosAlugados.get(i);
			carrosAlugados.get(i).aluga(periodo, tanque.get(i), seguro.get(i));
			contrato.adicionaServico(carrosAlugados.get(i));
			carrosAlugados.get(i).cancela();
		}

		for (CamaExtra c : camasAlugadas) {
			int index = camasAlugadas.indexOf(c);
			Periodo periodo = pcamasAlugadas.get(index);
			c.aluga(periodo);
			contrato.adicionaServico(c);
		}

		Sistema.setTela(tela);
		ErrorLabel.setVisible(false);
	}
}