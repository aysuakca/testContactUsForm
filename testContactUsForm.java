package testcase1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class testContactUsForm {
    @Test
    public void testContactUsFormSubmission() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        driver.get("https://automationexercise.com/");

        WebElement logo = driver.findElement(By.xpath("//img[@src='/static/images/home/logo.png']"));
        Assert.assertTrue(logo.isDisplayed());

        WebElement contactUs = driver.findElement(By.xpath("//a[text()=' Contact us']"));
        contactUs.click();

        WebElement getInTouchLogo = driver.findElement(By.xpath("//div[@class='contact-form']//h2[@class='title text-center']"));
        Assert.assertTrue(getInTouchLogo.isDisplayed());

        WebElement getInTouchName = driver.findElement(By.xpath("//input[@name='name']"));
        Actions actions = new Actions(driver);
        actions.click(getInTouchName).perform();
        actions.click(getInTouchName)
                .sendKeys("Aysu")
                .sendKeys(Keys.TAB)
                .sendKeys("aysu@gmail.com")
                .sendKeys(Keys.TAB)
                .sendKeys("Trying Test Automation")
                .sendKeys(Keys.TAB)
                .sendKeys("Hi, just trying")
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.ENTER)
                .perform();

        Alert alert = driver.switchTo().alert();
        alert.accept();


        String expectedText= "Success! Your details have been submitted successfully.";
        String actualText=driver.findElement(By.xpath("//div[@class='status alert alert-success']")).getText();
        Assert.assertEquals(expectedText,actualText);

        driver.quit();
    }
}
