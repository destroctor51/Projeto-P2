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
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import core.servicos.devolviveis.TipoCarro;
import core.servicos.devolviveis.TipoQuarto;

public class AdicionarServico extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField tfNomeBs;
	private JTextField tfCodigo;
	private JFormattedTextField tfPlaca;
	private MaskFormatter ftmPlaca;	
	private JTextField tfNumero;
	private JTextField tfNomeR;
	private final JLabel lbObs = new JLabel("*Observa\u00E7\u00F5es");
	private final JPanel panel_card = new JPanel();
	JComboBox<TipoCarro> cbTipoCarro = new JComboBox<>();
	JComboBox<TipoQuarto> cbTipoQuarto = new JComboBox<>();

	/**
	 * Create the panel.
	 */
	public AdicionarServico(final String cbSelecionado,
			final JPanel telaAnterior) {
		setName("Adicionar Servi\u00E7o");

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.25, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.25, 1.0 };
		setLayout(gridBagLayout);
		GridBagConstraints gbc_panel_card = new GridBagConstraints();
		gbc_panel_card.fill = GridBagConstraints.BOTH;
		gbc_panel_card.insets = new Insets(0, 0, 10, 5);
		gbc_panel_card.gridx = 1;
		gbc_panel_card.gridy = 1;
		panel_card.setBorder(new CompoundBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), cbSelecionado,
				TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(
						0, 0, 0)), new EmptyBorder(0, 10, 0, 10)));
		add(panel_card, gbc_panel_card);
		panel_card.setLayout(new CardLayout(0, 0));

		JPanel panel_bs = new JPanel();
		panel_card.add(panel_bs, "Babysitter");
		GridBagLayout gbl_panel_bs = new GridBagLayout();
		gbl_panel_bs.columnWidths = new int[] { 85, 0 };
		gbl_panel_bs.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel_bs.columnWeights = new double[] { 0.0, 1.0 };
		gbl_panel_bs.rowWeights = new double[] { 1.0, 0.5, 1.0 };
		panel_bs.setLayout(gbl_panel_bs);

		JLabel lblNome = new JLabel("Nome:");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.insets = new Insets(0, 0, 0, 10);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 1;
		panel_bs.add(lblNome, gbc_lblNome);

		tfNomeBs = new JTextField();
		tfNomeBs.setColumns(10);
		GridBagConstraints gbc_tfNomeBs = new GridBagConstraints();
		gbc_tfNomeBs.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNomeBs.gridx = 1;
		gbc_tfNomeBs.gridy = 1;
		panel_bs.add(tfNomeBs, gbc_tfNomeBs);

		JPanel panel_ce = new JPanel();
		panel_card.add(panel_ce, "Cama Extra");
		GridBagLayout gbl_panel_ce = new GridBagLayout();
		gbl_panel_ce.columnWidths = new int[] { 85, 0 };
		gbl_panel_ce.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel_ce.columnWeights = new double[] { 0.0, 1.0 };
		gbl_panel_ce.rowWeights = new double[] { 1.0, 0.5, 1.0 };
		panel_ce.setLayout(gbl_panel_ce);

		JLabel lblCodigo = new JLabel("C\u00F3digo:");
		GridBagConstraints gbc_lblCodigo = new GridBagConstraints();
		gbc_lblCodigo.insets = new Insets(0, 0, 0, 10);
		gbc_lblCodigo.anchor = GridBagConstraints.EAST;
		gbc_lblCodigo.gridx = 0;
		gbc_lblCodigo.gridy = 1;
		panel_ce.add(lblCodigo, gbc_lblCodigo);

		tfCodigo = new JTextField();
		tfCodigo.setColumns(10);
		GridBagConstraints gbc_tfCodigo = new GridBagConstraints();
		gbc_tfCodigo.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfCodigo.gridx = 1;
		gbc_tfCodigo.gridy = 1;
		panel_ce.add(tfCodigo, gbc_tfCodigo);

		JPanel panel_c = new JPanel();
		panel_card.add(panel_c, "Carro");
		GridBagLayout gbl_panel_c = new GridBagLayout();
		gbl_panel_c.columnWidths = new int[] { 85, 0 };
		gbl_panel_c.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel_c.columnWeights = new double[] { 0.0, 1.0 };
		gbl_panel_c.rowWeights = new double[] { 1.0, 0.5, 0.5, 1.0 };
		panel_c.setLayout(gbl_panel_c);

		JLabel lblPlaca = new JLabel("Placa:");
		GridBagConstraints gbc_lblPlaca = new GridBagConstraints();
		gbc_lblPlaca.anchor = GridBagConstraints.EAST;
		gbc_lblPlaca.insets = new Insets(0, 0, 10, 10);
		gbc_lblPlaca.gridx = 0;
		gbc_lblPlaca.gridy = 1;
		panel_c.add(lblPlaca, gbc_lblPlaca);

		try {
			ftmPlaca = new MaskFormatter("UUU-####");
		} catch (ParseException e2) {
			lbObs.setText("Placa inv\u00E1lida");
			lbObs.setVisible(true);
		}

		tfPlaca = new JFormattedTextField(ftmPlaca);
		tfPlaca.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfPlaca.setColumns(10);
		GridBagConstraints gbc_tfPlaca = new GridBagConstraints();
		gbc_tfPlaca.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfPlaca.insets = new Insets(0, 0, 10, 0);
		gbc_tfPlaca.gridx = 1;
		gbc_tfPlaca.gridy = 1;
		panel_c.add(tfPlaca, gbc_tfPlaca);

		JLabel lblTipoDeCarro = new JLabel("Tipo de carro:");
		GridBagConstraints gbc_lblTipoDeCarro = new GridBagConstraints();
		gbc_lblTipoDeCarro.insets = new Insets(0, 0, 0, 10);
		gbc_lblTipoDeCarro.anchor = GridBagConstraints.EAST;
		gbc_lblTipoDeCarro.fill = GridBagConstraints.VERTICAL;
		gbc_lblTipoDeCarro.gridx = 0;
		gbc_lblTipoDeCarro.gridy = 2;
		panel_c.add(lblTipoDeCarro, gbc_lblTipoDeCarro);

		cbTipoCarro.setModel(new DefaultComboBoxModel<>(TipoCarro.values()));
		GridBagConstraints gbc_cbTipoCarro = new GridBagConstraints();
		gbc_cbTipoCarro.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbTipoCarro.gridx = 1;
		gbc_cbTipoCarro.gridy = 2;
		panel_c.add(cbTipoCarro, gbc_cbTipoCarro);

		JPanel panel_q = new JPanel();
		panel_card.add(panel_q, "Quarto");
		GridBagLayout gbl_panel_q = new GridBagLayout();
		gbl_panel_q.columnWidths = new int[] { 85, 0 };
		gbl_panel_q.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel_q.columnWeights = new double[] { 0.0, 1.0 };
		gbl_panel_q.rowWeights = new double[] { 1.0, 0.5, 0.5, 1.0 };
		panel_q.setLayout(gbl_panel_q);

		tfNumero = new JTextField();
		tfNumero.setColumns(10);
		GridBagConstraints gbc_tfNumero = new GridBagConstraints();
		gbc_tfNumero.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNumero.insets = new Insets(0, 0, 10, 0);
		gbc_tfNumero.gridx = 1;
		gbc_tfNumero.gridy = 1;
		panel_q.add(tfNumero, gbc_tfNumero);

		JLabel lblNumero = new JLabel("N\u00FAmero:");
		GridBagConstraints gbc_lblNumero = new GridBagConstraints();
		gbc_lblNumero.anchor = GridBagConstraints.EAST;
		gbc_lblNumero.insets = new Insets(0, 0, 10, 10);
		gbc_lblNumero.gridx = 0;
		gbc_lblNumero.gridy = 1;
		panel_q.add(lblNumero, gbc_lblNumero);

		JLabel lblTipoDeQuarto = new JLabel("Tipo de quarto:");
		GridBagConstraints gbc_lblTipoDeQuarto = new GridBagConstraints();
		gbc_lblTipoDeQuarto.insets = new Insets(0, 0, 0, 10);
		gbc_lblTipoDeQuarto.anchor = GridBagConstraints.EAST;
		gbc_lblTipoDeQuarto.gridx = 0;
		gbc_lblTipoDeQuarto.gridy = 2;
		panel_q.add(lblTipoDeQuarto, gbc_lblTipoDeQuarto);
		cbTipoQuarto.setModel(new DefaultComboBoxModel<>(TipoQuarto.values()));
		GridBagConstraints gbc_cbTipoQuarto = new GridBagConstraints();
		gbc_cbTipoQuarto.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbTipoQuarto.gridx = 1;
		gbc_cbTipoQuarto.gridy = 2;
		panel_q.add(cbTipoQuarto, gbc_cbTipoQuarto);

		JPanel panel_r = new JPanel();
		panel_card.add(panel_r, "Restaurante");
		GridBagLayout gbl_panel_r = new GridBagLayout();
		gbl_panel_r.columnWidths = new int[] { 85, 0 };
		gbl_panel_r.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel_r.columnWeights = new double[] { 0.0, 1.0 };
		gbl_panel_r.rowWeights = new double[] { 1.0, 0.5, 1.0 };
		panel_r.setLayout(gbl_panel_r);

		JLabel Nome = new JLabel("Nome:");
		GridBagConstraints gbc_Nome = new GridBagConstraints();
		gbc_Nome.insets = new Insets(0, 0, 0, 10);
		gbc_Nome.anchor = GridBagConstraints.EAST;
		gbc_Nome.gridx = 0;
		gbc_Nome.gridy = 1;
		panel_r.add(Nome, gbc_Nome);

		tfNomeR = new JTextField();
		tfNomeR.setColumns(10);
		GridBagConstraints gbc_tfNomeR = new GridBagConstraints();
		gbc_tfNomeR.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNomeR.gridx = 1;
		gbc_tfNomeR.gridy = 1;
		panel_r.add(tfNomeR, gbc_tfNomeR);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.NORTH;
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 2;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, 0.0, 0.0 };
		gbl_panel_1.rowWeights = new double[] { 0.0 };
		panel_1.setLayout(gbl_panel_1);
		GridBagConstraints gbc_lbObs = new GridBagConstraints();
		gbc_lbObs.fill = GridBagConstraints.BOTH;
		gbc_lbObs.insets = new Insets(0, 0, 0, 10);
		gbc_lbObs.gridx = 0;
		gbc_lbObs.gridy = 0;
		panel_1.add(lbObs, gbc_lbObs);
		lbObs.setForeground(Color.RED);
		lbObs.setVisible(false);

		JButton btnVoltar = new JButton("Cancelar");
		GridBagConstraints gbc_btnVoltar = new GridBagConstraints();
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
		GridBagConstraints gbc_btnAdicionar = new GridBagConstraints();
		gbc_btnAdicionar.gridx = 2;
		gbc_btnAdicionar.gridy = 0;
		panel_1.add(btnAdicionar, gbc_btnAdicionar);

		btnAdicionar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (cbSelecionado) {
				case "Babysitter":
					try {
						// verifica se eh valido
						boolean invalido = false;
						for (int i = 0; i < tfNomeBs.getText().length(); i++)
							if (!Character.isAlphabetic(tfNomeBs.getText()
									.charAt(i))
									&& tfNomeBs.getText().charAt(i) != ' ')
								invalido = true;

						if (invalido || tfNomeBs.getText().length() < 3)
							throw new IllegalArgumentException(
									"Nome deve ter no m\u00EDnimo 3 caract\u00E9res (apenas letras)");

						Sistema.getHotel().adicionaBaba(tfNomeBs.getText());
						Sistema.setTela(telaAnterior);

					} catch (Exception exc) {
						lbObs.setText(exc.getMessage());
						lbObs.setForeground(Color.RED);
						lbObs.setVisible(true);
					}
					break;

				case "Cama Extra":
					try {
						if (tfCodigo.getText() == null
								|| Integer.parseInt(tfCodigo.getText()) < 0)
							throw new IllegalArgumentException();
						int codigo = Integer.parseInt(tfCodigo.getText());

						Sistema.getHotel().adicionaCamaExtra(codigo);
						Sistema.setTela(telaAnterior);

					} catch (IllegalArgumentException nfe) {
						lbObs.setText("O C\u00F3digo deve ser um n\u00FAmero inteiro positivo");
						lbObs.setForeground(Color.RED);
						lbObs.setVisible(true);

					} catch (Exception exc) {
						lbObs.setText(exc.getMessage());
						lbObs.setForeground(Color.RED);
						lbObs.setVisible(true);
					}
					break;

				case "Carro":
					try {
						String placa = tfPlaca.getText();

						if (cbTipoCarro.getSelectedIndex() == -1)
							throw new Exception("Escolha um tipo de carro");

						Sistema.getHotel().adicionaCarro(
								(TipoCarro) cbTipoCarro.getSelectedItem(),
								placa);
						Sistema.setTela(telaAnterior);

					} catch (Exception exc) {
						lbObs.setText(exc.getMessage());
						lbObs.setForeground(Color.RED);
						lbObs.setVisible(true);
					}
					break;

				case "Quarto":
					try {
						if (tfNumero.getText() == null)
							throw new IllegalArgumentException();

						int numero = Integer.parseInt(tfNumero.getText());

						if (cbTipoQuarto.getSelectedIndex() == -1)
							throw new Exception("Escolha um tipo de quarto");

						Sistema.getHotel().adicionaQuarto(
								(TipoQuarto) cbTipoQuarto.getSelectedItem(),
								numero);
						Sistema.setTela(telaAnterior);

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

				case "Restaurante":
					try {
						// verifica se eh valido
						boolean invalido = false;
						for (int i = 0; i < tfNomeR.getText().length(); i++)
							if (!Character.isAlphabetic(tfNomeR.getText()
									.charAt(i))
									&& tfNomeR.getText().charAt(i) != ' ')
								invalido = true;

						if (invalido || tfNomeR.getText().length() < 3)
							throw new IllegalArgumentException(
									"Nome deve ter no m\u00EDnimo 3 caract\u00E9res (apenas letras)");

						Sistema.getHotel().adicionaRestaurante(
								tfNomeR.getText());
						Sistema.setTela(telaAnterior);

					} catch (Exception exc) {
						lbObs.setText(exc.getMessage());
						lbObs.setForeground(Color.RED);
						lbObs.setVisible(true);
					}
					break;
				}
			}
		});

		CardLayout cl = (CardLayout) panel_card.getLayout();
		cl.show(panel_card, cbSelecionado);
	}
}