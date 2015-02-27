package gui.servicos;

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
import java.util.LinkedList;
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
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import utils.Filtro;
import core.hotel.Restaurante;
import core.servicos.alugaveis.Babysitter;
import core.servicos.alugaveis.CamaExtra;
import core.servicos.devolviveis.Carro;
import core.servicos.devolviveis.Quarto;

public class Servicos extends JPanel {

	private static final long serialVersionUID = 1L;

	private SuperTextField tfBuscar;
	final JComboBox<String> cbServicos_1 = new JComboBox<>();
	private JButton btnAdicionar;
	private JLabel  lblAtualizarServios, lblBuscar, lblTipoDeServio;
	final JList<Object> list = new JList<Object>();
	private JLabel lbObs = new JLabel("*Observa\u00E7\u00F5es");
	private JPanel tela = this;

	/**
	 * Create the panel.
	 */
	public Servicos() {

		addAncestorListener(new AncestorListener() {
			@Override
			public void ancestorAdded(AncestorEvent arg0) {
				List<Object> elementos = filtraList();
				Filtro.exibeFiltrado(tfBuscar.getText(), elementos, list);
				
				lbObs.setVisible(false);
			}
			@Override
			public void ancestorMoved(AncestorEvent arg0) {}
			@Override
			public void ancestorRemoved(AncestorEvent arg0) {}
		});
		setName("Servi\u00E7os");
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
				List<Object> elementos = filtraList();
				Filtro.exibeFiltrado(text, elementos, list);
			}
		};
		
		tfBuscar.setMaximumSize(new Dimension(100, 100));
		panelSuperior.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{tfBuscar, cbServicos_1, btnAdicionar, lblAtualizarServios, lblBuscar, lblTipoDeServio}));

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

		cbServicos_1.setAlignmentX(Component.LEFT_ALIGNMENT);

		cbServicos_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				atualizaJList();
				tfBuscar.setText("");
			}
		});

		JLabel lblTipoDeServio_1 = new JLabel("Tipo de Servi\u00E7o:");
		GridBagConstraints gbc_lblTipoDeServio_1 = new GridBagConstraints();
		gbc_lblTipoDeServio_1.anchor = GridBagConstraints.EAST;
		gbc_lblTipoDeServio_1.insets = new Insets(0, 0, 0, 10);
		gbc_lblTipoDeServio_1.gridx = 0;
		gbc_lblTipoDeServio_1.gridy = 2;
		panelSuperior.add(lblTipoDeServio_1, gbc_lblTipoDeServio_1);
		cbServicos_1.setModel(new DefaultComboBoxModel<String>(new String[] {"Babysitter", "Cama Extra", "Carro", "Quarto", "Restaurante"}));
		GridBagConstraints gbc_cbServicos_1 = new GridBagConstraints();
		gbc_cbServicos_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbServicos_1.gridx = 1;
		gbc_cbServicos_1.gridy = 2;
		panelSuperior.add(cbServicos_1, gbc_cbServicos_1);
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
		gbl_panel.columnWidths = new int[]{113, 115, 0};
		gbl_panel.rowHeights = new int[]{23, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		JButton btnRemoverServio = new JButton("Remover Servi\u00E7o");
		GridBagConstraints gbc_btnRemoverServio = new GridBagConstraints();
		gbc_btnRemoverServio.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnRemoverServio.insets = new Insets(0, 0, 0, 5);
		gbc_btnRemoverServio.gridx = 0;
		gbc_btnRemoverServio.gridy = 0;
		panel.add(btnRemoverServio, gbc_btnRemoverServio);
		btnRemoverServio.setMaximumSize(new Dimension(150, 100));
		btnRemoverServio.setBounds(new Rectangle(100, 0, 100, 50));
		btnRemoverServio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object item = list.getSelectedValue();
				int selected = cbServicos_1.getSelectedIndex();

				if (item == null) {
					lbObs.setText("Nenhum item foi selecionado");
					lbObs.setForeground(Color.RED);
					lbObs.setVisible(true);
					return;
				}

				lbObs.setForeground(new Color(0, 180, 0));
				lbObs.setText("Item removido com sucesso");
				lbObs.setVisible(true);

				switch(selected) {

				case 0:
					Babysitter bs = (Babysitter)item;
					Sistema.getHotel().removeBaba(bs);
					break;

				case 1:
					CamaExtra ce = (CamaExtra)item;
					Sistema.getHotel().removeCamaExtra(ce);
					break;
				case 2:
					Carro c = (Carro)item;
					try {
						Sistema.getHotel().removeCarro(c);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					break;

				case 3:
					Quarto q = (Quarto)item;
					Sistema.getHotel().removeQuarto(q);
					break;
				case 4:
					Restaurante r = (Restaurante)item;
					Sistema.getHotel().removeRestaurante(r);
					break;
				}
				List<Object> elementos = filtraList();
				Filtro.exibeFiltrado(tfBuscar.getText(), elementos, list);
			}
		});

		JButton btnAdicionar_1 = new JButton("Adicionar Servi\u00E7o");
		GridBagConstraints gbc_btnAdicionar_1 = new GridBagConstraints();
		gbc_btnAdicionar_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnAdicionar_1.gridx = 1;
		gbc_btnAdicionar_1.gridy = 0;
		panel.add(btnAdicionar_1, gbc_btnAdicionar_1);
		btnAdicionar_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String cbSelecionado = (String) cbServicos_1.getSelectedItem();
				Sistema.setTela(new AdicionarServico(cbSelecionado, tela));
			}
		});

		JPanel panelLista = new JPanel();
		panelLista.setMaximumSize(new Dimension(10, 80));
		GridBagConstraints gbc_panelLista = new GridBagConstraints();
		gbc_panelLista.insets = new Insets(0, 0, 5, 5);
		gbc_panelLista.fill = GridBagConstraints.BOTH;
		gbc_panelLista.gridx = 1;
		gbc_panelLista.gridy = 2;
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
		list.addMouseListener(new doubleClick(list));
		scrollPane.setViewportView(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.NORTH;
		gbc_panel_1.insets = new Insets(5, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 3;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {0, 0};
		gbl_panel_1.rowHeights = new int[] {0};
		gbl_panel_1.columnWeights = new double[]{1.0, 0.0};
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
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 0;
		panel_1.add(btnNewButton, gbc_btnNewButton);

		lbObs.setVisible(false);

	}

	private List<Object> filtraList() {
		String selecionado = (String) cbServicos_1.getSelectedItem();
		
		if (selecionado == null)
			return new ArrayList<Object>();
		
		List<Object> elementos = new LinkedList<>();
		
		switch(selecionado){
		case "Babysitter":
			for(Babysitter bs: Sistema.getHotel().getBabas())
				elementos.add(bs);
			break;
		case "Cama Extra":
			for(CamaExtra bs: Sistema.getHotel().getCamas())
				elementos.add(bs);
			break;
		case "Carro":
			for(Carro c: Sistema.getHotel().getCarros())
				elementos.add(c);
			break;
		case "Quarto":
			for(Quarto q: Sistema.getHotel().getQuartos())
				elementos.add(q);
			break;
		case "Restaurante":
			for(Restaurante r: Sistema.getHotel().getRestaurantes())
				elementos.add(r);
			break;
		}
		
		return elementos;
	}
	
	private void atualizaJList() {
		List<Object> elementos = filtraList();
		Filtro.ordenaToString(elementos);
		Filtro.preencheJList(elementos, list);
	}

	class doubleClick extends MouseAdapter {
		protected JList<Object> list;

		public doubleClick(JList<Object> l) {
			list = l;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				int index;
				Object item;
				try {
					index = list.locationToIndex(e.getPoint());
					ListModel<Object> dlm = list.getModel();
					item = dlm.getElementAt(index);
				} catch (ArrayIndexOutOfBoundsException ex) {
					return;
				}
				list.ensureIndexIsVisible(index);
				Sistema.setTela(new AtualizarServico(tela, item));
			}
		}
	}
}

