package gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import tempo.Periodo;

/**
 * Um compontente Swing que e capaz de graficamente permitir a determinacao de um periodo.
 *
 * @author Victor Andrade de Almeida
 */
public class Calendario extends JComponent {

	private static final long serialVersionUID = 1L;

	private static final Color corIndisponivel = new Color(255, 120, 120);
	private static final Color corSelecinado = new Color(120, 255, 120);

	private Set<Periodo> periodos;

	private Color[][] cores;
	private Calendar[][] dias;
	private Calendar dataAtual;
	private Calendar dataInicio, dataFim;

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
		periodos = new TreeSet<Periodo>();
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
		if(dataInicio == null)
			return null;
		if(dataFim == null)
			return new Periodo(dataInicio, dataInicio);
		return new Periodo(dataInicio, dataFim);
	}

	/**
	 * Marca um periodo no calendario como indisponivel.
	 * <p>
	 * Periodos indisponiveis nao podem entrar em conflito uns com os outros e nao podem ser marcados pelo usuario.
	 *
	 * @param periodo  o periodo a ser adicionado, nao null
	 * @return true se o periodo foi adicionado com sucesso, false caso contrario
	 */
	public boolean adicionaPeriodoIndisponivel(Periodo periodo) {
		if(periodo == null)
			return false;

		boolean operacaoValida = periodos.add(periodo);

		if(operacaoValida) {
			dataInicio = null;
			dataFim = null;
			atualizaDias();
		}

		return operacaoValida;
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
		g.fillRect(0, 0, largura, top);

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

		// TODO paint text
		g.setColor(Color.BLACK);
		g.drawString("<   "+dataAtual.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH)+" / "+dataAtual.get(Calendar.YEAR)+"   >", left+50, top-5);

		// TODO paint numbers
		g.setColor(Color.BLACK);
		for(int y=0; y<6; y++) for(int x=0; x<7; x++) {
			if(dias[x][y].get(Calendar.MONTH) == dataAtual.get(Calendar.MONTH)) g.setFont(g.getFont().deriveFont(Font.PLAIN));
			else  g.setFont(g.getFont().deriveFont(Font.ITALIC));
			g.drawString(Integer.toString(dias[x][y].get(Calendar.DATE)), (int)(dateWidth*0.35)+left+x*dateWidth, (int)(dateHeight*0.7)+top+y*dateHeight);
		}
	}

	private void recalculaTamanho() {
		largura = (int) (tamanho/7) * 7;
		altura = (int) (tamanho/7) * 6;

		separator = (int) (altura * proporcao);
		altura += separator;

		largura += getInsets().left + getInsets().right;
		altura += getInsets().top + getInsets().bottom;
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

	private void atualizaDias() {
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
			for(Periodo p : periodos)
				if(p.contem(dias[x][y]))
					cores[x][y] = corIndisponivel;

			Periodo selecionado = getSelecao();
			if(selecionado != null && selecionado.contem(dias[x][y]))
				cores[x][y] = corSelecinado;
		}

		repaint();
	}

	private void alteraMes(int variacao) {
		dataAtual.add(Calendar.MONTH, variacao);
		atualizaDias();
	}

	private class ControleCalendario extends MouseAdapter {

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
					if(mx < largura/2) alteraMes(-1);
					else alteraMes(+1);
				}

				if(isEnabled() && my > top && my <= bottom) {
					Calendar backInicio = dataInicio;
					Calendar backFim = dataFim;

					int x = (mx-left) / dateWidth;
					int y = (my-top) / dateHeight;

					if(dataInicio == null)
						dataInicio = dias[x][y];

					else {
						if(dias[x][y].equals(dataInicio)) {
							dataInicio = dataFim;
							dataFim = null;
						}

						else if(dias[x][y].equals(dataFim))
							dataFim = null;

						else if(dias[x][y].before(dataInicio)) {
							if(dataFim == null)
								dataFim = dataInicio;
							dataInicio = dias[x][y];
						}

						else dataFim = dias[x][y];
					}

					for(Periodo p : periodos)
						if(p.entraEmConflito(getSelecao())) {
							dataInicio = backInicio;
							dataFim = backFim;
						}
				}
			}

			if(e.getButton() == MouseEvent.BUTTON3) {
				dataInicio = null;
				dataFim = null;
			}

			atualizaDias();
		}
	}
}
