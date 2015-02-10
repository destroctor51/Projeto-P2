package core.eventos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Button;

import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

import core.eventos.FullscreenEvent;
import core.eventos.FullscreenListener;

public class FullscreenTest {

	FPanel pane1, pane2, pane3;
	JPanel pane4, pane5, pane6;
	Button component;
	FullscreenEvent e1, e2, e3;

	@Before
	public void setUp() {
		pane1 = new FPanel();
		pane2 = new FPanel();
		pane3 = new FPanel();

		pane4 = new JPanel();
		pane5 = new JPanel();
		pane6 = new JPanel();

		component = new Button();

		pane1.add(pane2);
		pane1.add(pane4);
		pane2.add(pane5);
		pane4.add(pane6);
		pane6.add(pane3);
		pane5.add(component);

		e1 = new FullscreenEvent(pane1, true);
		e2 = new FullscreenEvent(pane2, false);
		e3 = new FullscreenEvent(pane3, true);
	}

	@Test
	public void testSetUp() {
		assertFalse(pane1.getValue());
		assertFalse(pane2.getValue());
		assertFalse(pane3.getValue());

		assertTrue(e1.isFullscreen());
		assertFalse(e2.isFullscreen());
		assertTrue(e3.isFullscreen());
	}

	@Test
	public void testConstructor() {
		try {
			new FullscreenEvent(null, true);
			fail();
		} catch(IllegalArgumentException e) {}
	}

	@Test
	public void testSend() {
		e1.sendDown();
		assertTrue(pane1.getValue());
		assertTrue(pane2.getValue());
		assertTrue(pane3.getValue());

		e2.sendDown();
		assertTrue(pane1.getValue());
		assertFalse(pane2.getValue());
		assertTrue(pane3.getValue());

		e3.sendDown();
		assertTrue(pane1.getValue());
		assertFalse(pane2.getValue());
		assertTrue(pane3.getValue());
	}

	private class FPanel extends JPanel implements FullscreenListener {

		private static final long serialVersionUID = 1L;
		private boolean value = false;

		@Override
		public void fullscreenChanged(FullscreenEvent e) {
			value = e.isFullscreen();
		}

		public boolean getValue() {
			return value;
		}
	}
}
