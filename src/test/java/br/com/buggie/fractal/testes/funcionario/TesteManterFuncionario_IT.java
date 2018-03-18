package br.com.buggie.fractal.testes.funcionario;

import org.junit.Test;

import br.com.buggie.config.pagebase.PageHomeFractal;
import br.com.buggie.config.setup.Selenium;
import br.com.buggie.config.testbase.BaseTestCase;
import br.com.buggie.fractal.model.ModelFuncionario;
import br.com.buggie.fractal.pages.funcionario.PageHomeFuncionario;
import br.com.buggie.fractal.pages.funcionario.PageVisualizarFuncionario;

/**
 * 
 * Classe de testes com cenários relacionados ao menu CLIENTES
 * @author Jarbas
 * 
 * */
public class TesteManterFuncionario_IT extends BaseTestCase {

	PageHomeFractal           pageHomeFractal           = new PageHomeFractal();
	PageHomeFuncionario       pageHomeFuncionario       = new PageHomeFuncionario();
	PageVisualizarFuncionario pageVisualizarFuncionario = new PageVisualizarFuncionario();
	
	@Test
	public void verificaTratamentoPageContaNaoEncontrada () {
		Selenium.getDriver().navigate().to("http://buggie4.fractaltecnologia.com.br/employees/1000");
		pageHomeFuncionario.isPageNotExists();
	}
	
	@Test
	public void visualizarFuncionarioComSucesso(){
		pageHomeFractal.navegarParaPaginaDeListagemDeFuncionarios();
		ModelFuncionario func = pageHomeFuncionario.capturarRegistroPrimeiroFuncionario();
		pageHomeFuncionario.irParaPaginaDeVisualizacaoDoPrimeiroRegistro();
		pageVisualizarFuncionario.validarRetornoDePesquisaDeConta(func.getRg(), func.getCpf(), func.getNome(), func.getFuncao(), func.getNomeBanco());
	}
}