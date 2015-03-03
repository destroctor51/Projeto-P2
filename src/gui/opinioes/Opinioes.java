package gui.opinioes;

import gui.Menu;
import gui.Sistema;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import utils.Filtro;
import core.hotel.Opiniao;

public class Opinioes extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox<Object> cbMes = new JComboBox<>();
	private JComboBox<Object> cbAno = new JComboBox<>();
	final JList<Opiniao> list = new JList<>();
	private JPanel tela = this;

	private Calendar dataAtual = GregorianCalendar.getInstance();

	/**
	 * Create the panel.
	 */
	public Opinioes() {

		addAncestorListener(new AncestorListener() {
			@Override
			public void ancestorAdded(AncestorEvent arg0) {
				atualizaJList();
			}
			@Override
			public void ancestorMoved(AncestorEvent arg0) {}
			@Override
			public void ancestorRemoved(AncestorEvent arg0) {}
		});
		setName("Opini\u00F5es");
		setMinimumSize(new Dimension(0, 0));

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[] {0, 123, 0, 0, 0};
		gridBagLayout.columnWidths = new int[] {0, 400, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.5, 1.0, 1.0, 0.0, 1.0};
		setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.SOUTH;
		gbc_panel.insets = new Insets(0, 0, 10, 5);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{113, 0, 115, 0};
		gbl_panel.rowHeights = new int[]{0, 23, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JLabel lblAno = new JLabel("Ano:");
		GridBagConstraints gbc_lblAno = new GridBagConstraints();
		gbc_lblAno.anchor = GridBagConstraints.WEST;
		gbc_lblAno.insets = new Insets(0, 0, 5, 5);
		gbc_lblAno.gridx = 0;
		gbc_lblAno.gridy = 0;
		panel.add(lblAno, gbc_lblAno);

		JLabel lblMs = new JLabel("M\u00EAs:");
		GridBagConstraints gbc_lblMs = new GridBagConstraints();
		gbc_lblMs.anchor = GridBagConstraints.WEST;
		gbc_lblMs.insets = new Insets(0, 0, 5, 0);
		gbc_lblMs.gridx = 2;
		gbc_lblMs.gridy = 0;
		panel.add(lblMs, gbc_lblMs);


		GridBagConstraints gbc_cbAno = new GridBagConstraints();
		gbc_cbAno.insets = new Insets(0, 0, 0, 5);
		gbc_cbAno.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbAno.gridx = 0;
		gbc_cbAno.gridy = 1;
		cbAno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				atualizaJList();
			}
		});
		panel.add(cbAno, gbc_cbAno);

		DefaultComboBoxModel<Object> ano = new DefaultComboBoxModel<>();
		for (int i = 2000; i <= dataAtual.get(Calendar.YEAR); i++)
			ano.addElement(i);

		cbAno.setModel(ano);
		cbAno.setSelectedItem(dataAtual.get(Calendar.YEAR));

		GridBagConstraints gbc_cbMes = new GridBagConstraints();
		gbc_cbMes.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbMes.gridx = 2;
		gbc_cbMes.gridy = 1;
		cbMes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				atualizaJList();
			}
		});
		panel.add(cbMes, gbc_cbMes);

		String[] e = {"Janeiro","Fevereiro","Mar\u00E7o","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
		DefaultComboBoxModel<Object> meses = new DefaultComboBoxModel<Object>(e);
		cbMes.setModel(meses);

		cbMes.setSelectedIndex(dataAtual.get(Calendar.MONTH));
		JPanel panelLista = new JPanel();
		panelLista.setMaximumSize(new Dimension(10, 80));
		GridBagConstraints gbc_panelLista = new GridBagConstraints();
		gbc_panelLista.insets = new Insets(0, 0, 5, 5);
		gbc_panelLista.fill = GridBagConstraints.BOTH;
		gbc_panelLista.gridx = 1;
		gbc_panelLista.gridy = 1;
		add(panelLista, gbc_panelLista);
		GridBagLayout gbl_panelLista = new GridBagLayout();
		gbl_panelLista.columnWeights = new double[]{0.0};
		gbl_panelLista.rowWeights = new double[]{1.0};
		panelLista.setLayout(gbl_panelLista);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.weighty = 1.0;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.weightx = 1.0;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panelLista.add(scrollPane, gbc_scrollPane);

		list.setVisibleRowCount(-1); //controla crescimento vertical
		list.setFixedCellWidth(100); //controla crescimento horizontal
		list.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setViewportView(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		atualizaJList();

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 3;
		gbc_panel_1.anchor = GridBagConstraints.NORTH;
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 2;
		add(panel_1, gbc_panel_1);

		JButton btVoltar = new JButton("Voltar");
		btVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(new Menu());
			}
		});
		panel_1.add(btVoltar);

		JButton btnAdicionar_1 = new JButton("Adicionar Opini\u00E3o");
		panel_1.add(btnAdicionar_1);
		btnAdicionar_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Sistema.setTela(new AdicionarOpiniao(tela));
			}
		});

	}

	private void atualizaJList() {
		List<Opiniao> lista = filtraOpinioes();
		Filtro.ordenaToString(lista);
		Filtro.preencheJList(lista, list);
	}

	private List<Opiniao> filtraOpinioes() {
		int mes_selecionado = cbMes.getSelectedIndex();
		int ano_selecionado = (int) cbAno.getSelectedItem();

		if (mes_selecionado == -1 || ano_selecionado == -1) {
			return new LinkedList<Opiniao>();
		}

		List<Opiniao> resultado = new LinkedList<>();
		for (Opiniao op : Sistema.getHotel().getOpinioes()) {
			Calendar dataOpiniao = op.getData();
			if (dataOpiniao.get(Calendar.YEAR) == ano_selecionado && dataOpiniao.get(Calendar.MONTH) == mes_selecionado)
				resultado.add(op);
		}
		return resultado;
	}

}
