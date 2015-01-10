package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.Arrays;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import login.Conta;
import login.GerenciadorDeContas;
import login.Permissao;

/**
 * A tela de login e responsavel por controlar a entrada de usuarios no sistema.
 * <p>
 * Se for a primeira vez que o programa e inicializado, a tela permite o cadastro do gerente.
 * 
 * @author Victor Andrade de Almeida
 */
public class Login extends JPanel {

	private static final long serialVersionUID = 1L;
	
	GerenciadorDeContas gdc = new GerenciadorDeContas("login.dat");
	
	private JTextField loginUserField;
	private JPasswordField loginPasswordField;
	private final Action confimaLogin = new LoginAction();
	private JButton loginButton;
	
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
		
		JPanel checkPanel = new JPanel();
		add(checkPanel, "checagem");
		GridBagLayout gbl_checkPanel = new GridBagLayout();
		gbl_checkPanel.columnWeights = new double[]{};
		gbl_checkPanel.rowWeights = new double[]{};
		checkPanel.setLayout(gbl_checkPanel);
		
		JInternalFrame firstLogin = new JInternalFrame("Primeiro cad\u00E1stro");
		firstLogin.setFrameIcon(null);
		GridBagConstraints gbc_firstLogin = new GridBagConstraints();
		gbc_firstLogin.fill = GridBagConstraints.BOTH;
		gbc_firstLogin.gridx = 0;
		gbc_firstLogin.gridy = 0;
		checkPanel.add(firstLogin, gbc_firstLogin);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0};
		firstLogin.getContentPane().setLayout(gridBagLayout);
		
		Component registerUpStrut = Box.createVerticalStrut(10);
		GridBagConstraints gbc_registerUpStrut = new GridBagConstraints();
		gbc_registerUpStrut.fill = GridBagConstraints.BOTH;
		gbc_registerUpStrut.insets = new Insets(0, 0, 5, 5);
		gbc_registerUpStrut.gridx = 1;
		gbc_registerUpStrut.gridy = 0;
		firstLogin.getContentPane().add(registerUpStrut, gbc_registerUpStrut);
		
		JLabel registerUserLabel = new JLabel("Novo gerente:");
		GridBagConstraints gbc_registerUserLabel = new GridBagConstraints();
		gbc_registerUserLabel.anchor = GridBagConstraints.LINE_START;
		gbc_registerUserLabel.insets = new Insets(0, 0, 5, 5);
		gbc_registerUserLabel.gridx = 1;
		gbc_registerUserLabel.gridy = 1;
		firstLogin.getContentPane().add(registerUserLabel, gbc_registerUserLabel);
		
		registerUserField = new JTextField();
		GridBagConstraints gbc_registerUserField = new GridBagConstraints();
		gbc_registerUserField.insets = new Insets(0, 0, 5, 5);
		gbc_registerUserField.fill = GridBagConstraints.HORIZONTAL;
		gbc_registerUserField.gridx = 1;
		gbc_registerUserField.gridy = 2;
		firstLogin.getContentPane().add(registerUserField, gbc_registerUserField);
		registerUserField.setColumns(10);
		
		JLabel registerPasswordLabel = new JLabel("Nova senha:");
		GridBagConstraints gbc_registerPasswordLabel = new GridBagConstraints();
		gbc_registerPasswordLabel.anchor = GridBagConstraints.LINE_START;
		gbc_registerPasswordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_registerPasswordLabel.gridx = 1;
		gbc_registerPasswordLabel.gridy = 3;
		firstLogin.getContentPane().add(registerPasswordLabel, gbc_registerPasswordLabel);
		
		registerPasswordField = new JPasswordField();
		GridBagConstraints gbc_registerPasswordField;
		gbc_registerPasswordField = new GridBagConstraints();
		gbc_registerPasswordField.insets = new Insets(0, 0, 5, 5);
		gbc_registerPasswordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_registerPasswordField.gridx = 1;
		gbc_registerPasswordField.gridy = 4;
		firstLogin.getContentPane().add(registerPasswordField, gbc_registerPasswordField);
		
		JLabel registerConfirmPasswordLabel = new JLabel("Repita a senha:");
		GridBagConstraints gbc_registerConfirmPasswordLabel = new GridBagConstraints();
		gbc_registerConfirmPasswordLabel.anchor = GridBagConstraints.LINE_START;
		gbc_registerConfirmPasswordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_registerConfirmPasswordLabel.gridx = 1;
		gbc_registerConfirmPasswordLabel.gridy = 5;
		firstLogin.getContentPane().add(registerConfirmPasswordLabel, gbc_registerConfirmPasswordLabel);
		
		registerConfirmPasswordField = new JPasswordField();
		GridBagConstraints gbc_registerConfirmPasswordField = new GridBagConstraints();
		gbc_registerConfirmPasswordField.insets = new Insets(0, 0, 5, 5);
		gbc_registerConfirmPasswordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_registerConfirmPasswordField.gridx = 1;
		gbc_registerConfirmPasswordField.gridy = 6;
		firstLogin.getContentPane().add(registerConfirmPasswordField, gbc_registerConfirmPasswordField);
		
		Component registerRigidArea = Box.createRigidArea(new Dimension(260, 10));
		GridBagConstraints gbc_registerRigidArea = new GridBagConstraints();
		gbc_registerRigidArea.fill = GridBagConstraints.BOTH;
		gbc_registerRigidArea.insets = new Insets(0, 0, 5, 5);
		gbc_registerRigidArea.gridx = 1;
		gbc_registerRigidArea.gridy = 7;
		firstLogin.getContentPane().add(registerRigidArea, gbc_registerRigidArea);
		
		Component registerLeftStrut = Box.createHorizontalStrut(10);
		GridBagConstraints gbc_registerLeftStrut = new GridBagConstraints();
		gbc_registerLeftStrut.fill = GridBagConstraints.BOTH;
		gbc_registerLeftStrut.insets = new Insets(0, 0, 5, 5);
		gbc_registerLeftStrut.gridx = 0;
		gbc_registerLeftStrut.gridy = 7;
		firstLogin.getContentPane().add(registerLeftStrut, gbc_registerLeftStrut);
		
		Component registerRightStrut = Box.createHorizontalStrut(10);
		GridBagConstraints gbc_registerRightStrut = new GridBagConstraints();
		gbc_registerRightStrut.insets = new Insets(0, 0, 5, 5);
		gbc_registerRightStrut.fill = GridBagConstraints.BOTH;
		gbc_registerRightStrut.gridx = 2;
		gbc_registerRightStrut.gridy = 7;
		firstLogin.getContentPane().add(registerRightStrut, gbc_registerRightStrut);
		
		JPanel registerBottomPanel = new JPanel();
		GridBagConstraints gbc_registerBottomPanel = new GridBagConstraints();
		gbc_registerBottomPanel.insets = new Insets(0, 0, 5, 5);
		gbc_registerBottomPanel.fill = GridBagConstraints.BOTH;
		gbc_registerBottomPanel.gridx = 1;
		gbc_registerBottomPanel.gridy = 8;
		firstLogin.getContentPane().add(registerBottomPanel, gbc_registerBottomPanel);
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
		
		Component registerDownStrut = Box.createVerticalStrut(10);
		GridBagConstraints gbc_registerDownStrut = new GridBagConstraints();
		gbc_registerDownStrut.insets = new Insets(0, 0, 0, 5);
		gbc_registerDownStrut.fill = GridBagConstraints.BOTH;
		gbc_registerDownStrut.gridx = 1;
		gbc_registerDownStrut.gridy = 9;
		firstLogin.getContentPane().add(registerDownStrut, gbc_registerDownStrut);
		firstLogin.setVisible(true);
		
		JPanel loginPanel = new JPanel();
		add(loginPanel, "login");
		GridBagLayout gbl_loginPanel = new GridBagLayout();
		gbl_loginPanel.columnWeights = new double[]{};
		gbl_loginPanel.rowWeights = new double[]{};
		loginPanel.setLayout(gbl_loginPanel);
		
		JInternalFrame loginFrame = new JInternalFrame("Login");
		loginFrame.setFrameIcon(null);
		GridBagConstraints gbc_loginFrame = new GridBagConstraints();
		gbc_loginFrame.fill = GridBagConstraints.BOTH;
		gbc_loginFrame.gridx = 0;
		gbc_loginFrame.gridy = 0;
		loginPanel.add(loginFrame, gbc_loginFrame);
		GridBagLayout gbl_loginFrame = new GridBagLayout();
		gbl_loginFrame.columnWeights = new double[]{0.0, 1.0, 0.0};
		gbl_loginFrame.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0};
		loginFrame.getContentPane().setLayout(gbl_loginFrame);
		
		Component loginUpStrut = Box.createVerticalStrut(10);
		GridBagConstraints gbc_loginUpStrut = new GridBagConstraints();
		gbc_loginUpStrut.fill = GridBagConstraints.BOTH;
		gbc_loginUpStrut.insets = new Insets(0, 0, 5, 5);
		gbc_loginUpStrut.gridx = 1;
		gbc_loginUpStrut.gridy = 0;
		loginFrame.getContentPane().add(loginUpStrut, gbc_loginUpStrut);
		
		JLabel loginUserLabel = new JLabel("Usu\u00E1rio:");
		GridBagConstraints gbc_loginUserLabel = new GridBagConstraints();
		gbc_loginUserLabel.anchor = GridBagConstraints.LINE_START;
		gbc_loginUserLabel.insets = new Insets(0, 0, 5, 5);
		gbc_loginUserLabel.gridx = 1;
		gbc_loginUserLabel.gridy = 1;
		loginFrame.getContentPane().add(loginUserLabel, gbc_loginUserLabel);
		
		loginUserField = new JTextField();
		GridBagConstraints gbc_loginUserField = new GridBagConstraints();
		gbc_loginUserField.insets = new Insets(0, 0, 5, 5);
		gbc_loginUserField.fill = GridBagConstraints.HORIZONTAL;
		gbc_loginUserField.gridx = 1;
		gbc_loginUserField.gridy = 2;
		loginFrame.getContentPane().add(loginUserField, gbc_loginUserField);
		loginUserField.setColumns(10);
		
		JLabel loginPasswordLabel = new JLabel("Senha:");
		GridBagConstraints gbc_loginPasswordLabel = new GridBagConstraints();
		gbc_loginPasswordLabel.anchor = GridBagConstraints.LINE_START;
		gbc_loginPasswordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_loginPasswordLabel.gridx = 1;
		gbc_loginPasswordLabel.gridy = 3;
		loginFrame.getContentPane().add(loginPasswordLabel, gbc_loginPasswordLabel);
		
		loginPasswordField = new JPasswordField();
		GridBagConstraints gbc_loginPasswordField = new GridBagConstraints();
		gbc_loginPasswordField.fill = GridBagConstraints.BOTH;
		gbc_loginPasswordField.insets = new Insets(0, 0, 5, 5);
		gbc_loginPasswordField.gridx = 1;
		gbc_loginPasswordField.gridy = 4;
		loginFrame.getContentPane().add(loginPasswordField, gbc_loginPasswordField);
		
		Component loginLeftStrut = Box.createHorizontalStrut(10);
		GridBagConstraints gbc_loginLeftStrut = new GridBagConstraints();
		gbc_loginLeftStrut.fill = GridBagConstraints.BOTH;
		gbc_loginLeftStrut.insets = new Insets(0, 0, 5, 5);
		gbc_loginLeftStrut.gridx = 0;
		gbc_loginLeftStrut.gridy = 5;
		loginFrame.getContentPane().add(loginLeftStrut, gbc_loginLeftStrut);
		
		Component loginRightStrut = Box.createHorizontalStrut(10);
		GridBagConstraints gbc_loginRightStrut = new GridBagConstraints();
		gbc_loginRightStrut.fill = GridBagConstraints.BOTH;
		gbc_loginRightStrut.insets = new Insets(0, 0, 5, 0);
		gbc_loginRightStrut.gridx = 2;
		gbc_loginRightStrut.gridy = 5;
		loginFrame.getContentPane().add(loginRightStrut, gbc_loginRightStrut);
		
		Component loginRigidArea = Box.createRigidArea(new Dimension(260, 10));
		GridBagConstraints gbc_loginRigidArea = new GridBagConstraints();
		gbc_loginRigidArea.insets = new Insets(0, 0, 5, 5);
		gbc_loginRigidArea.gridx = 1;
		gbc_loginRigidArea.gridy = 5;
		loginFrame.getContentPane().add(loginRigidArea, gbc_loginRigidArea);
		
		JPanel loginBottomPanel = new JPanel();
		GridBagConstraints gbc_loginBottomPanel = new GridBagConstraints();
		gbc_loginBottomPanel.insets = new Insets(0, 0, 5, 5);
		gbc_loginBottomPanel.fill = GridBagConstraints.BOTH;
		gbc_loginBottomPanel.gridx = 1;
		gbc_loginBottomPanel.gridy = 6;
		loginFrame.getContentPane().add(loginBottomPanel, gbc_loginBottomPanel);
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
		
		loginButton = new JButton("");
		GridBagConstraints gbc_loginButton = new GridBagConstraints();
		gbc_loginButton.anchor = GridBagConstraints.LINE_END;
		gbc_loginButton.gridx = 1;
		gbc_loginButton.gridy = 0;
		loginBottomPanel.add(loginButton, gbc_loginButton);
		loginButton.setAction(confimaLogin);
		
		Component loginDownStrut = Box.createVerticalStrut(10);
		GridBagConstraints gbc_loginDownStrut = new GridBagConstraints();
		gbc_loginDownStrut.fill = GridBagConstraints.BOTH;
		gbc_loginDownStrut.insets = new Insets(0, 0, 0, 5);
		gbc_loginDownStrut.gridx = 1;
		gbc_loginDownStrut.gridy = 7;
		loginFrame.getContentPane().add(loginDownStrut, gbc_loginDownStrut);
		loginFrame.setVisible(true);
		
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
				gdc.cadastra(id, new String(senha), Permissao.GERENTE);
				//Conta usuario = gdc.login(id, new String(senha));
				gdc.salvaContas();
				
				InterfaceGrafica.setTela(new JPanel()); //TODO troca de tela
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
		
		public void actionPerformed(ActionEvent e) {
			String id = loginUserField.getText();
			char[] senha = loginPasswordField.getPassword();
			Conta usuario = gdc.login(id, new String(senha));
			
			if(usuario == null) {
				loginErrorLabel.setText("Login inv\u00E1lido");
				loginErrorLabel.setVisible(true);
			}

			else InterfaceGrafica.setTela(new JPanel()); //TODO troca de tela
			
			for(int i=0; i<senha.length; i++)
				senha[i] = 0;
			loginPasswordField.setText("");
		}
	}
}
