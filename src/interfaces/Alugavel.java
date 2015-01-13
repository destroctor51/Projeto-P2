package interfaces;

import tempo.Periodo;
/**
 * Interface que representa um alugavel. Um alugavel extende pagavel e alem disso possui um metodo que retorna o Periodo em que foi alugado.<p>
 * Interface desenvolvida para o projeto da disciplina de Laboratorio de Programacao II na UFCG 2014.2. 
 * 
 * 12 de janeiro de 2015.
 * 
 * @author Marianne Linhares Monteiro
 * @version 2.0
 */
public interface Alugavel extends Pagavel{

	/**
	 * @return
	 * 		Periodo em que objeto foi alugado.
	 */
	public Periodo getPeriodo();
	
}