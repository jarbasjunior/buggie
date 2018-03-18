package br.com.buggie.fractal.pages.funcionario;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.buggie.config.pagebase.PageObjectGeneric;
import br.com.buggie.config.setup.Selenium;
import br.com.buggie.config.util.Log;
import br.com.buggie.config.util.Utils;

public class PageVisualizarFuncionario extends PageObjectGeneric<PageVisualizarFuncionario> {

	public PageVisualizarFuncionario() {
		PageFactory.initElements(Selenium.getDriver(), this);
	}

	@FindBy(xpath = "//*[@id='page-wrapper']//../h2")
	WebElement titleIdentiificaoFuncionario;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//./strong[text()='Nome']/..")
	WebElement titleNome;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//./strong[text()='Nome do Banco']/..")
	WebElement titleNomeBanco;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//./strong[text()='CPF']/..")
	WebElement titleCPF;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//./strong[text()='RG']/..")
	WebElement titleRG;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//./strong[text()='Função']/..")
	WebElement titleFuncao;
	
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

	
	public void validarRetornoDePesquisaDeConta(String rg, String cpf, String nome, String funcao, String nomeBanco) {
		aguardarElementoVisivel(titleIdentiificaoFuncionario);
		Log.info("Validando retorno da pesquisa de funcionário...");
		Utils.assertEquals(getTextElement(titleIdentiificaoFuncionario) , "Identificação do Servidor");
		Utils.assertEquals(getTextElement(titleRG)                      , "RG "+rg);
		Utils.assertEquals(getTextElement(titleCPF)                     , "CPF "+cpf);
		Utils.assertEquals(getTextElement(titleNome)                    , "Nome "+nome);
		Utils.assertEquals(getTextElement(titleFuncao)                  , "Função "+funcao);
		Utils.assertEquals(getTextElement(titleNomeBanco)               , "Nome do Banco "+nomeBanco);
		Log.info("Pesquisa de funcionário validada com sucesso!");
	}
}
