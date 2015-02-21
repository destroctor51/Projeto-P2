package gui.opinioes;

import gui.Sistema;
import gui.components.NotaPorEstrelas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import gui.components.SuperTextField;

public class AdicionarOpiniao extends JPanel {

	private static final long serialVersionUID = 1L;

	private Calendar dataAtual = GregorianCalendar.getInstance();

	/**
	 * Create the panel.
	 */
	public AdicionarOpiniao(final JPanel tela) {

		setName("Adicionar Opiniao");
		setMinimumSize(new Dimension(0, 0));

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[] {67, 168, 37, 58};
		gridBagLayout.columnWidths = new int[] {0, 400, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.5, 1.0, 0.0, 1.0};
		setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.SOUTH;
		gbc_panel.insets = new Insets(0, 0, 10, 5);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{113, 143, 96, 0};
		gbl_panel.rowHeights = new int[]{0, 23, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JLabel lblAno = new JLabel("Nome:");
		GridBagConstraints gbc_lblAno = new GridBagConstraints();
		gbc_lblAno.anchor = GridBagConstraints.WEST;
		gbc_lblAno.insets = new Insets(0, 0, 5, 5);
		gbc_lblAno.gridx = 0;
		gbc_lblAno.gridy = 0;
		panel.add(lblAno, gbc_lblAno);

		JLabel lblMs = new JLabel("*Nota:");
		GridBagConstraints gbc_lblMs = new GridBagConstraints();
		gbc_lblMs.insets = new Insets(0, 0, 5, 5);
		gbc_lblMs.gridx = 1;
		gbc_lblMs.gridy = 0;
		panel.add(lblMs, gbc_lblMs);

		final JLabel lblAvaliacao = new JLabel("Avaliacao");
		lblAvaliacao.setVisible(false);
		
		final SuperTextField tfNome = new SuperTextField();
		tfNome.setHint("Anonimo");
		GridBagConstraints gbc_superTextField = new GridBagConstraints();
		gbc_superTextField.insets = new Insets(0, 0, 0, 5);
		gbc_superTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_superTextField.gridx = 0;
		gbc_superTextField.gridy = 1;
		panel.add(tfNome, gbc_superTextField);
		GridBagConstraints gbc_lblAvaliacao = new GridBagConstraints();
		gbc_lblAvaliacao.gridx = 2;
		gbc_lblAvaliacao.gridy = 1;
		panel.add(lblAvaliacao, gbc_lblAvaliacao);

		final NotaPorEstrelas starRate = new NotaPorEstrelas();
		starRate.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {
				selecionaComentario(starRate.getSelection(),lblAvaliacao);
			}
		});
		starRate.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				selecionaComentario(starRate.getRollover(),lblAvaliacao);
			}
		});
		GridBagConstraints gbc_starRate = new GridBagConstraints();
		gbc_starRate.insets = new Insets(0, 0, 0, 5);
		gbc_starRate.fill = GridBagConstraints.VERTICAL;
		gbc_starRate.gridx = 1;
		gbc_starRate.gridy = 1;
		panel.add(starRate, gbc_starRate);

		JPanel panelLista = new JPanel();
		panelLista.setMaximumSize(new Dimension(10, 10));
		GridBagConstraints gbc_panelLista = new GridBagConstraints();
		gbc_panelLista.anchor = GridBagConstraints.NORTH;
		gbc_panelLista.insets = new Insets(0, 0, 5, 5);
		gbc_panelLista.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelLista.gridx = 1;
		gbc_panelLista.gridy = 1;
		add(panelLista, gbc_panelLista);
		GridBagLayout gbl_panelLista = new GridBagLayout();
		gbl_panelLista.rowHeights = new int[]{0, 149};
		gbl_panelLista.columnWeights = new double[]{1.0};
		gbl_panelLista.rowWeights = new double[]{0.0, 1.0};
		panelLista.setLayout(gbl_panelLista);

		JLabel lblComentrio = new JLabel("*Comentário:");
		GridBagConstraints gbc_lblComentrio = new GridBagConstraints();
		gbc_lblComentrio.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblComentrio.insets = new Insets(0, 0, 5, 0);
		gbc_lblComentrio.gridx = 0;
		gbc_lblComentrio.gridy = 0;
		panelLista.add(lblComentrio, gbc_lblComentrio);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		panelLista.add(scrollPane, gbc_scrollPane);

		final JTextArea tfComentario = new JTextArea(2,2);
		scrollPane.setViewportView(tfComentario);
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.anchor = GridBagConstraints.NORTH;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 2;
		add(panel_2, gbc_panel_2);

		final JLabel lblobs = new JLabel("*Obs");
		lblobs.setVisible(false);
		panel_2.add(lblobs);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.NORTH;
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 3;
		add(panel_1, gbc_panel_1);

		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Sistema.setTela(tela);
			}
		});
		panel_1.add(btnNewButton);

		JButton btnAdicionar_1 = new JButton("Adicionar Opiniao");
		panel_1.add(btnAdicionar_1);
		btnAdicionar_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {

					int nota = starRate.getSelection();
					String nome = tfNome.getText();
					String comentario = tfComentario.getText();

					if (nota <= 0)
						throw new NumberFormatException();
					if (comentario.length() < 3)
						throw new IllegalArgumentException();
					if (nome == null || nome.equals(""))
						Sistema.getHotel().adicionaOpiniao(nota, comentario, dataAtual);
					else{
						if(nome.length() < 3)
							throw new IllegalArgumentException();
						Sistema.getHotel().adicionaOpiniao(nome, nota, comentario, dataAtual);
					}
					
					Sistema.setTela(tela);

				} catch (NumberFormatException nfe) {
					lblobs.setVisible(true);
					lblobs.setForeground(Color.RED);
					lblobs.setText("Deve ser dada uma nota!");
				} catch (IllegalArgumentException iae) {
					lblobs.setVisible(true);
					lblobs.setForeground(Color.RED);
					lblobs.setText("Comentario e nome(se for preenchido) devem ter no minimo 3 caracteres!");
				}
			}
		});

	}

	protected void selecionaComentario(int i, JLabel lblAvaliacao) {
		switch(i){
		case 0:
			lblAvaliacao.setVisible(false);
			break;
		case 1:
			lblAvaliacao.setForeground(new Color(220, 20, 30));
			lblAvaliacao.setVisible(true);
			lblAvaliacao.setText("Ruim");
			break;
		case 2:
			lblAvaliacao.setForeground(new Color(220,100,50));
			lblAvaliacao.setVisible(true);
			lblAvaliacao.setText("Pode Melhorar");
			break;
		case 3:
			lblAvaliacao.setForeground(new Color(0,0,255));
			lblAvaliacao.setVisible(true);
			lblAvaliacao.setText("Medio");
			break;
		case 4:
			lblAvaliacao.setForeground(new Color(10,140,60));
			lblAvaliacao.setVisible(true);
			lblAvaliacao.setText("Bom!");
			break;
		case 5:
			lblAvaliacao.setForeground(new Color(10,180,40));
			lblAvaliacao.setVisible(true);
			lblAvaliacao.setText("Excelente!");
			break;
		}
	}
}
