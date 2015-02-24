package core.interfaces;

import java.util.Calendar;

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
	 *
	 * @param data  a data da devolucao
	 * @return false se ja havia sido devolvido, true caso contrario
	 */
	public boolean devolve(Calendar data);

	/**
	 * Cancela o alugel do objeto, resetando seu estado.
	 * @return false se ja havia sido devolvido, true caso contrario
	 */
	public boolean cancela();

	/**
	 *
	 * @return
	 * 		Se o objeto ja foi devolvido ou nao.
	 */
	public boolean isDevolvido();
}
