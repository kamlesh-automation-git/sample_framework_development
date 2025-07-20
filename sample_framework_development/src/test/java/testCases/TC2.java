package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import testBase.BaseClass;

public class TC2 extends BaseClass 
{

public WebDriver driver;

	
	
@Test(groups= {"sanity", "regression", "master"})

public void function1()
	{
		// for logging
		logger.info("********Stating of the xyz test************");
		
		logger.info("I am doing this");
		logger.info("I am doing that");
		logger.trace("I am doing this and that");
		System.out.println("I am in function1 of TC2");
	}

@Test(groups= {"master"})

public void function2() 
{
System.out.println("I am in function2 of TC2");	
}

@Test(groups= {"sanity", "regression"})
public void function3() 
{
System.out.println("I am in function3 of TC2");	
}


@Test(groups= {"regression", "master"})
public void function4() 
{
System.out.println("I am in function4 of TC2");	
}


@Test(groups= {"sanity"})
public void function5() 
{
System.out.println("I am in function5 of TC2");	
}


}
