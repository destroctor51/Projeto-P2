package gui.refeicoes;

import gui.Sistema;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import core.hotel.Contrato;
import core.hotel.EstadoDeContrato;
import core.hotel.Hospede;
import core.servicos.pagaveis.Refeicao;

public class AdicionaRefeicoes extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Hospede hospede;
	private DefaultListModel<Object> lista1 = new DefaultListModel<>();
	private JComboBox<Contrato> cbContratos;
	private JLabel errorLabel;

	/**
	 * Create the panel.
	 */
	public AdicionaRefeicoes(final List<Refeicao> escolhidas, final JPanel tela) {
		setName("Adiciona Refei\u00E7\u00E3o");
		setVisible(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 105, 49, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.5, 1.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 0.0, 1.0, 1.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblSelecionaHospede = new JLabel("Selecione o h\u00F3spede:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 10, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 0;
		add(lblSelecionaHospede, gbc_lblNewLabel_1);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
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
		scrollPane
		.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel_1.add(scrollPane, gbc_scrollPane);
		scrollPane.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);

		final JList<Object> list = new JList<Object>();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				hospede = (Hospede) list.getSelectedValue();
				if (hospede != null) {
					for (Contrato c : hospede.getContratos()) {
						if (c.getEstado() == EstadoDeContrato.ABERTO)
							cbContratos.addItem(c);
					}
				}
			}
		});
		list.setFixedCellWidth(100);
		list.setVisibleRowCount(-1);
		scrollPane.setViewportView(list);
		for (Hospede h : Sistema.getHotel().getHospedes()) {
			lista1.addElement(h);
		}
		list.setModel(lista1);

		JLabel lblSelecioneContrato = new JLabel("Selecione o contrato:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 10, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 2;
		add(lblSelecioneContrato, gbc_lblNewLabel_2);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.anchor = GridBagConstraints.NORTH;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 3;
		add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		cbContratos = new JComboBox<Contrato>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 0;
		panel_2.add(cbContratos, gbc_comboBox);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 4;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 200, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0 };
		gbl_panel.rowWeights = new double[] { 0.0 };
		panel.setLayout(gbl_panel);

		errorLabel = new JLabel("");
		errorLabel.setForeground(Color.red);
		GridBagConstraints gbc_ErrorLabel = new GridBagConstraints();
		gbc_ErrorLabel.anchor = GridBagConstraints.WEST;
		gbc_ErrorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_ErrorLabel.gridx = 0;
		gbc_ErrorLabel.gridy = 0;
		panel.add(errorLabel, gbc_ErrorLabel);
		errorLabel.setVisible(false);
		errorLabel.setIcon(new ImageIcon(AdicionaRefeicoes.class
				.getResource("/gui/images/error.png")));

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Contrato contrato = (Contrato) cbContratos.getSelectedItem();
				if (hospede == null) {
					errorLabel.setText("Nenhum h\u00F3spede foi selecionado");
					errorLabel.setVisible(true);
					return;
				}
				else if (cbContratos.getSelectedIndex() == -1) {
					errorLabel.setText("Nenhum contrato foi selecionado");
					errorLabel.setVisible(true);
					return;
				}

				errorLabel.setVisible(false);
				for (Refeicao r : escolhidas){
					contrato.adicionaServico(r);
					Sistema.setTela(tela);
				}
			}
		});

		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 10);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 0;
		panel.add(btnConfirmar, gbc_btnNewButton_1);

		JButton btnRetornar = new JButton("Retornar");
		btnRetornar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(tela);
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 0;
		panel.add(btnRetornar, gbc_btnNewButton);

	}
}
