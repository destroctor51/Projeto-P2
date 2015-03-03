package gui.components;

import java.util.Calendar;
import java.util.Set;
import java.util.TreeSet;

import utils.Tempo;
import core.tempo.Periodo;

/**
 * Um compontente Swing feito para permitir a selecao de periodos, filtrando os indisponiveis.
 *
 * @author Victor Andrade de Almeida
 */
public class CalendarioFiltrado extends Calendario {

	private static final long serialVersionUID = 1L;

	private Set<Periodo> periodos;

	/**
	 * Cria um novo CalendarioFiltrado default.
	 */
	public CalendarioFiltrado() {
		periodos = new TreeSet<>();
	}

	/**
	 * Marca um periodo no calendario como indisponivel.
	 * <p>
	 * Periodos indisponiveis nao podem entrar em conflito uns com os outros e nao podem ser marcados pelo usuario.
	 *
	 * @param periodo  o periodo a ser marcado
	 */
	public void marcaIndisponivel(Periodo periodo) {
		Tempo.merge(periodo, periodos);
		atualizaDias();
	}

	/**
	 * Garante que um periodo estara disponivel para selecao no calendario
	 *
	 * @param periodo  o periodo a ser marcado
	 */
	public void marcaDisponivel(Periodo periodo) {
		Tempo.cut(periodo, periodos);
		atualizaDias();
	}

	@Override
	protected boolean isDiaIndisponivel(Calendar dia) {
		if(periodos == null)
			return false;
		for(Periodo p : periodos)
			if(p.contem(dia))
				return true;
		return false;
	}

	@Override
	protected boolean isSelecaoInvalida() {
		return periodos.contains(getSelecao());
	}
}
