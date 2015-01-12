package hotel;

import java.util.ArrayList;
import java.util.List;
import alugaveis.Carro;
import alugaveis.Quarto;
import alugaveis.TipoCarro;
import alugaveis.TipoQuarto;

import servicos.Babysitter;
import servicos.CamaExtra;
import servicos.Restaurante;
import tempo.Estacao;
import tempo.Periodo;

/**
 * Uma classe que cria hoteis.
 * 
 * @author Arthur Vinicius Tome Rodrigues
 */
public class Hotel {
	private String nome;
	private List<CamaExtra> camas;
	private List<Babysitter> babas;
	private List<Hospede> hospedes;
	private List<Quarto> quartos;
	private List<Carro> carros;
	private List<Restaurante> restaurantes;
	private List<Estacao> tarifas;
	private List<Opiniao> opinioes;
	
	// construtor
	
	/**
	 * Cria um hotel a partir do nome.
	 * 
	 * @param nome
	 * 			O nome do hotel.
	 */
	public Hotel(String nome) {
		if (nome == null || nome.equals(""))
			throw new IllegalArgumentException();
		
		this.nome = nome;
		camas = new ArrayList<CamaExtra>();
		babas = new ArrayList<Babysitter>();
		hospedes = new ArrayList<Hospede>();
		quartos = new ArrayList<Quarto>();
		carros = new ArrayList<Carro>();
		restaurantes = new ArrayList<Restaurante>();
		tarifas = new ArrayList<Estacao>();
		opinioes = new ArrayList<Opiniao>();
	}

	// metodos
	
	/**
	 * Recupera o nome do hotel.
	 * @return
	 * 			O nome do hotel.
	 */
	public String getNome() {
		return nome;
	}	
	
	/**
	 * Recupera uma lista com as camas extras no hotel.
	 * 
	 * @return
	 * 			Lista de camas extras.
	 */
	public List<CamaExtra> getCamas() {
		return camas;
	}
	
	/**
	 * Recupera o numero de camas extras disponiveis no hotel em um periodo.
	 * 
	 * @param periodo
	 * 			O periodo desejado para ver as camas disponiveis.
	 * @return
	 * 			Numero de camas disponiveis no periodo.
	 */
	public int getCamasDisponiveis(Periodo periodo) {
		int camasDisponiveis = 0;
		
		for (CamaExtra cama : camas) {
			if (cama.getPeriodo() != null && !(cama.getPeriodo().entraEmConflito(periodo)))
				camasDisponiveis++;
		}
		
		return camasDisponiveis;
	}
	

	/**
	 * Adiciona uma cama extra ao estoque de camas do hotel.
	 * 
	 * @param codigo
	 * 			O codigo da cama a ser adicionada.
	 */
	public void adicionaCamaExtra(int codigo) throws Exception {
		if (codigo < 0)
			throw new IllegalArgumentException();
		
		for (CamaExtra cama : camas) {
			if (cama.getCodigo() == codigo)
				throw new Exception("Codigo ja esta sendo utilizado.");
		}
		
		CamaExtra cama = new CamaExtra(codigo);
		camas.add(cama);
	}
	
	/**
	 * Remove uma cama do estoque do hotel.
	 * 
	 * @param codigo
	 * 			O codigo da cama a ser removida.
	 */
	public void removeCamaExtra(int codigo) {
		if (codigo < 0)
			throw new IllegalArgumentException();
		
		for (CamaExtra cama : camas) {
			if (cama.getCodigo() == codigo) {
				camas.remove(cama);
				break;
			}
		}
	}
	
	/**
	 * Recupera uma lista com as babas que trabalham no hotel.
	 * 
	 * @return
	 * 			Lista de babas contratadas pelo hotel.
	 */
	public List<Babysitter> getBabas() {
		return babas;
	}

	/**
	 * Registra uma nova baba contratada pelo hotel.
	 * 
	 * @param nome
	 * 			O nome da baba a ser registrada.
	 * @throws Exception
	 */
	public void adicionaBaba(String nome) throws Exception {
		if (nome == null || nome.equals(""))
			throw new IllegalArgumentException();
		
		for (Babysitter baba : babas) {
			if (baba.getNome().equals(nome))
				throw new Exception("Nome ja existente.");
		}
		
		Babysitter baba = new Babysitter(nome);
		babas.add(baba);
	}
	
	
	/**
	 * Remove uma baba que foi demitida do hotel.
	 * 
	 * @param nome
	 * 			Nome da baba a ser removida.
	 */
	public void removeBaba(String nome) {
		if (nome == null || nome.equals(""))
			throw new IllegalArgumentException();
		Babysitter baba = new Babysitter(nome);
		babas.remove(baba);
	}
	
