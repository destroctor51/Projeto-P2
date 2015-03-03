package gui.components;

import java.util.Calendar;

import core.tempo.Estacao;

public class CalendarioEstacao extends Calendario {

	private static final long serialVersionUID = 1L;

	private Estacao estacao;

	public CalendarioEstacao(Estacao estacao) {
		if(estacao == null)
			throw new IllegalArgumentException();
		this.estacao = estacao;
	}

	public void setEstacao(Estacao estacao) {
		if(estacao == null)
			throw new IllegalArgumentException();
		this.estacao = estacao;
		atualizaDias();
	}

	@Override
	protected boolean isDiaIndisponivel(Calendar dia) {
		if(estacao == null) return false;
		return estacao.contem(dia);
	}

	@Override
	protected boolean isSelecaoInvalida() {
		if(estacao == null) return false;
		return estacao.entraEmConflito(getSelecao());
	}
}
