package alugaveis;

import tempo.Periodo;
import interfaces.Alugavel;
import classesTemporarias.Hospede;

/**
 * Classe que representa um Quarto. A Classe Quarto guarda as informações referentes ao quarto alugado
 * além de gerenciar o tempo conferido ao Hospede para o usufruto do Quarto. <p>
 * Classe desenvolvida para o projeto da disciplina de Laboratório de Programação II na UFCG 2014.2.
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
	 * @param placa
	 * 			Numero do quarto.
	 */
	public Quarto(TipoQuarto tipoQuarto,int numero){	
		
		if(tipoQuarto == null)
			throw new IllegalArgumentException();
		
		this.tipoQuarto = tipoQuarto;
		this.numero= numero;
		devolvido = true;
		preco = 0;
	}
	
	/**
	 * Método utilizado para alugar um quarto.
	 * @param responsavel
	 * 			Hóspede responsável pelo aluguel.
	 * @param periodo
	 * 			Período que foi alugado. 
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
	 * 			Número do quarto.
	 */
	public int getNumero() {
		return numero;
	}
	
	/**
	 * 
	 * @return
	 * 			Hóspede responsável pelo quarto.
	 */
	public Hospede getResponsavel() {
		return responsavel;
	}

	/**
	 * 
	 * @param responsavel
	 * 			O responsável pelo quarto será alterado para o hóspede recebido como parâmetro.
	 */
	public void setResponsavel(Hospede responsavel) {
		this.responsavel = responsavel;
	}

	/**
	 * 
	 * @return
	 * 			Número de dias alugados.
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
	 * 		O período em que o objeto foi alugado será alterado para o período recebido como parâmetro.
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
	 *		Uma representação textual do objeto.
	 */
	public String toString(){
		return "Numero do quarto: " + getNumero() + ". " +
				tipoQuarto.getDescricao() +
			   "Quarto para " + tipoQuarto.getCapacidade() + " pessoas. " +
			   "Valor da diária: " + tipoQuarto.getDiaria() + ". " +
			   "Valor total: " + getPreco() + ".";
	}
	
	@Override
	/**
	 * @return
	 * 		Se dois quartos forem iguais retorna true, caso contrário retorna false. Dois quartos são iguais quando
	 * 		possuem o mesmo número.
	 */
	public boolean equals(Object obj){
		
		if(!(obj instanceof Quarto))
			return false;
		
		Quarto outroQuarto = (Quarto) obj;	
		return getNumero() == outroQuarto.getNumero();
	}
	
}