	/**
	 * Recupera uma baba a partir do nome.
	 * 
	 * @param nome
	 * 			O nome da baba procurada.
	 * @return
	 * 			A baba, caso exista.
	 */
	public Babysitter ProcuraBaba(String nome) {
		if (nome == null || nome.equals(""))
			throw new IllegalArgumentException();
		
		for (Babysitter baba : babas) {
			if (baba.getNome().equals(nome))
				return baba;
		}
		
		return null;
	}

	/**
	 * Recupera uma lista com todos os hospedes registrados no hotel.
	 * 
	 * @return
	 * 			Lista com os hospedes registrados.
	 */
	public List<Hospede> getHospedes() {
		return hospedes;
	}

	/**
	 * Cadastra um hospede no hotel.
	 * 
	 * @param nome
	 * 			O nome do hospede a ser cadastrado.
	 * @param telefone
	 * 			O telefone do hospede.
	 * @param cpf
	 * 			O cpf do hospede.
	 * @param email
	 * 			O email do hospede.
	 * @param cidade
	 * 			A cidade na qual o hospede reside.
	 * @param endereco
	 * 			O endereco no qual o hospede reside.
	 * @throws Exception
	 */
	public void adicionaHospede(String nome, String telefone, String cpf, String email, String cidade, String endereco) throws Exception {
		if (nome == null || telefone == null || cpf == null || email == null || cidade == null || endereco == null)
			throw new IllegalArgumentException();
		
		if (!(Hospede.verificaCpf(cpf)))
			throw new Exception("Cpf invalido.");
		
		for (Hospede hosp : hospedes) {
			if (hosp.getTelefone().equals(telefone))
				throw new Exception("Telefone ja esta sendo utilizado.");
			else if (hosp.getEmail().equals(email))
				throw new Exception("Email ja esta sendo utilizado.");
			else if (hosp.getCpf().equals(cpf))
				throw new Exception("Cpf ja esta sendo utilizado.");
		}
			
		Hospede hospede = new Hospede(nome, telefone, cpf, email, cidade, endereco);
		hospedes.add(hospede);
	}
	
	/**
	 * Remove hospede cadastrado no hotel.
	 * 
	 * @param cpf
	 * 			O cpf do hospede a ser removido.
	 * @throws Exception
	 */
	public void removeHospede(String cpf) throws Exception {
		if (cpf == null || cpf.equals(""))
			throw new IllegalArgumentException();
		
		if (!(Hospede.verificaCpf(cpf)))
			throw new Exception("Cpf invalido.");
		
		for (Hospede hospede : hospedes) {
			if (hospede.getCpf().equals(cpf)) {
				hospedes.remove(hospede);
				break;
			}
		}
	}

	/**
	 * Recupera uma lista com todos os quartos existentes no hotel.
	 * 
	 * @return
	 * 			Lista de quartos existentes no hotel.
	 */
	public List<Quarto> getQuartos() {
		return quartos;
	}
	
	/**
	 * Recupera uma lista com os quartos disponiveis no hotel em um periodo.
	 * 
	 * @param periodo
	 * 			O periodo desejado para ver os quartos disponiveis.
	 * @return
	 * 			Lista de quartos disponiveis no periodo.
	 */
	public List<Quarto> getQuartosDisponiveis(Periodo periodo) {
		List<Quarto> quartosDisponiveis = new ArrayList<>();
		
		for (Quarto quarto : quartos) {
			if (quarto.getPeriodo() != null && !(quarto.getPeriodo().entraEmConflito(periodo)))
				quartosDisponiveis.add(quarto);
		}
		
		return quartosDisponiveis;
	}

	/**
	 * Adiciona um quarto ao hotel.
	 * 
	 * @param tipo
	 * 			O tipo do quarto (TipoQuarto.PRESIDENCIAL ou TipoQuarto.LUXO_SIMPLES ou TipoQuarto.LUXO_DUPLO ou
	 * 							  TipoQuarto.LUXO_TRIPLO ou TipoQuarto.EXECUTIVO_SIMPLES ou TipoQuarto.EXECUTIVO_DUPLO ou
	 * 							  TipoQuarto.EXECUTIVO_TRIPLO).
	 * @param numero
	 * 			Numero do quarto.
	 * @throws Exception
	 */
	public void adicionaQuarto(TipoQuarto tipo, int numero) throws Exception {
		if (numero <= 0)
			throw new IllegalArgumentException();
		
		for (Quarto quarto : quartos) {
			if (quarto.getNumero() == numero)
				throw new Exception("Numero ja existente.");
		}
		
		Quarto quarto = new Quarto(tipo, numero);
		quartos.add(quarto);
	}
	
