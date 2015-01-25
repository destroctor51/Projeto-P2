package gui.servicos;

import gui.Sistema;
import hotel.Restaurante;

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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import servicos.alugaveis.Babysitter;
import servicos.alugaveis.CamaExtra;
import servicos.devolviveis.Carro;
import servicos.devolviveis.Quarto;

public class Servicos extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField tfBuscar;
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

		final DefaultListModel<Object> dlm = new DefaultListModel<Object>();
		final DefaultListModel<Object> dlmNotVisible = new DefaultListModel<>();

		addAncestorListener(new AncestorListener() {
			@Override
			public void ancestorAdded(AncestorEvent arg0) {
				atualizaJList(dlm);
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
		gridBagLayout.rowHeights = new int[] {0, 0, 0, 0};
		gridBagLayout.columnWidths = new int[] {0, 400, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.75, 0.5, 1.0, 0.75};
		setLayout(gridBagLayout);

		JPanel panelSuperior = new JPanel();
		GridBagLayout gbl_panelSuperior = new GridBagLayout();
		gbl_panelSuperior.columnWeights = new double[]{0.0, 1.0};
		gbl_panelSuperior.rowWeights = new double[]{1.0, 1.0, 1.0};
		panelSuperior.setLayout(gbl_panelSuperior);

		tfBuscar = new JTextField();
		tfBuscar.setMaximumSize(new Dimension(100, 100));
		tfBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				//coloca de volta os elementos que nao estavam visiveis
				for(int i=0; i < dlmNotVisible.getSize();i++)
					dlm.addElement(dlmNotVisible.getElementAt(i));

				//como agora todos os elementos estao visiveis sao retirados dessa lista
				dlmNotVisible.clear();
				list.setModel(dlm);

				String palavra = tfBuscar.getText();
				if(e.getKeyChar() != '\b' && e.getKeyChar() != '\u007F' )
					palavra = tfBuscar.getText() + e.getKeyChar();

				//seleciona palavras validas de acordo com o texto do tf
				if (!palavra.equals("")) {
					for (int i = 0; i < dlm.getSize(); i++) {
						String str = dlm.getElementAt(i).toString();
						if (!checaSemelhanca(palavra,str)) {
							dlmNotVisible.addElement(dlm.getElementAt(i));
						}
					}
				}

				//remove elementos invalidos
				for(int i=0; i < dlmNotVisible.getSize();i++)
					dlm.removeElement(dlmNotVisible.getElementAt(i));

				ordenaJList();
			}
		});
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
				atualizaJList(dlm);
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
		cbServicos_1.setSelectedIndex(-1);
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
		gbl_panel.rowHeights = new int[]{0, 23, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		JButton btnRemoverServio = new JButton("Remover Servi\u00E7o");
		GridBagConstraints gbc_btnRemoverServio = new GridBagConstraints();
		gbc_btnRemoverServio.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnRemoverServio.insets = new Insets(0, 0, 0, 5);
		gbc_btnRemoverServio.gridx = 0;
		gbc_btnRemoverServio.gridy = 1;
		panel.add(btnRemoverServio, gbc_btnRemoverServio);
		btnRemoverServio.setMaximumSize(new Dimension(150, 100));
		btnRemoverServio.setBounds(new Rectangle(100, 0, 100, 50));
		btnRemoverServio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object item = list.getSelectedValue();
				dlm.removeElement(item);
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
				ordenaJList();
			}
		});

		JButton btnAdicionar_1 = new JButton("Adicionar Servi\u00E7o");
		GridBagConstraints gbc_btnAdicionar_1 = new GridBagConstraints();
		gbc_btnAdicionar_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnAdicionar_1.gridx = 1;
		gbc_btnAdicionar_1.gridy = 1;
		panel.add(btnAdicionar_1, gbc_btnAdicionar_1);
		btnAdicionar_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object cbSelecionado = cbServicos_1.getSelectedItem();
				Sistema.setTela(new AdicionarServico(cbSelecionado,tela));
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
		list.setBorder(new EmptyBorder(0, 0, 0, 0));
		list.addMouseListener(new doubleClick(list));
		scrollPane.setViewportView(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(dlm);
		list.setSelectedIndex(0);
		GridBagConstraints gbc_lbObs = new GridBagConstraints();
		gbc_lbObs.anchor = GridBagConstraints.NORTH;
		gbc_lbObs.insets = new Insets(0, 0, 0, 5);
		gbc_lbObs.gridx = 1;
		gbc_lbObs.gridy = 3;
		add(lbObs, gbc_lbObs);

		lbObs.setVisible(false);

	}

	private boolean checaSemelhanca(String palavra, String str) {
		for (int i = 0; i <= str.length() - palavra.length(); i++)
			if (str.substring(i, i + palavra.length()).toLowerCase().equals(palavra.toLowerCase()))
				return true;

		return false;
	}

	private void ordenaJList(){

		ListModel<Object> lm = list.getModel();
		DefaultListModel<Object> dlm = (DefaultListModel<Object>) lm;
		Object[] contents = dlm.toArray();
		for(int i =0; i< contents.length;i++){
			for(int j=i+1; j<contents.length;j++){
				String str1 = contents[i].toString().toLowerCase();
				String str2 = contents[j].toString().toLowerCase();
				if(str1.compareTo(str2) > 0) {
					Object temp = contents[i];
					contents[i] = contents[j];
					contents[j] = temp;
				}
			}
		}

		dlm.clear();
		for(Object obj: contents)
			dlm.addElement(obj);
		list.setModel(dlm);
	}

	private void atualizaJList(DefaultListModel<Object> dlm) {
		String selecionado = (String) cbServicos_1.getSelectedItem();
		dlm.clear();
		if (selecionado == null)
			return;
		switch(selecionado){
			case "Babysitter":
				for(Babysitter bs: Sistema.getHotel().getBabas())
					dlm.addElement(bs);
				break;
			case "Cama Extra":
				for(CamaExtra bs: Sistema.getHotel().getCamas())
					dlm.addElement(bs);
				break;
			case "Carro":
				for(Carro c: Sistema.getHotel().getCarros())
					dlm.addElement(c);
				break;
			case "Quarto":
				for(Quarto q: Sistema.getHotel().getQuartos())
					dlm.addElement(q);
				break;
			case "Restaurante":
				for(Restaurante r: Sistema.getHotel().getRestaurantes())
					dlm.addElement(r);
				break;

		}
		ordenaJList();
	}

	class doubleClick extends MouseAdapter {
		protected JList<Object> list;

		public doubleClick(JList<Object> l) {
			list = l;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				int index = list.locationToIndex(e.getPoint());
				ListModel<Object> dlm = list.getModel();
				Object item = dlm.getElementAt(index);
				list.ensureIndexIsVisible(index);
				Sistema.setTela(new AtualizarServico(tela, item));
			}
		}
	}
}

