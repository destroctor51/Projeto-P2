package core.hotel;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import core.hotel.Contrato;
import core.hotel.EstadoDeContrato;
import core.interfaces.Pagavel;
import core.servicos.alugaveis.CamaExtra;
import core.servicos.devolviveis.Quarto;
import core.servicos.devolviveis.TipoQuarto;
import core.servicos.pagaveis.Refeicao;
import core.tempo.Periodo;

public class ContratoTest {
	private String cartao = "5555666677778884";
	private Contrato contrato;

	@Before
	public void setUp() {
		contrato = new Contrato(cartao, 100);
	}

	@Test
	public void testaCriaContrato() {
		try {
			new Contrato(null, 100);
			Assert.fail();
		} catch (IllegalArgumentException e) {}

		try {
			new Contrato(cartao, -1);
			Assert.fail();
		} catch (IllegalArgumentException e) {}

		Assert.assertEquals(cartao, contrato.getCartao());
		Assert.assertEquals(100, contrato.getTarifa(), 0.01);
		Assert.assertEquals(EstadoDeContrato.valueOf("PENDENTE"), contrato.getEstado());
	}

	@Test
	public void testaAdicionaServico() {
		Assert.assertEquals(0, contrato.getServicos().size());
		Assert.assertNotEquals(1, contrato.getServicos().size());

		CamaExtra cama = new CamaExtra(10394);
		Assert.assertFalse(contrato.adicionaServico(null));

		Assert.assertTrue(contrato.adicionaServico(cama));
		Assert.assertNotEquals(0, contrato.getServicos().size());
		Assert.assertEquals(1, contrato.getServicos().size());

		Assert.assertTrue(contrato.removeServico(cama));
		Assert.assertEquals(0, contrato.getServicos().size());
		Assert.assertNotEquals(1, contrato.getServicos().size());

		Assert.assertTrue(contrato.realizarCheckIn(cartao));
		Assert.assertTrue(contrato.realizarCheckOut(cartao, new GregorianCalendar()));
		Assert.assertFalse(contrato.adicionaServico(cama));
		Assert.assertFalse(contrato.removeServico(cama));
	}

	@Test
	public void testaAdicionaServicos() {
		Quarto quarto = new Quarto(TipoQuarto.PRESIDENCIAL, 102);
		CamaExtra cama = new CamaExtra(39);
		Refeicao comida = new Refeicao("barata frita", 12.25f);

		List<Pagavel> colecao = new ArrayList<>();
		Assert.assertFalse(contrato.adicionaServicos(null));
		Assert.assertFalse(contrato.adicionaServicos(colecao));

		colecao.add(null);
		Assert.assertFalse(contrato.adicionaServicos(colecao));

		colecao.add(quarto);
		colecao.add(cama);
		colecao.add(comida);
		Assert.assertTrue(contrato.adicionaServicos(colecao));

		Assert.assertEquals(3, contrato.getServicos().size());

		colecao.clear();
		Assert.assertFalse(contrato.removeServicos(null));
		Assert.assertFalse(contrato.removeServicos(colecao));

		colecao.add(null);
		colecao.add(quarto);
		Assert.assertTrue(contrato.removeServicos(colecao));
		Assert.assertEquals(2, contrato.getServicos().size());
	}

	@Test
	public void testaCheckInOut() {
		Assert.assertEquals(contrato.getEstado(), EstadoDeContrato.PENDENTE);
		Assert.assertFalse(contrato.realizarCheckOut(cartao, new GregorianCalendar()));

		Assert.assertFalse(contrato.realizarCheckIn(null));
		Assert.assertTrue(contrato.realizarCheckIn(cartao));
		Assert.assertEquals(contrato.getEstado(), EstadoDeContrato.ABERTO);
		Assert.assertFalse(contrato.realizarCheckIn(cartao));

		Quarto quarto = new Quarto(TipoQuarto.EXECUTIVO_SIMPLES, 102);
		quarto.aluga(new Periodo(new GregorianCalendar(2015, 0, 15), new GregorianCalendar(2015, 0, 25)));
		contrato.adicionaServico(new Refeicao("barata frita", 12.25f));
		contrato.adicionaServico(quarto);

		try {
			Assert.assertFalse(contrato.realizarCheckOut(null, new GregorianCalendar()));
			Assert.fail();
		} catch(IllegalArgumentException iae) {}

		try {
			Assert.assertFalse(contrato.realizarCheckOut(cartao, null));
			Assert.fail();
		} catch(IllegalArgumentException iae) {}

		Assert.assertEquals(null, contrato.getDataCheckOut());
		Assert.assertFalse(contrato.realizarCheckOut(cartao, new GregorianCalendar(2015, 2, 23)));

		((Quarto) contrato.getServicos().get(1)).devolve(new GregorianCalendar(2015, 0, 25));
		Assert.assertFalse(contrato.realizarCheckOut("salsa", new GregorianCalendar(2015, 2, 23)));

		Assert.assertTrue(contrato.realizarCheckOut(cartao, new GregorianCalendar(2015, 2, 23)));
		Assert.assertEquals(new GregorianCalendar(2015, 2, 23), contrato.getDataCheckOut());
	}

	@Test
	public void testaToString() {
		Assert.assertEquals("Contrato PENDENTE, com inicio em 'indisponível' e sem quartos", contrato.toString());
	}

}
