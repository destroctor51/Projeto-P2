package test;

import hotel.Hospede;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HospedeTest {
	private Hospede hospede;

	@Before
	public void criaObjetos() {
		hospede = new Hospede("Arthur", "99999999", "01234567890", "email@gmail.com", "Campina Grande", "Rua Qualquer, 10");
	}

	@Test
	public void testaCriaHospede() {
		try {
			new Hospede(null, "99999999", "01234567890", "email@gmail.com", "Campina Grande", "Rua Qualquer, 10");
			Assert.fail("Esperava excecao, pois o nome esta null.");
		} catch (IllegalArgumentException e) {}

		try {
			new Hospede("Arthur", null, "01234567890", "email@gmail.com", "Campina Grande", "Rua Qualquer, 10");
			Assert.fail("Esperava excecao, pois o telefone esta null.");
		} catch (IllegalArgumentException e) {}

		try {
			new Hospede("Arthur", "99999999", null, "email@gmail.com", "Campina Grande", "Rua Qualquer, 10");
			Assert.fail("Esperava excecao, pois o cpf esta null.");
		} catch (IllegalArgumentException e) {}

		try {
			new Hospede("Arthur", "99999999", "01234567890", null, "Campina Grande", "Rua Qualquer, 10");
			Assert.fail("Esperava excecao, pois o email esta null.");
		} catch (IllegalArgumentException e) {}

		try {
			new Hospede("Arthur", "99999999", "01234567890", "email@gmail.com", null, "Rua Qualquer, 10");
			Assert.fail("Esperava excecao, pois a cidade esta null.");
		} catch (IllegalArgumentException e) {}

		try {
			new Hospede("Arthur", "99999999", "01234567890", "email@gmail.com", "Campina Grande", null);
			Assert.fail("Esperava excecao, pois o endereco esta null.");
		} catch (IllegalArgumentException e) {}
	}

	@Test
	public void testaSets() {
		Assert.assertFalse(hospede.getContratos().hasNext());

		Assert.assertEquals("99999999", hospede.getTelefone());
		hospede.setTelefone("11111111");
		Assert.assertFalse("99999999".equals(hospede.getTelefone()));
		Assert.assertEquals("11111111", hospede.getTelefone());

		try {
			hospede.setTelefone(null);
			Assert.fail();
		} catch(IllegalArgumentException e) {}

		Assert.assertEquals("email@gmail.com", hospede.getEmail());
		hospede.setEmail("email@hotmail.com");
		Assert.assertFalse("email@gmail.com".equals(hospede.getEmail()));
		Assert.assertEquals("email@hotmail.com", hospede.getEmail());

		try {
			hospede.setEmail(null);
			Assert.fail();
		} catch(IllegalArgumentException e) {}

		Assert.assertEquals("Campina Grande", hospede.getCidade());
		hospede.setCidade("Joao Pessoa");
		Assert.assertFalse("Campina Grande".equals(hospede.getCidade()));
		Assert.assertEquals("Joao Pessoa", hospede.getCidade());

		try {
			hospede.setCidade(null);
			Assert.fail();
		} catch(IllegalArgumentException e) {}

		Assert.assertEquals("Rua Qualquer, 10", hospede.getEndereco());
		hospede.setEndereco("Sem Rua");
		Assert.assertFalse("Rua Qualquer, 10".equals(hospede.getEndereco()));
		Assert.assertEquals("Sem Rua", hospede.getEndereco());

		Assert.assertEquals("Arthur", hospede.getNome());

		try {
			hospede.setEndereco(null);
			Assert.fail();
		} catch(IllegalArgumentException e) {}
	}

	@Test
	public void testaRealizaReserva() throws Exception {
		String cartao1 = "5555666677778884";
		String cartao2 = "1234567890123456";

		try {
			hospede.realizarReserva(null, 100);
			Assert.fail();
		} catch (IllegalArgumentException e) {}

		try {
			hospede.realizarReserva("", 100);
			Assert.fail();
		} catch (IllegalArgumentException e) {}

		try {
			hospede.realizarReserva(cartao1, -100);
			Assert.fail();
		} catch (IllegalArgumentException e) {}

		try {
			hospede.realizarReserva(cartao1, 100);
		} catch (Exception e) {
			Assert.fail("Nao esperava excecao, pois o numero de cartao e valido.");
		}

		try {
			hospede.realizarReserva(cartao2, 100);
			Assert.fail("Esperava excecao, pois o numero de cartao e invalido.");
		} catch (Exception e) {}
	}

	@Test
	public void testaToString() {
		Assert.assertEquals("Arthur - 01234567890", hospede.toString());
	}

	@Test
	public void testaEquals() {
		Hospede hospede1 = new Hospede("Arthur", "99999999", "01234567890", "email@gmail.com", "Campina Grande", "Rua Qualquer, 10");
		Hospede hospede2 = new Hospede("Victor", "88888888", "09876543210", "nada@gmail.com", "Campina Grande", "Rua Qualquer, 10");
		Hospede hospede3 = new Hospede("Victor", "88888888", "01234567890", "nada@gmail.com", "Campina Grande", "Rua Qualquer, 10");

		Assert.assertNotEquals(hospede, null);
		Assert.assertEquals(hospede, hospede1);
		Assert.assertNotEquals(hospede, hospede2);
		Assert.assertEquals(hospede1, hospede3);
		Assert.assertEquals(hospede1.hashCode(), hospede3.hashCode());
	}
}
