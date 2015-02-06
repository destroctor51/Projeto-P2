package gui.refeicoes;

import gui.Sistema;
import hotel.Restaurante;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import servicos.pagaveis.Refeicao;

public class AtualizarRefeicao extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPanel telaAnterior;
	private Refeicao refeicao;
	private String nomeRestaurante;
	private final JLabel lbObs = new JLabel("*Observa\u00E7\u00F5es");
	private JTextField tfNome;
	private JTextField tfPreco;

	/**
	 * Create the panel.
	 */
	public AtualizarRefeicao(final JPanel telaAnterior,final String nomeRestaurante, final Refeicao refeicao) {
		setName("Atualizar Refeição");
		this.setTelaAnterior(telaAnterior);
		this.setRefeicao(refeicao);
		this.setNomeRestaurante(nomeRestaurante);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{450, 0};
		gridBagLayout.rowHeights = new int[]{205, 100, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EmptyBorder(0, 0, 0, 70));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.VERTICAL;
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 0;
		add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 42, 265, 0};
		gbl_panel_3.rowHeights = new int[]{80, 61, 0, 0, 0};
		gbl_panel_3.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		GridBagConstraints gbc_lbObs = new GridBagConstraints();
		gbc_lbObs.anchor = GridBagConstraints.SOUTH;
		gbc_lbObs.insets = new Insets(0, 0, 5, 0);
		gbc_lbObs.gridx = 2;
		gbc_lbObs.gridy = 1;
		panel_3.add(lbObs, gbc_lbObs);
		lbObs.setForeground(Color.RED);
		lbObs.setVisible(false);

		JLabel lblNome = new JLabel("Nome:");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 1;
		gbc_lblNome.gridy = 2;
		panel_3.add(lblNome, gbc_lblNome);

		tfNome = new JTextField(refeicao.getDescricao());
		GridBagConstraints gbc_tfNome = new GridBagConstraints();
		gbc_tfNome.insets = new Insets(0, 0, 5, 0);
		gbc_tfNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNome.gridx = 2;
		gbc_tfNome.gridy = 2;
		panel_3.add(tfNome, gbc_tfNome);
		tfNome.setColumns(10);

		JLabel lblNewLabel = new JLabel("Pre\u00E7o:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 3;
		panel_3.add(lblNewLabel, gbc_lblNewLabel);

		tfPreco = new JTextField(refeicao.getPreco() + "");
		GridBagConstraints gbc_tfPreco = new GridBagConstraints();
		gbc_tfPreco.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfPreco.gridx = 2;
		gbc_tfPreco.gridy = 3;
		panel_3.add(tfPreco, gbc_tfPreco);
		tfPreco.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setName("Atualizar Refei\u00E7\u00E3o");
		panel_1.setBorder(new EmptyBorder(50, 0, 0, 0));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{66, 168, 206, 79, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);

		JButton btnVoltar = new JButton("Voltar");
		GridBagConstraints gbc_btnVoltar = new GridBagConstraints();
		gbc_btnVoltar.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnVoltar.insets = new Insets(0, 0, 5, 5);
		gbc_btnVoltar.gridx = 1;
		gbc_btnVoltar.gridy = 0;
		panel_1.add(btnVoltar, gbc_btnVoltar);
		JButton btnAdicionar = new JButton("Atualizar");
		GridBagConstraints gbc_btnAdicionar = new GridBagConstraints();
		gbc_btnAdicionar.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnAdicionar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdicionar.gridx = 2;
		gbc_btnAdicionar.gridy = 0;
		panel_1.add(btnAdicionar, gbc_btnAdicionar);

		btnAdicionar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (tfNome.getText() == null || tfNome.getText().length() < 3)
						throw new IllegalArgumentException();
					for (int i = 0; i < tfNome.getText().length(); i++)
						if (!Character.isAlphabetic(tfNome.getText().charAt(i))
								&& tfNome.getText().charAt(i) != ' ')
							throw new IllegalArgumentException();
					float preco = (float) Double.parseDouble(tfPreco.getText());

					if(preco <= 0)
						throw new NumberFormatException();

					for(Restaurante r: Sistema.getHotel().getRestaurantes())
						if(r.getNome().equals(nomeRestaurante))
							for(Refeicao re: r.getEstoque())
								if(re.equals(refeicao)) {
									r.removeRefeicao(re);
									r.cadastraRefeicao(tfNome.getText(), preco);
								}

					lbObs.setText("Refei\u00E7\u00E3o atualizada com sucesso");
					lbObs.setForeground(new Color(0, 180, 0));
					lbObs.setVisible(true);
				} catch(NumberFormatException nfe) {
					lbObs.setText("O pre\u00E7o deve ser um numero positivo");
					lbObs.setForeground(Color.RED);
					lbObs.setVisible(true);
				} catch(IllegalArgumentException iae) {
					lbObs.setText("Nome deve ter no m\u00EDnimo 3 caracteres(apenas letras)");
					lbObs.setForeground(Color.RED);
					lbObs.setVisible(true);
				} catch(Exception exc) {
					lbObs.setText(exc.getMessage());
					lbObs.setForeground(Color.RED);
					lbObs.setVisible(true);
				}

			}
		});
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Sistema.setTela(telaAnterior);
			}
		});
	}
	public JPanel getTelaAnterior() {
		return telaAnterior;
	}
	public void setTelaAnterior(JPanel telaAnterior) {
		this.telaAnterior = telaAnterior;
	}
	public Refeicao getRefeicao() {
		return refeicao;
	}
	public void setRefeicao(Refeicao refeicao) {
		this.refeicao = refeicao;
	}
	public String getNomeRestaurante() {
		return nomeRestaurante;
	}
	public void setNomeRestaurante(String nomeRestaurante) {
		this.nomeRestaurante = nomeRestaurante;
	}
}
