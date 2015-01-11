package servicos;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * Classe que cria um Restaurante.
 *
 * @author Jose Benardi de Souza Nunes
 *
 */
public class Restaurante {
	private String nome;
	private Set<Refeicao> estoque;

	/**
	 * Cria um restaurante a partir de seu futuro nome.
	 * 
	 * @param nome
	 *            O nome do restaurante.
	 */
	public Restaurante(String nome) {
		if (nome == null)
			throw new IllegalArgumentException();
		this.nome = nome;
		estoque = new HashSet<Refeicao>(100);
	}

	// metodos

	/**
	 * Cadastra uma refeicao ao estoque do restaurante.
	 * 
	 * @param nome
	 *            O nome da refeicao a ser cadastrada.
	 * @param preco
	 *            O preco da refeicao a ser cadastrada.
	 */
	public void cadastraRefeicao(String nome, float preco) {
		Refeicao umaRefeicao = new Refeicao(preco, nome);
		estoque.add(umaRefeicao);
	}

	/**
	 * Retira refeicao do estoque do restaurante.
	 * 
	 * @param nome
	 *            O nome de uma refeicao a ser descadastrada.
	 */
	public void descadastraRefeicao(String nome) {
		Refeicao ahRetirar = new Refeicao(0, nome);
		Iterator<Refeicao> iterator = estoque.iterator();
		while (iterator.hasNext()) {
			if (ahRetirar.equals(iterator.next())) {
				iterator.remove();
				break;
			}
		}
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
		if (!(obj instanceof Restaurante)) {
			return false;
		}
		Restaurante umRestaurante = (Restaurante) obj;

		return umRestaurante.getNome().equals(getNome());
	}

	@Override
	public String toString() {
		return "Restaurante [nome=" + nome + ", estoque=" + estoque + "]";
	}

}
