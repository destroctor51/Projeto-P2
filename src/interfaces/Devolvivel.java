package interfaces;
/**
 * Interface que representa um devolvivel. Um devolvivel extende alugavel e alem disso possui metodos para devolver o objeto e
 * para checar se o objeto ja foi devolvido .<p>
 * Interface desenvolvida para o projeto da disciplina de Laboratorio de Programacao II na UFCG 2014.2. 
 * 
 * 12 de janeiro de 2015.
 * 
 * @author Marianne Linhares Monteiro
 * @version 1.0
 */
public interface Devolvivel extends Alugavel{
	/**
	 * Altera o "estado" do objeto para devolvido.
	 */
	public void devolve();
	
	/**
	 * 
	 * @return
	 * 		Se o objeto ja foi devolvido ou nao.
	 */
	public boolean isDevolvido();
}

