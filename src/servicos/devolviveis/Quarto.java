package servicos.devolviveis;

import interfaces.Devolvivel;

import java.util.Set;
import java.util.TreeSet;

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
public class Quarto implements Devolvivel {

	private TipoQuarto tipoQuarto;
	private int numero;

	private float preco = 0;
	private boolean devolvido = true;
	private Periodo periodoAlugado;
	private Set<Periodo> historico = new TreeSet<>();

	/**
	 * Construtor do Quarto.
	 * @param tipoQuarto
	 * 			tipo do quarto
	 * @param numero
	 * 			numero do quarto
	 */
	public Quarto(TipoQuarto tipoQuarto, int numero){

		if(tipoQuarto == null)
			throw new IllegalArgumentException();

		this.tipoQuarto = tipoQuarto;
		this.numero = numero;
	}

	@Override
	public String getDescricao() {
		return tipoQuarto + " alugado por "+getDiasAlugados()+" dias";
	}

	@Override
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
	 * @return um objeto que representa o tipo deste quarto
	 */
	public TipoQuarto getTipo() {
		return tipoQuarto;
	}

	/**
	 *
	 * @return
	 * 			Numero de dias alugados.
	 */
	public int getDiasAlugados() {
		if(periodoAlugado == null) return 0;
		return periodoAlugado.getNumeroDias();
	}

	@Override
	public void devolve() {
		if (devolvido == false) {
			preco = getDiasAlugados() * tipoQuarto.getDiaria();
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
		return tipoQuarto + " de numero " + numero;
	}

	/**
	 * Dois quartos sao iguais quando possuem o mesmo numero.
	 *
	 * @return
	 * 		Se dois quartos forem iguais retorna true, caso contrario retorna false
	 */
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Quarto))
			return false;

		Quarto outroQuarto = (Quarto) obj;
		return getNumero() == outroQuarto.getNumero();
	}

	@Override
	public Object clone() {
		try {
			Quarto clone = (Quarto) super.clone();
			clone.historico = new TreeSet<Periodo>();
			if(periodoAlugado != null) {
				clone.periodoAlugado = (Periodo) periodoAlugado.clone();
				clone.historico.add(clone.periodoAlugado);
			} return clone;
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public boolean aluga(Periodo periodo) {
		if(periodo == null)
			throw new IllegalArgumentException();

		if(!devolvido || !historico.add(periodo))
			return false;
		periodoAlugado = periodo;
		devolvido = false;
		preco = 0;
		return true;
	}

	@Override
	public Set<Periodo> getHistorico() {
		return historico;
	}
}
