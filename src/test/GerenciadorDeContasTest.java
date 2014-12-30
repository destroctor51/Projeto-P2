package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import static login.Permissao.*;
import login.Conta;
import login.GerenciadorDeContas;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import disco.Arquivo;

public class GerenciadorDeContasTest {

	GerenciadorDeContas gdc;
	
	@Before
	public void setUp() {
		gdc = new GerenciadorDeContas("test.dat");
		
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
		
		gdc.salvaContas();
		gdc = new GerenciadorDeContas("test.dat");
	}
	
	@Test
	public void testCadastra() {
		gdc = new GerenciadorDeContas();
		
		assertTrue(gdc.cadastra("Roberto", "afewgs", FUNCIONARIO));
		assertTrue(gdc.cadastra("Paulo", "grhtrd", FUNCIONARIO));
		assertTrue(gdc.cadastra("Mateus", "aofnhsue", FUNCIONARIO));
		assertTrue(gdc.cadastra("Rodrigo", "asdpi", FUNCIONARIO));
		assertTrue(gdc.cadastra("Rafaela", "eqrete", FUNCIONARIO));
		assertTrue(gdc.cadastra("Nara", "htrthr", ADMINISTRADOR));
		assertTrue(gdc.cadastra("Ingrid", "asdfe", ADMINISTRADOR));
		assertTrue(gdc.cadastra("Maria", "ththuy", ADMINISTRADOR));
		assertTrue(gdc.cadastra("Safire", "ahybnui", ADMINISTRADOR));
		assertTrue(gdc.cadastra("Victor", "oemugnu", GERENTE));
		
		assertFalse(gdc.cadastra("Nara", "afewgs", FUNCIONARIO));
		assertFalse(gdc.cadastra("Ingrid", "grhtrd", FUNCIONARIO));
		assertFalse(gdc.cadastra("Maria", "aofnhsue", FUNCIONARIO));
		assertFalse(gdc.cadastra("Safire", "asdpi", FUNCIONARIO));
		assertFalse(gdc.cadastra("Victor", "eqrete", FUNCIONARIO));
		assertFalse(gdc.cadastra("Roberto", "htrthr", ADMINISTRADOR));
		assertFalse(gdc.cadastra("Paulo", "asdfe", ADMINISTRADOR));
		assertFalse(gdc.cadastra("Mateus", "ththuy", ADMINISTRADOR));
		assertFalse(gdc.cadastra("Rodrigo", "ahybnui", ADMINISTRADOR));
		assertFalse(gdc.cadastra("Rafaela", "oemugnu", GERENTE));
		
		assertFalse(gdc.salvaContas());
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
		
		assertFalse(gdc.remove("Emanuell"));
		assertFalse(gdc.remove("Jacob"));
		assertFalse(gdc.remove("Abigail"));
		
		assertTrue(gdc.remove("Roberto"));
		assertTrue(gdc.remove("Rafaela"));
		assertTrue(gdc.remove("Maria"));
		
		assertEquals(null, gdc.login("Roberto", "afewgs"));
		assertEquals(null, gdc.login("Rafaela", "eqrete"));
		assertEquals(null, gdc.login("Maria", "ththuy"));
		
		gdc = new GerenciadorDeContas();
		assertFalse(gdc.remove("Victor"));
		
		try {
			gdc.remove(null);
			fail("Esperava excecao, pois argumento nao pode ser null");
		} catch(IllegalArgumentException e) {}
	}
	
	@Test
	public void testCarregaContas() {
		Arquivo.salvaObjeto(null, "test.dat");
		gdc = new GerenciadorDeContas("test.dat");
		assertTrue(gdc.cadastra("Victor", "senha", GERENTE));
		assertEquals("Victor".hashCode(), gdc.login("Victor", "senha").hashCode());
		
		Set<Object> arquivo = new HashSet<Object>();
		
		Arquivo.salvaObjeto(arquivo, "test.dat");
		gdc = new GerenciadorDeContas("test.dat");
		assertTrue(gdc.cadastra("Emanuell", "senha", ADMINISTRADOR));
		assertEquals("Emanuell".hashCode(), gdc.login("Emanuell", "senha").hashCode());
		
		arquivo.add(new Conta("Safire", "senha", FUNCIONARIO));
		Arquivo.salvaObjeto(arquivo, "test.dat");
		gdc = new GerenciadorDeContas("test.dat");
		assertEquals("Safire".hashCode(), gdc.login("Safire", "senha").hashCode());
		
		arquivo.add("uma string");
		Arquivo.salvaObjeto(arquivo, "test.dat");
		gdc = new GerenciadorDeContas("test.dat");
		assertEquals(null, gdc.login("Safire", "senha"));
	}

	@After
	public void tearDown() {
		Arquivo.deleta("test.dat");
	}
}
