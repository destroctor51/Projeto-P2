package gui.components;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import core.interfaces.Alugavel;
import core.tempo.Periodo;

/**
 * Um compontente Swing capaz mostrar interativamente os periodos em que e possivel realizar um aluguel.
 *
 * @author Victor Andrade de Almeida
 */
public class CalendarioAlugavel extends Calendario {
	private static final long serialVersionUID = 1L;

	private Map<Object, Requisito> categorias;

	/**
	 * Cria um novo CalendarioAlugavel default.
	 */
	public CalendarioAlugavel() {
		categorias = new HashMap<>();
	}

	/**
	 * Cria uma categoria de alugaveis, determinando o numero minimo de elementos necessarios para realizar um aluguel.
	 *
	 * @param id  uma identificacao para a categoria
	 * @param min  o numero minimo de alugaveis disponiveis necessario
	 */
	public void initCategoria(Object id, int min) {
		categorias.put(id, new Requisito(min));
		atualizaDias();
	}

	/**
	 * Remove uma categoria, juntamente com todas suas informacoes.
	 *
	 * @param id  a identificacao da categoria em questao
	 * @return true caso o elemento seja removido, false caso contrario
	 */
	public boolean removeCategoria(Object id) {
		if(!categorias.containsKey(id))
			return false;
		categorias.remove(id);
		atualizaDias();
		return true;
	}

	/**
	 * Define o numero minimo de elementos necessarios para realizar um aluguel, para uma categoria.
	 *
	 * @param id  a identificacao da categoria em questao
	 * @param min  o numero minimo de alugaveis disponiveis necessario
	 * @return true caso a operacao ocorra com sucesso, false caso contrario
	 */
	public boolean setRequisito(Object id, int min) {
		if(!categorias.containsKey(id))
			return false;
		categorias.get(id).setMinimo(min);
		atualizaDias();
		return true;
	}

	/**
	 * Adiciona um alugavel a categoria.
	 *
	 * @param id  a identificacao da categoria em questao
	 * @param elemento  o elemento a ser adicionado
	 */
	public void adicionaElemento(Object id, Alugavel elemento) {
		categorias.get(id).adicionaElemento(elemento);
		atualizaDias();
	}

	@Override
	protected boolean isDiaIndisponivel(Calendar dia) {
		if(categorias == null)
			return false;
		for(Requisito r : categorias.values())
			if(!r.isDisponivel(dia))
				return true;
		return false;
	}

	@Override
	protected boolean isSelecaoInvalida() {
		if(categorias == null)
			return false;
		for(Requisito r : categorias.values())
			if(!r.isDisponivel(getSelecao()))
				return true;
		return false;
	}

	private class Requisito {

		private int minimo;
		private List<Alugavel> lista;

		public Requisito(int minimo) {
			this.minimo = minimo;
			this.lista = new LinkedList<>();
		}

		public void setMinimo(int minimo) {
			this.minimo = minimo;
		}

		public void adicionaElemento(Alugavel elemento) {
			lista.add(elemento);
		}

		public boolean isDisponivel(Calendar data) {
			int cont = 0;

			for(Alugavel alugavel : lista) {
				boolean disponivel = true;
				for(Periodo periodo : alugavel.getHistorico())
					if(periodo.contem(data))
						disponivel = false;
				if(disponivel) cont++;
			}

			return cont >= minimo;
		}

		public boolean isDisponivel(Periodo periodo) {
			int cont = 0;

			for(Alugavel alugavel : lista) {
				boolean disponivel = true;
				for(Periodo p : alugavel.getHistorico())
					if(p.entraEmConflito(periodo))
						disponivel = false;
				if(disponivel) cont++;
			}

			return cont >= minimo;
		}
	}
}
