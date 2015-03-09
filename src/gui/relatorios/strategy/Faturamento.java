package gui.relatorios.strategy;

import gui.Sistema;

import java.text.DecimalFormat;
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

public class Faturamento extends Estrategia {

	@Override
	public String geraRelatorioMensal(JComboBox<?> cb, int anoRelatorio) {

		resetarVariaveis();

		String dataAtual = new SimpleDateFormat("dd/MM/yyyy").format(Calendar
				.getInstance().getTime());
		String texto = "";

		texto += "Data de consulta: " + dataAtual + "\n";
		texto += "Faturamento mensal\n";
		texto += "M\u00EAs: " + cb.getSelectedItem() + "\n\n";

		calculaRelatorioMensal(texto, cb.getSelectedIndex(), anoRelatorio);
		DecimalFormat df = new DecimalFormat("0.00");

		texto += "Bab\u00E1s:\t\tR$ " + df.format(baba) + "\n";
		texto += "Camas Extras:\tR$ " + df.format(cama) + "\n";
		texto += "Carros:\t\tR$ " + df.format(carro) + "\n";
		texto += "Quartos:\t\tR$ " + df.format(quarto) + "\n";
		texto += "Restaurantes:\tR$ " + df.format(restaurante) + "\n\n";

		texto += "Faturamento mensal:\tR$ " + df.format(mes) + "\n";

		return texto;
	}

	@Override
	public String geraRelatorioAnual(JComboBox<?> cb) {

		resetarVariaveis();

		String dataAtual = new SimpleDateFormat("dd/MM/yyyy").format(Calendar
				.getInstance().getTime());
		String texto = "";

		texto += "Data de consulta: " + dataAtual + "\n";

		calculaRelatorioAnual(cb);
		DecimalFormat df = new DecimalFormat("0.00");

		texto += "Faturamento anual\n";
		texto += "Ano: " + cb.getSelectedItem() + "\n\n";

		texto += "Janeiro:\t\tR$ " + df.format(meses[0]) + "\n";
		texto += "Fevereiro:\t\tR$ " + df.format(meses[1]) + "\n";
		texto += "Mar\u00E7o:\t\tR$ " + df.format(meses[2]) + "\n";
		texto += "Abril:\t\tR$ " + df.format(meses[3]) + "\n";
		texto += "Maio:\t\tR$ " + df.format(meses[4]) + "\n";
		texto += "Junho:\t\tR$ " + df.format(meses[5]) + "\n";
		texto += "Julho:\t\tR$ " + df.format(meses[6]) + "\n";
		texto += "Agosto:\t\tR$ " + df.format(meses[7]) + "\n";
		texto += "Setembro:\t\tR$ " + df.format(meses[8]) + "\n";
		texto += "Outubro:\t\tR$ " + df.format(meses[9]) + "\n";
		texto += "Novembro:\t\tR$ " + df.format(meses[10]) + "\n";
		texto += "Dezembro:\t\tR$ " + df.format(meses[11]) + "\n\n";

		texto += "Faturamento anual:\tR$ " + df.format(ano) + "\n";

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
		DecimalFormat df = new DecimalFormat("0.00");

		texto += "Faturamento de esta\u00E7\u00E3o\n";
		texto += "Esta\u00E7\u00E3o: " + estacao1 + "\n\n";

		texto += "Bab\u00E1s:\t\tR$ " + df.format(baba) + "\n";
		texto += "Camas Extras:\tR$ " + df.format(cama) + "\n";
		texto += "Carros:\t\tR$ " + df.format(carro) + "\n";
		texto += "Quartos:\t\tR$ " + df.format(quarto) + "\n";
		texto += "Restaurantes:\tR$ " + df.format(restaurante) + "\n";

		texto += "\n";
		texto += "Faturamento da esta\u00E7\u00E3o:\tR$ " + df.format(estacao) + "\n";
		return texto;
	}

	private void calculaRelatorioAnual(JComboBox<?> cb) {

		int anoFatura = (int) cb.getSelectedItem();
		for (Hospede hospede : Sistema.getHotel().getHospedes()) {
			for (Contrato contrato : hospede.getContratos()) {
				if (contrato.getEstado() == EstadoDeContrato.FECHADO
						&& contrato.getDataCheckOut().get(Calendar.YEAR) == anoFatura) {
					ano += contrato.getFatura();
					meses[contrato.getDataCheckOut().get(Calendar.MONTH)] += contrato.getFatura();
				}
			}
		}
	}

	private void calculaRelatorioEstacao(Estacao estacao1, int anoRelatorio) {
		for (Hospede hospede : Sistema.getHotel().getHospedes()) {
			for (Contrato contrato : hospede.getContratos()) {
				if (contrato.getEstado() == EstadoDeContrato.FECHADO
						&& contrato.getEstacao().equals(estacao1)) {
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
		double preco;
		for (Pagavel p : contrato.getServicos()) {
			preco = p.getPreco() * contrato.getEstacao().getTarifa();
			valor += preco;

			if (p instanceof Babysitter) {
				baba += preco;
			} else if (p instanceof CamaExtra) {
				cama += preco;
			} else if (p instanceof Carro) {
				carro += preco;
			} else if (p instanceof Quarto) {
				quarto += preco;
			} else if (p instanceof Refeicao) {
				restaurante += preco;
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
		geraGraficoPizzaPadrao(panel, cb, "Faturamento do m\u00EAs de ", mes);
	}

	@Override
	public void geraGraficoEstacao(JPanel panel, JComboBox<?> cb) {
		geraGraficoPizzaPadrao(panel, cb, "Faturamento da esta\u00E7\u00E3o ", estacao);
	}

}
