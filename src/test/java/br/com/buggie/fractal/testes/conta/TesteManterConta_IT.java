package br.com.buggie.fractal.testes.conta;

import org.junit.Test;

import br.com.buggie.config.pagebase.PageHomeFractal;
import br.com.buggie.config.setup.Selenium;
import br.com.buggie.config.testbase.BaseTestCase;
import br.com.buggie.config.util.Utils;
import br.com.buggie.fractal.model.ModelConta;
import br.com.buggie.fractal.pages.conta.PageAtualizarConta;
import br.com.buggie.fractal.pages.conta.PageHomeConta;
import br.com.buggie.fractal.pages.conta.PageIncluirConta;
import br.com.buggie.fractal.pages.conta.PageVisualizarConta;

/**
 * 
 * Classe de testes com cenários relacionados ao menu CLIENTES
 * @author Jarbas
 * 
 * */
public class TesteManterConta_IT extends BaseTestCase {

	PageHomeConta       pageHomeConta       = new PageHomeConta();
	PageHomeFractal     pageHomeFractal     = new PageHomeFractal();
	PageIncluirConta    pageIncluirConta    = new PageIncluirConta();
	PageAtualizarConta  pageAtualizarConta  = new PageAtualizarConta();
	PageVisualizarConta pageVisualizarConta = new PageVisualizarConta();
	
	@Test
	public void verificaTratamentoPageContaNaoEncontrada () {
		Selenium.getDriver().navigate().to("http://buggie4.fractaltecnologia.com.br/accounts/1000");
		pageHomeConta.isPageNotExists();
	}
	
	@Test
	public void validarMensagemFeedbackOrientacaoPreenchimentoIncorretoDeConta(){
		pageHomeFractal.navegarParaPaginaDeListagemDeContas();
		String conta = pageHomeConta.capturarNomePrimeiroRegistro();
		pageHomeConta.navegarParaPaginaDeAtualizacaoDeConta(conta);
		pageAtualizarConta.removerValoresCamposConta(conta);
		pageAtualizarConta.validarMsgFeedbackOrientacaoCamposEmBranco();
	}
	
	@Test
	public void cadastrarContaComSucesso(){
		String conta        = Utils.geraNumeroConta();
		String saldo        = Utils.geraSaldoAtual();
		String banco        = "DINERS_CLUB";
		String agencia      = Utils.geraNumeroAgencia();
		String proprietario = "Aurora Pimenta";
		pageHomeFractal.navegarParaPaginaDeListagemDeContas();
		pageHomeConta.navegarParaPaginaDeInclusaoDeConta();
		pageIncluirConta.incluirContaSucesso(proprietario, banco, conta, agencia, saldo);
		pageHomeFractal.navegarParaPaginaDeListagemDeContas();
		pageHomeConta.pesquisarConta(conta);
		pageVisualizarConta.validarRetornoDePesquisaDeConta(proprietario, banco, conta, agencia, saldo);
	}
	
	@Test
	public void cadastrarMaisDeUmaContaParaMesmaAgenciaContaComSucesso(){
		String banco        = "SANTANDER UK PLC";
		String conta        = Utils.geraNumeroConta();
		String saldo        = Utils.geraSaldoAtual();
		String proprietario = "Aurora Pimenta";
		pageHomeFractal.navegarParaPaginaDeListagemDeContas();
		pageHomeConta.irParaPaginaDeAtualizacaoDoPrimeiroRegistro();
		String agencia = pageAtualizarConta.getAgencia();
		pageHomeFractal.navegarParaPaginaDeListagemDeContas();
		pageHomeConta.navegarParaPaginaDeInclusaoDeConta();
		pageIncluirConta.incluirContaSucesso(proprietario, banco, conta, agencia, saldo);
		pageHomeFractal.navegarParaPaginaDeListagemDeContas();
		pageHomeConta.pesquisarConta(conta);
		pageVisualizarConta.validarRetornoDePesquisaDeConta(proprietario, banco, conta, agencia, saldo);
	}
	
	@Test
	public void pesquisarContaComSucesso(){
		String proprietario = "José Bulhões Filho";
		String banco        = "ABN AMRO HOARE GOVETT CORPORATE FINANCE LTD.";
		String agencia      = "4360";
		String saldo        = "2451,67";
		String conta        = "10341";
		pageHomeFractal.navegarParaPaginaDeListagemDeContas();
		pageHomeConta.pesquisarConta(conta);
		pageVisualizarConta.validarRetornoDePesquisaDeConta(proprietario, banco, conta, agencia, saldo);
	}
	
	@Test
	public void excluirContaComSucessoPelaTelaDeListagemDeConta(){
		pageHomeFractal.navegarParaPaginaDeListagemDeContas();
		pageHomeConta.irParaPaginaDeAtualizacaoDoPrimeiroRegistro();
		ModelConta oConta = pageAtualizarConta.getPrimeiroRegistroConta();
		pageAtualizarConta.cancelarAtualizacao();
		pageHomeConta.excluirConta(oConta.getProprietario(), oConta.getBanco(), oConta.getConta(), oConta.getAgencia(), oConta.getSaldo());
		pageHomeConta.pesquisarConta(oConta.getConta());
		pageVisualizarConta.validarRetornoDePesquisaDeConta(oConta.getProprietario(), oConta.getBanco(), oConta.getConta(), oConta.getAgencia(), oConta.getSaldo());
	}
	
	@Test
	public void excluirContaComSucessoPelaTelaDePesquisaDeConta(){
		pageHomeFractal.navegarParaPaginaDeListagemDeContas();
		pageHomeConta.irParaPaginaDeAtualizacaoDoPrimeiroRegistro();
		ModelConta oConta = pageAtualizarConta.getPrimeiroRegistroConta();
		pageAtualizarConta.cancelarAtualizacao();
		pageHomeConta.pesquisarConta(oConta.getConta());
		pageVisualizarConta.excluirConta(oConta.getProprietario(), oConta.getBanco(), oConta.getConta(), oConta.getAgencia(), oConta.getSaldo());
		pageHomeConta.pesquisarConta(oConta.getConta());
		pageVisualizarConta.validarRetornoDePesquisaDeConta(oConta.getProprietario(), oConta.getBanco(), oConta.getConta(), oConta.getAgencia(), oConta.getSaldo());
	}
}