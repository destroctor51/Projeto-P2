package test;

import hotel.Hospede;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import servicos.devolviveis.Quarto;
import servicos.devolviveis.TipoQuarto;
import tempo.Periodo;

public class HospedeTest {
	private Hospede hospede;
	
	@Before
	public void criaObjetos() throws Exception {
		hospede = new Hospede("Arthur", "99999999", "01234567890", "email@gmail.com", "Campina Grande", "Rua Qualquer, 10");
	}
	
	@Test
	public void testaCriaHospede() {
		try {
			new Hospede(null, "99999999", "01234567890", "email@gmail.com", "Campina Grande", "Rua Qualquer, 10");
			Assert.fail("Esperava exceção, pois o nome esta null.");
		} catch (IllegalArgumentException e) {
		}
		
		try {
			new Hospede("Arthur", null, "01234567890", "email@gmail.com", "Campina Grande", "Rua Qualquer, 10");
			Assert.fail("Esperava exceção, pois o telefone esta null.");
		} catch (IllegalArgumentException e) {
		}
		
		try {
			new Hospede("Arthur", "99999999", null, "email@gmail.com", "Campina Grande", "Rua Qualquer, 10");
			Assert.fail("Esperava exceção, pois o cpf esta null.");
		} catch (IllegalArgumentException e) {
		}
		
		try {
			new Hospede("Arthur", "99999999", "01234567890", null, "Campina Grande", "Rua Qualquer, 10");
			Assert.fail("Esperava exceção, pois o email esta null.");
		} catch (IllegalArgumentException e) {
		}
		
		try {
			new Hospede("Arthur", "99999999", "01234567890", "email@gmail.com", null, "Rua Qualquer, 10");
			Assert.fail("Esperava exceção, pois a cidade esta null.");
		} catch (IllegalArgumentException e) {
		}
		
		try {
			new Hospede("Arthur", "99999999", "01234567890", "email@gmail.com", "Campina Grande", null);
			Assert.fail("Esperava exceção, pois o endereco esta null.");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void testaSets() {
		Assert.assertEquals("99999999", hospede.getTelefone());
		hospede.setTelefone("11111111");
		Assert.assertFalse("99999999".equals(hospede.getTelefone()));
		Assert.assertEquals("11111111", hospede.getTelefone());
		
		Assert.assertEquals("email@gmail.com", hospede.getEmail());
		hospede.setEmail("email@hotmail.com");
		Assert.assertFalse("email@gmail.com".equals(hospede.getEmail()));
		Assert.assertEquals("email@hotmail.com", hospede.getEmail());
		
		Assert.assertEquals("Campina Grande", hospede.getCidade());
		hospede.setCidade("Joao Pessoa");
		Assert.assertFalse("Campina Grande".equals(hospede.getCidade()));
		Assert.assertEquals("Joao Pessoa", hospede.getCidade());
		
		Assert.assertEquals("Rua Qualquer, 10", hospede.getEndereco());
		hospede.setEndereco("Sem Rua");
		Assert.assertFalse("Rua Qualquer, 10".equals(hospede.getEndereco()));
		Assert.assertEquals("Sem Rua", hospede.getEndereco());
	}
	
	@Test
	public void testaRealizaReserva() throws Exception {
		String cartao1 = "5555666677778884";
		String cartao2 = "1234567890123456";
		Calendar c1 = new GregorianCalendar(2014,1,28);
		Calendar c2 = new GregorianCalendar(2014,1,30);
		Periodo p1 = new Periodo(c1,c2);
		Quarto quarto1 = new Quarto(TipoQuarto.LUXO_SIMPLES,101);
		Quarto quarto2 = new Quarto(TipoQuarto.LUXO_DUPLO,102);
		Quarto quarto3 = new Quarto(TipoQuarto.LUXO_TRIPLO,103);
		List<Quarto> quartos = new ArrayList<>();
		quartos.add(quarto1);
		quartos.add(quarto2);
		quartos.add(quarto3);
		
		try {
			hospede.realizarReserva(cartao1, 100, p1, quartos);
		} catch (Exception e) {
			Assert.fail("Não esperava exceção, pois o numero de cartao é valido.");
		}
		
		try {
			hospede.realizarReserva(cartao2, 100, p1, quartos);
			Assert.fail("Esperava exceção, pois o numero de cartao é invalido.");
		} catch (Exception e) {
		}		
	}
	
	@Test
	public void testaToString() {
		Assert.assertEquals("Hospede [Nome = Arthur, telefone = 99999999, cpf = 01234567890" +
		", email = email@gmail.com, cidade = Campina Grande, endereco = Rua Qualquer, 10]", hospede.toString());
	}
	
	@Test
	public void testaEquals() {
		Hospede hospede1 = new Hospede("Arthur", "99999999", "01234567890", "email@gmail.com", "Campina Grande", "Rua Qualquer, 10");
		Hospede hospede2 = new Hospede("Victor", "88888888", "09876543210", "nada@gmail.com", "Campina Grande", "Rua Qualquer, 10");
		
		Assert.assertTrue(hospede.equals(hospede1));
		Assert.assertFalse(hospede.equals(hospede2));
	}
}
