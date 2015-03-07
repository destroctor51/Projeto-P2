package gui.servicos;

import gui.Sistema;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import core.hotel.Restaurante;
import core.servicos.alugaveis.Babysitter;
import core.servicos.alugaveis.CamaExtra;
import core.servicos.devolviveis.Carro;
import core.servicos.devolviveis.Quarto;
import core.servicos.devolviveis.TipoCarro;
import core.servicos.devolviveis.TipoQuarto;

public class AtualizarServico extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField tfNomeBs = new JTextField();
	private JTextField tfCodigo = new JTextField();
	private JFormattedTextField tfPlaca;
	private MaskFormatter ftmPlaca;
	private JTextField tfNumero = new JTextField();
	private JTextField tfNomeR = new JTextField();
	private final JLabel lbObs = new JLabel("<erro>");
	private JComboBox<TipoCarro> cbTipoCarro = new JComboBox<>();
	private JComboBox<TipoQuarto> cbTipoQuarto = new JComboBox<>();

	public AtualizarServico(final JPanel telaAnterior, final Object servico) {
		setName("Atualizar Servi\u00E7o");

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.1, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 0.25, 1.0};
		setLayout(gridBagLayout);

		final JPanel panel = new JPanel();
		panel.setBorder(new CompoundBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "<dynamic>", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)), new EmptyBorder(0, 20, 0, 20)));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 10, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		panel.setLayout(new CardLayout(0, 0));

		JPanel panel_ce = new JPanel();
		panel.add(panel_ce, "Cama Extra");
		GridBagLayout gbl_panel_ce = new GridBagLayout();
		gbl_panel_ce.columnWidths = new int[] {0, 0};
		gbl_panel_ce.rowHeights = new int[] {0, 0, 0};
		gbl_panel_ce.columnWeights = new double[]{0.0, 1.0};
		gbl_panel_ce.rowWeights = new double[]{1.0, 0.5, 1.0};
		panel_ce.setLayout(gbl_panel_ce);

		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		GridBagConstraints gbc_lblCdigo = new GridBagConstraints();
		gbc_lblCdigo.anchor = GridBagConstraints.EAST;
		gbc_lblCdigo.insets = new Insets(0, 0, 0, 10);
		gbc_lblCdigo.gridx = 0;
		gbc_lblCdigo.gridy = 1;
		panel_ce.add(lblCdigo, gbc_lblCdigo);


		tfCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		tfCodigo.setColumns(10);
		GridBagConstraints gbc_tfCodigo = new GridBagConstraints();
		gbc_tfCodigo.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfCodigo.gridx = 1;
		gbc_tfCodigo.gridy = 1;
		panel_ce.add(tfCodigo, gbc_tfCodigo);

		JPanel panel_q = new JPanel();
		panel.add(panel_q, "Quarto");
		GridBagLayout gbl_panel_q = new GridBagLayout();
		gbl_panel_q.columnWidths = new int[] {0, 0};
		gbl_panel_q.rowHeights = new int[] {0, 0, 0, 0};
		gbl_panel_q.columnWeights = new double[]{0.0, 1.0};
		gbl_panel_q.rowWeights = new double[]{1.0, 0.5, 0.5, 1.0};
		panel_q.setLayout(gbl_panel_q);

		JLabel lblNmero = new JLabel("N\u00FAmero:");
		GridBagConstraints gbc_lblNmero = new GridBagConstraints();
		gbc_lblNmero.anchor = GridBagConstraints.EAST;
		gbc_lblNmero.insets = new Insets(0, 0, 10, 10);
		gbc_lblNmero.gridx = 0;
		gbc_lblNmero.gridy = 1;
		panel_q.add(lblNmero, gbc_lblNmero);

		tfNumero.setColumns(10);
		GridBagConstraints gbc_tfNumero = new GridBagConstraints();
		gbc_tfNumero.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNumero.insets = new Insets(0, 0, 10, 0);
		gbc_tfNumero.gridx = 1;
		gbc_tfNumero.gridy = 1;
		panel_q.add(tfNumero, gbc_tfNumero);

		JLabel label_5 = new JLabel("Tipo de quarto:");
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.insets = new Insets(0, 0, 0, 10);
		gbc_label_5.anchor = GridBagConstraints.EAST;
		gbc_label_5.gridx = 0;
		gbc_label_5.gridy = 2;
		panel_q.add(label_5, gbc_label_5);
		cbTipoQuarto.setFocusable(false);

		cbTipoQuarto.setModel(new DefaultComboBoxModel<>(TipoQuarto.values()));
		GridBagConstraints gbc_cbTipoQuarto = new GridBagConstraints();
		gbc_cbTipoQuarto.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbTipoQuarto.gridx = 1;
		gbc_cbTipoQuarto.gridy = 2;
		panel_q.add(cbTipoQuarto, gbc_cbTipoQuarto);

		JPanel panel_r = new JPanel();
		panel.add(panel_r, "Restaurante");
		GridBagLayout gbl_panel_r = new GridBagLayout();
		gbl_panel_r.columnWidths = new int[] {0, 0};
		gbl_panel_r.rowHeights = new int[] {0, 0, 0};
		gbl_panel_r.columnWeights = new double[]{0.0, 1.0};
		gbl_panel_r.rowWeights = new double[]{1.0, 0.5, 1.0};
		panel_r.setLayout(gbl_panel_r);

		JLabel label_6 = new JLabel("Nome:");
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.anchor = GridBagConstraints.EAST;
		gbc_label_6.insets = new Insets(0, 0, 0, 10);
		gbc_label_6.gridx = 0;
		gbc_label_6.gridy = 1;
		panel_r.add(label_6, gbc_label_6);

		tfNomeR.setColumns(10);
		GridBagConstraints gbc_tfNomeR = new GridBagConstraints();
		gbc_tfNomeR.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNomeR.gridx = 1;
		gbc_tfNomeR.gridy = 1;
		panel_r.add(tfNomeR, gbc_tfNomeR);

		JPanel panel_bs = new JPanel();
		panel.add(panel_bs, "Babysitter");
		GridBagLayout gbl_panel_bs = new GridBagLayout();
		gbl_panel_bs.columnWidths = new int[] {0, 0};
		gbl_panel_bs.rowHeights = new int[] {0, 0, 0};
		gbl_panel_bs.columnWeights = new double[]{0.0, 1.0};
		gbl_panel_bs.rowWeights = new double[]{1.0, 0.5, 1.0};
		panel_bs.setLayout(gbl_panel_bs);

		JLabel label = new JLabel("Nome:");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 0, 10);
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		panel_bs.add(label, gbc_label);

		tfNomeBs.setColumns(10);
		GridBagConstraints gbc_tfNomeBs = new GridBagConstraints();
		gbc_tfNomeBs.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNomeBs.gridx = 1;
		gbc_tfNomeBs.gridy = 1;
		panel_bs.add(tfNomeBs, gbc_tfNomeBs);

		JPanel panel_c = new JPanel();
		panel.add(panel_c, "Carro");
		GridBagLayout gbl_panel_c = new GridBagLayout();
		gbl_panel_c.columnWidths = new int[] {0, 0};
		gbl_panel_c.rowHeights = new int[] {0, 0, 0, 0};
		gbl_panel_c.columnWeights = new double[]{0.0, 1.0};
		gbl_panel_c.rowWeights = new double[]{1.0, 0.5, 0.5, 1.0};
		panel_c.setLayout(gbl_panel_c);

		JLabel label_2 = new JLabel("Placa:");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.EAST;
		gbc_label_2.insets = new Insets(0, 0, 10, 10);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 1;
		panel_c.add(label_2, gbc_label_2);

		try {
			ftmPlaca = new MaskFormatter("UUU-####");
		} catch (ParseException e2) {
			lbObs.setText("Placa inv\u00E1lida");
			lbObs.setVisible(true);
		}

		tfPlaca = new JFormattedTextField(ftmPlaca);
		tfPlaca.setColumns(10);
		GridBagConstraints gbc_tfPlaca = new GridBagConstraints();
		gbc_tfPlaca.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfPlaca.insets = new Insets(0, 0, 10, 0);
		gbc_tfPlaca.gridx = 1;
		gbc_tfPlaca.gridy = 1;
		panel_c.add(tfPlaca, gbc_tfPlaca);

		JLabel label_3 = new JLabel("Tipo de carro:");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.insets = new Insets(0, 0, 0, 10);
		gbc_label_3.anchor = GridBagConstraints.EAST;
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 2;
		panel_c.add(label_3, gbc_label_3);

		selecionaCard(servico, panel, cbTipoCarro, cbTipoQuarto);
		cbTipoCarro.setFocusable(false);

		cbTipoCarro.setModel(new DefaultComboBoxModel<>(TipoCarro.values()));
		GridBagConstraints gbc_cbTipoCarro = new GridBagConstraints();
		gbc_cbTipoCarro.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbTipoCarro.gridx = 1;
		gbc_cbTipoCarro.gridy = 2;
		panel_c.add(cbTipoCarro, gbc_cbTipoCarro);

		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.anchor = GridBagConstraints.NORTH;
		gbc_panel_3.insets = new Insets(0, 0, 0, 5);
		gbc_panel_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 2;
		add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] {200, 0, 0};
		gbl_panel_3.rowHeights = new int[] {0};
		gbl_panel_3.columnWeights = new double[]{0.0, 1.0, 0.0};
		gbl_panel_3.rowWeights = new double[]{0.0};
		panel_3.setLayout(gbl_panel_3);
		GridBagConstraints gbc_lbObs = new GridBagConstraints();
		gbc_lbObs.insets = new Insets(0, 0, 0, 5);
		gbc_lbObs.anchor = GridBagConstraints.WEST;
		gbc_lbObs.gridx = 0;
		gbc_lbObs.gridy = 0;
		lbObs.setIcon(new ImageIcon(AtualizarServico.class.getResource("/gui/images/error.png")));
		panel_3.add(lbObs, gbc_lbObs);

		lbObs.setForeground(Color.RED);
		lbObs.setVisible(false);

		JButton btnCancelar = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.anchor = GridBagConstraints.EAST;
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 10);
		gbc_btnCancelar.gridx = 1;
		gbc_btnCancelar.gridy = 0;
		panel_3.add(btnCancelar, gbc_btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(telaAnterior);
			}
		});
		JButton btnConfirmar = new JButton("Confirmar");
		GridBagConstraints gbc_btnConfirmar = new GridBagConstraints();
		gbc_btnConfirmar.gridx = 2;
		gbc_btnConfirmar.gridy = 0;
		panel_3.add(btnConfirmar, gbc_btnConfirmar);
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (servico instanceof Babysitter) {
					try{

						if(tfNomeBs.getText() == null || tfNomeBs.getText().length() < 3)
							throw new IllegalArgumentException();

						//verifica se eh valido
						for(int i = 0; i < tfNomeBs.getText().length();i++)
							if (!Character.isAlphabetic(tfNomeBs.getText().charAt(i)) && tfNomeBs.getText().charAt(i) != ' ')
								throw new IllegalArgumentException();

						Babysitter bs = (Babysitter) servico;
						Sistema.getHotel().removeBaba(bs);
						Sistema.getHotel().adicionaBaba(tfNomeBs.getText());
						Sistema.setTela(telaAnterior);

					} catch(IllegalArgumentException iae){
						lbObs.setText("Nome deve ter no m\u00EDnimo 3 letras");
						lbObs.setVisible(true);
					} catch(Exception exc){
						lbObs.setText(exc.getMessage());
						lbObs.setVisible(true);
					}
				}
				if(servico instanceof CamaExtra){
					try {
						if(tfCodigo.getText() == null || Integer.parseInt(tfCodigo.getText()) < 0)
							throw new IllegalArgumentException();

						int codigo = Integer.parseInt(tfCodigo.getText());

						CamaExtra ce = (CamaExtra)servico;
						Sistema.getHotel().removeCamaExtra(ce);
						Sistema.getHotel().adicionaCamaExtra(codigo);
						Sistema.setTela(telaAnterior);

					} catch (IllegalArgumentException nfe) {
						lbObs.setText("O C\u00F3digo deve ser um inteiro positivo");
						lbObs.setVisible(true);

					} catch (Exception exc) {
						lbObs.setText(exc.getMessage());
						lbObs.setVisible(true);
					}
				}
				if (servico instanceof Carro){
					try {
						String placa = tfPlaca.getText();

						if (cbTipoCarro.getSelectedIndex() == -1)
							throw new Exception("Escolha um tipo de carro");

						Carro c = (Carro)servico;
						Sistema.getHotel().removeCarro(c);
						Sistema.getHotel().adicionaCarro((TipoCarro)cbTipoCarro.getSelectedItem(), placa);
						Sistema.setTela(telaAnterior);

					} catch (Exception exc) {
						lbObs.setText(exc.getMessage());
						lbObs.setVisible(true);
					}
				}
				if(servico instanceof Quarto){
					try {
						if(tfNumero.getText() == null)
							throw new IllegalArgumentException();

						int numero = Integer.parseInt(tfNumero.getText());

						if (cbTipoQuarto.getSelectedIndex() == -1)
							throw new Exception("Escolha um tipo de quarto");

						Quarto c = (Quarto)servico;
						Sistema.getHotel().removeQuarto(c);
						Sistema.getHotel().adicionaQuarto((TipoQuarto)cbTipoQuarto.getSelectedItem(),numero);
						Sistema.setTela(telaAnterior);

					} catch (NumberFormatException nfe) {
						lbObs.setText("N\u00FAmero deve ser um inteiro positivo");
						lbObs.setVisible(true);

					} catch (IllegalArgumentException nfe) {
						lbObs.setText("N\u00FAmero deve ser um inteiro  positivo");
						lbObs.setVisible(true);

					} catch (Exception exc) {
						lbObs.setText(exc.getMessage());
						lbObs.setVisible(true);
					}
				}
				if(servico instanceof Restaurante){
					try{
						if(tfNomeR.getText() == null)
							throw new IllegalArgumentException();
						//verifica se eh valido
						for(int i = 0; i < tfNomeR.getText().length();i++)
							if (!Character.isAlphabetic(tfNomeR.getText().charAt(i)) && tfNomeR.getText().charAt(i) != ' ')
								throw new IllegalArgumentException();

						if(tfNomeR.getText().length() < 3)
							throw new IllegalArgumentException();

						Restaurante r = (Restaurante)servico;
						Sistema.getHotel().removeRestaurante(r);
						Sistema.getHotel().adicionaRestaurante(tfNomeR.getText());
						Sistema.setTela(telaAnterior);

					}
					catch(IllegalArgumentException iae){
						lbObs.setText("Nome deve ter no m\u00EDnimo 3 letras");
						lbObs.setVisible(true);
					}
					catch(Exception exc){
						lbObs.setText(exc.getMessage());
						lbObs.setVisible(true);
					}
				}

			}
		});

	}

	private void selecionaCard(Object servico, final JPanel panel,
			JComboBox<TipoCarro> cbTipoCarro, JComboBox<TipoQuarto> cbTipoQuarto) {
		CardLayout cl = (CardLayout) panel.getLayout();
		if (servico instanceof Babysitter){
			cl.show(panel, "Babysitter");
			Babysitter bs = (Babysitter)servico;
			tfNomeBs.setText(bs.getNome());
		}

		else if (servico instanceof CamaExtra){
			cl.show(panel, "Cama Extra");
			CamaExtra ce = (CamaExtra)servico;
			tfCodigo.setText("" + ce.getCodigo());
		}

		else if (servico instanceof Carro){
			cl.show(panel, "Carro");
			Carro c = (Carro)servico;
			tfPlaca.setText(c.getPlaca());
			cbTipoCarro.setSelectedItem(c.getTipo());
		}

		else if (servico instanceof Quarto){
			cl.show(panel, "Quarto");
			Quarto q = (Quarto)servico;
			tfNumero.setText("" + q.getNumero());
			cbTipoQuarto.setSelectedItem(q.getTipo());
		}

		else if (servico instanceof Restaurante){
			cl.show(panel, "Restaurante");
			Restaurante r = (Restaurante)servico;
			tfNomeR.setText(r.getNome());
		}
	}
}