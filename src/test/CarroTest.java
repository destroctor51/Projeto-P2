package test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import servicos.devolviveis.Carro;
import servicos.devolviveis.TipoCarro;
import tempo.Periodo;

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
		Assert.assertEquals(0, carro1.getNumeroDias());
	}

	@Test
	public void AlugarTest() {
		try {
			carro1.aluga(null);
			Assert.fail();
		} catch(IllegalArgumentException i) {}

		carro1.devolve();
		carro1.aluga(p1, true, true);
		Assert.assertFalse(carro1.aluga(p1, true, true));

		Assert.assertTrue(carro1.getHistorico().contains(p1));
		Assert.assertEquals("ABC-123", carro1.getPlaca());
		Assert.assertEquals(false, carro1.isDevolvido());

		carro1.devolve();
		Assert.assertFalse(carro1.aluga(p1, true, true));
	}

	@Test
	public void getPrecoTest() {

		carro1.aluga(p1, true, true);
		Assert.assertEquals(0, carro1.getPreco(), 0.001);
		carro1.devolve();
		Assert.assertEquals(450, carro1.getPreco(), 0.001);

		carro2.aluga(p2, true, false);
		Assert.assertEquals(0, carro2.getPreco(), 0.001);
		carro2.devolve();
		Assert.assertEquals(1710, carro2.getPreco(), 0.001);
	}

	@Test
	public void verificaPlaca() {

		Assert.assertFalse(Carro.verificaPlaca("123"));
		Assert.assertFalse(Carro.verificaPlaca("ABC"));
		Assert.assertFalse(Carro.verificaPlaca("ABC123"));
		Assert.assertFalse(Carro.verificaPlaca("ABCD4123"));
		Assert.assertFalse(Carro.verificaPlaca("A2C4123"));
		Assert.assertFalse(Carro.verificaPlaca("ABC41D3"));
		Assert.assertFalse(Carro.verificaPlaca("abc4123"));
		Assert.assertTrue(Carro.verificaPlaca("ABC4123"));
	}

	@Test
	public void toStringTest() {

		Assert.assertTrue(carro1.aluga(p1, true, true));
		Assert.assertEquals(0, carro1.getPreco(), 0.001);
		carro1.devolve();

		Assert.assertEquals( "Carro Luxo. " +
				"Placa do carro: " + "ABC-123" + ". " +
				"Valor da diaria: " + 100.0 + ". " +
				"Servico tanque cheio: incluso. " +
				"Seguro: incluso. " +
				"Valor total: 450.0.", carro1.toString());

		Assert.assertTrue(carro2.aluga(p1, true, false));
		Assert.assertEquals(0, carro2.getPreco(), 0.001);
		carro2.devolve();

		Assert.assertEquals( "Carro Executivo. " +
				"Placa do carro: " + "ABC-123" + ". " +
				"Valor da diaria: " + 60.0 + ". " +
				"Servico tanque cheio: incluso. " +
				"Seguro: nao incluso. " +
				"Valor total: 270.0.", carro2.toString());

		Assert.assertTrue(carro3.aluga(p2, false, true));
		Assert.assertEquals(0, carro3.getPreco(), 0.001);
		carro3.devolve();

		Assert.assertEquals( "Carro Luxo. " +
				"Placa do carro: " + "ABCD-123" + ". " +
				"Valor da diaria: " + 100.0 + ". " +
				"Servico tanque cheio: nao incluso. " +
				"Seguro: incluso. " +
				"Valor total: 2700.0.", carro3.toString());
	}

	@Test
	public void getDescricaoTest() {

		Assert.assertEquals( "Carro Luxo", carro1.getDescricao());
		Assert.assertEquals( "Carro Executivo", carro2.getDescricao());
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
		carro1.devolve();
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
