package core.tempo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Classe que representa um periodo de tempo, guardado a data de inicio e de fim de tal periodo,
 * alem de fornecer metodos para comparar periodos. <p>
 * Classe desenvolvida para o projeto da disciplina de Laboratorio de Programacao II na UFCG 2014.2.
 *
 * 31 de dezembro de 2014.
 *
 * @author Marianne Linhares Monteiro
 * @version 1.0
 */

public class Periodo implements Comparable<Periodo>, Cloneable, Serializable {

	private static final long serialVersionUID = 1L;

	private Calendar inicio;
	private Calendar fim;

	/**
	 * Cria um periodo usando as datas dadas.
	 * <p>
	 * A data de inicio deve vir antes da data de fim.
	 *
	 * @param inicio  data de inicio do periodo.
	 * @param fim  data de fim do periodo.
	 */
	public Periodo(Calendar inicio, Calendar fim) {
		if(inicio == null || fim == null || fim.before(inicio))
			throw new IllegalArgumentException();

		this.inicio = inicio;
		this.fim = fim;
	}

	/**
	 * @return data de inicio do periodo
	 */
	public Calendar getInicio() {
		return inicio;
	}

	/**
	 * Troca a data de inicio do periodo pela data recebida como parametro.
	 *
	 * @param inicio  a nova data de inicio, nao null
	 */
	public void setInicio(Calendar inicio) {
		if(inicio == null || inicio.after(fim))
			throw new IllegalArgumentException();
		this.inicio = inicio;
	}

	/**
	 * @return data de fim do perido.
	 */
	public Calendar getFim() {
		return fim;
	}

	/**
	 *Troca a data de fim do periodo pela data recebida como parametro.
	 *
	 * @param fim  a nova data de fim, nao null
	 */
	public void setFim(Calendar fim) {
		if(fim == null || fim.before(inicio))
			throw new IllegalArgumentException();
		this.fim = fim;
	}

	/**
	 * @return quantidade de dias do periodo
	 */
	public long getNumeroDias() {
		long ms = fim.getTimeInMillis() - inicio.getTimeInMillis();
		return ms / 86400000;
	}

	/**
	 * @return quantidade de horas do periodo
	 */
	public long getNumeroHoras() {
		long ms = fim.getTimeInMillis() - inicio.getTimeInMillis();
		return ms / 3600000;
	}

	/**
	 * Confere se ha uma intercessao entre dois periodos.
	 * <p>
	 * Caso um deles seja null, considera-se que nao ha intercessao.
	 *
	 * @param outroPeriodo  o periodo a ser checado
	 * @return true se os os periodos entrarem em conflito, false caso contrario
	 */
	public boolean entraEmConflito(Periodo outroPeriodo) {
		if(outroPeriodo == null)
			return false;

		return outroPeriodo.inicio.compareTo(fim) < 0 &&
				(outroPeriodo.fim.compareTo(inicio) > 0 || outroPeriodo.inicio.compareTo(inicio) >= 0);
	}

	/**
	 * Confere se o periodo engloba uma data especifica.
	 * <p>
	 * Caso a data seja null, considera-se que o periodo nao a engloba.
	 *
	 * @param data  data a ser checada
	 * @return true se o periodo contem a data, false caso contrario
	 */
	public boolean contem(Calendar data) {
		if(data == null) return false;
		return data.compareTo(inicio) >= 0 && data.compareTo(fim) < 0;
	}

	/**
	 * Confere se o periodo engloba outro.
	 * <p>
	 * Caso o outro periodo seja null, considera-se que este nao o engloba.
	 *
	 * @param periodo  o periodo a ser checado
	 * @return true se o periodo contem o outro, false caso contrario
	 */
	public boolean contem(Periodo periodo) {
		if(periodo == null) return false;
		return inicio.compareTo(periodo.inicio) <= 0 &&
				fim.compareTo(periodo.fim) >= 0;
	}

	/**
	 * Dois periodos sao considerados iguais se entrarem em conflito.
	 *
	 * @return se dois periodos forem iguais retorna true, caso contrario retorna false
	 */
	@Override
	public boolean equals(Object obj){

		if (!(obj instanceof Periodo))
			return false;

		Periodo outroPeriodo = (Periodo) obj;
		return entraEmConflito(outroPeriodo);
	}

	@Override
	public String toString() {
		String dataInicial = new SimpleDateFormat("d/M/yyyy").format(inicio.getTime());
		String dataFinal = new SimpleDateFormat("d/M/yyyy").format(fim.getTime());
		return dataInicial + " a " + dataFinal;
	}

	@Override
	public int compareTo(Periodo p) {
		if (equals(p))
			return 0;
		else if(inicio.before(p.inicio))
			return -1;
		else return 1;
	}

	@Override
	public Object clone() {
		try {
			Periodo clone = (Periodo) super.clone();
			clone.inicio = (Calendar) inicio.clone();
			clone.fim = (Calendar) fim.clone();
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException();
		}
	}
}
