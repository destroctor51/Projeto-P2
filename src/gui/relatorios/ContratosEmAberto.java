package gui.relatorios;

import gui.Sistema;

import java.awt.Color;
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
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import core.hotel.Contrato;
import core.hotel.EstadoDeContrato;
import core.hotel.Hospede;
import core.tempo.Periodo;

public class ContratosEmAberto extends JPanel {

	private static final long serialVersionUID = 1L;

	public ContratosEmAberto(final JPanel tela) {
		setName("Relat\u00F3rio de contratos");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 0};
		gridBagLayout.columnWeights = new double[] { 1.0, 0.5, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.5, 1.0 };
		setLayout(gridBagLayout);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFocusable(false);
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(tela);
			}
		});

		JPanel panel = new JPanel();
		panel.setBorder(new CompoundBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Relat\u00F3rio", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)), new EmptyBorder(10, 10, 10, 10)));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0};
		gbl_panel.rowHeights = new int[] {0};
		gbl_panel.columnWeights = new double[]{1.0};
		gbl_panel.rowWeights = new double[]{1.0};
		panel.setLayout(gbl_panel);

		JTextArea relatorio = new JTextArea();
		GridBagConstraints gbc_relatorio = new GridBagConstraints();
		gbc_relatorio.fill = GridBagConstraints.BOTH;
		gbc_relatorio.gridx = 0;
		gbc_relatorio.gridy = 0;
		panel.add(relatorio, gbc_relatorio);
		relatorio.setBackground(UIManager.getColor("Panel.background"));
		relatorio.setEditable(false);
		relatorio.setText(geraRelatorio(null));
		GridBagConstraints gbc_btnVoltar = new GridBagConstraints();
		gbc_btnVoltar.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnVoltar.insets = new Insets(0, 0, 0, 5);
		gbc_btnVoltar.gridx = 1;
		gbc_btnVoltar.gridy = 2;

		add(btnVoltar, gbc_btnVoltar);


	}

	private String geraRelatorio(Periodo periodo) {
		String dataAtual = new SimpleDateFormat("dd/MM/yyyy").format(Calendar
				.getInstance().getTime());
		String texto = "";

		texto += "Data: " + dataAtual + "\n";
		texto += "Relat\u00F3rio sobre contratos em aberto\n\n";

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

		texto += "H\u00F3spedes no hotel: " + hospedes + "\n";
		texto += "Contratos abertos: " + contratosAbertos + "\n";

		return texto;
	}

}
