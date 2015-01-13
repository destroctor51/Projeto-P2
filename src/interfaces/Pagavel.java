package interfaces;

/**
 * 
 * Interface que representa classes passiveis de pagamento. Um objeto pagavel deve ser capaz de retornar 
 * uma descricao (String) e um preco (float).
 * Interface desenvolvida para o projeto da disciplina de Laboratorio de Programacao II na UFCG 2014.2. 
 *@author Jose Benardi de Souza Nunes
 */
public interface Pagavel {
	
	/**
	 * 
	 * @return 
	 * 		A descricao do pagavel. Informacoes em geral sobre o objeto.
	 */
	public String getDescricao();
	
	/**
	 * 
	 * @return
	 * 		O custo associado ao Pagavel.
	 */
	public float getPreco();
}
