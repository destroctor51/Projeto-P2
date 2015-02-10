package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import core.eventos.FullscreenEvent;
import core.eventos.FullscreenListener;
import core.login.Conta;
import core.login.GerenciadorDeContas;
import core.login.Permissao;

/**
 * A tela de login e responsavel por controlar a entrada de usuarios no sistema.
 * <p>
 * Se for a primeira vez que o programa e inicializado, a tela permite o cadastro do gerente.
 *
 * @author Victor Andrade de Almeida
 */
public class Login extends JPanel implements FullscreenListener {

	private static final long serialVersionUID = 1L;

	GerenciadorDeContas gdc = new GerenciadorDeContas("login.dat");

	private JInternalFrame loginFrame;
	private JTextField loginUserField;
	private JPasswordField loginPasswordField;
	private final Action confirmaLogin = new LoginAction();

	private JInternalFrame registerFrame;
	private JPasswordField registerPasswordField;
	private JTextField registerUserField;
	private JPasswordField registerConfirmPasswordField;
	private final Action confirmaCadastro = new RegisterAction();

	private JLabel registerErrorLabel;
	private JLabel loginErrorLabel;

	/**
	 * Cria a tela de login.
	 */
	public Login() {
		setLayout(new CardLayout(0, 0));

		JPanel registerCard = new JPanel();
		add(registerCard, "checagem");
		GridBagLayout gbl_registerCard = new GridBagLayout();
		gbl_registerCard.columnWeights = new double[]{};
		gbl_registerCard.rowWeights = new double[]{};
		registerCard.setLayout(gbl_registerCard);

		registerFrame = new JInternalFrame("Primeiro cadastro");
		registerFrame.setClosable(Sistema.isFullscreen());
		registerFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		registerFrame.setFrameIcon(null);
		GridBagConstraints gbc_registerFrame = new GridBagConstraints();
		gbc_registerFrame.insets = new Insets(0, 0, 5, 0);
		gbc_registerFrame.fill = GridBagConstraints.BOTH;
		gbc_registerFrame.gridx = 0;
		gbc_registerFrame.gridy = 0;
		registerCard.add(registerFrame, gbc_registerFrame);
		registerFrame.getContentPane().setLayout(new CardLayout(0, 0));

		JPanel registerPanel = new JPanel();
		registerPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		registerFrame.getContentPane().add(registerPanel, "name_22582440891546");
		GridBagLayout gbl_registerPanel = new GridBagLayout();
		gbl_registerPanel.columnWidths = new int[] {260};
		gbl_registerPanel.rowHeights = new int[] {14, 20, 14, 20, 14, 20, 10, 23};
		gbl_registerPanel.columnWeights = new double[]{0.0};
		gbl_registerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		registerPanel.setLayout(gbl_registerPanel);

		JLabel registerUserLabel = new JLabel("Novo gerente:");
		GridBagConstraints gbc_registerUserLabel = new GridBagConstraints();
		gbc_registerUserLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_registerUserLabel.insets = new Insets(0, 0, 5, 0);
		gbc_registerUserLabel.gridx = 0;
		gbc_registerUserLabel.gridy = 0;
		registerPanel.add(registerUserLabel, gbc_registerUserLabel);

		registerUserField = new JTextField();
		GridBagConstraints gbc_registerUserField = new GridBagConstraints();
		gbc_registerUserField.fill = GridBagConstraints.HORIZONTAL;
		gbc_registerUserField.anchor = GridBagConstraints.NORTH;
		gbc_registerUserField.insets = new Insets(0, 0, 5, 0);
		gbc_registerUserField.gridx = 0;
		gbc_registerUserField.gridy = 1;
		registerPanel.add(registerUserField, gbc_registerUserField);
		registerUserField.setColumns(10);

		JLabel registerPasswordLabel = new JLabel("Nova senha:");
		GridBagConstraints gbc_registerPasswordLabel = new GridBagConstraints();
		gbc_registerPasswordLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_registerPasswordLabel.insets = new Insets(0, 0, 5, 0);
		gbc_registerPasswordLabel.gridx = 0;
		gbc_registerPasswordLabel.gridy = 2;
		registerPanel.add(registerPasswordLabel, gbc_registerPasswordLabel);

		registerPasswordField = new JPasswordField();
		GridBagConstraints gbc_registerPasswordField = new GridBagConstraints();
		gbc_registerPasswordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_registerPasswordField.anchor = GridBagConstraints.NORTH;
		gbc_registerPasswordField.insets = new Insets(0, 0, 5, 0);
		gbc_registerPasswordField.gridx = 0;
		gbc_registerPasswordField.gridy = 3;
		registerPanel.add(registerPasswordField, gbc_registerPasswordField);

		JLabel registerConfirmPasswordLabel = new JLabel("Repita a senha:");
		GridBagConstraints gbc_registerConfirmPasswordLabel = new GridBagConstraints();
		gbc_registerConfirmPasswordLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_registerConfirmPasswordLabel.insets = new Insets(0, 0, 5, 0);
		gbc_registerConfirmPasswordLabel.gridx = 0;
		gbc_registerConfirmPasswordLabel.gridy = 4;
		registerPanel.add(registerConfirmPasswordLabel, gbc_registerConfirmPasswordLabel);

		registerConfirmPasswordField = new JPasswordField();
		GridBagConstraints gbc_registerConfirmPasswordField = new GridBagConstraints();
		gbc_registerConfirmPasswordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_registerConfirmPasswordField.anchor = GridBagConstraints.NORTH;
		gbc_registerConfirmPasswordField.insets = new Insets(0, 0, 5, 0);
		gbc_registerConfirmPasswordField.gridx = 0;
		gbc_registerConfirmPasswordField.gridy = 5;
		registerPanel.add(registerConfirmPasswordField, gbc_registerConfirmPasswordField);

		Component registerRigidArea = Box.createRigidArea(new Dimension(260, 10));
		GridBagConstraints gbc_registerRigidArea = new GridBagConstraints();
		gbc_registerRigidArea.anchor = GridBagConstraints.NORTHWEST;
		gbc_registerRigidArea.insets = new Insets(0, 0, 5, 0);
		gbc_registerRigidArea.gridx = 0;
		gbc_registerRigidArea.gridy = 6;
		registerPanel.add(registerRigidArea, gbc_registerRigidArea);

		JPanel registerBottomPanel = new JPanel();
		GridBagConstraints gbc_registerBottomPanel = new GridBagConstraints();
		gbc_registerBottomPanel.anchor = GridBagConstraints.NORTHWEST;
		gbc_registerBottomPanel.gridx = 0;
		gbc_registerBottomPanel.gridy = 7;
		registerPanel.add(registerBottomPanel, gbc_registerBottomPanel);
		GridBagLayout gbl_registerBottomPanel = new GridBagLayout();
		gbl_registerBottomPanel.columnWidths = new int[] {180, 80};
		gbl_registerBottomPanel.columnWeights = new double[]{0.0, 0.0};
		gbl_registerBottomPanel.rowWeights = new double[]{0.0};
		registerBottomPanel.setLayout(gbl_registerBottomPanel);

		registerErrorLabel = new JLabel("");
		registerErrorLabel.setVisible(false);
		registerErrorLabel.setIcon(new ImageIcon(Login.class.getResource("/gui/images/error.png")));
		registerErrorLabel.setForeground(Color.RED);
		GridBagConstraints gbc_registerErrorLabel = new GridBagConstraints();
		gbc_registerErrorLabel.anchor = GridBagConstraints.LINE_START;
		gbc_registerErrorLabel.insets = new Insets(0, 0, 0, 5);
		gbc_registerErrorLabel.gridx = 0;
		gbc_registerErrorLabel.gridy = 0;
		registerBottomPanel.add(registerErrorLabel, gbc_registerErrorLabel);

		JButton registerButton = new JButton("New button");
		GridBagConstraints gbc_registerButton = new GridBagConstraints();
		gbc_registerButton.anchor = GridBagConstraints.LINE_END;
		gbc_registerButton.gridx = 1;
		gbc_registerButton.gridy = 0;
		registerBottomPanel.add(registerButton, gbc_registerButton);
		registerButton.setAction(confirmaCadastro);
		registerFrame.setVisible(true);

		JPanel loginCard = new JPanel();
		add(loginCard, "login");
		GridBagLayout gbl_loginCard = new GridBagLayout();
		gbl_loginCard.columnWeights = new double[]{};
		gbl_loginCard.rowWeights = new double[]{};
		loginCard.setLayout(gbl_loginCard);

		loginFrame = new JInternalFrame("Login");
		loginFrame.setClosable(Sistema.isFullscreen());
		loginFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		loginFrame.setFrameIcon(null);
		GridBagConstraints gbc_loginFrame = new GridBagConstraints();
		gbc_loginFrame.fill = GridBagConstraints.BOTH;
		gbc_loginFrame.gridx = 0;
		gbc_loginFrame.gridy = 0;
		loginCard.add(loginFrame, gbc_loginFrame);
		loginFrame.getContentPane().setLayout(new CardLayout(0, 0));

		JPanel loginPanel = new JPanel();
		loginPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		loginFrame.getContentPane().add(loginPanel, "name_23083868703421");
		GridBagLayout gbl_loginPanel = new GridBagLayout();
		gbl_loginPanel.columnWidths = new int[]{260, 0};
		gbl_loginPanel.rowHeights = new int[]{14, 20, 14, 20, 10, 23, 0};
		gbl_loginPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_loginPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		loginPanel.setLayout(gbl_loginPanel);

		JLabel loginUserLabel = new JLabel("Usu\u00E1rio:");
		GridBagConstraints gbc_loginUserLabel = new GridBagConstraints();
		gbc_loginUserLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_loginUserLabel.insets = new Insets(0, 0, 5, 0);
		gbc_loginUserLabel.gridx = 0;
		gbc_loginUserLabel.gridy = 0;
		loginPanel.add(loginUserLabel, gbc_loginUserLabel);

		loginUserField = new JTextField();
		GridBagConstraints gbc_loginUserField = new GridBagConstraints();
		gbc_loginUserField.fill = GridBagConstraints.HORIZONTAL;
		gbc_loginUserField.anchor = GridBagConstraints.NORTH;
		gbc_loginUserField.insets = new Insets(0, 0, 5, 0);
		gbc_loginUserField.gridx = 0;
		gbc_loginUserField.gridy = 1;
		loginPanel.add(loginUserField, gbc_loginUserField);
		loginUserField.setColumns(10);

		JLabel loginPasswordLabel = new JLabel("Senha:");
		GridBagConstraints gbc_loginPasswordLabel = new GridBagConstraints();
		gbc_loginPasswordLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_loginPasswordLabel.insets = new Insets(0, 0, 5, 0);
		gbc_loginPasswordLabel.gridx = 0;
		gbc_loginPasswordLabel.gridy = 2;
		loginPanel.add(loginPasswordLabel, gbc_loginPasswordLabel);

		loginPasswordField = new JPasswordField();
		GridBagConstraints gbc_loginPasswordField = new GridBagConstraints();
		gbc_loginPasswordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_loginPasswordField.anchor = GridBagConstraints.NORTH;
		gbc_loginPasswordField.insets = new Insets(0, 0, 5, 0);
		gbc_loginPasswordField.gridx = 0;
		gbc_loginPasswordField.gridy = 3;
		loginPanel.add(loginPasswordField, gbc_loginPasswordField);

		Component loginRigidArea = Box.createRigidArea(new Dimension(260, 10));
		GridBagConstraints gbc_loginRigidArea = new GridBagConstraints();
		gbc_loginRigidArea.anchor = GridBagConstraints.NORTHWEST;
		gbc_loginRigidArea.insets = new Insets(0, 0, 5, 0);
		gbc_loginRigidArea.gridx = 0;
		gbc_loginRigidArea.gridy = 4;
		loginPanel.add(loginRigidArea, gbc_loginRigidArea);

		JPanel loginBottomPanel = new JPanel();
		GridBagConstraints gbc_loginBottomPanel = new GridBagConstraints();
		gbc_loginBottomPanel.anchor = GridBagConstraints.NORTHWEST;
		gbc_loginBottomPanel.gridx = 0;
		gbc_loginBottomPanel.gridy = 5;
		loginPanel.add(loginBottomPanel, gbc_loginBottomPanel);
		GridBagLayout gbl_loginBottomPanel = new GridBagLayout();
		gbl_loginBottomPanel.columnWidths = new int[] {180, 80};
		gbl_loginBottomPanel.columnWeights = new double[]{0.0, 0.0};
		gbl_loginBottomPanel.rowWeights = new double[]{0.0};
		loginBottomPanel.setLayout(gbl_loginBottomPanel);

		loginErrorLabel = new JLabel("");
		loginErrorLabel.setVisible(false);
		loginErrorLabel.setIcon(new ImageIcon(Login.class.getResource("/gui/images/error.png")));
		loginErrorLabel.setForeground(Color.RED);
		GridBagConstraints gbc_loginErrorLabel = new GridBagConstraints();
		gbc_loginErrorLabel.anchor = GridBagConstraints.LINE_START;
		gbc_loginErrorLabel.insets = new Insets(0, 0, 0, 5);
		gbc_loginErrorLabel.gridx = 0;
		gbc_loginErrorLabel.gridy = 0;
		loginBottomPanel.add(loginErrorLabel, gbc_loginErrorLabel);

		JButton loginButton = new JButton("");
		GridBagConstraints gbc_loginButton = new GridBagConstraints();
		gbc_loginButton.anchor = GridBagConstraints.LINE_END;
		gbc_loginButton.gridx = 1;
		gbc_loginButton.gridy = 0;
		loginBottomPanel.add(loginButton, gbc_loginButton);
		loginButton.setAction(confirmaLogin);
		loginFrame.setVisible(true);

		KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
		registerFrame.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(enter, "confirm");
		registerFrame.getActionMap().put("confirm", confirmaCadastro);
		loginFrame.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(enter, "confirm");
		loginFrame.getActionMap().put("confirm", confirmaLogin);

		InternalFrameAdapter close = new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				System.exit(0);
			}
		};

		registerFrame.addInternalFrameListener(close);
		loginFrame.addInternalFrameListener(close);

		realizaChecagem();
	}

	private void realizaChecagem() {
		if(!gdc.vazio()) permiteLogin();
	}

	private void permiteLogin() {
		CardLayout cardLayout = (CardLayout) getLayout();
		cardLayout.show(this, "login");
	}

	private class RegisterAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public RegisterAction() {
			putValue(NAME, "Cadastra");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String id = registerUserField.getText();
			char[] senha = registerPasswordField.getPassword();
			char[] confereSenha = registerConfirmPasswordField.getPassword();

			if(registerUserField.getText().length() < 3 ) {
				registerErrorLabel.setText("Nome menor que 3 caract\u00E9res");
				registerErrorLabel.setVisible(true);
			}

			else if(registerPasswordField.getPassword().length < 5) {
				registerErrorLabel.setText("Senha menor que 5 caract\u00E9res");
				registerErrorLabel.setVisible(true);
			}

			else if(!Arrays.equals(senha, confereSenha)) {
				registerErrorLabel.setText("Senha n\u00E3o confere");
				registerErrorLabel.setVisible(true);
			}

			else {
				Conta usuario = gdc.cadastra(id, new String(senha), Permissao.GERENTE);
				Sistema.fazLogin(usuario);
				gdc.salvaContas();

				Sistema.setTela(new Menu());
			}

			for(int i=0; i<senha.length; i++)
				senha[i] = 0;
			for(int i=0; i<confereSenha.length; i++)
				confereSenha[i] = 0;
			registerPasswordField.setText("");
			registerConfirmPasswordField.setText("");
		}
	}

	private class LoginAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		public LoginAction() {
			putValue(NAME, "Confirma");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String id = loginUserField.getText();
			char[] senha = loginPasswordField.getPassword();
			Conta usuario = gdc.login(id, new String(senha));

			if(usuario == null) {
				loginErrorLabel.setText("Login inv\u00E1lido");
				loginErrorLabel.setVisible(true);
			}

			else {
				Sistema.fazLogin(usuario);
				Sistema.setTela(new Menu());
			}

			for(int i=0; i<senha.length; i++)
				senha[i] = 0;
			loginPasswordField.setText("");
		}
	}

	@Override
	public void fullscreenChanged(FullscreenEvent e) {
		if(e.isFullscreen()) {
			loginFrame.setClosable(true);
			registerFrame.setClosable(true);
		}

		else {
			loginFrame.setClosable(false);
			registerFrame.setClosable(false);
		}
	}
}
