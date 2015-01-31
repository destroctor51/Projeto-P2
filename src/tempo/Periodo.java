package tempo;

import java.util.Calendar;
import java.util.GregorianCalendar;

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

public class Periodo implements Comparable<Periodo>, Cloneable {

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
	public Periodo(Calendar inicio, Calendar fim){

		if(inicio == null || fim == null || fim.before(inicio))
			throw new IllegalArgumentException();

		this.inicio = inicio;
		this.fim = fim;
	}

	/**
	 *
	 * @return
	 * 		Data de inicio do periodo.
	 */
	public Calendar getInicio() {
		return inicio;
	}

	/**
	 *
	 * @param inicio
	 * 		Troca a data de inicio do periodo pela data recebida como parametro.
	 */
	public void setInicio(Calendar inicio) {
		this.inicio = inicio;
	}

	/**
	 *
	 * @return
	 * 		Data de fim do perido.
	 */
	public Calendar getFim() {
		return fim;
	}

	/**
	 *
	 * @param fim
	 * 		Troca a data de fim do periodo pela data recebida como parametro.
	 */
	public void setFim(Calendar fim) {
		this.fim = fim;
	}

	/**
	 *
	 * @return
	 * 		Quantidade de dias do periodo.
	 */
	public int getNumeroDias() {
		int diasNaoContados = 0;
		int anoInicial = inicio.get(Calendar.YEAR);
		int anoFinal = fim.get(Calendar.YEAR);
		while (anoInicial != anoFinal) {
			if (new GregorianCalendar().isLeapYear(anoInicial))
				diasNaoContados += 366;
			else
				diasNaoContados += 365;
			anoInicial += 1;
		}

		return (diasNaoContados + fim.get(Calendar.DAY_OF_YEAR)) - (inicio.get(Calendar.DAY_OF_YEAR));
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

		return outroPeriodo.inicio.compareTo(fim) <= 0 &&
				(outroPeriodo.fim.compareTo(inicio) >= 0 || outroPeriodo.inicio.compareTo(inicio) >= 0);
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
		return data.compareTo(inicio) >= 0 && data.compareTo(fim) <= 0;
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

	@Override
	/**
	 * @return
	 * 		Se dois periodos forem iguais retorna true, caso contrario retorna false. Dois periodos sao considerados iguais
	 * 		se entrarem em conflito.
	 */
	public boolean equals(Object obj){

		if (!(obj instanceof Periodo))
			return false;

		Periodo outroPeriodo = (Periodo) obj;
		return entraEmConflito(outroPeriodo);
	}

	@Override
	public int compareTo(Periodo p) {
		if (this.equals(p))
			return 0;
		else if (this.getFim().before(p.getInicio()))
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
