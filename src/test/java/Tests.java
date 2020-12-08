import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class Tests extends ParentClass {

    MethodsClass method = new MethodsClass();

    @Test
    public void test1() throws InterruptedException, IOException {

        chromeDriver.get(method.getHerokuAppAddress());
        chromeDriver.manage().window().maximize();

        WebElement addRemoveElements = chromeDriver.findElement(By.linkText(method.getRemoveElementsAddress()));
        addRemoveElements.click();

        WebElement addElementButton = chromeDriver.findElementByCssSelector(method.getAddElementButton());
        for (int i = 0; i <= 2; i++) {
            addElementButton.click();
            Thread.sleep(1000); //waiting for elements to appear
        }

        List<WebElement> deleteButtons = chromeDriver.findElementsByClassName(method.getAddedManually());
        Assert.assertEquals(deleteButtons.size(), 3, "There are NOT 3 delete buttons");

        deleteButtons.get(0).click();
        Thread.sleep(500); //waiting for element to disappear
        deleteButtons = chromeDriver.findElementsByClassName(method.getAddedManually());
        Assert.assertEquals(deleteButtons.size(), 2, "There are NOT 2 elements left");
    }


}
