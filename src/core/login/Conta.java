package core.login;

import java.io.Serializable;

/**
 * Uma representacao de uma conta usada para acessar um servico qualquer.
 * <p>
 * Esse conta possui uma string de identificacao, uma senha e um nivel de acesso.
 *
 * @author Victor Andrade de Almeida
 */
public class Conta implements Serializable {

	private static final long serialVersionUID = 1L;

	private Permissao permissao;
	private String nome, id, senha;

	/**
	 * Cria uma conta com um id, uma senha e um nivel de acesso.
	 *
	 * @param id  a identificacao da conta
	 * @param senha  a senha usada para acessar a conta
	 * @param permissao  o nivel de acesso que a conta possui
	 */
	public Conta(String id, String senha, Permissao permissao) {
		if(id == null || senha == null || permissao == null)
			throw new IllegalArgumentException();

		this.id = id;
		this.senha = senha;
		this.permissao = permissao;
	}

	/**
	 * @return o id da conta
	 */
	public String getID() {
		return id;
	}

	/**
	 * @return o nivel de permissao da conta
	 */
	public Permissao getPermissao() {
		return permissao;
	}

	/**
	 * Confere se a conta atende ao requerimento de acesso especificado.
	 *
	 * @param requerimento  o nivel de acesso necessario
	 * @return true se a conta atende ao requerimento, false caso contrario
	 */
	public boolean possuiPermissao(Permissao requerimento) {
		if(requerimento == null) return true;
		return permissao.ordinal() >= requerimento.ordinal();
	}

	/**
	 * Modifica o nivel de acesso da conta.
	 *
	 * @param permissao  o novo nivel de acesso
	 */
	public void setPermissao(Permissao permissao) {
		if(permissao == null)
			throw new IllegalArgumentException();
		this.permissao = permissao;
	}

	/**
	 * Relaciona um nome a conta, para facilitar sua identificacao e melhorar sua seguranca.
	 *
	 * @param nome  o nome a ser relacionado, nao null
	 */
	public void setNome(String nome) {
		if(nome == null)
			throw new IllegalArgumentException();
		this.nome = nome;
	}

	/**
	 * Troca a senha atual por uma nova.
	 * <p>
	 * A senha so sera trocada se a senha antiga for fornecida corretamente.
	 *
	 * @param senhaAntiga  a senha atual, usada para fins de seguranca
	 * @param novaSenha  a senha desejada
	 * @return true se a senha foi trocada, false caso a operacao nao tenha sido permitida
	 */
	public boolean setSenha(String senhaAntiga, String novaSenha) {
		if(novaSenha == null)
			throw new IllegalArgumentException();

		if(!senha.equals(senhaAntiga))
			return false;
		senha = novaSenha;
		return true;
	}

	/**
	 * Confere se a conta possui a mesma identificacao e senha fornecidas.
	 *
	 * @param id  a identificacao a ser testada
	 * @param senha  a senha a ser testada
	 * @return true se a identificacao e a senha estiverem corretas, false caso contario
	 */
	public boolean confereAcesso(String id, String senha) {
		return this.id.equals(id) && this.senha.equals(senha);
	}

	/**
	 * Retorna um inteiro que representa a conta.
	 * <p>
	 * O numero retornado e gerado usando apenas a identificacao da conta.
	 *
	 * @return o codigo que representa a conta
	 */
	@Override
	public int hashCode() {
		return id.hashCode();
	}

	/**
	 * Compara a conta com um objeto para ver se sao iguais.
	 * <p>
	 * Eles serao iguais somente se o objeto for uma Conta e a identificacao dos dois forem iguais.
	 *
	 * @return true se forem iguais, false caso contrario
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Conta))
			return false;

		Conta outro = (Conta) obj;
		return id.equals(outro.id);
	}

	@Override
	public String toString() {
		return nome == null? id : nome;
	}
}
