package servicos;

/**
 * 
 * Classe que cria uma cama extra.
 *
 *@author Jose Benardi de Souza Nunes
 *
 */
public class CamaExtra implements Pagavel {
	public static final float DIARIA_CAMA_EXTRA = 30.0f;
	private int diasReservados;
	private final String descricao = "Camas extras para crianças menores de 9 anos são permitidas em quartos do tipo luxo e executivo single e double quando o hóspede solicitar na reserva e de acordo com a disponibilidade de cama extra no diasReservados.";
/**
 * Cria uma cama extra a  partir dos dias em que estarah reservada.
 * @param diasReservados
 * 		Dias em que estarah reservada.
 */
	public CamaExtra(int diasReservados) {
		this.diasReservados = diasReservados;
	}

	@Override
	public String toString() {
		return "CamaExtra [diasReservados=" + diasReservados + ", descricao=" + descricao
				+ "]";
	}
/**
 * Recupera a descricao de uma cama extra.
 */
	@Override
	public String getDescricao() {
		return descricao;
	}
/**
 * Recupera o custo da locacao da cama extra em questao.
 */
	@Override
	public float getPreco() {
		return DIARIA_CAMA_EXTRA * diasReservados;
	}

}
