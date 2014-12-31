package servicos;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Classe que cria um Restaurante.
 *
 * @author Jose Benardi de Souza Nunes
 *
 */
public class Restaurante {
	private String nome;
	private List<Refeicao> estoque;

	/**
	 * Cria um restaurante a partir de seu futuro nome.
	 * 
	 * @param nome
	 *            O nome do restaurante.
	 */
	public Restaurante(String nome) {
		this.nome = nome;
		estoque = new ArrayList<Refeicao>();
	}

	/**
	 * Cria um restaurante a partir de seu futuro nome e de um estoque ja
	 * definido.
	 * 
	 * @param nome
	 *            O nome do restaurante.
	 * @param novoestoque
	 *            O estoque a ser utilizado.
	 */
	public Restaurante(String nome, List<Refeicao> novoestoque) {
		this.nome = nome;
		estoque = novoestoque;
	}

	// metodos

	/**
	 * Cadastra uma refeicao ao estoque do restaurante.
	 * 
	 * @param umaRefeicao
	 *            Refeicao a ser cadastrada.
	 */
	public void cadastraRefeicao(Refeicao umaRefeicao) {
		estoque.add(umaRefeicao);
	}

	/**
	 * Retira refeicao do estoque do restaurante.
	 * 
	 * @param umaRefeicao
	 *            Refeicao a ser descadastrada.
	 */
	public void descadastraRefeicao(Refeicao umaRefeicao) {
		estoque.remove(umaRefeicao);

	}

	/**
	 * Muda o nome dado ao restaurante.
	 * 
	 * @param novoNome
	 *            O novo nome a ser dado ao restaurante.
	 */
	public void ateraNome(String novoNome) {
		nome = novoNome;
	}

	/**
	 * Recupera o nome do restaurante.
	 * 
	 * @return nome O nome do restaurante.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Recupera o estoque do restaurante.
	 * 
	 * @return estoque O estoque do restaurante.
	 */
	public List<Refeicao> getEstoque() {
		return estoque;
	}

	/**
	 * Um restaurante eh igual ao outro quando tem o mesmo nome e o mesmo
	 * estoque.
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Restaurante)) {
			return false;
		}
		Restaurante umRestaurante = (Restaurante) obj;

		return umRestaurante.getNome().equals(getNome())
				&& umRestaurante.getEstoque().size() == getEstoque().size()
				&& umRestaurante.getEstoque().containsAll(getEstoque());
	}

	@Override
	public String toString() {
		String respective = "Restaurante [nome=" + nome + ", estoque= ";
		for (int i = 0; i < getEstoque().size(); i++) {
			if (i == getEstoque().size() - 1) {
				respective += getEstoque().get(i);
			} else {
				respective += getEstoque().get(i) + ", ";
			}
		}
		respective += "]";
		return respective;

	}

}
