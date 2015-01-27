package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import servicos.pagaveis.Refeicao;

;

public class RefeicaoTest {

	private Refeicao refeicao1;
	private Refeicao refeicao2;

	@Before
	public void setUp() {
		refeicao1 = new Refeicao("Pra nao morrer de fome", 52.89f);
		refeicao2 = new Refeicao("sss Pra ostentar", 199.89f);

	}

	@Test
	public void testConstrutor() {
		try {
			new Refeicao(null, 52.89f);
			Assert.fail();
		} catch (IllegalArgumentException e) {}

		try {
			refeicao1.alteraDescricao(null);
		} catch (IllegalArgumentException e) {}
	}

	@Test
	public void testEquals() {
		Assert.assertNotEquals(refeicao1, refeicao2);
		refeicao2.alteraDescricao(refeicao1.getDescricao());
		refeicao2.alteraPreco(refeicao1.getPreco());
		Assert.assertEquals(refeicao1, refeicao2);
		Assert.assertNotEquals(refeicao1, null);
	}

	@Test
	public void testToString() {
		Assert.assertEquals("Pra nao morrer de fome", refeicao1.toString());
		Assert.assertEquals("sss Pra ostentar", refeicao2.toString());
		refeicao2.alteraDescricao(refeicao1.getDescricao());
		refeicao2.alteraPreco(refeicao1.getPreco());
		Assert.assertEquals(refeicao1.toString(), refeicao2.toString());
	}

	@Test
	public void testHas() {
		Assert.assertFalse(refeicao1.hashCode()== refeicao2.hashCode());
		refeicao1.alteraDescricao(refeicao2.getDescricao());
		Assert.assertEquals(refeicao1.hashCode(), refeicao2.hashCode());
	}

	@Test
	public void testClone() {
		refeicao2 = (Refeicao) refeicao1.clone();
		Assert.assertEquals(refeicao1, refeicao2);
		refeicao1.alteraDescricao("Outro nome");
		Assert.assertNotEquals(refeicao1, refeicao2);
	}
}
