package test;

import hotel.Opiniao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class OpiniaoTest {
	private Opiniao opiniao;
	
	@Before
	public void criaObjetos() throws Exception {
		opiniao = new Opiniao("Arthur", 9, "Hotel otimo e atendimento rapido, recomendo.");
	}
	
	@Test
	public void testaCriaOpiniao() {
		try {
			new Opiniao(9, null);
			Assert.fail("Esperava exceção, pois o comentario esta null.");
		} catch (IllegalArgumentException e) {
		}
		
		try {
			new Opiniao(null, 9, "Hotel otimo e atendimento rapido, recomendo.");
			Assert.fail("Esperava exceção, pois o autor esta null.");
		} catch (IllegalArgumentException e) {
		}
		
		try {
			new Opiniao("Arthur", 9, null);
			Assert.fail("Esperava exceção, pois o comentario esta null.");
		} catch (IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testaToString() throws Exception {
		Opiniao opiniao1 = new Opiniao(2, "Pessimo quarto e atendimento.");
		Opiniao opiniao2 = new Opiniao(10, "");
		
		Assert.assertEquals(opiniao.toString(), "Opiniao [Nota = 9, comentario = Hotel otimo e atendimento rapido, recomendo., autor = Arthur]");
		Assert.assertEquals(opiniao1.toString(), "Opiniao [Nota = 2, comentario = Pessimo quarto e atendimento., autor = Anonimo]");
		Assert.assertEquals(opiniao2.toString(), "Opiniao [Nota = 10, comentario = , autor = Anonimo]");
	}

	@Test
	public void testaEquals() throws Exception {
		Opiniao opiniao1 = new Opiniao("Arthur", 9, "Hotel otimo e atendimento rapido, recomendo.");		
		Opiniao opiniao2 = new Opiniao(9, "Hotel otimo e atendimento rapido, recomendo.");
		Opiniao opiniao3 = new Opiniao("Arthur", 2, "Hotel otimo e atendimento rapido, recomendo.");
		Opiniao opiniao4 = new Opiniao("Arthur", 9, "");
		
		Assert.assertTrue(opiniao.equals(opiniao1));
		Assert.assertFalse(opiniao.equals(opiniao2));
		Assert.assertFalse(opiniao.equals(opiniao3));
		Assert.assertFalse(opiniao.equals(opiniao4));
	}
}
