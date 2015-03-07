package utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import core.tempo.Periodo;

/**
 * Metodos estaticos com foco em facilitar a interacao com conjuntos de periodos.
 *
 * @author Victor Andrade de Almeida
 */
public final class Tempo {

	private Tempo() {}

	/**
	 * Cria um clone da data sem o ano.
	 *
	 * @param data  a data a ser congelada no tempo
	 * @return o clone resultante
	 */
	public static Calendar freeze(Calendar data) {
		Calendar clone = (Calendar) data.clone();
		clone.set(Calendar.YEAR, 1);
		return clone;
	}

	/**
	 * Congela um periodo no tempo, removendo suas informacoes sobre o ano.
	 *
	 * @param periodo  o periodo alvo
	 * @return uma lista contendo os periodos resultantes
	 */
	public static List<Periodo> freeze(Periodo periodo) {
		List<Periodo> resultado = new ArrayList<>();
		if(periodo == null) return resultado;

		Calendar inicio = (Calendar) periodo.getInicio().clone();
		Calendar fim = (Calendar) periodo.getFim().clone();
		Calendar marcoInicial = new GregorianCalendar(1, 0, 1);
		Calendar marcoFinal = new GregorianCalendar(2, 0, 1);

		fim.add(Calendar.YEAR, 1-inicio.get(Calendar.YEAR));
		inicio.set(Calendar.YEAR, 1);
		int diff = fim.get(Calendar.YEAR);

		if(marcoFinal.before(fim)) {
			fim.set(Calendar.YEAR, 1);
			if(fim.before(inicio) && diff <= 2) {
				resultado.add(new Periodo(inicio, marcoFinal));
				resultado.add(new Periodo(marcoInicial, fim));
			} else resultado.add(new Periodo(marcoInicial, marcoFinal));
		} else resultado.add(new Periodo(inicio, fim));

		return resultado;
	}

	/**
	 * Adiciona um periodo a um conjunto de modo a manter o minimo de periodos dentro dele.
	 *
	 * @param periodo  o periodo a ser adicionado
	 * @param set  o conjunto alvo
	 */
	public static void merge(Periodo periodo, Set<Periodo> set) {
		if(periodo == null) return;
		Set<Periodo> cache = new TreeSet<>(set);

		for(Periodo p : cache)
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

		while(set.remove(periodo));
		set.add(periodo);
	}

	/**
	 * Remove um periodo qualquer de um conjunto, mesmo que ele nao tenha sido colocado explicitamente.
	 *
	 * @param periodo  o periodo a ser removido
	 * @param set  o conjunto alvo
	 */
	public static void cut(Periodo periodo, Set<Periodo> set) {
		if(periodo == null) return;
		Set<Periodo> cache = new TreeSet<>();

		for(Periodo p : set) {
			if(periodo.entraEmConflito(p))
				if(periodo.contem(p))
					continue;
				else if(p.contem(periodo)) {
					if(!p.getInicio().equals(periodo.getInicio()))
						cache.add(new Periodo(p.getInicio(), periodo.getInicio()));
					if(!p.getFim().equals(periodo.getFim()))
						cache.add(new Periodo(periodo.getFim(), p.getFim()));
				}
				else if(periodo.getInicio().before(p.getInicio()))
					cache.add(new Periodo(periodo.getFim(), p.getFim()));
				else
					cache.add(new Periodo(p.getInicio(), periodo.getInicio()));
			else cache.add(p);
		}

		set.clear();
		set.addAll(cache);
	}
}
