package gui.refeicoes;

import gui.Sistema;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import core.hotel.Restaurante;

public class AdicionarRefeicao extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPanel telaAnterior;
	private String nomeRestaurante;
	private final JLabel lbObs = new JLabel("<erro>");
	private JTextField tfNome;
	private JTextField tfPreco;

	public AdicionarRefeicao(final JPanel telaAnterior,final String nomeRestaurante) {
		setName("Adicionar Refei\u00E7\u00E3o");
		this.setTelaAnterior(telaAnterior);
		this.setNomeRestaurante(nomeRestaurante);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.25, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 0.25, 1.0};
		setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		panel.setBorder(new CompoundBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), nomeRestaurante, TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)), new EmptyBorder(0, 10, 0, 10)));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 10, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {};
		gbl_panel.rowHeights = new int[] {0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0};
		gbl_panel.rowWeights = new double[]{1.0, 0.5, 0.5, 1.0};
		panel.setLayout(gbl_panel);

		JLabel lblNome = new JLabel("Nome:");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.insets = new Insets(0, 0, 10, 10);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 1;
		panel.add(lblNome, gbc_lblNome);

		tfNome = new JTextField();
		GridBagConstraints gbc_tfNome = new GridBagConstraints();
		gbc_tfNome.insets = new Insets(0, 0, 10, 0);
		gbc_tfNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNome.gridx = 1;
		gbc_tfNome.gridy = 1;
		panel.add(tfNome, gbc_tfNome);
		tfNome.setColumns(10);

		JLabel lblNewLabel = new JLabel("Pre\u00E7o:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 10);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		tfPreco = new JTextField();
		GridBagConstraints gbc_tfPreco = new GridBagConstraints();
		gbc_tfPreco.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfPreco.gridx = 1;
		gbc_tfPreco.gridy = 2;
		panel.add(tfPreco, gbc_tfPreco);
		tfPreco.setColumns(10);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.NORTH;
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 2;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {180, 0, 0};
		gbl_panel_1.rowHeights = new int[] {0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0.0};
		gbl_panel_1.rowWeights = new double[]{0.0};
		panel_1.setLayout(gbl_panel_1);
		GridBagConstraints gbc_lbObs = new GridBagConstraints();
		gbc_lbObs.anchor = GridBagConstraints.WEST;
		gbc_lbObs.insets = new Insets(0, 2, 0, 5);
		gbc_lbObs.gridx = 0;
		gbc_lbObs.gridy = 0;
		lbObs.setIcon(new ImageIcon(AdicionarRefeicao.class.getResource("/gui/resources/error.png")));
		panel_1.add(lbObs, gbc_lbObs);
		lbObs.setForeground(Color.RED);
		lbObs.setVisible(false);

		JButton btnVoltar = new JButton("Cancelar");
		btnVoltar.setFocusable(false);
		GridBagConstraints gbc_btnVoltar = new GridBagConstraints();
		gbc_btnVoltar.anchor = GridBagConstraints.EAST;
		gbc_btnVoltar.insets = new Insets(0, 0, 0, 10);
		gbc_btnVoltar.gridx = 1;
		gbc_btnVoltar.gridy = 0;
		panel_1.add(btnVoltar, gbc_btnVoltar);
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Sistema.setTela(telaAnterior);
			}
		});
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setFocusable(false);
		GridBagConstraints gbc_btnAdicionar = new GridBagConstraints();
		gbc_btnAdicionar.insets = new Insets(0, 0, 0, 1);
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

					NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
					Number number = format.parse(tfPreco.getText());
					float preco = number.floatValue();

					if(preco <= 0)
						throw new NumberFormatException();

					for(Restaurante r: Sistema.getHotel().getRestaurantes())
						if(r.getNome().equals(nomeRestaurante))
							r.cadastraRefeicao(tfNome.getText(), preco);

					Sistema.setTela(telaAnterior);
				} catch(ParseException nfe) {
					lbObs.setText("O pre\u00E7o deve ser um real positivo");
					lbObs.setVisible(true);
				} catch(IllegalArgumentException iae) {
					lbObs.setText("Nome deve ter no m\u00EDnimo 3 letras");
					lbObs.setVisible(true);
				} catch(Exception exc) {
					lbObs.setText(exc.getMessage());
					lbObs.setVisible(true);
				}

			}
		});
	}
	public JPanel getTelaAnterior() {
		return telaAnterior;
	}
	public void setTelaAnterior(JPanel telaAnterior) {
		this.telaAnterior = telaAnterior;
	}
	public String getNomeRestaurante() {
		return nomeRestaurante;
	}
	public void setNomeRestaurante(String nomeRestaurante) {
		this.nomeRestaurante = nomeRestaurante;
	}
}