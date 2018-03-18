package br.com.buggie.fractal.pages.cliente;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.buggie.config.pagebase.PageObjectGeneric;
import br.com.buggie.config.setup.Selenium;
import br.com.buggie.config.util.Log;
import br.com.buggie.config.util.Utils;

public class PageHomeCliente extends PageObjectGeneric<PageHomeCliente> {

	public PageHomeCliente() {
		PageFactory.initElements(Selenium.getDriver(), this);
	}

	@FindBy(xpath = "//*[@id='wrapper']//../a[text()='Fractal Tecnologia - Teste']")
	WebElement titleFractal;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../h1")
	WebElement titleCliente;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../th[text()='Nome']")
	WebElement titleNome;

	@FindBy(xpath = "//*[@id='page-wrapper']//../th[text()='CPF']")
	WebElement titleCPF;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../th[text()='RG']")
	WebElement titleRG;

	@FindBy(xpath = "//*[@id='page-wrapper']//../th[text()='Número de Contas']")
	WebElement titleNumeroContas;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../th[text()='Ações']")
	WebElement titleAcoes;
	
	@FindBy(xpath = "//*[@type='button']//../span[text()='Cadastrar Novo']")
	WebElement btCadastrarNovo;
	
	@FindBy(name = "commit")
	WebElement btPesquisar;
	
	@FindBy(xpath = "//*[@rel='next']//../a[contains(.,'Próximo')]")
	WebElement btProximo;
	
	@FindBy(xpath = "//*[@class='last next']/a")
	WebElement btUltimo;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//./tr[1]/td[2]")
	WebElement firstRegistro;
	
	public void navegarParaPaginaDeInclusaoDeCliente() {
		aguardarElementoVisivel(titleCliente);
		validarOrtografiaPageHomeClientes();
		Log.info("Navegando para página de inclusão de cliente...");
		waitAndClick(btCadastrarNovo);
	}
	
	public void navegarParaPaginaDeVisualizacaoDeCliente(String cliente) {
		aguardarElementoVisivel(titleCliente);
		validarOrtografiaPageHomeClientes();
		Log.info("Navegando para página de visualização de cliente...");
		By xpathCliente = By.xpath("//*[@id='page-wrapper']//./td[text()='"+cliente+"']//../td[3]");
		waitAndClick(Selenium.getDriver().findElement(xpathCliente));
	}
	
	public void navegarParaPaginaDeAtualizacaoDeCliente(String cliente) {
		aguardarElementoVisivel(titleCliente);
		validarOrtografiaPageHomeClientes();
		Log.info("Navegando para página de atualização de cliente...");
		By xpathCliente = By.xpath("//*[@id='page-wrapper']//./td[text()='"+cliente+"']//../td[7]");
		waitAndClick(Selenium.getDriver().findElement(xpathCliente));
	}
	
	public String capturarNomePrimeiroRegistro() {
		aguardarElementoVisivel(titleCliente);
		validarOrtografiaPageHomeClientes();
		Log.info("Capturando nome do primeiro registro do tipo cliente na tela...");
		String cliente = firstRegistro.getText().trim();
		Log.info("Nome encontrado no primeiro registro ["+cliente+"].");
		return cliente;
	}
	
	public void pesquisarCliente(String cliente) {
		aguardarElementoVisivel(titleFractal);
		By xpathCliente = By.xpath("//*[@id='page-wrapper']//../td[text()='"+cliente+"']");
		Log.info("Pesquisando cliente ["+cliente+"]...");
		waitAndClick(Selenium.getDriver().findElement(xpathCliente));
		Log.info("Direcionando para página de visualização do cliente ["+cliente+"]...");
	}
	
	public void validarOrtografiaPageHomeClientes() {
		aguardarElementoVisivel(titleCliente);
		Log.info("Verificando ortografia de page home clientes...");
		Utils.assertEquals(getTextElement(titleCliente).substring(0, 8).trim(), "Clientes");
		Utils.assertEquals(getTextElement(titleNome)                          , "Nome");
		Utils.assertEquals(getTextElement(titleCPF)                           , "CPF");
		Utils.assertEquals(getTextElement(titleRG)                            , "RG");
		Utils.assertEquals(getTextElement(titleNumeroContas)                  , "Número de Contas");
		Utils.assertEquals(getTextElement(titleAcoes)                         , "Ações");
		Utils.assertEquals(getTextElement(btCadastrarNovo)                    , "Cadastrar Novo");
		Utils.assertEquals(getTextElement(btProximo)                          , "Próximo ›");
		Utils.assertEquals(getTextElement(btUltimo)                           , "Último »");
		Log.info("Ortografia de page home clientes validada com sucesso.");
	}
}
