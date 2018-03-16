package br.com.buggie.fractal.pages.cliente;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.buggie.config.pagebase.PageObjectGeneric;
import br.com.buggie.config.setup.Selenium;
import br.com.buggie.config.util.Log;

public class PageHomeCliente extends PageObjectGeneric<PageHomeCliente> {

	public PageHomeCliente() {
		PageFactory.initElements(Selenium.getDriver(), this);
	}

	@FindBy(xpath = "//*[@id='container']/header/div[2]/ul/li/a/span")
	WebElement user;
	
	public void sairDoSistema() {
		Log.info("Saindo do retaguarda...");
		waitAndClick(user);
	}
}
