package core.hotel;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class OpiniaoTest {

	private Calendar data;
	private Opiniao opiniao;

	@Before
	public void criaObjetos() throws Exception {
		data = new GregorianCalendar(2015, 3, 6);
		opiniao = new Opiniao("Arthur", 9, "Hotel otimo e atendimento rapido, recomendo.", data);
	}

	@Test
	public void testaCriaOpiniao() {
		try {
			new Opiniao(9, null, new GregorianCalendar());
			Assert.fail();
		} catch (IllegalArgumentException e) {}

		try {
			new Opiniao("Arthur", 9, null, new GregorianCalendar());
			Assert.fail();
		} catch (IllegalArgumentException e) {}

		try {
			new Opiniao("Arthur", 9, "alo", null);
			Assert.fail();
		} catch (IllegalArgumentException e) {}

		Assert.assertEquals(data, opiniao.getData());

		opiniao = new Opiniao("", 9, "Hotel otimo e atendimento rapido, recomendo.", data);
		Assert.assertEquals("An\00F4nimo", opiniao.getAutor());

		opiniao = new Opiniao(null, 9, "Hotel otimo e atendimento rapido, recomendo.", data);
		Assert.assertEquals("An\00F4nimo", opiniao.getAutor());
	}

	@Test
	public void testaToString() throws Exception {
		Opiniao opiniao1 = new Opiniao(2, "Pessimo quarto e atendimento.", new GregorianCalendar());
		Opiniao opiniao2 = new Opiniao(10, "", new GregorianCalendar());

		Assert.assertEquals(opiniao.toString(), "Autor: " + opiniao.getAutor() + ", Nota: " + opiniao.getNota() + ", Coment\u00E1rio: " + opiniao.getComentario());
		Assert.assertEquals(opiniao1.toString(), "Autor: " + opiniao1.getAutor() + ", Nota: " + opiniao1.getNota() + ", Coment\u00E1rio: " + opiniao1.getComentario());
		Assert.assertEquals(opiniao2.toString(), "Autor: " + opiniao2.getAutor() + ", Nota: " + opiniao2.getNota() + ", Coment\u00E1rio: " + opiniao2.getComentario());
	}

	@Test
	public void testaEquals() throws Exception {
		Opiniao opiniao1 = new Opiniao("Arthur", 9, "Hotel otimo e atendimento rapido, recomendo.", new GregorianCalendar());
		Opiniao opiniao2 = new Opiniao(9, "Hotel otimo e atendimento rapido, recomendo.", new GregorianCalendar());
		Opiniao opiniao3 = new Opiniao("Arthur", 2, "Hotel otimo e atendimento rapido, recomendo.", new GregorianCalendar());
		Opiniao opiniao4 = new Opiniao("Arthur", 9, "", new GregorianCalendar());

		Assert.assertTrue(opiniao.equals(opiniao1));
		Assert.assertFalse(opiniao.equals(opiniao2));
		Assert.assertFalse(opiniao.equals(opiniao3));
		Assert.assertFalse(opiniao.equals(opiniao4));
		Assert.assertFalse(opiniao.equals(null));

		Assert.assertEquals(opiniao.hashCode(), opiniao1.hashCode());
		Assert.assertNotEquals(opiniao.hashCode(), opiniao2.hashCode());
		Assert.assertNotEquals(opiniao.hashCode(), opiniao3.hashCode());
		Assert.assertNotEquals(opiniao.hashCode(), opiniao4.hashCode());
	}
}
