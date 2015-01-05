package test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tempo.Periodo;

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
		c2 = new GregorianCalendar(2014,1,30);
		c3 = new GregorianCalendar(2014,2,28);
		
		p1 = new Periodo(c1,c2);
		p2 = new Periodo(c2,c3);
		p3 = new Periodo(c1,c3);
	}
	
	@Test
	public void ConstrutorTest() {
		try {
			new Periodo(null,c1);
		} catch(IllegalArgumentException i){}
	
		try {
			new Periodo(c1,null);
		} catch(IllegalArgumentException i) {}
	}
	
	@Test
	public void getNumeroDiasTest() {
		
		Assert.assertEquals(2,p1.getNumeroDias());
		Assert.assertEquals(26,p2.getNumeroDias());
		Assert.assertEquals(28,p3.getNumeroDias());
		
		c1 = new GregorianCalendar(2016,1,1);
		c2 = new GregorianCalendar(2017,1,1);
		p3 = new Periodo(c1,c2);
		Assert.assertEquals(366,p3.getNumeroDias());
		
		c1 = new GregorianCalendar(2016,1,1);
		c2 = new GregorianCalendar(2025,1,11);
		p3 = new Periodo(c1,c2);
		Assert.assertEquals(3298,p3.getNumeroDias());
		
		c1 = new GregorianCalendar(2016,1,1);
		p3.setInicio(c1);
		c1 = new GregorianCalendar(2016,1,2);
		p3.setFim(c1);
		Assert.assertEquals(1,p3.getNumeroDias());
	}
	
	@Test
	public void entraEmConlitoTest() {
		
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
	public void equalsTest() {
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
	
}
