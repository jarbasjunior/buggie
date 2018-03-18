package br.com.buggie.fractal.pages.banco;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.buggie.config.pagebase.PageObjectGeneric;
import br.com.buggie.config.setup.Selenium;
import br.com.buggie.config.util.Log;
import br.com.buggie.config.util.Utils;

public class PageAtualizarBanco extends PageObjectGeneric<PageAtualizarBanco> {

	public PageAtualizarBanco() {
		PageFactory.initElements(Selenium.getDriver(), this);
	}

	@FindBy(xpath = "//*[@id='page-wrapper']//../h1")
	WebElement atualizacaoBanco;
	
	@FindBy(xpath = "//*[@for='bank_name']")
	WebElement titleNome;
	
	@FindBy(name = "commit")
	WebElement btSalvar;
	
	@FindBy(xpath = "//*[@class='btn btn-default']")
	WebElement btCancelar;
	
	@FindBy(xpath = "//*[@class='btn btn-default']")
	WebElement fieldName;
	
	@FindBy(xpath = "//*[@id='toast-container']/div/div")
	WebElement msgFeedback;
	
	public void atualizarBanco(String banco) {
		aguardarElementoVisivel(atualizacaoBanco);
		validarOrtografiaPageAtualizacaoBanco();
		Log.info("Atualizando nome de banco para ["+banco+"]...");
		preencherCampo(fieldName, banco);
		waitAndClick(btSalvar);
		Log.info("Validando mensagem de feedback de sucesso...");
		Utils.assertEquals(getTextElement(msgFeedback), "Registro atualizado com sucesso!");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validarOrtografiaPageAtualizacaoBanco() {
		
		Log.info("Verificando ortografia da página de atualização de banco...");
		Utils.assertEquals(getTextElement(atualizacaoBanco)  , "Identificação do Banco");
		Utils.assertEquals(getTextAtributoElement(titleNome) , "Nome");
		Utils.assertEquals(getTextElement(btSalvar)          , "Salvar");
		Utils.assertEquals(getTextElement(btCancelar)        , "Cancelar");
		Log.info("Ortografia da página de atualização de banco validada com sucesso.");
	}
}
