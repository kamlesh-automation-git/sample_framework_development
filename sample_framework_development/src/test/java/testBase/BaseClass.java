package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;



//this is require to store the common reusable functions for various test cases. This is not web page specific, rather it is a task intended.
public class BaseClass {
	
	// to enable logging
	public Logger logger;		//Log4j
	public static WebDriver driver;		//WebDriver
	public Properties p;	//Properties file
	
	@BeforeClass (groups= {"sanity", "regression", "master"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException
	{
		//Loading config.properties file
		FileReader file= new FileReader("./src//test//resources/config.properties");
		p= new Properties();
		p.load(file);
				
//--------------- Checking if it remote execution or local execution ----------------
	//------------For Remote Execution
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
		//Setting up the OS platform based on the OS name
			switch(os.toLowerCase())
			{
				case "windows":capabilities.setPlatform(Platform.WIN11); break; 
				case "mac":capabilities.setPlatform(Platform.MAC); break;
				case "linux":capabilities.setPlatform(Platform.LINUX); break;
				default:System.out.println("Invalid OS name"); return;
			}
			
			
		//Setting up the browser driver based on the browser name
			switch(br.toLowerCase())
			{
				case "chrome":capabilities.setBrowserName("chrome"); break; 
				case "edge":capabilities.setBrowserName("MicrosoftEdge"); break;
				default:System.out.println("Invalid browser name"); return;
			}
			
			// launching driver
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);			// RemoteWebDriver is used for remote execution
			
		}
		
		

	//------------For Local Execution
		else

		{
		//Setting up the browser driver based on the browser name
			switch(br.toLowerCase())
			{
			case "chrome":driver = new ChromeDriver(); break; 
			case "edge":driver = new EdgeDriver(); break;
			default:System.out.println("Invalid browser name"); return;
			}
		}
		
		
		
		
//---------------------------------------------------------------------------		
		
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
			//getting the URL from config.properties file);
			driver.get(p.getProperty("appUrl"));	
	
			driver.manage().window().maximize();
		
			logger=LogManager.getLogger(this.getClass());		//logg4j2
		}
	
	
	@AfterClass (groups= {"sanity", "regression", "master"})
	public void tearDown()
	{
	driver.quit();
	}
	
	
	public String reusableMethod()
	{
		/*
		 xxxxxxxx
		 yyyyyyyyyy
		 */
		
		return "xyz";
		
	}


	public String captureScreen(String tname) 
	{
		// TODO Auto-generated method stub
		String timestamp=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		
		String destinationFilePath=System.getProperty("user.dir") + "\\Screenshots\\" + tname + "_" + timestamp + ".png";
		File destinationFile=new File(destinationFilePath);
		
		source.renameTo(destinationFile);
		
		
		return destinationFilePath;			//if path not returned then though file will be saved, but will not be part of Extent report  
	}
	
	
	
	
}
