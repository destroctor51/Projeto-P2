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

public class Faturamento extends Estrategia {

	@Override
	public String geraRelatorioMensal(JComboBox<?> cb, int anoRelatorio) {

		resetarVariaveis();

		String dataAtual = new SimpleDateFormat("dd/MM/yyyy").format(Calendar
				.getInstance().getTime());
		String texto = "";

		texto += "Data: " + dataAtual + "\n";
		texto += "Faturamento mensal\n";
		texto += "Mes: " + cb.getSelectedItem() + "\n\n";

		calculaRelatorioMensal(texto, cb.getSelectedIndex(), anoRelatorio);

		texto += "Babas: " + baba + "\n";
		texto += "Camas Extras: " + cama + "\n";
		texto += "Carros: " + carro + "\n";
		texto += "Quartos: " + quarto + "\n";
		texto += "Restaurantes: " + restaurante + "\n\n";

		texto += "Fatuamento mensal: " + mes + "\n";

		return texto;
	}

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

		texto += "Faturamento anuais: " + ano + "\n";

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

		texto += "Faturamento de estacao\n";
		texto += "Estacao: " + estacao1 + "\n\n";

		texto += "Babas: " + baba + "\n";
		texto += "Camas Extras: " + cama + "\n";
		texto += "Carros: " + carro + "\n";
		texto += "Quartos: " + quarto + "\n";
		texto += "Restaurantes: " + restaurante + "\n";

		texto += "\n";
		texto += "Fatuamento da estacao: " + estacao + "\n";
		return texto;
	}

	private void calculaRelatorioAnual(JComboBox<?> cb) {

		int anoFatura = (int) cb.getSelectedItem();
		double valor = 0.0;
		for (Hospede hospede : Sistema.getHotel().getHospedes()) {
			for (Contrato contrato : hospede.getContratos()) {
				if (contrato.getEstado() == EstadoDeContrato.FECHADO
						&& contrato.getDataCheckOut().get(Calendar.YEAR) == anoFatura) {
					valor = calculaValor(contrato);
					ano += valor;
					meses[contrato.getDataCheckOut().get(Calendar.MONTH)] += valor;
				}
			}
		}
	}

	private void calculaRelatorioEstacao(Estacao estacao1, int anoRelatorio) {
		for (Hospede hospede : Sistema.getHotel().getHospedes()) {
			for (Contrato contrato : hospede.getContratos()) {
				if (contrato.getEstado() == EstadoDeContrato.FECHADO
						&& estacao1
								.entraEmConflito(new Periodo(contrato
										.getDataCheckOut(), contrato
										.getDataCheckOut())) && contrato.getDataCheckOut().get(Calendar.YEAR)== anoRelatorio) {
					estacao += calculaValor(contrato);
				}
			}
		}
	}
	
	private void calculaRelatorioMensal(String texto, int mesRelatorio, int anoRelatorio) {
		for (Hospede hospede : Sistema.getHotel().getHospedes()) {
			for (Contrato contrato : hospede.getContratos()) {
				if (contrato.getEstado() == EstadoDeContrato.FECHADO
						&& contrato.getDataCheckOut().get(Calendar.MONTH) == mesRelatorio && contrato.getDataCheckOut().get(Calendar.YEAR) == anoRelatorio) {
					mes += calculaValor(contrato);
				}
			}
		}
	}

	private double calculaValor(Contrato contrato) {
		double valor = 0.0;
		for (Pagavel p : contrato.getServicos()) {
			valor += p.getPreco();

			if (p instanceof Babysitter) {
				baba += p.getPreco();
			} else if (p instanceof CamaExtra) {
				cama += p.getPreco();
			} else if (p instanceof Carro) {
				carro += p.getPreco();
			} else if (p instanceof Quarto) {
				quarto += p.getPreco();
			} else if (p instanceof Refeicao) {
				restaurante += p.getPreco();
			}
		}
		return valor;
	}

	@Override
	public void geraGraficoAnual(JPanel panel, JComboBox<?> cb) {
		geraGraficoLinhaPadrao(panel, cb, "Faturamento no ano de " , "Faturamento");
	}

	@Override
	public void geraGraficoMensal(JPanel panel, JComboBox<?> cb) {
		geraGraficoPizzaPadrao(panel, cb, "Faturamento do mes de ", mes);
	}

	@Override
	public void geraGraficoEstacao(JPanel panel, JComboBox<?> cb) {
		geraGraficoPizzaPadrao(panel, cb, "Faturamento da estacao ", estacao);
	}

}
