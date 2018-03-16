package br.com.buggie.fractal.testes.banco;

import org.junit.Test;

import br.com.buggie.config.pagebase.PageHomeFractal;
import br.com.buggie.config.testbase.BaseTestCase;
import br.com.buggie.config.util.Utils;
import br.com.buggie.fractal.pages.banco.PageHomeBanco;
import br.com.buggie.fractal.pages.banco.PageIncluirBanco;

/**
 * 
 * Classe de testes com cenários relacionados ao menu BANCOS
 * @author Jarbas
 * 
 * */
public class TesteManterBancos_IT extends BaseTestCase {

	PageHomeBanco    pageHomeBanco    = new PageHomeBanco();
	PageHomeFractal  pageHomeFractal  = new PageHomeFractal();
	PageIncluirBanco pageIncluirBanco = new PageIncluirBanco();
	
	@Test
	public void cadastrarBancoComSucesso(){
		String banco = Utils.geraNomeBanco();
		pageHomeFractal.navegarParaPaginaDeListagemDeBancos();
		pageHomeBanco.navegarParaPaginaDeInclusaoDeBanco();
		pageIncluirBanco.incluirBanco(banco);
		pageHomeFractal.navegarParaPaginaDeListagemDeBancos();
		pageHomeFractal.pesquisarBanco(banco);
		pageHomeFractal.validarRetornoPesquisarBanco(banco);
	}
}