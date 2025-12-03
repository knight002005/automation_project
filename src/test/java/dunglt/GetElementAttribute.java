package dunglt;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetElementAttribute  {

    private WebDriver driver;
    private String usernameInvalid = "usernameInvalid";
    private String passInvalid = "passInvalid";
    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void loginInvalid() throws InterruptedException {
        driver.get("http://103.121.90.177:8888/");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//label[text()='E-mail']/following-sibling::input")).sendKeys(usernameInvalid);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(passInvalid);
        driver.findElement(By.xpath("//span[contains(text(),'Đăng nhập')]")).click();
        String emailErrorMessage = driver.findElement(By.xpath("//div[@class='v-messages__message message-transition-enter-to']")).getText();
        Assert.assertEquals(emailErrorMessage, "Chưa đúng định dạng email");
    }

    @Test
    public void loginEmailPassEmpty() throws InterruptedException {
        driver.get("http://103.121.90.177:8888/");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//label[text()='E-mail']/following-sibling::input")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Đăng nhập')]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Email là bắt buộc']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Mật khẩu là bắt buộc']")).isDisplayed());

    }

    @Test
    public void getAttribute() throws InterruptedException {
        driver.get("http://103.121.90.177:8888/");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//label[text()='Mật khẩu']/following-sibling::input")).sendKeys(passInvalid);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Mật khẩu']/following-sibling::input")).getAttribute("type"), "password");

        driver.findElement(By.xpath("//button[@aria-label='Mật khẩu appended action']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Mật khẩu']/following-sibling::input")).getAttribute("type"), "text");

        driver.findElement(By.xpath("//button[@aria-label='Mật khẩu appended action']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Mật khẩu']/following-sibling::input")).getAttribute("type"), "password");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
