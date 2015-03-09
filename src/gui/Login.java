package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import javax.swing.AbstractAction;
import javax.swing.Action;
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

import utils.Internet;
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

	private GerenciadorDeContas gdc = Sistema.getContas();

	private JInternalFrame loginFrame;
	private JTextField loginUserField;
	private JPasswordField loginPasswordField;
	private JButton loginRecuperacao;
	private final Action confirmaLogin = new LoginAction();

	private JInternalFrame registerFrame;
	private JPasswordField registerPasswordField;
	private JTextField registerUserField;
	private JPasswordField registerConfirmPasswordField;
	private final Action confirmaCadastro = new RegisterAction();

	private JLabel registerErrorLabel;
	private JLabel loginErrorLabel;
	private JTextField registerNameField;
	private JTextField registerEmailField;

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
		gbl_registerPanel.rowHeights = new int[] {14, 20, 0, 0, 0, 0, 14, 20, 14, 20, 23};
		gbl_registerPanel.columnWeights = new double[]{1.0};
		gbl_registerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		registerPanel.setLayout(gbl_registerPanel);

		JLabel registerUserLabel = new JLabel("Nome de usu\u00E1rio:");
		GridBagConstraints gbc_registerUserLabel = new GridBagConstraints();
		gbc_registerUserLabel.anchor = GridBagConstraints.WEST;
		gbc_registerUserLabel.insets = new Insets(0, 1, 5, 0);
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

		JLabel registerNameLabel = new JLabel("Nome real:");
		GridBagConstraints gbc_registerNameLabel = new GridBagConstraints();
		gbc_registerNameLabel.anchor = GridBagConstraints.WEST;
		gbc_registerNameLabel.insets = new Insets(0, 1, 5, 0);
		gbc_registerNameLabel.gridx = 0;
		gbc_registerNameLabel.gridy = 2;
		registerPanel.add(registerNameLabel, gbc_registerNameLabel);

		registerNameField = new JTextField();
		GridBagConstraints gbc_registerNameField = new GridBagConstraints();
		gbc_registerNameField.insets = new Insets(0, 0, 5, 0);
		gbc_registerNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_registerNameField.gridx = 0;
		gbc_registerNameField.gridy = 3;
		registerPanel.add(registerNameField, gbc_registerNameField);
		registerNameField.setColumns(10);

		JLabel registerEmailLabel = new JLabel("E-mail:");
		GridBagConstraints gbc_registerEmailLabel = new GridBagConstraints();
		gbc_registerEmailLabel.anchor = GridBagConstraints.WEST;
		gbc_registerEmailLabel.insets = new Insets(0, 1, 5, 0);
		gbc_registerEmailLabel.gridx = 0;
		gbc_registerEmailLabel.gridy = 4;
		registerPanel.add(registerEmailLabel, gbc_registerEmailLabel);

		registerEmailField = new JTextField();
		GridBagConstraints gbc_registerEmailField = new GridBagConstraints();
		gbc_registerEmailField.insets = new Insets(0, 0, 5, 0);
		gbc_registerEmailField.fill = GridBagConstraints.HORIZONTAL;
		gbc_registerEmailField.gridx = 0;
		gbc_registerEmailField.gridy = 5;
		registerPanel.add(registerEmailField, gbc_registerEmailField);
		registerEmailField.setColumns(10);

		JLabel registerPasswordLabel = new JLabel("Nova senha:");
		GridBagConstraints gbc_registerPasswordLabel = new GridBagConstraints();
		gbc_registerPasswordLabel.anchor = GridBagConstraints.WEST;
		gbc_registerPasswordLabel.insets = new Insets(0, 1, 5, 0);
		gbc_registerPasswordLabel.gridx = 0;
		gbc_registerPasswordLabel.gridy = 6;
		registerPanel.add(registerPasswordLabel, gbc_registerPasswordLabel);

		registerPasswordField = new JPasswordField();
		GridBagConstraints gbc_registerPasswordField = new GridBagConstraints();
		gbc_registerPasswordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_registerPasswordField.anchor = GridBagConstraints.NORTH;
		gbc_registerPasswordField.insets = new Insets(0, 0, 5, 0);
		gbc_registerPasswordField.gridx = 0;
		gbc_registerPasswordField.gridy = 7;
		registerPanel.add(registerPasswordField, gbc_registerPasswordField);

		JLabel registerConfirmPasswordLabel = new JLabel("Repita a senha:");
		GridBagConstraints gbc_registerConfirmPasswordLabel = new GridBagConstraints();
		gbc_registerConfirmPasswordLabel.anchor = GridBagConstraints.WEST;
		gbc_registerConfirmPasswordLabel.insets = new Insets(0, 1, 5, 0);
		gbc_registerConfirmPasswordLabel.gridx = 0;
		gbc_registerConfirmPasswordLabel.gridy = 8;
		registerPanel.add(registerConfirmPasswordLabel, gbc_registerConfirmPasswordLabel);

		registerConfirmPasswordField = new JPasswordField();
		GridBagConstraints gbc_registerConfirmPasswordField = new GridBagConstraints();
		gbc_registerConfirmPasswordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_registerConfirmPasswordField.anchor = GridBagConstraints.NORTH;
		gbc_registerConfirmPasswordField.insets = new Insets(0, 0, 15, 0);
		gbc_registerConfirmPasswordField.gridx = 0;
		gbc_registerConfirmPasswordField.gridy = 9;
		registerPanel.add(registerConfirmPasswordField, gbc_registerConfirmPasswordField);

		JPanel registerBottomPanel = new JPanel();
		GridBagConstraints gbc_registerBottomPanel = new GridBagConstraints();
		gbc_registerBottomPanel.anchor = GridBagConstraints.NORTHWEST;
		gbc_registerBottomPanel.gridx = 0;
		gbc_registerBottomPanel.gridy = 10;
		registerPanel.add(registerBottomPanel, gbc_registerBottomPanel);
		GridBagLayout gbl_registerBottomPanel = new GridBagLayout();
		gbl_registerBottomPanel.columnWidths = new int[] {200, 0};
		gbl_registerBottomPanel.columnWeights = new double[]{0.0, 1.0};
		gbl_registerBottomPanel.rowWeights = new double[]{0.0};
		registerBottomPanel.setLayout(gbl_registerBottomPanel);

		registerErrorLabel = new JLabel("");
		registerErrorLabel.setVisible(false);
		registerErrorLabel.setIcon(new ImageIcon(Login.class.getResource("/gui/resources/error.png")));
		registerErrorLabel.setForeground(Color.RED);
		GridBagConstraints gbc_registerErrorLabel = new GridBagConstraints();
		gbc_registerErrorLabel.anchor = GridBagConstraints.LINE_START;
		gbc_registerErrorLabel.insets = new Insets(0, 0, 0, 5);
		gbc_registerErrorLabel.gridx = 0;
		gbc_registerErrorLabel.gridy = 0;
		registerBottomPanel.add(registerErrorLabel, gbc_registerErrorLabel);

		JButton registerButton = new JButton("New button");
		registerButton.setFocusable(false);
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
		gbl_loginPanel.columnWidths = new int[] {0};
		gbl_loginPanel.rowHeights = new int[] {0, 0, 0, 0, 0};
		gbl_loginPanel.columnWeights = new double[]{1.0};
		gbl_loginPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		loginPanel.setLayout(gbl_loginPanel);

		JLabel loginUserLabel = new JLabel("Usu\u00E1rio:");
		GridBagConstraints gbc_loginUserLabel = new GridBagConstraints();
		gbc_loginUserLabel.anchor = GridBagConstraints.WEST;
		gbc_loginUserLabel.insets = new Insets(0, 1, 5, 0);
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

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		loginPanel.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0, 0};
		gbl_panel.rowHeights = new int[] {15};
		gbl_panel.columnWeights = new double[]{0.0, 1.0};
		gbl_panel.rowWeights = new double[]{0.0};
		panel.setLayout(gbl_panel);

		JLabel loginPasswordLabel = new JLabel("Senha:");
		GridBagConstraints gbc_loginPasswordLabel = new GridBagConstraints();
		gbc_loginPasswordLabel.insets = new Insets(0, 1, 0, 5);
		gbc_loginPasswordLabel.anchor = GridBagConstraints.WEST;
		gbc_loginPasswordLabel.gridx = 0;
		gbc_loginPasswordLabel.gridy = 0;
		panel.add(loginPasswordLabel, gbc_loginPasswordLabel);

		loginRecuperacao = new JButton("<html><u>Esqueci minha senha</u></html>");
		loginRecuperacao.setBorder(null);
		loginRecuperacao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				final Conta conta = gdc.getConta(loginUserField.getText());
				loginRecuperacao.setForeground(new Color(100, 0, 155));

				if(conta == null)
					printMensagem("Usu\u00E1rio n\u00E3o existente", true, loginErrorLabel);

				else {
					new Thread() {
						@Override
						public void run() {
							Internet.enviaEmail(conta.getEmail(), "Recupera\u00E7\u00E3o de conta",
									"<html>Ol\u00E1 "+conta.getNome()+",<br><br>"+
											"Aqui est\u00E1 sua senha: <strong>"+conta.getSenha()+"</strong><br><br>"+
											"Cuidado para n\u00E3o perd\u00EA-la novamente!<br>"+
											"Caso voc\u00EA n\u00E3o tenha pedido esse e-mail, fale com seu gerente,<br>"+
											"pois a seguran\u00E7a de sua conta pode estar em risco!<br><br>"+
											"Atenciosamente,<br>"+
									"O time do Riviera Hotel</html>");
						}
					}.start();

					loginRecuperacao.setVisible(false);
					loginRecuperacao.setForeground(new Color(0, 0, 255));
					printMensagem("E-mail enviado", false, loginErrorLabel);
				}
			}
		});
		loginRecuperacao.setVisible(false);
		loginRecuperacao.setForeground(new Color(0, 0, 255));
		loginRecuperacao.setContentAreaFilled(false);
		GridBagConstraints gbc_loginRecuperacao = new GridBagConstraints();
		gbc_loginRecuperacao.anchor = GridBagConstraints.EAST;
		gbc_loginRecuperacao.gridx = 1;
		gbc_loginRecuperacao.gridy = 0;
		panel.add(loginRecuperacao, gbc_loginRecuperacao);

		loginPasswordField = new JPasswordField();
		GridBagConstraints gbc_loginPasswordField = new GridBagConstraints();
		gbc_loginPasswordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_loginPasswordField.anchor = GridBagConstraints.NORTH;
		gbc_loginPasswordField.insets = new Insets(0, 0, 15, 0);
		gbc_loginPasswordField.gridx = 0;
		gbc_loginPasswordField.gridy = 3;
		loginPanel.add(loginPasswordField, gbc_loginPasswordField);

		JPanel loginBottomPanel = new JPanel();
		GridBagConstraints gbc_loginBottomPanel = new GridBagConstraints();
		gbc_loginBottomPanel.anchor = GridBagConstraints.NORTHWEST;
		gbc_loginBottomPanel.gridx = 0;
		gbc_loginBottomPanel.gridy = 4;
		loginPanel.add(loginBottomPanel, gbc_loginBottomPanel);
		GridBagLayout gbl_loginBottomPanel = new GridBagLayout();
		gbl_loginBottomPanel.columnWidths = new int[] {200, 80};
		gbl_loginBottomPanel.columnWeights = new double[]{0.0, 0.0};
		gbl_loginBottomPanel.rowWeights = new double[]{0.0};
		loginBottomPanel.setLayout(gbl_loginBottomPanel);

		loginErrorLabel = new JLabel("<erro>");
		loginErrorLabel.setVisible(false);
		loginErrorLabel.setIcon(new ImageIcon(Login.class.getResource("/gui/resources/error.png")));
		loginErrorLabel.setForeground(Color.RED);
		GridBagConstraints gbc_loginErrorLabel = new GridBagConstraints();
		gbc_loginErrorLabel.anchor = GridBagConstraints.LINE_START;
		gbc_loginErrorLabel.insets = new Insets(0, 0, 0, 5);
		gbc_loginErrorLabel.gridx = 0;
		gbc_loginErrorLabel.gridy = 0;
		loginBottomPanel.add(loginErrorLabel, gbc_loginErrorLabel);

		JButton loginButton = new JButton("");
		loginButton.setFocusable(false);
		loginButton.setFocusPainted(false);
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

	private void printMensagem(String mensagem, boolean isErro, JLabel alvo) {
		String icone = isErro? "/gui/resources/error.png" : "/gui/resources/success.png";
		alvo.setForeground(isErro? Color.RED : new Color(0, 150, 0));
		alvo.setIcon(new ImageIcon(Login.class.getResource(icone)));
		alvo.setText(mensagem);
		alvo.setVisible(true);
	}

	private class RegisterAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public RegisterAction() {
			putValue(NAME, "Cadastra");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String id = registerUserField.getText();
			String nome = registerNameField.getText();
			String email = registerEmailField.getText();
			char[] senha = registerPasswordField.getPassword();
			char[] confereSenha = registerConfirmPasswordField.getPassword();

			if(registerUserField.getText().length() < 3 )
				printMensagem("Nome menor que 3 caract\u00E9res", true, registerErrorLabel);

			else if(nome.length() < 3)
				printMensagem("Nome real menor que 3 caract\u00E9res", true, registerErrorLabel);

			else if(!Internet.isEmailValido(email))
				printMensagem("Email inv\u00E1lido", true, registerErrorLabel);

			else if(!Arrays.equals(senha, confereSenha))
				printMensagem("Senha n\u00E3o confere", true, registerErrorLabel);

			else if(registerPasswordField.getPassword().length < 5)
				printMensagem("Senha menor que 5 caract\u00E9res", true, registerErrorLabel);

			else {
				Conta usuario = gdc.cadastra(id, Permissao.GERENTE, nome, email, new String(senha));
				Sistema.fazLogin(usuario);
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
				printMensagem("Login inv\u00E1lido", true, loginErrorLabel);
				loginRecuperacao.setVisible(true);
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
		loginFrame.setClosable(e.isFullscreen());
		registerFrame.setClosable(e.isFullscreen());
	}
}
