package br.com.buggie.config.setup;

import java.io.File;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;



/**
 * Classe Utils para o Selenium
 * Identifica qual o browser escolhido no config.properties e inicializa o webdriver correspondente
 */
public class Selenium {
	
	private static WebDriver driver = null;
	
	
	/**
	 * Verifica qual o browser escolhido no arquivo de propriedades
	 * inicializa o driver apropriado e o retorna
	 * @return retorna instância do WebDriver
	 */
	public static WebDriver getDriver() {
		String browser = Property.BROWSER_NAME;
		
		if (driver == null) {
			
			if (Browser.CHROME.equals(browser)) {
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--start-maximized");
				File file = new File(Property.CHROME_DRIVE_PATH);
				System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
				driver = new ChromeDriver(chromeOptions);
				
			} else  if (Browser.FIREFOX.equals(browser)){
				File file = new File(Property.FIREFOX_DRIVE_PATH);
				System.setProperty("webdriver.gecko.driver",file.getAbsolutePath());
				FirefoxProfile firefoxProfile = new FirefoxProfile();
				//Remove o debug do "Marionette"
				System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
				Proxy proxy = new Proxy();
				//Define a configuração de proxy
				proxy.setProxyType(Proxy.ProxyType.SYSTEM);
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.setCapability("marionette", true);
				firefoxOptions.setCapability(CapabilityType.PROXY, proxy);
				firefoxOptions.setCapability("pdfjs.disabled", false);
				//Rodar sem abrir o navegador = true, com o navegador = false
				firefoxOptions.setHeadless(false);
				firefoxOptions.setProfile(getProfileFireFox(firefoxProfile));
				driver = new FirefoxDriver(firefoxOptions);
			}
		}
		return driver;
	}
	private static FirefoxProfile getProfileFireFox(FirefoxProfile firefoxProfile) {
		//	Alterar o perfil para aceitar certificado nÃ£o confiaveis
		firefoxProfile.setAcceptUntrustedCertificates(true);
		firefoxProfile.setAssumeUntrustedCertificateIssuer(false);
		//	Definida como falsa , portanto, nenhum popup exibido quando o download terminar.
		firefoxProfile.setPreference("browser.download.manager.showAlertOnComplete",false);
		//	desabilita botao de imprimir
		//	firefoxProfile.setPreference("dom.disable_beforeunload", true);
		firefoxProfile.setPreference("print.always_print_silent", true);
		firefoxProfile.setPreference("print.show_print_progress", false);
		firefoxProfile.setPreference("network.proxy.type", 0);
		/****
		 * 
		0 - Direct connection (or) no proxy. 
		1 - Manual proxy configuration
		2 - Proxy auto-configuration (PAC).
		4 - Auto-detect proxy settings.
		5 - Use system proxy settings.l
		 */
		firefoxProfile.setPreference("browser.download.dir",Property.EVIDENCIAS_TESTE_PATH);
		firefoxProfile.setPreference("browser.download.folderList", 2);
		firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/x-csv, application/zip, application/txt, application/pdf, application/vnd.ms-excel, application/aspx");

        firefoxProfile.setPreference("browser.download.useToolkitUI",true);
        // Set this to true to disable the pdf opening
        firefoxProfile.setPreference("pdfjs.disabled", true);

		return firefoxProfile;
	}
	
	public static void resetDriver(){
		if (driver != null) {
			driver.close();
		}
		driver = null;
	}
	
}
