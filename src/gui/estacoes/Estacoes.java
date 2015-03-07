package gui.estacoes;

import gui.Menu;
import gui.Sistema;
import gui.components.SuperTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import utils.Filtro;
import core.tempo.Estacao;

public class Estacoes extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private SuperTextField tfBuscar;
	private JButton btnAdicionar;
	final JList<Object> list = new JList<Object>();
	private JLabel lblobservaes;

	/**
	 * Create the panel.
	 */
	public Estacoes() {
		setName("Esta\u00E7\u00F5es");
		addAncestorListener(new AncestorListener() {
			@Override
			public void ancestorAdded(AncestorEvent arg0) {
				List<Object> elementos = filtraList();
				Filtro.exibeFiltrado(tfBuscar.getText(), elementos, list);

				lblobservaes.setVisible(false);
			}
			@Override
			public void ancestorMoved(AncestorEvent arg0) {}
			@Override
			public void ancestorRemoved(AncestorEvent arg0) {}
		});

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0};
		gridBagLayout.rowHeights = new int[] {0, 120, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.25, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 0.5, 1.0};
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
		gbl_panel.columnWidths = new int[] {0, 0};
		gbl_panel.rowHeights = new int[] {0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0};
		gbl_panel.rowWeights = new double[]{1.0, 1.0};
		panel.setLayout(gbl_panel);

		JLabel label = new JLabel("Buscar:");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 0, 10);
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		panel.add(label, gbc_label);

		tfBuscar = new SuperTextField() {
			private static final long serialVersionUID = 1L;
			@Override
			protected void textChanged(String text) {
				List<Object> elementos = filtraList();
				Filtro.exibeFiltrado(text, elementos, list);
			}
		};

		tfBuscar.setMaximumSize(new Dimension(100, 100));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		panel.add(tfBuscar, gbc_textField);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 10, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		list.setFocusable(false);

		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
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
					Sistema.setTela(new GerenciarEstacao((Estacao) item));
				}
			}
		});

		list.setVisibleRowCount(-1);
		list.setFixedCellWidth(100);
		scrollPane.setViewportView(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);


		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.anchor = GridBagConstraints.NORTH;
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 2;
		add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] {160, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[] {0};
		gbl_panel_2.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0};
		gbl_panel_2.rowWeights = new double[]{0.0};
		panel_2.setLayout(gbl_panel_2);

		lblobservaes = new JLabel("<erro>");
		lblobservaes.setIcon(new ImageIcon(Estacoes.class.getResource("/gui/images/error.png")));
		lblobservaes.setVisible(false);
		GridBagConstraints gbc_lblobservaes = new GridBagConstraints();
		gbc_lblobservaes.anchor = GridBagConstraints.WEST;
		gbc_lblobservaes.insets = new Insets(0, 0, 0, 5);
		gbc_lblobservaes.gridx = 0;
		gbc_lblobservaes.gridy = 0;
		panel_2.add(lblobservaes, gbc_lblobservaes);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFocusable(false);
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Sistema.setTela(new Menu());
			}
		});


		JButton btnRemoverEstao = new JButton("Remover Esta\u00E7\u00E3o");
		GridBagConstraints gbc_btnRemoverEstao = new GridBagConstraints();
		gbc_btnRemoverEstao.insets = new Insets(0, 0, 0, 10);
		gbc_btnRemoverEstao.gridx = 2;
		gbc_btnRemoverEstao.gridy = 0;
		panel_2.add(btnRemoverEstao, gbc_btnRemoverEstao);
		btnRemoverEstao.setFocusable(false);
		btnRemoverEstao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object item = list.getSelectedValue();

				if (item == null) {
					lblobservaes.setText("Nenhum item foi selecionado");
					lblobservaes.setForeground(Color.RED);
					lblobservaes.setVisible(true);
					return;
				}

				lblobservaes.setVisible(false);

				Sistema.getHotel().removeEstacao((Estacao) item);

				List<Object> elementos = filtraList();
				Filtro.exibeFiltrado(tfBuscar.getText(), elementos, list);
			}
		});
		btnRemoverEstao.setMaximumSize(new Dimension(150, 100));
		btnRemoverEstao.setBounds(new Rectangle(100, 0, 100, 50));
		GridBagConstraints gbc_btnVoltar = new GridBagConstraints();
		gbc_btnVoltar.insets = new Insets(0, 0, 0, 10);
		gbc_btnVoltar.anchor = GridBagConstraints.EAST;
		gbc_btnVoltar.gridx = 1;
		gbc_btnVoltar.gridy = 0;
		panel_2.add(btnVoltar, gbc_btnVoltar);


		btnAdicionar = new JButton("Adicionar Esta\u00E7\u00E3o");
		GridBagConstraints gbc_btnAdicionar = new GridBagConstraints();
		gbc_btnAdicionar.gridx = 3;
		gbc_btnAdicionar.gridy = 0;
		panel_2.add(btnAdicionar, gbc_btnAdicionar);
		btnAdicionar.setFocusable(false);
		btnAdicionar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(new GerenciarEstacao());
			}
		});

	}


	private List<Object> filtraList() {
		List<Object> elementos = new LinkedList<>();
		Iterator<Estacao> estacoes = Sistema.getHotel().getTarifas();
		while (estacoes.hasNext()) {
			elementos.add(estacoes.next());
		}
		return elementos;
	}
}
