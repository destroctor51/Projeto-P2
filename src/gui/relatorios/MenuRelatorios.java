package gui.relatorios;

import gui.Menu;
import gui.Sistema;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuRelatorios extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final JPanel tela = this;

	/**
	 * Create the panel.
	 */
	public MenuRelatorios() {
		
		setName("Gerar relatorios");
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 0.1, 0.1, 0.1, 0.1, 0.1, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton btnServicosDisponiveis = new JButton("Servicos disponiveis");
		GridBagConstraints gbc_btnServicosDisponiveis = new GridBagConstraints();
		gbc_btnServicosDisponiveis.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnServicosDisponiveis.insets = new Insets(0, 0, 5, 5);
		gbc_btnServicosDisponiveis.gridx = 1;
		gbc_btnServicosDisponiveis.gridy = 1;
		add(btnServicosDisponiveis, gbc_btnServicosDisponiveis);
		
		JButton btnContratosEmAberto = new JButton("Contratos em aberto");
		GridBagConstraints gbc_btnContratosEmAberto = new GridBagConstraints();
		gbc_btnContratosEmAberto.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnContratosEmAberto.insets = new Insets(0, 0, 5, 5);
		gbc_btnContratosEmAberto.gridx = 1;
		gbc_btnContratosEmAberto.gridy = 2;
		add(btnContratosEmAberto, gbc_btnContratosEmAberto);
		
		JButton btnFaturamento = new JButton("Faturamento e Servicos");
		GridBagConstraints gbc_btnFaturamentoPorMs = new GridBagConstraints();
		gbc_btnFaturamentoPorMs.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnFaturamentoPorMs.insets = new Insets(0, 0, 5, 5);
		gbc_btnFaturamentoPorMs.gridx = 1;
		gbc_btnFaturamentoPorMs.gridy = 3;
		add(btnFaturamento, gbc_btnFaturamentoPorMs);
		
		JButton btnOpinioes = new JButton("Opinioes sobre hotel");
		GridBagConstraints gbc_btnOpinioes = new GridBagConstraints();
		gbc_btnOpinioes.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOpinioes.insets = new Insets(0, 0, 5, 5);
		gbc_btnOpinioes.gridx = 1;
		gbc_btnOpinioes.gridy = 4;
		add(btnOpinioes, gbc_btnOpinioes);
		
		JButton btnVoltar = new JButton("Voltar");
		GridBagConstraints gbc_btnVoltar = new GridBagConstraints();
		gbc_btnVoltar.anchor = GridBagConstraints.EAST;
		gbc_btnVoltar.insets = new Insets(0, 0, 5, 5);
		gbc_btnVoltar.gridx = 1;
		gbc_btnVoltar.gridy = 5;
		add(btnVoltar, gbc_btnVoltar);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(new Menu());
			}
		});
		btnOpinioes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(new OpinioesSobreHotel(tela));
			}
		});
		btnFaturamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(new FaturamentoEServicos(tela));
			}
		});
		btnContratosEmAberto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Sistema.setTela(new ContratosEmAberto(tela));
			}
		});
		btnServicosDisponiveis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(new ServicosDisponiveis(tela));
			}
		});

	}

}
