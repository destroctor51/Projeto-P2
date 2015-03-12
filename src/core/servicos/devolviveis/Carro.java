package core.servicos.devolviveis;

import java.util.Calendar;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

import core.interfaces.Devolvivel;
import core.tempo.Periodo;

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

	private static final long serialVersionUID = 1L;

	/**
	 * Valor pago se o servico tanque cheio for solicitado.
	 */
	public final static float VALOR_TANQUE = 150.0f;

	/**
	 * Valor pago se o seguro do carro for solicitado.
	 */
	public final static float VALOR_SEGURO = 100.0f;

	private TipoCarro tipoCarro;
	private String placa;
	private boolean tanqueCheio;
	private boolean seguro;

	private float preco = 0;
	private float multa = 0;
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
		if(tipoCarro == null || placa == null || placa.equals(""))
			throw new IllegalArgumentException();

		this.tipoCarro = tipoCarro;
		this.placa = placa;
	}

	@Override
	public String getDescricao() {
		String opcionais = "";
		if(tanqueCheio || seguro) {
			opcionais = ", com " + (tanqueCheio? "tanque cheio": "seguro");
			opcionais += tanqueCheio && seguro? " e seguro," : ",";
		}
		String multa = this.multa > 0? " e com multa de R$"+this.multa : "";
		return tipoCarro+opcionais+" alugado por "+getNumeroDias()+" dias"+multa;
	}

	@Override
	public float getPreco() {
		return preco+multa;
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
	 * @return numero de dias alugados
	 */
	private long getNumeroDias() {
		if(periodoAlugado == null) return 0;
		return periodoAlugado.getNumeroDias();
	}

	private float getMulta(Calendar data) {
		Calendar limite = (Calendar) periodoAlugado.getFim().clone();
		limite.add(Calendar.HOUR_OF_DAY, -10);

		if(limite.after(data)) return 0;
		return new Periodo(limite, data).getNumeroHoras() * tipoCarro.getDiaria() / 12;
	}

	@Override
	public boolean devolve(Calendar data) {
		if (devolvido == true) return false;
		multa = getMulta(data);
		preco = getNumeroDias() * tipoCarro.getDiaria();
		preco += tanqueCheio ? Carro.VALOR_TANQUE : 0;
		preco += seguro ? Carro.VALOR_SEGURO : 0;
		return devolvido = true;
	}

	@Override
	public boolean cancela() {
		if (devolvido == true)
			return false;
		devolvido = true;
		preco = 0; multa = 0;
		return true;
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
	public String toString() {
		return tipoCarro + " de placa " + placa;
	}

	/**
	 *  Dois carros sao iguais se tiverem mesma placa.
	 *
	 * @return
	 * 		se dois carros forem iguais retorna true, caso contrario retorna false
	 */
	@Override
	public boolean equals(Object obj) {
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
		return Pattern.matches("^[A-Z]{3}-[0-9]{4}$", placa);
	}

	@Override
	public Object clone() {
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
		multa = 0;

		return true;
	}

	@Override
	public Set<Periodo> getHistorico() {
		return historico;
	}
}
