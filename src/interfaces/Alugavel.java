package interfaces;

import tempo.Periodo;
/**
 * Interface que representa um alugavel. Um alugavel extende pagavel e alem disso possui metodos para
 * checar se o objeto ja foi devolvido e o periodo que foi alugado.<p>
 * Interface desenvolvida para o projeto da disciplina de Laboratorio de Programacao II na UFCG 2014.2. 
 * 
 * 31 de dezembro de 2014.
 * 
 * @author Marianne Linhares Monteiro
 * @version 1.0
 */
public interface Alugavel extends Pagavel{

	/**
	 * @return
	 * 		periodo em que objeto foi alugado.
	 */
	public Periodo getPeriodo();
	
	/**
	 * Altera o "estado" do objeto para devolvido.
	 */
	public void devolve();
	
	/**
	 * 
	 * @return
	 * 		se o objeto ja foi devolvido ou nao.
	 */
	public boolean isDevolvido();
}
