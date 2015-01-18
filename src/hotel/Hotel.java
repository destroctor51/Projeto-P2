package hotel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import servicos.alugaveis.Babysitter;
import servicos.alugaveis.CamaExtra;
import servicos.devolviveis.Carro;
import servicos.devolviveis.Quarto;
import servicos.devolviveis.TipoCarro;
import servicos.devolviveis.TipoQuarto;
import tempo.Estacao;
import tempo.Periodo;

/**
 * Uma classe que cria hoteis.
 *
 * @author Arthur Vinicius Tome Rodrigues
 */
public class Hotel {
	private String nome;
	private List<CamaExtra> camas = new ArrayList<>();
	private List<Babysitter> babas = new ArrayList<>();
	private List<Hospede> hospedes = new ArrayList<>();
	private List<Quarto> quartos = new ArrayList<>();
	private List<Carro> carros = new ArrayList<>();
	private List<Restaurante> restaurantes = new ArrayList<>();
	private List<Estacao> tarifas = new ArrayList<>();
	private List<Opiniao> opinioes = new ArrayList<>();

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

		try {
			int i=1, j=0;
			while(j < 5) adicionaQuarto(TipoQuarto.EXECUTIVO_SIMPLES, i+j++); i+=j; j=0;
			while(j < 15) adicionaQuarto(TipoQuarto.EXECUTIVO_DUPLO, i+j++); i+=j; j=0;
			while(j < 10) adicionaQuarto(TipoQuarto.EXECUTIVO_TRIPLO, i+j++);

			i=101; j=0;
			while(j < 10) adicionaQuarto(TipoQuarto.EXECUTIVO_TRIPLO, i+j++); i+=j; j=0;
			while(j < 5) adicionaQuarto(TipoQuarto.LUXO_SIMPLES, i+j++); i+=j; j=0;
			while(j < 15) adicionaQuarto(TipoQuarto.LUXO_DUPLO, i+j++);

			i=201; j=0;
			while(j < 20) adicionaQuarto(TipoQuarto.LUXO_TRIPLO, i+j++); i+=j; j=0;
			while(j < 5) adicionaQuarto(TipoQuarto.PRESIDENCIAL, i+j++);
		} catch (Exception e) {}
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
	public List<CamaExtra> getCamasDisponiveis(Periodo periodo) {
		List<CamaExtra> camasDisponiveis = new ArrayList<>();

		for (CamaExtra cama : camas) {
			boolean disponivel = true;
			for(Periodo p : cama.getHistorico())
				disponivel &= !p.entraEmConflito(periodo);
			if(disponivel) camasDisponiveis.add(cama);
		}

		return camasDisponiveis;
	}


	/**
	 * Adiciona uma cama extra ao estoque de camas do hotel.
	 *
	 * @param codigo  o codigo da cama a ser adicionada.
	 * @throws Exception  se ja houver uma cama com o mesmo codigo cadastrada
	 */
	public void adicionaCamaExtra(int codigo) throws Exception {
		for (CamaExtra cama : camas) {
			if (cama.getCodigo() == codigo)
				throw new Exception("Codigo ja esta sendo utilizado");
		}

		CamaExtra cama = new CamaExtra(codigo);
		camas.add(cama);
	}

	/**
	 * Remove uma cama do estoque do hotel.
	 *
	 * @param cama  a cama a ser removida
	 * @return true caso a cama tenha sido removida com sucesso, false caso contrario
	 */
	public boolean removeCamaExtra(CamaExtra cama) {
		return camas.remove(cama);
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
	 * @param nome  o nome da baba a ser registrada.
	 * @throws Exception se ja houver uma baba com o mesmo nome cadastrada
	 */
	public void adicionaBaba(String nome) throws Exception {
		for (Babysitter baba : babas)
			if (baba.getNome().equals(nome))
				throw new Exception("Nome ja existente");

		Babysitter baba = new Babysitter(nome);
		babas.add(baba);
	}


	/**
	 * Remove uma baba que foi demitida do hotel.
	 *
	 * @param baba  a baba a ser removida
	 * @return true se a baba foi removida com sucesso, false caso contrario
	 */
	public boolean removeBaba(Babysitter baba) {
		return babas.remove(baba);
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
	 * @param nome  o nome do hospede a ser cadastrado
	 * @param telefone  o telefone do hospede
	 * @param cpf  o cpf do hospede
	 * @param email  o email do hospede
	 * @param cidade  o cidade na qual o hospede reside
	 * @param endereco  o endereco no qual o hospede reside
	 * @throws Exception se o cpf for invalido ou ja houver um contato com o mesmo telefone, email ou cpf cadastrado
	 */
	public void adicionaHospede(String nome, String telefone, String cpf, String email, String cidade, String endereco) throws Exception {
		if (!(Hospede.verificaCpf(cpf)))
			throw new Exception("Cpf invalido");

		for (Hospede hosp : hospedes) {
			if (hosp.getTelefone().equals(telefone))
				throw new Exception("Telefone ja esta sendo utilizado");
			if (hosp.getEmail().equals(email))
				throw new Exception("Email ja esta sendo utilizado");
			if (hosp.getCpf().equals(cpf))
				throw new Exception("Cpf ja esta sendo utilizado");
		}

		Hospede hospede = new Hospede(nome, telefone, cpf, email, cidade, endereco);
		hospedes.add(hospede);
	}

	/**
	 * Remove hospede cadastrado no hotel.
	 *
	 * @param hospede  o hospede a ser removido
	 * @return true se ele for removido com sucesso, false caso contrario
	 */
	public boolean removeHospede(Hospede hospede) {
		return hospedes.remove(hospede);
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
			boolean disponivel = true;
			for(Periodo p : quarto.getHistorico())
				disponivel &= !p.entraEmConflito(periodo);
			if(disponivel) quartosDisponiveis.add(quarto);
		}

		return quartosDisponiveis;
	}

	/**
	 * Adiciona um quarto ao hotel.
	 *
	 * @param tipo  o tipo do quarto
	 * @param numero  o numero do quarto
	 * @throws Exception se ja houver um quarto com o mesmo numero cadastrado
	 */
	public void adicionaQuarto(TipoQuarto tipo, int numero) throws Exception {
		for (Quarto quarto : quartos)
			if (quarto.getNumero() == numero)
				throw new Exception("Numero ja existente");

		Quarto quarto = new Quarto(tipo, numero);
		quartos.add(quarto);
	}

	/**
	 * Remove um quarto do hotel.
	 *
	 * @param quarto  o quarto a ser removido
	 * @return true se ele for removido com sucesso, false caso contrario
	 */
	public boolean removeQuarto(Quarto quarto) {
		return quartos.remove(quarto);
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
			boolean disponivel = true;
			for(Periodo p : carro.getHistorico())
				disponivel &= !p.entraEmConflito(periodo);
			if(disponivel) carrosDisponiveis.add(carro);
		}

		return carrosDisponiveis;
	}

