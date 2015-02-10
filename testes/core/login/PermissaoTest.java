package core.login;

import static org.junit.Assert.*;

import org.junit.Test;

import core.login.Permissao;

public class PermissaoTest {

	@Test
	public void test() {
		assertEquals(0 , Permissao.valueOf("FUNCIONARIO").ordinal());
		assertEquals(1 , Permissao.valueOf("ADMINISTRADOR").ordinal());
		assertEquals(2 , Permissao.valueOf("GERENTE").ordinal());
	}
}
