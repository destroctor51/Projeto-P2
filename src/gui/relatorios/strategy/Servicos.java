package gui.relatorios.strategy;

import gui.Sistema;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import core.hotel.Contrato;
import core.hotel.EstadoDeContrato;
import core.hotel.Hospede;
import core.interfaces.Pagavel;
import core.servicos.alugaveis.Babysitter;
import core.servicos.alugaveis.CamaExtra;
import core.servicos.devolviveis.Carro;
import core.servicos.devolviveis.Quarto;
import core.servicos.pagaveis.Refeicao;
import core.tempo.Estacao;
import core.tempo.Periodo;

public class Servicos extends Estrategia {

	@Override
	public String geraRelatorioAnual(JComboBox<?> cb) {

		resetarVariaveis();

		String dataAtual = new SimpleDateFormat("dd/MM/yyyy").format(Calendar
				.getInstance().getTime());
		String texto = "";

		texto += "Data: " + dataAtual + "\n";

		calculaRelatorioAnual(cb);

		texto += "Faturamento anual\n";
		texto += "Ano: " + cb.getSelectedItem() + "\n\n";

		texto += "Janeiro: " + meses[0] + "\n";
		texto += "Fevereiro: " + meses[1] + "\n";
		texto += "Marco: " + meses[2] + "\n";
		texto += "Abril: " + meses[3] + "\n";
		texto += "Maio: " + meses[4] + "\n";
		texto += "Junho: " + meses[5] + "\n";
		texto += "Julho: " + meses[6] + "\n";
		texto += "Agosto: " + meses[7] + "\n";
		texto += "Setembro: " + meses[8] + "\n";
		texto += "Outubro: " + meses[9] + "\n";
		texto += "Novembro: " + meses[10] + "\n";
		texto += "Dezembro: " + meses[11] + "\n\n";

		texto += "Servicos anuais: " + ano + "\n";

		return texto;
	}

	@Override
	public String geraRelatorioMensal(JComboBox<?> cb, int anoRelatorio) {

		resetarVariaveis();

		String dataAtual = new SimpleDateFormat("dd/MM/yyyy").format(Calendar
				.getInstance().getTime());
		String texto = "";

		texto += "Data: " + dataAtual + "\n";
		texto += "Servicos mensais\n\n";
		texto += "Mes: " + cb.getSelectedItem() + "\n\n";

		calculaRelatorioMensal(texto, cb.getSelectedIndex(), anoRelatorio);

		texto += "Babas: " + baba + "\n";
		texto += "Camas Extras: " + cama + "\n";
		texto += "Carros: " + carro + "\n";
		texto += "Quartos: " + quarto + "\n";
		texto += "Restaurantes: " + restaurante + "\n\n";

		texto += "Servicos mensais: " + mes + "\n";

		return texto;
	}

	@Override
	public String geraRelatorioEstacao(Estacao estacao1, int anoRelatorio) {

		resetarVariaveis();

		String dataAtual = new SimpleDateFormat("dd/MM/yyyy").format(Calendar
				.getInstance().getTime());
		String texto = "";

		texto += "Data: " + dataAtual + "\n";
		calculaRelatorioEstacao(estacao1, anoRelatorio);

		texto += "Servicos na estacao\n";
		texto += "Estacao: " + estacao1 + "\n\n";

		texto += "Babas: " + baba + "\n";
		texto += "Camas Extras: " + cama + "\n";
		texto += "Carros: " + carro + "\n";
		texto += "Quartos: " + quarto + "\n";
		texto += "Restaurantes: " + restaurante + "\n\n";

		texto += "Servicos da estacao: " + estacao + "\n";
		return texto;
	}

	private void calculaRelatorioAnual(JComboBox<?> cb) {

		int anoFatura = (int) cb.getSelectedItem();
		for (Hospede hospede : Sistema.getHotel().getHospedes()) {
			for (Contrato contrato : hospede.getContratos()) {
				if (contrato.getEstado() == EstadoDeContrato.FECHADO
						&& contrato.getDataCheckOut().get(Calendar.YEAR) == anoFatura) {
					ano += calculaValor(contrato);
					meses[contrato.getDataCheckOut().get(Calendar.MONTH)] += calculaValor(contrato);
				}
			}
		}
	}

	private double calculaValor(Contrato contrato) {
		int valor = 0;
		for (Pagavel p : contrato.getServicos()) {
			valor++;

			if (p instanceof Babysitter) {
				baba++;
			} else if (p instanceof CamaExtra) {
				cama++;
			} else if (p instanceof Carro) {
				carro++;
			} else if (p instanceof Quarto) {
				quarto++;
			} else if (p instanceof Refeicao) {
				restaurante++;
			}
		}
		return valor;
	}

	private void calculaRelatorioEstacao(Estacao estacao, int anoRelatorio) {
		for (Hospede hospede : Sistema.getHotel().getHospedes()) {
			for (Contrato contrato : hospede.getContratos()) {
				if (contrato.getEstado() == EstadoDeContrato.FECHADO
						&& estacao.entraEmConflito(new Periodo(contrato.getDataCheckOut(), contrato.getDataCheckOut())) && contrato.getDataCheckOut().get(Calendar.YEAR)== anoRelatorio) {
					this.estacao += calculaValor(contrato);
				}
			}
		}
	}

	private void calculaRelatorioMensal(String texto, int mesRelatorio, int anoRelatorio) {
		double valor = 0.0;
		for (Hospede hospede : Sistema.getHotel().getHospedes()) {
			for (Contrato contrato : hospede.getContratos()) {
				if (contrato.getEstado() == EstadoDeContrato.FECHADO
						&& contrato.getDataCheckOut().get(Calendar.MONTH) == mesRelatorio && contrato.getDataCheckOut().get(Calendar.YEAR) == anoRelatorio) {
					valor = calculaValor(contrato);
					mes += valor;
				}
			}
		}
	}

	@Override
	public void geraGraficoAnual(JPanel panel, JComboBox<?> cb) {
		geraGraficoLinhaPadrao(panel, cb, "Servicos mais utilizados no ano de " , "Servicos");
	}

	@Override
	public void geraGraficoMensal(JPanel panel, JComboBox<?> cb) {
		geraGraficoPizzaPadrao(panel, cb, "Servicos mais utilizados mes de ", mes);
	}

	@Override
	public void geraGraficoEstacao(JPanel panel, JComboBox<?> cb) {
		geraGraficoPizzaPadrao(panel, cb, "Servicos mais utilizados na estacao ", estacao);
	}
}
