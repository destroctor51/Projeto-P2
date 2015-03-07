package gui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

/**
 * TextField capaz de mostrar um texto default e saber quando seu conteudo e modificado.
 *
 * @author Victor Andrade de Almeida
 */
public class SuperTextField extends JTextField {

	private static final long serialVersionUID = 1L;

	private String hint;

	/**
	 * Cria um SuperTextField
	 */
	public SuperTextField() {
		this.hint = new String();

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String text = getText();
				if(!Character.isISOControl(e.getKeyChar()))
					text += e.getKeyChar();
				textChanged(text);
			}
		});
	}

	/**
	 * Metodo chamado sempre que o campo de texto e alterado.
	 * <p>
	 * Sobrescreva esse metodo para dar um comportamento customizado ao SuperTextField.
	 *
	 * @param text  o novo conteudo de texto
	 */
	protected void textChanged(String text) {}

	/**
	 * Modifica o texto default.
	 *
	 * @param hint  o novo texto default
	 */
	public void setHint(String hint) {
		if(hint == null)
			throw new IllegalArgumentException();
		this.hint = hint;
	}

	@Override
	public void setText(String text) {
		super.setText(text);
		textChanged(text);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if(getText().isEmpty()) {
			Graphics2D g2d = (Graphics2D) g.create();

			int x = getInsets().left;
			int y = getInsets().top/2 + getHeight()/2 - getInsets().bottom/2 + g.getFont().getSize()/2 - 1;
			Color hintColor = getDisabledTextColor();

			g2d.setRenderingHint(
					RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2d.setColor(hintColor);
			g2d.drawString(hint, x, y);

			g2d.dispose();
		}
	}
}
