package gui.hospede;

import gui.Menu;
import gui.Sistema;
import gui.components.SuperTextField;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
import core.hotel.Hospede;

public class PesquisaHospede extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	protected String nome;
	private static JList<Hospede> list = new JList<>();
	private JLabel errorLabel;
	private JPanel tela = this;
                    
	private SuperTextField superTextField; 
	/**
	 * Create the panel.
	 *
	 * @throws Exception
	 */
	public PesquisaHospede() {
		addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {
				List<Hospede> elementos = new ArrayList<>();
				for (Hospede hospede: Sistema.getHotel().getHospedes())
					elementos.add(hospede);
				Filtro.exibeFiltrado(superTextField.getText(), elementos, list);
			}
			public void ancestorMoved(AncestorEvent arg0) {
			}
			public void ancestorRemoved(AncestorEvent arg0) {
			}
		});

		setName("Pesquisa Hospede");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {30, 400, 30};
		gridBagLayout.rowHeights = new int[] {0, 90, 0};
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0 };
		setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.SOUTH;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0 };
		gbl_panel.rowWeights = new double[] { 0.0 };
		panel.setLayout(gbl_panel);

		JLabel lblHspede = new JLabel("H\u00F3spede :");
		GridBagConstraints gbc_lblHspede = new GridBagConstraints();
		gbc_lblHspede.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblHspede.insets = new Insets(0, 0, 10, 10);
		gbc_lblHspede.gridx = 0;
		gbc_lblHspede.gridy = 0;
		panel.add(lblHspede, gbc_lblHspede);
		
		superTextField = new SuperTextField() {
			private static final long serialVersionUID = 1L;
			@Override
			protected void textChanged(String text) {
				List<Hospede> elementos = new ArrayList<>();
				for (Hospede hospede: Sistema.getHotel().getHospedes())
					elementos.add(hospede);
				Filtro.exibeFiltrado(text, elementos, list);
			}
		};
		atualizaJList();
		GridBagConstraints gbc_superTextField = new GridBagConstraints();
		gbc_superTextField.insets = new Insets(0, 0, 0, 5);
		gbc_superTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_superTextField.gridx = 1;
		gbc_superTextField.gridy = 0;
		panel.add(superTextField, gbc_superTextField);

		JPanel panel_2 = new JPanel();
		panel_2.setMaximumSize(new Dimension(10, 80));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 1;
		add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWeights = new double[]{1.0};
		gbl_panel_2.rowWeights = new double[]{1.0};
		panel_2.setLayout(gbl_panel_2);

				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 0;
				gbc_scrollPane.gridy = 0;
				panel_2.add(scrollPane, gbc_scrollPane);

						list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						atualizaJList();
						list.addMouseListener(new doubleClick(list));
						
		scrollPane.setViewportView(list);

		list.setVisibleRowCount(-1); //controla crescimento vertical
		list.setFixedCellWidth(100); //controla crescimento horizontal

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.NORTH;
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 2;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		gbl_panel_1.rowWeights = new double[] { 0.0 };
		panel_1.setLayout(gbl_panel_1);

		errorLabel = new JLabel("");
		GridBagConstraints gbc_ErrorLabel = new GridBagConstraints();
		gbc_ErrorLabel.anchor = GridBagConstraints.WEST;
		gbc_ErrorLabel.insets = new Insets(10, 0, 0, 10);
		gbc_ErrorLabel.gridx = 0;
		gbc_ErrorLabel.gridy = 0;
		panel_1.add(errorLabel, gbc_ErrorLabel);
		errorLabel.setVisible(false);
		errorLabel.setIcon(new ImageIcon(PesquisaHospede.class
				.getResource("/gui/images/error.png")));
								
										JButton btnCancelar = new JButton("Voltar");
										GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
										gbc_btnCancelar.insets = new Insets(10, 0, 0, 10);
										gbc_btnCancelar.gridx = 7;
										gbc_btnCancelar.gridy = 0;
										panel_1.add(btnCancelar, gbc_btnCancelar);
										
												JButton btnCadastrar = new JButton("Cadastrar");
												GridBagConstraints gbc_btnCadastrar = new GridBagConstraints();
												gbc_btnCadastrar.anchor = GridBagConstraints.SOUTH;
												gbc_btnCadastrar.insets = new Insets(0, 0, 0, 5);
												gbc_btnCadastrar.gridx = 8;
												gbc_btnCadastrar.gridy = 0;
												panel_1.add(btnCadastrar, gbc_btnCadastrar);
												btnCadastrar.addActionListener(new ActionListener() {
													@Override
													public void actionPerformed(ActionEvent e) {
														Sistema.setTela(new CadastraHospede(tela));
													}
												});
										btnCancelar.addActionListener(new ActionListener() {
											@Override
											public void actionPerformed(ActionEvent e) {
												Sistema.setTela(new Menu());
											}
										});
	}

	private void atualizaJList() {
		List<Hospede> elementos = new ArrayList<>();
		for (Hospede hospede: Sistema.getHotel().getHospedes())
			elementos.add(hospede);
		Filtro.ordenaToString(elementos);
		Filtro.preencheJList(elementos, list);
	}
	
	private class doubleClick extends MouseAdapter {
		protected JList<Hospede> list;

		public doubleClick(JList<Hospede> list) {
			this.list = list;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				int index;
				Object item;
				try {
					index = list.locationToIndex(e.getPoint());
					ListModel<Hospede> lm = list.getModel();
					item = lm.getElementAt(index);
				} catch (ArrayIndexOutOfBoundsException ex) {
					return;
				}
				list.ensureIndexIsVisible(index);
				Sistema.setTela(new AtualizaHospede((Hospede) item,tela));
			}
		}
	}
}
	
