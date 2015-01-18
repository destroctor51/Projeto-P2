package test;

import hotel.Restaurante;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import servicos.pagaveis.Refeicao;

public class RestauranteTest {
	List<Refeicao> estoque;
	private Refeicao refeicao1;
	private Refeicao refeicao2;
	private Restaurante heartAttack;
	private Restaurante diabetes;

	@Before
	public void setUp() {
		heartAttack = new Restaurante("Le chanson");
		diabetes = new Restaurante("Prestes a fechar.");
		diabetes.cadastraRefeicao("Um prato generico de valor aceitavel.", 52.89f);
		diabetes.cadastraRefeicao("Um prato refinado, caro e de gosto detestavel.", 199.89f);
	}

	@Test
	public void testConstrutor() {
		try {
			new Restaurante(null);
			Assert.fail();
		} catch (IllegalArgumentException e) {}
	}

	@Test
	public void testCadastra() {
		refeicao1 = new Refeicao("Um prato generico de valor aceitavel.", 52.89f);
		refeicao2 = new Refeicao("Um prato refinado, caro e de gosto detestavel.", 199.89f);

		Assert.assertFalse(diabetes.cadastraRefeicao("Um prato generico de valor aceitavel.", 52.89f));

		Assert.assertTrue(diabetes.getEstoque().contains(refeicao1));
		Assert.assertTrue(diabetes.getEstoque().contains(refeicao2));
		Assert.assertFalse(diabetes.getEstoque().contains(null));

		Assert.assertFalse(heartAttack.getEstoque().contains(refeicao1));
		Assert.assertFalse(heartAttack.getEstoque().contains(refeicao2));
		Assert.assertFalse(heartAttack.getEstoque().contains(null));

		Assert.assertTrue(diabetes.removeRefeicao(refeicao1));
		Assert.assertFalse(heartAttack.removeRefeicao(refeicao1));
		Assert.assertFalse(diabetes.getEstoque().contains(refeicao1));
	}

	@Test
	public void testEquals() {
		Assert.assertNotEquals(heartAttack, diabetes);
		heartAttack.alteraNome(diabetes.getNome());
		Assert.assertEquals(heartAttack, diabetes);
		Assert.assertNotEquals(heartAttack, null);
	}

	@Test
	public void testToString() {
		Assert.assertEquals(diabetes.toString(), "Restaurante [nome=Prestes a fechar., estoque=[Refeicao [Descricao=Um prato refinado, caro e de gosto detestavel., Preco=199.89], Refeicao [Descricao=Um prato generico de valor aceitavel., Preco=52.89]]]");
		Assert.assertEquals(heartAttack.toString(), "Restaurante [nome=Le chanson, estoque=[]]");
	}


}
