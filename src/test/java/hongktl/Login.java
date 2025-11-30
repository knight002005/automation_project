package hongktl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Login {
    private WebDriver driver;
    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void loginSuccess() throws InterruptedException {
        driver.get("http://103.121.90.177:8888/");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//label[text()='E-mail']//following-sibling::input")).sendKeys("tamdd1998@gmail.com");
        driver.findElement(By.xpath("//label[text()='Mật khẩu']//following-sibling::input")).sendKeys("123456aA@");
        driver.findElement(By.xpath("//span[text()=' Đăng nhập ']")).click();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
