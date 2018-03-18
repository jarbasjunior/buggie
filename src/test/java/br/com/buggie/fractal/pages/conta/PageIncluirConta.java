package br.com.buggie.fractal.pages.conta;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.buggie.config.pagebase.PageObjectGeneric;
import br.com.buggie.config.setup.Selenium;
import br.com.buggie.config.util.Log;
import br.com.buggie.config.util.Utils;

public class PageIncluirConta extends PageObjectGeneric<PageIncluirConta> {

	public PageIncluirConta() {
		PageFactory.initElements(Selenium.getDriver(), this);
	}

	@FindBy(xpath = "//*[@id='page-wrapper']//../h1")
	WebElement titleRegistroNovoConta;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../h3")
	WebElement titleIdentificacaoConta;
	
	@FindBy(xpath = "//*[@for='account_bank']")
	WebElement titleNomeBanco;

	@FindBy(xpath = "//*[@for='account_client']")
	WebElement titleNomeProprietario;

	@FindBy(xpath = "//*[@for='account_number']")
	WebElement titleConta;
	
	@FindBy(xpath = "//*[@for='account_agency']")
	WebElement titleAgencia;
	
	@FindBy(xpath = "//*[@for='account_value']")
	WebElement titleSaldoAtual;
	
	@FindBy(name = "account[bank_id]")
	WebElement comboBanco;
	
	@FindBy(name = "account[client_id]")
	WebElement comboProprietario;
	
	@FindBy(id = "account_number")
	WebElement fieldConta;
	
	@FindBy(id = "account_agency")
	WebElement fieldAgencia;
	
	@FindBy(id = "account_value")
	WebElement fieldSaldoAtual;
	
	@FindBy(name = "commit")
	WebElement btSalvar;
	
	@FindBy(xpath = "//*[@class='btn btn-default']")
	WebElement btCancelar;
	
	@FindBy(xpath = "//*[@id='toast-container']/div/div")
	WebElement msgFeedback;
	
	public void incluirContaSucesso(String proprietario, String banco, String conta, String agencia, String saldo) {
		aguardarElementoVisivel(titleRegistroNovoConta);
		validarOrtografiaPageIncluirConta();
		
		Log.info("Preenchendo campos...");
		Log.info("Banco....... ["+banco+"]");
		Log.info("Proprietário ["+proprietario+"]");
		Log.info("Conta....... ["+conta+"]");
		Log.info("Agência..... ["+agencia+"]");
		Log.info("Saldo....... ["+saldo+"]");
		selecionarValorComboTexto(comboBanco       , banco);
		selecionarValorComboTexto(comboProprietario, proprietario);
		preencherCampo(fieldConta     , conta);
		preencherCampo(fieldAgencia   , agencia);
		preencherCampo(fieldSaldoAtual, saldo);
		Log.info("Salvando novo registro de conta...");
		waitAndClick(btSalvar);
		Log.info("Validando mensagem de feedback de sucesso...");
		Utils.assertEquals(getTextElement(msgFeedback), "Registro incluído com sucesso!");
		Log.info("Mensagem de feedback de sucesso validada");
	}
	
	public void incluirBancoSemPreencherCampoNome() {
		aguardarElementoVisivel(titleRegistroNovoConta);
		validarOrtografiaPageIncluirConta();
		
		Log.info("Salvando banco com campo [Nome] vazio...");
		waitAndClick(btSalvar);
		Log.info("Validando mensagem de feedback de erro...");
		Utils.assertEquals(getTextElement(msgFeedback), "Não foi possível concluir o registro, corrija os erros e tente novamente.");
		Log.info("Mensagem de feedback de sucesso validada");
	}
	
	public void validarOrtografiaPageIncluirConta() {
		Log.info("Verificando ortografia da página de atualização de conta...");
		Utils.assertEquals(getTextElement(titleRegistroNovoConta)  , "Registro de Novas Contas");
		Utils.assertEquals(getTextElement(titleIdentificacaoConta) , "Identificação da Conta");
		Utils.assertEquals(getTextElement(titleNomeBanco)          , "Nome do Banco");
		Utils.assertEquals(getTextElement(titleNomeProprietario)   , "Nome do Proprietário");
		Utils.assertEquals(getTextElement(titleConta)              , "Conta");
		Utils.assertEquals(getTextElement(titleAgencia)            , "Agência");
		Utils.assertEquals(getTextElement(titleSaldoAtual)         , "Saldo Atual");
		Utils.assertEquals(getTextAtributoElement(btSalvar)        , "Salvar");
		Utils.assertEquals(getTextElement(btCancelar)              , "Cancelar");
		Log.info("Ortografia da página de atualização de conta validada com sucesso.");
	}
	
	
}
