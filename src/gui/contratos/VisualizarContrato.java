package gui.contratos;

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
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import utils.Filtro;
import core.hotel.Contrato;
import core.interfaces.Devolvivel;
import core.interfaces.Pagavel;

public class VisualizarContrato extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField textField;
	private JButton btnDevolver;
	private JButton btnDeletar;
	private JPanel telaAtual = this;
	private JTextField textField_1;
	private JTextField textField_2;

	public VisualizarContrato(final Contrato contrato, final JPanel tela) {
		this.setName("Visualizar Contrato");

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[] {0, 0, 120, 0};
		gridBagLayout.columnWidths = new int[] {30, 0, 30};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 0.1, 0.25, 1.0};
		setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.SOUTHWEST;
		gbc_panel.insets = new Insets(0, 0, 10, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[] {0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0};
		gbl_panel.rowWeights = new double[]{0.0};
		panel.setLayout(gbl_panel);

		JLabel label = new JLabel("Estado do contrato:");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.insets = new Insets(0, 0, 0, 10);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel.add(label, gbc_label);

		textField = new JTextField(contrato.getEstado().toString());
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 0, 20);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		panel.add(textField, gbc_textField);
		textField.setEditable(false);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Protocolo");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 10);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 0;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		textField_2 = new JTextField(Integer.toString(contrato.getProtocolo()));
		textField_2.setEditable(false);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 0, 20);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 0;
		panel.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);

		JLabel label_1 = new JLabel("Esta\u00E7\u00E3o");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 0, 10);
		gbc_label_1.gridx = 4;
		gbc_label_1.gridy = 0;
		panel.add(label_1, gbc_label_1);

		textField_1 = new JTextField(contrato.getEstacao().toString());
		textField_1.setEditable(false);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridx = 5;
		gbc_textField_1.gridy = 0;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		JLabel label_2 = new JLabel("Servi\u00E7os contratados:");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.SOUTHWEST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 1;
		gbc_label_2.gridy = 1;
		add(label_2, gbc_label_2);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		final JList<Pagavel> list = new JList<>();
		list.setVisibleRowCount(-1);
		list.setFixedCellWidth(100);
		list.setFocusable(false);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Pagavel selecao = list.getSelectedValue();
				boolean ativado = selecao instanceof Devolvivel && !((Devolvivel) selecao).isDevolvido();
				btnDevolver.setEnabled(ativado);
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setCellRenderer(new FilterRenderer());
		scrollPane_1.setViewportView(list);
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.insets = new Insets(0, 0, 10, 5);
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 2;
		add(scrollPane_1, gbc_scrollPane_1);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.anchor = GridBagConstraints.NORTH;
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 3;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFocusable(false);
		GridBagConstraints gbc_btnVoltar = new GridBagConstraints();
		gbc_btnVoltar.insets = new Insets(0, 0, 0, 10);
		gbc_btnVoltar.anchor = GridBagConstraints.EAST;
		gbc_btnVoltar.gridx = 0;
		gbc_btnVoltar.gridy = 0;
		panel_1.add(btnVoltar, gbc_btnVoltar);
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(tela);
			}
		});

		btnDevolver = new JButton("Devolver Servi\u00E7o");
		btnDevolver.setFocusable(false);
		btnDevolver.setEnabled(false);
		btnDevolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Devolvivel selecionado = (Devolvivel) list.getSelectedValue();
				selecionado.devolve(GregorianCalendar.getInstance());
				btnDevolver.setEnabled(false);
			}
		});

		btnDeletar = new JButton("Remover Servi\u00E7o");
		btnDeletar.setFocusable(false);
		btnDeletar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(list.isSelectionEmpty()) return;
				Pagavel selecionado = list.getSelectedValue();
				contrato.removeServico(selecionado);
				Filtro.preencheJList(contrato.getServicos(), list);
			}
		});
		GridBagConstraints gbc_btnDeletarServico = new GridBagConstraints();
		gbc_btnDeletarServico.insets = new Insets(0, 0, 0, 10);
		gbc_btnDeletarServico.gridx = 1;
		gbc_btnDeletarServico.gridy = 0;
		panel_1.add(btnDeletar, gbc_btnDeletarServico);
		GridBagConstraints gbc_btnDevolver = new GridBagConstraints();
		gbc_btnDevolver.insets = new Insets(0, 0, 0, 10);
		gbc_btnDevolver.gridx = 2;
		gbc_btnDevolver.gridy = 0;
		panel_1.add(btnDevolver, gbc_btnDevolver);

		JButton btnContratar = new JButton("Contratar Servi\u00E7o");
		btnContratar.setFocusable(false);
		btnContratar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Sistema.setTela(new SelecionarServicos(contrato, telaAtual));
			}
		});
		GridBagConstraints gbc_btnContratar = new GridBagConstraints();
		gbc_btnContratar.gridx = 3;
		gbc_btnContratar.gridy = 0;
		panel_1.add(btnContratar, gbc_btnContratar);

		addAncestorListener(new AncestorListener() {
			@Override
			public void ancestorAdded(AncestorEvent arg0) {
				Filtro.preencheJList(contrato.getServicos(), list);
				btnDevolver.setEnabled(false);
			}
			@Override
			public void ancestorMoved(AncestorEvent arg0) {}
			@Override
			public void ancestorRemoved(AncestorEvent arg0) {}
		});
	}

	private class FilterRenderer extends JLabel implements ListCellRenderer<Pagavel> {

		private static final long serialVersionUID = 1L;

		public FilterRenderer() {
			setOpaque(true);
			setBorder(new EmptyBorder(2, 5, 0, 0));
		}

		@Override
		public Component getListCellRendererComponent(
				JList<? extends Pagavel> list, Pagavel value, int index,
				boolean isSelected, boolean cellHasFocus) {

			setText(value.toString());

			setBackground(list.getBackground());
			setForeground(list.getForeground());

			if(value instanceof Devolvivel && !((Devolvivel) value).isDevolvido())
				setForeground(Color.RED);

			if (isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			}

			return this;
		}

	}
}
