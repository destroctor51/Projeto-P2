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

public class Servicos extends Estrategia {

	@Override
	public String geraRelatorioAnual(JComboBox<?> cb) {

		resetarVariaveis();

		String dataAtual = new SimpleDateFormat("dd/MM/yyyy").format(Calendar
				.getInstance().getTime());
		String texto = "";

		texto += "Data de consulta: " + dataAtual + "\n";

		calculaRelatorioAnual(cb);

		texto += "Servi\u00E7os anuais\n";
		texto += "Ano: " + cb.getSelectedItem() + "\n\n";

		texto += "Janeiro: " + meses[0] + "\n";
		texto += "Fevereiro: " + meses[1] + "\n";
		texto += "Mar\u00E7o: " + meses[2] + "\n";
		texto += "Abril: " + meses[3] + "\n";
		texto += "Maio: " + meses[4] + "\n";
		texto += "Junho: " + meses[5] + "\n";
		texto += "Julho: " + meses[6] + "\n";
		texto += "Agosto: " + meses[7] + "\n";
		texto += "Setembro: " + meses[8] + "\n";
		texto += "Outubro: " + meses[9] + "\n";
		texto += "Novembro: " + meses[10] + "\n";
		texto += "Dezembro: " + meses[11] + "\n\n";

		texto += "Servi\u00E7os anuais: " + ano + "\n";

		return texto;
	}

	@Override
	public String geraRelatorioMensal(JComboBox<?> cb, int anoRelatorio) {

		resetarVariaveis();

		String dataAtual = new SimpleDateFormat("dd/MM/yyyy").format(Calendar
				.getInstance().getTime());
		String texto = "";

		texto += "Data de consulta: " + dataAtual + "\n";
		texto += "Servi\u00E7os mensais\n";
		texto += "M\u00EAs: " + cb.getSelectedItem() + "\n\n";

		calculaRelatorioMensal(texto, cb.getSelectedIndex(), anoRelatorio);

		texto += "Bab\u00E1s: " + baba + "\n";
		texto += "Camas Extras: " + cama + "\n";
		texto += "Carros: " + carro + "\n";
		texto += "Quartos: " + quarto + "\n";
		texto += "Restaurantes: " + restaurante + "\n\n";

		texto += "Servi\u00E7os mensais: " + mes + "\n";

		return texto;
	}

	@Override
	public String geraRelatorioEstacao(Estacao estacao1, int anoRelatorio) {

		resetarVariaveis();

		String dataAtual = new SimpleDateFormat("dd/MM/yyyy").format(Calendar
				.getInstance().getTime());
		String texto = "";

		texto += "Data de consulta: " + dataAtual + "\n";
		calculaRelatorioEstacao(estacao1, anoRelatorio);

		texto += "Servi\u00E7os na esta\u00E7\u00E3o\n";
		texto += "Esta\u00E7\u00E3o: " + estacao1 + "\n\n";

		texto += "Babas: " + baba + "\n";
		texto += "Camas Extras: " + cama + "\n";
		texto += "Carros: " + carro + "\n";
		texto += "Quartos: " + quarto + "\n";
		texto += "Restaurantes: " + restaurante + "\n\n";

		texto += "Servi\u00E7os da esta\u00E7\u00E3o: " + estacao + "\n";
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
						&& contrato.getEstacao().equals(estacao)) {
					this.estacao += calculaValor(contrato);
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

	@Override
	public void geraGraficoAnual(JPanel panel, JComboBox<?> cb) {
		geraGraficoLinhaPadrao(panel, cb, "Servi\u00E7os mais utilizados no ano de " , "Servi\u00E7os");
	}

	@Override
	public void geraGraficoMensal(JPanel panel, JComboBox<?> cb) {
		geraGraficoPizzaPadrao(panel, cb, "Servi\u00E7os mais utilizados m\u00EAs de ", mes);
	}

	@Override
	public void geraGraficoEstacao(JPanel panel, JComboBox<?> cb) {
		geraGraficoPizzaPadrao(panel, cb, "Servi\u00E7os mais utilizados na esta\u00E7\u00E3o ", estacao);
	}
}
