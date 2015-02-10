package gui.contratos;

import gui.Sistema;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.border.LineBorder;

import core.hotel.Contrato;
import core.interfaces.Pagavel;
import core.servicos.devolviveis.Quarto;

import java.awt.Color;

public class VisualizarContrato extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private DefaultListModel<Object> lista = new DefaultListModel<>();
	private DefaultListModel<Object> lista1 = new DefaultListModel<>();
	
	/**
	 * Create the panel.
	 */
	public VisualizarContrato(Contrato contrato) {
		
		JPanel panel = new JPanel();
		
		JLabel lblVisualizarContrato = new JLabel("Visualizar Contrato");
		lblVisualizarContrato.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(lblVisualizarContrato);
		
		JLabel lblEstadoDoContrato = new JLabel("Estado do contrato :");
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		
		JButton btnVoltar = new JButton("Retornar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sistema.setTela(new AtualizarContratos());
			}
		});
		
		JLabel lblDataDeEntrada = new JLabel("Data de entrada :");
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		
		JLabel lblQuartos = new JLabel("Quartos :");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		JLabel lblServiosContratados = new JLabel("Serviços contratados :");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
						.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblEstadoDoContrato)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnVoltar, Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDataDeEntrada)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
						.addComponent(lblQuartos)
						.addComponent(lblServiosContratados))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEstadoDoContrato)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataDeEntrada)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblQuartos)
					.addGap(12)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(lblServiosContratados)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnVoltar)
					.addContainerGap())
		);
		
		JList<Object> list_1 = new JList<Object>();
		scrollPane_1.setViewportView(list_1);
		
		JList<Object> list = new JList<Object>();
		scrollPane.setViewportView(list);
		setLayout(groupLayout);
		
		
		for (Pagavel p : contrato.getServicos()) {
			if (p instanceof Quarto)
				lista.addElement((Quarto) p);
			else
				lista1.addElement(p);
		}
		
		list_1.setModel(lista1);
		list.setModel(lista);
		
		textField.setText(contrato.getEstado().toString());
		
	}
}
