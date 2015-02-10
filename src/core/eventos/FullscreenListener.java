package core.eventos;

import java.util.EventListener;

/**
 * Uma interface que ajuda na deteccao de mudancas no modo de visualizacao do programa.
 * 
 * @author Victor Andrade de Almeida
 */
public interface FullscreenListener extends EventListener {
	
	/**
	 * Informa ao objeto que houve uma mudanca no modo de visualizacao do programa.
	 * 
	 * @param e  um objeto que retrata a natureza da mudanca
	 */
	public void fullscreenChanged(FullscreenEvent e);
}
