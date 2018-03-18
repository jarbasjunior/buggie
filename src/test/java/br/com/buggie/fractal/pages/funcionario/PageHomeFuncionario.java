package br.com.buggie.fractal.pages.funcionario;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.buggie.config.pagebase.PageObjectGeneric;
import br.com.buggie.config.setup.Selenium;
import br.com.buggie.config.util.Log;
import br.com.buggie.config.util.Utils;
import br.com.buggie.fractal.model.ModelFuncionario;

public class PageHomeFuncionario extends PageObjectGeneric<PageHomeFuncionario> {

	public PageHomeFuncionario() {
		PageFactory.initElements(Selenium.getDriver(), this);
	}

	@FindBy(xpath = "//*[@id='page-wrapper']//../h1")
	WebElement titleFuncionarios;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../th[text()='Nome']")
	WebElement titleNome;

	@FindBy(xpath = "//*[@id='page-wrapper']//../th[text()='CPF']")
	WebElement titleCPF;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../th[text()='RG']")
	WebElement titleRG;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../th[text()='Função']")
	WebElement titleFuncao;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../th[text()='Nome do Banco']")
	WebElement titleNomeBanco;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../th[text()='Ações']")
	WebElement titleAcoes;
	
	@FindBy(id = "term")
	WebElement fieldFuncionario;
	
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
	WebElement firstNome;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//./tr[1]/td[3]")
	WebElement firstCPF;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//./tr[1]/td[4]")
	WebElement firstRG;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//./tr[1]/td[5]")
	WebElement firstFuncao;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//./tr[1]/td[6]")
	WebElement firstNomeBanco;
	
	@FindBy(xpath = "//*[@class='bootbox-body']")
	WebElement msgAlerta;
	
	@FindBy(xpath = "//*[@id='toast-container']/div/div")
	WebElement msgFeedback;
	
	public ModelFuncionario capturarRegistroPrimeiroFuncionario() {
		ModelFuncionario func = new ModelFuncionario();
		aguardarElementoVisivel(titleFuncionarios);
		validarOrtografiaPageHomeFuncionario();
		Log.info("Capturando primeiro registro do tipo funcionario na tela...");
		func.setRg(getTextElement(firstRG));
		func.setCpf(getTextElement(firstCPF));
		func.setNome(getTextElement(firstNome));
		func.setFuncao(getTextElement(firstFuncao));
		func.setNomeBanco(getTextElement(firstNomeBanco));
		Log.info("RG........ ["+func.getRg()+"]");
		Log.info("CPF....... ["+func.getCpf()+"]");
		Log.info("NOME...... ["+func.getNome()+"]");
		Log.info("FUNCÃO.... ["+func.getFuncao()+"]");
		Log.info("NOME BANCO ["+func.getNomeBanco()+"]");
		return func;
	}
	
	public void irParaPaginaDeVisualizacaoDoPrimeiroRegistro() {
		aguardarElementoVisivel(titleFuncionarios);
		String funcionario = firstNome.getText().trim();
		waitAndClick(Selenium.getDriver().findElement(By.xpath("//*[@id='page-wrapper']//./tr[1]/td[7]")));
		Log.info("Redirecionando para página de visualização do funcionário(a) ["+funcionario+"]...");
	}

	public void validarOrtografiaPageHomeFuncionario() {
		Utils.wait(500);
		Log.info("Verificando ortografia de page home conta...");
		Utils.assertEquals(getTextElement(titleFuncionarios).substring(0, 12).trim() , "Funcionários");
		Utils.assertEquals(getTextElement(titleNome)                                 , "Nome");
		Utils.assertEquals(getTextElement(titleCPF)                                  , "CPF");
		Utils.assertEquals(getTextElement(titleRG)                                   , "RG");
		Utils.assertEquals(getTextElement(titleFuncao)                               , "Função");
		Utils.assertEquals(getTextElement(titleNomeBanco)                            , "Nome do Banco");
		Utils.assertEquals(getTextElement(titleAcoes)                                , "Ações");
		Utils.assertEquals(getTextElement(btCadastrarNovo)                           , "Cadastrar Novo");
		Utils.assertEquals(getTextAtributoElement(btPesquisar)                       , "Pesquisar");
		Utils.assertEquals(getTextElement(btProximo)                                 , "Próximo ›");
		Utils.assertEquals(getTextElement(btUltimo)                                  , "Último »");
		Log.info("Ortografia de page home banco validada com sucesso.");
	}
}
