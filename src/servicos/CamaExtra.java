package servicos;

import interfaces.Alugavel;
import tempo.Periodo;

/**
 * 
 * Classe que cria uma cama extra.
 *
 * @author Jose Benardi de Souza Nunes *
 */
public class CamaExtra implements Alugavel {

	private final String descricao = "Servico de Cama Extra";
	private int codigo;
	public static final float DIARIA_CAMA_EXTRA = 30;
	private Periodo aluguel;

	/**
	 * Cria uma cama extra a partir dos dias em que estarah reservada.
	 * 
	 * @param codigo
	 *            O codigo que representa a cama.
	 */
	public CamaExtra(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Recupera a descricao de uma cama extra.
	 * 
	 * @return descricao A descricao generalizante de uma cama extra.
	 */
	@Override
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Recupera o codigo referente ah cama.
	 * 
	 * @return codigo O codigo que identifica a cama;
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Recupera o custo da locacao da cama extra em questao.
	 * 
	 * return O custo referente ao uso da cama.
	 */
	@Override
	public float getPreco() {
		return  (DIARIA_CAMA_EXTRA * getPeriodo().getNumeroDias());
	}

	@Override
	public String toString() {
		return "CamaExtra [descricao=" + descricao + ", codigo=" + codigo + "]";
	}
	
	/**
	 * Determina o periodo em que o servico de cam extra sera solicitado
	 * 
	 * @param novoAluguel
	 * 		O novo periodo em que a cama extra estara sendo usada.
	 */
	public void setPeriodo(Periodo novoAluguel) {
		aluguel = novoAluguel;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof CamaExtra)) {
			return false;
		}
		CamaExtra umaCama = (CamaExtra) obj;

		return umaCama.getCodigo() == getCodigo();
	}
	
	/**
	 * Retorna o periodo em que a cama esta sendo alugada.
	 * 
	 * @return
	 * 		O periodo de locacao da cama.
	 */
	@Override
	public Periodo getPeriodo() {
		return aluguel;
	}

	@Override
	public void devolve() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDevolvido() {
		// TODO Auto-generated method stub
		return false;
	}

}
