package test;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import servicos.Restaurante;
import servicos.Refeicao;

public class RestauranteTest {
	List<Refeicao> estoque;
	private Refeicao refeicao1;
	private Refeicao refeicao2;
	private Restaurante heartAttack;
	private Restaurante diabetes;

	@Before
	public void setUp() throws Exception {
		estoque = new ArrayList<Refeicao>();
		refeicao1 = new Refeicao(52.89f,
				"Um prato generico de valor aceitavel.");
		refeicao2 = new Refeicao(199.89f,
				"Um prato refinado, caro e de gosto detestavel.");
		estoque.add(refeicao1);
		estoque.add(refeicao2);
		heartAttack = new Restaurante("Le chanson");
		diabetes = new Restaurante("Mon petit", estoque);
	}

	@Test
	public void testConstrutor() {
		try {
			heartAttack = new Restaurante(null);
		} catch (NullPointerException e) {
		}
		try {
			heartAttack = new Restaurante("les chason", null);
		} catch (NullPointerException e) {
		}

	}

	@Test
	public void testEquals() {
		Assert.assertFalse(heartAttack.equals(diabetes));
		heartAttack.cadastraRefeicao(refeicao1);
		heartAttack.cadastraRefeicao(refeicao2);
		heartAttack.ateraNome(diabetes.getNome());
		Assert.assertEquals(heartAttack, diabetes);
	}

	@Test
	public void testToString() {
		Assert.assertEquals(diabetes.toString(), "Restaurante [nome=Mon petit, estoque= Refeicao [Descricao=Um prato generico de valor aceitavel., Preco=52.89], Refeicao [Descricao=Um prato refinado, caro e de gosto detestavel., Preco=199.89]]");
		Assert.assertEquals(heartAttack.toString(), "Restaurante [nome=Le chanson, estoque= ]");
	}
	

}
