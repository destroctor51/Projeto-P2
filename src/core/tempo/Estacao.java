package core.tempo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;
import java.util.TreeSet;

import utils.Tempo;

/**
 * Classe que representa uma Estacao. Uma estacao nada mais e do que um conjunto de datas
 * ou epocas do ano durante as quais os custos ligados a estadia no hotel sao diferenciados.<p>
 * Classe desenvolvida para o projeto da disciplina de Laboratorio de Programacao II na UFCG 2014.2.
 *
 * 31 de dezembro de 2014.
 *
 * @author Marianne Linhares Monteiro
 * @version 1.0
 */

public class Estacao implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;

	private Set<Periodo> periodos = new TreeSet<>();
	private double tarifa;
	private String id;

	/**
	 * Construtor da Estacao.
	 * @param tarifa
	 * 			Tarifa da estacao.
	 */
	public Estacao(String id, double tarifa){
		if(id == null)
			throw new IllegalArgumentException();

		this.tarifa = tarifa;
		this.id = id;
	}

	/**
	 * @return o valor da tarifa.
	 */
	public double getTarifa() {
		return tarifa;
	}

	/**
	 * @return o id da estacao
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param tarifa  a nova tarifa a ser atribuida
	 */
	public void setTarifa(double tarifa) {
		this.tarifa = tarifa;
	}

	/**
	 * @return periodos  os periodos da estacao
	 */
	public Set<Periodo> getPeriodos() {
		return periodos;
	}

	/**
	 *
	 * @param novoPeriodo  periodo a ser comparado
	 * @return se entra em conflito ou nao
	 */
	public boolean entraEmConflito(Periodo periodo) {
		for(Periodo p : Tempo.freeze(periodo))
			if(periodos.contains(p)) return true;
		return false;
	}

	/**
	 * Confere se uma data e englobada pela estacao
	 *
	 * @param data  a data a ser conferida
	 * @return true se for englobada, false caso contrario
	 */
	public boolean contem(Calendar data) {
		Calendar clone = Tempo.freeze(data);
		for(Periodo p : periodos)
			if(p.contem(clone)) return true;
		return false;
	}

	/**
	 *
	 * @param novoPeriodo  periodo a ser adicionado.
	 * @return se acao foi realizada com sucesso ou nao.
	 *
	 */
	public void addPeriodo(Periodo novoPeriodo) {
		for(Periodo p : Tempo.freeze(novoPeriodo))
			Tempo.merge(p, periodos);
	}

	/**
	 *
	 * @param periodo  periodo a ser removido.
	 * @return se a acao foi realizada com sucesso ou nao.
	 *
	 */
	public void removePeriodo(Periodo periodo) {
		for(Periodo p : Tempo.freeze(periodo))
			Tempo.cut(p, periodos);
	}

	/**
	 * Remove todos os periodos da estacao.
	 */
	public void clear() {
		periodos.clear();
	}

	@Override
	public String toString() {
		return id;
	}
	
	@Override
	public Object clone() {
		try {
			Estacao clone = (Estacao) super.clone();
			clone.periodos = new TreeSet<>();
			for(Periodo p : periodos)
				clone.periodos.add((Periodo) p.clone());
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException();
		}
	}
}
