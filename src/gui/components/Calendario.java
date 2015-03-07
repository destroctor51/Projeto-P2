package gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import core.tempo.Periodo;

/**
 * Um compontente Swing que e capaz de graficamente permitir a determinacao de um periodo.
 *
 * @author Victor Andrade de Almeida
 */
public class Calendario extends JComponent {

	private static final long serialVersionUID = 1L;

	private static final Color corIndisponivel = new Color(255, 120, 120);
	private static final Color corSelecinado = new Color(120, 255, 120);

	private Color[][] cores;
	private Calendar[][] dias;
	private Calendar dataAtual;
	private Periodo selecao;

	private boolean multiplos = true;

	private int largura;
	private int altura;
	private int separator;
	private double tamanho = 200;
	private double proporcao = 0.12;

	private MouseListener controle = new ControleCalendario();

	/**
	 * Cria um novo Calendario default.
	 */
	public Calendario() {
		addMouseListener(controle);
		setBorder(new LineBorder(Color.BLACK, 1));
		recalculaTamanho();

		dataAtual = GregorianCalendar.getInstance();
		dias = new Calendar[7][6];
		cores = new Color[7][6];

		for(int y=0; y<6; y++) for(int x=0; x<7; x++)
			dias[x][y] = new GregorianCalendar();

		atualizaDias();
	}

	/**
	 * Recupera o periodo determinado pelo usuario.
	 *
	 * @return o periodo escolhido ou null caso nenhum periodo tenha sido marcado
	 */
	public Periodo getSelecao() {
		if(selecao == null) return null;
		return (Periodo) selecao.clone();
	}

	/**
	 * Define se o calendario pode selecionar um periodo com varios dias.
	 *
	 * @param modo  true se ele puder selecionar varios dias, false caso contrario
	 */
	public void setMultiplos(boolean modo) {
		multiplos = modo;
	}

	@Override
	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	@Override
	public Dimension getMaximumSize() {
		return getPreferredSize();
	}

	@Override
	public Dimension getPreferredSize() {
		Dimension size = new Dimension();
		size.setSize(largura-1, altura-1);
		return size;  // TODO make resize better
	}

