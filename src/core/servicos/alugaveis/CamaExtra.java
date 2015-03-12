package core.servicos.alugaveis;

import java.util.Set;
import java.util.TreeSet;

import core.interfaces.Alugavel;
import core.tempo.Periodo;

/**
 *
 * Classe que permite a adicao de um custo aos gastos do hospede no Hotel, no que
 * se refere a uma flexibilidade na acomodacao de um numero de pessoas que excede
 * a capacidade regular de um determinado quarto.
 *
 * @author Jose Benardi de Souza Nunes *
 */
public class CamaExtra implements Alugavel {

	private static final long serialVersionUID = 1L;

	public static final float DIARIA_CAMA_EXTRA = 30;
	private int codigo;

	private Periodo periodoAlugado;
	private Set<Periodo> historico = new TreeSet<>();

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
		return "Cama extra alugada por " + periodoAlugado.getNumeroDias() + " dias";
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
		if(periodoAlugado == null) return 0;
		return DIARIA_CAMA_EXTRA * periodoAlugado.getNumeroDias();
	}

	@Override
	public String toString() {
		return "Cama extra de c\u00F3digo " + Integer.toString(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof CamaExtra))
			return false;
		CamaExtra umaCama = (CamaExtra) obj;
		return umaCama.getCodigo() == getCodigo();
	}

	@Override
	public Object clone()  {
		try {
			CamaExtra clone = (CamaExtra) super.clone();
			clone.historico = new TreeSet<Periodo>();
			if(periodoAlugado != null) {
				clone.periodoAlugado = (Periodo) periodoAlugado.clone();
				clone.historico.add(clone.periodoAlugado);
			} return clone;
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public boolean aluga(Periodo periodo) {
		if(periodo == null)
			throw new IllegalArgumentException();

		if(!historico.add(periodo))
			return false;
		periodoAlugado = periodo;
		return true;
	}

	@Override
	public Set<Periodo> getHistorico() {
		return historico;
	}
}
