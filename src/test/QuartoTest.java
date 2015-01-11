package test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tempo.Periodo;
import alugaveis.Quarto;
import alugaveis.TipoQuarto;
import classesTemporarias.Hospede;

public class QuartoTest {

	private Quarto quarto1;
	private Quarto quarto2;
	private Quarto quarto3;
	private Quarto quarto4;
	private Quarto quarto5;
	private Quarto quarto6;
	private Quarto quarto7;
	
	private Calendar c1;
	private Calendar c2;
	
	private Periodo p1;	
	
	private Hospede h1;
	private Hospede h2;
	
	@Before
	public void before(){
		
		quarto1 = new Quarto(TipoQuarto.PRESIDENCIAL,100);
		
		quarto2 = new Quarto(TipoQuarto.LUXO_SIMPLES,101);
		quarto3 = new Quarto(TipoQuarto.LUXO_DUPLO,102);
		quarto4 = new Quarto(TipoQuarto.LUXO_TRIPLO,103);
		
		quarto5 = new Quarto(TipoQuarto.EXECUTIVO_SIMPLES,104);
		quarto6 = new Quarto(TipoQuarto.EXECUTIVO_DUPLO,105);
		quarto7 = new Quarto(TipoQuarto.EXECUTIVO_TRIPLO,106);
		
		c1 = new GregorianCalendar(2014,1,28);
		c2 = new GregorianCalendar(2014,1,30);
		new GregorianCalendar(2014,2,28);
		
		p1 = new Periodo(c1,c2);
		h1 = new Hospede();
		h2 = new Hospede();
	}
	
	@Test
	public void ConstrutorTest() {
		try {
			new Quarto(null,123);
		} catch(IllegalArgumentException i){}
	}
	
	@Test
	public void AlugarTest() {
		try {
			quarto1.alugar(null,p1);
		} catch(IllegalArgumentException i) {}
	
		try {
			quarto1.alugar(h1,null);
		} catch(IllegalArgumentException i) {}
		
		quarto1.alugar(h1,p1);
		
		Assert.assertEquals(p1, quarto1.getPeriodo());
		Assert.assertEquals(100, quarto1.getNumero()); //testando gets
		Assert.assertEquals(false, quarto1.isDevolvido());
		Assert.assertEquals(h1, quarto1.getResponsavel());
		
		quarto1.setResponsavel(h2);  //testando setResponsavel
		Assert.assertEquals(h2, quarto1.getResponsavel());
	}
	
	@Test
	public void getPrecoTest() {
		
		quarto1.alugar(h1,p1);
		Assert.assertEquals(0, quarto1.getPreco(),0.001);
		quarto1.devolve();
		Assert.assertEquals(2400.0, quarto1.getPreco(),0.001);
		
		quarto2.alugar(h1,p1);
		Assert.assertEquals(0, quarto2.getPreco(),0.001);
		quarto2.devolve();
		Assert.assertEquals(1040.0, quarto2.getPreco(),0.001);
		
		quarto3.alugar(h1,p1);
		Assert.assertEquals(0, quarto3.getPreco(),0.001);
		quarto3.devolve();
		Assert.assertEquals(1140.0, quarto3.getPreco(),0.001);
		
		quarto4.alugar(h1,p1);
		Assert.assertEquals(0, quarto4.getPreco(),0.001);
		quarto4.devolve();
		Assert.assertEquals(1240.0, quarto4.getPreco(),0.001);
		
		quarto5.alugar(h1,p1);
		Assert.assertEquals(0, quarto5.getPreco(),0.001);
		quarto5.devolve();
		Assert.assertEquals(720.0, quarto5.getPreco(),0.001);
		
		quarto6.alugar(h1,p1);
		Assert.assertEquals(0, quarto6.getPreco(),0.001);
		quarto6.devolve();
		Assert.assertEquals(770.0, quarto6.getPreco(),0.001);
		
		quarto7.alugar(h1,p1);
		Assert.assertEquals(0, quarto7.getPreco(),0.001);
		quarto7.devolve();
		Assert.assertEquals(880.0, quarto7.getPreco(),0.001);
	}
	
	@Test
	public void getDescricaoTest() {
		
		quarto1.alugar(h1,p1);
		Assert.assertEquals(0, quarto1.getPreco(),0.001);
		quarto1.devolve();
		
		Assert.assertEquals( "Numero do quarto: " + 100 + ". " + 
					"Quarto Presidencial\nQuarto equipado com TV LCD 42, split, frigobar, cofre,sala de jogos e home theater, ideal para familias em ferias.\n" +
				   "Quarto para " + 4 + " pessoas. " + 
				   "Valor da diaria: " + 1200.0 + ". " +
				   "Valor total: 2400.0.", quarto1.getDescricao());
		
		quarto2.alugar(h1,p1);
		Assert.assertEquals(0, quarto2.getPreco(),0.001);
		quarto2.devolve();
		
		Assert.assertEquals("Numero do quarto: " + 101 + ". " + 
				"Quarto Luxo Simples\nQuarto equipado com TV LCD 42, split, frigobar, cofre e home theater.\n" +
				"Quarto para " + 1 + " pessoas. " +
			   "Valor da diaria: " + 520.0 + ". " +
			   "Valor total: " + 1040.0 + ".", quarto2.getDescricao());	
	}
	
	@Test
	public void equalsTest() {
		
		Assert.assertFalse(quarto1.equals(quarto2));
		Assert.assertFalse(quarto3.equals(quarto2));
		Assert.assertFalse(quarto1.equals(quarto3));
		
		Quarto quartoNovo = new Quarto(TipoQuarto.EXECUTIVO_SIMPLES,100);
		Assert.assertTrue(quarto1.equals(quartoNovo));
	}	
}
