package gui.relatorios;

import gui.Sistema;

import java.awt.GridBagLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JPanel;

import core.hotel.Contrato;
import core.hotel.EstadoDeContrato;
import core.hotel.Hospede;
import core.tempo.Periodo;
import java.awt.GridBagConstraints;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.EmptyBorder;

public class ContratosEmAberto extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ContratosEmAberto(final JPanel tela) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 70, 0, 0, 76, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setBorder(new CompoundBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Relatorio", TitledBorder.LEADING, TitledBorder.TOP, null, null), new EmptyBorder(10, 10, 10, 10)));
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
		relatorio.setText(geraRelatorio(null));
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

	private String geraRelatorio(Periodo periodo) {
		String dataAtual = new SimpleDateFormat("dd/MM/yyyy").format(Calendar
				.getInstance().getTime());
		String texto = "";

		texto += "Data: " + dataAtual + "\n";
		texto += "Relatorio sobre contratos em aberto\n\n";

		int contratosAbertos = 0;
		int hospedes = 0;

		for (Hospede hosp : Sistema.getHotel().getHospedes()) {
			int temp = contratosAbertos;
			for(Contrato contrato: hosp.getContratos()){
				if (contrato.getEstado() == EstadoDeContrato.ABERTO)
					contratosAbertos++;
			}
			if (contratosAbertos != temp)
				hospedes++;
		}
		
		texto += "Hospedes no hotel: " + hospedes + "\n";
		texto += "Contratos abertos: " + contratosAbertos + "\n";
		
		return texto;
	}

}
