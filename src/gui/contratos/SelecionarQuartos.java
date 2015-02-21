package gui.contratos;

import gui.Menu;
import gui.Sistema;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.ImageIcon;

import core.hotel.Contrato;
import core.hotel.Hospede;
import core.servicos.devolviveis.Quarto;
import core.tempo.Periodo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;

public class SelecionarQuartos extends JPanel {
	private Periodo estadia;
	private Hospede hospede;
	private String cartao;
	private double tarifa;
	private JLabel ErrorLabel;	
	private DefaultListModel<Object> lista = new DefaultListModel<>();
	private DefaultListModel<Object> lista1 = new DefaultListModel<>();
	private JList<Object> list_1;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 * @param tarifa 
	 * @param cartao 
	 */
	public SelecionarQuartos(Periodo estadia, Hospede hospede, String cartao, double tarifa,final JPanel tela) {
		
		this.setName("Selecionar Quartos");
		
		this.estadia = estadia;
		this.hospede = hospede;
		this.cartao = cartao;
		this.tarifa = tarifa;
		
		JPanel panel = new JPanel();
		
		JLabel lblSelecioneOsQuartos = new JLabel("Selecione os quartos desejados :");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		JLabel lblSeusQuartos = new JLabel("Seus quartos :");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		JButton btnRemoverQuarto = new JButton("Remover quarto");
		btnRemoverQuarto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removeQuarto();
			}
		});
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alugar();
			}
		});
		
		JButton btnCancelar = new JButton("Retornar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Sistema.setTela(tela);
			}
		});
		
		ErrorLabel = new JLabel("");
		ErrorLabel.setForeground(Color.RED);
		ErrorLabel.setVisible(false);
		ErrorLabel.setIcon(new ImageIcon(SelecionarQuartos.class.getResource("/gui/images/error.png")));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(ErrorLabel, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
							.addComponent(btnCancelar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnConfirmar)
							.addGap(16))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSelecioneOsQuartos)
							.addContainerGap(371, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSeusQuartos)
							.addContainerGap(604, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnRemoverQuarto)
							.addContainerGap(561, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(lblSelecioneOsQuartos)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
					.addGap(24)
					.addComponent(lblSeusQuartos)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnRemoverQuarto)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnConfirmar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(ErrorLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(15))
		);
		
		list_1 = new JList<Object>();
		scrollPane_1.setViewportView(list_1);
		
		list_1.setModel(lista1);
		
		final JList<Object> list = new JList<Object>();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Quarto quarto = (Quarto) list.getSelectedValue();
				if (quarto != null)
					adicionaQuartosEscolhidos(quarto);
			}
		});
		scrollPane.setViewportView(list);
		setLayout(groupLayout);
		
		for (Quarto quarto : Sistema.getHotel().getQuartosDisponiveis(estadia)) {
			lista.addElement(quarto);
		}
		
		list.setModel(lista);

	}
	
	private void removeQuarto() {
		Object item = list_1.getSelectedValue();

		if (item == null) {
			ErrorLabel.setText("Nenhum item foi selecionado.");
			ErrorLabel.setVisible(true);
			return;
		}
		
		lista1.removeElement(item);
		lista.addElement(item);
		ErrorLabel.setVisible(false);
	}
	
	private void alugar() {
		
		if (lista1.isEmpty()) {
			ErrorLabel.setText("Nenhum quarto foi escolhido.");
			ErrorLabel.setVisible(true);
			return;
		}		
		
		try {
			hospede.realizarReserva(cartao, tarifa);
		} catch (Exception e) {
		}
		
		Contrato contrato = null;
		Iterator<Contrato> contratos = hospede.getContratos();
		
		while (contratos.hasNext()) {
			contrato = contratos.next();
		}
		
		for (int i = 0; i < lista1.getSize(); i++) {
			Quarto quarto = (Quarto) lista1.getElementAt(i);
			quarto.aluga(estadia);
			contrato.adicionaServico(quarto);
		}
		
		ErrorLabel.setVisible(false);
		lista1.clear();
		Sistema.setTela(new Menu());
	}
	
	private void adicionaQuartosEscolhidos(Quarto quarto) {
		if (quarto == null)
			throw new IllegalArgumentException();
		lista1.addElement(quarto);
		lista.removeElement(quarto);
	}
	
}

