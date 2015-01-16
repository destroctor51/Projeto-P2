package test;

import static login.Permissao.ADMINISTRADOR;
import static login.Permissao.FUNCIONARIO;
import static login.Permissao.GERENTE;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

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
		
		assertFalse(gdc.salvaContas());
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
		assertNotEquals(null, gdc.cadastra("Victor", "senha", GERENTE));
		assertEquals("Victor".hashCode(), gdc.login("Victor", "senha").hashCode());
		
		Set<Object> arquivo = new HashSet<Object>();
		
		Arquivo.salvaObjeto(arquivo, "test.dat");
		gdc = new GerenciadorDeContas("test.dat");
		assertNotEquals(null, gdc.cadastra("Emanuell", "senha", ADMINISTRADOR));
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
