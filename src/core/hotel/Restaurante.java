package core.hotel;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import core.servicos.pagaveis.Refeicao;

/**
 *
 * Classe que gerencia por meio de cadastramento e remocao a manutencao e
 * disponibilidade de refeicoes.
 *
 * @author Jose Benardi de Souza Nunes
 *
 */
public class Restaurante implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private Set<Refeicao> estoque;

	/**
	 * Cria um restaurante a partir de seu futuro nome.
	 *
	 * @param nome
	 *            O nome do restaurante.
	 */
	public Restaurante(String nome) {
		if (nome == null || nome.equals(""))
			throw new IllegalArgumentException();
		this.nome = nome;
		estoque = new HashSet<Refeicao>();
	}

	// metodos

	/**
	 * Cadastra uma refeicao ao estoque do restaurante.
	 *
	 * @param nome  o nome da refeicao a ser cadastrada.
	 * @param preco  o preco da refeicao a ser cadastrada.
	 * @return true se a refeicao for cadastrada com sucesso, false caso ja hava uma refeicao com o mesmo nome
	 */
	public boolean cadastraRefeicao(String nome, float preco) {
		Refeicao umaRefeicao = new Refeicao(nome, preco);
		return estoque.add(umaRefeicao);
	}

	/**
	 * Retira refeicao do estoque do restaurante.
	 *
	 * @param refeicao  a refeicao a ser removida
	 * @return true caso a refeicao seja removida com sucesso
	 */
	public boolean removeRefeicao(Refeicao refeicao) {
		return estoque.remove(refeicao);
	}

	/**
	 * Muda o nome dado ao restaurante.
	 *
	 * @param novoNome
	 *            O novo nome a ser dado ao restaurante.
	 */
	public void alteraNome(String novoNome) {
		nome = novoNome;
	}

	/**
	 * Recupera o nome do restaurante.
	 *
	 * @return nome
	 * 		O nome do restaurante.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Recupera o estoque do restaurante.
	 *
	 * @return estoque
	 * 		O estoque do restaurante.
	 */
	public Set<Refeicao> getEstoque() {
		return estoque;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Restaurante))
			return false;

		Restaurante umRestaurante = (Restaurante) obj;
		return umRestaurante.getNome().equals(getNome());
	}

	@Override
	public String toString() {
		return nome;
	}

}
