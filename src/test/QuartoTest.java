package test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import servicos.devolviveis.Quarto;
import servicos.devolviveis.TipoQuarto;
import tempo.Periodo;

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
	private Calendar c3;
	private Calendar c4;
	private Calendar c5;
	private Calendar c6;

	private Periodo p1;
	private Periodo p2;
	private Periodo p3;

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

		p1 = new Periodo(c1,c2);
	}

	@Test
	public void ConstrutorTest() {
		try {
			new Quarto(null,123);
			Assert.fail();
		} catch(IllegalArgumentException i){}

		Assert.assertEquals(TipoQuarto.valueOf("PRESIDENCIAL"), quarto1.getTipo());
		Assert.assertEquals(0, quarto1.getDiasAlugados());
	}

	@Test
	public void alugaTest() {
		try {
			quarto1.aluga(null);
			Assert.fail();
		} catch(IllegalArgumentException i) {}

		quarto1.devolve();
		Assert.assertTrue(quarto1.aluga(p1));
		Assert.assertFalse(quarto1.aluga(p1));

		Assert.assertTrue(quarto1.getHistorico().contains(p1));
		Assert.assertEquals(100, quarto1.getNumero());
		Assert.assertEquals(false, quarto1.isDevolvido());

		quarto1.devolve();
		Assert.assertFalse(quarto1.aluga(p1));
	}

	@Test
	public void getPrecoTest() {

		Assert.assertTrue(quarto1.aluga(p1));
		Assert.assertEquals(0, quarto1.getPreco(),0.001);
		quarto1.devolve();
		Assert.assertEquals(2400.0, quarto1.getPreco(),0.001);

		Assert.assertTrue(quarto2.aluga(p1));
		Assert.assertEquals(0, quarto2.getPreco(),0.001);
		quarto2.devolve();
		Assert.assertEquals(1040.0, quarto2.getPreco(),0.001);

		Assert.assertTrue(quarto3.aluga(p1));
		Assert.assertEquals(0, quarto3.getPreco(),0.001);
		quarto3.devolve();
		Assert.assertEquals(1140.0, quarto3.getPreco(),0.001);

		Assert.assertTrue(quarto4.aluga(p1));
		Assert.assertEquals(0, quarto4.getPreco(),0.001);
		quarto4.devolve();
		Assert.assertEquals(1240.0, quarto4.getPreco(),0.001);

		Assert.assertTrue(quarto5.aluga(p1));
		Assert.assertEquals(0, quarto5.getPreco(),0.001);
		quarto5.devolve();
		Assert.assertEquals(720.0, quarto5.getPreco(),0.001);

		Assert.assertTrue(quarto6.aluga(p1));
		Assert.assertEquals(0, quarto6.getPreco(),0.001);
		quarto6.devolve();
		Assert.assertEquals(770.0, quarto6.getPreco(),0.001);

		Assert.assertTrue(quarto7.aluga(p1));
		Assert.assertEquals(0, quarto7.getPreco(),0.001);
		quarto7.devolve();
		Assert.assertEquals(880.0, quarto7.getPreco(),0.001);
	}

	@Test
	public void toStringTest() {

		Assert.assertTrue(quarto1.aluga(p1));
		Assert.assertEquals(0, quarto1.getPreco(),0.001);
		quarto1.devolve();

		Assert.assertEquals( "Numero do quarto: " + 100 + ". " +
				"Quarto Presidencial\nQuarto equipado com TV LCD 42, split, frigobar, cofre,sala de jogos e home theater, ideal para familias em ferias.\n" +
				"Quarto para " + 4 + " pessoas. " +
				"Valor da diaria: " + 1200.0 + ". " +
				"Valor total: 2400.0.", quarto1.toString());

		Assert.assertTrue(quarto2.aluga(p1));
		Assert.assertEquals(0, quarto2.getPreco(),0.001);
		quarto2.devolve();

		Assert.assertEquals("Numero do quarto: " + 101 + ". " +
				"Quarto Luxo Simples\nQuarto equipado com TV LCD 42, split, frigobar, cofre e home theater.\n" +
				"Quarto para " + 1 + " pessoas. " +
				"Valor da diaria: " + 520.0 + ". " +
				"Valor total: " + 1040.0 + ".", quarto2.toString());
	}

	@Test
	public void getDescricaoTest() {

		Assert.assertEquals("Quarto Presidencial\nQuarto equipado com TV LCD 42, split, frigobar, cofre,sala de jogos e home theater, ideal para familias em ferias.\n" , quarto1.getDescricao());
		Assert.assertEquals("Quarto Luxo Simples\nQuarto equipado com TV LCD 42, split, frigobar, cofre e home theater.\n", quarto2.getDescricao());
	}

	@Test
	public void equalsTest() {

		Assert.assertFalse(quarto1.equals(null));
		Assert.assertFalse(quarto1.equals(quarto2));
		Assert.assertFalse(quarto3.equals(quarto2));
		Assert.assertFalse(quarto1.equals(quarto3));

		Quarto quartoNovo = new Quarto(TipoQuarto.EXECUTIVO_SIMPLES,100);
		Assert.assertTrue(quarto1.equals(quartoNovo));
	}

	@Test
	public void testClone() {
		c3 = new GregorianCalendar(2014,2,15);
		c4 = new GregorianCalendar(2014,3,17);
		c5 = new GregorianCalendar(2014,5,15);
		c6 = new GregorianCalendar(2014,6,17);
		p2 = new Periodo(c3,c4);
		p3 = new Periodo(c5,c6);

		Assert.assertEquals(0, ((Quarto) quarto1.clone()).getPreco(), 0.01);

		Assert.assertTrue(quarto1.aluga(p1));
		quarto1.devolve();
		Assert.assertTrue(quarto1.aluga(p2));

		quarto2 = (Quarto) quarto1.clone();
		Assert.assertEquals(quarto1, quarto2);
		Assert.assertEquals(quarto1.getPreco(), quarto2.getPreco(), 0.001);

		Assert.assertTrue(quarto1.getHistorico().contains(p1));
		Assert.assertTrue(quarto1.getHistorico().contains(p2));
		Assert.assertFalse(quarto2.getHistorico().contains(p1));
		Assert.assertTrue(quarto2.getHistorico().contains(p2));

		quarto1.devolve();
		Assert.assertTrue(quarto1.aluga(p3));
		quarto1.devolve();
		Assert.assertNotEquals(quarto1.getPreco(), quarto2.getPreco(), 0.001);
		Assert.assertTrue(quarto1.getHistorico().contains(p3));
		Assert.assertFalse(quarto2.getHistorico().contains(p3));
	}
}
