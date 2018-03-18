package br.com.buggie.fractal.pages.banco;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.buggie.config.pagebase.PageObjectGeneric;
import br.com.buggie.config.setup.Selenium;
import br.com.buggie.config.util.Log;
import br.com.buggie.config.util.Utils;

public class PageIncluirBanco extends PageObjectGeneric<PageIncluirBanco> {

	public PageIncluirBanco() {
		PageFactory.initElements(Selenium.getDriver(), this);
	}

	@FindBy(xpath = "//*[@id='page-wrapper']//../h1")
	WebElement titleRegistroNovoBanco;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../h3")
	WebElement titleIdentificacaoBanco;
	
	@FindBy(xpath = "//*[@for='bank_name']")
	WebElement titleNome;
	
	@FindBy(name = "commit")
	WebElement btSalvar;
	
	@FindBy(xpath = "//*[@class='btn btn-default']")
	WebElement btCancelar;
	
	@FindBy(id = "bank_name")
	WebElement fieldName;
	
	@FindBy(xpath = "//*[@id='toast-container']/div/div")
	WebElement msgFeedback;
	
	public void incluirBancoSucesso(String banco) {
		aguardarElementoVisivel(titleRegistroNovoBanco);
		validarOrtografiaPageIncluirBanco();
		
		Log.info("Cadastrando banco ["+banco+"]...");
		preencherCampo(fieldName, banco);
		Log.info("Salvando registro ["+banco+"]...");
		waitAndClick(btSalvar);
		Log.info("Validando mensagem de feedback de sucesso...");
		Utils.assertEquals(msgFeedback.getText(), "Registro incluído com sucesso!");
		Log.info("Mensagem de feedback de sucesso validada");
	}
	
	public void incluirBancoSemPreencherCampoNome() {
		aguardarElementoVisivel(titleRegistroNovoBanco);
		validarOrtografiaPageIncluirBanco();
		
		Log.info("Salvando banco com campo [Nome] vazio...");
		waitAndClick(btSalvar);
		Log.info("Validando mensagem de feedback de erro...");
		Utils.assertEquals(getTextElement(msgFeedback), "Não foi possível concluir o registro, corrija os erros e tente novamente.");
		Log.info("Mensagem de feedback de sucesso validada");
	}
	
	public void validarOrtografiaPageIncluirBanco() {
		Log.info("Verificando ortografia da página de inclusão de banco...");
		Utils.assertEquals(getTextElement(titleRegistroNovoBanco) , "Registro de Novos Bancos");
		Utils.assertEquals(getTextElement(titleIdentificacaoBanco), "Identificação do Banco");
		Utils.assertEquals(getTextElement(titleNome)              , "Nome");
		Utils.assertEquals(getTextAtributoElement(btSalvar)       , "Salvar");
		Utils.assertEquals(getTextElement(btCancelar)             , "Cancelar");
		Log.info("Ortografia da página de inclusão de banco validada com sucesso.");
	}
}
