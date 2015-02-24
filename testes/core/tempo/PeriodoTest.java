package core.tempo;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PeriodoTest {

	private Calendar c1;
	private Calendar c2;
	private Calendar c3;

	private Periodo p1;
	private Periodo p2;
	private Periodo p3;

	@Before
	public void before(){

		c1 = new GregorianCalendar(2014,1,28);
		c2 = new GregorianCalendar(2014,2,2);
		c3 = new GregorianCalendar(2014,2,28);

		p1 = new Periodo(c1,c2);
		p2 = new Periodo(c2,c3);
		p3 = new Periodo(c1,c3);
	}

	@Test
	public void ConstrutorTest() {
		try {
			new Periodo(null, c1);
			Assert.fail();
		} catch(IllegalArgumentException i) {}

		try {
			new Periodo(c1, null);
			Assert.fail();
		} catch(IllegalArgumentException i) {}

		try {
			new Periodo(c2, c1);
			Assert.fail();
		} catch(IllegalArgumentException i) {}
	}

	@Test
	public void getNumeroDiasHorasTest() {

		Assert.assertEquals(2, p1.getNumeroDias());
		Assert.assertEquals(26, p2.getNumeroDias());
		Assert.assertEquals(28, p3.getNumeroDias());

		c1 = new GregorianCalendar(2016, 1, 1);
		c2 = new GregorianCalendar(2017, 1, 1);
		p3 = new Periodo(c1, c2);
		Assert.assertEquals(366, p3.getNumeroDias());

		c1 = new GregorianCalendar(2016, 1, 1);
		c2 = new GregorianCalendar(2025, 1, 11);
		p3 = new Periodo(c1, c2);
		Assert.assertEquals(3298, p3.getNumeroDias());

		c1 = new GregorianCalendar(2016, 1, 1);
		p3.setInicio(c1);
		c1 = new GregorianCalendar(2016, 1, 2);
		p3.setFim(c1);
		Assert.assertEquals(1, p3.getNumeroDias());

		c1 = new GregorianCalendar(2016, 1, 1, 12, 36, 48);
		c2 = new GregorianCalendar(2016, 1, 1, 15, 56, 12);
		p3 = new Periodo(c1, c2);
		Assert.assertEquals(3, p3.getNumeroHoras());

		c1 = new GregorianCalendar(2016, 1, 1, 12, 36, 48);
		c2 = new GregorianCalendar(2016, 1, 1, 16, 26, 34);
		p3 = new Periodo(c1, c2);
		Assert.assertEquals(3, p3.getNumeroHoras());

		c1 = new GregorianCalendar(2016, 1, 1, 12, 36, 48);
		c2 = new GregorianCalendar(2016, 1, 1, 13, 36, 47);
		p3 = new Periodo(c1, c2);
		Assert.assertEquals(0, p3.getNumeroHoras());
	}

	@Test
	public void entraEmConlitoTest() {
		Assert.assertFalse(p1.entraEmConflito(null));

		Assert.assertTrue(p2.entraEmConflito(p3));
		Assert.assertTrue(p3.entraEmConflito(p2));
		Assert.assertTrue(p3.entraEmConflito(p3));
		Assert.assertTrue(p1.entraEmConflito(p3));
		Assert.assertTrue(p1.entraEmConflito(p2));

		c1 = new GregorianCalendar(2016,1,1);
		c2 = new GregorianCalendar(2017,1,1);
		p3 = new Periodo(c1,c2);
		Assert.assertFalse(p1.entraEmConflito(p3));
	}

	@Test
	public void contemTest() {
		Calendar dataNull = null;
		Assert.assertFalse(p1.contem(dataNull));
		Assert.assertTrue(p1.contem(c1));
		Assert.assertTrue(p1.contem(c2));
		Assert.assertTrue(p1.contem(new GregorianCalendar(2014,2,1)));
		Assert.assertFalse(p1.contem(new GregorianCalendar(2014,1,1)));
		Assert.assertFalse(p1.contem(new GregorianCalendar(2014,2,4)));

		Periodo periodoNull = null;
		Assert.assertFalse(p2.contem(periodoNull));
		Assert.assertFalse(p2.contem(p1));
		Assert.assertFalse(p2.contem(p3));
		Assert.assertTrue(p2.contem(p2));

		c1 = new GregorianCalendar(2014,2,3);
		c2 = new GregorianCalendar(2014,2,27);
		p1 = new Periodo(c1,c2);

		Assert.assertTrue(p2.contem(p1));

		c1 = new GregorianCalendar(2014,2,3);
		c2 = new GregorianCalendar(2014,3,2);
		p1 = new Periodo(c1,c2);

		Assert.assertFalse(p2.contem(p1));

		c1 = new GregorianCalendar(2014,2,1);
		c2 = new GregorianCalendar(2014,2,27);
		p1 = new Periodo(c1,c2);

		Assert.assertFalse(p2.contem(p1));
	}

	@Test
	public void equalsTest() {
		Assert.assertFalse(p2.equals(null));

		Assert.assertTrue(p2.equals(p3));
		Assert.assertTrue(p3.equals(p2));
		Assert.assertTrue(p3.equals(p3));
		Assert.assertTrue(p1.equals(p3));
		Assert.assertTrue(p1.equals(p2));

		c1 = new GregorianCalendar(2016,1,1);
		c2 = new GregorianCalendar(2017,1,1);
		p3 = new Periodo(c1,c2);
		Assert.assertFalse(p1.equals(p3));
	}

	@Test
	public void compareToTest() {
		Assert.assertEquals(0,p2.compareTo(p3));
		Assert.assertEquals(0,p1.compareTo(p3));

		c1 = new GregorianCalendar(2016,1,1);
		c2 = new GregorianCalendar(2017,1,1);
		p3 = new Periodo(c1,c2);
		Assert.assertEquals(-1,p1.compareTo(p3));
		Assert.assertEquals(1,p3.compareTo(p1));

		c1 = new GregorianCalendar(2014,1,31);
		c2 = new GregorianCalendar(2014,2,5);
		p3 = new Periodo(c1,c2);
		Assert.assertEquals(-1,p1.compareTo(p3));
		Assert.assertEquals(1,p3.compareTo(p1));
	}

	@Test
	public void cloneTest() {
		p2 = (Periodo) p1.clone();
		Assert.assertEquals(p1, p2);

		Assert.assertEquals(p1.getInicio(), p2.getInicio());
		Assert.assertEquals(p1.getFim(), p2.getFim());

		p1.getInicio().set(Calendar.YEAR, 1996);
		Assert.assertNotEquals(p1.getInicio(), p2.getInicio());

		p1.getFim().set(Calendar.YEAR, 1996);
		Assert.assertNotEquals(p1.getFim(), p2.getFim());
	}
}
