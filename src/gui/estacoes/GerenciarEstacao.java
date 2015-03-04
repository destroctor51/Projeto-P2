package gui.estacoes;

import gui.Sistema;
import gui.components.CalendarioEstacao;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import core.tempo.Estacao;
import core.tempo.Periodo;

public class GerenciarEstacao extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private CalendarioEstacao calendario;
	private DefaultListModel<Periodo> lista = new DefaultListModel<>();
	private JLabel ErrorLabel;
	private Estacao est;
	private Estacao estacao;
	private JList<Periodo> list;

	/**
	 * Create the panel.
	 */
	public GerenciarEstacao(Estacao estac) {
		est = estac;
		if (est == null) {
			this.setName("Adicionar esta\u00E7\u00E3o");
			estacao = new Estacao("", 1);
		}
		else {
			this.setName("Gerenciar esta\u00E7\u00E3o");
			estacao = (Estacao) est.clone();
		}

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.5, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.5, 1.0 };
		setLayout(gridBagLayout);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.SOUTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 28, 28, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel lblNomeDaEstao = new JLabel("Nome da esta\u00E7\u00E3o:");
		GridBagConstraints gbc_lblNomeDaEstao = new GridBagConstraints();
		gbc_lblNomeDaEstao.anchor = GridBagConstraints.EAST;
		gbc_lblNomeDaEstao.insets = new Insets(0, 0, 10, 10);
		gbc_lblNomeDaEstao.gridx = 0;
		gbc_lblNomeDaEstao.gridy = 0;
		panel_1.add(lblNomeDaEstao, gbc_lblNomeDaEstao);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 10, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		panel_1.add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblTarifaDaEstao = new JLabel("Tarifa da esta\u00E7\u00E3o (%):");
		GridBagConstraints gbc_lblTarifaDaEstao = new GridBagConstraints();
		gbc_lblTarifaDaEstao.anchor = GridBagConstraints.EAST;
		gbc_lblTarifaDaEstao.insets = new Insets(0, 0, 0, 10);
		gbc_lblTarifaDaEstao.gridx = 0;
		gbc_lblTarifaDaEstao.gridy = 1;
		panel_1.add(lblTarifaDaEstao, gbc_lblTarifaDaEstao);

		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		panel_1.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 10, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0 };
		gbl_panel.rowWeights = new double[] { 0.0 };
		panel.setLayout(gbl_panel);

		calendario = new CalendarioEstacao(estacao);
		GridBagConstraints gbc_calendario = new GridBagConstraints();
		gbc_calendario.insets = new Insets(0, 0, 0, 5);
		gbc_calendario.anchor = GridBagConstraints.NORTHWEST;
		gbc_calendario.gridx = 0;
		gbc_calendario.gridy = 0;
		panel.add(calendario, gbc_calendario);

		JButton btnAdicionarPeriodio = new JButton("Adicionar per\u00EDodo");
		btnAdicionarPeriodio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Periodo periodo = calendario.getSelecao();

				if (periodo == null) {
					ErrorLabel.setText("Per\u00EDodo n\u00E3o foi selecionado");
					ErrorLabel.setVisible(true);
					return;
				}

				ErrorLabel.setVisible(false);
				lista.addElement(periodo);
				estacao.addPeriodo(periodo);
				calendario.atualizaDias();
				list.setModel(lista);
			}
		});
		GridBagConstraints gbc_btnAdicionarPeriodio = new GridBagConstraints();
		gbc_btnAdicionarPeriodio.insets = new Insets(0, 0, 0, 10);
		gbc_btnAdicionarPeriodio.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnAdicionarPeriodio.gridx = 1;
		gbc_btnAdicionarPeriodio.gridy = 0;
		panel.add(btnAdicionarPeriodio, gbc_btnAdicionarPeriodio);

		JButton btnRemoverPeriodo = new JButton("Remover per\u00EDodo");
		btnRemoverPeriodo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Periodo p = (Periodo) list.getSelectedValue();
					estacao.removePeriodo(p);
					calendario.atualizaDias();
					lista.remove(list.getSelectedIndex());
					} catch(ArrayIndexOutOfBoundsException ex) {
						ErrorLabel.setText("Nenhum per\u00EDodo foi selecionado");
						ErrorLabel.setVisible(true);
						return;
					}
					ErrorLabel.setVisible(false);
			}
		});
		GridBagConstraints gbc_btnRemoverPeriodo = new GridBagConstraints();
		gbc_btnRemoverPeriodo.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnRemoverPeriodo.gridx = 2;
		gbc_btnRemoverPeriodo.gridy = 0;
		panel.add(btnRemoverPeriodo, gbc_btnRemoverPeriodo);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 10, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);

		list = new JList<Periodo>();
		scrollPane.setViewportView(list);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.anchor = GridBagConstraints.NORTH;
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 3;
		add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 200, 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 1.0, 0.0 };
		gbl_panel_2.rowWeights = new double[] { 0.0 };
		panel_2.setLayout(gbl_panel_2);

		ErrorLabel = new JLabel("*Observa\u00E7\u00D5es");
		ErrorLabel.setIcon(new ImageIcon(GerenciarEstacao.class
				.getResource("/gui/images/error.png")));
		ErrorLabel.setForeground(Color.RED);
		ErrorLabel.setVisible(false);
		GridBagConstraints gbc_lblobservaes = new GridBagConstraints();
		gbc_lblobservaes.anchor = GridBagConstraints.WEST;
		gbc_lblobservaes.insets = new Insets(0, 0, 0, 5);
		gbc_lblobservaes.gridx = 0;
		gbc_lblobservaes.gridy = 0;
		panel_2.add(ErrorLabel, gbc_lblobservaes);

		JButton btnCancelar = new JButton("Voltar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(new Estacoes());
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.anchor = GridBagConstraints.EAST;
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 10);
		gbc_btnCancelar.gridx = 1;
		gbc_btnCancelar.gridy = 0;
		panel_2.add(btnCancelar, gbc_btnCancelar);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				double tarifa;
				if (estacao.getId().equals("")) {
					String nome = textField.getText();
					if (nome == null || nome.equals("")) {
						ErrorLabel.setText("Nome inv\u00EDlido");
						ErrorLabel.setVisible(true);
						return;
					}

					try {
						tarifa = Double.parseDouble(textField_1.getText());
					} catch (NumberFormatException ex) {
						ErrorLabel.setText("Valor de tarifa inv\u00EDlido");
						ErrorLabel.setVisible(true);
						return;
					}

					ErrorLabel.setVisible(false);

					estacao = new Estacao(nome, tarifa / 100);
					
					for (int i = 0; i < lista.size(); i++) {
						estacao.addPeriodo(lista.get(i));
					}
					
					Sistema.getHotel().adicionaEstacao(estacao);
				} else {
					try {
						tarifa = Double.parseDouble(textField_1.getText());
					} catch (NumberFormatException ex) {
						ErrorLabel.setText("Valor de tarifa inv\u00EDlido");
						ErrorLabel.setVisible(true);
						return;
					}
					estacao.setTarifa(tarifa/100);

					Sistema.getHotel().removeEstacao(est);
					Sistema.getHotel().adicionaEstacao(estacao);

				}

				Sistema.setTela(new Estacoes());
			}
		});
		GridBagConstraints gbc_btnConfirmar = new GridBagConstraints();
		gbc_btnConfirmar.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnConfirmar.gridx = 2;
		gbc_btnConfirmar.gridy = 0;
		panel_2.add(btnConfirmar, gbc_btnConfirmar);

		if (estac != null) {
			textField.setText(estac.getId());
			textField.setEditable(false);
			textField_1
			.setText(String.valueOf((int) (estac.getTarifa() * 100)));

			for (Periodo p : estac.getPeriodos()) {
				lista.addElement(p);
				list.setModel(lista);
			}
		}
		
		calendario.atualizaDias();
	}

}