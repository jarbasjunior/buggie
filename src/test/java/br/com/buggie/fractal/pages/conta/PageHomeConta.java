package br.com.buggie.fractal.pages.conta;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.buggie.config.pagebase.PageObjectGeneric;
import br.com.buggie.config.setup.Selenium;
import br.com.buggie.config.util.Log;
import br.com.buggie.config.util.Utils;

public class PageHomeConta extends PageObjectGeneric<PageHomeConta> {

	public PageHomeConta() {
		PageFactory.initElements(Selenium.getDriver(), this);
	}

	@FindBy(xpath = "//*[@id='page-wrapper']//../h1")
	WebElement titleContas;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../th[text()='Nome do Proprietário']")
	WebElement titleProprietario;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../th[text()='Nome do Banco']")
	WebElement titleNomeBanco;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../th[text()='Ações']")
	WebElement titleAcoes;
	
	@FindBy(id = "term")
	WebElement fieldConta;
	
	@FindBy(xpath = "//*[@type='button']//../span[text()='Cadastrar Novo']")
	WebElement btCadastrarNovo;
	
	@FindBy(name = "commit")
	WebElement btPesquisar;
	
	@FindBy(xpath = "//*[@class='btn btn-danger btn-sm glyphicon glyphicon-trash']")
	WebElement btExcluirConta;
	
	@FindBy(xpath = "//*[@data-bb-handler='confirm']")
	WebElement btConfirmarAlerta;
	
	@FindBy(xpath = "//*[@rel='next']//../a[contains(.,'Próximo')]")
	WebElement btProximo;
	
	@FindBy(xpath = "//*[@class='last next']/a")
	WebElement btUltimo;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//./tr[1]/td[2]")
	WebElement firstRegistro;
	
	@FindBy(xpath = "//*[@class='bootbox-body']")
	WebElement msgAlerta;
	
	@FindBy(xpath = "//*[@id='toast-container']/div/div")
	WebElement msgFeedback;
	
	public void navegarParaPaginaDeInclusaoDeConta() {
		aguardarElementoVisivel(titleContas);
		validarOrtografiaPageHomeContas();
		Log.info("Navegando para página de inclusão de conta...");
		waitAndClick(btCadastrarNovo);
	}
	
	public void navegarParaPaginaDeVisualizacaoDeConta(String conta) {
		aguardarElementoVisivel(titleContas);
		validarOrtografiaPageHomeContas();
		Log.info("Navegando para página de visualização de conta...");
		By xpathConta = By.xpath("//*[@id='page-wrapper']//./td[text()='"+conta+"']//../td[4]");
		waitAndClick(Selenium.getDriver().findElement(xpathConta));
	}
	
	public void pesquisarConta(String conta) {
		aguardarElementoVisivel(titleContas);
		validarOrtografiaPageHomeContas();
		preencherCampo(fieldConta, conta);
		waitAndClick(btPesquisar);
		Log.info("Pesquisando conta de número ["+conta+"]...");
	}
	
	public void navegarParaPaginaDeAtualizacaoDeConta(String conta) {
		aguardarElementoVisivel(titleContas);
		validarOrtografiaPageHomeContas();
		Log.info("Navegando para página de atualização de conta...");
		By xpathConta = By.xpath("//*[@id='page-wrapper']//./td[text()='"+conta+"']//../td[5]");
		waitAndClick(Selenium.getDriver().findElement(xpathConta));
	}
	
	public String capturarNomePrimeiroRegistro() {
		aguardarElementoVisivel(titleContas);
		validarOrtografiaPageHomeContas();
		Log.info("Capturando nome do primeiro registro do tipo conta na tela...");
		String conta = firstRegistro.getText().trim();
		Log.info("Nome encontrado no primeiro registro ["+conta+"].");
		return conta;
	}
	
	public void irParaPaginaDeAtualizacaoDoPrimeiroRegistro() {
		aguardarElementoVisivel(titleContas);
		validarOrtografiaPageHomeContas();
		String conta = firstRegistro.getText().trim();
		waitAndClick(Selenium.getDriver().findElement(By.xpath("//*[@id='page-wrapper']//./tr[1]/td[5]")));
		Log.info("Redirecionando para página de atualização do Proprietário(a) ["+conta+"]...");
	}
	
	public void excluirConta(String proprietario, String banco, String conta, String agencia, String saldo) {
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
	
	public void validarOrtografiaPageHomeContas() {
		aguardarElementoVisivel(titleContas);
		Utils.wait(1000);
		Log.info("Verificando ortografia de page home conta...");
		Utils.assertEquals(getTextElement(titleContas).substring(0, 7).trim() , "Contas");
		Utils.assertEquals(getTextElement(titleProprietario)                  , "Nome do Proprietário");
		Utils.assertEquals(getTextElement(titleNomeBanco)                     , "Nome do Banco");
		Utils.assertEquals(getTextElement(titleAcoes)                         , "Ações");
		Utils.assertEquals(getTextElement(btCadastrarNovo)                    , "Cadastrar Novo");
		Utils.assertEquals(getTextAtributoElement(btPesquisar)                , "Pesquisar");
		Utils.assertEquals(getTextElement(btProximo)                          , "Próximo ›");
		Utils.assertEquals(getTextElement(btUltimo)                           , "Último »");
		Log.info("Ortografia de page home banco validada com sucesso.");
	}
}
