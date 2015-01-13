package test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import servicos.alugaveis.Babysitter;
import tempo.Periodo;

public class BabyssiterTest {

	private Babysitter baba1;
	private Babysitter baba2;
	private Babysitter baba3;
	private Calendar c1;
	private Calendar c2;
	private Periodo p1;

	@Before
	public void setUp() throws Exception {
		baba1 = new Babysitter("Juliana");
		baba2 = new Babysitter("Maria");
		baba3 =  new Babysitter("Juliana");
		c1 = new GregorianCalendar(2014,1,28, 12, 0);
		c2 = new GregorianCalendar(2014,1,28, 15, 0);
		p1 = new Periodo(c1,c2);
		
	}

	@Test
	public void testConstrutor() throws Exception {
		try {
			baba1 = new Babysitter(null);
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void testEquals() {
		Assert.assertFalse(baba1.equals(baba2));
		Assert.assertEquals(baba1, baba3);
		Assert.assertFalse(baba3.equals(baba2));
		

	}
	
	@Test
		public void testGetPreco(){
		baba1.setPeriodo(p1);
		Assert.assertEquals(baba1.getPreco(), 75, 0.005);
		c1 = new GregorianCalendar(2014,1,22, 12, 0);
		c2 = new GregorianCalendar(2014,1,24, 18, 0);
		p1 = new Periodo(c1,c2);
		baba1.setPeriodo(p1);
		Assert.assertEquals(baba1.getPreco(), 1675, 0.005);
		
		
	}

	@Test
	public void testToString() {
		Assert.assertEquals(baba1.toString(), "Babysitter [descricao=Servico de Babysitter, nome=Juliana]");
		Assert.assertEquals(baba2.toString(), "Babysitter [descricao=Servico de Babysitter, nome=Maria]");
		

	}

}
