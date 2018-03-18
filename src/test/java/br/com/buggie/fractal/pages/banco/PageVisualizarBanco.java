package br.com.buggie.fractal.pages.banco;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.buggie.config.pagebase.PageObjectGeneric;
import br.com.buggie.config.setup.Selenium;
import br.com.buggie.config.util.Log;
import br.com.buggie.config.util.Utils;

public class PageVisualizarBanco extends PageObjectGeneric<PageVisualizarBanco> {

	public PageVisualizarBanco() {
		PageFactory.initElements(Selenium.getDriver(), this);
	}

	@FindBy(xpath = "//*[@class='page-header']")
	WebElement titleIdentificacaoBanco;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../strong[text()='Nome']")
	WebElement titleNome;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../a[text()='Atualizar']")
	WebElement btAtualizar;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../a[text()='Cadastrar Novo']")
	WebElement btCadastrarNovo;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../a[text()='Voltar']")
	WebElement btVoltar;
	
	
	public void navegarParaPageAtualizacaoBanco(String banco) {
		aguardarElementoVisivel(titleIdentificacaoBanco);
		validarOrtografiaPageVisualizacaoBanco(banco);
		waitAndClick(btAtualizar);
		Log.info("Direcionando para página de atualização de banco...");
	}
	
	public void validarOrtografiaPageVisualizacaoBanco(String banco) {
		WebElement element           = Selenium.getDriver().findElement(By.xpath("//*[@id='page-wrapper']//../p['"+banco+"']"));
		String     valorRegistroTela = element.getText();
		valorRegistroTela            = valorRegistroTela.substring(5, valorRegistroTela.length()).trim();
		
		Log.info("Verificando ortografia da página de visualização de banco...");
		Utils.assertEquals(getTextElement(titleIdentificacaoBanco) , "Identificação do Banco");
		Utils.assertEquals(getTextElement(titleNome)               , "Nome");
		Utils.assertEquals(valorRegistroTela                       , banco);
		Utils.assertEquals(getTextElement(btVoltar)                , "Voltar");
		Utils.assertEquals(getTextElement(btCadastrarNovo)         , "Atualizar");
		Log.info("Ortografia da página de visualização de banco validada com sucesso.");
	}
}
