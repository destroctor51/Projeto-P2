package gui.contratos;

import gui.Sistema;
import gui.components.CalendarioAlugavel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

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

	private static final long serialVersionUID = 1L;

	private static final String[] horas = new String[] {
		"00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
		"10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
		"20", "21", "22", "23" };

	private Contrato contrato;

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
	private JList<Pagavel> list;
	private CalendarioAlugavel calendario;
	private JComboBox<String> comboBox;
	private JPanel panel_3;
	private DefaultListModel<Pagavel> lista1 = new DefaultListModel<>();

	private JComboBox<TipoCarro> comboBox_3;
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox chckbxNewCheckBox_1;

	private JList<Quarto> list_1;

	private JComboBox<String> comboBox_1;
	private JComboBox<String> comboBox_2;

	public SelecionarServicos(Contrato contrato, final JPanel tela) {

		this.setName("Selecionar Servi\u00E7os");

		this.contrato = contrato;

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 400, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 120, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.25, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.25, 1.0};
		setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 10, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JLabel lblPeriodoDesejado = new JLabel("Per\u00EDodo desejado :");
		GridBagConstraints gbc_lblPeriodoDesejado = new GridBagConstraints();
		gbc_lblPeriodoDesejado.anchor = GridBagConstraints.WEST;
		gbc_lblPeriodoDesejado.insets = new Insets(0, 0, 5, 10);
		gbc_lblPeriodoDesejado.gridx = 0;
		gbc_lblPeriodoDesejado.gridy = 0;
		panel.add(lblPeriodoDesejado, gbc_lblPeriodoDesejado);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);

		JLabel lblServioDesejado = new JLabel("Servi\u00E7o desejado :");
		GridBagConstraints gbc_lblServioDesejado = new GridBagConstraints();
		gbc_lblServioDesejado.insets = new Insets(0, 0, 0, 10);
		gbc_lblServioDesejado.gridx = 0;
		gbc_lblServioDesejado.gridy = 0;
		panel_1.add(lblServioDesejado, gbc_lblServioDesejado);

		comboBox = new JComboBox<String>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		panel_1.add(comboBox, gbc_comboBox);
		comboBox.setFocusable(false);
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				atualizaCalendar();
			}
		});
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Babysitter", "Carro", "CamaExtra" }));

		calendario = new CalendarioAlugavel();
		GridBagConstraints gbc_calendario = new GridBagConstraints();
		gbc_calendario.insets = new Insets(0, 0, 0, 10);
		gbc_calendario.gridx = 0;
		gbc_calendario.gridy = 1;
		panel.add(calendario, gbc_calendario);

		calendario.initCategoria("babysitter", 1);
		calendario.initCategoria("carroe", 0);
		calendario.initCategoria("carrol", 0);
		calendario.initCategoria("cama", 0);
		calendario.setMultiplos(false);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 1;
		panel.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);

		panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.insets = new Insets(0, 0, 10, 0);
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 0;
		panel_2.add(panel_3, gbc_panel_3);
		panel_3.setLayout(new CardLayout(0, 0));

		JPanel babysitterCard = new JPanel();
		panel_3.add(babysitterCard, "babysitter");
		GridBagLayout gbl_babysitterCard = new GridBagLayout();
		gbl_babysitterCard.columnWidths = new int[] {0, 50};
		gbl_babysitterCard.rowHeights = new int[] {0, 0};
		gbl_babysitterCard.columnWeights = new double[]{0.0, 0.0};
		gbl_babysitterCard.rowWeights = new double[]{0.0, 0.0};
		babysitterCard.setLayout(gbl_babysitterCard);

		JLabel lblHoraDeIncio = new JLabel("Hora de in\u00EDcio:");
		GridBagConstraints gbc_lblHoraDeIncio = new GridBagConstraints();
		gbc_lblHoraDeIncio.insets = new Insets(0, 0, 10, 10);
		gbc_lblHoraDeIncio.gridx = 0;
		gbc_lblHoraDeIncio.gridy = 0;
		babysitterCard.add(lblHoraDeIncio, gbc_lblHoraDeIncio);

		comboBox_1 = new JComboBox<>();
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.insets = new Insets(0, 0, 10, 0);
		gbc_comboBox_1.gridx = 1;
		gbc_comboBox_1.gridy = 0;
		babysitterCard.add(comboBox_1, gbc_comboBox_1);
		comboBox_1.setFocusable(false);
		comboBox_1.setModel(new DefaultComboBoxModel<>(horas));

		JLabel lblHoraDeSada = new JLabel("Hora de sa\u00EDda:");
		GridBagConstraints gbc_lblHoraDeSada = new GridBagConstraints();
		gbc_lblHoraDeSada.insets = new Insets(0, 0, 0, 10);
		gbc_lblHoraDeSada.gridx = 0;
		gbc_lblHoraDeSada.gridy = 1;
		babysitterCard.add(lblHoraDeSada, gbc_lblHoraDeSada);

		comboBox_2 = new JComboBox<>();
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.gridx = 1;
		gbc_comboBox_2.gridy = 1;
		babysitterCard.add(comboBox_2, gbc_comboBox_2);
		comboBox_2.setFocusable(false);
		comboBox_2.setModel(new DefaultComboBoxModel<>(horas));

		JPanel carroCard = new JPanel();
		panel_3.add(carroCard, "carro");
		GridBagLayout gbl_carroCard = new GridBagLayout();
		gbl_carroCard.columnWidths = new int[] {0, 0};
		gbl_carroCard.rowHeights = new int[] {0, 0, 0};
		gbl_carroCard.columnWeights = new double[]{0.0, 0.0};
		gbl_carroCard.rowWeights = new double[]{0.0, 0.0, 0.0};
		carroCard.setLayout(gbl_carroCard);

		JLabel lblTipoDeCarro = new JLabel("Especifica\u00E7\u00F5es:");
		GridBagConstraints gbc_lblTipoDeCarro = new GridBagConstraints();
		gbc_lblTipoDeCarro.anchor = GridBagConstraints.WEST;
		gbc_lblTipoDeCarro.insets = new Insets(0, 0, 5, 10);
		gbc_lblTipoDeCarro.gridx = 0;
		gbc_lblTipoDeCarro.gridy = 0;
		carroCard.add(lblTipoDeCarro, gbc_lblTipoDeCarro);

		comboBox_3 = new JComboBox<TipoCarro>();
		GridBagConstraints gbc_comboBox_3 = new GridBagConstraints();
		gbc_comboBox_3.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_3.gridx = 1;
		gbc_comboBox_3.gridy = 0;
		carroCard.add(comboBox_3, gbc_comboBox_3);
		comboBox_3.setFocusable(false);
		comboBox_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox_3.getSelectedItem().equals(TipoCarro.EXECUTIVO)) {
					calendario.setRequisito("carroe", 1);
					calendario.setRequisito("carrol", 0);
				}
				else {
					calendario.setRequisito("carrol", 1);
					calendario.setRequisito("carroe", 0);
				}
			}
		});
		comboBox_3.setModel(new DefaultComboBoxModel<>(TipoCarro.values()));

		chckbxNewCheckBox = new JCheckBox("Tanque cheio");
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.anchor = GridBagConstraints.WEST;
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxNewCheckBox.gridx = 1;
		gbc_chckbxNewCheckBox.gridy = 1;
		carroCard.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
		chckbxNewCheckBox.setFocusable(false);

		chckbxNewCheckBox_1 = new JCheckBox("Seguro");
		GridBagConstraints gbc_chckbxNewCheckBox_1 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_1.anchor = GridBagConstraints.WEST;
		gbc_chckbxNewCheckBox_1.gridx = 1;
		gbc_chckbxNewCheckBox_1.gridy = 2;
		carroCard.add(chckbxNewCheckBox_1, gbc_chckbxNewCheckBox_1);
		chckbxNewCheckBox_1.setFocusable(false);

		JPanel camaExtraCard = new JPanel();
		panel_3.add(camaExtraCard, "camaExtra");
		GridBagLayout gbl_camaExtraCard = new GridBagLayout();
		gbl_camaExtraCard.columnWidths = new int[] {0};
		gbl_camaExtraCard.rowHeights = new int[] {0};
		gbl_camaExtraCard.columnWeights = new double[]{1.0};
		gbl_camaExtraCard.rowWeights = new double[]{1.0};
		camaExtraCard.setLayout(gbl_camaExtraCard);

		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 0;
		camaExtraCard.add(scrollPane_1, gbc_scrollPane_1);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		list_1 = new JList<>();
		list_1.setFocusable(false);
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(list_1);

		DefaultListModel<Quarto> lista2 = new DefaultListModel<>();
		list_1.setModel(lista2);

		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 1;
		panel_2.add(panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[] {0, 0};
		gbl_panel_4.rowHeights = new int[] {0};
		gbl_panel_4.columnWeights = new double[]{1.0, 0.0};
		gbl_panel_4.rowWeights = new double[]{0.0};
		panel_4.setLayout(gbl_panel_4);

		JButton btnRemoverServio = new JButton("Remover servi\u00E7o");
		GridBagConstraints gbc_btnRemoverServio = new GridBagConstraints();
		gbc_btnRemoverServio.anchor = GridBagConstraints.EAST;
		gbc_btnRemoverServio.insets = new Insets(0, 0, 0, 5);
		gbc_btnRemoverServio.gridx = 0;
		gbc_btnRemoverServio.gridy = 0;
		panel_4.add(btnRemoverServio, gbc_btnRemoverServio);
		btnRemoverServio.setFocusable(false);
		btnRemoverServio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removerServico();
			}
		});

		JButton btnSelecionarServio = new JButton("Selecionar servi\u00E7o");
		GridBagConstraints gbc_btnSelecionarServio = new GridBagConstraints();
		gbc_btnSelecionarServio.gridx = 1;
		gbc_btnSelecionarServio.gridy = 0;
		panel_4.add(btnSelecionarServio, gbc_btnSelecionarServio);
		btnSelecionarServio.setFocusable(false);
		btnSelecionarServio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				selecionarServico();
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		list = new JList<>();
		list.setVisibleRowCount(-1);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFocusable(false);
		scrollPane.setViewportView(list);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 10, 5);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);

		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.anchor = GridBagConstraints.NORTH;
		gbc_panel_5.insets = new Insets(0, 0, 0, 5);
		gbc_panel_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_5.gridx = 1;
		gbc_panel_5.gridy = 3;
		add(panel_5, gbc_panel_5);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[] {200, 0, 0};
		gbl_panel_5.rowHeights = new int[] {0};
		gbl_panel_5.columnWeights = new double[]{0.0, 1.0, 0.0};
		gbl_panel_5.rowWeights = new double[]{0.0};
		panel_5.setLayout(gbl_panel_5);

		ErrorLabel = new JLabel("<erro>");
		GridBagConstraints gbc_ErrorLabel = new GridBagConstraints();
		gbc_ErrorLabel.anchor = GridBagConstraints.WEST;
		gbc_ErrorLabel.insets = new Insets(0, 0, 0, 5);
		gbc_ErrorLabel.gridx = 0;
		gbc_ErrorLabel.gridy = 0;
		panel_5.add(ErrorLabel, gbc_ErrorLabel);
		ErrorLabel.setForeground(Color.RED);
		ErrorLabel.setVisible(false);
		ErrorLabel.setIcon(new ImageIcon(SelecionarServicos.class
				.getResource("/gui/resources/error.png")));

		JButton btnCancelar = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.anchor = GridBagConstraints.EAST;
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 10);
		gbc_btnCancelar.gridx = 1;
		gbc_btnCancelar.gridy = 0;
		panel_5.add(btnCancelar, gbc_btnCancelar);
		btnCancelar.setFocusable(false);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(tela);
			}
		});

		JButton btnConfirmar = new JButton("Confirmar");
		GridBagConstraints gbc_btnConfirmar = new GridBagConstraints();
		gbc_btnConfirmar.gridx = 2;
		gbc_btnConfirmar.gridy = 0;
		panel_5.add(btnConfirmar, gbc_btnConfirmar);
		btnConfirmar.setFocusable(false);
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				concluir(tela);
			}
		});

		for (Pagavel servico : contrato.getServicos()) {
			if(servico instanceof Quarto) {
				Quarto q = (Quarto) servico;
				if (q.getTipo() != TipoQuarto.EXECUTIVO_TRIPLO
						&& q.getTipo() != TipoQuarto.PRESIDENCIAL)
					lista2.addElement(q);
			}
		}

		for (Babysitter b : Sistema.getHotel().getBabas()) {
			Alugavel a = (Alugavel) b.clone();

			for (Periodo p : b.getHistorico())
				a.aluga(p);

			calendario.adicionaElemento("babysitter", a);
			clones.add(a);
		}

		for (Carro c : Sistema.getHotel().getCarros()) {
			Carro a = (Carro) c.clone();
			clones.add(a);

			for (Periodo p : c.getHistorico()) {
				a.aluga(p);
				a.cancela();
			}

			if(c.getTipo() == TipoCarro.EXECUTIVO)
				calendario.adicionaElemento("carroe", a);
			else calendario.adicionaElemento("carrol", a);
		}

		for (CamaExtra ce : Sistema.getHotel().getCamas()) {
			Alugavel a = (Alugavel) ce.clone();

			for (Periodo p : ce.getHistorico())
				a.aluga(p);

			clones.add(a);
			calendario.adicionaElemento("cama", a);
		}
	}

	private void selecionarServico() {
		Periodo periodo = calendario.getSelecao();
		Pagavel pagavel = null;

		if (periodo == null) {
			ErrorLabel.setText("Per\u00EDodo ainda n\u00E3o escolhido");
			ErrorLabel.setVisible(true);
			return;
		}

		if (comboBox.getSelectedItem().equals("Carro")) {
			boolean disp;
			for (Carro c : Sistema.getHotel().getCarrosDisponiveis(periodo)) {
				disp = true;
				if ((comboBox_3.getSelectedItem().equals(TipoCarro.EXECUTIVO) &&
						c.getTipo().equals(TipoCarro.EXECUTIVO)) ||
						(comboBox_3.getSelectedItem().equals(TipoCarro.LUXO) &&
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
		else if (comboBox.getSelectedItem().equals("CamaExtra")) {
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
			Quarto quartosel = list_1.getSelectedValue();

			if (quartosel == null) {
				ErrorLabel.setText("Nenhum quarto foi selecionado");
				ErrorLabel.setVisible(true);
				return;
			}

		} else {
			int inicio = Integer.parseInt(comboBox_1.getSelectedItem()
					.toString());
			int saida = Integer.parseInt(comboBox_2.getSelectedItem()
					.toString());

			if (inicio == saida) {
				ErrorLabel
				.setText("Hora de in\u00EDcio e de sa\u00EDda n\u00E3o podem ser iguais");
				ErrorLabel.setVisible(true);
				return;
			} else if (inicio > saida) {
				ErrorLabel
				.setText("Hora de in\u00EDcio deve ser menor que a de sa\u00EDda");
				ErrorLabel.setVisible(true);
				return;
			} else if (saida - inicio > 8) {
				ErrorLabel
				.setText("Mesma bab\u00E1 n\u00E3o pode ser contratada por mais de 8 horas no mesmo dia");
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
					atualizaCalendar();
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
				atualizaCalendar();
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
				atualizaCalendar();
				for (Alugavel a : clones) {
					if (a instanceof Babysitter)
						calendario.adicionaElemento("babysitter", a);
				}

			}

			lista1.removeElement(list.getSelectedValue());
			ErrorLabel.setVisible(false);
		} else {
			ErrorLabel.setText("Nenhum servi\u00E7o foi escolhido");
			ErrorLabel.setVisible(true);
			return;
		}

		calendario.atualizaDias();
	}

	private void concluir(JPanel tela) {
		if (lista1.isEmpty()) {
			ErrorLabel.setText("Nenhum servi\u00E7o foi escolhido");
			ErrorLabel.setVisible(true);
			return;
		}

		for (int i = 0; i < babasAlugadas.size(); i++) {
			Periodo periodo = pbabasAlugadas.get(i);
			babasAlugadas.get(i).aluga(periodo);
			contrato.adicionaServico(babasAlugadas.get(i));
		}

		for (int i = 0; i < carrosAlugados.size(); i++) {
			Periodo periodo = pcarrosAlugados.get(i);
			carrosAlugados.get(i).aluga(periodo, tanque.get(i), seguro.get(i));
			contrato.adicionaServico(carrosAlugados.get(i));
			carrosAlugados.get(i).cancela();
		}

		for (int i = 0; i < camasAlugadas.size(); i++) {
			Periodo periodo = pcamasAlugadas.get(i);
			camasAlugadas.get(i).aluga(periodo);
			contrato.adicionaServico(camasAlugadas.get(i));
		}

		Sistema.setTela(tela);
		ErrorLabel.setVisible(false);
	}

	private void atualizaCalendar() {
		if(comboBox.getSelectedItem().equals("Carro")) {
			((CardLayout) panel_3.getLayout()).show(panel_3, "carro");

			calendario.setRequisito("babysitter", 0);
			calendario.setRequisito("cama", 0);
			calendario.setMultiplos(true);

			if (comboBox_3.getSelectedItem() == TipoCarro.EXECUTIVO) {
				calendario.setRequisito("carroe", 1);
				calendario.setRequisito("carrol", 0);
			}

			else {
				calendario.setRequisito("carrol", 1);
				calendario.setRequisito("carroe", 0);
			}
		}

		else if(comboBox.getSelectedItem().equals("CamaExtra")) {
			((CardLayout) panel_3.getLayout()).show(panel_3, "camaExtra");

			calendario.setRequisito("babysitter", 0);
			calendario.setRequisito("carroe", 0);
			calendario.setRequisito("carrol", 0);
			calendario.setRequisito("cama", 1);
			calendario.setMultiplos(true);
		}

		else {
			((CardLayout) panel_3.getLayout()).show(panel_3, "babysitter");

			calendario.setRequisito("babysitter", 1);
			calendario.setRequisito("carroe", 0);
			calendario.setRequisito("carrol", 0);
			calendario.setRequisito("cama", 0);
		}
	}
}