package gui.contratos;

import gui.Menu;
import gui.Sistema;
import gui.components.SuperTextField;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import utils.Filtro;
import core.hotel.Contrato;
import core.hotel.EstadoDeContrato;
import core.hotel.Hospede;

public class AtualizarContratos extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Hospede hospede;
	private Contrato contrato;
	private SuperTextField NameField;
	private JLabel ErrorLabel;
	private DefaultListModel<Contrato> lista1 = new DefaultListModel<>();
	private JList<Hospede> list;
	private JList<Contrato> list_1;

	private JPanel tela = this;
	/**
	 * Create the panel.
	 *
	 */
	public AtualizarContratos() {
		addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				Filtro.exibeFiltrado(NameField.getText(), Sistema.getHotel().getHospedes(), list);
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});

		this.setName("Atualizar Contratos");
		
		JPanel panel = new JPanel();

		JPanel panel_1 = new JPanel();

		JLabel label = new JLabel("Procurar Hóspede :");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(label);

		NameField = new SuperTextField() {
			private static final long serialVersionUID = 1L;
			@Override
			protected void textChanged(String text) {
				Filtro.exibeFiltrado(text, Sistema.getHotel().getHospedes(), list);
			}
		};
		NameField.setColumns(37);
		panel_1.add(NameField);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		JLabel label_1 = new JLabel("Contratos:");

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		ErrorLabel = new JLabel("");
		ErrorLabel.setForeground(Color.RED);
		ErrorLabel.setVisible(false);
		ErrorLabel.setIcon(new ImageIcon(AtualizarContratos.class
				.getResource("/gui/images/error.png")));

		JButton btnContinuar = new JButton("Contratar Servicos");
		btnContinuar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				continuar();
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(new Menu(true));
			}
		});

		JButton btnNewButton = new JButton("Visualizar");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				visualizarContrato();
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
						.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(ErrorLabel, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
							.addComponent(btnCancelar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnContinuar)
							.addGap(3))
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(label_1)
					.addGap(12)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnCancelar)
							.addComponent(btnNewButton)
							.addComponent(btnContinuar))
						.addComponent(ErrorLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);

		list_1 = new JList<>();
		list_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Contrato cont = (Contrato) list_1.getSelectedValue();
				if (cont != null)
					setContrato(cont);
			}
		});
		scrollPane_1.setViewportView(list_1);

		list = new JList<>();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Hospede hosp = (Hospede) list.getSelectedValue();
				if (hosp != null) {
					setHospede(hosp);
					preencheContratos(hosp);
				}
			}
		});
		scrollPane.setViewportView(list);
		setLayout(groupLayout);

	}

	private void setHospede(Hospede hosp) {
		contrato = null;

		if (hosp == null)
			throw new IllegalArgumentException();
		hospede = hosp;
	}

	private void setContrato(Contrato cont) {
		if (cont == null)
			throw new IllegalArgumentException();
		contrato = cont;
	}

	private void preencheContratos(Hospede hosp) {
		 lista1.clear();
		Iterator<Contrato> contratos = hosp.getContratos();

		while (contratos.hasNext()) {
			Contrato contrato = contratos.next();

			if (contrato.getEstado().equals(EstadoDeContrato.ABERTO))
				lista1.addElement(contrato);
		}
		list_1.setModel(lista1);
	}

	private void continuar() {
		if (hospede == null) {
			ErrorLabel.setText("Hóspede ainda não escolhido.");
			ErrorLabel.setVisible(true);
			return;
		}

		else if (contrato == null) {
			ErrorLabel.setText("Contrato ainda não escolhido.");
			ErrorLabel.setVisible(true);
			return;
		}

		ErrorLabel.setVisible(false);
		Sistema.setTela(new SelecionarServicos(contrato,this));
	}

	private void visualizarContrato() {
		if (hospede == null) {
			ErrorLabel.setText("Hospede ainda nao escolhido.");
			ErrorLabel.setVisible(true);
			return;
		}

		else if (contrato == null) {
			ErrorLabel.setText("Contrato ainda nao escolhido.");
			ErrorLabel.setVisible(true);
			return;
		}

		ErrorLabel.setVisible(false);
		Sistema.setTela(new VisualizarContrato(contrato,tela));
	}
}
