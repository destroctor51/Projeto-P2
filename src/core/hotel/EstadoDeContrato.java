package core.hotel;

/**
 * Enumeracao utilizada no status de um contrato.
 *
 * @author Arthur Vinicius Tome Rodrigues
 */
public enum EstadoDeContrato {
	PENDENTE("Pendente"), ABERTO("Aberto"), FECHADO("Fechado");
	
	private String nome;
	
	private EstadoDeContrato(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return nome;
	};
}
