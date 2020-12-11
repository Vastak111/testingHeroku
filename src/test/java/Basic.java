import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Basic {
    private ChromeDriver chromeDriver;
    protected XPathClass XPathClass;

    public ChromeDriver getChromeDriver() {
        if (chromeDriver == null)
            chromeDriver = new ChromeDriver();
        return chromeDriver;
    }

    public void click(String xPath) {
        Actions.INSTANCE.click(chromeDriver, xPath);
    }

    @BeforeMethod
    public void getDriver() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/driver/chromedriver.exe");
        chromeDriver = new ChromeDriver();
        XPathClass = new XPathClass();
    }

    @AfterMethod
    public void afterTest() {
        chromeDriver.quit();
    }
}
