package utils;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Test;

import utils.Arquivo;

public class ArquivoTest {

	@Test
	public void test() {
		Random rand = new Random();
		List<Integer> original = new ArrayList<>();
		for(int i=0; i<100; i++) original.add(new Integer(rand.nextInt()));
		assertTrue(Arquivo.salvaObjeto(original, "test.dat"));
		
		@SuppressWarnings("unchecked")
		List<Integer> leitura = (List<Integer>) Arquivo.carregaObjeto("test.dat");
		assertEquals(original, leitura);
	}
	
	@After
	public void tearDown() {
		assertTrue(Arquivo.deleta("test.dat"));
		assertFalse(Arquivo.deleta("test.dat"));
		
		File f = new File("test.dat");
		assertFalse(f.exists());
	}
}
