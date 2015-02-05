package servicos.alugaveis;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import servicos.alugaveis.CamaExtra;
import tempo.Periodo;

public class CamaExtraTest {
	private CamaExtra camaextra1;
	private CamaExtra camaextra2;
	private CamaExtra camaextra3;
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
	public void setUp() {
		camaextra1 = new CamaExtra(10);
		camaextra2 = new CamaExtra(18);
		camaextra3 = new CamaExtra(18);
		c1 = new GregorianCalendar(2014, 1, 28);
		c2 = new GregorianCalendar(2014, 3, 12);
		c3 = new GregorianCalendar(2016, 1, 30);
		c4 = new GregorianCalendar(2016, 7, 1);
		c5 = new GregorianCalendar(2016, 8, 15);
		c6 = new GregorianCalendar(2016, 8, 18);
		p1 = new Periodo(c1, c2);
		p2 = new Periodo(c3, c4);
		p3 = new Periodo(c5, c6);
	}

	@Test
	public void testAluga() {
		try {
			camaextra1.aluga(null);
			Assert.fail();
		} catch(IllegalArgumentException e) {}

		Assert.assertTrue(camaextra1.aluga(p1));
		Assert.assertFalse(camaextra1.aluga(p1));
	}

	@Test
	public void testGets() {
		Assert.assertEquals(camaextra1.getPreco(), 0, 0.0005);
		Assert.assertEquals(camaextra2.getPreco(), 0, 0.0005);

		camaextra1.aluga(p1);
		camaextra2.aluga(p2);
		Assert.assertEquals(camaextra1.getPreco(), 1290, 0.0005);
		Assert.assertEquals(camaextra2.getPreco(), 4590, 0.0005);

		Assert.assertEquals("Cama extra alugada por 43 dias", camaextra1.getDescricao());
		Assert.assertEquals("Cama extra alugada por 153 dias", camaextra2.getDescricao());

		Assert.assertTrue(camaextra1.getHistorico().contains(p1));
		Assert.assertFalse(camaextra1.getHistorico().contains(p2));
		Assert.assertFalse(camaextra2.getHistorico().contains(p1));
		Assert.assertTrue(camaextra2.getHistorico().contains(p2));
	}

	@Test
	public void testEquals() {
		Assert.assertFalse(camaextra1.equals(null));
		Assert.assertFalse(camaextra1.equals(camaextra2));
		Assert.assertFalse(camaextra1.equals(camaextra3));
		Assert.assertEquals(camaextra2, camaextra3);
	}

	@Test
	public void testToString(){
		Assert.assertEquals(camaextra1.toString(), "10");
		Assert.assertEquals(camaextra2.toString(), "18");
		Assert.assertEquals(camaextra3.toString(), "18");
		Assert.assertEquals(camaextra2.toString(), camaextra3.toString());
	}

	@Test
	public void testClone() {
		Assert.assertEquals(0, ((CamaExtra) camaextra1.clone()).getPreco(), 0.01);

		Assert.assertTrue(camaextra1.aluga(p1));
		Assert.assertTrue(camaextra1.aluga(p2));

		camaextra2 = (CamaExtra) camaextra1.clone();
		Assert.assertEquals(camaextra1, camaextra2);
		Assert.assertEquals(camaextra1.getPreco(), camaextra2.getPreco(), 0.001);

		Assert.assertTrue(camaextra1.getHistorico().contains(p1));
		Assert.assertTrue(camaextra1.getHistorico().contains(p2));
		Assert.assertFalse(camaextra2.getHistorico().contains(p1));
		Assert.assertTrue(camaextra2.getHistorico().contains(p2));

		Assert.assertTrue(camaextra1.aluga(p3));
		Assert.assertNotEquals(camaextra1.getPreco(), camaextra2.getPreco(), 0.001);
		Assert.assertTrue(camaextra1.getHistorico().contains(p3));
		Assert.assertFalse(camaextra2.getHistorico().contains(p3));
	}
}
