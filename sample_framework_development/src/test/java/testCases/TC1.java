package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import pageObjects.PageObject1;
import pageObjects.PageObject2;
import testBase.BaseClass;

public class TC1 extends BaseClass 
{
//********public WebDriver driver;***********no more needed due to base class*************

	
@Test (groups= {"sanity", "regression", "master"})
	public void function1()
	{
		
		PageObject1 Obj1 = new PageObject1(driver);
		PageObject2 Obj2 = new PageObject2(driver);
		
		
		// to invoke the reusable method
		String result = reusableMethod();
		
		// for logging
		logger.info("The result of the reusable method is: " + result);

		// for logging
		logger.info("********Stating of the xyz test************");
		logger.info("I am doing this");
		logger.info("I am doing that");
		logger.trace("I am doing this and that");
		System.out.println("I am in function1 of TC1");
		
	}

	@Test(groups= {"master"})
	public void function2() 
	{
	System.out.println("I am in function2 of TC1");	
	}

	@Test(groups= {"sanity", "regression"})
	public void function3() 
	{
	System.out.println("I am in function3 of TC1");	
	}
	
	
	@Test(groups= {"regression"})
	public void function4() 
	{
	System.out.println("I am in function4 of TC1");	
	}
	

	@Test(groups= {"sanity", "master"})
	public void function5() 
	{
	System.out.println("I am in function5 of TC1");	
	}
	
}
