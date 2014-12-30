package login;

import java.util.HashSet;
import java.util.Set;

import disco.Arquivo;

/**
 * Um objeto que e capaz de gerenciar as contas de um sistema de modo persistente.
 * 
 * @author Victor Andrade de Almeida
 */
public class GerenciadorDeContas {
	
	private Set<Conta> contas;
	private String arquivo;
	
	/**
	 * Cria um GerenciadorDeContas temporario, incapaz de salvar seus dados em arquivo.
	 */
	public GerenciadorDeContas() {
		this.arquivo = null;
	}
	
	/**
	 * Cria um GerenciadorDeContas que usara o caminho de arquivo especificado para salvar seus dados.
	 * 
	 * @param arquivo  o nome e caminho do arquivo usado para salvar informacoes
	 */
	public GerenciadorDeContas(String arquivo) {
		this.arquivo = arquivo;
	}
	
	/**
	 * Acessa uma conta especifica do sistema.
	 * 
	 * @param id  a identificacao da conta em questao
	 * @param senha  a senha da conta em questao
	 * @return a conta desejada ou null caso a identificacao ou a senha nao baterem
	 */
	public Conta login(String id, String senha) {
		if(contas == null) carregaContas();

		for(Conta c : contas)
			if(c.confereAcesso(id, senha))
				return c;
		return null;
	}
	
	/**
	 * Cadastra uma nova conta no sitema.
	 * <p>
	 * Caso ja haja uma conta com a mesma identificacao, a operacao nao sera realizada.
	 * 
	 * @param id  a identificacao da conta
	 * @param senha  a senha usada para o acesso da conta
	 * @param permissao  o nivel de acesso que a conta possui
	 * @return true se a operacao foi realizada com sucesso, false caso contrario
	 */
	public boolean cadastra(String id, String senha, Permissao permissao) {
		if(contas == null) carregaContas();
		
		Conta novaConta = new Conta(id, senha, permissao);
		return contas.add(novaConta);
	}
	
	/**
	 * Remove uma conta do sistema.
	 * 
	 * @param id  a identificacao da conta em questao
	 * @return true caso a operacao tenha sido concluida com sucesso, false caso nao haja uma conta correspondente
	 */
	public boolean remove(String id) {
		if(id == null)
			throw new IllegalArgumentException();
		
		if(contas == null) carregaContas();
		
		Conta alvo = new Conta(id, "", Permissao.FUNCIONARIO);
		return contas.remove(alvo);
	}

	private void carregaContas() {		
		Object leitura = Arquivo.carregaObjeto(arquivo);
		contas = confereIntegridade(leitura);
	}
	
	/**
	 * Salva as informacoes das contas de modo criptografado no arquivo defenido na criacao do objeto.
	 * 
	 * @return true se a operacao foi concluida com sucesso, false caso contrario
	 */
	public boolean salvaContas() {
		return Arquivo.salvaObjeto(contas, arquivo);
	}

	@SuppressWarnings("unchecked")
	private Set<Conta> confereIntegridade(Object alvo) {
		if(!(alvo instanceof Set)) 
			return new HashSet<Conta>();
		Set<Conta> set = (Set<Conta>) alvo;
		
		for(Object o : set)
			if(!(o instanceof Conta))
				return new HashSet<Conta>();
		
		return set;
	}
}
