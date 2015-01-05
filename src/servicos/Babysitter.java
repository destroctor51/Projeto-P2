package servicos;

import interfaces.Pagavel;

/**
 * 
 *Classe que cria uma babysitter.
 *
 *@author Jose Benardi de Souza Nunes
 */
public class Babysitter implements Pagavel {
	private String nome;
	private int horasNormais;
	private int horasDobradas;
/**
 * Cria  uma babysitter a partir de um nome, as horas normais e dobradas em que estara empregada.
 * 
 * @param nome
 * 		O nome da babysitter.
 * @param horasNormais
 * 		As horas que trabalharah em expediente regular.
 * @param horasDobradas
 * 		As horas que trabalharah em expediente extra regular.
 */
	public Babysitter(String nome, int horasNormais, int horasDobradas) {
		if (nome == null)throw new IllegalArgumentException();
		this.nome = nome;
		this.horasNormais = horasNormais;
		this.horasDobradas = horasDobradas;
	}
//metodo
	/**
	 * Recupera o nome da babysitter.
	 * 
	 * @return nome
	 * 		O nome da babysitter.
	 */
	@Override
	public String getDescricao() {
		return nome;
	}
	/**
	 *  Altera o nome da babysitter.
	 *  
	 * @param novoNome
	 *  O novo nome da babysitter.
	 */
	public void alteraNome(String novoNome){
		nome = novoNome;
	}
	/**
	 * Retorna o preco resultante do servico prestado pela babyssiter.
	 * 
	 */
	@Override
	public float getPreco() {
		return horasNormais * 25 + horasDobradas * 50;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Babysitter)) {
			return false;
		}
		Babysitter umaBaba = (Babysitter) obj;
		
		return umaBaba.getDescricao().equals(getDescricao());
	}

	@Override
	public String toString() {
		return "Babysitter [nome=" + nome + ", horasNormais=" + horasNormais
				+ ", horasDobradas=" + horasDobradas + "]";
	}
}
