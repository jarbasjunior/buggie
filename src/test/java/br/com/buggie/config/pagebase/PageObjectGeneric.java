package br.com.buggie.config.pagebase;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import br.com.buggie.config.setup.Property;
import br.com.buggie.config.setup.Selenium;
import br.com.buggie.config.util.Log;
import br.com.buggie.config.util.Utils;

public abstract class PageObjectGeneric<T> {

	private static final String URL                              = Property.URL_BUGGIE4;
	private static final int    LOAD_TIMEOUT                     = 10;
	private static final int    INTERVALO_VERIFICACAO            = 1;
	private String windowHandleJanelaInicial;
	private static final Wait<WebDriver> wait = new FluentWait<WebDriver>(Selenium.getDriver())
				    								.withTimeout( LOAD_TIMEOUT         , TimeUnit.SECONDS) // Tempo limite (segundos)
				    								.pollingEvery(INTERVALO_VERIFICACAO, TimeUnit.SECONDS) // Intervalo de tempo de cada busca (segundos) 
				    								.ignoring(NoSuchElementException.class);  

	public PageObjectGeneric() {
		PageFactory.initElements(Selenium.getDriver(), this);
	}

	public T abrirPagina(Class<T> clazz) {
		T pagina = PageFactory.initElements(Selenium.getDriver(), clazz);
		Selenium.getDriver().navigate().to(URL);
		return pagina;
	}

	public void preencherCampo(WebElement element, String value) {
		try {
			aguardarElementoVisivel(element);
			element.clear();
			element.sendKeys(value);
		} catch (WebDriverException e) {
			erroPreenchimento(element, value);
		}
	}
	
