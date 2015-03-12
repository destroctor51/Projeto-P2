package core.tempo;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import utils.Tempo;

public class EstacaoTest {

	private Calendar c1;
	private Calendar c2;
	private Calendar c3;
	private Calendar c4;

	private Periodo p1;
	private Periodo p2;
	private Periodo p3;

	private Estacao e1;

	@Before
	public void before(){
		c1 = new GregorianCalendar(2014,1,28);
		c2 = new GregorianCalendar(2014,1,30);
		c3 = new GregorianCalendar(2014,2,28);
		c4 = new GregorianCalendar(2014,2,30);

		p1 = new Periodo(c1,c2);
		p2 = new Periodo(c2,c3);
		p3 = new Periodo(c3,c4);

		e1 = new Estacao("Sao Joao", 0.1);
	}

	@Test
	public void ConstrutorTest() {
		Estacao e4 = new Estacao("Natal", 0.01);
		Assert.assertEquals(0.01,e4.getTarifa(),0.01);
		Assert.assertEquals("Natal", e4.getId());
		Assert.assertEquals("Natal", e4.toString());

		e4.setTarifa(0.02);
		Assert.assertEquals(0.02,e4.getTarifa(),0.01);

		try {
			new Estacao(null, 0.01);
			Assert.fail();
		} catch(IllegalArgumentException e) {}
		
		try {
			new Estacao("Estacao", -1);
			Assert.fail();
		} catch(IllegalArgumentException e) {}

		try {
			e1.setTarifa(-1);
			Assert.fail();
		} catch(IllegalArgumentException e) {}
		
	}

	@Test
	public void addPeriodoTest() {
		Assert.assertFalse(e1.entraEmConflito(p1));
		e1.addPeriodo(p1);
		Assert.assertTrue(e1.entraEmConflito(p1));

		Assert.assertFalse(e1.entraEmConflito(p2));
		e1.addPeriodo(p2);
		Assert.assertTrue(e1.entraEmConflito(p2));

		Assert.assertFalse(e1.entraEmConflito(p3));
		e1.addPeriodo(p3);
		Assert.assertTrue(e1.entraEmConflito(p3));
	}

	@Test
	public void entraEmConflitoTest() {
		Assert.assertFalse(e1.entraEmConflito(p1));
		e1.addPeriodo(p2);
		e1.addPeriodo(p3);

		Assert.assertFalse(e1.entraEmConflito(p1));
		Assert.assertTrue(e1.entraEmConflito(p2));
		Assert.assertTrue(e1.entraEmConflito(p3));

		e1.removePeriodo(p2);
		Assert.assertFalse(e1.entraEmConflito(p2));

		Assert.assertFalse(e1.contem(c1));
		Assert.assertFalse(e1.contem(c2));
		Assert.assertTrue(e1.contem(c3));
		Assert.assertFalse(e1.contem(c4));
	}

	@Test
	public void getPeriodoTest() {
		e1.addPeriodo(p1);
		e1.addPeriodo(p3);

		Set<Periodo> p = new TreeSet<>();

		p.addAll(Tempo.freeze(p1));
		p.addAll(Tempo.freeze(p3));

		Assert.assertEquals(p, e1.getPeriodos());

		e1.clear();
		Assert.assertEquals(0, e1.getPeriodos().size());
	}
	
	@Test
	public void setIdCloneTest(){
		
		try{
			e1.setId(null);
			Assert.fail();
		}catch(Exception exc){
			
		}
		e1.setId("3216354635");
		e1.addPeriodo(p1);
		Estacao e2 = (Estacao)e1.clone();
		Assert.assertTrue(e1.equals(e2));
	}
}
