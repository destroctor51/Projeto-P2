package login;

/**
 * Uma enumeracao de niveis de acesso que um usuario pode possuir.
 * <p>
 * Os niveis estao ordenados de forma crescente com relacao a liberdade que possuem.
 * 
 * @author Victor Andrade de Almeida
 */
public enum Permissao {
	FUNCIONARIO, ADMINISTRADOR, GERENTE;
}
