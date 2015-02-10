package core.hotel;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import core.hotel.Opiniao;


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
			Assert.fail("Esperava excecao, pois o comentario esta null.");
		} catch (IllegalArgumentException e) {}

		try {
			new Opiniao("Arthur", 9, null, new GregorianCalendar());
			Assert.fail("Esperava excecao, pois o comentario esta null.");
		} catch (IllegalArgumentException e) {}

		try {
			new Opiniao("Arthur", 9, "alo", null);
			Assert.fail("Esperava excecao, pois a data esta null.");
		} catch (IllegalArgumentException e) {}

		Assert.assertEquals(data, opiniao.getData());
	}

	@Test
	public void testaToString() throws Exception {
		Opiniao opiniao1 = new Opiniao(2, "Pessimo quarto e atendimento.", new GregorianCalendar());
		Opiniao opiniao2 = new Opiniao(10, "", new GregorianCalendar());

		Assert.assertEquals(opiniao.toString(), "Opiniao [Nota = 9, comentario = Hotel otimo e atendimento rapido, recomendo., autor = Arthur]");
		Assert.assertEquals(opiniao1.toString(), "Opiniao [Nota = 2, comentario = Pessimo quarto e atendimento., autor = Anonimo]");
		Assert.assertEquals(opiniao2.toString(), "Opiniao [Nota = 10, comentario = , autor = Anonimo]");
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
