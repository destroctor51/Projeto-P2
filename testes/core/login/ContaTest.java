package core.login;

import static core.login.Permissao.ADMINISTRADOR;
import static core.login.Permissao.FUNCIONARIO;
import static core.login.Permissao.GERENTE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class ContaTest {

	@Test
	public void testConstrutor() {
		Conta conta = new Conta("Victor", "segredo", ADMINISTRADOR);
		assertEquals("Victor", conta.toString());

		assertTrue(conta.confereAcesso("Victor", "segredo"));
		assertFalse(conta.confereAcesso("Victor", "senha"));
		assertFalse(conta.confereAcesso("Vitor", "segredo"));
		assertFalse(conta.confereAcesso("Vitor", "senha"));

		assertTrue(conta.possuiPermissao(null));
		assertTrue(conta.possuiPermissao(FUNCIONARIO));
		assertTrue(conta.possuiPermissao(ADMINISTRADOR));
		assertFalse(conta.possuiPermissao(GERENTE));

		try {
			new Conta(null, "segredo", ADMINISTRADOR);
			fail("Esperava excecao, pois paramentros nao podem ser null");
		} catch(IllegalArgumentException e) {}

		try {
			new Conta("Victor", null, ADMINISTRADOR);
			fail("Esperava excecao, pois paramentros nao podem ser null");
		} catch(IllegalArgumentException e) {}

		try {
			new Conta("Victor", "segredo", null);
			fail("Esperava excecao, pois paramentros nao podem ser null");
		} catch(IllegalArgumentException e) {}
	}

	@Test
	public void testHashEquals() {
		Conta conta1 = new Conta("Victor", "segredo", ADMINISTRADOR);
		Conta conta2 = new Conta("Safire", "senha", FUNCIONARIO);
		assertNotEquals(conta1.hashCode(), conta2.hashCode());
		assertNotEquals(conta1, conta2);

		conta2 = new Conta("Safire", "segredo", FUNCIONARIO);
		assertNotEquals(conta1.hashCode(), conta2.hashCode());
		assertNotEquals(conta1, conta2);

		conta2 = new Conta("Safire", "senha", ADMINISTRADOR);
		assertNotEquals(conta1.hashCode(), conta2.hashCode());
		assertNotEquals(conta1, conta2);

		conta2 = new Conta("Safire", "segredo", ADMINISTRADOR);
		assertNotEquals(conta1.hashCode(), conta2.hashCode());
		assertNotEquals(conta1, conta2);

		conta2 = new Conta("Victor", "senha", FUNCIONARIO);
		assertEquals(conta1.hashCode(), conta2.hashCode());
		assertEquals(conta1, conta2);

		assertNotEquals(conta1, null);
	}

	@Test
	public void testSet() {
		Conta conta = new Conta("Victor", "segredo", ADMINISTRADOR);
		assertTrue(conta.confereAcesso("Victor", "segredo"));

		assertFalse(conta.setSenha("senha", "confidencial"));
		assertFalse(conta.setSenha(null, "confidencial"));
		assertTrue(conta.setSenha("segredo", "confidencial"));

		assertFalse(conta.confereAcesso("Victor", "segredo"));
		assertTrue(conta.confereAcesso("Victor", "confidencial"));

		assertEquals(ADMINISTRADOR, conta.getPermissao());
		conta.setPermissao(GERENTE);
		assertEquals(GERENTE, conta.getPermissao());

		try {
			conta.setSenha("confidencial", null);
			fail("Esperava excecao, pois nova senha nao pode ser null");
		} catch(IllegalArgumentException e) {}

		try {
			conta.setNome(null);
			fail("Esperava excecao, pois nome nao pode ser null");
		} catch(IllegalArgumentException e) {}

		try {
			conta.setPermissao(null);
			fail("Esperava excecao, pois permissao nao pode ser null");
		} catch(IllegalArgumentException e) {}

		conta.setNome("Victor Andrade de Almeida");
		assertEquals("Victor Andrade de Almeida", conta.toString());
		assertTrue(conta.confereAcesso("Victor", "confidencial"));
	}
}
