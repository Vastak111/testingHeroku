import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Optional;

public enum Actions {
    INSTANCE;
    private final int INTERVAL = Integer.parseInt(Optional.ofNullable(System.getenv("INTERVAL")).orElse("60"));

    public void click(ChromeDriver chromeDriver, String xPath) {
        WebDriverWait wait = new WebDriverWait(chromeDriver, INTERVAL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
        WebElement webElement = chromeDriver.findElement(By.xpath(xPath));
        webElement.click();
    }
    public void openSite(ChromeDriver chromeDriver, String urlAddress) {
        chromeDriver.get(urlAddress);
        chromeDriver.manage().window().maximize();
    }
}