	/**
	 * Altera o tamanho do componente, mantendo suas propocoes.
	 * <p>
	 * O valor dado nao correspondera ao tamanho real do calendario.
	 *
	 * @param tamanho  o novo parametro usado para calcular o tamanho do componente
	 */
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
		recalculaTamanho();
	}

	@Override
	public void setBorder(Border border) {
		super.setBorder(border);
		recalculaTamanho();
	}

	private void recalculaTamanho() {
		largura = (int) (tamanho/7) * 7;
		altura = (int) (tamanho/7) * 6;

		separator = (int) (altura * proporcao);
		altura += separator;

		largura += getInsets().left + getInsets().right;
		altura += getInsets().top + getInsets().bottom;
	}

	private void alteraMes(int variacao) {
		dataAtual.add(Calendar.MONTH, variacao);
		atualizaDias();
	}

	protected boolean isDiaIndisponivel(Calendar dia) {
		return false;
	}

	protected boolean isSelecaoInvalida() {
		return false;
	}

	protected String getHeader(Calendar dataAtual) {
		return dataAtual.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) + " / "+dataAtual.get(Calendar.YEAR);
	}

	@Override
	protected void paintComponent(Graphics g) {
		int left = getInsets().left-1;
		int right = largura - getInsets().right;
		int top = separator + getInsets().top-1;
		int bottom = altura - getInsets().bottom;

		int dateWidth = (right-left) / 7;
		int dateHeight = (bottom-top) / 6;

		// paint colors
		g.setColor(getBackground());
		g.fillRect(0, 0, right, top);

		for(int y=0; y<6; y++) for(int x=0; x<7; x++) {
			g.setColor(cores[x][y]);
			g.fillRect(left+x*dateWidth, top+y*dateHeight, dateWidth, dateHeight);
		}

		// paint grid
		g.setColor(getForeground());

		for(int x=1; x<7; x++)
			g.drawLine(left+x*dateWidth, top, left+x*dateWidth, bottom);
		for(int y=0; y<6; y++)
			g.drawLine(left, top+y*dateHeight, right, top+y*dateHeight);


		{ // paint header
			String header = getHeader(dataAtual);
			int drawingHeight = top/2 + g.getFont().getSize()/2;

			g.setColor(Color.BLACK);
			g.drawString(header, (right-left)/2 - g.getFontMetrics().stringWidth(header)/2, drawingHeight);
			g.drawString("<", (right-left)*1/6 - g.getFontMetrics().stringWidth("<")/2, drawingHeight);
			g.drawString(">", (right-left)*5/6 - g.getFontMetrics().stringWidth(">")/2, drawingHeight);
		}

		// paint numbers
		for(int y=0; y<6; y++) for(int x=0; x<7; x++) {
			if(dias[x][y].get(Calendar.MONTH) == dataAtual.get(Calendar.MONTH)) g.setColor(Color.BLACK);
			else g.setColor(Color.GRAY);

			String text = Integer.toString(dias[x][y].get(Calendar.DATE));
			int posx = left+x*dateWidth+dateWidth/2 - g.getFontMetrics().stringWidth(text)/2;
			int posy = top+y*dateHeight+dateHeight/2 + g.getFont().getSize()/2;

			g.drawString(text, posx, posy);
		}
	}

	/**
	 * Recarrega as informacoes usadas para selecionar e visualizar o calendario
	 */
	public final void atualizaDias() {
		if(isSelecaoInvalida())
			selecao = null;

		int year = dataAtual.get(Calendar.YEAR);
		int month = dataAtual.get(Calendar.MONTH);
		Calendar base = new GregorianCalendar(year, month, 1);

		do base.add(Calendar.DATE, -1);
		while(base.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY);

		for(int y=0; y<6; y++) for(int x=0; x<7; x++) {
			dias[x][y] = (Calendar) base.clone();
			base.add(Calendar.DATE, +1);
		}

		for(int y=0; y<6; y++) for(int x=0; x<7; x++) {
			cores[x][y] = getBackground();
			if(isDiaIndisponivel(dias[x][y]))
				cores[x][y] = corIndisponivel;

			Periodo selecionado = getSelecao();
			if(selecionado != null && selecionado.contem(dias[x][y]))
				cores[x][y] = corSelecinado;
		}

		repaint();
	}

	private final class ControleCalendario extends MouseAdapter {

		private Calendar offset(Calendar data, int offset) {
			Calendar copia = (Calendar) data.clone();
			copia.add(Calendar.DATE, offset);
			return copia;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			int left = getInsets().left-1;
			int right = largura - getInsets().right;
			int top = separator + getInsets().top-1;
			int bottom = altura - getInsets().bottom;

			int dateWidth = (right-left) / 7;
			int dateHeight = (bottom-top) / 6;

			if(e.getButton() == MouseEvent.BUTTON1) {
				int mx = e.getX(), my = e.getY();

				if(mx < left && mx >= right)
					return;

				if(my <= top) {
					if(mx < largura*1/4) alteraMes(-1);
					if(mx > largura*3/4) alteraMes(+1);
				}

				if(isEnabled() && my > top && my <= bottom) {
					Periodo backup = getSelecao();

					int x = (mx-left) / dateWidth;
					int y = (my-top) / dateHeight;

					if(selecao == null)
						selecao = new Periodo(dias[x][y], offset(dias[x][y], +1));

					else if(!multiplos)
						if(selecao.getInicio().equals(dias[x][y]))
							selecao = null;
						else selecao = new Periodo(dias[x][y], offset(dias[x][y], +1));

					else {
						if(selecao.getInicio().equals(dias[x][y]))
							if(selecao.getNumeroDias() > 1)
								selecao.setInicio(offset(selecao.getFim(), -1));
							else selecao = null;

						else if(offset(selecao.getFim(), -1).equals(dias[x][y]))
							selecao.setFim(offset(selecao.getInicio(), +1));

						else if(selecao.getInicio().after(dias[x][y]))
							selecao.setInicio(dias[x][y]);

						else selecao.setFim(offset(dias[x][y], +1));
					}

					if(isSelecaoInvalida())
						selecao = backup;
				}
			}

			if(e.getButton() == MouseEvent.BUTTON3)
				selecao = null;

			atualizaDias();
		}
	}
}
