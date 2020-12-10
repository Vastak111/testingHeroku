import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class ParentClass {
    protected ChromeDriver chromeDriver;
    protected MethodsClass methodsClass;

    @BeforeTest
    public void getDriver() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/driver/chromedriver.exe");
        chromeDriver = new ChromeDriver();
        methodsClass = new MethodsClass();
    }

    @AfterTest
    public void afterTest() {
        chromeDriver.quit();
    }
}
