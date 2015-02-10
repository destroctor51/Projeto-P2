package core.eventos;

import java.awt.Component;
import java.awt.Container;
import java.util.EventObject;

/**
 * Uma classe que retrata a natureza de uma mudanca no modo de visualizacao do programa.
 *
 * @author Victor Andrade de Almeida
 */
public class FullscreenEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	private Component source;
	private boolean fullscreen;

	/**
	 * Cria um evento com base na mudanca ocorrida.
	 *
	 * @param source  o objeto que criou o evento
	 * @param fullscreen  true se a tela passou a ser exibida em tela cheia, false caso contrario
	 */
	public FullscreenEvent(Component source, boolean fullscreen) {
		super(source);
		this.source = source;
		this.fullscreen = fullscreen;
	}

	/**
	 * Recupera a mudanca sofrida pela tela.
	 *
	 * @return true se a tela passou a ser exibida em tela cheia, false caso contrario
	 */
	public boolean isFullscreen() {
		return fullscreen;
	}

	/**
	 * Repassa o evento para o componente da interface grafica que o criou e para todos seus subcomponentes.
	 * <p>
	 * O evento so sera processado pelos componentes que implementarem FullscreenListener
	 */
	public void sendDown() {
		sendTo(source);
	}

	private void sendTo(Component component) {
		if(component instanceof FullscreenListener)
			((FullscreenListener) component).fullscreenChanged(this);

		if(component instanceof Container) {
			Container container = (Container) component;
			for(Component child : container.getComponents())
				sendTo(child);
		}
	}
}
