package br.com.buggie.config.pagebase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.buggie.config.setup.Selenium;
import br.com.buggie.config.util.Log;
import br.com.buggie.config.util.Utils;

public class PageHomeFractal extends PageObjectGeneric<PageHomeFractal> {

	public PageHomeFractal() {
		PageFactory.initElements(Selenium.getDriver(), this);
	}

	@FindBy(xpath = "//*[@id='wrapper']//../a[text()='Fractal Tecnologia - Teste']")
	WebElement titleFractal;
	
	@FindBy(xpath = "//*[@id='side-menu']//../a[@href='/banks']")
	WebElement menuBancos;
	
	@FindBy(xpath = "//*[@id='side-menu']//../a[@href='/clients']")
	WebElement menuClientes;
	
	@FindBy(xpath = "//*[@id='side-menu']//../a[@href='/accounts']")
	WebElement menuContas;
	
	@FindBy(xpath = "//*[@id='side-menu']//../a[@href='/employees']")
	WebElement menuFuncionarios;
	
	@FindBy(id = "term")
	WebElement fieldPesquisaBanco;
	
	@FindBy(name = "commit")
	WebElement btPesquisar;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../h3")
	WebElement titleResultadoPesquisa;
	
	public void pesquisarBanco(String banco) {
		aguardarElementoVisivel(titleFractal);
		Log.info("Filtrando pesquisa com o valor banco["+banco+"]...");
		preencherCampo(fieldPesquisaBanco, banco);
		Log.info("Pesquisando banco["+banco+"]...");
		waitAndClick(btPesquisar);
	}
	
	public void validarRetornoPesquisarBanco(String banco) {
		aguardarElementoVisivel(titleResultadoPesquisa);
		By xpathBanco = By.xpath("//*[@id='page-wrapper']//../td[text()='"+banco+"']");
		Log.info("Validando retorno da pesquisa...");
		Utils.assertEquals(titleResultadoPesquisa.getText()                      , "Resultado da pesquisa para: "+banco);
		Utils.assertEquals(Selenium.getDriver().findElement(xpathBanco).getText(), banco);
		Log.info("Retorno da pesquisa validado com sucesso!");
	}
	
	public void navegarParaPaginaDeListagemDeBancos() {
		aguardarElementoVisivel(titleFractal);
		Log.info("Navegando para página de listagem de bancos...");
		waitAndClick(menuBancos);
	}
	public void navegarParaPaginaDeListagemDeContas() {
		aguardarElementoVisivel(titleFractal);
		Log.info("Navegando para página de listagem de contas...");
		waitAndClick(menuContas);
	}
	public void navegarParaPaginaDeListagemDeClientes() {
		aguardarElementoVisivel(titleFractal);
		Log.info("Navegando para página de listagem de clientes...");
		waitAndClick(menuClientes);
	}
	public void navegarParaPaginaDeListagemDeFuncionarios() {
		aguardarElementoVisivel(titleFractal);
		Log.info("Navegando para página de listagem de funcionarios...");
		waitAndClick(menuFuncionarios);
	}
}
