package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * Janela principal do programa, que controla as telas sendo mostradas e contem o metodo main.
 * 
 * @author ALMEIDA
 */
public class InterfaceGrafica extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static InterfaceGrafica janelaPrincipal;

	/**
	 * Inicializa o sistema.
	 * 
	 * @param args  nao e utilizado
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		
		setTela(new Login());
	}
	
	private InterfaceGrafica() {
		setTitle("Hotel Riviera Campina");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 768);
	}
	
	private static void init() {
		janelaPrincipal = new InterfaceGrafica();
		janelaPrincipal.setVisible(true);
	}
	
	/**
	 * Troca a tela atual para a tela dada.
	 * 
	 * @param novaTela  a tela que se deseja mostrar
	 */
	public static void setTela(JPanel novaTela) {
		if(janelaPrincipal == null) init();
		
		if(novaTela == null)
			throw new IllegalArgumentException();
		
		janelaPrincipal.setContentPane(novaTela);
		janelaPrincipal.repaint();
	}
}
