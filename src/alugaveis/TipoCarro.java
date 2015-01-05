package alugaveis;

/**
 * Enum que representa um Tipo de carro. Cada tipo de carro tem um valor de diaria(int) e um nome(String).<p>
 * Enum desenvolvida para o projeto da disciplina de Laboratório de Programação II na UFCG 2014.2.
 * 
 * 31 de dezembro de 2014.
 * 
 * @author Marianne Linhares Monteiro
 * @version 1.0
 */
 public enum TipoCarro {
	
	LUXO(100.0, "Carro Luxo"),EXECUTIVO(60.0, "Carro Executivo");
	
	private double diaria;
	private String nome;
	
	/**
	 * 
	 * @param diaria
	 * 			Diária do carro.
	 * @param nome
	 * 			Nome do carro.
	 */
	TipoCarro(double diaria, String nome){
		this.diaria = diaria;
		this.nome = nome;
	}

	/**
	 * 
	 * @return
	 * 		Valor da diária.
	 */
	public double getDiaria() {
		return diaria;
	}
	/**
	 * 
	 * @return
	 * 		Nome do tipo de carro.
	 */
	public String getNome() {
		return nome;
	}

}
