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

	@Before
	public void setUp() {
		gdc = new GerenciadorDeContas();

		gdc.cadastra("Roberto", "afewgs", FUNCIONARIO);
		gdc.cadastra("Paulo", "grhtrd", FUNCIONARIO);
		gdc.cadastra("Mateus", "aofnhsue", FUNCIONARIO);
		gdc.cadastra("Rodrigo", "asdpi", FUNCIONARIO);
		gdc.cadastra("Rafaela", "eqrete", FUNCIONARIO);
		gdc.cadastra("Nara", "htrthr", ADMINISTRADOR);
		gdc.cadastra("Ingrid", "asdfe", ADMINISTRADOR);
		gdc.cadastra("Maria", "ththuy", ADMINISTRADOR);
		gdc.cadastra("Safire", "ahybnui", ADMINISTRADOR);
		gdc.cadastra("Victor", "oemugnu", GERENTE);
	}

	@Test
	public void testCadastra() {
		gdc = new GerenciadorDeContas();
		assertTrue(gdc.vazio());

		assertNotEquals(null, gdc.cadastra("Roberto", "afewgs", FUNCIONARIO));
		assertNotEquals(null, gdc.cadastra("Paulo", "grhtrd", FUNCIONARIO));
		assertNotEquals(null, gdc.cadastra("Mateus", "aofnhsue", FUNCIONARIO));
		assertNotEquals(null, gdc.cadastra("Rodrigo", "asdpi", FUNCIONARIO));
		assertNotEquals(null, gdc.cadastra("Rafaela", "eqrete", FUNCIONARIO));
		assertNotEquals(null, gdc.cadastra("Nara", "htrthr", ADMINISTRADOR));
		assertNotEquals(null, gdc.cadastra("Ingrid", "asdfe", ADMINISTRADOR));
		assertNotEquals(null, gdc.cadastra("Maria", "ththuy", ADMINISTRADOR));
		assertNotEquals(null, gdc.cadastra("Safire", "ahybnui", ADMINISTRADOR));
		assertNotEquals(null, gdc.cadastra("Victor", "oemugnu", GERENTE));

		assertEquals(null, gdc.cadastra("Nara", "afewgs", FUNCIONARIO));
		assertEquals(null, gdc.cadastra("Ingrid", "grhtrd", FUNCIONARIO));
		assertEquals(null, gdc.cadastra("Maria", "aofnhsue", FUNCIONARIO));
		assertEquals(null, gdc.cadastra("Safire", "asdpi", FUNCIONARIO));
		assertEquals(null, gdc.cadastra("Victor", "eqrete", FUNCIONARIO));
		assertEquals(null, gdc.cadastra("Roberto", "htrthr", ADMINISTRADOR));
		assertEquals(null, gdc.cadastra("Paulo", "asdfe", ADMINISTRADOR));
		assertEquals(null, gdc.cadastra("Mateus", "ththuy", ADMINISTRADOR));
		assertEquals(null, gdc.cadastra("Rodrigo", "ahybnui", ADMINISTRADOR));
		assertEquals(null, gdc.cadastra("Rafaela", "oemugnu", GERENTE));

		assertFalse(gdc.vazio());
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

		Conta emanuell = new Conta("Emanuell", "sdfji", FUNCIONARIO);
		Conta jacob = new Conta("Jacob", "feafe", GERENTE);
		Conta abigail = new Conta("Abigail", "gweagg", ADMINISTRADOR);

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
