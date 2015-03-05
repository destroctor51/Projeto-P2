package gui.relatorios;

import gui.Sistema;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import core.hotel.Contrato;
import core.hotel.Hospede;
import core.interfaces.Pagavel;

public class FaturamentoHospede extends JPanel {

	private static final long serialVersionUID = 1L;

	private Contrato contrato;
	private Hospede hospede;

	/**
	 * Create the panel.
	 */
	public FaturamentoHospede(final JPanel tela, final Contrato contrato, final Hospede hospede) {
		setName("Fatura");

		this.contrato = contrato;
		this.hospede = hospede;

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 0};
		gridBagLayout.columnWeights = new double[] { 1.0, 0.5, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.75, 1.0 };
		setLayout(gridBagLayout);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);

		final JTextArea relatorio = new JTextArea();
		relatorio.setBorder(new EmptyBorder(0, 10, 0, 10));
		scrollPane.setViewportView(relatorio);
		relatorio.setBackground(UIManager.getColor("Panel.background"));
		relatorio.setEditable(false);
		relatorio.setMaximumSize(relatorio.getPreferredSize());
		relatorio.setText(geraRelatorio());

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.NORTHEAST;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 2;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setFocusable(false);
		btnImprimir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(Sistema.isFullscreen())
						Sistema.toggleFullscreen();
					relatorio.print();
				} catch (PrinterException e) {}
			}
		});

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFocusable(false);
		GridBagConstraints gbc_btnVoltar = new GridBagConstraints();
		gbc_btnVoltar.insets = new Insets(0, 0, 0, 10);
		gbc_btnVoltar.gridx = 0;
		gbc_btnVoltar.gridy = 0;
		panel.add(btnVoltar, gbc_btnVoltar);
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(tela);
			}
		});
		GridBagConstraints gbc_btnImprimir = new GridBagConstraints();
		gbc_btnImprimir.gridx = 1;
		gbc_btnImprimir.gridy = 0;
		panel.add(btnImprimir, gbc_btnImprimir);
	}

	private String geraRelatorio() {
		String dataAtual = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
		String dataCheckout = new SimpleDateFormat("dd/MM/yyyy").format(contrato.getDataCheckOut().getTime());
		double tarifa = contrato.getEstacao().getTarifa();
		
		String texto = "";

		texto += "Esta\u00E7\u00E3o: " + contrato.getEstacao() + "    ";
		texto += "Data: " + dataAtual + "    Data de check out: " + dataCheckout + "\n";
		texto += "Fatura do h\u00F3spede " + hospede.getNome() + "    CPF: " + hospede.getCpf() + "\n\n";

		double total = 0;
		for(Pagavel p: contrato.getServicos()) {
			texto += p + " : R$ " + p.getPreco() +  "\n";
			total += p.getPreco();
		}

		texto += "\n";
		texto += "Soma: R$ " + total + "\n";
		texto += "Tarifa: " + tarifa * 100 + "%\n"; 
		texto += "Pre\u00E7o total: R$ " + total * tarifa + "\n";

		return texto;
	}

}
