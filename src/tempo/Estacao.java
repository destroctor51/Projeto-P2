package tempo;

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

public class Estacao {
		
	private Set<Periodo> periodos = new TreeSet<>();
	private double tarifa;
	
	/**
	 * Construtor da Estacao.
	 * @param tarifa
	 * 			Tarifa da estacao.
	 */
	public Estacao(double tarifa){
		this.tarifa = tarifa;
	}
	
	/**
	 * 
	 * @return
	 * 		Valor da tarifa.
	 */
	public double getTarifa() {
		return tarifa;
	}

	/**
	 * 
	 * @param tarifa
	 * 		A tarifa da estacao para a tarifa passada como parametro.
	 */
	public void setTarifa(double tarifa) {
		this.tarifa = tarifa;
	}

	/**
	 * 
	 * @return periodos
	 * 		  Periodos da estacao.
	 */
	public Set<Periodo> getPeriodos() {
		return periodos;
	}

	/**
	 * 
	 * @param novoPeriodo
	 * 		  Periodo a ser comparado.
	 * @return
	 * 		  Se entra em conflito ou nao.
	 */
	public boolean entraEmConflito(Periodo novoPeriodo) {
		return periodos.contains(novoPeriodo);
	}
	
	/**
	 * 
	 * @param novoPeriodo
	 * 			Periodo a ser adicionado.
	 * @return 
	 * 			Se acao foi realizada com sucesso ou nao.
	 * 
	 */
	public boolean addPeriodo(Periodo novoPeriodo) {
		return periodos.add(novoPeriodo);
	}

}
