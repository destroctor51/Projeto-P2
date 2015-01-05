package tempo;

import java.util.Set;
import java.util.TreeSet;

/**
 * Classe que representa uma Esta��o. Uma esta��o nada mais � do que um conjunto de datas
 * ou �pocas do ano durante as quais os custos ligados � estadia no hotel s�o diferenciados.<p>
 * Classe desenvolvida para o projeto da disciplina de Laborat�rio de Programa��o II na UFCG 2014.2.
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
	 * Construtor da Esta��o.
	 * @param tarifa
	 * 			Tarifa da esta��o.
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
	 * 		A tarifa da esta��o para a tarifa passada como par�metro.
	 */
	public void setTarifa(double tarifa) {
		this.tarifa = tarifa;
	}

	/**
	 * 
	 * @return periodos
	 * 		  Periodos da esta��o.
	 */
	public Set<Periodo> getPeriodos() {
		return periodos;
	}

	/**
	 * 
	 * @param novoPeriodo
	 * 		  Periodo a ser comparado.
	 * @return
	 * 		  Se entra em conflito ou n�o.
	 */
	public boolean entraEmConflito(Periodo novoPeriodo) {
		return periodos.contains(novoPeriodo);
	}
	
	/**
	 * 
	 * @param novoPeriodo
	 * 			Periodo a ser adicionado.
	 * @return 
	 * 			Se a��o foi realizada com sucesso ou n�o.
	 * 
	 */
	public boolean addPeriodo(Periodo novoPeriodo) {
		return periodos.add(novoPeriodo);
	}

}
