package test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import servicos.CamaExtra;
import tempo.Periodo;

public class CamaExtraTest {
	private CamaExtra camaextra1;
	private CamaExtra camaextra2;
	private CamaExtra camaextra3;
	private Calendar c1;
	private Calendar c2;
	private Calendar c3;
	private Calendar c4;
	private Periodo p1;
	private Periodo p2;

	@Before
	public void setUp() {
		camaextra1 = new CamaExtra(10);
		camaextra2 = new CamaExtra(18);
		camaextra3 = new CamaExtra(18);
		c1 = new GregorianCalendar(2014, 1, 28);
		c2 = new GregorianCalendar(2014, 3, 12);
		c3 = new GregorianCalendar(2016, 1, 30);
		c4 = new GregorianCalendar(2016, 6, 32);
		p1 = new Periodo(c1, c2);
		p2 = new Periodo(c3, c4);
	}

	@Test
	public void testGetPreco() {
		camaextra1.setPeriodo(p1);
		camaextra2.setPeriodo(p2);
		Assert.assertEquals(camaextra1.getPreco(), 1290, 0.0005);
		Assert.assertEquals(camaextra2.getPreco(), 4590, 0.0005);
	}

	@Test
	public void testEquals() {
		Assert.assertFalse(camaextra1.equals(camaextra2));
		Assert.assertFalse(camaextra1.equals(camaextra3));
		Assert.assertEquals(camaextra2,camaextra3);
	}
	
	@Test
	public void testToString(){
	Assert.assertEquals(camaextra1.toString(), "CamaExtra [descricao=Servico de Cama Extra, codigo=10]");
	Assert.assertEquals(camaextra2.toString(), "CamaExtra [descricao=Servico de Cama Extra, codigo=18]");
	Assert.assertEquals(camaextra3.toString(), "CamaExtra [descricao=Servico de Cama Extra, codigo=18]");
	Assert.assertEquals(camaextra2.toString(), camaextra3.toString());
	
	}
}
