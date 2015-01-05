package tempo;

import java.util.Set;
import java.util.TreeSet;

/**
 * Classe que representa uma Estação. Uma estação nada mais é do que um conjunto de datas
 * ou épocas do ano durante as quais os custos ligados à estadia no hotel são diferenciados.<p>
 * Classe desenvolvida para o projeto da disciplina de Laboratório de Programação II na UFCG 2014.2.
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
	 * Construtor da Estação.
	 * @param tarifa
	 * 			Tarifa da estação.
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
	 * 		A tarifa da estação para a tarifa passada como parâmetro.
	 */
	public void setTarifa(double tarifa) {
		this.tarifa = tarifa;
	}

	/**
	 * 
	 * @return periodos
	 * 		  Periodos da estação.
	 */
	public Set<Periodo> getPeriodos() {
		return periodos;
	}

	/**
	 * 
	 * @param novoPeriodo
	 * 		  Periodo a ser comparado.
	 * @return
	 * 		  Se entra em conflito ou não.
	 */
	public boolean entraEmConflito(Periodo novoPeriodo) {
		return periodos.contains(novoPeriodo);
	}
	
	/**
	 * 
	 * @param novoPeriodo
	 * 			Periodo a ser adicionado.
	 * @return 
	 * 			Se ação foi realizada com sucesso ou não.
	 * 
	 */
	public boolean addPeriodo(Periodo novoPeriodo) {
		return periodos.add(novoPeriodo);
	}

}