	/**
	 * Remove um quarto do hotel.
	 * 
	 * @param numero
	 * 			Numero do quarto.
	 */
	public void removeQuarto(int numero) {
		if (numero <= 0)
			throw new IllegalArgumentException();
		
		for (Quarto quarto : quartos) {
			if (quarto.getNumero() == numero) {
				quartos.remove(quarto);
				break;
			}
		}
	}

	/**
	 * Recupera um quarto a partir do numero.
	 * 
	 * @param numero
	 * 			O numero do quarto procurado.
	 * @return
	 * 			O quarto, caso exista.
	 */
	public Quarto ProcuraQuarto(int numero) {
		if (numero <= 0)
			throw new IllegalArgumentException();
		
		for (Quarto quarto : quartos) {
			if (quarto.getNumero() == numero)
				return quarto;
		}
		
		return null;
	}
	
	/**
	 * Recupera uma lista com todos os carros disponiveis no hotel.
	 * 
	 * @return
	 * 			Lista de quartos disponiveis para aluguel.
	 */
	public List<Carro> getCarros() {
		return carros;
	}

	/**
	 * Recupera uma lista com os carros disponiveis no hotel em um periodo.
	 * 
	 * @param periodo
	 * 			O periodo desejado para ver os carros disponiveis.
	 * @return
	 * 			Lista de carros disponiveis no periodo.
	 */
	public List<Carro> getCarrosDisponiveis(Periodo periodo) {
		List<Carro> carrosDisponiveis = new ArrayList<>();
		
		for (Carro carro : carros) {
			if (carro.getPeriodo() != null && !(carro.getPeriodo().entraEmConflito(periodo)))
				carrosDisponiveis.add(carro);
		}
		
		return carrosDisponiveis;
	}
	
	/**
	 * Adiciona um novo carro aos disponiveis no hotel.
	 * 
	 * @param tipo
	 * 			Tipo do carro (TipoCarro.LUXO ou TipoCarro.EXECUTIVO).
	 * @param placa
	 * 			Placa do carro.
	 * @throws Exception
	 */
	public void adicionaCarro(TipoCarro tipo, String placa) throws Exception {
		if (tipo == null || placa == null)
			throw new IllegalArgumentException();
		
		if (!(Carro.verificaPlaca(placa)))
			throw new Exception("Placa invalida.");
		
		for (Carro carro : carros) {
			if (carro.getPlaca().equals(placa))
				throw new Exception("Placa ja existente.");
		}
		
		Carro carro = new Carro(tipo, placa);
		carros.add(carro);
	}
	
	/**
	 * Remove um dos carros disponiveis no hotel.
	 * 
	 * @param placa
	 * 			Placa do carro.
	 * @throws Exception
	 */
	public void removeCarro(String placa) throws Exception {
		if (placa == null || placa.equals(""))
			throw new IllegalArgumentException();
		
		if (!(Carro.verificaPlaca(placa)))
			throw new Exception("Placa invalida.");
		
		for (Carro carro : carros) {
			if (carro.getPlaca().equals(placa)) {
				carros.remove(carro);
				break;
			}
		}
	}

	/**
	 * Recupera uma lista com os restaurantes existentes no hotel.
	 * 
	 * @return
	 * 			Lista de restaurantes disponiveis no hotel.
	 */
	public List<Restaurante> getRestaurantes() {
		return restaurantes;
	}

	/**
	 * Adiciona um restaurante ao hotel.
	 * 
	 * @param nome
	 * 			O nome do restaurante a ser adicionado.
	 * @throws Exception
	 */
	public void adicionaRestaurante(String nome) throws Exception {
		if (nome == null || nome.equals(""))
			throw new IllegalArgumentException();
		
		for (Restaurante restaurante : restaurantes) {
			if (restaurante.getNome().equals(nome))
				throw new Exception("Nome ja existente.");
		}
		
		Restaurante restaurante = new Restaurante(nome);
		restaurantes.add(restaurante);
	}
	
