package gui.components;

import java.util.Calendar;
import java.util.Iterator;
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
		if(periodo == null)
			return;
		
		Set<Periodo> update = new TreeSet<>(periodos);
		
		for(Periodo p : periodos)
			if(!periodo.entraEmConflito(p)) {
				if(periodo.getInicio().equals(p.getFim()))
					periodo.setInicio(p.getInicio());
				if(periodo.getFim().equals(p.getInicio()))
					periodo.setFim(p.getFim());
			}
			else if(periodo.contem(p))
				continue;
			else if(p.contem(periodo))
				return;
			else if(periodo.getInicio().before(p.getInicio()))
				periodo.setFim(p.getFim());
			else
				periodo.setInicio(p.getInicio());

		while(update.remove(periodo));
		update.add(periodo);
		periodos = update;
		atualizaDias();
	}

	/**
	 * Garante que um periodo estara disponivel para selecao no calendario
	 *
	 * @param periodo  o periodo a ser marcado
	 */
	public void marcaDisponivel(Periodo periodo) {
		if(periodo == null)
			return;
		
		Set<Periodo> update = new TreeSet<>();
		
		for(Periodo p : periodos) {
			if(periodo.entraEmConflito(p))
				if(periodo.contem(p))
					continue;
				else if(p.contem(periodo)) {
					if(!p.getInicio().equals(periodo.getInicio()))
						update.add(new Periodo(p.getInicio(), periodo.getInicio()));
					if(!p.getFim().equals(periodo.getFim()))
						update.add(new Periodo(periodo.getFim(), p.getFim()));
				}
				else if(periodo.getInicio().before(p.getInicio()))
					update.add(new Periodo(periodo.getFim(), p.getFim()));
				else
					update.add(new Periodo(p.getInicio(), periodo.getInicio()));
			else update.add(p);
		}

		periodos = update;
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
		if(periodos == null)
			return false;
		for(Periodo p : periodos)
			if(p.entraEmConflito(getSelecao()))
				return true;
		return false;
	}
}
