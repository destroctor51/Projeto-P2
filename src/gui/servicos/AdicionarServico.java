package gui.servicos;

import gui.Sistema;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;

import servicos.devolviveis.TipoCarro;
import servicos.devolviveis.TipoQuarto;

public class AdicionarServico extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPanel telaAnterior;
	private JTextField tfNomeBs;
	private JTextField tfCodigo;
	private JTextField tfPlaca;
	private JTextField tfNumero;
	private JTextField tfNomeR;
	private final JLabel lbObs = new JLabel("*Observa\u00E7\u00F5es");
	private final JComboBox<Object> cbServicos = new JComboBox<Object>();
	private final JPanel panel_card = new JPanel();
	JComboBox<Object> cbTipoCarro = new JComboBox<Object>();
	JComboBox<Object> cbTipoQuarto = new JComboBox<Object>();

	/**
	 * Create the panel.
	 */
	public AdicionarServico(Object cbSelecionado,final JPanel telaAnterior) {
		setName("Adicionar Servi\u00E7o");

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{450, 0};
		gridBagLayout.rowHeights = new int[]{160, 30, 111, 76, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{112, 191, 155, 0};
		gbl_panel.rowHeights = new int[]{15, 30, 45, 46, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		JButton btnAdicionar = new JButton("Adicionar");

		JLabel lblAdicionarServios = new JLabel("");
		lblAdicionarServios.setFont(new Font("Dialog", Font.PLAIN, 20));
		GridBagConstraints gbc_lblAdicionarServios = new GridBagConstraints();
		gbc_lblAdicionarServios.insets = new Insets(0, 0, 5, 0);
		gbc_lblAdicionarServios.gridx = 1;
		gbc_lblAdicionarServios.gridy = 1;
		panel.add(lblAdicionarServios, gbc_lblAdicionarServios);

		JLabel lblServio = new JLabel("Servi\u00E7o:");
		GridBagConstraints gbc_lblServio = new GridBagConstraints();
		gbc_lblServio.anchor = GridBagConstraints.EAST;
		gbc_lblServio.fill = GridBagConstraints.VERTICAL;
		gbc_lblServio.insets = new Insets(0, 0, 0, 5);
		gbc_lblServio.gridx = 0;
		gbc_lblServio.gridy = 3;
		panel.add(lblServio, gbc_lblServio);

		panel_card.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_panel_card = new GridBagConstraints();
		gbc_panel_card.anchor = GridBagConstraints.NORTH;
		gbc_panel_card.insets = new Insets(0, 0, 5, 0);
		gbc_panel_card.gridx = 0;
		gbc_panel_card.gridy = 2;
		add(panel_card, gbc_panel_card);
		panel_card.setLayout(new CardLayout(0, 0));

		cbServicos.setModel(new DefaultComboBoxModel<Object>(new String[] {"Babysitter", "Cama Extra", "Carro", "Quarto", "Restaurante"}));
		cbServicos.setSelectedItem(cbSelecionado);
		GridBagConstraints gbc_cbServicos = new GridBagConstraints();
		gbc_cbServicos.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbServicos.insets = new Insets(0, 0, 0, 5);
		gbc_cbServicos.gridx = 1;
		gbc_cbServicos.gridy = 3;
		panel.add(cbServicos, gbc_cbServicos);


		// Codigo do comboBox esta aqui por causa que o JPanel tem que ja esta
		// criado
		cbServicos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lbObs.setVisible(false);
				CardLayout cl = (CardLayout) panel_card.getLayout();
				String selecionado = (String) cbServicos.getSelectedItem();
				cl.show(panel_card, selecionado);
			}
		});
		GridBagConstraints gbc_btnAdicionar = new GridBagConstraints();
		gbc_btnAdicionar.gridx = 2;
		gbc_btnAdicionar.gridy = 3;
		panel.add(btnAdicionar, gbc_btnAdicionar);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(null);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);



		GridBagConstraints gbc_lbObs = new GridBagConstraints();
		gbc_lbObs.anchor = GridBagConstraints.SOUTH;
		gbc_lbObs.gridx = 0;
		gbc_lbObs.gridy = 0;
		panel_2.add(lbObs, gbc_lbObs);
		lbObs.setForeground(Color.RED);
		lbObs.setVisible(false);


		JPanel panel_bs = new JPanel();
		panel_bs.setBorder(new EmptyBorder(0, 0, 0, 70));
		panel_card.add(panel_bs, "Babysitter");
		GridBagLayout gbl_panel_bs = new GridBagLayout();
		gbl_panel_bs.columnWidths = new int[]{117, 278, 0};
		gbl_panel_bs.rowHeights = new int[]{81, 0};
		gbl_panel_bs.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_bs.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_bs.setLayout(gbl_panel_bs);

		JLabel lblNome = new JLabel("Nome:");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.insets = new Insets(0, 0, 0, 5);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		panel_bs.add(lblNome, gbc_lblNome);

		tfNomeBs = new JTextField();
		tfNomeBs.setColumns(10);
		GridBagConstraints gbc_tfNomeBs = new GridBagConstraints();
		gbc_tfNomeBs.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNomeBs.gridx = 1;
		gbc_tfNomeBs.gridy = 0;
		panel_bs.add(tfNomeBs, gbc_tfNomeBs);

		JPanel panel_ce = new JPanel();
		panel_ce.setBorder(new EmptyBorder(0, 0, 0, 70));
		panel_card.add(panel_ce, "Cama Extra");
		GridBagLayout gbl_panel_ce = new GridBagLayout();
		gbl_panel_ce.columnWidths = new int[]{97, 261, 0};
		gbl_panel_ce.rowHeights = new int[]{56, 0};
		gbl_panel_ce.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_ce.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_ce.setLayout(gbl_panel_ce);

		JLabel lblCodigo = new JLabel("C\u00F3digo:");
		GridBagConstraints gbc_lblCodigo = new GridBagConstraints();
		gbc_lblCodigo.insets = new Insets(0, 0, 0, 5);
		gbc_lblCodigo.anchor = GridBagConstraints.EAST;
		gbc_lblCodigo.gridx = 0;
		gbc_lblCodigo.gridy = 0;
		panel_ce.add(lblCodigo, gbc_lblCodigo);

		tfCodigo = new JTextField();
		tfCodigo.setColumns(10);
		GridBagConstraints gbc_tfCodigo = new GridBagConstraints();
		gbc_tfCodigo.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfCodigo.gridx = 1;
		gbc_tfCodigo.gridy = 0;
		panel_ce.add(tfCodigo, gbc_tfCodigo);

		JPanel panel_c = new JPanel();
		panel_c.setBorder(new EmptyBorder(0, 0, 0, 70));
		panel_card.add(panel_c, "Carro");
		GridBagLayout gbl_panel_c = new GridBagLayout();
		gbl_panel_c.columnWidths = new int[]{107, 268, 0};
		gbl_panel_c.rowHeights = new int[]{30, 32, 0};
		gbl_panel_c.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_c.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panel_c.setLayout(gbl_panel_c);

		JLabel lblPlaca = new JLabel("Placa:");
		GridBagConstraints gbc_lblPlaca = new GridBagConstraints();
		gbc_lblPlaca.anchor = GridBagConstraints.EAST;
		gbc_lblPlaca.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlaca.gridx = 0;
		gbc_lblPlaca.gridy = 0;
		panel_c.add(lblPlaca, gbc_lblPlaca);

		tfPlaca = new JTextField();
		tfPlaca.setFont(new Font("Dialog", Font.PLAIN, 12));
		tfPlaca.setColumns(10);
		GridBagConstraints gbc_tfPlaca = new GridBagConstraints();
		gbc_tfPlaca.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfPlaca.insets = new Insets(0, 0, 5, 0);
		gbc_tfPlaca.gridx = 1;
		gbc_tfPlaca.gridy = 0;
		panel_c.add(tfPlaca, gbc_tfPlaca);

		JLabel lblTipoDeCarro = new JLabel("Tipo de carro:");
		GridBagConstraints gbc_lblTipoDeCarro = new GridBagConstraints();
		gbc_lblTipoDeCarro.anchor = GridBagConstraints.EAST;
		gbc_lblTipoDeCarro.fill = GridBagConstraints.VERTICAL;
		gbc_lblTipoDeCarro.gridx = 0;
		gbc_lblTipoDeCarro.gridy = 1;
		panel_c.add(lblTipoDeCarro, gbc_lblTipoDeCarro);

		cbTipoCarro.setModel(new DefaultComboBoxModel<Object>(TipoCarro.values()));
		GridBagConstraints gbc_cbTipoCarro = new GridBagConstraints();
		gbc_cbTipoCarro.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbTipoCarro.gridx = 1;
		gbc_cbTipoCarro.gridy = 1;
		panel_c.add(cbTipoCarro, gbc_cbTipoCarro);

		JPanel panel_q = new JPanel();
		panel_q.setBorder(new EmptyBorder(0, 0, 0, 70));
		panel_card.add(panel_q, "Quarto");
		GridBagLayout gbl_panel_q = new GridBagLayout();
		gbl_panel_q.columnWidths = new int[]{126, 286, 0};
		gbl_panel_q.rowHeights = new int[]{27, 24, 0};
		gbl_panel_q.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_q.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel_q.setLayout(gbl_panel_q);

		tfNumero = new JTextField();
		tfNumero.setColumns(10);
		GridBagConstraints gbc_tfNumero = new GridBagConstraints();
		gbc_tfNumero.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNumero.insets = new Insets(0, 0, 5, 0);
		gbc_tfNumero.gridx = 1;
		gbc_tfNumero.gridy = 0;
		panel_q.add(tfNumero, gbc_tfNumero);

		JLabel lblNumero = new JLabel("N\u00FAmero:");
		GridBagConstraints gbc_lblNumero = new GridBagConstraints();
		gbc_lblNumero.anchor = GridBagConstraints.EAST;
		gbc_lblNumero.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumero.gridx = 0;
		gbc_lblNumero.gridy = 0;
		panel_q.add(lblNumero, gbc_lblNumero);

		JLabel lblTipoDeQuarto = new JLabel("Tipo de quarto:");
		GridBagConstraints gbc_lblTipoDeQuarto = new GridBagConstraints();
		gbc_lblTipoDeQuarto.anchor = GridBagConstraints.EAST;
		gbc_lblTipoDeQuarto.gridx = 0;
		gbc_lblTipoDeQuarto.gridy = 1;
		panel_q.add(lblTipoDeQuarto, gbc_lblTipoDeQuarto);
		cbTipoQuarto.setModel(new DefaultComboBoxModel<Object>(TipoQuarto.values()));
		GridBagConstraints gbc_cbTipoQuarto = new GridBagConstraints();
		gbc_cbTipoQuarto.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbTipoQuarto.gridx = 1;
		gbc_cbTipoQuarto.gridy = 1;
		panel_q.add(cbTipoQuarto, gbc_cbTipoQuarto);

		JPanel panel_r = new JPanel();
		panel_r.setBorder(new EmptyBorder(0, 0, 0, 70));
		panel_card.add(panel_r, "Restaurante");
		GridBagLayout gbl_panel_r = new GridBagLayout();
		gbl_panel_r.columnWidths = new int[]{131, 270, 0};
		gbl_panel_r.rowHeights = new int[]{46, 0};
		gbl_panel_r.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_r.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_r.setLayout(gbl_panel_r);

		JLabel Nome = new JLabel("Nome:");
		GridBagConstraints gbc_Nome = new GridBagConstraints();
		gbc_Nome.insets = new Insets(0, 0, 0, 5);
		gbc_Nome.anchor = GridBagConstraints.EAST;
		gbc_Nome.gridx = 0;
		gbc_Nome.gridy = 0;
		panel_r.add(Nome, gbc_Nome);

		tfNomeR = new JTextField();
		tfNomeR.setColumns(10);
		GridBagConstraints gbc_tfNomeR = new GridBagConstraints();
		gbc_tfNomeR.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNomeR.gridx = 1;
		gbc_tfNomeR.gridy = 0;
		panel_r.add(tfNomeR, gbc_tfNomeR);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 3;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);

		JButton btnVoltar = new JButton("Voltar");
		GridBagConstraints gbc_btnVoltar = new GridBagConstraints();
		gbc_btnVoltar.insets = new Insets(0, 0, 5, 5);
		gbc_btnVoltar.gridx = 1;
		gbc_btnVoltar.gridy = 0;
		panel_1.add(btnVoltar, gbc_btnVoltar);
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Sistema.setTela(telaAnterior);
			}
		});

		btnAdicionar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selected = cbServicos.getSelectedIndex();
				if (selected != -1)
					checarAcao(selected);
				else{
					lbObs.setText("Nenhum servi\u00E7o selecionado");
					lbObs.setForeground(Color.RED);
					lbObs.setVisible(true);
				}
			}

			private void checarAcao(int selected) {
				switch (selected) {

					case 0:
						try {
							// verifica se eh valido
							for (int i = 0; i < tfNomeBs.getText().length(); i++)
								if (!Character.isAlphabetic(tfNomeBs.getText()
										.charAt(i))
										&& tfNomeBs.getText().charAt(i) != ' ')
									throw new IllegalArgumentException();

							if (tfNomeBs.getText().length() < 3)
								throw new IllegalArgumentException();

							Sistema.getHotel().adicionaBaba(tfNomeBs.getText());

							lbObs.setText("Babysitter adicionada com sucesso");
							lbObs.setForeground(new Color(0, 180, 0));
							lbObs.setVisible(true);
							lbObs.setVisible(false);
							lbObs.setVisible(true);

							tfNomeBs.setText("");
						} catch (IllegalArgumentException iae) {
							lbObs.setText("Nome deve ter no m\u00EDnimo 3 caracteres(apenas letras)");
							lbObs.setForeground(Color.RED);
							lbObs.setVisible(true);
						} catch (Exception exc) {
							lbObs.setText(exc.getMessage());
							lbObs.setForeground(Color.RED);
							lbObs.setVisible(true);
						}
						break;
					case 1:
						try {
							if(tfCodigo.getText() == null || Integer.parseInt(tfCodigo.getText()) < 0)
								throw new IllegalArgumentException();
							int codigo = Integer.parseInt(tfCodigo.getText());

							Sistema.getHotel().adicionaCamaExtra(codigo);

							lbObs.setText("Cama Extra adicionada com sucesso");
							lbObs.setForeground(new Color(0, 180, 0));
							lbObs.setVisible(true);
							lbObs.setVisible(false);
							lbObs.setVisible(true);

							tfCodigo.setText("");
						} catch (NumberFormatException nfe) {
							lbObs.setText("O C\u00F3digo deve ser um n\u00FAmero inteiro positivo");
							lbObs.setForeground(Color.RED);
							lbObs.setVisible(true);

						} catch (IllegalArgumentException iae) {
							lbObs.setText("O C\u00F3digo deve ser n\u00FAmero inteiro positivo");
							lbObs.setForeground(Color.RED);
							lbObs.setVisible(true);

						} catch (Exception exc) {
							lbObs.setText(exc.getMessage());
							lbObs.setForeground(Color.RED);
							lbObs.setVisible(true);
						}
						break;

					case 2:
						try {

							String placa = tfPlaca.getText();

							if (cbTipoCarro.getSelectedIndex() == -1)
								throw new Exception("Escolha um tipo de carro");

							Sistema.getHotel().adicionaCarro(
									(TipoCarro) cbTipoCarro.getSelectedItem(),
									placa);

							lbObs.setText("Carro adicionado com sucesso");
							lbObs.setForeground(new Color(0, 180, 0));
							lbObs.setVisible(true);

							tfPlaca.setText("");
						} catch (IllegalArgumentException iae) {
							lbObs.setText("Placa inv\u00E1lida (ex: ABC1234)");
							lbObs.setForeground(Color.RED);
							lbObs.setVisible(true);

						} catch (Exception exc) {
							lbObs.setText(exc.getMessage());
							lbObs.setForeground(Color.RED);
							lbObs.setVisible(true);
						}
						break;

					case 3:
						try {

							if(tfNumero.getText() == null)
								throw new IllegalArgumentException();

							int numero = Integer.parseInt(tfNumero.getText());

							if (cbTipoQuarto.getSelectedIndex() == -1)
								throw new Exception("Escolha um tipo de quarto");

							Sistema.getHotel().adicionaQuarto(
									(TipoQuarto) cbTipoQuarto.getSelectedItem(),
									numero);

							lbObs.setText("Quarto adicionado com sucesso");
							lbObs.setForeground(new Color(0, 180, 0));
							lbObs.setVisible(true);

							tfNumero.setText("");
						} catch (NumberFormatException nfe) {
							lbObs.setText("N\u00FAmero deve ser positivo");
							lbObs.setForeground(Color.RED);
							lbObs.setVisible(true);

						} catch (IllegalArgumentException nfe) {
							lbObs.setText("N\u00FAmero deve ser positivo");
							lbObs.setForeground(Color.RED);
							lbObs.setVisible(true);

						} catch (Exception exc) {
							lbObs.setText(exc.getMessage());
							lbObs.setForeground(Color.RED);
							lbObs.setVisible(true);
						}
						break;

					case 4:
						try {
							// verifica se eh valido
							for (int i = 0; i < tfNomeR.getText().length(); i++)
								if (!Character.isAlphabetic(tfNomeR.getText()
										.charAt(i))
										&& tfNomeR.getText().charAt(i) != ' ')
									throw new IllegalArgumentException();

							if (tfNomeR.getText().length() < 3)
								throw new IllegalArgumentException();

							Sistema.getHotel().adicionaRestaurante(tfNomeR.getText());

							lbObs.setText("Restaurante adicionado com sucesso");
							lbObs.setForeground(new Color(0, 180, 0));
							lbObs.setVisible(true);
							lbObs.setVisible(false);
							lbObs.setVisible(true);

							tfNomeR.setText("");
						} catch (IllegalArgumentException iae) {
							lbObs.setText("Nome deve ter no m\u00EDnimo 3 caracteres(apenas letras)");
							lbObs.setForeground(Color.RED);
							lbObs.setVisible(true);
						} catch (Exception exc) {
							lbObs.setText(exc.getMessage());
							lbObs.setForeground(Color.RED);
							lbObs.setVisible(true);
						}
						break;
				}
			}
		});


		this.setTelaAnterior(telaAnterior);
		CardLayout cl = (CardLayout) panel_card.getLayout();
		cbServicos.setSelectedItem(cbSelecionado);
		cl.show(panel_card, (String)cbSelecionado);
	}


	public JPanel getTelaAnterior() {
		return telaAnterior;
	}
	public void setTelaAnterior(JPanel telaAnterior) {
		this.telaAnterior = telaAnterior;
	}
}
