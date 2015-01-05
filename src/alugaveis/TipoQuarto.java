package alugaveis;

/**
 * Enum que representa um Tipo de quarto. Cada tipo de quarto tem um valor de diaria(int),
 * uma capacidade(int) e uma descrição(String).<p>
 * Enum desenvolvida para o projeto da disciplina de Laboratório de Programação II na UFCG 2014.2.
 * 
 * 31 de dezembro de 2014.
 * 
 * @author Marianne Linhares Monteiro
 * @version 1.0
 */
public enum TipoQuarto {

	PRESIDENCIAL(1200.0, 4, "Quarto Presidencial\nQuarto equipado com TV LCD 42, split, frigobar, cofre,sala de jogos e home theater, ideal para famílias em férias.\n"),
	
	LUXO_SIMPLES(520.0, 1, "Quarto Luxo Simples\nQuarto equipado com TV LCD 42, split, frigobar, cofre e home theater.\n"),
	LUXO_DUPLO(570.0, 2, "Quarto Luxo Duplo\nQuarto equipado com TV LCD 42, split, frigobar, cofre e home theater.\n"),
	LUXO_TRIPLO(620.0, 3, "Quarto Luxo Triplo\nQuarto equipado com TV LCD 42, split, frigobar, cofre e home theater.\n"),
	
	EXECUTIVO_SIMPLES(360.0, 1, "Quarto Executivo Simples\nQuarto equipado com TV LCD 42, split, frigobar e cofre.\n"),
	EXECUTIVO_DUPLO(385.0, 2, "Quarto Executivo Duplo\nQuarto equipado com TV LCD 42, split, frigobar e cofre.\n"),
	EXECUTIVO_TRIPLO(440.0, 3 , "Quarto Executivo Triplo\nQuarto equipado com TV LCD 42, split, frigobar e cofre.\n");
	
	private double diaria;
	private int capacidade;
	private String descricao;
	
	/**
	 * 
	 * @param diaria
	 * 			Diaria do quarto.
	 * @param capacidade
	 * 			Capacidade do quarto.
	 * @param descricao
	 * 			descricao do quarto.
	 */
	TipoQuarto(double diaria, int capacidade, String descricao){
		this.diaria = diaria;
		this.capacidade = capacidade;
		this.descricao = descricao;
	}

	/**
	 * 
	 * @return
	 * 			Valor da diária.
	 */
	public double getDiaria() {
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
	 * 			Descrição do quarto.
	 */
	public String getDescricao() {
		return descricao;
	}

}
