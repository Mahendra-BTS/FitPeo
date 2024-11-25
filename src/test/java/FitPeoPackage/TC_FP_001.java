package FitPeoPackage;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TC_FP_001 {
	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		//Navigate to the FitPeo Homepage 
		
		driver.get("https://www.fitpeo.com/");
		
		//Navigate to the Revenue Calculator PageRevenue Calculator
		
		driver.findElement(By.linkText("Revenue Calculator")).click();
		
		//Scroll Down to the Slider section
	
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");
		
		

		//Adjust the Slider
		
		Actions actions = new Actions(driver);
		for (int i = 1; i <= 13; i++) {
			actions.sendKeys(Keys.TAB).perform();
		}
		js.executeScript("window.scrollBy(0,300)");
		for (int i = 1; i <= 620; i++) {
			actions.sendKeys(Keys.ARROW_RIGHT).perform();
		}   
		WebElement sliderBar = driver.findElement(By.xpath("//span[@class='MuiSlider-rail css-3ndvyc']"));
		WebElement sliderHilightedBar = driver.findElement(By.xpath("//span[@class='MuiSlider-track css-10opxo5']"));
		WebElement sliderPoint = driver.findElement(By.xpath("//span[@data-index='0']"));
		js.executeScript("arguments[0].style.background = 'red';", sliderBar);
		js.executeScript("arguments[0].style.background = 'red';", sliderHilightedBar);
		js.executeScript("arguments[0].style.background = 'red';", sliderPoint);
		
		//Update the Text Field
		
		Thread.sleep(3000);
		WebElement inputField = driver.findElement(By.xpath("//*[@type='number']"));
		inputField.sendKeys("560"); //click is not working here so i used sendkeys for click the input field
		for (int i = 1; i <= 3; i++) { //clear is not working here so i used actions
			actions.sendKeys(Keys.BACK_SPACE).perform();
		}
		inputField.sendKeys("560");
		
		//Validate Slider Value
		
		WebElement sliderInputBar = driver.findElement(By.xpath("//span[@class='MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary css-1sfugkh']/input"));
		String sliderValue = sliderInputBar.getAttribute("aria-valuenow");
		String inputFieldValue = "560";
		System.out.println(sliderValue.equals(inputFieldValue));
		
		//Select CPT Codes
		
		inputField.sendKeys("560"); //click is not working here so i used sendkeys for click the input field
		for (int i = 1; i <= 3; i++) { //clear is not working here so i used actions
			actions.sendKeys(Keys.BACK_SPACE).perform();
		}
		inputField.sendKeys("820");
		List<WebElement> inputList = driver.findElements(By.xpath("//input[@class='PrivateSwitchBase-input css-1m9pwf3'][@type='checkbox']"));
		int i =1;
		for (WebElement list : inputList) {
			if (i<=3 || i==8) {
				list.click();
			}
			i++;
		}
		
		//Validate Total Recurring Reimbursement
		String expectedResults = "$110700";
		String actualResults = driver.findElement(By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 inter css-1bl0tdj'][text()='110700']")).getText();
		System.out.println(actualResults.equals(expectedResults));
		
	}
}