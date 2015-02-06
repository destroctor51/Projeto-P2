package gui.components;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

/**
 * Um TextField que permite a selecao de um texto Default caso este nao seja preenchido.
 *
 * @author Marianne Linhares Monteiro
 */
public class TextFieldSelecaoDefault extends JTextField implements FocusListener,
		KeyListener, MouseListener {

	private static final long serialVersionUID = 1L;

	private final String hint;
	private boolean showingHint;
	/**
	 * Constroi um TextFieldSelecaoDefault
	 * @param hint texto Default
	 */
	public TextFieldSelecaoDefault(final String hint) {
		super(hint);
		super.setForeground(Color.GRAY);
		this.hint = hint;
		this.showingHint = true;
		super.addFocusListener(this);
		super.addKeyListener(this);
		super.addMouseListener(this);
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (this.getText().isEmpty()) {
			super.setText(hint);
			super.setForeground(Color.GRAY);
			showingHint = true;
			super.setCaretPosition(0);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (this.getText().isEmpty()) {
			super.setText(hint);
			super.setForeground(Color.GRAY);
			showingHint = true;
		}
	}

	@Override
	public String getText() {
		return showingHint ? "" : super.getText();
	}

	@Override
	public void keyPressed(KeyEvent arg) {

	}

	@Override
	public void keyReleased(KeyEvent arg) {
	}

	@Override
	public void keyTyped(KeyEvent e) {

		if (super.getText().equals("Anonimo")) {
			
			if (e.getKeyChar() != '\b' && e.getKeyChar() != '\u007F' || super.getCaretPosition() > 0) {
				super.setForeground(Color.BLACK);
				super.setText(new String(""));
				showingHint = false;
			}
			
			if (e.getKeyChar() == '\b' || e.getKeyChar() == '\u007F')
			{
				super.setText(hint);
				super.setForeground(Color.GRAY);
				showingHint = true;
				super.setCaretPosition(0);
			}
		}
		
		if (e.getKeyChar() == '\b' && super.getText().length() == 0)
		{
			super.setText(hint);
			super.setCaretPosition(0);
			super.setForeground(Color.GRAY);
			showingHint = true;
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (showingHint){
			super.setCaretPosition(0);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (showingHint){
			super.setCaretPosition(0);
		}
	}
}
