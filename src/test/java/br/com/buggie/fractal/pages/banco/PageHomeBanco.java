package br.com.buggie.fractal.pages.banco;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.buggie.config.pagebase.PageObjectGeneric;
import br.com.buggie.config.setup.Selenium;
import br.com.buggie.config.util.Log;
import br.com.buggie.config.util.Utils;

public class PageHomeBanco extends PageObjectGeneric<PageHomeBanco> {

	public PageHomeBanco() {
		PageFactory.initElements(Selenium.getDriver(), this);
	}

	@FindBy(xpath = "//*[@id='page-wrapper']//../h1")
	WebElement titleBanco;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../th[text()='Nome']")
	WebElement titleNome;
	
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
	
	public void navegarParaPaginaDeInclusaoDeBanco() {
		aguardarElementoVisivel(titleBanco);
		validarOrtografiaPageHomeBanco();
		Log.info("Navegando para página de inclusão de banco...");
		waitAndClick(btCadastrarNovo);
	}
	
	public void validarOrtografiaPageHomeBanco() {
		aguardarElementoVisivel(titleBanco);
		Log.info("Verificando ortografia de page home banco...");
		Utils.assertEquals(titleBanco.getText().substring(0, 6).trim(), "Bancos");
		Utils.assertEquals(titleNome.getText()                        , "Nome");
		Utils.assertEquals(titleAcoes.getText()                       , "Ações");
		Utils.assertEquals(btCadastrarNovo.getText()                  , "Cadastrar Novo");
		Utils.assertEquals(btPesquisar.getAttribute("value")          , "Pesquisar");
		Utils.assertEquals(btProximo.getText()                        , "Próximo ›");
		Utils.assertEquals(btUltimo.getText()                         , "Último »");
		Log.info("Ortografia de page home banco validada com sucesso.");
	}
}
