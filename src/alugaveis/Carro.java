package alugaveis;

import tempo.Periodo;
import interfaces.Alugavel;
import classesTemporarias.Hospede;

/**
 * Classe que representa um Carro. Carro se comporta como alugável,tem uma placa que o designa
 * e pode estar vinculado a um Hóspede e consequentemente estando com seu retorno pendente.<p>
 * Classe desenvolvida para o projeto da disciplina de Laboratório de Programação II na UFCG 2014.2.
 * 
 * 31 de dezembro de 2014.
 * 
 * @author Marianne Linhares Monteiro
 * @version 1.0
 */
public class Carro implements Alugavel{
	
	/**
	 * Valor pago se o serviço tanque cheio for solicitado.
	 */
	public final static double VALOR_TANQUE = 150.0;

	/**
	 * Valor pago se o seguro do carro for solicitado.
	 */
	public final static double VALOR_SEGURO = 100.0;
	
	/**
	 * Valor padrão pago se um serviço não for solicitado.
	 */
	public final static double SEM_SERVICO = 0.0;

	private static final int LENGTH_PLACA = 7;
	
	private Hospede responsavel;
	private TipoCarro tipoCarro;
	private String placa;
	private float preco;
	private boolean tanqueCheio;
	private boolean seguro;
	private Periodo periodo;
	
	private boolean devolvido;
	
	/**
	 * Construtor do Carro.
	 * @param tipoCarro
	 * 			Tipo do carro(Executivo ou Luxo).
	 * @param placa
	 * 			Placa do carro.
	 */
	public Carro(TipoCarro tipoCarro, String placa) {
		
		if(tipoCarro == null)
			throw new IllegalArgumentException();
		if(placa == null)
			throw new IllegalArgumentException();
		
		this.tipoCarro = tipoCarro;
		this.placa = placa;
		
		devolvido = true;
		periodo = null;
		preco = 0;
	}
	
	/**
	 * Método utilizado para alugar um carro.
	 * @param responsavel
	 * 			Hóspede responsável pelo aluguel.
	 * @param tanqueCheio
	 * 			boolean que representa se o serviço tanque cheio foi solicitado ou não.
	 * @param seguro
	 * 			boolean que representa se o seguro foi solicitado ou não.
	 * @param periodo
	 * 			Período que será alugado. 
	 */
	public void alugar(Hospede responsavel, boolean tanqueCheio, boolean seguro, Periodo periodo) {
		
		if(responsavel == null)
			throw new IllegalArgumentException();
		if(periodo == null)
			throw new IllegalArgumentException();
		
		this.responsavel = responsavel;
		this.tanqueCheio = tanqueCheio;
		this.seguro = seguro;
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
	 * 			Placa do carro.
	 */
	public String getPlaca() {
		return placa;
	}

	/**
	 * 
	 * @return
	 * 			Número de dias alugados.
	 */
	public int getNumeroDias() {
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
	
	/**
	 * 
	 * @return
	 * 			Hóspede responsável pelo carro.
	 */
	public Hospede getResponsavel() {
		return responsavel;
	}

	/**
	 * 
	 * @param responsavel
	 * 			O responsável pelo carro será alterado para o hóspede recebido como parâmetro.
	 */
	public void setResponsavel(Hospede responsavel) {
		this.responsavel = responsavel;
	}
	
	@Override
	/**
	 * @inheritDoc
	 */
	public void devolve() {
		
		if (devolvido == false) {
			preco += getNumeroDias() * tipoCarro.getDiaria();
			preco += tanqueCheio ? Carro.VALOR_TANQUE : Carro.SEM_SERVICO;
			preco += seguro ? Carro.VALOR_SEGURO : Carro.SEM_SERVICO;

			responsavel = null;
			devolvido = true;
			periodo = null;
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
		return tipoCarro.getNome() + ". " +
			   "Placa do carro: " + getPlaca() + ". " + 
			   "Valor da diária: " + tipoCarro.getDiaria() + ". " +
			   "Servico tanque cheio: " + (tanqueCheio ? "" : "nao ") + "incluso. " + 
			   "Seguro: " + (seguro ? "" : "nao ") + "incluso. " +
			   "Valor total: " + getPreco() + ".";
	}
	
	/**
	 * @return
	 * 		Se dois carros forem iguais retorna true, caso contrário retorna false. Dois carros são iguais
	 * 		se tiverem mesma placa.
	 */
	public boolean equals(Object obj){
		
		if(!(obj instanceof Carro))
			return false;
		
		Carro outroCarro = (Carro) obj;
		
		return getPlaca().equals(outroCarro.getPlaca());
	}
	
	/**
	 * 
	 * @param placa
	 * @return
	 * 		Se a placa é válida ou não. Uma placa é válida(de acordo com os padrões brasileiros)
	 * 		se é formada por 3 letras e 4 números respectivamente.
	 * 	 */
	public static boolean verificaPlaca(String placa) {
		if (placa.length() != LENGTH_PLACA)
			return false;
		
		String letras = placa.substring(0, 3);
		String numeros = placa.substring(3);
	  
		System.out.println(letras);
		System.out.println(numeros);
		for (int i = 0; i < letras.length(); i++) {
			if (Character.isLetter(letras.charAt(i)) == false) {
				return false;
			}
		}
		for (int i = 0; i < numeros.length(); i++) {
			if (Character.isDigit(numeros.charAt(i)) == false) {
				return false;
			}
		}
		
		return true;
	}
	
}
