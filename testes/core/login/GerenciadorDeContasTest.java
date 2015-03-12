package core.login;

import static core.login.Permissao.ADMINISTRADOR;
import static core.login.Permissao.FUNCIONARIO;
import static core.login.Permissao.GERENTE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class GerenciadorDeContasTest {

	GerenciadorDeContas gdc;

	private final String emailDefault = "email@mail.com";
	private final String nomeDefault = "maria jose";

	@Before
	public void setUp() {
		gdc = new GerenciadorDeContas();

		gdc.cadastra("Roberto", FUNCIONARIO, nomeDefault, emailDefault, "afewgs");
		gdc.cadastra("Paulo", FUNCIONARIO, nomeDefault, emailDefault, "grhtrd");
		gdc.cadastra("Mateus", FUNCIONARIO, nomeDefault, emailDefault, "aofnhsue");
		gdc.cadastra("Rodrigo", FUNCIONARIO, nomeDefault, emailDefault, "asdpi");
		gdc.cadastra("Rafaela", FUNCIONARIO, nomeDefault, emailDefault, "eqrete");
		gdc.cadastra("Nara", ADMINISTRADOR, nomeDefault, emailDefault, "htrthr");
		gdc.cadastra("Ingrid", ADMINISTRADOR, nomeDefault, emailDefault, "asdfe");
		gdc.cadastra("Maria", ADMINISTRADOR, nomeDefault, emailDefault, "ththuy");
		gdc.cadastra("Safire", ADMINISTRADOR, nomeDefault, emailDefault, "ahybnui");
		gdc.cadastra("Victor", GERENTE, nomeDefault, emailDefault, "oemugnu");
	}

	@Test
	public void testCadastra() {
		gdc = new GerenciadorDeContas();
		assertTrue(gdc.vazio());

		assertNotEquals(null, gdc.cadastra("Roberto", FUNCIONARIO, nomeDefault, emailDefault, "afewgs"));
		assertNotEquals(null, gdc.cadastra("Paulo", FUNCIONARIO, nomeDefault, emailDefault, "grhtrd"));
		assertNotEquals(null, gdc.cadastra("Mateus", FUNCIONARIO, nomeDefault, emailDefault, "aofnhsue"));
		assertNotEquals(null, gdc.cadastra("Rodrigo", FUNCIONARIO, nomeDefault, emailDefault, "asdpi"));
		assertNotEquals(null, gdc.cadastra("Rafaela", FUNCIONARIO, nomeDefault, emailDefault, "eqrete"));
		assertNotEquals(null, gdc.cadastra("Nara", ADMINISTRADOR, nomeDefault, emailDefault, "htrthr"));
		assertNotEquals(null, gdc.cadastra("Ingrid", ADMINISTRADOR, nomeDefault, emailDefault, "asdfe"));
		assertNotEquals(null, gdc.cadastra("Maria", ADMINISTRADOR, nomeDefault, emailDefault, "ththuy"));
		assertNotEquals(null, gdc.cadastra("Safire", ADMINISTRADOR, nomeDefault, emailDefault, "ahybnui"));
		assertNotEquals(null, gdc.cadastra("Victor", GERENTE, nomeDefault, emailDefault, "oemugnu"));

		assertEquals(null, gdc.cadastra("Nara", FUNCIONARIO, nomeDefault, emailDefault, "afewgs"));
		assertEquals(null, gdc.cadastra("Ingrid", FUNCIONARIO, nomeDefault, emailDefault, "grhtrd"));
		assertEquals(null, gdc.cadastra("Maria", FUNCIONARIO, nomeDefault, emailDefault, "aofnhsue"));
		assertEquals(null, gdc.cadastra("Safire", FUNCIONARIO, nomeDefault, emailDefault, "asdpi"));
		assertEquals(null, gdc.cadastra("Victor", FUNCIONARIO, nomeDefault, emailDefault, "eqrete"));
		assertEquals(null, gdc.cadastra("Roberto", ADMINISTRADOR, nomeDefault, emailDefault, "htrthr"));
		assertEquals(null, gdc.cadastra("Paulo", ADMINISTRADOR, nomeDefault, emailDefault, "asdfe"));
		assertEquals(null, gdc.cadastra("Mateus", ADMINISTRADOR, nomeDefault, emailDefault, "ththuy"));
		assertEquals(null, gdc.cadastra("Rodrigo", ADMINISTRADOR, nomeDefault, emailDefault, "ahybnui"));
		assertEquals(null, gdc.cadastra("Rafaela", GERENTE, nomeDefault, emailDefault, "oemugnu"));
		
		assertFalse(gdc.vazio());
		
		assertEquals(null,gdc.getConta("Jurema"));
		
		Conta conta = new Conta("Victor", GERENTE, nomeDefault, emailDefault, "oemugnu");
		assertEquals(true,conta.equals(gdc.getConta("Victor")));
		
	}

	@Test
	public void testLogin() {
		assertEquals(null, gdc.login("Roberto", "naosei"));
		assertEquals(null, gdc.login("Victor", "seila"));
		assertEquals(null, gdc.login("Safire", "senha?"));
		assertEquals(null, gdc.login("Emanuell", "qwerr"));

		assertEquals("Paulo".hashCode(), gdc.login("Paulo", "grhtrd").hashCode());
		assertEquals("Rafaela".hashCode(), gdc.login("Rafaela", "eqrete").hashCode());
		assertEquals("Ingrid".hashCode(), gdc.login("Ingrid", "asdfe").hashCode());
		assertEquals("Victor".hashCode(), gdc.login("Victor", "oemugnu").hashCode());
	}

	@Test
	public void testRemove() {
		assertEquals("Roberto".hashCode(), gdc.login("Roberto", "afewgs").hashCode());
		assertEquals("Rafaela".hashCode(), gdc.login("Rafaela", "eqrete").hashCode());
		assertEquals("Maria".hashCode(), gdc.login("Maria", "ththuy").hashCode());

		Conta emanuell = new Conta("Emanuell", FUNCIONARIO, nomeDefault, emailDefault, "sdfji");
		Conta jacob = new Conta("Jacob", GERENTE, nomeDefault, emailDefault, "feafe");
		Conta abigail = new Conta("Abigail", ADMINISTRADOR, nomeDefault, emailDefault, "gweagg");

		assertFalse(gdc.remove(emanuell));
		assertFalse(gdc.remove(jacob));
		assertFalse(gdc.remove(abigail));

		Conta roberto = null;
		Conta rafaela = null;
		Conta maria = null;

		for(Conta c : gdc) {
			if(c.getID().equals("Roberto")) roberto = c;
			if(c.getID().equals("Rafaela")) rafaela = c;
			if(c.getID().equals("Maria")) maria = c;
		}

		assertTrue(gdc.remove(roberto));
		assertTrue(gdc.remove(rafaela));
		assertTrue(gdc.remove(maria));

		assertEquals(null, gdc.login("Roberto", "afewgs"));
		assertEquals(null, gdc.login("Rafaela", "eqrete"));
		assertEquals(null, gdc.login("Maria", "ththuy"));
	}
}
