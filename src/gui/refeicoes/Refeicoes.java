package gui.refeicoes;

import gui.Menu;
import gui.Sistema;
import gui.components.SuperTextField;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import utils.Filtro;
import core.hotel.Restaurante;
import core.servicos.pagaveis.Refeicao;

public class Refeicoes extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private SuperTextField tfBuscar;
	private static JComboBox<String> cbRestaurantes = new JComboBox<>();
	private JButton btnAdicionar;
	private JLabel  lblAtualizarServios, lblBuscar, lblTipoDeServio;
	private JList<Refeicao> list = new JList<>();
	private JLabel lbObs = new JLabel("*Observa\u00E7\u00F5es");
	private JPanel tela = this;
	
	private List<Refeicao> selecionadas = new ArrayList<>();
	
	/**
	 * Create the panel.
	 */
	public Refeicoes() {

		addAncestorListener(new AncestorListener() {
			@Override
			public void ancestorAdded(AncestorEvent arg0) {
				List<Refeicao> elementos = filtraList();
				Filtro.exibeFiltrado(tfBuscar.getText(), elementos, list);
				selecionadas = new ArrayList<>();
				lbObs.setVisible(false);
			}
			@Override
			public void ancestorMoved(AncestorEvent arg0) {}
			@Override
			public void ancestorRemoved(AncestorEvent arg0) {}
		});

		setName("Refei\u00E7\u00F5es");
		setMinimumSize(new Dimension(0, 0));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[] {0, 0, 90, 0};
		gridBagLayout.columnWidths = new int[] {0, 400, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.75, 0.5, 1.0, 1.0};
		setLayout(gridBagLayout);

		JPanel panelSuperior = new JPanel();
		GridBagLayout gbl_panelSuperior = new GridBagLayout();
		gbl_panelSuperior.columnWeights = new double[]{0.0, 1.0};
		gbl_panelSuperior.rowWeights = new double[]{1.0, 1.0, 1.0};
		panelSuperior.setLayout(gbl_panelSuperior);


		tfBuscar = new SuperTextField() {
			private static final long serialVersionUID = 1L;
			@Override
			protected void textChanged(String text) {
				List<Refeicao> elementos = filtraList();
				Filtro.exibeFiltrado(text, elementos, list);
			}
		};
		tfBuscar.setMaximumSize(new Dimension(100, 100));
		panelSuperior.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{tfBuscar, cbRestaurantes, btnAdicionar, lblAtualizarServios, lblBuscar, lblTipoDeServio}));


		JLabel lblBuscar_1 = new JLabel("Buscar:");
		GridBagConstraints gbc_lblBuscar_1 = new GridBagConstraints();
		gbc_lblBuscar_1.anchor = GridBagConstraints.EAST;
		gbc_lblBuscar_1.insets = new Insets(0, 0, 10, 10);
		gbc_lblBuscar_1.gridx = 0;
		gbc_lblBuscar_1.gridy = 1;
		panelSuperior.add(lblBuscar_1, gbc_lblBuscar_1);
		GridBagConstraints gbc_tfBuscar = new GridBagConstraints();
		gbc_tfBuscar.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfBuscar.insets = new Insets(0, 0, 10, 0);
		gbc_tfBuscar.gridx = 1;
		gbc_tfBuscar.gridy = 1;
		panelSuperior.add(tfBuscar, gbc_tfBuscar);

		cbRestaurantes.setAlignmentX(Component.LEFT_ALIGNMENT);

		cbRestaurantes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tfBuscar.setText("");
				atualizaJList();
			}
		});

		JLabel lbRestaurante = new JLabel("Restaurante:");
		GridBagConstraints gbc_lbRestaurante = new GridBagConstraints();
		gbc_lbRestaurante.anchor = GridBagConstraints.EAST;
		gbc_lbRestaurante.insets = new Insets(0, 0, 0, 10);
		gbc_lbRestaurante.gridx = 0;
		gbc_lbRestaurante.gridy = 2;
		panelSuperior.add(lbRestaurante, gbc_lbRestaurante);

		DefaultComboBoxModel<String> dcm = new DefaultComboBoxModel<>();
		for (Restaurante r: Sistema.getHotel().getRestaurantes())
			dcm.addElement(r.getNome());
		cbRestaurantes.setModel(dcm);

		GridBagConstraints gbc_cbRestaurantes = new GridBagConstraints();
		gbc_cbRestaurantes.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbRestaurantes.gridx = 1;
		gbc_cbRestaurantes.gridy = 2;
		panelSuperior.add(cbRestaurantes, gbc_cbRestaurantes);
		GridBagConstraints gbc_panelSuperior = new GridBagConstraints();
		gbc_panelSuperior.anchor = GridBagConstraints.SOUTH;
		gbc_panelSuperior.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelSuperior.insets = new Insets(0, 0, 5, 5);
		gbc_panelSuperior.gridx = 1;
		gbc_panelSuperior.gridy = 0;
		add(panelSuperior, gbc_panelSuperior);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.SOUTH;
		gbc_panel.insets = new Insets(0, 0, 10, 5);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{113, 115, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 23, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		JButton btnRemoverRefeicao = new JButton("Remover Refei\u00E7\u00E3o");
		GridBagConstraints gbc_btnRemoverRefeicao = new GridBagConstraints();
		gbc_btnRemoverRefeicao.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnRemoverRefeicao.insets = new Insets(0, 0, 0, 5);
		gbc_btnRemoverRefeicao.gridx = 0;
		gbc_btnRemoverRefeicao.gridy = 1;
		panel.add(btnRemoverRefeicao, gbc_btnRemoverRefeicao);
		btnRemoverRefeicao.setMaximumSize(new Dimension(150, 100));
		btnRemoverRefeicao.setBounds(new Rectangle(100, 0, 100, 50));
		btnRemoverRefeicao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Object value = list.getSelectedValue();
				Object item = cbRestaurantes.getSelectedItem();

				if (value == null) {
					lbObs.setText("Nenhum item foi selecionado");
					lbObs.setForeground(Color.RED);
					lbObs.setVisible(true);
					return;
				}

				String nomeRestaurante = (String) item;
				for (Restaurante r : Sistema.getHotel().getRestaurantes())
					if (r.getNome().equals(nomeRestaurante)){
						r.getEstoque().remove(value);
						break;
					}

				List<Refeicao> elementos = filtraList();
				Filtro.exibeFiltrado(tfBuscar.getText(), elementos, list);

				lbObs.setForeground(new Color(0, 180, 0));
				lbObs.setText("Item removido com sucesso");
				lbObs.setVisible(true);
			}
		});

		JButton btnAdicionar_1 = new JButton("Adicionar Nova Refei\u00E7\u00E3o");
		GridBagConstraints gbc_btnAdicionar_1 = new GridBagConstraints();
		gbc_btnAdicionar_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnAdicionar_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnAdicionar_1.gridx = 1;
		gbc_btnAdicionar_1.gridy = 1;
		panel.add(btnAdicionar_1, gbc_btnAdicionar_1);
		
		JButton btnAddContrato = new JButton("Add Contrato");
		btnAddContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedIndex() != -1) selecionadas.add(list.getSelectedValue());
			}
		});
		GridBagConstraints gbc_btnAddContrato = new GridBagConstraints();
		gbc_btnAddContrato.gridx = 2;
		gbc_btnAddContrato.gridy = 1;
		panel.add(btnAddContrato, gbc_btnAddContrato);
		btnAdicionar_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (cbRestaurantes.getSelectedItem() == null) {
					lbObs.setText("Nao h\u00E1 um restaurante selecionado");
					lbObs.setForeground(Color.RED);
					lbObs.setVisible(true);
				} else {
					String nome = (String)cbRestaurantes.getSelectedItem();
					Sistema.setTela(new AdicionarRefeicao(tela,nome));
				}
			}
		});

		JPanel panelLista = new JPanel();
		panelLista.setMaximumSize(new Dimension(10, 100));
		GridBagConstraints gbc_panelLista = new GridBagConstraints();
		gbc_panelLista.insets = new Insets(0, 0, 5, 5);
		gbc_panelLista.fill = GridBagConstraints.BOTH;
		gbc_panelLista.gridx = 1;
		gbc_panelLista.gridy = 2;
		add(panelLista, gbc_panelLista);
		GridBagLayout gbl_panelLista = new GridBagLayout();
		gbl_panelLista.columnWeights = new double[]{1.0};
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
		list.setFixedCellWidth(100);//controla crescimento horizontal

		list.setBorder(new EmptyBorder(0, 0, 0, 0));
		list.addMouseListener(new doubleClick(list));
		scrollPane.setViewportView(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(-1);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.NORTH;
		gbc_panel_1.insets = new Insets(5, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 3;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {0, 0, 0};
		gbl_panel_1.rowHeights = new int[] {0};
		gbl_panel_1.columnWeights = new double[]{1.0, 0.0, 0.0};
		gbl_panel_1.rowWeights = new double[]{0.0};
		panel_1.setLayout(gbl_panel_1);
		GridBagConstraints gbc_lbObs = new GridBagConstraints();
		gbc_lbObs.anchor = GridBagConstraints.WEST;
		gbc_lbObs.insets = new Insets(0, 0, 0, 5);
		gbc_lbObs.gridx = 0;
		gbc_lbObs.gridy = 0;
		panel_1.add(lbObs, gbc_lbObs);

		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(new Menu());
			}
		});
		
		JButton btnContrato = new JButton("Contrato");
		btnContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Sistema.setTela(new EscolheRefeicoes(selecionadas,tela));
			}
		});
		GridBagConstraints gbc_btnContrato = new GridBagConstraints();
		gbc_btnContrato.insets = new Insets(0, 0, 0, 5);
		gbc_btnContrato.gridx = 1;
		gbc_btnContrato.gridy = 0;
		panel_1.add(btnContrato, gbc_btnContrato);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 0;
		panel_1.add(btnNewButton, gbc_btnNewButton);

		lbObs.setVisible(false);
		atualizaJList();
	}

	private void atualizaJList() {
		List<Refeicao> elementos = filtraList();
		Filtro.ordenaToString(elementos);
		Filtro.preencheJList(elementos, list);
	}

	private List<Refeicao> filtraList() {
		String selecionado = (String) cbRestaurantes.getSelectedItem();
		if (selecionado == null)
			return new ArrayList<>();

		List<Refeicao> resultado = new ArrayList<>();
		for (Restaurante r : Sistema.getHotel().getRestaurantes())
			if (r.getNome().equals(selecionado))
				for (Refeicao re : r.getEstoque())
					resultado.add(re);
		return resultado;
	}

	class doubleClick extends MouseAdapter {
		protected JList<Refeicao> list;

		public doubleClick(JList<Refeicao> l) {
			list = l;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				int index;
				Object item;
				try {
					index = list.locationToIndex(e.getPoint());
					ListModel<Refeicao> dlm = list.getModel();
					item = dlm.getElementAt(index);
				} catch (ArrayIndexOutOfBoundsException ex) {
					return;
				}	
				list.ensureIndexIsVisible(index);
				String nome = (String) Refeicoes.cbRestaurantes.getSelectedItem();
				Sistema.setTela(new AtualizarRefeicao(tela, nome,(Refeicao) item));
			}
		}
	}
}