	public void aguardarElementoVisivel(WebElement element) {
		try {
			final WebElement e = element;
			wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return e;
				}
			});
			WebDriverWait driverWait = new WebDriverWait(Selenium.getDriver(),
			LOAD_TIMEOUT);
			driverWait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			erroEspera(element);
		}
	}
	
	public void waitAndClick(WebElement element) {
		try {
			final WebElement e = element;
			WebElement elementoAguardado = wait.until(new Function<WebDriver, WebElement>() {
			       public WebElement apply(WebDriver driver) {
			              return e;
			             }
			       });
			click(elementoAguardado);
			
		} catch (WebDriverException e) {
			erroClick(element);
		}
	}
	
	public void click(WebElement element) {
		try {
			aguardarElementoVisivel(element);
			element.click();
		} catch (WebDriverException e) {
			erroClick(element);
		}
	}

	public String getTextAtributoElement(WebElement element) {
		try {
			String s = element.getAttribute("value"); 
			return s;
		} catch (Exception e) {
			erroGetTextAtributo(element);
			return null;
		}
	}
	
	public String getTextElement(WebElement element) {
		try {
			String s = element.getText(); 
			return s;
		} catch (Exception e) {
			erroGetText(element);
			return null;
		}
	}

	public void confirmarAlerta() {
		try {
			Alert alert = Selenium.getDriver().switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			erroConfirmaAlerta();
		}
	}

	public void esperarElementoDesaparecer(By elemento, int qtdSegundos){
		try {
			int segundosEspera      = 0;
			int segundosRegressivos = qtdSegundos;
			while (isVisibility(elemento) || segundosEspera == qtdSegundos) {
				Log.info("Aguardando mensagem de espera desaparecer");
				Utils.wait(1000);
				segundosEspera++;
				Log.info("Tempo de espera restante ["+segundosRegressivos+"]");
				segundosRegressivos--;
				if (!isVisibility(elemento) || segundosEspera == qtdSegundos) {
					Log.info("Mensagem de espera removida");
					break;
				}
			}
		} catch (Exception e) {
			Log.info("Erro");
			Assert.fail("Error!");
		}
	}

	public boolean isVisibility(By locator) {
		try {
			WebElement element = Selenium.getDriver().findElement(locator);
			element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public void selecionarValorComboTexto(WebElement element, String textVisible){
		try{
			new Select(element).selectByVisibleText(textVisible);
		}catch(NoSuchElementException e){
			erroSelecaoCombo(element, textVisible);
		}
	}
	
	public void selecionarValorComboValue(WebElement element, String valueVisible){
		try{
			new Select(element).selectByValue(valueVisible);
		}catch(NoSuchElementException e){
			erroSelecaoCombo(element, valueVisible);
		}
	}
	public void clicarBotaoDireito(WebElement elemento) {
		Actions action = new Actions(Selenium.getDriver());
		action.contextClick(elemento).build().perform();
	}
	
	public void doubleclick(WebElement elemento) {
		Actions action = new Actions(Selenium.getDriver());
		action.doubleClick().build().perform();
	}

	public void moverCursorPara(WebElement elemento) {
		Actions action = new Actions(Selenium.getDriver());
		aguardarElementoVisivel(elemento);
		action.moveToElement(elemento).build().perform();
	}
	
	public boolean existText(WebElement elemento, String texto) {
		aguardarElementoVisivel(elemento);
		return elemento.getText().contains(texto);
	}

	public void voltarPagina() {
		Selenium.getDriver().navigate().back();
	}

	public void alternarJanela() {
		windowHandleJanelaInicial = Selenium.getDriver().getWindowHandle();
		Set<String> windowHandles = Selenium.getDriver().getWindowHandles();
		for (String windowHandle : windowHandles) {
			if (!windowHandle.equals(windowHandleJanelaInicial)) {
				Selenium.getDriver().switchTo().window(windowHandle);
			}
		}
		setWindowHandleJanelaInicial(windowHandleJanelaInicial);
	}
	
	public void retonarJanelaOriginal() {
		Selenium.getDriver().switchTo().window(getWindowHandleJanelaInicial());
	}

	public String getWindowHandleJanelaInicial() {
		return windowHandleJanelaInicial;
	}

	public void setWindowHandleJanelaInicial(String windowHandleJanelaInicial) {
		this.windowHandleJanelaInicial = windowHandleJanelaInicial;
	}

	public WebElement getElement(By by) {
		return Selenium.getDriver().findElement(by);
	}
	
	public void erroPreenchimento(WebElement element, String value) {
		erro();
		Log.erro(element.toString().substring(45, element.toString().length()-2)+"]. não encontrado, valor ["+value+"] não pôde ser preenchido.");
		Assert.fail(element.toString().substring(45, element.toString().length()-2)+"]. não encontrado, valor ["+value+"] não pôde ser preenchido.");
		
	}
	
	public void erroEspera(WebElement element) {
		erro();
		Log.erro("Tempo excedido ("+LOAD_TIMEOUT+"s) para aguardar elemento: "+element.toString().substring(45, element.toString().length()-1)+"");
		Assert.fail("Tempo excedido ("+LOAD_TIMEOUT+"s) para aguardar elemento: "+element.toString().substring(45, element.toString().length()-1)+"");
	}
	
	public void erroClick(WebElement element) {
		erro();
		Log.erro("Erro ao clicar no elemento: "+element.toString().substring(45, element.toString().length()-2)+"].");
		Assert.fail("Erro ao clicar no elemento "+element.toString().substring(45, element.toString().length()-2)+"].");
	}
	
	public void erroGetTextAtributo(WebElement element) {
		erro();
		Log.erro("Erro ao buscar texto de atributo do elemento: "+element.toString().substring(45, element.toString().length()-2)+"].");
		Assert.fail("Erro ao buscar texto de atributo do elemento: "+element.toString().substring(45, element.toString().length()-2)+"].");
	}
	
	public void erroGetText(WebElement element) {
		erro();
		Log.erro("Erro ao buscar texto do elemento: "+element.toString().substring(45, element.toString().length()-2)+"].");
		Assert.fail("Erro ao buscar texto do elemento: "+element.toString().substring(45, element.toString().length()-2)+"].");
	}
	
	public void erroConfirmaAlerta() {
		erro();
		Log.erro("Erro ao realizar a confirmacao do Alerta");
		Assert.fail("Erro ao realizar a confirmacao do Alerta");
	}
	
	public void errogetText() {
		erro();
		Log.erro("Erro ao realizar a confirmacao do Alerta");
		Assert.fail("Erro ao realizar a confirmacao do Alerta");
	}
	
	public void erroSelecaoCombo(WebElement element, String valor) {
		erro();
		Log.erro("Erro ao selecionar elemento do combo: "+element.toString().substring(45, element.toString().length()-2)+"], com o valor: "+valor);
		Utils.takeScreenshot("Combobox-"+valor);
		Assert.fail("Erro ao selecionar elemento do combo: "+element.toString().substring(45, element.toString().length()-2)+"], com o valor: "+valor);
	}
	
	public void erro() {
		Log.erro("E R R O ...");
		isPageNotExists();
	}
	
	public void isPageNotExists() {
		boolean isPageNotExists = Selenium.getDriver().getTitle().toString().equals("The page you were looking for doesn't exist (404)");
		if (isPageNotExists) {
			Log.erro("A PÁGINA PROCURADA NÃO EXISTE ! ! !");
			Utils.takeScreenshot("Not found-404!");
			Selenium.getDriver().navigate().to(Property.URL_BUGGIE4);
			Utils.wait(1000);
			Assert.fail("A PÁGINA PROCURADA NÃO EXISTE ! ! !");
		}
	}
}