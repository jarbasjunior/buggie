package br.com.buggie.fractal.testes.banco;

import org.junit.Test;

import br.com.buggie.config.pagebase.PageHomeFractal;
import br.com.buggie.config.setup.Selenium;
import br.com.buggie.config.testbase.BaseTestCase;
import br.com.buggie.config.util.Utils;
import br.com.buggie.fractal.pages.banco.PageAtualizarBanco;
import br.com.buggie.fractal.pages.banco.PageHomeBanco;
import br.com.buggie.fractal.pages.banco.PageIncluirBanco;
import br.com.buggie.fractal.pages.banco.PageVisualizarBanco;

/**
 * 
 * Classe de testes com cenários relacionados ao menu BANCOS
 * @author Jarbas
 * 
 * */
public class TesteManterBanco_IT extends BaseTestCase {

	PageHomeBanco       pageHomeBanco        = new PageHomeBanco();
	PageHomeFractal     pageHomeFractal      = new PageHomeFractal();
	PageIncluirBanco    pageIncluirBanco     = new PageIncluirBanco();
	PageAtualizarBanco  pageAtualizacaoBanco = new PageAtualizarBanco();
	PageVisualizarBanco pageVisualizarBanco  = new PageVisualizarBanco();
	
	@Test
	public void verificaTratamentoPageBancoNaoEncontrada () {
		Selenium.getDriver().navigate().to("http://buggie4.fractaltecnologia.com.br/banks/1000");
		pageHomeBanco.isPageNotExists();
	}
	
	@Test
	public void cadastrarBancoComSucesso(){
		String banco = Utils.geraNomeBanco();
		pageHomeFractal.navegarParaPaginaDeListagemDeBancos();
		pageHomeBanco.navegarParaPaginaDeInclusaoDeBanco();
		pageIncluirBanco.incluirBancoSucesso(banco);
		pageHomeFractal.navegarParaPaginaDeListagemDeBancos();
		pageHomeFractal.pesquisarBanco(banco);
		pageHomeFractal.validarRetornoPesquisaBanco(banco);
	}
	
	@Test
	public void cadastrarBancoSemSucessoComCampoNomeEmBranco(){
		pageHomeFractal.navegarParaPaginaDeListagemDeBancos();
		pageHomeBanco.navegarParaPaginaDeInclusaoDeBanco();
		pageIncluirBanco.incluirBancoSemPreencherCampoNome();
		pageHomeFractal.navegarParaPaginaDeListagemDeBancos();
		pageHomeFractal.pesquisarBanco("");
		pageHomeFractal.validarRetornoPesquisaBanco("");
	}
	
	@Test
	public void pesquisarBancoComSucesso(){
		pageHomeFractal.navegarParaPaginaDeListagemDeBancos();
		String banco = pageHomeBanco.capturarNomePrimeiroRegistro();
		pageHomeFractal.pesquisarBanco(banco);
		pageHomeFractal.validarRetornoPesquisaBanco(banco);
	}
	
	@Test
	public void atualizarBancoComSucessoPeloBotaoVisualizarBanco(){
		String novoBanco = Utils.geraNomeBanco();
		pageHomeFractal.navegarParaPaginaDeListagemDeBancos();
		String banco = pageHomeBanco.capturarNomePrimeiroRegistro();
		pageHomeBanco.navegarParaPaginaDeVisualizacaoDeBanco(banco);
		pageVisualizarBanco.navegarParaPageAtualizacaoBanco(banco);
		pageAtualizacaoBanco.atualizarBanco(novoBanco);
		pageHomeFractal.pesquisarBanco(novoBanco);
		pageHomeFractal.validarRetornoPesquisaBanco(novoBanco);
	}
}