package br.com.buggie.fractal.pages.conta;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.buggie.config.pagebase.PageObjectGeneric;
import br.com.buggie.config.setup.Selenium;
import br.com.buggie.config.util.Log;
import br.com.buggie.config.util.Utils;
import br.com.buggie.fractal.model.ModelConta;

public class PageAtualizarConta extends PageObjectGeneric<PageAtualizarConta> {

	public PageAtualizarConta() {
		PageFactory.initElements(Selenium.getDriver(), this);
	}

	@FindBy(xpath = "//*[@id='page-wrapper']//../h1")
	WebElement titleAtualizacaoConta;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../h2")
	WebElement titleMsgOrientacao;

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
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../h2//..//../li[text()='Conta não pode ficar em branco']")
	WebElement msgContaEmBranco;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../h2//..//../li[text()='Conta não é um número']")
	WebElement msgContaNotText;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../h2//..//../li[text()='Conta não possui o tamanho esperado (5 caracteres)']")
	WebElement msgContaCurta;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../h2//..//../li[text()='Agência não pode ficar em branco']")
	WebElement msgAgenciaEmBranco;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../h2//..//../li[text()='Agência não é um número']")
	WebElement msgAgenciaNotText;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../h2//..//../li[text()='Agência não possui o tamanho esperado (4 caracteres)']")
	WebElement msgAgenciaCurta;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../h2//..//../li[text()='Saldo Atual não pode ficar em branco']")
	WebElement msgSaldoAtualEmBranco;
	
	@FindBy(xpath = "//*[@id='page-wrapper']//../h2//..//../li[text()='Saldo Atual não é um número']")
	WebElement msgSaldoAtualNotText;
	
	public void atualizarConta(String conta) {
		aguardarElementoVisivel(titleAtualizacaoConta);
		validarOrtografiaPageAtualizacaoConta();
		Log.info("Atualizando nome de conta para ["+conta+"]...");
		waitAndClick(btSalvar);
		Log.info("Validando mensagem de feedback de sucesso...");
		Utils.assertEquals(getTextElement(msgFeedback), "Registro atualizado com sucesso!");
		Log.info("Mensagem de feedback validada.");
	}
	
	public String getAgencia() {
		return fieldAgencia.getAttribute("value");
	}
	
	public void cancelarAtualizacao() {
		Log.info("Cancelando atualização..");
		waitAndClick(btCancelar);
	}
	
	public void removerValoresCamposConta(String conta) {
		aguardarElementoVisivel(titleAtualizacaoConta);
		validarOrtografiaPageAtualizacaoConta();
		Log.info("Removendo valores dos campos da conta ["+conta+"]...");
		selecionarValorComboTexto(comboBanco       , "");
		selecionarValorComboTexto(comboProprietario, conta);
		preencherCampo(fieldConta     , "");
		preencherCampo(fieldAgencia   , "");
		preencherCampo(fieldSaldoAtual, "");
		Log.info("Tentando salvar registro com campos vazios...");
		waitAndClick(btSalvar);
		Log.info("Validando mensagem de feedback de erro...");
		Utils.assertEquals(getTextElement(msgFeedback), "Não foi possível atualizar o registro, corrija os erros e tente novamente.");
		Log.info("Mensagem de feedback de erro validada.");
	}
	
	public ModelConta getPrimeiroRegistroConta() {
		ModelConta object = new ModelConta();
		Log.info("Capturando dados da conta...");
		object.setBanco(Selenium.getDriver().findElement(By.xpath("//*[@name='account[bank_id]']//../option[@selected='selected']")).getText());
		object.setProprietario(Selenium.getDriver().findElement(By.xpath("//*[@name='account[client_id]']//../option[@selected='selected']")).getText());
		object.setConta(fieldConta.getAttribute("value"));
		object.setAgencia(fieldAgencia.getAttribute("value"));
		object.setSaldo(fieldSaldoAtual.getAttribute("value"));
		Log.info("Conta....... ["+object.getConta()+"]");
		Log.info("Banco....... ["+object.getBanco()+"]");
		Log.info("Saldo....... ["+object.getSaldo()+"]");
		Log.info("Agencia..... ["+object.getAgencia()+"]");
		Log.info("Proprietário ["+object.getProprietario()+"]");
		return object;
	}
	
	public void validarMsgFeedbackOrientacaoCamposEmBranco() {
		aguardarElementoVisivel(titleAtualizacaoConta);
		Log.info("Validando mensagem de feedback de orientação...");
		Utils.assertEquals(getTextElement(titleAtualizacaoConta)   , "Atualização da Conta");
		Utils.assertEquals(getTextElement(titleMsgOrientacao)      , "8 Erro(s), veja abaixo o que precisa ser corrigido:");
		Utils.assertEquals(getTextElement(msgContaEmBranco)        , "Conta não pode ficar em branco");
		Utils.assertEquals(getTextElement(msgContaNotText)         , "Conta é número");
		Utils.assertEquals(getTextElement(msgContaCurta)           , "Conta não possui o tamanho esperado (5 caracteres)");
		Utils.assertEquals(getTextElement(msgAgenciaEmBranco)      , "Agência não pode ficar em branco");
		Utils.assertEquals(getTextElement(msgAgenciaNotText)       , "Agência é número");
		Utils.assertEquals(getTextElement(msgAgenciaCurta)         , "Agência não possui o tamanho esperado (4 caracteres)");
		Utils.assertEquals(getTextElement(msgSaldoAtualEmBranco)   , "Saldo Atual não pode ficar em branco");
		Utils.assertEquals(getTextElement(msgSaldoAtualNotText)    , "Saldo Atual é número");
		Log.info("Mensagens de orientações validadas com sucesso!");
	}
	
	public void validarOrtografiaPageAtualizacaoConta() {
		Log.info("Verificando ortografia da página de atualização de conta...");
		Utils.assertEquals(getTextElement(titleAtualizacaoConta)   , "Atualização da Conta");
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
