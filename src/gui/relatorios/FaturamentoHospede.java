package gui.relatorios;

import gui.Sistema;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import core.hotel.Contrato;
import core.hotel.Hospede;
import core.interfaces.Pagavel;

public class FaturamentoHospede extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	
	private Contrato contrato;
	private Hospede hospede;
	
	public FaturamentoHospede(final JPanel tela, final Contrato contrato, final Hospede hospede) {
		
		this.contrato = contrato;
		this.hospede = hospede;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 70, 0, 0, 76, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Fatura", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JTextArea relatorio = new JTextArea();
		relatorio.setEditable(false);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 0;
		relatorio.setMaximumSize(relatorio.getPreferredSize());
		relatorio.setText(geraRelatorio());
		panel.add(relatorio, gbc_textArea);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(tela);
			}
		});
		GridBagConstraints gbc_btnVoltar = new GridBagConstraints();
		gbc_btnVoltar.anchor = GridBagConstraints.EAST;
		gbc_btnVoltar.insets = new Insets(0, 0, 0, 5);
		gbc_btnVoltar.gridx = 2;
		gbc_btnVoltar.gridy = 2;
		
		add(btnVoltar, gbc_btnVoltar);
	}

	private String geraRelatorio() {
		String dataAtual = new SimpleDateFormat("dd/MM/yyyy").format(Calendar
				.getInstance().getTime());
		String texto = "";

		texto += "Data: " + dataAtual + "\n";
		texto += "Fatura do Hospede " + hospede.getNome() + ". CPF: " + hospede.getCpf() + "\n\n";
		
		int total = 0;
		for(Pagavel p: contrato.getServicos())
		{
			texto += p + " : " + p.getPreco() + "\n";
			total += p.getPreco();
		}
		
		texto += "\n";
		texto += "Preco total: " + total + "\n";
		
		return texto;
	}

}
