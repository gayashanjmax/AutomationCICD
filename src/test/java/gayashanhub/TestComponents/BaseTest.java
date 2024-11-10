package gayashanhub.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import gayashanhub.pageobjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver intializeDriver() throws IOException
	{
		Properties prop = new Properties(); //properties class can read the global properties . here it read the properties of "GlobalData.properties" file
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\gayashanhub\\resources\\GlobalData.properties"); //"GlobalData.properties" file path to InputStream
		prop.load(fis); //load the "GlobalData.properties"
		
		//java turnery operator (we can use this instead of if else)
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");//do this for Maven commands(if specifically mentioned what browser should run i.e. -DBrowser...)
		
			
		if(browserName.contains("Chrome"))
		{
			ChromeOptions options = new ChromeOptions();//headless mode
			if(browserName.contains("headless"))
			{
			options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));// maximize the screen to full screen
		}
		else if(browserName.equalsIgnoreCase("Firefox"))
		{
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	//Read Json file
	public List<HashMap<String, String>> getJasonDatatoMap(String filePath) throws IOException
	{
		//scan the JSON file and convert it to a String variable
		String jsonContent = FileUtils.readFileToString(new File(filePath));
	
		//convert the String to HashMap using Jackson DataBind concept
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
		});
		
		return data;
	}
	
	//get a screenshot
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver; // cast TakeScreenShot to the driver
		File source = ts.getScreenshotAs(OutputType.FILE);//get the source
		File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");//set the destination
		FileUtils.copyFile(source, file);
		
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver = intializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeBrowser()
	{
		driver.close();
	}

}
