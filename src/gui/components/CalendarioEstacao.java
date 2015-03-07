package gui.components;

import java.util.Calendar;
import java.util.Locale;

import core.tempo.Estacao;

/**
 * Um calendario feito para visualizar e interagir com estacoes
 *
 * @author Victor Andrade de Almeida
 */
public class CalendarioEstacao extends Calendario {

	private static final long serialVersionUID = 1L;

	private Estacao estacao;

	/**
	 * Cria um calendario e mostra nele a estacao dada.
	 *
	 * @param estacao  a estacao a ser visualizada
	 */
	public CalendarioEstacao(Estacao estacao) {
		if(estacao == null)
			throw new IllegalArgumentException();
		this.estacao = estacao;
	}

	/**
	 * Troca a estacao usada no calendario.
	 *
	 * @param estacao  a nova estacao a ser visualizada
	 */
	public void setEstacao(Estacao estacao) {
		if(estacao == null)
			throw new IllegalArgumentException();
		this.estacao = estacao;
		atualizaDias();
	}

	@Override
	protected boolean isDiaIndisponivel(Calendar dia) {
		if(estacao == null) return false;
		return estacao.contem(dia);
	}

	@Override
	protected boolean isSelecaoInvalida() {
		if(estacao == null) return false;
		return estacao.entraEmConflito(getSelecao());
	}

	@Override
	protected String getHeader(Calendar dataAtual) {
		return dataAtual.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
	}
}
