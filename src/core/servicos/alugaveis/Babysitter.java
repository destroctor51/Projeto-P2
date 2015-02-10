package core.servicos.alugaveis;

import java.util.Calendar;
import java.util.Set;
import java.util.TreeSet;

import core.interfaces.Alugavel;
import core.tempo.Periodo;

/**
 *
 * Classe que representa uma baby-sitter cujo custo nao so depende da quantidade
 * de tempo empregado, mas eh relativo ao periodo em que foi requisitado.
 * Enquanto tipo de Alugavel, o uso de uma baby-sitter eh referido aos gastos
 * dos hospedes no Hotel.
 *
 * @author Jose Benardi de Souza Nunes
 */
public class Babysitter implements Alugavel {

	private static final long serialVersionUID = 1L;

	public static final float HORA_EXTRA = 50.0f;
	public static final float HORA_REGULAR = 25.0f;
	private final String descricao = "Servico de Babysitter";

	private String nome;
	private Periodo periodoAlugado;
	private Set<Periodo> historico = new TreeSet<>();

	/**
	 * Cria uma babysitter a partir de um nome.
	 *
	 * @param nome
	 *            o nome da babysitter
	 */
	public Babysitter(String nome) {
		if (nome == null)
			throw new IllegalArgumentException();
		this.nome = nome;
	}

	// metodos

	/**
	 * Recupera a descricao da babysitter.
	 *
	 * @return descricao
	 * 		A descricao do servico babysitter.
	 */
	@Override
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Recupera o nome da babysitter.
	 *
	 * @return nome
	 * 		O nome da babysitter.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Determina o custo da locacao do servico prestado pela babysitter a partir
	 * do periodo de trabalho.
	 *
	 * @return custo
	 * 		 O custo pelo servico da babysitter.
	 */
	@Override
	public float getPreco() {
		if(periodoAlugado == null)
			return 0;

		int hora_inicial = periodoAlugado.getInicio().get(Calendar.HOUR_OF_DAY);
		int hora_final = periodoAlugado.getFim().get(Calendar.HOUR_OF_DAY);
		float custo = 0;

		if (periodoAlugado.getInicio().get(Calendar.DAY_OF_YEAR) != periodoAlugado.getFim()
				.get(Calendar.DAY_OF_YEAR)) {
			for (int i = hora_inicial; i < 24; i++) {
				if (i < 7) {
					custo += HORA_EXTRA;
				} else if (i >= 18) {
					custo += HORA_EXTRA;
				} else {
					custo += HORA_REGULAR;
				}

			}

			for (int i = hora_final; i < 24; i++) {
				if (i < 7) {
					custo += HORA_EXTRA;
				} else if (i >= 18) {
					custo += HORA_EXTRA;
				} else {
					custo += HORA_REGULAR;
				}

			}
			custo += ((periodoAlugado.getNumeroDias() - 1) * 13 * HORA_EXTRA);
			custo += ((periodoAlugado.getNumeroDias() - 1) * 11 * HORA_REGULAR);
		} else if (periodoAlugado.getInicio().get(Calendar.DAY_OF_YEAR) == periodoAlugado
				.getFim().get(Calendar.DAY_OF_YEAR)
				&& periodoAlugado.getInicio().get(Calendar.YEAR) == periodoAlugado.getFim()
				.get(Calendar.YEAR)) {
			for (int i = hora_inicial; i < hora_final; i++) {
				if (i < 7) {
					custo += HORA_EXTRA;
				} else if (i >= 18) {
					custo += HORA_EXTRA;
				} else {
					custo += HORA_REGULAR;
				}

			}
		}
		return custo;

	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Babysitter))
			return false;
		Babysitter umaBaba = (Babysitter) obj;
		return umaBaba.getNome().equals(getNome());
	}

	@Override
	public Object clone() {
		try {
			Babysitter clone = (Babysitter) super.clone();
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

		if(!historico.add(periodo))
			return false;
		periodoAlugado = periodo;
		return true;
	}

	@Override
	public Set<Periodo> getHistorico() {
		return historico;
	}
}
