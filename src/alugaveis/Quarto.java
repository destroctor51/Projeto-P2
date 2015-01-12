package alugaveis;

import hotel.Hospede;
import interfaces.Alugavel;
import tempo.Periodo;

/**
 * Classe que representa um Quarto. A Classe Quarto guarda as informacoes referentes ao quarto alugado
 * alem de gerenciar o tempo conferido ao Hospede para o usufruto do Quarto. <p>
 * Classe desenvolvida para o projeto da disciplina de Laboratorio de Programacao II na UFCG 2014.2.
 * 
 * 31 de dezembro de 2014.
 * 
 * @author Marianne Linhares Monteiro
 * @version 1.0
 */
public class Quarto implements Alugavel{
	
	private Hospede responsavel;
	private TipoQuarto tipoQuarto;
	private float preco;
	private Periodo periodo;
	private int numero;
	private boolean devolvido;
	
	/**
	 * Construtor do Quarto.
	 * @param tipoQuarto
	 * 			Tipo do quarto.
	 * @param numero
	 * 			Numero do quarto.
	 */
	public Quarto(TipoQuarto tipoQuarto, int numero){	
		
		if(tipoQuarto == null)
			throw new IllegalArgumentException();
		
		this.tipoQuarto = tipoQuarto;
		this.numero= numero;
		devolvido = true;
		preco = 0;
	}
	
	/**
	 * Metodo utilizado para alugar um quarto.
	 * @param responsavel
	 * 			Hospede responsavel pelo aluguel.
	 * @param periodo
	 * 			Periodo que foi alugado. 
	 */
	public void alugar(Hospede responsavel, Periodo periodo){

		if(responsavel == null)
			throw new IllegalArgumentException();
		if(periodo == null)
			throw new IllegalArgumentException();
		
		this.responsavel = responsavel;
		this.periodo = periodo;	
		devolvido = false;
		preco = 0;
	}

	@Override
	/**
	 * @inheritDoc
	 */
	public String getDescricao() {
		return toString();
	}

	@Override
	/**
	 * @inheritDoc
	 */
	public float getPreco() {
		return preco;
	}

	/**
	 * 
	 * @return
	 * 			Numero do quarto.
	 */
	public int getNumero() {
		return numero;
	}
	
	/**
	 * 
	 * @return
	 * 			Hospede responsavel pelo quarto.
	 */
	public Hospede getResponsavel() {
		return responsavel;
	}

	/**
	 * 
	 * @param responsavel
	 * 			O responsavel pelo quarto sera alterado para o hospede recebido como parametro.
	 */
	public void setResponsavel(Hospede responsavel) {
		this.responsavel = responsavel;
	}

	/**
	 * 
	 * @return
	 * 			Numero de dias alugados.
	 */
	public int getDiasAlugados() {
		return periodo.getNumeroDias();
	}
	
	@Override
	/**
	 * @inheritDoc
	 */
	public Periodo getPeriodo() {
		return periodo;
	}
	
	/**
	 * 
	 * @param periodo
	 * 		O periodo em que o objeto foi alugado sera alterado para o periodo recebido como parametro.
	 */
	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}
	
	@Override
	/**
	 * @inheritDoc
	 */
	public void devolve() {
		if (devolvido == false) {
			preco = getDiasAlugados() * tipoQuarto.getDiaria();
			devolvido = true;
			periodo = null;
			responsavel = null;
		}
	}

	@Override
	/**
	 * @inheritDoc
	 */
	public boolean isDevolvido() {
		return devolvido;
	}

	@Override
	/**
	 *@return
	 *		Uma representacao textual do objeto.
	 */
	public String toString(){
		return "Numero do quarto: " + getNumero() + ". " +
				tipoQuarto.getDescricao() +
			   "Quarto para " + tipoQuarto.getCapacidade() + " pessoas. " +
			   "Valor da diaria: " + tipoQuarto.getDiaria() + ". " +
			   "Valor total: " + getPreco() + ".";
	}
	
	@Override
	/**
	 * @return
	 * 		Se dois quartos forem iguais retorna true, caso contrario retorna false. Dois quartos sao iguais quando
	 * 		possuem o mesmo numero.
	 */
	public boolean equals(Object obj){
		
		if(!(obj instanceof Quarto))
			return false;
		
		Quarto outroQuarto = (Quarto) obj;	
		return getNumero() == outroQuarto.getNumero();
	}
	
}
