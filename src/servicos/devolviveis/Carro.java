package servicos.devolviveis;

import interfaces.Devolvivel;

import java.util.Set;
import java.util.TreeSet;

import tempo.Periodo;

/**
 * Classe que representa um Carro. Carro se comporta como devolvivel,tem uma placa que o designa
 * e pode estar vinculado a um Hospede e consequentemente estando com seu retorno pendente.<p>
 * Classe desenvolvida para o projeto da disciplina de Laboratorio de Programacao II na UFCG 2014.2.
 *
 * 31 de dezembro de 2014.
 *
 * @author Marianne Linhares Monteiro
 * @version 1.0
 */
public class Carro implements Devolvivel{

	/**
	 * Valor pago se o servico tanque cheio for solicitado.
	 */
	public final static double VALOR_TANQUE = 150.0;

	/**
	 * Valor pago se o seguro do carro for solicitado.
	 */
	public final static double VALOR_SEGURO = 100.0;

	/**
	 * Valor padrao pago se um servico nao for solicitado.
	 */
	public final static double SEM_SERVICO = 0.0;

	private final static int LENGTH_PLACA = 7;

	private TipoCarro tipoCarro;
	private String placa;
	private boolean tanqueCheio;
	private boolean seguro;

	private float preco = 0;
	private boolean devolvido = true;
	private Periodo periodoAlugado;
	private Set<Periodo> historico = new TreeSet<>();

	/**
	 * Construtor do Carro.
	 * @param tipoCarro
	 * 			Tipo do carro(Executivo ou Luxo).
	 * @param placa
	 * 			Placa do carro.
	 */
	public Carro(TipoCarro tipoCarro, String placa) {
		if(tipoCarro == null || placa == null)
			throw new IllegalArgumentException();

		this.tipoCarro = tipoCarro;
		this.placa = placa;

		devolvido = true;
		preco = 0;
	}

	@Override
	public String getDescricao() {
		String opcionais = "";
		if(tanqueCheio || seguro) {
			opcionais = " com " + (tanqueCheio? "tanque cheio": "seguro");
			if(tanqueCheio && seguro) opcionais += " e seguro";
		}
		return tipoCarro+opcionais+" alugado por "+getNumeroDias()+" dias";
	}

	@Override
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
	 * @return um objeto que representa o tipo deste carro
	 */
	public TipoCarro getTipo() {
		return tipoCarro;
	}

	/**
	 *
	 * @return
	 * 			Numero de dias alugados.
	 */
	public int getNumeroDias() {
		if(periodoAlugado == null) return 0;
		return periodoAlugado.getNumeroDias();
	}

	@Override
	public void devolve() {
		if (devolvido == false) {
			preco = getNumeroDias() * tipoCarro.getDiaria();
			preco += tanqueCheio ? Carro.VALOR_TANQUE : Carro.SEM_SERVICO;
			preco += seguro ? Carro.VALOR_SEGURO : Carro.SEM_SERVICO;
			devolvido = true;
		}
	}

	@Override
	public boolean isDevolvido() {
		return devolvido;
	}

	/**
	 *@return
	 *		Uma representacao textual do objeto.
	 */
	@Override
	public String toString(){
		return tipoCarro + " de placa " + placa;
	}

	/**
	 *  Dois carros sao iguais se tiverem mesma placa.
	 *
	 * @return
	 * 		se dois carros forem iguais retorna true, caso contrario retorna false
	 */
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Carro))
			return false;

		Carro outroCarro = (Carro) obj;
		return getPlaca().equals(outroCarro.getPlaca());
	}

	/**
	 * Uma placa e valida (de acordo com os padroes brasileiros)
	 * se e formada por 3 letras e 4 numeros respectivamente.
	 *
	 * @param placa  a placa a ser conferida
	 * @return true se a placa e valida ou false se nao e
	 *
	 */
	public static boolean verificaPlaca(String placa) {
		if (placa.length() != LENGTH_PLACA)
			return false;

		String letras = placa.substring(0, 3);
		String numeros = placa.substring(3);

		for (int i = 0; i < letras.length(); i++)
			if (!Character.isLetter(letras.charAt(i))||
					!Character.isUpperCase(letras.charAt(i)))
				return false;
		for (int i = 0; i < numeros.length(); i++)
			if (!Character.isDigit(numeros.charAt(i)))
				return false;

		return true;
	}

	@Override
	public Object clone()  {
		try {
			Carro clone = (Carro) super.clone();
			clone.historico = new TreeSet<Periodo>();
			if(periodoAlugado != null) {
				clone.periodoAlugado = (Periodo) periodoAlugado.clone();
				clone.historico.add(clone.periodoAlugado);
			} return clone;
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException();
		}
	}

	/**
	 * Marca o carro como alugado durante o periodo definido e define se os opcionais estao incluidos.
	 * <p>
	 * Ele so sera alugado se o periodo nao entrar em conflito com outro aluguel.
	 *
	 * @param periodo  o periodo em que se quer alugar o objeto
	 * @param tanqueCheio  se e requisitado que o tanque seja preenchido
	 * @param seguro  se o cliente deseja que o carro tenha seguro
	 * @return true se for alugado com sucesso, false caso contrario
	 */
	public boolean aluga(Periodo periodo, boolean tanqueCheio, boolean seguro) {
		if(!aluga(periodo))
			return false;

		this.tanqueCheio = tanqueCheio;
		this.seguro = seguro;
		return true;
	}

	@Override
	public boolean aluga(Periodo periodo) {
		if(periodo == null)
			throw new IllegalArgumentException();

		if(!devolvido || !historico.add(periodo))
			return false;

		periodoAlugado = periodo;
		devolvido = false;

		tanqueCheio = false;
		seguro = false;
		preco = 0;

		return true;
	}

	@Override
	public Set<Periodo> getHistorico() {
		return historico;
	}
}
