package core.hotel;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import core.interfaces.Devolvivel;
import core.interfaces.Pagavel;
import core.servicos.devolviveis.Quarto;
import core.tempo.Periodo;

/**
 * Uma classe que cria contratos.
 *
 * @author Arthur Vinicius Tome Rodrigues
 */
public class Contrato implements Serializable {

	private static final long serialVersionUID = 1L;

	private EstadoDeContrato estado;
	private String cartao;
	private double tarifa;
	private List<Pagavel> servicos = new ArrayList<Pagavel>();
	private Calendar dataCheckOut;

	// construtor

	/**
	 * Cria um contrato a partir do numero de cartao do hospede e da tarifa atual do hotel.
	 *
	 * @param cartao
	 * 			O numero do cartao do hospede.
	 * @param tarifa
	 * 			A tarifa atual do hotel.
	 */
	public Contrato(String cartao, double tarifa) {
		estado = EstadoDeContrato.PENDENTE;
		if (cartao == null || tarifa <= 0)
			throw new IllegalArgumentException();

		this.cartao = cartao;
		this.tarifa = tarifa;
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
	 * Recupera a tarifa adicionada ao contrato.
	 *
	 * @return
	 * 			A tarifa a que o contrato esta sujeito.
	 */
	public double getTarifa() {
		return tarifa;
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

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String datai = "'indispon\u00EDvel'";
		Calendar data = null;
		String quartos = " e de quartos ";
		List<Quarto> quartosl = new ArrayList<>();
		
		for(Pagavel p : servicos) {
			if (p instanceof Quarto) {
				for (Periodo p1 : ((Quarto) p).getHistorico()) {
					if (data == null)
						data = p1.getInicio();
				}
				quartosl.add((Quarto) p);
			}
		}

		for (int i = 0; i < quartosl.size(); i++) {
			if (i < quartosl.size() - 2)
				quartos += Integer.toString(quartosl.get(i).getNumero()) + ", ";
			else if (i < quartosl.size() - 1)
				quartos += Integer.toString(quartosl.get(i).getNumero()) + " e ";
			else
				quartos += Integer.toString(quartosl.get(i).getNumero());
		}
		
		if (data != null) {
		Date d = data.getTime();
		datai = sdf.format(d);
		} else
			quartos = " e sem quartos";
		
		return "Contrato " + estado + ", com inicio em " + datai + quartos;
	}
}
