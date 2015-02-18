package utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import org.junit.Before;
import org.junit.Test;

public class FiltroTest {

	List<String> lista;

	@Before
	public void setUp() {
		lista = new ArrayList<String>();

		lista.add("telEvisAo");
		lista.add("eXploSao");
		lista.add("MacaCao");
		lista.add("maniveLA");
		lista.add("aMAdor");
		lista.add("estRogOnofe");
		lista.add("eStroGenio");
		lista.add("mestre YoDa");
		lista.add("merenGUe");
	}

	@Test
	public void testBusca() {
		List<String> resultado;

		resultado = Filtro.busca("est", lista);
		assertTrue(resultado.contains("eStroGenio"));
		assertTrue(resultado.contains("estRogOnofe"));
		assertTrue(resultado.contains("mestre YoDa"));
		assertEquals(3, resultado.size());

		resultado = Filtro.busca("en", lista);
		assertTrue(resultado.contains("eStroGenio"));
		assertTrue(resultado.contains("merenGUe"));
		assertEquals(2, resultado.size());

		resultado = Filtro.busca("sao", lista);
		assertTrue(resultado.contains("eXploSao"));
		assertTrue(resultado.contains("telEvisAo"));
		assertEquals(2, resultado.size());

		resultado = Filtro.busca("ao", lista);
		assertTrue(resultado.contains("eXploSao"));
		assertTrue(resultado.contains("MacaCao"));
		assertTrue(resultado.contains("telEvisAo"));
		assertEquals(3, resultado.size());

		resultado = Filtro.busca("ma", lista);
		assertTrue(resultado.contains("aMAdor"));
		assertTrue(resultado.contains("MacaCao"));
		assertTrue(resultado.contains("maniveLA"));
		assertEquals(3, resultado.size());
	}

	@Test
	public void testBuscaList() {
		JList<String> jlista = new JList<>();
		DefaultListModel<String> dlm;

		Filtro.exibeFiltrado("est", lista, jlista);
		dlm = (DefaultListModel<String>) jlista.getModel();
		assertEquals("eStroGenio", dlm.get(0));
		assertEquals("estRogOnofe", dlm.get(1));
		assertEquals("mestre YoDa", dlm.get(2));
		assertEquals(3, dlm.size());

		Filtro.exibeFiltrado("en", lista, jlista);
		dlm = (DefaultListModel<String>) jlista.getModel();
		assertEquals("eStroGenio", dlm.get(0));
		assertEquals("merenGUe", dlm.get(1));
		assertEquals(2, dlm.size());

		Filtro.exibeFiltrado("sao", lista, jlista);
		dlm = (DefaultListModel<String>) jlista.getModel();
		assertEquals("eXploSao", dlm.get(0));
		assertEquals("telEvisAo", dlm.get(1));
		assertEquals(2, dlm.size());

		Filtro.exibeFiltrado("ao", lista, jlista);
		dlm = (DefaultListModel<String>) jlista.getModel();
		assertEquals("eXploSao", dlm.get(0));
		assertEquals("MacaCao", dlm.get(1));
		assertEquals("telEvisAo", dlm.get(2));
		assertEquals(3, dlm.size());

		Filtro.exibeFiltrado("ma", lista, jlista);
		dlm = (DefaultListModel<String>) jlista.getModel();
		assertEquals("aMAdor", dlm.get(0));
		assertEquals("MacaCao", dlm.get(1));
		assertEquals("maniveLA", dlm.get(2));
		assertEquals(3, dlm.size());
	}
}
