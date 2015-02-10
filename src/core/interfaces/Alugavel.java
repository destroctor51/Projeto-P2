package core.interfaces;

import java.util.Set;

import core.tempo.Periodo;

/**
 * Interface que representa um alugavel. Um alugavel extende pagavel e alem disso possui um metodo que retorna o Periodo em que foi alugado.<p>
 * Interface desenvolvida para o projeto da disciplina de Laboratorio de Programacao II na UFCG 2014.2.
 *
 * 12 de janeiro de 2015.
 *
 * @author Marianne Linhares Monteiro
 * @version 2.0
 */
public interface Alugavel extends Pagavel {

	/**
	 * Marca o objeto como alugado durante o periodo definido.
	 * <p>
	 * Ele so sera alugado se o periodo nao entrar em conflito com outro aluguel.
	 *
	 * @param periodo  o periodo em que se quer alugar o objeto
	 * @return true se for alugado com sucesso, false caso contrario
	 */
	public boolean aluga(Periodo periodo);

	/**
	 * Retorna todos os periodos em que o objeto ja foi ou esta sendo alugado.
	 *
	 * @return um conjunto com os periodos presentes no historico de aluguel do objeto
	 */
	public Set<Periodo> getHistorico();
}