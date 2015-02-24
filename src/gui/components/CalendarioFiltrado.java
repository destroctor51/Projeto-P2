package gui.components;

import java.util.Calendar;
import java.util.Set;
import java.util.TreeSet;

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
		periodos = new TreeSet<Periodo>();
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
			clearSelecao();
			atualizaDias();
		}

		return operacaoValida;
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
		for(Periodo p : periodos)
			if(p.entraEmConflito(getSelecao()))
				return true;
		return false;
	}
}
