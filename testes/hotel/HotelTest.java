package hotel;

import hotel.Hospede;
import hotel.Hotel;
import hotel.Restaurante;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import servicos.alugaveis.Babysitter;
import servicos.alugaveis.CamaExtra;
import servicos.devolviveis.Carro;
import servicos.devolviveis.Quarto;
import servicos.devolviveis.TipoCarro;
import servicos.devolviveis.TipoQuarto;
import tempo.Estacao;
import tempo.Periodo;


public class HotelTest {

	private Hotel hotel;
	private Calendar c1, c2, c3, c4, c5;
	private Periodo p1, p2, p3;

	@Before
	public void setUp() {
		hotel = new Hotel("Riviera");

		c1 = new GregorianCalendar(2015, 0, 2);
		c2 = new GregorianCalendar(2015, 0, 15);
		c3 = new GregorianCalendar(2015, 1, 7);
		c4 = new GregorianCalendar(2015, 1, 22);
		c5 = new GregorianCalendar(2015, 2, 26);

		p1 = new Periodo(c1, c2);
		p2 = new Periodo(c3, c4);
		p3 = new Periodo(c3, c5);
	}

	@Test
	public void testaCriaHotel() {
		try {
			new Hotel(null);
			Assert.fail("Esperava excecao, pois o nome esta null");
		} catch (IllegalArgumentException e) {}

		try {
			new Hotel("");
			Assert.fail("Esperava excecao, pois o nome esta vazio");
		} catch (IllegalArgumentException e) {}

		Assert.assertEquals(0, hotel.getCamas().size());
		Assert.assertEquals(0, hotel.getBabas().size());
		Assert.assertEquals(0, hotel.getHospedes().size());
		Assert.assertEquals(85, hotel.getQuartos().size());
		Assert.assertEquals(0, hotel.getCarros().size());
		Assert.assertEquals("Riviera", hotel.getNome());
		Assert.assertFalse(hotel.getTarifas().hasNext());
	}

