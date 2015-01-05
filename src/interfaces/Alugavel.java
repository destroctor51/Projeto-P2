package interfaces;

import tempo.Periodo;
/**
 * Interface que representa um alug�vel. Um alug�vel extende pag�vel e al�m disso possui m�todos para
 * checar se o objeto j� foi devolvido e o per�odo que foi alugado.<p>
 * Interface desenvolvida para o projeto da disciplina de Laborat�rio de Programa��o II na UFCG 2014.2. 
 * 
 * 31 de dezembro de 2014.
 * 
 * @author Marianne Linhares Monteiro
 * @version 1.0
 */
public interface Alugavel extends Pagavel{

	/**
	 * @return periodo
	 * 		Per�odo em que objeto foi alugado.
	 */
	public Periodo getPeriodo();
	
	/**
	 * altera o "estado" do objeto para devolvido.
	 */
	public void devolve();
	
	/**
	 * 
	 * @return
	 * 		Se o objeto j� foi devolvido ou n�o.
	 */
	public boolean isDevolvido();
}
