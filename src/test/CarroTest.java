package test;

import hotel.Hospede;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tempo.Periodo;
import alugaveis.Carro;
import alugaveis.TipoCarro;

public class CarroTest {

	private Carro carro1;
	private Carro carro2;
	private Carro carro3;
	
	private Calendar c1;
	private Calendar c2;
	private Calendar c3;
	
	private Periodo p1;
	private Periodo p3;
	
	private Hospede h1;
	private Hospede h2;
	
	@Before
	public void before() {
		
		carro1 = new Carro(TipoCarro.LUXO,"ABC-123");
		carro2 = new Carro(TipoCarro.EXECUTIVO,"ABC-123");
		carro3 = new Carro(TipoCarro.LUXO,"ABCD-123");
		
		c1 = new GregorianCalendar(2014,1,28);
		c2 = new GregorianCalendar(2014,1,30);
		c3 = new GregorianCalendar(2014,2,28);
		
		p1 = new Periodo(c1,c2);
		new Periodo(c2,c3);
		p3 = new Periodo(c1,c3);
		
		h1 = new Hospede("Maria", "telefone", "cpf", "email", "endereco", "cidade");
		h2 = new Hospede("Jose", "telefone", "cpf", "email", "endereco", "cidade");
	}
	
	@Test
	public void ConstrutorTest() {
		try {
			new Carro(null,"123");
		} catch(IllegalArgumentException i) {}
	
		try {
			new Carro(TipoCarro.EXECUTIVO,null);
		} catch(IllegalArgumentException i){}
	}
	
	@Test
	public void AlugarTest() {
		try {
			carro1.alugar(null, true, true, p1);
		} catch(IllegalArgumentException i) {}
	
		try {
			carro1.alugar(h1, true, true, null);
		} catch(IllegalArgumentException i) {}
		
		carro1.alugar(h1, true, true, p1);
		
		Assert.assertEquals(p1, carro1.getPeriodo());
		Assert.assertEquals(h1, carro1.getResponsavel());
		Assert.assertEquals("ABC-123", carro1.getPlaca());   //testando gets
		Assert.assertEquals(false, carro1.isDevolvido());
		
		carro1.setResponsavel(h2);  //testando setResponsavel
		Assert.assertEquals(h2, carro1.getResponsavel());
	}
	
	@Test
	public void getPrecoTest() {
		
		carro1.alugar(h1, true, true, p1);
		Assert.assertEquals(0, carro1.getPreco(),0.001);
		carro1.devolve();
		Assert.assertEquals(450, carro1.getPreco(),0.001);
		
		carro2.alugar(h1, true, false, p3);
		Assert.assertEquals(0, carro2.getPreco(),0.001);
		carro2.devolve();
		Assert.assertEquals(1830, carro2.getPreco(),0.001);	
	}
	
	@Test
	public void verificaPlaca() {
		
		Assert.assertFalse(Carro.verificaPlaca("123"));
		Assert.assertFalse(Carro.verificaPlaca("ABC"));
		Assert.assertFalse(Carro.verificaPlaca("ABC123"));
		Assert.assertFalse(Carro.verificaPlaca("ABCD4123"));
		Assert.assertTrue(Carro.verificaPlaca("ABC4123"));
	}
	
	@Test
	public void getDescricaoTest() {
		
		carro1.alugar(h1, true, true, p1);
		Assert.assertEquals(0, carro1.getPreco(),0.001);
		carro1.devolve();
		
		Assert.assertEquals( "Carro Luxo. " +
				   "Placa do carro: " + "ABC-123" + ". " + 
				   "Valor da diaria: " + 100.0 + ". " +
				   "Servico tanque cheio: incluso. " + 
				   "Seguro: incluso. " +
				   "Valor total: 450.0.", carro1.getDescricao());
		
		carro2.alugar(h1, true, false, p3);
		Assert.assertEquals(0, carro2.getPreco(),0.001);
		carro2.devolve();
		
		Assert.assertEquals( "Carro Executivo. " +
				   "Placa do carro: " + "ABC-123" + ". " + 
				   "Valor da diaria: " + 60.0 + ". " +
				   "Servico tanque cheio: incluso. " + 
				   "Seguro: nao incluso. " +
				   "Valor total: 1830.0.", carro2.getDescricao());	
	
	
		carro2.alugar(h1, true, false, p3);
		Assert.assertEquals(0, carro2.getPreco(),0.001);
		carro2.setPeriodo(p1); //testando setPeriodo
		carro2.devolve();
	
		Assert.assertEquals( "Carro Executivo. " +
			   "Placa do carro: " + "ABC-123" + ". " + 
			   "Valor da diaria: " + 60.0 + ". " +
			   "Servico tanque cheio: incluso. " + 
			   "Seguro: nao incluso. " +
			   "Valor total: 270.0.", carro2.getDescricao());	
	}
	
	@Test
	public void equalsTest() {
		
		Assert.assertTrue(carro1.equals(carro2));
		Assert.assertFalse(carro3.equals(carro2));
		Assert.assertFalse(carro1.equals(carro3));
	}
}
