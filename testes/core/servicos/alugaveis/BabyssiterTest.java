package core.servicos.alugaveis;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import core.servicos.alugaveis.Babysitter;
import core.tempo.Periodo;

public class BabyssiterTest {

	private Babysitter baba1;
	private Babysitter baba2;
	private Babysitter baba3;
	private Calendar c1;
	private Calendar c2;
	private Calendar c3;
	private Calendar c4;
	private Periodo p1;
	private Periodo p2;

	@Before
	public void setUp() throws Exception {
		baba1 = new Babysitter("Juliana");
		baba2 = new Babysitter("Maria");
		baba3 =  new Babysitter("Juliana");
		c1 = new GregorianCalendar(2014,1,28, 12, 0);
		c2 = new GregorianCalendar(2014,1,28, 15, 0);
		c3 = new GregorianCalendar(2016,1,28, 12, 0);
		c4 = new GregorianCalendar(2016,1,28, 15, 0);
		p1 = new Periodo(c1,c2);
		p2 = new Periodo(c3,c4);

	}

	@Test
	public void testConstrutor() throws Exception {
		try {
			baba1 = new Babysitter(null);
			Assert.fail();
		} catch (IllegalArgumentException e) {}

		Assert.assertEquals("Servico de Babysitter", baba1.getDescricao());
		Assert.assertEquals("Servico de Babysitter", baba2.getDescricao());
		Assert.assertEquals("Servico de Babysitter", baba3.getDescricao());
	}

	@Test
	public void testEquals() {
		Assert.assertNotEquals(baba1, null);
		Assert.assertNotEquals(baba1, baba2);
		Assert.assertEquals(baba1, baba3);
		Assert.assertNotEquals(baba3, baba2);
	}

	@Test
	public void testGetPreco(){
		Assert.assertEquals(baba1.getPreco(), 0, 0.005);
		Assert.assertTrue(baba1.aluga(p1));
		Assert.assertFalse(baba1.aluga(p1));
		Assert.assertEquals(baba1.getPreco(), 75, 0.005);
		c1 = new GregorianCalendar(2014,1,22, 12, 0);
		c2 = new GregorianCalendar(2014,1,24, 18, 0);
		p1 = new Periodo(c1,c2);
		baba1.aluga(p1);
		Assert.assertEquals(baba1.getPreco(), 1675, 0.005);

		try {
			baba1.aluga(null);
			Assert.fail();
		} catch(IllegalArgumentException e) {}
	}

	@Test
	public void testToString() {
		Assert.assertEquals(baba1.toString(), "Juliana");
		Assert.assertEquals(baba2.toString(), "Maria");
	}

	@Test
	public void testClone() {
		Assert.assertEquals(0, ((Babysitter) baba1.clone()).getPreco(), 0.01);

		Assert.assertTrue(baba1.aluga(p1));
		Assert.assertTrue(baba1.aluga(p2));

		baba2 = (Babysitter) baba1.clone();
		Assert.assertEquals(baba1, baba2);
		Assert.assertEquals(baba1.getPreco(), baba2.getPreco(), 0.001);

		Assert.assertTrue(baba1.getHistorico().contains(p1));
		Assert.assertTrue(baba1.getHistorico().contains(p2));
		Assert.assertFalse(baba2.getHistorico().contains(p1));
		Assert.assertTrue(baba2.getHistorico().contains(p2));
	}
}
