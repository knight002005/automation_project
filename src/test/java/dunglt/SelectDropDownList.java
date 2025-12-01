package dunglt;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class SelectDropDownList {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void selectDefault() {
        driver.get("https://egov.danang.gov.vn/reg");
        Select select = new Select(driver.findElement(By.xpath("//select[@id='gioiTinh']")));
        select.selectByVisibleText("Nam");
        select.selectByVisibleText("Không xác định");
        System.out.println("Page title: " + driver.getTitle());
    }

    @Test
    public void selectCustomize() throws InterruptedException {
        driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@class='ms-parent multiple-select ']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='May']")));
        driver.findElement(By.xpath("//span[text()='May']")).click();
    }

    @Test
    public void groupSelect() throws InterruptedException {
        driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//label[contains(text(),'Group Select')]/following-sibling::div/select[@multiple='multiple']")).click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//label[contains(text(),'Group Select')]/following-sibling::div/select[@multiple='multiple']//option[contains(text(),'Option 4')]")));

        driver.findElement(By.xpath("//label[contains(text(),'Group Select')]/following-sibling::div/select[@multiple='multiple']//option[contains(text(),'Option 4')]")).click();

        driver.findElement(By.xpath("//label[contains(text(),'Group Select')]")).click();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

