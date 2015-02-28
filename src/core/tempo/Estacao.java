package core.tempo;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

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

	/**
	 * 
	 */
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
	public boolean entraEmConflito(Periodo novoPeriodo) {
		return periodos.contains(novoPeriodo);
	}

	/**
	 *
	 * @param novoPeriodo  periodo a ser adicionado.
	 * @return se acao foi realizada com sucesso ou nao.
	 *
	 */
	public boolean addPeriodo(Periodo novoPeriodo) {
		return periodos.add(novoPeriodo);
	}
	
	/**
	 * 
	 * @param periodo  periodo a ser removido.
	 * @return se a acao foi realizada com sucesso ou nao.
	 * 
	 */
	public boolean removePeriodo(Periodo periodo) {
		return periodos.remove(periodo);
	}

	@Override
	public String toString() {
		return id;
	}
}
