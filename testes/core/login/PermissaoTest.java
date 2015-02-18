package core.login;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PermissaoTest {

	@Test
	public void test() {
		assertEquals(0 , Permissao.valueOf("FUNCIONARIO").ordinal());
		assertEquals(1 , Permissao.valueOf("ADMINISTRADOR").ordinal());
		assertEquals(2 , Permissao.valueOf("GERENTE").ordinal());

		assertEquals("Funcion\u00E1rio", Permissao.FUNCIONARIO.toString());
		assertEquals("Administrador", Permissao.ADMINISTRADOR.toString());
		assertEquals("Gerente", Permissao.GERENTE.toString());
	}
}
