package video;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Video {
  private WebDriver wd;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new ChromeDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void cft() throws Exception {
    wd.get("https://yandex.ru/portal/video");
    wd.findElement(By.cssSelector("input[name='text']")).sendKeys("ЦФТ");
    wd.findElement(By.cssSelector("div[class='websearch-button__text']")).click();

    List<WebElement> allVideos = wd.findElements(By.xpath("//div[@class='thumb-image__shadow']"));

    Actions actions = new Actions(wd);
    actions.moveToElement(allVideos.get(21)).build().perform();

    List<WebElement> allVideosToFindActive = wd.findElements(By.xpath("//div[contains(@class,'thumb-image__preview')]"));

    Thread.sleep(2000);

    String activeVideo = allVideosToFindActive.get(21).getAttribute("class");
    Assert.assertEquals(activeVideo, "thumb-image__preview thumb-preview__target thumb-preview__target_playing");


  }
}