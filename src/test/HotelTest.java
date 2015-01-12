package test;

import hotel.Hotel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import alugaveis.TipoCarro;
import alugaveis.TipoQuarto;


public class HotelTest {
	private Hotel hotel;
	
	@Before
	public void criaObjetos() {
		hotel = new Hotel("Riviera");
	}
	
	@Test
	public void testaCriaHotel() {
		try {
			new Hotel(null);
			Assert.fail("Esperava exceção, pois o nome esta null.");
		} catch (IllegalArgumentException e) {
		}
		
		try {
			new Hotel("");
			Assert.fail("Esperava exceção, pois o nome esta vazio.");
		} catch (IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testaAdicionaeRemoveObjetos() throws Exception {
		Assert.assertTrue(hotel.getCamas().size() == 0);
		
		hotel.adicionaCamaExtra(10394);
		Assert.assertFalse(hotel.getCamas().size() == 0);
		Assert.assertTrue(hotel.getCamas().size() == 1);
		
		hotel.removeCamaExtra(10394);
		Assert.assertFalse(hotel.getCamas().size() == 1);
		Assert.assertTrue(hotel.getCamas().size() == 0);
		
		Assert.assertTrue(hotel.getBabas().size() == 0);
		
		hotel.adicionaBaba("Julia");
		Assert.assertFalse(hotel.getBabas().size() == 0);
		Assert.assertTrue(hotel.getBabas().size() == 1);
		
		hotel.removeBaba("Julia");
		Assert.assertFalse(hotel.getBabas().size() == 1);
		Assert.assertTrue(hotel.getBabas().size() == 0);
		
		Assert.assertTrue(hotel.getHospedes().size() == 0);
		
		try {
			hotel.adicionaHospede("Arthur", "99999999", "12241315311", "email@gmail.com", "Campina Grande", "Rua Qualquer, 10");
			Assert.fail("Esperava Exceção, pois o cpf é inválido.");
		} catch (Exception e) {
			Assert.assertEquals("Mensagem Errada", "Cpf invalido.", e.getMessage());
		}
		
		hotel.adicionaHospede("Arthur", "99999999", "72921211602", "email@gmail.com", "Campina Grande", "Rua Qualquer, 10");
		
		Assert.assertFalse(hotel.getHospedes().size() == 0);
		Assert.assertTrue(hotel.getHospedes().size() == 1);
		
		hotel.removeHospede("72921211602");
		Assert.assertFalse(hotel.getHospedes().size() == 1);
		Assert.assertTrue(hotel.getHospedes().size() == 0);
		
		Assert.assertTrue(hotel.getQuartos().size() == 0);
		
		hotel.adicionaQuarto(TipoQuarto.LUXO_SIMPLES, 100);
		Assert.assertFalse(hotel.getQuartos().size() == 0);
		Assert.assertTrue(hotel.getQuartos().size() == 1);
		
		hotel.removeQuarto(100);
		Assert.assertFalse(hotel.getQuartos().size() == 1);
		Assert.assertTrue(hotel.getQuartos().size() == 0);
		
		Assert.assertTrue(hotel.getCarros().size() == 0);
		
		hotel.adicionaCarro(TipoCarro.LUXO, "ABC4123");
		Assert.assertFalse(hotel.getCarros().size() == 0);
		Assert.assertTrue(hotel.getCarros().size() == 1);
		
		hotel.removeCarro("ABC4123");
		Assert.assertFalse(hotel.getCarros().size() == 1);
		Assert.assertTrue(hotel.getCarros().size() == 0);
		
		Assert.assertTrue(hotel.getRestaurantes().size() == 0);
		
		hotel.adicionaRestaurante("Gourmet");
		Assert.assertFalse(hotel.getRestaurantes().size() == 0);
		Assert.assertTrue(hotel.getRestaurantes().size() == 1);
		
		hotel.removeRestaurante("Gourmet");
		Assert.assertFalse(hotel.getRestaurantes().size() == 1);
		Assert.assertTrue(hotel.getRestaurantes().size() == 0);
		
		Assert.assertTrue(hotel.getTarifas().size() == 0);
		
		hotel.adicionaEstacao(100);
		Assert.assertFalse(hotel.getTarifas().size() == 0);
		Assert.assertTrue(hotel.getTarifas().size() == 1);
		
		hotel.removeEstacao(100);
		Assert.assertFalse(hotel.getTarifas().size() == 1);
		Assert.assertTrue(hotel.getTarifas().size() == 0);
		
		Assert.assertTrue(hotel.getOpinioes().size() == 0);
		
		hotel.adicionaOpiniao("Arthur", 9, "Hotel otimo e atendimento rapido, recomendo.");
		Assert.assertFalse(hotel.getOpinioes().size() == 0);
		Assert.assertTrue(hotel.getOpinioes().size() == 1);		
	}

	@Test
	public void testaToString() {
		Assert.assertEquals("Hotel [Numero de hospedes = 0, numero de quartos = 0, numero de carros = 0," +
							" numero de restaurantes = 0, numero de tarifas = 0, opinioes registradas = 0]", hotel.toString());
	}
	
}
