package utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 * Algoritmos de busca e filtragem.
 *
 * @author Victor Andrade de Almeida
 */
public class Filtro {

	private Filtro() {}

	/**
	 * Filtra elementos que contem a string dada em seu toString.
	 *
	 * @param string  string a ser buscada
	 * @param elementos  conjunto universo da busca
	 * @return uma lista com os elementos que satisfazem a busca
	 */
	public static <T> List<T> busca(String string, Iterable<T> elementos) {
		List<T> resultado = new LinkedList<>();

		for(T elemento : elementos)
			if(elemento.toString().toLowerCase().contains(string.toLowerCase()))
				resultado.add(elemento);

		return resultado;
	}

	/**
	 * Preenche uma JList com os elementos dados.
	 *
	 * @param elementos  elementos a serem adicionados
	 * @param lista  a JList alvo
	 */
	public static <T> void preencheJList(Iterable<T> elementos, JList<T> lista) {
		DefaultListModel<T> dlm = new DefaultListModel<>();
		for(T e : elementos) dlm.addElement(e);
		lista.setModel(dlm);
	}

	/**
	 * Ordena uma lista pelo toString de seus elementos.
	 *
	 * @param lista  a lista a ser ordenada
	 */
	public static void ordenaToString(List<?> lista) {
		Collections.sort(lista, new Comparator<Object>() {
			@Override
			public int compare(Object a, Object b) {
				return a.toString().toLowerCase().compareTo(b.toString().toLowerCase());
			}
		});
	}

	/**
	 * Filtra, ordena pelo toString e exibe em uma JList elementos que contem a string dada em seu toString.
	 *
	 * @param string  string a ser buscada
	 * @param elementos  conjunto universo da busca
	 * @param lista  a JList em que sera exibido o resultado
	 */
	public static <T> void exibeFiltrado(String string, Iterable<T> elementos, JList<T> lista) {
		List<T> resultado = busca(string, elementos);
		ordenaToString(resultado);
		preencheJList(resultado, lista);
	}
}
