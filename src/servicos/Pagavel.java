package servicos;

/**
 * 
 * Interface que representa classes passiveis de pagamento.
 *
 *@author Jose Benardi de Souza Nunes
 */
public interface Pagavel {
	
	/**
	 * 
	 * @return 
	 * 		a descricao do pagavel .
	 */
	public String getDescricao();
	/**
	 * 
	 * @return
	 * 		O custo associado ao Pagavel.
	 */
	public float getPreco();

}
