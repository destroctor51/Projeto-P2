package servicos.alugaveis;

import interfaces.Alugavel;

import java.util.Calendar;

import tempo.Periodo;

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
	private String descricao = "Servico de Babysitter";
	private String nome;
	private Periodo aluguel;
	public static final float HORA_EXTRA = 50.0f;
	public static final float HORA_REGULAR = 25.0f;

	/**
	 * Cria uma babysitter a partir de um nome.
	 * 
	 * @param nome
	 *            O nome da babysitter.
	 * @param horasNormais
	 *            As horas que trabalharou em expediente regular.
	 * @param horasDobradas
	 *            As horas que trabalharou em expediente extra regular.
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
	 * Seta o periodo de trabalho ah ser realizado pela babysitter.
	 * 
	 * @param periodo
	 *            Tempo de trabalho.
	 */
	public void setPeriodo(Periodo periodo) {
		aluguel = periodo;
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
		int hora_inicial = aluguel.getInicio().get(Calendar.HOUR_OF_DAY);
		int hora_final = aluguel.getFim().get(Calendar.HOUR_OF_DAY);
		float custo = 0;

		if (aluguel.getInicio().get(Calendar.DAY_OF_YEAR) != aluguel.getFim()
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
			custo += ((aluguel.getNumeroDias() - 1) * 13 * HORA_EXTRA);
			custo += ((aluguel.getNumeroDias() - 1) * 11 * HORA_REGULAR);
		} else if (aluguel.getInicio().get(Calendar.DAY_OF_YEAR) == aluguel
				.getFim().get(Calendar.DAY_OF_YEAR)
				&& aluguel.getInicio().get(Calendar.YEAR) == aluguel.getFim()
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
		return "Babysitter [descricao=" + descricao + ", nome=" + nome + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Babysitter)) {
			return false;
		}
		Babysitter umaBaba = (Babysitter) obj;

		return umaBaba.getNome() == getNome();
	}
	/**
	 * Retorna o periodo mais recente de locacao do servico da babysitter.
	 * 
	 * @return aluguel
	 * 		O periodo de aluguel do servico da babysitter.
	 */
	@Override
	public Periodo getPeriodo() {
		return aluguel;
	}

}
