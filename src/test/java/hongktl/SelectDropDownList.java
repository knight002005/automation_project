package hongktl;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class SelectDropDownList {
  WebDriver driver;

  @BeforeClass
  public void setup()
  {
    driver = new ChromeDriver();
    driver.manage().window().maximize();
  }

  @Test
  public void selectDefault(){
    driver.get("https://egov.danang.gov.vn/reg");
    Select select = new Select(driver.findElement(By.xpath("//select[@id='gioiTinh']")));
    select.selectByVisibleText("Nữ");
    System.out.println("Trang: "+driver.getTitle());
  }

  @Test
  public void selectCustom (){
    driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.findElement(By.xpath("//label[contains(text(),' Group Select')]/parent::div[@class='mb-3 row']/preceding-sibling::div/div/div/button[@class='ms-choice']")).click();
    WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Feb')]")));
    driver.findElement(By.xpath("//span[contains(text(),'Feb')]")).click();
    driver.findElement(By.xpath("//p[contains(text(),'Multiple select without any options')]")).click();


  }
  @Test
  public void dropListScroll() {
    driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.findElement(By.xpath("//label[contains(text(),'Group Select')]/parent::div/div/select/optgroup[@label='Group 1']")).click();

    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//label[contains(text(),'Group Select')]/parent::div/div/select/optgroup[@label='Group 1']/following-sibling::optgroup[@label='Group 3']/option[text()='Option 7']")));
    driver.findElement(By.xpath("//label[contains(text(),'Group Select')]/parent::div/div/select/optgroup[@label='Group 1']/following-sibling::optgroup[@label='Group 3']/option[text()='Option 7']")).click();
  }

  @AfterClass
 public void tertDown()
 {
   if (driver!=null)
   {
     driver.quit();
   }
 }

}
