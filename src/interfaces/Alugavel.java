package interfaces;

import tempo.Periodo;
/**
 * Interface que representa um alugável. Um alugável extende pagável e além disso possui métodos para
 * checar se o objeto já foi devolvido e o período que foi alugado.<p>
 * Interface desenvolvida para o projeto da disciplina de Laboratório de Programação II na UFCG 2014.2. 
 * 
 * 31 de dezembro de 2014.
 * 
 * @author Marianne Linhares Monteiro
 * @version 1.0
 */
public interface Alugavel extends Pagavel{

	/**
	 * @return periodo
	 * 		Período em que objeto foi alugado.
	 */
	public Periodo getPeriodo();
	
	/**
	 * altera o "estado" do objeto para devolvido.
	 */
	public void devolve();
	
	/**
	 * 
	 * @return
	 * 		Se o objeto já foi devolvido ou não.
	 */
	public boolean isDevolvido();
}
