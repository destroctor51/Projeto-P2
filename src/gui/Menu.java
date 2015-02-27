package gui;

import gui.contas.Contas;
import gui.contratos.AtualizarContratos;
import gui.contratos.CheckIn;
import gui.contratos.CheckOut;
import gui.contratos.RealizaReserva;
import gui.estacoes.Estacoes;
import gui.hospede.PesquisaHospede;
import gui.opinioes.Opinioes;
import gui.refeicoes.Refeicoes;
import gui.servicos.Servicos;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import core.login.Permissao;

/**
 * Essa tela conecta todas as outras do sistema.
 *
 * @author Victor Andrade de Almeida
 */
public class Menu extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Menu() {
		this(false);
	}

	public Menu(boolean skip) {
		setLayout(new CardLayout(0, 0));

		JPanel mainPanel = new JPanel();
		add(mainPanel, "main");
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.rowHeights = new int[] {0, 0, 0};
		gbl_mainPanel.columnWidths = new int[] {0, 0, 0, 0, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		gbl_mainPanel.rowWeights = new double[]{1.0, 0.3, 1.0};
		mainPanel.setLayout(gbl_mainPanel);

		JButton btnNewButton = new JButton("Hospedes");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(new PesquisaHospede());
			}
		});
		btnNewButton.setMinimumSize(new Dimension(150, 150));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.SOUTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 0;
		mainPanel.add(btnNewButton, gbc_btnNewButton);
		btnNewButton.setPreferredSize(new Dimension(150, 150));
		btnNewButton.setFocusable(false);
		btnNewButton.setFocusPainted(false);

		JButton btnNewButton_1 = new JButton("Contratos");
		btnNewButton_1.setMinimumSize(new Dimension(150, 150));
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setTela("contracts");
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.SOUTH;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 0;
		mainPanel.add(btnNewButton_1, gbc_btnNewButton_1);
		btnNewButton_1.setPreferredSize(new Dimension(150, 150));
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setFocusPainted(false);

		JButton btnNewButton_2 = new JButton("Refei\u00E7\u00F5es");
		btnNewButton_2.setMinimumSize(new Dimension(150, 150));
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Sistema.setTela(new Refeicoes());
			}
		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.anchor = GridBagConstraints.SOUTH;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 3;
		gbc_btnNewButton_2.gridy = 0;
		mainPanel.add(btnNewButton_2, gbc_btnNewButton_2);
		btnNewButton_2.setPreferredSize(new Dimension(150, 150));
		btnNewButton_2.setFocusable(false);
		btnNewButton_2.setFocusPainted(false);

		JButton btnNewButton_3 = new JButton("Opini\u00F5es");
		btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(new Opinioes());
			}
		});
		btnNewButton_3.setMinimumSize(new Dimension(150, 150));
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.anchor = GridBagConstraints.SOUTH;
		gbc_btnNewButton_3.gridx = 4;
		gbc_btnNewButton_3.gridy = 0;
		mainPanel.add(btnNewButton_3, gbc_btnNewButton_3);
		btnNewButton_3.setPreferredSize(new Dimension(150, 150));
		btnNewButton_3.setFocusable(false);
		btnNewButton_3.setFocusPainted(false);

		JButton btnNewButton_4 = new JButton("Contas de usu\u00E1rio");
		btnNewButton_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Sistema.setTela(new Contas());
			}
		});
		btnNewButton_4.setMinimumSize(new Dimension(150, 150));
		btnNewButton_4.setEnabled(Sistema.getUsuario().possuiPermissao(Permissao.GERENTE));
		btnNewButton_4.setFocusable(false);
		btnNewButton_4.setFocusPainted(false);
		btnNewButton_4.setPreferredSize(new Dimension(150, 150));
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton_4.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_4.gridx = 1;
		gbc_btnNewButton_4.gridy = 2;
		mainPanel.add(btnNewButton_4, gbc_btnNewButton_4);

		JButton btnNewButton_5 = new JButton("Relat\u00F3rios");
		btnNewButton_5.setMinimumSize(new Dimension(150, 150));
		btnNewButton_5.setEnabled(Sistema.getUsuario().possuiPermissao(Permissao.GERENTE));
		btnNewButton_5.setEnabled(false); // OUT OF ORDER
		btnNewButton_5.setFocusable(false);
		btnNewButton_5.setFocusPainted(false);
		btnNewButton_5.setPreferredSize(new Dimension(150, 150));
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton_5.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_5.gridx = 2;
		gbc_btnNewButton_5.gridy = 2;
		mainPanel.add(btnNewButton_5, gbc_btnNewButton_5);

		JButton btnNewButton_6 = new JButton("Servi\u00E7os");
		btnNewButton_6.setMinimumSize(new Dimension(150, 150));
		btnNewButton_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Sistema.setTela(new Servicos());
			}
		});
		btnNewButton_6.setEnabled(Sistema.getUsuario().possuiPermissao(Permissao.ADMINISTRADOR));
		btnNewButton_6.setFocusable(false);
		btnNewButton_6.setFocusPainted(false);
		btnNewButton_6.setPreferredSize(new Dimension(150, 150));
		GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
		gbc_btnNewButton_6.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton_6.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_6.gridx = 3;
		gbc_btnNewButton_6.gridy = 2;
		mainPanel.add(btnNewButton_6, gbc_btnNewButton_6);

		JButton btnNewButton_7 = new JButton("Esta\u00E7\u00F5es");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(new Estacoes());
			}
		});
		btnNewButton_7.setMinimumSize(new Dimension(150, 150));
		btnNewButton_7.setEnabled(Sistema.getUsuario().possuiPermissao(Permissao.ADMINISTRADOR));
		btnNewButton_7.setFocusable(false);
		btnNewButton_7.setFocusPainted(false);
		btnNewButton_7.setPreferredSize(new Dimension(150, 150));
		GridBagConstraints gbc_btnNewButton_7 = new GridBagConstraints();
		gbc_btnNewButton_7.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_7.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton_7.gridx = 4;
		gbc_btnNewButton_7.gridy = 2;
		mainPanel.add(btnNewButton_7, gbc_btnNewButton_7);

		JPanel contractPanel = new JPanel();
		add(contractPanel, "contracts");
		GridBagLayout gbl_contractPanel = new GridBagLayout();
		gbl_contractPanel.rowHeights = new int[] {0, 0, 0};
		gbl_contractPanel.columnWidths = new int[] {0, 0, 0, 0, 0, 0};
		gbl_contractPanel.columnWeights = new double[]{1.0, 0.3, 0.3, 0.3, 0.3, 1.0};
		gbl_contractPanel.rowWeights = new double[]{1.0, 0.0, 1.0};
		contractPanel.setLayout(gbl_contractPanel);

		JButton btnNewButton_8 = new JButton("Realizar reserva");
		btnNewButton_8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Sistema.setTela(new RealizaReserva());
			}
		});
		btnNewButton_8.setMinimumSize(new Dimension(150, 150));
		btnNewButton_8.setFocusable(false);
		btnNewButton_8.setFocusPainted(false);
		btnNewButton_8.setPreferredSize(new Dimension(150, 150));
		GridBagConstraints gbc_btnNewButton_8 = new GridBagConstraints();
		gbc_btnNewButton_8.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_8.gridx = 1;
		gbc_btnNewButton_8.gridy = 1;
		contractPanel.add(btnNewButton_8, gbc_btnNewButton_8);

		JButton btnNewButton_9 = new JButton("Atualizar contratos");
		btnNewButton_9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(new AtualizarContratos());
			}
		});
		btnNewButton_9.setMinimumSize(new Dimension(150, 150));
		btnNewButton_9.setFocusable(false);
		btnNewButton_9.setFocusPainted(false);
		btnNewButton_9.setPreferredSize(new Dimension(150, 150));
		GridBagConstraints gbc_btnNewButton_9 = new GridBagConstraints();
		gbc_btnNewButton_9.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_9.gridx = 2;
		gbc_btnNewButton_9.gridy = 1;
		contractPanel.add(btnNewButton_9, gbc_btnNewButton_9);

		JButton btnNewButton_10 = new JButton("Check-in");
		btnNewButton_10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(new CheckIn());
			}
		});
		btnNewButton_10.setMinimumSize(new Dimension(150, 150));
		btnNewButton_10.setFocusable(false);
		btnNewButton_10.setFocusPainted(false);
		btnNewButton_10.setPreferredSize(new Dimension(150, 150));
		GridBagConstraints gbc_btnNewButton_10 = new GridBagConstraints();
		gbc_btnNewButton_10.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_10.gridx = 3;
		gbc_btnNewButton_10.gridy = 1;
		contractPanel.add(btnNewButton_10, gbc_btnNewButton_10);

		JButton btnNewButton_11 = new JButton("Check-out");
		btnNewButton_11.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(new CheckOut());
			}
		});
		btnNewButton_11.setMinimumSize(new Dimension(150, 150));
		btnNewButton_11.setFocusable(false);
		btnNewButton_11.setFocusPainted(false);
		btnNewButton_11.setPreferredSize(new Dimension(150, 150));
		GridBagConstraints gbc_btnNewButton_11 = new GridBagConstraints();
		gbc_btnNewButton_11.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_11.gridx = 4;
		gbc_btnNewButton_11.gridy = 1;
		contractPanel.add(btnNewButton_11, gbc_btnNewButton_11);

		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(150, 32767));
		panel.setPreferredSize(new Dimension(150, 10));
		panel.setMinimumSize(new Dimension(150, 10));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.gridx = 4;
		gbc_panel.gridy = 2;
		contractPanel.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0};
		gbl_panel.rowHeights = new int[] {0, 0};
		gbl_panel.columnWeights = new double[]{1.0};
		gbl_panel.rowWeights = new double[]{1.0, 1.0};
		panel.setLayout(gbl_panel);

		JButton btnNewButton_12 = new JButton("Voltar");
		btnNewButton_12.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setTela("main");
			}
		});
		btnNewButton_12.setFocusable(false);
		btnNewButton_12.setFocusPainted(false);
		GridBagConstraints gbc_btnNewButton_12 = new GridBagConstraints();
		gbc_btnNewButton_12.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_12.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_12.gridx = 0;
		gbc_btnNewButton_12.gridy = 0;
		panel.add(btnNewButton_12, gbc_btnNewButton_12);

		if(skip) setTela("contracts");
	}

	private void setTela(String tela) {
		CardLayout cards = (CardLayout) getLayout();
		cards.show(this, tela);
	}
}
