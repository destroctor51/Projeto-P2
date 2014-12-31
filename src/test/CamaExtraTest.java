package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import servicos.CamaExtra;

public class CamaExtraTest {
	private CamaExtra camaextra1;
	private CamaExtra camaextra2;

	@Before
	public void setUp(){
	camaextra1 = new CamaExtra(10);
	camaextra2 = new CamaExtra(18);
	}
	@Test
	public void testGetPreco(){
	Assert.assertEquals(camaextra1.getPreco(), 300, 0.0005);
	Assert.assertEquals(camaextra2.getPreco(), 540, 0.0005);
	}
}
