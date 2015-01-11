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

public class Periodo implements Comparable<Periodo>{

	private Calendar inicio;
	private Calendar fim;
	
	/**
	 * Construtor do periodo.
	 * @param inicio
	 * 			Data de inicio do periodo.
	 * @param fim
	 * 			Data de fim do periodo.
	 */
	public Periodo(Calendar inicio, Calendar fim){
		
		if(inicio == null)
			throw new IllegalArgumentException();
		if(fim == null)
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
	public int getNumeroDias(){
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
	 * 
	 * @param outroPeriodo
	 * 			Outro periodo.
	 * @return
	 * 			Se entra em conflito.
	 */
	public boolean entraEmConflito(Periodo outroPeriodo){
		return ((getInicio().after(outroPeriodo.getInicio()) || getInicio().equals(outroPeriodo.getInicio())) &&
				(getInicio().before(outroPeriodo.getFim()) || getInicio().equals(outroPeriodo.getFim()))) ||
				((getFim().before(outroPeriodo.getFim()) || getFim().equals(outroPeriodo.getFim())) &&
				(getFim().after(outroPeriodo.getInicio())) || getFim().equals(outroPeriodo.getInicio()));
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
}
