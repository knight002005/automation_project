package hongktl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class WaitElement {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void waitImplicit() {
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
        driver.findElement(By.xpath("//h4[text()='Hello World!']")).click();
    }

    @Test
    public void waitExplicit() {
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
        driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Hello World!']")));
        driver.findElement(By.xpath("//h4[text()='Hello World!']")).click();
    }
}