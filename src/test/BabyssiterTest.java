package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import servicos.Babysitter;

public class BabyssiterTest {

	private Babysitter baba1;
	private Babysitter baba2;

	@Before
	public void setUp() throws Exception {
		baba1 = new Babysitter("Juliana", 10, 10);
		baba2 = new Babysitter("Maria", 5, 15);
	}

	@Test
	public void testConstrutor() throws Exception {
		try {
			baba1 = new Babysitter(null, 12, 12);
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void testEquals() {
		Assert.assertFalse(baba1.equals(baba2));
		baba1.alteraNome(baba2.getDescricao());
		Assert.assertEquals(baba1, baba2);

	}

	@Test
	public void testToString() {
		Assert.assertEquals(baba1.toString(), "Babysitter [nome=Juliana, horasNormais=10, horasDobradas=10]");
		Assert.assertEquals(baba2.toString(), "Babysitter [nome=Maria, horasNormais=5, horasDobradas=15]");
		

	}

}
