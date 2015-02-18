package core.login;

/**
 * Uma enumeracao de niveis de acesso que um usuario pode possuir.
 * <p>
 * Os niveis estao ordenados de forma crescente com relacao a liberdade que possuem.
 *
 * @author Victor Andrade de Almeida
 */
public enum Permissao {
	FUNCIONARIO("Funcion\u00E1rio"),
	ADMINISTRADOR("Administrador"),
	GERENTE("Gerente");

	private String nome;

	private Permissao(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return nome;
	}
}
