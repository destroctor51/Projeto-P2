package servicos.pagaveis;

import interfaces.Pagavel;

/**
 *
 * Classe que cria uma refeicao.
 *
 * @author Jose Benardi de Souza Nunes
 *
 */
public class Refeicao implements Pagavel {
	private String nome;
	private float preco;

	/**
	 * Cria uma refeicao a partir de um preco e um nome.
	 *
	 * @param preco
	 *            O preco da refeicao.
	 * @param nome
	 *            O nome da refeicao.
	 */
	public Refeicao(String nome, float preco) {
		if (nome == null)
			throw new IllegalArgumentException();
		this.preco = preco;
		this.nome = nome;
	}

	// metodos

	/**
	 * Altera o nome da refeicao.
	 *
	 * @param novaDescricao
	 *            O novo nome dado ah refeicao.
	 */
	public void alteraDescricao(String novaDescricao) {
		if (novaDescricao == null)
			throw new IllegalArgumentException();
		nome = novaDescricao;
	}

	/**
	 * Altera o preco da refeicao.
	 *
	 * @param novoPreco
	 *            O novo preco dado ah refeicao.
	 */
	public void alteraPreco(float novoPreco) {
		preco = novoPreco;
	}

	/**
	 * Recupera a descricao da refeicao
	 *
	 * @return nome
	 * 		O nome da refeicao
	 */
	@Override
	public String getDescricao() {
		return nome;
	}

	@Override
	public String toString() {
		return "Refeicao [Descricao=" + getDescricao() + ", Preco="
				+ getPreco() + "]";
	}

	/**
	 * Recupera o preco da refeicao.
	 *
	 * @return
	 * 		Preco da refeicao.
	 */
	@Override
	public float getPreco() {
		return preco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + nome.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Refeicao))
			return false;

		Refeicao umaRefeicao = (Refeicao) obj;
		return umaRefeicao.getDescricao().equals(getDescricao());
	}

	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException();
		}
	}
}
