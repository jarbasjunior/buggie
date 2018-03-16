package br.com.buggie.config.testbase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;

import br.com.buggie.config.setup.Property;
import br.com.buggie.config.setup.Selenium;
import br.com.buggie.config.suite.AllTests;
import br.com.buggie.config.util.Log;
public class BaseTestCase {
	
	
	protected static WebDriver           driver;
	
	@Rule
	public TestName nameTest = new TestName();
	
	@Before
	public void before(){
		Log.msgInicioTeste(nameTest.getMethodName());
	}

	@After
	public void after(){
		Log.msgFimTeste(nameTest.getMethodName());
	}
	
	@BeforeClass
	public static void beforeClass(){
		if(!AllTests.isAllTestsExecution)
			Selenium.getDriver().navigate().to(Property.URL_BUGGIE4);
	}
	
	@AfterClass
	public static void afterClass(){
		if(!AllTests.isAllTestsExecution){
			Selenium.resetDriver();
		}
	}
	
}
