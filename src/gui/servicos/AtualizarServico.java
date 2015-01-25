package gui.servicos;

import gui.Sistema;
import hotel.Restaurante;

import java.awt.CardLayout;
import java.awt.Color;
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
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;

import servicos.alugaveis.Babysitter;
import servicos.alugaveis.CamaExtra;
import servicos.devolviveis.Carro;
import servicos.devolviveis.Quarto;
import servicos.devolviveis.TipoCarro;
import servicos.devolviveis.TipoQuarto;

public class AtualizarServico extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPanel telaAnterior;
	private Object servico;
	private JTextField tfNomeBs = new JTextField();
	private JTextField tfCodigo = new JTextField();
	private JTextField tfPlaca = new JTextField();
	private JTextField tfNumero = new JTextField();
	private JTextField tfNomeR = new JTextField();
	private final JLabel lbObs = new JLabel("*Observa\u00E7\u00F5es");
	private JComboBox<Object> cbTipoCarro = new JComboBox<>();
	private JComboBox<Object> cbTipoQuarto = new JComboBox<>();

	/**
	 * Create the panel.
	 */
	public AtualizarServico(final JPanel telaAnterior,Object servico) {
		setName("Atualizar Servi\u00E7o");

		this.setTelaAnterior(telaAnterior);
		this.servico = servico;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{119, 0};
		gridBagLayout.rowHeights = new int[]{76, 109, 113, 61, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{288, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 59, 0, 41, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		JButton btnConfirmar = new JButton("Confirmar");
		GridBagConstraints gbc_btnConfirmar = new GridBagConstraints();
		gbc_btnConfirmar.insets = new Insets(0, 0, 5, 0);
		gbc_btnConfirmar.gridx = 0;
		gbc_btnConfirmar.gridy = 3;
		panel_1.add(btnConfirmar, gbc_btnConfirmar);
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (getServico() instanceof Babysitter) {
					try{

						if(tfNomeBs.getText() == null || tfNomeBs.getText().length() < 3)
							throw new IllegalArgumentException();

						//verifica se eh valido
						for(int i = 0; i < tfNomeBs.getText().length();i++)
							if (!Character.isAlphabetic(tfNomeBs.getText().charAt(i)) && tfNomeBs.getText().charAt(i) != ' ')
								throw new IllegalArgumentException();


						Babysitter bs = (Babysitter)getServico();
						Sistema.getHotel().removeBaba(bs);
						Sistema.getHotel().adicionaBaba(tfNomeBs.getText());
						setServico(new Babysitter(tfNomeBs.getText()));

						lbObs.setText("Babysitter atualizada com sucesso");
						lbObs.setForeground(new Color(0, 180, 0));
						lbObs.setVisible(true);
					} catch(IllegalArgumentException iae){
						lbObs.setText("Nome deve ter no m\u00EDnimo 3 caracteres(apenas letras)");
						lbObs.setForeground(Color.RED);
						lbObs.setVisible(true);
					} catch(Exception exc){
						lbObs.setText(exc.getMessage());
						lbObs.setForeground(Color.RED);
						lbObs.setVisible(true);
					}
				}
				if(getServico() instanceof CamaExtra){
					try {
						if(tfCodigo.getText() == null || Integer.parseInt(tfCodigo.getText()) < 0)
							throw new IllegalArgumentException();

						int codigo = Integer.parseInt(tfCodigo.getText());

						CamaExtra ce = (CamaExtra)getServico();
						Sistema.getHotel().removeCamaExtra(ce);
						Sistema.getHotel().adicionaCamaExtra(codigo);
						setServico(new CamaExtra(codigo));

						lbObs.setText("Cama Extra atualizada com sucesso");
						lbObs.setForeground(new Color(0, 180, 0));
						lbObs.setVisible(true);
						lbObs.setVisible(false);
						lbObs.setVisible(true);

					} catch (NumberFormatException nfe) {
						lbObs.setText("O C\u00F3digo deve ser um n\u00FAmero inteiro positivo");
						lbObs.setForeground(Color.RED);
						lbObs.setVisible(true);

					} catch (IllegalArgumentException iae) {
						lbObs.setText("O C\u00F3digo deve ser um n\u00FAmero inteiro positivo");
						lbObs.setForeground(Color.RED);
						lbObs.setVisible(true);

					} catch (Exception exc) {
						lbObs.setText(exc.getMessage());
						lbObs.setForeground(Color.RED);
						lbObs.setVisible(true);
					}
				}
				if (getServico() instanceof Carro){
					try {
						String placa = tfPlaca.getText();

						if (cbTipoCarro.getSelectedIndex() == -1)
							throw new Exception("Escolha um tipo de carro");

						Carro c = (Carro)getServico();
						Sistema.getHotel().removeCarro(c);
						Sistema.getHotel().adicionaCarro((TipoCarro)cbTipoCarro.getSelectedItem(), placa);
						setServico(new Carro((TipoCarro)cbTipoCarro.getSelectedItem(), placa));

						lbObs.setText("Carro atualizado com sucesso");
						lbObs.setForeground(new Color(0, 180, 0));
						lbObs.setVisible(true);

					} catch (IllegalArgumentException iae) {
						lbObs.setText("Placa inv\u00E1lida (ex: ABC1234)");
						lbObs.setForeground(Color.RED);
						lbObs.setVisible(true);

					} catch (Exception exc) {
						lbObs.setText(exc.getMessage());
						lbObs.setForeground(Color.RED);
						lbObs.setVisible(true);
					}
				}
				if(getServico() instanceof Quarto){
					try {
						if(tfNumero.getText() == null)
							throw new IllegalArgumentException();

						int numero = Integer.parseInt(tfNumero.getText());

						if (cbTipoQuarto.getSelectedIndex() == -1)
							throw new Exception("Escolha um tipo de quarto");

						Quarto c = (Quarto)getServico();
						Sistema.getHotel().removeQuarto(c);
						Sistema.getHotel().adicionaQuarto((TipoQuarto)cbTipoQuarto.getSelectedItem(),numero);
						setServico(new Quarto((TipoQuarto)cbTipoQuarto.getSelectedItem(),numero));

						lbObs.setText("Quarto atualizado com sucesso");
						lbObs.setForeground(new Color(0, 180, 0));
						lbObs.setVisible(true);

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
				}
				if(getServico() instanceof Restaurante){
					try{
						if(tfNomeR.getText() == null)
							throw new IllegalArgumentException();
						//verifica se eh valido
						for(int i = 0; i < tfNomeR.getText().length();i++)
							if (!Character.isAlphabetic(tfNomeR.getText().charAt(i)) && tfNomeR.getText().charAt(i) != ' ')
								throw new IllegalArgumentException();

						if(tfNomeR.getText().length() < 3)
							throw new IllegalArgumentException();

						Restaurante r = (Restaurante)getServico();
						Sistema.getHotel().removeRestaurante(r);
						Sistema.getHotel().adicionaRestaurante(tfNomeR.getText());
						setServico(new Restaurante(tfNomeR.getText()));

						lbObs.setText("Restaurante atualizado com sucesso");
						lbObs.setForeground(new Color(0, 180, 0));
						lbObs.setVisible(true);
						lbObs.setVisible(false);
						lbObs.setVisible(true);

					}
					catch(IllegalArgumentException iae){
						lbObs.setText("Nome deve ter no m\u00EDnimo 3 caracteres(apenas letras)");
						lbObs.setForeground(Color.RED);
						lbObs.setVisible(true);
					}
					catch(Exception exc){
						lbObs.setText(exc.getMessage());
						lbObs.setForeground(Color.RED);
						lbObs.setVisible(true);
					}
				}

			}
		});

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{264, 0};
		gbl_panel_2.rowHeights = new int[]{15, 0};
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

		final JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		add(panel, gbc_panel);
		panel.setLayout(new CardLayout(0, 0));

		JPanel panel_ce = new JPanel();
		panel_ce.setBorder(new EmptyBorder(0, 0, 0, 70));
		panel.add(panel_ce, "Cama Extra");
		GridBagLayout gbl_panel_ce = new GridBagLayout();
		gbl_panel_ce.columnWidths = new int[]{129, 261, 0};
		gbl_panel_ce.rowHeights = new int[]{0, 0};
		gbl_panel_ce.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_ce.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_ce.setLayout(gbl_panel_ce);

		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		GridBagConstraints gbc_lblCdigo = new GridBagConstraints();
		gbc_lblCdigo.anchor = GridBagConstraints.EAST;
		gbc_lblCdigo.insets = new Insets(0, 0, 0, 5);
		gbc_lblCdigo.gridx = 0;
		gbc_lblCdigo.gridy = 0;
		panel_ce.add(lblCdigo, gbc_lblCdigo);


		tfCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		tfCodigo.setColumns(10);
		GridBagConstraints gbc_tfCodigo = new GridBagConstraints();
		gbc_tfCodigo.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfCodigo.gridx = 1;
		gbc_tfCodigo.gridy = 0;
		panel_ce.add(tfCodigo, gbc_tfCodigo);

		JPanel panel_q = new JPanel();
		panel_q.setBorder(new EmptyBorder(0, 0, 0, 70));
		panel.add(panel_q, "Quarto");
		GridBagLayout gbl_panel_q = new GridBagLayout();
		gbl_panel_q.columnWidths = new int[]{105, 253, 0};
		gbl_panel_q.rowHeights = new int[]{56, 71, 0};
		gbl_panel_q.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_q.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel_q.setLayout(gbl_panel_q);

		JLabel lblNmero = new JLabel("N\u00FAmero:");
		GridBagConstraints gbc_lblNmero = new GridBagConstraints();
		gbc_lblNmero.anchor = GridBagConstraints.EAST;
		gbc_lblNmero.insets = new Insets(0, 0, 5, 5);
		gbc_lblNmero.gridx = 0;
		gbc_lblNmero.gridy = 0;
		panel_q.add(lblNmero, gbc_lblNmero);

		tfNumero.setColumns(10);
		GridBagConstraints gbc_tfNumero = new GridBagConstraints();
		gbc_tfNumero.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNumero.insets = new Insets(0, 0, 5, 0);
		gbc_tfNumero.gridx = 1;
		gbc_tfNumero.gridy = 0;
		panel_q.add(tfNumero, gbc_tfNumero);

		JLabel label_5 = new JLabel("Tipo de quarto:");
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.anchor = GridBagConstraints.EAST;
		gbc_label_5.gridx = 0;
		gbc_label_5.gridy = 1;
		panel_q.add(label_5, gbc_label_5);

		cbTipoQuarto.setModel(new DefaultComboBoxModel<Object>(TipoQuarto.values()));
		GridBagConstraints gbc_cbTipoQuarto = new GridBagConstraints();
		gbc_cbTipoQuarto.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbTipoQuarto.gridx = 1;
		gbc_cbTipoQuarto.gridy = 1;
		panel_q.add(cbTipoQuarto, gbc_cbTipoQuarto);

		JPanel panel_r = new JPanel();
		panel_r.setBorder(new EmptyBorder(0, 0, 0, 70));
		panel.add(panel_r, "Restaurante");
		GridBagLayout gbl_panel_r = new GridBagLayout();
		gbl_panel_r.columnWidths = new int[]{124, 296, 0};
		gbl_panel_r.rowHeights = new int[]{63, 0};
		gbl_panel_r.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_r.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_r.setLayout(gbl_panel_r);

		JLabel label_6 = new JLabel("Nome:");
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.anchor = GridBagConstraints.EAST;
		gbc_label_6.insets = new Insets(0, 0, 0, 5);
		gbc_label_6.gridx = 0;
		gbc_label_6.gridy = 0;
		panel_r.add(label_6, gbc_label_6);

		tfNomeR.setColumns(10);
		GridBagConstraints gbc_tfNomeR = new GridBagConstraints();
		gbc_tfNomeR.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNomeR.gridx = 1;
		gbc_tfNomeR.gridy = 0;
		panel_r.add(tfNomeR, gbc_tfNomeR);

		JPanel panel_bs = new JPanel();
		panel_bs.setBorder(new EmptyBorder(0, 0, 0, 70));
		panel.add(panel_bs, "Babysitter");
		GridBagLayout gbl_panel_bs = new GridBagLayout();
		gbl_panel_bs.columnWidths = new int[]{120, 252, 0};
		gbl_panel_bs.rowHeights = new int[]{35, 0};
		gbl_panel_bs.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_bs.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_bs.setLayout(gbl_panel_bs);

		JLabel label = new JLabel("Nome:");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel_bs.add(label, gbc_label);

		tfNomeBs.setColumns(10);
		GridBagConstraints gbc_tfNomeBs = new GridBagConstraints();
		gbc_tfNomeBs.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNomeBs.gridx = 1;
		gbc_tfNomeBs.gridy = 0;
		panel_bs.add(tfNomeBs, gbc_tfNomeBs);

		JPanel panel_c = new JPanel();
		panel_c.setBorder(new EmptyBorder(0, 0, 0, 70));
		panel.add(panel_c, "Carro");
		GridBagLayout gbl_panel_c = new GridBagLayout();
		gbl_panel_c.columnWidths = new int[]{54, 235, 0};
		gbl_panel_c.rowHeights = new int[]{30, 32, 0};
		gbl_panel_c.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_c.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel_c.setLayout(gbl_panel_c);

		JLabel label_2 = new JLabel("Placa:");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.EAST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 0;
		panel_c.add(label_2, gbc_label_2);

		tfPlaca.setColumns(10);
		GridBagConstraints gbc_tfPlaca = new GridBagConstraints();
		gbc_tfPlaca.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfPlaca.insets = new Insets(0, 0, 5, 0);
		gbc_tfPlaca.gridx = 1;
		gbc_tfPlaca.gridy = 0;
		panel_c.add(tfPlaca, gbc_tfPlaca);

		JLabel label_3 = new JLabel("Tipo de carro:");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.anchor = GridBagConstraints.EAST;
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 1;
		panel_c.add(label_3, gbc_label_3);

		selecionaCard(servico, panel, cbTipoCarro, cbTipoQuarto);

		cbTipoCarro.setModel(new DefaultComboBoxModel<Object>(TipoCarro.values()));
		GridBagConstraints gbc_cbTipoCarro = new GridBagConstraints();
		gbc_cbTipoCarro.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbTipoCarro.gridx = 1;
		gbc_cbTipoCarro.gridy = 1;
		panel_c.add(cbTipoCarro, gbc_cbTipoCarro);

		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 3;
		add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{46, 77, 0};
		gbl_panel_3.rowHeights = new int[]{25, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);

		JButton btnCancelar = new JButton("Voltar");
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnCancelar.gridx = 1;
		gbc_btnCancelar.gridy = 0;
		panel_3.add(btnCancelar, gbc_btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(telaAnterior);
			}
		});

	}

	private void selecionaCard(Object servico, final JPanel panel,
			JComboBox<Object> cbTipoCarro, JComboBox<Object> cbTipoQuarto) {
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

	private Object getServico(){
		return servico;
	}

	private void setServico(Object obj){
		servico = obj;
	}

	public JPanel getTelaAnterior() {
		return telaAnterior;
	}

	public void setTelaAnterior(JPanel telaAnterior) {
		this.telaAnterior = telaAnterior;
	}
}
