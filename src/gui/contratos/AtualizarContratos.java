package gui.contratos;

import gui.Menu;
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

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import utils.Filtro;
import core.hotel.Contrato;
import core.hotel.EstadoDeContrato;
import core.hotel.Hospede;

public class AtualizarContratos extends JPanel {

	private static final long serialVersionUID = 1L;

	private SuperTextField buscaField;
	private JLabel errorLabel;
	private JList<Hospede> listHospedes;
	private JList<Contrato> listContratos;

	private JPanel tela = this;

	/**
	 * Create the panel.
	 */
	public AtualizarContratos() {
		setName("Atualizar Contratos");

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.5, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 0.25, 0.1, 0.25, 1.0};
		setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0};
		gbl_panel.rowHeights = new int[] {0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0};
		gbl_panel.rowWeights = new double[]{0.0};
		panel.setLayout(gbl_panel);

		JLabel label = new JLabel("Procurar H\u00F3spede:");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 0, 10);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel.add(label, gbc_label);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.SOUTH;
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.insets = new Insets(0, 0, 10, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);

		buscaField = new SuperTextField() {
			private static final long serialVersionUID = 1L;
			@Override
			protected void textChanged(String text) {
				Hospede selecao = listHospedes.getSelectedValue();
				Filtro.exibeFiltrado(text, Sistema.getHotel().getHospedes(), listHospedes);
				listHospedes.setSelectedValue(selecao, false);
				preencheContratos(listHospedes.getSelectedValue());
			}
		};
		GridBagConstraints gbc_buscaField = new GridBagConstraints();
		gbc_buscaField.fill = GridBagConstraints.HORIZONTAL;
		gbc_buscaField.gridx = 1;
		gbc_buscaField.gridy = 0;
		panel.add(buscaField, gbc_buscaField);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		listHospedes = new JList<>();
		listHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listHospedes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				preencheContratos(listHospedes.getSelectedValue());
			}
		});
		scrollPane.setViewportView(listHospedes);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 10, 5);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);

		JLabel label_1 = new JLabel("Contratos:");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.SOUTHWEST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 2;
		add(label_1, gbc_label_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		listContratos = new JList<>();
		listContratos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(listContratos);
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.insets = new Insets(0, 0, 10, 5);
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 3;
		add(scrollPane_1, gbc_scrollPane_1);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.NORTH;
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 4;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {200, 0, 0};
		gbl_panel_1.rowHeights = new int[] {0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0.0};
		gbl_panel_1.rowWeights = new double[]{0.0};
		panel_1.setLayout(gbl_panel_1);

		errorLabel = new JLabel("<erro>");
		GridBagConstraints gbc_errorLabel = new GridBagConstraints();
		gbc_errorLabel.anchor = GridBagConstraints.WEST;
		gbc_errorLabel.insets = new Insets(0, 0, 0, 5);
		gbc_errorLabel.gridx = 0;
		gbc_errorLabel.gridy = 0;
		panel_1.add(errorLabel, gbc_errorLabel);
		errorLabel.setForeground(Color.RED);
		errorLabel.setVisible(false);
		errorLabel.setIcon(new ImageIcon(AtualizarContratos.class
				.getResource("/gui/images/error.png")));

		JButton btnCancelar = new JButton("Voltar");
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.anchor = GridBagConstraints.EAST;
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 10);
		gbc_btnCancelar.gridx = 1;
		gbc_btnCancelar.gridy = 0;
		panel_1.add(btnCancelar, gbc_btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(new Menu(true));
			}
		});

		JButton btnVisualizar = new JButton("Visualizar");
		GridBagConstraints gbc_btnVisualizar = new GridBagConstraints();
		gbc_btnVisualizar.gridx = 2;
		gbc_btnVisualizar.gridy = 0;
		panel_1.add(btnVisualizar, gbc_btnVisualizar);
		btnVisualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				visualizarContrato();
			}
		});


		Filtro.exibeFiltrado("", Sistema.getHotel().getHospedes(), listHospedes);
	}

	private void preencheContratos(Hospede hospede) {
		DefaultListModel<Contrato> dml = new DefaultListModel<>();

		if(hospede != null) for(Contrato c : hospede.getContratos())
			if(!(c.getEstado().equals(EstadoDeContrato.FECHADO)))
				dml.addElement(c);
		listContratos.setModel(dml);
	}

	private void visualizarContrato() {
		if (listHospedes.isSelectionEmpty()) {
			errorLabel.setText("H\u00F3spede ainda n\u00E3o escolhido");
			errorLabel.setVisible(true);
			return;
		}

		else if(listContratos.isSelectionEmpty()) {
			errorLabel.setText("Contrato ainda n\u00E3o escolhido");
			errorLabel.setVisible(true);
			return;
		}

		errorLabel.setVisible(false);
		Sistema.setTela(new VisualizarContrato(listContratos.getSelectedValue(), tela));
	}
}