	/**
	 * Remove um restaurante do hotel.
	 * 
	 * @param nome
	 * 			O nome do restaurante a ser removido.
	 */
	public void removeRestaurante(String nome) {
		if (nome == null || nome.equals(""))
			throw new IllegalArgumentException();
		
		for (Restaurante restaurante : restaurantes) {
			if (restaurante.getNome().equals(nome)) {
				restaurantes.remove(restaurante);
				break;
			}
		}
	}
	
	/**
	 * Recupera uma lista com as estacoes do hotel (cada uma com certa tarifa).
	 * 
	 * @return
	 * 			Lista de estacoes do hotel.
	 */
	public List<Estacao> getTarifas() {
		return tarifas;
	}

	/**
	 * Adiciona uma estacao com sua respectiva tarifa ao hotel.
	 * 
	 * @param tarifa
	 * 			Tarifa da estacao.
	 */
	public void adicionaEstacao(double tarifa) {
		if (tarifa < 0)
			throw new IllegalArgumentException();
		
		Estacao estacao = new Estacao(tarifa);
		tarifas.add(estacao);
	}
	
	/**
	 * Remove uma estacao do hotel atraves de sua tarifa.
	 * 
	 * @param tarifa
	 * 			A tarifa da estacao a ser removida. 
	 */
	public void removeEstacao(double tarifa) {
		if (tarifa <= 0)
			throw new IllegalArgumentException();
		
		for (Estacao estacao : tarifas) {
			if (estacao.getTarifa() == tarifa) {
				tarifas.remove(estacao);
				break;
			}
		}
	}
	
	/**
	 * Recupera uma estacao a partir de sua tarifa.
	 * 
	 * @param tarifa
	 * 			A tarifa da estacao procurada.
	 * @return
	 * 			A estacao, caso exista.
	 */
	public Estacao ProcuraEstacao(double tarifa) {
		if (tarifa <= 0)
			throw new IllegalArgumentException();
		
		for (Estacao estacao : tarifas) {
			if (estacao.getTarifa() == tarifa)
				return estacao;
		}
		
		return null;
	}

	/**
	 * Recupera uma lista com as opinioes registradas sobre o hotel.
	 * 
	 * @return
	 * 			Lista de opinioes sobre o hotel.
	 */
	public List<Opiniao> getOpinioes() {
		return opinioes;
	}

	/**
	 * Adiciona uma opiniao sobre o hotel.
	 * 
	 * @param autor
	 * 			O nome do autor da opiniao.
	 * @param nota
	 * 			A nota dada ao hotel.
	 * @param comentario
	 * 			O comentario sobre o hotel.
	 */
	public void adicionaOpiniao(String autor, int nota, String comentario) {
		if (autor == null || comentario == null || autor.equals("") || nota < 0 || nota > 10 || comentario.equals(""))
			throw new IllegalArgumentException();
		
		Opiniao opiniao = new Opiniao(autor, nota, comentario);
		opinioes.add(opiniao);
	}
	
	/**
	 * Adiciona uma opiniao sobre o hotel.
	 * 
	 * @param nota
	 * 			A nota dada ao hotel.
	 * @param comentario
	 * 			O comentario sobre o hotel.
	 */
	public void adicionaOpiniao(int nota, String comentario) {
		if (comentario == null || nota < 0 || nota > 10 || comentario.equals(""))
			throw new IllegalArgumentException();
		
		Opiniao opiniao = new Opiniao(nota, comentario);
		opinioes.add(opiniao);
	}
	
	/**
	 * Recupera um hospede a partir do cpf.
	 * 
	 * @param cpf
	 * 			O cpf do hospede a ser procurado.
	 * @return
	 * 			O hospede procurado se existir, caso contrario, retorna null.
	 * @throws Exception
	 */
	public Hospede procuraHospede(String cpf) throws Exception {
		if (cpf == null || cpf.equals(""))
			throw new IllegalArgumentException();
		
		if (!(Hospede.verificaCpf(cpf)))
			throw new Exception("Cpf invalido.");
		
		Hospede hospede = null;
		
		for (Hospede hosp : hospedes) {
			if (hosp.getCpf().equals(cpf))
				hospede = hosp;
		}		
		return hospede;
	}
	
	@Override
	public String toString() {
		return "Hotel [Numero de hospedes = " + getHospedes().size() + ", numero de quartos = " + getQuartos().size() + 
			   ", numero de carros = " + getCarros().size() + ", numero de restaurantes = " + getRestaurantes().size() +
			   ", numero de tarifas = "+ getTarifas().size() + ", opinioes registradas = " + getOpinioes().size() + "]";
	}	
}