	/**
	 * Adiciona um novo carro aos disponiveis no hotel.
	 *
	 * @param tipo  tipo do carro
	 * @param placa  placa do carro
	 * @throws Exception  caso a placa seja invalida ou ja esteja cadastrada
	 */
	public void adicionaCarro(TipoCarro tipo, String placa) throws Exception {
		if (!(Carro.verificaPlaca(placa)))
			throw new Exception("Placa invalida");

		for (Carro carro : carros)
			if (carro.getPlaca().equals(placa))
				throw new Exception("Placa ja existente");

		Carro carro = new Carro(tipo, placa);
		carros.add(carro);
	}

	/**
	 * Remove um dos carros disponiveis no hotel.
	 *
	 * @param carro  o carro a ser removido
	 * @return true se ele for removido com sucesso, false caso contrario
	 */
	public boolean removeCarro(Carro carro) {
		return carros.remove(carro);
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
	 * @param nome  o nome do restaurante a ser adicionado
	 * @throws Exception se ja houver um restaurante com o mesmo nome cadastrado
	 */
	public void adicionaRestaurante(String nome) throws Exception {
		for (Restaurante restaurante : restaurantes)
			if (restaurante.getNome().equals(nome))
				throw new Exception("Nome ja existente");

		Restaurante restaurante = new Restaurante(nome);
		restaurantes.add(restaurante);
	}

	/**
	 * Remove um restaurante do hotel.
	 * @param restaurante  o restaurante a ser removido
	 * @return true se ele for removido com sucesso, false caso contrario
	 */
	public boolean removeRestaurante(Restaurante restaurante) {
		return restaurantes.remove(restaurante);
	}

	/**
	 * Recupera um iterador das estacoes do hotel (cada uma com certa tarifa).
	 *
	 * @return
	 * 			Iterador das estacoes do hotel.
	 */
	public Iterator<Estacao> getTarifas() {
		return tarifas.iterator();
	}

	/**
	 * Adiciona uma estacao ao hotel.
	 *
	 * @param estacao
	 * 			Estacao a ser adicionada.
	 */
	public void adicionaEstacao(Estacao estacao) {
		if (estacao == null)
			throw new IllegalArgumentException();
		tarifas.add(estacao);
	}

	/**
	 * Remove uma estacao do hotel atraves de um perido contido nela.
	 * @param estacao  a estacao a ser removida
	 * @return true se ela for removida com sucesso, false caso contrario
	 */
	public boolean removeEstacao(Estacao estacao) {
		return tarifas.remove(estacao);
	}

	/**
	 * Recupera uma estacao a partir de um periodo contido nela.
	 *
	 * @param periodo
	 * 			Um periodo contido na estacao procurada.
	 * @return
	 * 			A estacao, caso exista.
	 */
	public Estacao procuraEstacao(Periodo periodo) {
		for (Estacao estacao : tarifas)
			if (estacao.getPeriodos().contains(periodo))
				return estacao;

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
		adicionaOpiniao("Anonimo", nota, comentario);
	}

	@Override
	public String toString() {
		return "Hotel [Nome = " + getNome() + ", numero de hospedes = " + getHospedes().size() + ", numero de quartos = " + getQuartos().size() +
				", numero de carros = " + getCarros().size() + ", numero de restaurantes = " + getRestaurantes().size() +
				", opinioes registradas = " + getOpinioes().size() + "]";
	}
}