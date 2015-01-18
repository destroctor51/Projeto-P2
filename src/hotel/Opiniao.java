package hotel;

/**
 * Uma classe que cria opinioes.
 *
 * @author Arthur Vinicius Tome Rodrigues
 */
public class Opiniao {
	private int nota;
	private String comentario;
	private String autor = "Anonimo";

	// construtor

	/**
	 * Cria uma opiniao a partir da nota e do comentario.
	 *
	 * @param nota
	 * 			A nota do produto em que sera registrada a opiniao.
	 * @param comentario
	 * 			O comentario da opiniao.
	 */
	public Opiniao(int nota, String comentario) {
		this("Anonimo", nota, comentario);
	}

	/**
	 * Cria uma opiniao a partir do autor, da nota e do comentario.
	 *
	 * @param autor
	 * 			O nome do autor da opiniao.
	 * @param nota
	 * 			A nota do produto em que sera registrada a opiniao.
	 * @param comentario
	 * 			O comentario da opiniao.
	 */
	public Opiniao(String autor, int nota, String comentario) {
		if(comentario == null)
			throw new IllegalArgumentException();

		this.nota = nota;
		this.comentario = comentario;
		this.autor = autor;
	}

	// metodos

	/**
	 * Recupera a nota da opiniao.
	 *
	 * @return
	 * 			A nota dada na opiniao.
	 */
	public int getNota() {
		return nota;
	}

	/**
	 * Recupera o comentario da opiniao.
	 *
	 * @return
	 * 			O comentario dado na opiniao.
	 */
	public String getComentario() {
		return comentario;
	}

	/**
	 * Recupera o autor da opiniao.
	 *
	 * @return
	 * 			O autor que deu a opiniao.
	 */
	public String getAutor() {
		return autor;
	}

	@Override
	public String toString() {
		return "Opiniao [Nota = " + nota + ", comentario = " + comentario
				+ ", autor = " + getAutor() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + autor.hashCode();
		result = prime * result + comentario.hashCode();
		result = prime * result + nota;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Opiniao))
			return false;

		Opiniao umaOpiniao = (Opiniao) obj;
		return getNota() == umaOpiniao.getNota() &&
				getAutor().equals(umaOpiniao.getAutor()) &&
				getComentario().equals(umaOpiniao.getComentario());
	}

}
