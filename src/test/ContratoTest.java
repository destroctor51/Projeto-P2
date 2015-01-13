package test;

import hotel.Contrato;
import hotel.EstadoDeContrato;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import servicos.alugaveis.CamaExtra;
import servicos.devolviveis.Quarto;
import servicos.devolviveis.TipoQuarto;
import tempo.Periodo;

public class ContratoTest {
	private String cartao1 = "5555666677778884";
	private Calendar c1 = new GregorianCalendar(2014,1,28);
	private Calendar c2 = new GregorianCalendar(2014,1,30);
	private Periodo p1 = new Periodo(c1,c2);
	private Quarto quarto1 = new Quarto(TipoQuarto.LUXO_SIMPLES,101);
	private Quarto quarto2 = new Quarto(TipoQuarto.LUXO_DUPLO,102);
	private Quarto quarto3 = new Quarto(TipoQuarto.LUXO_TRIPLO,103);
	private List<Quarto> quartos = new ArrayList<>();
	private Contrato contrato;
	
	@Before
	public void criaObjetos() {
		quartos.add(quarto1);
		quartos.add(quarto2);
		quartos.add(quarto3);
		
		contrato = new Contrato(cartao1, 100, p1, quartos);
	}
	
	@Test
	public void testaCriaContrato() {
		try {
			new Contrato(null, 100, p1, quartos);
			Assert.fail("Esperava exceção, pois o cartao esta null.");
		} catch (IllegalArgumentException e) {
		}
		
		try {
			new Contrato(cartao1, -1, p1, quartos);
			Assert.fail("Esperava exceção, pois a tarifa é menor ou igual a 0.");
		} catch (IllegalArgumentException e) {
		}
		
		try {
			new Contrato(cartao1, 100, null, quartos);
			Assert.fail("Esperava exceção, pois o periodo esta null.");
		} catch (IllegalArgumentException e) {
		}
		
		try {
			new Contrato(cartao1, 100, p1, null);
			Assert.fail("Esperava exceção, pois a lista de quartos esta null.");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void testaAdicionaeRemoveServicos() {
		Assert.assertTrue(contrato.getServicos().size() == 0);
		Assert.assertFalse(contrato.getServicos().size() == 1);
		
		CamaExtra cama = new CamaExtra(10394);
		
		contrato.adicionaServico(cama);
		
		Assert.assertFalse(contrato.getServicos().size() == 0);
		Assert.assertTrue(contrato.getServicos().size() == 1);
		
		contrato.removeServico(cama);
		
		Assert.assertTrue(contrato.getServicos().size() == 0);
		Assert.assertFalse(contrato.getServicos().size() == 1);
	}
	
	@Test
	public void testaCheckIneCheckOut() {
		Assert.assertEquals(contrato.getEstado(), EstadoDeContrato.PENDENTE);
		
		contrato.realizarCheckIn(cartao1);
		Assert.assertEquals(contrato.getEstado(), EstadoDeContrato.ABERTO);
	}
	
	@Test
	public void testaToString() {
		Assert.assertEquals("Contrato [Estado = PENDENTE]", contrato.toString());
	}
	
}
