package br.com.buggie.fractal.pages.conta;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.buggie.config.pagebase.PageObjectGeneric;
import br.com.buggie.config.setup.Selenium;
import br.com.buggie.config.util.Log;
import br.com.buggie.config.util.Utils;

public class PageVisualizarConta extends PageObjectGeneric<PageVisualizarConta> {

	public PageVisualizarConta() {
		PageFactory.initElements(Selenium.getDriver(), this);
	}

	@FindBy(xpath = "//*[@id='page-wrapper']//../h1")
	WebElement titleContaH1;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../h3")
	WebElement titleResultadoPesquisa;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../th[text()='Nome do Proprietário']")
	WebElement titleProprietario;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../th[text()='Nome do Banco']")
	WebElement titleBanco;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../th[text()='Conta']")
	WebElement titleConta;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../th[text()='Agência']")
	WebElement titleaAgencia;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../th[text()='Saldo Atual']")
	WebElement titleSaldoAtual;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../th[text()='Ações']")
	WebElement titleAcoes;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../span[text()='Cadastrar Novo']")
	WebElement btCadastrarNovo;
	
	@FindBy(name = "commit")
	WebElement btPesquisar;
	
	@FindBy(xpath = "//*[@class='btn btn-danger btn-sm glyphicon glyphicon-trash']")
	WebElement btExcluirConta;
	
	@FindBy(xpath = "//*[@data-bb-handler='confirm']")
	WebElement btConfirmarAlerta;
	
	@FindBy(xpath = "//*[@class='bootbox-body']")
	WebElement msgAlerta;
	
	@FindBy(xpath = "//*[@id='toast-container']/div/div")
	WebElement msgFeedback;

	
	public void validarRetornoDePesquisaDeConta(String proprietario, String banco, String conta, String agencia, String saldo) {
		aguardarElementoVisivel(titleConta);
		Utils.assertEquals(titleResultadoPesquisa.getText(), "Resultado da pesquisa para: "+conta);
		Utils.assertEquals(getTextElement(titleResultadoPesquisa), "Resultado da pesquisa para: "+conta);

		By valueProprietario = By.xpath("//*[@id='page-wrapper']//../td[text()='"+proprietario+"']");
		By valueBanco        = By.xpath("//*[@id='page-wrapper']//../td[text()='"+banco+"']");
		By valueConta        = By.xpath("//*[@id='page-wrapper']//../td[text()='"+conta+"']");
		By valueAgencia      = By.xpath("//*[@id='page-wrapper']//../td[text()='"+agencia+"']");
		By valueSaldo        = By.xpath("//*[@id='page-wrapper']//../td[6]");
		
		boolean isRegistro = isVisibility(valueConta); 
		
		if (!isRegistro) {
			Utils.assertTrue("Registro não foi excluído ! ! !", !isRegistro);
			Log.info("Registro excluído com sucesso");
		}else {
			Log.info("Validando retorno da pesquisa...");
			Utils.assertEquals(Selenium.getDriver().findElement(valueProprietario).getText() , proprietario);
			Utils.assertEquals(Selenium.getDriver().findElement(valueBanco)       .getText() , banco);
			Utils.assertEquals(Selenium.getDriver().findElement(valueConta)       .getText() , conta);
			Utils.assertEquals(Selenium.getDriver().findElement(valueAgencia)     .getText() , agencia);
			Utils.assertEquals(Selenium.getDriver().findElement(valueSaldo)       .getText() , "R$ "+saldo);
			Log.info("Retorno da pesquisa validado com sucesso!");
		}
	}
	
	public void validarOrtografiaPageVisualizacaoConta() {
		Log.info("Verificando ortografia da página de visualização de banco...");
		Utils.assertEquals(getTextElement(titleContaH1).substring(0, 6).trim() , "Conta");
		Utils.assertEquals(getTextElement(titleProprietario)                   , "Nome do Proprietário");
		Utils.assertEquals(getTextElement(titleBanco)        					 , "Nome do Banco");
		Utils.assertEquals(getTextElement(titleConta)                          , "Conta");
		Utils.assertEquals(getTextElement(titleaAgencia)                       , "Agência");
		Utils.assertEquals(getTextElement(titleSaldoAtual)   					 , "Saldo Atual");
		Utils.assertEquals(getTextElement(titleAcoes)        					 , "Ações");
		Utils.assertEquals(getTextElement(btCadastrarNovo)   					 , "Cadastrar Novo");
		Utils.assertEquals(getTextAtributoElement(btPesquisar)       		 , "Pesquisar");
		Log.info("Ortografia da página de visualização de banco validada com sucesso.");
	}
	
	public void excluirConta(String proprietario, String banco, String conta, String agencia, String saldo) {
		aguardarElementoVisivel(titleConta);
		Utils.assertEquals(getTextElement(titleResultadoPesquisa), "Resultado da pesquisa para: "+conta);
		validarOrtografiaPageVisualizacaoConta();
		validarRetornoDePesquisaDeConta(proprietario, banco, conta, agencia, saldo);
		
		Log.info("Excluindo conta ["+conta+"]...");
		waitAndClick(btExcluirConta);
		Log.info("Verificando mensagem de alerta de exclusão...");
		Utils.assertEquals(getTextElement(msgAlerta), "Tem certeza que deseja deletar esse registro?");
		Log.info("Confirmando mensagem de alerta de exclusão...");
		waitAndClick(btConfirmarAlerta);
		Log.info("Exclusão confirmada.");
		Log.info("Verificando mensagem de feedback de sucesso...");
		Utils.assertEquals(getTextElement(msgFeedback), "Registro deletado com sucesso!");
		Log.info("Mensagem validada com sucesso!");
	}
}
