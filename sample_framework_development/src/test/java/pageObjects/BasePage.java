package pageObjects;

//import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


// includes only constructor. will be invoked by every page object class constructor (Re-usability)
public class BasePage 
{
	WebDriver driver;		
	
	public BasePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	
	
		
}
