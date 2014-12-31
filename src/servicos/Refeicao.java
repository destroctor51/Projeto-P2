package servicos;

/**
 * 
 * Classe que cria uma refeicao.
 *
 *@author Jose Benardi de Souza Nunes
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
	 * @throws Exception
	 */
	public Refeicao(float preco, String nome) throws Exception {
		if (nome == null)
			throw new NullPointerException();
		this.preco = preco;
		this.nome = nome;
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
	 * Recupera a descricao da refeicao.
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
	 * Recupera o preco da Refeicao.
	 */
	@Override
	public float getPreco() {
		return preco;
	}
/**
 * Uma refeicao eh igual ah outra quando ambas tem a mesma descricao e o mesmo preco.
 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Refeicao)) {
			return false;
		}
		Refeicao umaRefeicao = (Refeicao) obj;

		return umaRefeicao.getDescricao() == getDescricao()
				&& umaRefeicao.getPreco() == getPreco();
	}
}
