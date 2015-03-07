package gui.refeicoes;

import gui.Sistema;
import gui.components.SuperTextField;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import utils.Filtro;
import core.hotel.Contrato;
import core.hotel.EstadoDeContrato;
import core.hotel.Hospede;
import core.servicos.pagaveis.Refeicao;

public class ConfirmaCompra extends JPanel {

	private static final long serialVersionUID = 1L;

	private JList<Hospede> list;
	private JList<Contrato> list_1;
	private JLabel errorLabel;

	public ConfirmaCompra(final List<Refeicao> escolhidas, final JPanel telaAnterior) {
		setName("Confirmar Compra");

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 400, 0};
		gridBagLayout.rowHeights = new int[] {0, 120, 0, 120, 0};
		gridBagLayout.columnWeights = new double[] { 1.0, 0.25, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.25, 0.1, 0.25, 1.0 };
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
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel = new JLabel("Buscar H\u00F3spede:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 10);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		SuperTextField superTextField = new SuperTextField() {
			private static final long serialVersionUID = 1L;
			@Override
			protected void textChanged(String text) {
				Hospede selecao = list.getSelectedValue();
				Filtro.exibeFiltrado(text, Sistema.getHotel().getHospedes(), list);
				list.setSelectedValue(selecao, false);
				preencheContratos(list.getSelectedValue());
			}
		};
		GridBagConstraints gbc_superTextField = new GridBagConstraints();
		gbc_superTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_superTextField.gridx = 1;
		gbc_superTextField.gridy = 0;
		panel.add(superTextField, gbc_superTextField);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 10, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel_1.add(scrollPane, gbc_scrollPane);

		list = new JList<>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setVisibleRowCount(-1);
		list.setFocusable(false);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Hospede hospede = list.getSelectedValue();
				preencheContratos(hospede);
			}
		});
		list.setFixedCellWidth(100);
		scrollPane.setViewportView(list);

		JLabel lblSelecioneContrato = new JLabel("Selecione o contrato:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 2;
		add(lblSelecioneContrato, gbc_lblNewLabel_2);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 10, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 3;
		add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 0;
		panel_2.add(scrollPane_1, gbc_scrollPane_1);

		list_1 = new JList<>();
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_1.setVisibleRowCount(-1);
		list_1.setFocusable(false);
		list_1.setFixedCellWidth(100);
		scrollPane_1.setViewportView(list_1);

		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 0, 5);
		gbc_panel_3.anchor = GridBagConstraints.NORTH;
		gbc_panel_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 4;
		add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] { 200, 0, 0 };
		gbl_panel_3.rowHeights = new int[] { 0 };
		gbl_panel_3.columnWeights = new double[] { 0.0, 1.0, 0.0 };
		gbl_panel_3.rowWeights = new double[] { 0.0 };
		panel_3.setLayout(gbl_panel_3);

		errorLabel = new JLabel("<erro>");
		errorLabel.setForeground(Color.red);
		GridBagConstraints gbc_ErrorLabel = new GridBagConstraints();
		gbc_ErrorLabel.anchor = GridBagConstraints.WEST;
		gbc_ErrorLabel.insets = new Insets(0, 0, 0, 5);
		gbc_ErrorLabel.gridx = 0;
		gbc_ErrorLabel.gridy = 0;
		panel_3.add(errorLabel, gbc_ErrorLabel);
		errorLabel.setVisible(false);
		errorLabel.setIcon(new ImageIcon(ConfirmaCompra.class.getResource("/gui/images/error.png")));

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFocusable(false);
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Hospede hospede = list.getSelectedValue();
				Contrato contrato = list_1.getSelectedValue();

				if(hospede == null) {
					errorLabel.setText("Nenhum h\u00F3spede foi selecionado");
					errorLabel.setVisible(true);
					return;
				}

				else if(contrato == null) {
					errorLabel.setText("Nenhum contrato foi selecionado");
					errorLabel.setVisible(true);
					return;
				}

				for(Refeicao r : escolhidas)
					contrato.adicionaServico(r);
				Sistema.setTela(telaAnterior);
			}
		});

		JButton btnRetornar = new JButton("Voltar");
		btnRetornar.setFocusable(false);
		btnRetornar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(telaAnterior);
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 10);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 0;
		panel_3.add(btnRetornar, gbc_btnNewButton);

		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 0;
		panel_3.add(btnConfirmar, gbc_btnNewButton_1);

		Filtro.exibeFiltrado("", Sistema.getHotel().getHospedes(), list);
	}

	private void preencheContratos(Hospede hospede) {
		DefaultListModel<Contrato> dml = new DefaultListModel<>();

		if(hospede != null) for(Contrato c : hospede.getContratos())
			if(c.getEstado() == EstadoDeContrato.ABERTO)
				dml.addElement(c);
		list_1.setModel(dml);
	}
}
