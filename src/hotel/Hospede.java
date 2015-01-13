package hotel;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;

import servicos.devolviveis.Quarto;
import tempo.Periodo;

/**
 * Uma classe que cria hospedes.
 * 
 * @author Arthur Vinicius Tome Rodrigues
 */
public class Hospede {
	private String nome;
	private String telefone;
	private String cpf;
	private String email;
	private String cidade;
	private String endereco;
	private List<Contrato> contratos = new ArrayList<Contrato>();
	
	// construtor
	
	/**
	 * Cria um hospede a partir do nome, numero de telefone, cpf, email, cidade e endereco.
	 * 
	 * @param nome
	 * 			O nome do hospede a ser criado.
	 * @param telefone
	 * 			Telefone do hospede.
	 * @param cpf
	 * 			Cpf do hospede.
	 * @param email
	 * 			Email do hospede.
	 * @param cidade
	 * 			Cidade em que o hospede reside.
	 * @param endereco
	 * 			Endereco em que o hospede reside.
	 */
	public Hospede(String nome, String telefone, String cpf, String email, String cidade, String endereco) {
		if (nome == null || telefone == null || cpf == null || email == null || cidade == null || endereco == null)
			throw new IllegalArgumentException();
		
		this.nome = nome;
		this.telefone = telefone;
		this.cpf = cpf;
		this.email = email;
		this.cidade = cidade;
		this.endereco = endereco;
	}

	// metodos
	
	/**
	 * Recupera o numero de telefone do hospede.
	 * 
	 * @return
	 * 			O numero de telefone do hospede.
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * Seta o numero de telefone do hospede.
	 * 
	 * @param telefone
	 * 			Numero de telefone a ser colocado.
	 */
	public void setTelefone(String telefone) {
		if (telefone == null)
			throw new IllegalArgumentException();
		this.telefone = telefone;
	}

	/**
	 * Recupera o email do hospede.
	 * 
	 * @return
	 * 			O email do hospede.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Seta o email do hospede.
	 * 
	 * @param email
	 * 			O email a ser colocado.
	 */
	public void setEmail(String email) {
		if (email == null)
			throw new IllegalArgumentException();
		this.email = email;
	}

	/**
	 * Recupera a cidade em que o hospede reside.
	 * 
	 * @return
	 * 			A cidade em que o hospede reside.
	 */
	public String getCidade() {
		return cidade;
	}

	/**
	 * Seta a cidade em que o hospede reside.
	 * 
	 * @param cidade
	 * 			A cidade a ser colocada.
	 */
	public void setCidade(String cidade) {
		if (cidade == null)
			throw new IllegalArgumentException();
		this.cidade = cidade;
	}

	/**
	 * Recupera o endereco em que o hospede reside.
	 * 
	 * @return
	 * 			O endereco do hospede.
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * Seta o endereco em que o hospede reside.
	 * 
	 * @param endereco
	 * 			O endereco a ser colocado.
	 */
	public void setEndereco(String endereco) {
		if (endereco == null)
			throw new IllegalArgumentException();
		this.endereco = endereco;
	}

	/**
	 * Recupera o nome do hospede.
	 * 
	 * @return
	 * 			O nome do hospede.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Recupera o cpf do hospede.
	 * 
	 * @return
	 * 			O cpf do hospede.
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * Recupera um iterador dos contratos do hospede.
	 * 
	 * @return
	 * 			Iterador de contratos do hospede.
	 */
	public Iterator<Contrato> getContratos() {
		return contratos.iterator();
	}
	
	/**
	 * Realiza reserva do hospede, criando assim um contrato pendente.
	 * 
	 * @param cartao
	 * 			O numero do cartao do hospede.
	 * @param tarifa
	 * 			A tarifa a ser cobrada sobre o preco do contrato.
	 * @param estadia
	 * 			Periodo de estadia no hotel.
	 * @param quartos
	 * 			Lista de quartos do hotel.
	 * @throws Exception
	 */
	public void realizarReserva(String cartao, double tarifa, Periodo estadia, List<Quarto> quartos) throws Exception {
		if (cartao == null || estadia == null || quartos == null || cartao.equals("") || tarifa < 0)
			throw new IllegalArgumentException();
		if (!(verificaCartao(cartao)))
			throw new Exception("Cartao invalido.");
		
		Contrato contrato = new Contrato(cartao, tarifa, estadia, quartos);
		contratos.add(contrato);
	}
	
	@Override
	public String toString() {
		return "Hospede [Nome = " + getNome() + ", telefone = " + getTelefone() + ", cpf = "
				+ getCpf() + ", email = " + getEmail() + ", cidade = " + getCidade()
				+ ", endereco = " + getEndereco() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Hospede))
			return false;
		
		Hospede umHospede = (Hospede) obj;
		
		return umHospede.getCpf().equals(getCpf());
	}

	/**
	 * Verifica se uma string representa um numero de cpf valido.
	 * 
	 * @param cpf
	 * 			Cpf a ser verificado
	 * @return
	 * 			Boolean que representa validade do cpf.
	 */
	public static boolean verificaCpf(String cpf) {
	// considera-se erro CPF's formados por uma sequencia de numeros iguais
		if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222") ||
			cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555") ||
			cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888") || 
			cpf.equals("99999999999") || (cpf.length() != 11)) 
			return(false); 
		
		char dig10, dig11;
		int sm, i, r, num, peso;
		
		// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		try { 
		// Calculo do 1o. Digito Verificador 
			sm = 0; 
			peso = 10; 
			
			for (i=0; i<9; i++) { 
				// converte o i-esimo caractere do CPF em um numero: 
				// por exemplo, transforma o caractere '0' no inteiro 0 
				// (48 eh a posicao de '0' na tabela ASCII) 
				num = (int)(cpf.charAt(i) - 48); 
				sm = sm + (num * peso); 
				peso = peso - 1; 
			} 
			
			r = 11 - (sm % 11); 
			if ((r == 10) || (r == 11)) 
				dig10 = '0'; 
			else dig10 = (char)(r + 48); // converte no respectivo caractere numerico
			
		// Calculo do 2o. Digito Verificador 
			sm = 0; 
			peso = 11; 
			
			for(i=0; i<10; i++) { 
				num = (int)(cpf.charAt(i) - 48); 
				sm = sm + (num * peso); 
				peso = peso - 1; 
			} 
			
			r = 11 - (sm % 11); 
			if ((r == 10) || (r == 11)) 
				dig11 = '0'; 
			else dig11 = (char)(r + 48); 
			
		// Verifica se os digitos calculados conferem com os digitos informados. 
			if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
				return(true); 
			else return(false); 
			} catch (InputMismatchException erro) { 
				return(false); 
			}
		}
	
	/**
	 * Checa se uma string representa um numero de cartao de credito valido atraves do Luhn algorithm.
	 *
	 * @param cartao.
	 * 			O numero do cartao de credito.
	 * @return
	 * 			Boolean representando sua validade.
	 */
	private static boolean verificaCartao(String cartao) {
		int sum = 0;
 
		boolean alternate = false;
		for (int i = cartao.length() - 1; i >= 0; i--) {
			int n = Integer.parseInt(cartao.substring(i, i + 1));
			if (alternate) {
				n *= 2;
				if (n > 9) {
					n = (n % 10) + 1;
				}
			}
			sum += n;
			alternate = !alternate;
		}
 
		return (sum % 10 == 0);
	}
}
