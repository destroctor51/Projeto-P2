package gui.estacoes;

import gui.Menu;
import gui.Sistema;
import gui.components.SuperTextField;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import java.awt.Dimension;

import javax.swing.JButton;

import java.awt.Rectangle;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import core.tempo.Estacao;
import utils.Filtro;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class Estacoes extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private SuperTextField tfBuscar;
    private JButton btnAdicionar;
    final JList<Object> list = new JList<Object>();
    private JLabel lblobservaes;

    /**
     * Create the panel.
     */
    public Estacoes() {
    	setName("Estações");
            addAncestorListener(new AncestorListener() {
                    @Override
                    public void ancestorAdded(AncestorEvent arg0) {
                            List<Object> elementos = filtraList();
                            Filtro.exibeFiltrado(tfBuscar.getText(), elementos, list);

                            lblobservaes.setVisible(false);
                    }
                    @Override
                    public void ancestorMoved(AncestorEvent arg0) {}
                    @Override
                    public void ancestorRemoved(AncestorEvent arg0) {}
            });

            GridBagLayout gridBagLayout = new GridBagLayout();
            gridBagLayout.columnWidths = new int[]{0, 402, 0, 0};
            gridBagLayout.rowHeights = new int[]{59, 34, 166, 59, 0};
            gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
            gridBagLayout.rowWeights = new double[]{1.0, 0.25, 1.0, 1.0, Double.MIN_VALUE};
            setLayout(gridBagLayout);

            JPanel panel = new JPanel();
            GridBagConstraints gbc_panel = new GridBagConstraints();
            gbc_panel.anchor = GridBagConstraints.SOUTH;
            gbc_panel.insets = new Insets(0, 0, 5, 5);
            gbc_panel.fill = GridBagConstraints.HORIZONTAL;
            gbc_panel.gridx = 1;
            gbc_panel.gridy = 0;
            add(panel, gbc_panel);
            GridBagLayout gbl_panel = new GridBagLayout();
            gbl_panel.columnWidths = new int[]{0, 0, 0};
            gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
            gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
            gbl_panel.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
            panel.setLayout(gbl_panel);

            JLabel label = new JLabel("Buscar:");
            GridBagConstraints gbc_label = new GridBagConstraints();
            gbc_label.anchor = GridBagConstraints.EAST;
            gbc_label.insets = new Insets(0, 0, 10, 10);
            gbc_label.gridx = 0;
            gbc_label.gridy = 1;
            panel.add(label, gbc_label);

            tfBuscar = new SuperTextField() {
                    private static final long serialVersionUID = 1L;
                    @Override
                    protected void textChanged(String text) {
                            List<Object> elementos = filtraList();
                            Filtro.exibeFiltrado(text, elementos, list);
                    }
            };

            tfBuscar.setMaximumSize(new Dimension(100, 100));
            GridBagConstraints gbc_textField = new GridBagConstraints();
            gbc_textField.fill = GridBagConstraints.HORIZONTAL;
            gbc_textField.insets = new Insets(0, 0, 10, 0);
            gbc_textField.gridx = 1;
            gbc_textField.gridy = 1;
            panel.add(tfBuscar, gbc_textField);

		
            JPanel panel_1 = new JPanel();
            GridBagConstraints gbc_panel_1 = new GridBagConstraints();
            gbc_panel_1.anchor = GridBagConstraints.SOUTH;
            gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
            gbc_panel_1.insets = new Insets(0, 0, 5, 5);
            gbc_panel_1.gridx = 1;
            gbc_panel_1.gridy = 1;
            add(panel_1, gbc_panel_1);
            GridBagLayout gbl_panel_1 = new GridBagLayout();
            gbl_panel_1.columnWidths = new int[]{113, 115, 0};
            gbl_panel_1.rowHeights = new int[]{23, 0};
            gbl_panel_1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
            gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
            panel_1.setLayout(gbl_panel_1);

		
            JButton btnRemoverEstao = new JButton("Remover Estação");
            btnRemoverEstao.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                            Object item = list.getSelectedValue();

                            if (item == null) {
                                    lblobservaes.setText("Nenhum item foi selecionado");
                                    lblobservaes.setForeground(Color.RED);
                                    lblobservaes.setVisible(true);
                                    return;
                            }
                            
                            lblobservaes.setVisible(false);

                            Sistema.getHotel().removeEstacao((Estacao) item);

                            List<Object> elementos = filtraList();
                            Filtro.exibeFiltrado(tfBuscar.getText(), elementos, list);
                    }
            });
            btnRemoverEstao.setMaximumSize(new Dimension(150, 100));
            btnRemoverEstao.setBounds(new Rectangle(100, 0, 100, 50));
            GridBagConstraints gbc_btnRemoverEstao = new GridBagConstraints();
            gbc_btnRemoverEstao.anchor = GridBagConstraints.NORTHWEST;
            gbc_btnRemoverEstao.insets = new Insets(0, 0, 0, 5);
            gbc_btnRemoverEstao.gridx = 0;
            gbc_btnRemoverEstao.gridy = 0;
            panel_1.add(btnRemoverEstao, gbc_btnRemoverEstao);

		
            btnAdicionar = new JButton("Adicionar Estação");
            btnAdicionar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                            Sistema.setTela(new GerenciarEstacao(null));
                    }
            });
            GridBagConstraints gbc_btnAdicionarEstao = new GridBagConstraints();
            gbc_btnAdicionarEstao.anchor = GridBagConstraints.NORTHWEST;
            gbc_btnAdicionarEstao.gridx = 1;
            gbc_btnAdicionarEstao.gridy = 0;
            panel_1.add(btnAdicionar, gbc_btnAdicionarEstao);

            JScrollPane scrollPane = new JScrollPane();
            GridBagConstraints gbc_scrollPane = new GridBagConstraints();
            gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
            gbc_scrollPane.fill = GridBagConstraints.BOTH;
            gbc_scrollPane.gridx = 1;
            gbc_scrollPane.gridy = 2;
            add(scrollPane, gbc_scrollPane);

            list.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                            if (e.getClickCount() == 2) {
                            		int index;
                            		Object item;
                            		try {
                            			index = list.locationToIndex(e.getPoint());
                            			ListModel<Object> dlm = list.getModel();
                            			item = dlm.getElementAt(index);
                            		} catch (ArrayIndexOutOfBoundsException ex) {
            							return;
                            		}
                                    list.ensureIndexIsVisible(index);
                                    Sistema.setTela(new GerenciarEstacao((Estacao) item));
                            }
                    }
            });

            list.setVisibleRowCount(-1);
            list.setFixedCellWidth(100);
            scrollPane.setViewportView(list);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.setSelectedIndex(0);

		
            JPanel panel_2 = new JPanel();
            GridBagConstraints gbc_panel_2 = new GridBagConstraints();
            gbc_panel_2.anchor = GridBagConstraints.NORTH;
            gbc_panel_2.insets = new Insets(0, 0, 0, 5);
            gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
            gbc_panel_2.gridx = 1;
            gbc_panel_2.gridy = 3;
            add(panel_2, gbc_panel_2);
            GridBagLayout gbl_panel_2 = new GridBagLayout();
            gbl_panel_2.columnWidths = new int[]{154, 0, 46, 0, 0};
            gbl_panel_2.rowHeights = new int[]{25, 0};
            gbl_panel_2.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
            gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
            panel_2.setLayout(gbl_panel_2);

            lblobservaes = new JLabel("*Observações");
            lblobservaes.setIcon(new ImageIcon(Estacoes.class.getResource("/gui/images/error.png")));
            lblobservaes.setVisible(false);
            GridBagConstraints gbc_lblobservaes = new GridBagConstraints();
            gbc_lblobservaes.anchor = GridBagConstraints.NORTHWEST;
            gbc_lblobservaes.insets = new Insets(0, 0, 0, 5);
            gbc_lblobservaes.gridx = 0;
            gbc_lblobservaes.gridy = 0;
            panel_2.add(lblobservaes, gbc_lblobservaes);

            JButton btnVoltar = new JButton("Voltar");
            btnVoltar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                            Sistema.setTela(new Menu());
                    }
            });
            GridBagConstraints gbc_btnVoltar = new GridBagConstraints();
            gbc_btnVoltar.anchor = GridBagConstraints.NORTHEAST;
            gbc_btnVoltar.gridx = 3;
            gbc_btnVoltar.gridy = 0;
            panel_2.add(btnVoltar, gbc_btnVoltar);

    }


    private List<Object> filtraList() {
        List<Object> elementos = new LinkedList<>();
        Iterator<Estacao> estacoes = Sistema.getHotel().getTarifas();
        while (estacoes.hasNext()) {
                elementos.add(estacoes.next());
        }
        return elementos;
}
}
