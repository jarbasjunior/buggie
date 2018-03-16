package br.com.buggie.fractal.pages.funcionario;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.buggie.config.pagebase.PageObjectGeneric;
import br.com.buggie.config.setup.Selenium;
import br.com.buggie.config.util.Log;

public class PageHomeFuncionario extends PageObjectGeneric<PageHomeFuncionario> {

	public PageHomeFuncionario() {
		PageFactory.initElements(Selenium.getDriver(), this);
	}

	@FindBy(xpath = "//*[@id='container']/header/div[2]/ul/li/a/span")
	WebElement user;
	
	public void sairDoSistema() {
		Log.info("Saindo do retaguarda...");
		waitAndClick(user);
	}
}
