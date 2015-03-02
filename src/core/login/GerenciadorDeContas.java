package core.login;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Um objeto que e capaz de gerenciar as contas de um sistema.
 *
 * @author Victor Andrade de Almeida
 */
public class GerenciadorDeContas implements Serializable, Iterable<Conta> {

	private static final long serialVersionUID = 1L;

	private Set<Conta> contas;

	/**
	 * Cria um gerenciador de contas vazio.
	 */
	public GerenciadorDeContas() {
		contas = new HashSet<>();
	}

	/**
	 * Acessa uma conta especifica do sistema.
	 *
	 * @param id  a identificacao da conta em questao
	 * @param senha  a senha da conta em questao
	 * @return a conta desejada ou null caso a identificacao ou a senha nao baterem
	 */
	public Conta login(String id, String senha) {
		for(Conta c : contas)
			if(c.confereAcesso(id, senha))
				return c;
		return null;
	}

	/**
	 * Recupera a conta de um usuario em especifico.
	 *
	 * @param usuario  o nome de usuario
	 * @return a conta do usuario dado, ou null caso o usuario nao exista
	 */
	public Conta getConta(String usuario) {
		for(Conta c : contas)
			if(c.getID().equals(usuario))
				return c;
		return null;
	}

	/**
	 * Cadastra uma nova conta no sitema.
	 * <p>
	 * Caso ja haja uma conta com a mesma identificacao, a operacao nao sera realizada.
	 *
	 * @param id  a identificacao da conta
	 * @param permissao  o nivel de acesso que a conta possui
	 * @param nome  o nome real do usuario
	 * @param email  o e-mail do usuario
	 * @param senha  a senha usada para o acesso da conta
	 * @return a nova conta se a operacao foi realizada com sucesso, null caso contrario
	 */
	public Conta cadastra(String id, Permissao permissao, String nome, String email, String senha) {
		Conta novaConta = new Conta(id, permissao, nome, email, senha);
		return contas.add(novaConta)? novaConta : null;
	}

	/**
	 * Remove uma conta do sistema.
	 *
	 * @param conta  a conta em questao
	 * @return true caso a operacao tenha sido concluida com sucesso, false caso nao haja uma conta correspondente
	 */
	public boolean remove(Conta conta) {
		return contas.remove(conta);
	}

	/**
	 * Confere se ha alguma conta cadastrada.
	 *
	 * @return true se houve, false caso contrario
	 */
	public boolean vazio() {
		return contas.isEmpty();
	}

	@Override
	public Iterator<Conta> iterator() {
		return contas.iterator();
	}
}
