package servicos.devolviveis;

/**
 * Enum que representa um Tipo de carro. Cada tipo de carro tem um valor de diaria(int) e uma descricao(String).<p>
 * Enum desenvolvida para o projeto da disciplina de Laboratorio de Programacao II na UFCG 2014.2.
 * 
 * 31 de dezembro de 2014.
 * 
 * @author Marianne Linhares Monteiro
 * @version 1.0
 */
 public enum TipoCarro {
	
	LUXO(100.0f, "Carro Luxo"),EXECUTIVO(60.0f, "Carro Executivo");
	
	private float diaria;
	private String nome;
	
	/**
	 * 
	 * @param diaria
	 * 			Diaria do carro.
	 * @param nome
	 * 			Nome do carro.
	 */
	TipoCarro(float diaria, String nome){
		this.diaria = diaria;
		this.nome = nome;
	}

	/**
	 * 
	 * @return
	 * 		Valor da diaria.
	 */
	public float getDiaria() {
		return diaria;
	}
	/**
	 * 
	 * @return
	 * 		Descricao do carro.
	 */
	public String getDescricao() {
		return nome;
	}

}
