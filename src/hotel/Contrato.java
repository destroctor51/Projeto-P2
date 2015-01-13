package hotel;

import interfaces.Devolvivel;
import interfaces.Pagavel;

import java.util.ArrayList;
import java.util.List;

import servicos.devolviveis.Quarto;
import tempo.Periodo;

/**
 * Uma classe que cria contratos.
 * 
 * @author Arthur Vinicius Tome Rodrigues
 */
public class Contrato {
	private EstadoDeContrato estado;
	private String cartao;
	private double tarifa;
	private Periodo estadia;
	private List<Quarto> quartos;
	private List<Pagavel> servicos = new ArrayList<Pagavel>();
	
	// construtor
	
	/**
	 * Cria um contrato a partir do numero de cartao do hospede e da tarifa atual do hotel.
	 * 
	 * @param cartao
	 * 			O numero do cartao do hospede.
	 * @param tarifa
	 * 			A tarifa atual do hotel.
	 */
	public Contrato(String cartao, double tarifa, Periodo estadia, List<Quarto> quartos) {
		estado = EstadoDeContrato.PENDENTE;
		if (cartao == null || estadia == null || quartos == null || tarifa <= 0)
			throw new IllegalArgumentException();
		
		this.cartao = cartao;
		this.tarifa = tarifa;
		this.estadia = estadia;
		this.quartos = quartos;
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
	 * Seta o estado do contrato.
	 * 
	 * @param estado
	 * 			O estado a ser colocado no contrato.
	 */
	private void setEstado(EstadoDeContrato estado) {
		this.estado = estado;
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
	 * 			A tarifa a que o contrato está sujeito.
	 */
	public double getTarifa() {
		return tarifa;
	}

	/**
	 * Recupera o periodo de estadia contratada pelo hospede.
	 * 
	 * @return
	 * 			O periodo de estadia do hospede.
	 */
	public Periodo getEstadia() {
		return estadia;
	}
	
	/**
	 * Recupera uma lista com os quartos do contrato.
	 * 
	 * @return
	 * 			Lista com todos os quartos do contrato.
	 */
	public List<Quarto> getQuartos() {
		return quartos;
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
	 * 
	 * @param servico
	 * 			O servico a ser adicionado ao contrato.
	 */
	public void adicionaServico(Pagavel servico) {
		if (servico == null)
			throw new IllegalArgumentException();
		servicos.add(servico);
	}
	
	/**
	 * Adiciona uma lista de servicos ao contraro.
	 * 
	 * @param servicos
	 * 			Lista de servicos a ser adicionada.
	 */
	public void adicionaServicos(List<Pagavel> servicos) {
		if (servicos == null)
			throw new IllegalArgumentException();
		this.servicos.addAll(servicos);
	}
	
	/**
	 * Remove um servico do contrato.
	 * 
	 * @param servico
	 * 			O servico a ser removido.
	 */
	public void removeServico(Pagavel servico) {
		if (servico == null)
			throw new IllegalArgumentException();
		servicos.remove(servico);
	}
	
	/**
	 * Revome varios servicos do contrato a partir de uma lista com os servicos a serem removidos.
	 * 
	 * @param servicos
	 * 			Lista de servicos a serem removidos.
	 */
	public void removeServicos(List<Pagavel> servicos) {
		if (servicos == null)
			throw new IllegalArgumentException();
		this.servicos.removeAll(servicos);
	}
	
	/**
	 * Realiza check in do contrato a partir do cartao do contrato (utilizado apenas por seguranca). 
	 * 
	 * @param cartao
	 * 			O numero do cartao do contrato.
	 * @return
	 * 			Boolean representando se a acao foi ou nao realizada.
	 */
	public boolean realizarCheckIn(String cartao) {
		if (cartao == null || cartao.equals(""))
			throw new IllegalArgumentException();
		
		if (!(cartao.equals(this.cartao)))
			return false;
		
		if (getEstado().equals(EstadoDeContrato.PENDENTE))
			setEstado(EstadoDeContrato.ABERTO);
		return true;
	}
	
	/**
	 * Realiza check out do contrato a partir do cartao do contrato (utilizado apenas por seguranca). 
	 * 
	 * @param cartao
	 * 			O numero do cartao do contrato.
	 * @return
	 * 			Boolean representando se a acao foi ou nao realizada.
	 */
	public boolean realizarCheckOut(String cartao) {
		if (cartao == null || cartao.equals(""))
			throw new IllegalArgumentException();
		
		if (!(cartao.equals(this.cartao)))
			return false;
		
		for (Pagavel servico : servicos) {
			if (servico instanceof Devolvivel && !(((Devolvivel) servico).isDevolvido()))
				return false;
		}
		
		if (getEstado().equals(EstadoDeContrato.ABERTO))
				setEstado(EstadoDeContrato.FECHADO);
		return true;
	}
	
	@Override
	public String toString() {
		return "Contrato [Estado = " + estado + "]";
	}
}
