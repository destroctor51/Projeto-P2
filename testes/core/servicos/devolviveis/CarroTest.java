package core.servicos.devolviveis;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import core.tempo.Periodo;

public class CarroTest {

	private Carro carro1;
	private Carro carro2;
	private Carro carro3;

	private Calendar c1;
	private Calendar c2;
	private Calendar c3;
	private Calendar c4;

	private Periodo p1;
	private Periodo p2;
	private Periodo p3;

	@Before
	public void before() {

		carro1 = new Carro(TipoCarro.LUXO, "ABC-123");
		carro2 = new Carro(TipoCarro.EXECUTIVO, "ABC-123");
		carro3 = new Carro(TipoCarro.LUXO, "ABCD-123");

		c1 = new GregorianCalendar(2014,1,28);
		c2 = new GregorianCalendar(2014,2,2);
		c3 = new GregorianCalendar(2014,2,28);
		c4 = new GregorianCalendar(2014,3,15);

		p1 = new Periodo(c1,c2);
		p2 = new Periodo(c2,c3);
		p3 = new Periodo(c3,c4);
	}

	@Test
	public void ConstrutorTest() {
		try {
			new Carro(null, "123");
			Assert.fail();
		} catch(IllegalArgumentException i) {}

		try {
			new Carro(TipoCarro.EXECUTIVO, null);
			Assert.fail();
		} catch(IllegalArgumentException i) {}

		Assert.assertEquals(TipoCarro.valueOf("LUXO").ordinal(), carro1.getTipo().ordinal());
	}

	@Test
	public void AlugarTest() {
		try {
			carro1.aluga(null);
			Assert.fail();
		} catch(IllegalArgumentException i) {}

		carro1.devolve(c1);
		carro1.aluga(p1, true, true);
		Assert.assertFalse(carro1.aluga(p1, true, true));

		Assert.assertTrue(carro1.getHistorico().contains(p1));
		Assert.assertEquals("ABC-123", carro1.getPlaca());
		Assert.assertEquals(false, carro1.isDevolvido());

		carro1.devolve(c1);
		Assert.assertFalse(carro1.aluga(p1, true, true));
	}

	@Test
	public void getPrecoTest() {

		Assert.assertTrue(carro1.aluga(p1, true, true));
		Assert.assertEquals(0, carro1.getPreco(), 0.001);
		Assert.assertTrue(carro1.devolve(c1));
		Assert.assertEquals(450, carro1.getPreco(), 0.001);

		Assert.assertFalse(carro1.cancela());
		Assert.assertTrue(carro1.aluga(p2, true, true));
		Assert.assertEquals(0, carro1.getPreco(), 0.001);
		Assert.assertTrue(carro1.cancela());
		Assert.assertEquals(0, carro1.getPreco(), 0.001);

		Assert.assertTrue((carro2.aluga(p2, true, false)));
		Assert.assertEquals(0, carro2.getPreco(), 0.001);
		Assert.assertTrue(carro2.devolve(c1));
		Assert.assertEquals(1710, carro2.getPreco(), 0.001);
	}

	@Test
	public void verificaPlaca() {

		Assert.assertFalse(Carro.verificaPlaca("123"));
		Assert.assertFalse(Carro.verificaPlaca("ABC"));
		Assert.assertFalse(Carro.verificaPlaca("ABC-123"));
		Assert.assertFalse(Carro.verificaPlaca("ABCD-4123"));
		Assert.assertFalse(Carro.verificaPlaca("A2C-4123"));
		Assert.assertFalse(Carro.verificaPlaca("ABC-41D3"));
		Assert.assertFalse(Carro.verificaPlaca("abc-4123"));
		
		Assert.assertTrue(Carro.verificaPlaca("ABC-4123"));
	}

	@Test
	public void toStringTest() {
		Assert.assertEquals("Carro de luxo de placa ABC-123", carro1.toString());
		Assert.assertEquals("Carro executivo de placa ABC-123", carro2.toString());
		Assert.assertEquals("Carro de luxo de placa ABCD-123", carro3.toString());
	}

	@Test
	public void getDescricaoTest() {
		Calendar devolucao = null;
		Assert.assertEquals( "Carro de luxo alugado por 0 dias", carro1.getDescricao());
		Assert.assertEquals( "Carro executivo alugado por 0 dias", carro2.getDescricao());

		Assert.assertTrue(carro1.aluga(p1, true, false));
		Assert.assertEquals( "Carro de luxo, com tanque cheio, alugado por 2 dias", carro1.getDescricao());

		Assert.assertTrue(carro2.aluga(p2, false, true));
		devolucao = (Calendar) c3.clone();
		devolucao.add(Calendar.HOUR_OF_DAY, -10+3);
		Assert.assertTrue(carro2.devolve(devolucao));
		Assert.assertEquals( "Carro executivo, com seguro, alugado por 26 dias e com multa de R$15.0", carro2.getDescricao());

		Assert.assertTrue(carro3.aluga(p3, true, true));
		devolucao = (Calendar) c4.clone();
		devolucao.add(Calendar.HOUR_OF_DAY, -10+6);
		Assert.assertTrue(carro3.devolve(devolucao));
		Assert.assertEquals( "Carro de luxo, com tanque cheio e seguro, alugado por 18 dias e com multa de R$50.0", carro3.getDescricao());
	}


	@Test
	public void equalsTest() {
		Assert.assertFalse(carro1.equals(null));
		Assert.assertTrue(carro1.equals(carro2));
		Assert.assertFalse(carro3.equals(carro2));
		Assert.assertFalse(carro1.equals(carro3));
	}

	@Test
	public void testClone() {
		Assert.assertEquals(0, ((Carro) carro1.clone()).getPreco(), 0.01);

		Assert.assertTrue(carro1.aluga(p1));
		carro1.devolve(c1);
		Assert.assertTrue(carro1.aluga(p3));

		carro2 = (Carro) carro1.clone();
		Assert.assertEquals(carro1, carro2);
		Assert.assertEquals(carro1.getPreco(), carro2.getPreco(), 0.001);

		Assert.assertTrue(carro1.getHistorico().contains(p1));
		Assert.assertTrue(carro1.getHistorico().contains(p3));
		Assert.assertFalse(carro2.getHistorico().contains(p1));
		Assert.assertTrue(carro2.getHistorico().contains(p3));
	}
}
