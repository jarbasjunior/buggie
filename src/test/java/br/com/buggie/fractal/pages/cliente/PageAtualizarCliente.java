package br.com.buggie.fractal.pages.cliente;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.buggie.config.pagebase.PageObjectGeneric;
import br.com.buggie.config.setup.Selenium;
import br.com.buggie.config.util.Log;
import br.com.buggie.config.util.Utils;

public class PageAtualizarCliente extends PageObjectGeneric<PageAtualizarCliente> {

	public PageAtualizarCliente() {
		PageFactory.initElements(Selenium.getDriver(), this);
	}

	@FindBy(xpath = "//*[@id='page-wrapper']//../h1")
	WebElement titleAtualizacaoCliente;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../h2")
	WebElement titleMsgOrientacao;

	@FindBy(xpath = "//*[@id='page-wrapper']//../h3")
	WebElement titleIdentificacaoCliente;
		
	@FindBy(xpath = "//*[@for='client_name']")
	WebElement titleNome;

	@FindBy(xpath = "//*[@for='client_cpf']")
	WebElement titleCPF;

	@FindBy(xpath = "//*[@for='client_rg']")
	WebElement titleRG;
	
	@FindBy(id = "client_name")
	WebElement fielNome;
	
	@FindBy(id = "client_cpf")
	WebElement fielCPF;

	@FindBy(id = "client_rg")
	WebElement fielRG;
	
	@FindBy(name = "commit")
	WebElement btSalvar;
	
	@FindBy(xpath = "//*[@class='btn btn-default']")
	WebElement btCancelar;
	
	@FindBy(xpath = "//*[@class='btn btn-default']")
	WebElement fieldName;
	
	@FindBy(xpath = "//*[@id='toast-container']/div/div")
	WebElement msgFeedback;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../h2//..//../li[text()='Nome não pode ficar em branco']")
	WebElement msgNomeEmBranco;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../h2//..//../li[text()='Nome é muito curto (mínimo: 4 caracteres)']")
	WebElement msgNomeCurto;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../h2//..//../li[text()='CPF não pode ficar em branco']")
	WebElement msgCpfEmBranco;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../h2//..//../li[text()='CPF não é um número']")
	WebElement msgCpfNotText;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../h2//..//../li[text()='CPF não possui o tamanho esperado (11 caracteres)']")
	WebElement msgCpf11Caracteres;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../h2//..//../li[text()='RG não pode ficar em branco']")
	WebElement msgRgEmBranco;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../h2//..//../li[text()='RG não é um número']")
	WebElement msgRgNotText;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../h2//..//../li[text()='RG não possui o tamanho esperado (7 caracteres)']")
	WebElement msgRg7Caracteres;
	
	public void atualizarCliente(String cliente) {
		aguardarElementoVisivel(titleAtualizacaoCliente);
		validarOrtografiaPageAtualizacaoCliente();
		Log.info("Atualizando nome de cliente para ["+cliente+"]...");
		preencherCampo(fieldName, cliente);
		waitAndClick(btSalvar);
		Log.info("Validando mensagem de feedback de sucesso...");
		Utils.assertEquals(getTextElement(msgFeedback), "Registro atualizado com sucesso!");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void removerValoresCamposCliente(String cliente) {
		aguardarElementoVisivel(titleAtualizacaoCliente);
		validarOrtografiaPageAtualizacaoCliente();
		Log.info("Removendo valores dos campos do cliente ["+cliente+"]...");
		preencherCampo(fielNome, "");
		preencherCampo(fielCPF, "");
		preencherCampo(fielRG, "");
		Log.info("Tentando salvar registro com campos vazios...");
		waitAndClick(btSalvar);
		Log.info("Validando mensagem de feedback de erro...");
		Utils.assertEquals(getTextElement(msgFeedback), "Não foi possível atualizar o registro, corrija os erros e tente novamente.");
		Log.info("Mensagem de feedback de erro validada.");
	}
	
	public void validarMsgFeedbackOrientacaoCamposEmBranco() {
		aguardarElementoVisivel(titleAtualizacaoCliente);
		Log.info("Validando mensagem de feedback de orientação...");
		Utils.assertEquals(getTextElement(titleAtualizacaoCliente) , "Atualização do Cliente");
		Utils.assertEquals(getTextElement(titleMsgOrientacao)      , "8 Erro(s), veja abaixo o que precisa ser corrigido:");
		Utils.assertEquals(getTextElement(msgNomeEmBranco)         , "Nome não pode ficar em branco");
		Utils.assertEquals(getTextElement(msgNomeCurto)            , "Nome é muito curto (mínimo: 4 caracteres)");
		Utils.assertEquals(getTextElement(msgCpfEmBranco)          , "CPF não pode ficar em branco");
		Utils.assertEquals(getTextElement(msgCpfNotText)           , "CPF é número");
		Utils.assertEquals(getTextElement(msgCpf11Caracteres)      , "CPF não possui o tamanho esperado (11 caracteres)");
		Utils.assertEquals(getTextElement(msgRgEmBranco)           , "RG não pode ficar em branco");
		Utils.assertEquals(getTextElement(msgRgNotText)            , "RG é número");
		Utils.assertEquals(getTextElement(msgRg7Caracteres)        , "RG não possui o tamanho esperado (7 caracteres)");
		Log.info("Mensagens de orientações validadas com sucesso!");
	}
	
	public void validarOrtografiaPageAtualizacaoCliente() {
		
		Log.info("Verificando ortografia da página de atualização de cliente...");
		Utils.assertEquals(getTextElement(titleAtualizacaoCliente)   , "Atualização do Cliente");
		Utils.assertEquals(getTextElement(titleIdentificacaoCliente) , "Identificação do Cliente");
		Utils.assertEquals(getTextElement(titleNome)                 , "Nome");
		Utils.assertEquals(getTextElement(titleRG)                   , "RG");
		Utils.assertEquals(getTextElement(titleCPF)                  , "CPF");
		Utils.assertEquals(getTextAtributoElement(btSalvar)          , "Salvar");
		Utils.assertEquals(getTextElement(btCancelar)                , "Cancelar");
		Log.info("Ortografia da página de atualização de cliente validada com sucesso.");
	}
}
