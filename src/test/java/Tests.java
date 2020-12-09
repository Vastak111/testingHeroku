import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class Tests extends ParentClass {

    MethodsClass method = new MethodsClass();

    @Test
    public void test1() throws InterruptedException {

        chromeDriver.get(method.getHerokuAppAddress());
        chromeDriver.manage().window().maximize();

        WebElement addRemoveElements = chromeDriver.findElement(By.linkText(method.getRemoveElementsAddress()));
        addRemoveElements.click();

        WebElement addElementButton = chromeDriver.findElementByXPath(method.getAddElementButton());
        for (int i = 0; i <= 2; i++) {
            addElementButton.click();
            Thread.sleep(1000); //waiting for elements to appear
        }

        List<WebElement> deleteButtons = chromeDriver.findElementsByXPath(method.getAddedManually());
        Assert.assertEquals(deleteButtons.size(), 3, "There are NOT 3 delete buttons");

        deleteButtons.get(0).click();
        Thread.sleep(500); //waiting for element to disappear
        deleteButtons = chromeDriver.findElementsByXPath(method.getAddedManually());
        Assert.assertEquals(deleteButtons.size(), 2, "There are NOT 2 elements left");
    }

    @Test
    public void test2() throws InterruptedException {

        chromeDriver.get(method.getHerokuAppAddress());
        chromeDriver.manage().window().maximize();

        WebElement javaScriptAlerts = chromeDriver.findElement(By.linkText(method.getJavaScriptAlertsLink()));
        javaScriptAlerts.click();

       try {
           WebElement clickForJSAlertButton = chromeDriver.findElementByXPath(method.getClickForJSAlertXPath());
        clickForJSAlertButton.click();
        Thread.sleep(500); //waiting for element to appear
        chromeDriver.switchTo().alert().accept();
       } catch (TimeoutException e) {
           System.out.println("The alert hasn't appeared");
       }
        Thread.sleep(500); //waiting for text to appear

        WebElement alertResult = chromeDriver.findElementByXPath(method.getAlertResult());
        Assert.assertEquals(alertResult.getText(), "You successfuly clicked an alert",
                "The Result text is wrong");

        try {
            WebElement clickForJSConfirm = chromeDriver.findElementByXPath(method.getClickForJSConfirm());
            clickForJSConfirm.click();
            Thread.sleep(500);//waiting for element to appear
            chromeDriver.switchTo().alert().dismiss();
        } catch (TimeoutException e) {
            System.out.println("The alert hasn't appeared");
        }
        Thread.sleep(500); //waiting for text to appear

        alertResult = chromeDriver.findElementByXPath(method.getAlertResult());
        Assert.assertEquals(alertResult.getText(), "You clicked: Cancel",
                "The Result text is wrong");

        try {
            WebElement clickForJSPrompt = chromeDriver.findElementByXPath(method.getClickForJSPrompt());
            clickForJSPrompt.click();
            Thread.sleep(500);//waiting for element to appear
            Alert alert = chromeDriver.switchTo().alert();
            alert.sendKeys("asd");
            alert.accept();
        } catch (TimeoutException e) {
            System.out.println("The alert hasn't appeared");
        }
        Thread.sleep(500); //waiting for text to appear

        alertResult = chromeDriver.findElementByXPath(method.getAlertResult());
        Assert.assertEquals(alertResult.getText(), "You entered: asd",
                "The Result text is wrong");
    }


}
