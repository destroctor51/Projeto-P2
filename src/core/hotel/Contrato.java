package core.hotel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import core.interfaces.Devolvivel;
import core.interfaces.Pagavel;
import core.tempo.Estacao;

/**
 * Uma classe que cria contratos.
 *
 * @author Arthur Vinicius Tome Rodrigues
 */
public class Contrato implements Serializable {

	private static final long serialVersionUID = 1L;

	private EstadoDeContrato estado;
	private String cartao;
	private Estacao estacao;
	private List<Pagavel> servicos;
	private Calendar dataCheckOut;
	private int protocolo;

	// construtor

	/**
	 * Cria um contrato a partir do numero de cartao do hospede e da estacao atual.
	 *
	 * @param cartao
	 * 			O numero do cartao do hospede.
	 * @param estacao
	 * 			A estacao atual do hotel.
	 */
	public Contrato(String cartao, Estacao estacao) {
		estado = EstadoDeContrato.PENDENTE;
		if (cartao == null || estacao == null)
			throw new IllegalArgumentException();

		this.cartao = cartao;
		this.estacao = estacao;
		protocolo = new String(new Random().nextLong()+":"+GregorianCalendar.getInstance().hashCode()).hashCode();
		servicos = new ArrayList<>();
	}

	/**
	 * Recupera a data do check out.
	 *
	 * @return data do check out, null se contrato nao estiver fechado
	 */
	public Calendar getDataCheckOut() {
		return dataCheckOut;
	}

	// metodos

	/**
	 * Recupera o estado do contrato.
	 *
	 * @return
	 * 			O estado atual do contrato (Pendente, aberto ou fechado).
	 */
	public EstadoDeContrato getEstado() {
		return estado;
	}

	/**
	 * Recupera o cartao do hospede que fez o contrato.
	 *
	 * @return
	 * 			O numero do cartao do hospede.
	 */
	public String getCartao() {
		return cartao;
	}

	/**
	 * Recupera a estacao do contrato.
	 *
	 * @return
	 * 			A estacao do contrato.
	 */
	public Estacao getEstacao() {
		return estacao;
	}

	/**
	 * Recupera protocolo do contrato.
	 *
	 * @return
	 * 		   O protocolo do contrato.
	 */
	public int getProtocolo() {
		return protocolo;
	}

	/**
	 * Recupera uma lista com os servicos do contrato.
	 *
	 * @return
	 * 			Lista com todos os servicos registrados no contrato.
	 */
	public List<Pagavel> getServicos() {
		return servicos;
	}

	/**
	 * Adiciona um servico ao contrato.
	 * <p>
	 * Antes de ser adicionado, o servico e clonado, para evitar alteracoes indesejadas.
	 *
	 * @param servico  o servico a ser adicionado ao contrato
	 * @return true se o servico foi adicionado com sucesso, false caso contrario
	 */
	public boolean adicionaServico(Pagavel servico) {
		if(estado == EstadoDeContrato.FECHADO || servico == null)
			return false;
		Pagavel clone = (Pagavel) servico.clone();
		return servicos.add(clone);
	}

	/**
	 * Adiciona uma colecao de servicos ao contraro.
	 * <p>
	 * Antes de serem adicionados, cada servico e clonado, para evitar alteracoes indesejadas.
	 *
	 * @param servicos  colecao de servicos a ser adicionada
	 * @return true se algum servico foi adicionado, false caso contrario
	 */
	public boolean adicionaServicos(Collection<Pagavel> servicos) {
		if (servicos == null) return false;

		boolean resultado = false;
		Iterator<Pagavel> it = servicos.iterator();

		while(it.hasNext())
			resultado |= adicionaServico(it.next());

		return resultado;
	}

	/**
	 * Remove um servico do contrato.
	 *
	 * @param servico  o servico a ser removido.
	 * @return true se o servico pode ser removido, false caso contrario
	 */
	public boolean removeServico(Pagavel servico) {
		if(estado == EstadoDeContrato.FECHADO)
			return false;
		return servicos.remove(servico);
	}

	/**
	 * Remove varios servicos do contrato a partir de uma colecao com os servicos a serem removidos.
	 *
	 * @param servicos colecao de servicos a serem removidos
	 * @return true caso algum servico foi removido, false caso contrario
	 */
	public boolean removeServicos(Collection<Pagavel> servicos) {
		if (servicos == null) return false;
		return this.servicos.removeAll(servicos);
	}

	/**
	 * Realiza check in do contrato a partir do cartao do contrato (utilizado apenas por seguranca).
	 *
	 * @param cartao  o numero do cartao do contrato.
	 * @return boolean representando se a acao foi ou nao realizada
	 */
	public boolean realizarCheckIn(String cartao) {
		if (!(this.cartao.equals(cartao)))
			return false;

		if (getEstado().equals(EstadoDeContrato.PENDENTE)) {
			estado = EstadoDeContrato.ABERTO;
			return true;
		}

		return false;
	}

	/**
	 * Realiza check out do contrato a partir do cartao do contrato (utilizado apenas por seguranca).
	 *
	 * @param cartao  o numero do cartao do contrato
	 * @param data  a data em que o check-out foi realizado
	 * @return boolean representando se a acao foi ou nao realizada
	 */
	public boolean realizarCheckOut(String cartao, Calendar data) {

		if (cartao == null || data == null)
			throw new IllegalArgumentException();

		if (!(this.cartao.equals(cartao)))
			return false;

		for (Pagavel servico : servicos)
			if (servico instanceof Devolvivel && !(((Devolvivel) servico).isDevolvido()))
				return false;

		if (getEstado().equals(EstadoDeContrato.ABERTO)) {
			estado = EstadoDeContrato.FECHADO;
			dataCheckOut = data;
			return true;
		}

		return false;
	}

	/**
	 * Calcula fatura e retorna valor ja com tarifa da estacao.
	 *
	 * @return fatura
	 */
	public double getFatura() {
		double fatura = 0.0;
		double tarifa = estacao.getTarifa();

		for (Pagavel p : servicos) {
			fatura += p.getPreco();
		}

		return fatura * tarifa;
	}
	@Override
	public String toString() {
		return "Contrato " + estado + ", protocolo: " + protocolo;
	}
}
