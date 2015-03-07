package gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import utils.Arquivo;
import core.eventos.FullscreenEvent;
import core.eventos.FullscreenListener;
import core.hotel.Hotel;
import core.login.Conta;
import core.login.GerenciadorDeContas;

/**
 * Janela principal do programa, que controla as telas sendo mostradas e contem o metodo main.
 *
 * @author Victor Andrade de Almeida
 */
public class Sistema extends JFrame implements ActionListener, FullscreenListener {

	private static final long serialVersionUID = 1L;

	private static Sistema janela;

	private static Hotel hotel;
	private static Conta usuario;
	private static GerenciadorDeContas gdc;

	private JPanel contentPane;
	private JLabel userLabel;
	private JLabel dateLabel;
	private Timer timer;

	private final Action fullscreenAction = new FullscreenAction();
	private final Action logoutAction = new LogoutAction();
	private JButton logoutButton;
	private JLabel titleLabel;

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

	private Sistema() {
		setMinimumSize(new Dimension(720, 540));
		setTitle("Hotel Riviera Campina");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0};
		getContentPane().setLayout(gridBagLayout);

		JPanel toolBar = new JPanel();
		toolBar.setBackground(UIManager.getColor("activeCaption"));
		toolBar.setBorder(new EmptyBorder(5, 10, 5, 10));
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.insets = new Insets(0, 0, 5, 0);
		gbc_toolBar.anchor = GridBagConstraints.NORTH;
		gbc_toolBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_toolBar.gridx = 0;
		gbc_toolBar.gridy = 0;
		getContentPane().add(toolBar, gbc_toolBar);
		GridBagLayout gbl_toolBar = new GridBagLayout();
		gbl_toolBar.columnWidths = new int[] {15, 240, 0, 260};
		gbl_toolBar.rowHeights = new int[] {17};
		gbl_toolBar.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0};
		gbl_toolBar.rowWeights = new double[]{0.0};
		toolBar.setLayout(gbl_toolBar);

		logoutButton = new JButton("");
		logoutButton.setFocusable(false);
		logoutButton.setRolloverIcon(new ImageIcon(Sistema.class.getResource("/gui/images/power_hover.png")));
		logoutButton.setBorderPainted(false);
		logoutButton.setIcon(new ImageIcon(Sistema.class.getResource("/gui/images/power.png")));
		logoutButton.setContentAreaFilled(false);
		logoutButton.setPreferredSize(new Dimension(15, 15));
		logoutButton.setMargin(new Insets(0, 0, 0, 0));
		logoutButton.setMaximumSize(new Dimension(15, 15));
		logoutButton.setMinimumSize(new Dimension(15, 15));
		GridBagConstraints gbc_logoutButton = new GridBagConstraints();
		gbc_logoutButton.anchor = GridBagConstraints.WEST;
		gbc_logoutButton.insets = new Insets(0, 0, 0, 5);
		gbc_logoutButton.gridx = 0;
		gbc_logoutButton.gridy = 0;
		logoutButton.addActionListener(logoutAction);
		toolBar.add(logoutButton, gbc_logoutButton);

		userLabel = new JLabel();
		userLabel.setText("<user>");
		userLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_userLabel = new GridBagConstraints();
		gbc_userLabel.anchor = GridBagConstraints.WEST;
		gbc_userLabel.insets = new Insets(0, 0, 0, 5);
		gbc_userLabel.gridx = 1;
		gbc_userLabel.gridy = 0;
		toolBar.add(userLabel, gbc_userLabel);

		titleLabel = new JLabel("<title>");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.insets = new Insets(0, 0, 0, 5);
		gbc_titleLabel.gridx = 2;
		gbc_titleLabel.gridy = 0;
		toolBar.add(titleLabel, gbc_titleLabel);

		dateLabel = new JLabel();
		dateLabel.setText("<date>");
		dateLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_dateLabel = new GridBagConstraints();
		gbc_dateLabel.anchor = GridBagConstraints.EAST;
		gbc_dateLabel.gridx = 3;
		gbc_dateLabel.gridy = 0;
		toolBar.add(dateLabel, gbc_dateLabel);

		contentPane = new JPanel();
		GridBagConstraints gbc_contentPane = new GridBagConstraints();
		gbc_contentPane.fill = GridBagConstraints.BOTH;
		gbc_contentPane.gridx = 0;
		gbc_contentPane.gridy = 1;
		getContentPane().add(contentPane, gbc_contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		KeyStroke fullscreen = KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0);
		((JComponent) getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(fullscreen, "fullscreen");
		((JComponent) getContentPane()).getActionMap().put("fullscreen", fullscreenAction);

		timer = new Timer(1000, this);
		timer.setInitialDelay(0);
		timer.start();
	}

	private static void initGUI() {
		janela = new Sistema();
		janela.updateUserLabel();
		janela.setVisible(true);
	}

	private static void initHotel() {
		Object leitura = Arquivo.carregaObjeto("hotel.dat");
		if(leitura instanceof Hotel) hotel = (Hotel) leitura;
		else hotel = new Hotel("Hotel Riviera Campina");
	}

	private static void initContas() {
		Object leitura = Arquivo.carregaObjeto("login.dat");
		if(leitura instanceof GerenciadorDeContas) gdc = (GerenciadorDeContas) leitura;
		else gdc = new GerenciadorDeContas();
	}

	private static void salvaEstado() {
		if(hotel != null) Arquivo.salvaObjeto(hotel, "hotel.dat");
		if(gdc != null) Arquivo.salvaObjeto(gdc, "login.dat");
	}

	/**
	 * Troca a tela atual para a tela dada.
	 *
	 * @param novaTela  a tela que se deseja mostrar
	 */
	public static void setTela(JPanel novaTela) {
		if(janela == null) initGUI();
		salvaEstado();

		if(novaTela == null)
			throw new IllegalArgumentException();

		janela.contentPane.removeAll();
		janela.contentPane.add(novaTela);
		janela.titleLabel.setText(novaTela.getName());
		janela.repaint();
	}

	/**
	 * Confere se o sistema esta rodando em tela cheia.
	 *
	 * @return true se o sistema estiver em tela cheia, false caso contrario
	 */
	public static boolean isFullscreen() {
		if(janela == null) return false;
		return janela.isUndecorated();
	}

	/**
	 * Ativa ou desativa a funcao de tela cheia do sistema.
	 */
	public static void toggleFullscreen() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

		if(janela == null || !gd.isFullScreenSupported())
			return;

		boolean fullscreen = !Sistema.isFullscreen();

		janela.dispose();
		janela.setUndecorated(fullscreen);
		janela.setVisible(true);

		if(fullscreen) gd.setFullScreenWindow(janela);
		else gd.setFullScreenWindow(null);

		FullscreenEvent event = new FullscreenEvent(janela, fullscreen);
		event.sendDown();
	}

	/**
	 * Retorna o objeto Hotel do sistema.
	 *
	 * @return o objeto usado para guardar informacoes sobre o hotel
	 */
	public static Hotel getHotel() {
		if(hotel == null) initHotel();
		return hotel;
	}

	/**
	 * Retorna o objeto GerenciadorDeContas do sistema.
	 *
	 * @return o objeto usado para guardar informacoes sobre as contas de usuario
	 */
	public static GerenciadorDeContas getContas() {
		if(gdc == null) initContas();
		return gdc;
	}

	/**
	 * Determina o usuario que esta acessando o sistema.
	 *
	 * @param usuario  o objeto que representa quem esta usando o sistema
	 */
	public static void fazLogin(Conta usuario) {
		Sistema.usuario = usuario;
		janela.updateUserLabel();
	}

	/**
	 * Retorna quem esta usando o sistema.
	 *
	 * @return  o objeto que representa quem esta usando o sistema
	 */
	public static Conta getUsuario() {
		return usuario;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		updateDateLabel();
	}

	private void updateUserLabel() {
		userLabel.setText(usuario == null? "" : "Usu\u00E1rio: "+usuario);
		logoutButton.setVisible(usuario != null);
	}

	private void updateDateLabel() {
		Calendar date = GregorianCalendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm  -  dd/MM/yyyy");
		String result = formatter.format(date.getTime());
		dateLabel.setText(result);

		if(!Sistema.isFullscreen()) dateLabel.setText("");
	}

	@Override
	public void fullscreenChanged(FullscreenEvent e) {
		updateDateLabel();
	}

	private class FullscreenAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			Sistema.toggleFullscreen();
		}
	}

	private class LogoutAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			Sistema.fazLogin(null);
			Sistema.setTela(new Login());
		}
	}
}
