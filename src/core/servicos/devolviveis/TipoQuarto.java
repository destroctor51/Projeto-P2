package core.servicos.devolviveis;

/**
 * Enum que representa um Tipo de quarto. Cada tipo de quarto tem um valor de diaria(int),
 * uma capacidade(int) e uma descricao(String).<p>
 * Enum desenvolvida para o projeto da disciplina de Laboratorio de Programacao II na UFCG 2014.2.
 *
 * 31 de dezembro de 2014.
 *
 * @author Marianne Linhares Monteiro
 * @version 1.0
 */
public enum TipoQuarto {

	PRESIDENCIAL(1200.0f, 4, "Quarto presidencial", "Quarto equipado com TV LCD 42, split, frigobar, cofre,sala de jogos e home theater, ideal para familias em ferias"),

	LUXO_SIMPLES(520.0f, 1, "Quarto de luxo simples", "Quarto equipado com TV LCD 42, split, frigobar, cofre e home theater"),
	LUXO_DUPLO(570.0f, 2, "Quarto de luxo duplo", "Quarto equipado com TV LCD 42, split, frigobar, cofre e home theater"),
	LUXO_TRIPLO(620.0f, 3, "Quarto de luxo triplo", "Quarto equipado com TV LCD 42, split, frigobar, cofre e home theater"),

	EXECUTIVO_SIMPLES(360.0f, 1, "Quarto executivo simples", "Quarto equipado com TV LCD 42, split, frigobar e cofre"),
	EXECUTIVO_DUPLO(385.0f, 2, "Quarto executivo duplo", "Quarto equipado com TV LCD 42, split, frigobar e cofre"),
	EXECUTIVO_TRIPLO(440.0f, 3 , "Quarto executivo triplo", "Quarto equipado com TV LCD 42, split, frigobar e cofre");

	private float diaria;
	private int capacidade;
	private String nome, descricao;

	/**
	 *
	 * @param diaria
	 * 			Diaria do quarto.
	 * @param capacidade
	 * 			Capacidade do quarto.
	 * @param descricao
	 * 			descricao do quarto.
	 */
	TipoQuarto(float diaria, int capacidade, String nome, String descricao){
		this.diaria = diaria;
		this.capacidade = capacidade;
		this.nome = nome;
		this.descricao = descricao;
	}

	/**
	 *
	 * @return
	 * 			Valor da diaria.
	 */
	public float getDiaria() {
		return diaria;
	}

	/**
	 *
	 * @return
	 * 			Capacidade do quarto.
	 */
	public int getCapacidade() {
		return capacidade;
	}

	/**
	 *
	 * @return
	 * 			Descricao do quarto.
	 */
	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return nome;
	}
}