	@Test
	public void testaAdicionaObjetos() throws Exception {

		// CAMA EXTRA

		hotel.adicionaCamaExtra(10394);
		Assert.assertEquals(1, hotel.getCamas().size());
		hotel.adicionaCamaExtra(10395);

		hotel.getCamas().get(0).aluga(p1);
		hotel.getCamas().get(1).aluga(p2);
		Assert.assertEquals(1, hotel.getCamasDisponiveis(p3).size());
		Assert.assertEquals(10394, hotel.getCamasDisponiveis(p3).get(0).getCodigo());

		Assert.assertTrue(hotel.removeCamaExtra(new CamaExtra(10394)));
		Assert.assertFalse(hotel.removeCamaExtra(new CamaExtra(10394)));
		Assert.assertEquals(1, hotel.getCamas().size());
		hotel.adicionaCamaExtra(10394);

		try {
			hotel.adicionaCamaExtra(10394);
			Assert.fail();
		} catch(Exception e) {}

		// BABA

		hotel.adicionaBaba("Julia");
		Assert.assertEquals(1, hotel.getBabas().size());
		hotel.adicionaBaba("Roberta");

		Assert.assertTrue(hotel.removeBaba(new Babysitter("Julia")));
		Assert.assertFalse(hotel.removeBaba(new Babysitter("Julia")));
		Assert.assertEquals(1, hotel.getBabas().size());
		hotel.adicionaBaba("Julia");

		try {
			hotel.adicionaBaba("Julia");
			Assert.fail();
		} catch(Exception e) {}

		// HOSPEDE

		try {
			hotel.adicionaHospede("Arthur", "99999999", "12241315311", "email@gmail.com", "Campina Grande", "Rua Qualquer, 10");
			Assert.fail("Esperava Excecao, pois o cpf e invalido");
		} catch (Exception e) {
			Assert.assertEquals("Mensagem Errada", "Cpf invalido", e.getMessage());
		}

		hotel.adicionaHospede("Arthur", "99999999", "72921211602", "email@gmail.com", "Campina Grande", "Rua Qualquer, 10");
		hotel.adicionaHospede("Arthur", "88888888", "01234567890", "email@hotmail.com", "Campina Grande", "Rua Qualquer, 10");

		try {
			hotel.adicionaHospede("Arthur", "99999999", "01234567890", "email@hotmail.com", "Campina Grande", "Rua Qualquer, 10");
			Assert.fail();
		} catch (Exception e) {}

		try {
			hotel.adicionaHospede("Arthur", "88888888", "01234567890", "email@gmail.com", "Campina Grande", "Rua Qualquer, 10");
			Assert.fail();
		} catch (Exception e) {}

		try {
			hotel.adicionaHospede("Arthur", "88888888", "72921211602", "email@hotmail.com", "Campina Grande", "Rua Qualquer, 10");
			Assert.fail();
		} catch (Exception e) {}

		Assert.assertEquals(2, hotel.getHospedes().size());

		hotel.removeHospede(new Hospede("Arthur", "99999999", "72921211602", "email@gmail.com", "Campina Grande", "Rua Qualquer, 10"));
		Assert.assertEquals(1, hotel.getHospedes().size());

		// QUARTO

		hotel.adicionaQuarto(TipoQuarto.LUXO_SIMPLES, 1000);
		Assert.assertEquals(86, hotel.getQuartos().size());
		hotel.adicionaQuarto(TipoQuarto.LUXO_SIMPLES, 1001);

		hotel.getQuartos().get(0).aluga(p1);
		hotel.getQuartos().get(1).aluga(p2);
		Assert.assertEquals(86, hotel.getQuartosDisponiveis(p3).size());

		Assert.assertTrue(hotel.removeQuarto(new Quarto(TipoQuarto.LUXO_SIMPLES, 1000)));
		Assert.assertFalse(hotel.removeQuarto(new Quarto(TipoQuarto.LUXO_SIMPLES, 1000)));
		Assert.assertEquals(86, hotel.getQuartos().size());

		try {
			hotel.adicionaQuarto(TipoQuarto.LUXO_SIMPLES, 1001);
			Assert.fail();
		} catch(Exception e) {}

		// CARRO

		hotel.adicionaCarro(TipoCarro.LUXO, "ABC4123");
		Assert.assertEquals(1, hotel.getCarros().size());
		hotel.adicionaCarro(TipoCarro.EXECUTIVO, "MOR2933");

		hotel.getCarros().get(0).aluga(p1);
		hotel.getCarros().get(1).aluga(p2);
		Assert.assertEquals(1, hotel.getCarrosDisponiveis(p3).size());
		Assert.assertEquals("ABC4123", hotel.getCarrosDisponiveis(p3).get(0).getPlaca());

		Assert.assertTrue(hotel.removeCarro(new Carro(TipoCarro.LUXO, "ABC4123")));
		Assert.assertEquals(1, hotel.getCarros().size());

		try {
			hotel.adicionaCarro(TipoCarro.LUXO, "MOR2933");
			Assert.fail();
		} catch(Exception e) {}

		try {
			hotel.adicionaCarro(TipoCarro.LUXO, "4123ABC");
			Assert.fail();
		} catch(Exception e) {}

		// RESTAURANTE

		Assert.assertEquals(0, hotel.getRestaurantes().size());

		hotel.adicionaRestaurante("Gourmet");
		Assert.assertEquals(1, hotel.getRestaurantes().size());
		hotel.adicionaRestaurante("Guaiamum");

		hotel.removeRestaurante(new Restaurante("Gourmet"));
		Assert.assertEquals(1, hotel.getRestaurantes().size());

		try {
			hotel.adicionaRestaurante("Guaiamum");
			Assert.fail();
		} catch(Exception e) {}

		// ESTACAO

		Estacao estacao = new Estacao("Sao Joao", 100);
		Calendar c1 = new GregorianCalendar(2014,1,28);
		Calendar c2 = new GregorianCalendar(2014,1,30);
		Periodo periodo = new Periodo(c1, c2);
		estacao.addPeriodo(periodo);

		Estacao estacao2 = new Estacao("Natal", 100);
		hotel.adicionaEstacao(estacao2);
		hotel.adicionaEstacao(estacao);
		Assert.assertTrue(hotel.getTarifas().hasNext());

		Calendar c3 = new GregorianCalendar(2014,2,1);
		Calendar c4 = new GregorianCalendar(2014,2,15);
		Periodo periodo2 = new Periodo(c3, c4);
		Assert.assertEquals(estacao, hotel.procuraEstacao(periodo2));

		hotel.removeEstacao(estacao);
		hotel.removeEstacao(estacao2);
		Assert.assertFalse(hotel.getTarifas().hasNext());

		try {
			hotel.adicionaEstacao(null);
			Assert.fail();
		} catch(IllegalArgumentException e) {}

		Assert.assertEquals(null, hotel.procuraEstacao(periodo2));

		// OPINIAO

		Assert.assertEquals(0, hotel.getOpinioes().size());

		hotel.adicionaOpiniao("Arthur", 9, "Hotel otimo e atendimento rapido, recomendo", new GregorianCalendar());
		hotel.adicionaOpiniao(9, "Hotel otimo e atendimento rapido, recomendo", new GregorianCalendar());
		Assert.assertEquals(2, hotel.getOpinioes().size());
	}

	@Test
	public void testaToString() {
		Assert.assertEquals("Riviera", hotel.toString());
	}

}