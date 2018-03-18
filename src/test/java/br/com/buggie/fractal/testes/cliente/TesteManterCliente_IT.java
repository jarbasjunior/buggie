package br.com.buggie.fractal.testes.cliente;

import org.junit.Test;

import br.com.buggie.config.pagebase.PageHomeFractal;
import br.com.buggie.config.setup.Selenium;
import br.com.buggie.config.testbase.BaseTestCase;
import br.com.buggie.fractal.pages.cliente.PageAtualizarCliente;
import br.com.buggie.fractal.pages.cliente.PageHomeCliente;

/**
 * 
 * Classe de testes com cenários relacionados ao menu CLIENTES
 * @author Jarbas
 * 
 * */
public class TesteManterCliente_IT extends BaseTestCase {

	PageHomeCliente      pageHomeCliente        = new PageHomeCliente();
	PageHomeFractal      pageHomeFractal        = new PageHomeFractal();
	PageAtualizarCliente pageAtualizacaoCliente = new PageAtualizarCliente();
	
	@Test
	public void verificaTratamentoPageClienteNaoEncontrada () {
		Selenium.getDriver().navigate().to("http://buggie4.fractaltecnologia.com.br/clients/1000");
		pageHomeCliente.isPageNotExists();
	}
	
	@Test
	public void pesquisarClienteComSucesso(){
		pageHomeFractal.navegarParaPaginaDeListagemDeClientes();
		String cliente = pageHomeCliente.capturarNomePrimeiroRegistro();
		pageHomeCliente.pesquisarCliente(cliente);
		pageHomeFractal.validarRetornoPesquisaCliente(cliente);
	}
	
	@Test
	public void validarMensagemFeedbackOrientacaoPreenchimentoIncorretoDeCliente(){
		pageHomeFractal.navegarParaPaginaDeListagemDeClientes();
		String cliente = pageHomeCliente.capturarNomePrimeiroRegistro();
		pageHomeCliente.navegarParaPaginaDeAtualizacaoDeCliente(cliente);
		pageAtualizacaoCliente.removerValoresCamposCliente(cliente);
		pageAtualizacaoCliente.validarMsgFeedbackOrientacaoCamposEmBranco();
	}
}