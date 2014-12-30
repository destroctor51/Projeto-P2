package test;

import static org.junit.Assert.*;
import static login.Permissao.*;
import login.Conta;

import org.junit.Test;

public class ContaTest {

	@Test
	public void testConstrutor() {
		Conta conta = new Conta("Victor", "segredo", ADMINISTRADOR);
		
		assertTrue(conta.confereAcesso("Victor", "segredo"));
		assertFalse(conta.confereAcesso("Victor", "senha"));
		assertFalse(conta.confereAcesso("Vitor", "segredo"));
		assertFalse(conta.confereAcesso("Vitor", "senha"));
		
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
	public void testSetSenha() {
		Conta conta = new Conta("Victor", "segredo", ADMINISTRADOR);
		assertTrue(conta.confereAcesso("Victor", "segredo"));
		
		assertFalse(conta.setSenha("senha", "confidencial"));
		assertFalse(conta.setSenha(null, "confidencial"));
		assertTrue(conta.setSenha("segredo", "confidencial"));
		
		assertFalse(conta.confereAcesso("Victor", "segredo"));
		assertTrue(conta.confereAcesso("Victor", "confidencial"));
		
		try {
			conta.setSenha("confidencial", null);
			fail("Esperava excecao, pois nova senha nao pode ser null");
		} catch(IllegalArgumentException e) {}
	}
}
