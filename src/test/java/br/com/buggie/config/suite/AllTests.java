package br.com.buggie.config.suite;

import java.util.Calendar;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.WebDriver;

import br.com.buggie.config.setup.Property;
import br.com.buggie.config.setup.Selenium;
import br.com.buggie.config.util.Utils;
import br.com.buggie.fractal.testes.banco.TesteManterBanco_IT;
import br.com.buggie.fractal.testes.cliente.TesteManterCliente_IT;
import br.com.buggie.fractal.testes.conta.TesteManterConta_IT;
import br.com.buggie.fractal.testes.funcionario.TesteManterFuncionario_IT;

/**
 * Classe que agrupa todas as classes de teste, funcionando com uma suíte de regressão.
 * @author Jarbas
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	TesteManterBanco_IT.class,
	TesteManterCliente_IT.class,
	TesteManterConta_IT.class,
	TesteManterFuncionario_IT.class
})

public class AllTests {
	
protected static WebDriver driver;
	
	public static Date    fim    			  = null;
	public static Date    inicio 			  = null;
	public static Boolean isAllTestsExecution = false;
	
	@BeforeClass
	public static void beforeClass() throws Exception {
		inicio = Calendar.getInstance().getTime();
		isAllTestsExecution = true;
		Selenium.getDriver().navigate().to(Property.URL_BUGGIE4);
	}

	@AfterClass
	public static void afterClass() throws Exception {
		Selenium.resetDriver();
		fim = Calendar.getInstance().getTime();
		Utils.calculaTempoDoTest(inicio, fim);
	}

}
