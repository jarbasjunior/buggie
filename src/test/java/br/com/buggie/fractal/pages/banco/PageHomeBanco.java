package br.com.buggie.fractal.pages.banco;

import org.openqa.selenium.By;
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
	
	@FindBy(xpath = "//*[@id='page-wrapper']//./tr[1]/td[2]")
	WebElement firstRegistro;
	
	public void navegarParaPaginaDeInclusaoDeBanco() {
		aguardarElementoVisivel(titleBanco);
		validarOrtografiaPageHomeBancos();
		Log.info("Navegando para página de inclusão de banco...");
		waitAndClick(btCadastrarNovo);
	}
	
	public void navegarParaPaginaDeVisualizacaoDeBanco(String banco) {
		aguardarElementoVisivel(titleBanco);
		validarOrtografiaPageHomeBancos();
		Log.info("Navegando para página de visualização de banco...");
		By xpathBanco = By.xpath("//*[@id='page-wrapper']//./td[text()='"+banco+"']//../td[3]");
		waitAndClick(Selenium.getDriver().findElement(xpathBanco));
	}
	
	public String capturarNomePrimeiroRegistro() {
		aguardarElementoVisivel(titleBanco);
		validarOrtografiaPageHomeBancos();
		Log.info("Capturando nome do primeiro registro do tipo banco na tela...");
		String banco = firstRegistro.getText().trim();
		Log.info("Nome encontrado no primeiro registro ["+banco+"].");
		return banco;
	}
	
	public void validarOrtografiaPageHomeBancos() {
		Utils.wait(1000);
		aguardarElementoVisivel(titleBanco);
		Log.info("Verificando ortografia de page home banco...");
		Utils.assertEquals(getTextElement(titleBanco).substring(0, 6).trim(), "Bancos");
		Utils.assertEquals(getTextElement(titleNome)                        , "Nome");
		Utils.assertEquals(getTextElement(titleAcoes)                       , "Ações");
		Utils.assertEquals(getTextElement(btCadastrarNovo)                  , "Cadastrar Novo");
		Utils.assertEquals(getTextAtributoElement(btPesquisar)              , "Pesquisar");
		Utils.assertEquals(getTextElement(btProximo)                        , "Próximo ›");
		Utils.assertEquals(getTextElement(btUltimo)                         , "Último »");
		Log.info("Ortografia de page home banco validada com sucesso.");
	}
}
