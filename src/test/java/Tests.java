import org.json.JSONArray;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

public class Tests extends ParentClass {
//All Thread.sleep() were added to make tests more visual, they don't affect test's functionality
//Tests works well without them
    MethodsClass method = new MethodsClass();

    @Test
    public void test1() throws InterruptedException {

        chromeDriver.get(method.getHerokuAppAddress());
        chromeDriver.manage().window().maximize();
        Thread.sleep(500);

        WebElement addRemoveElements = chromeDriver.findElement(By.linkText(method.getRemoveElementsAddress()));
        addRemoveElements.click();
        Thread.sleep(500);

        WebElement addElementButton = chromeDriver.findElementByXPath(method.getAddElementButton());
        for (int i = 0; i <= 2; i++) {
            addElementButton.click();
            Thread.sleep(1000);
        }

        List<WebElement> deleteButtons = chromeDriver.findElementsByXPath(method.getAddedManually());
        Assert.assertEquals(deleteButtons.size(), 3, "There are NOT 3 delete buttons");

        deleteButtons.get(0).click();
        Thread.sleep(500);
        deleteButtons = chromeDriver.findElementsByXPath(method.getAddedManually());
        Assert.assertEquals(deleteButtons.size(), 2, "There are NOT 2 elements left");
    }

    @Test
    public void test2() throws InterruptedException {

        chromeDriver.get(method.getHerokuAppAddress());
        chromeDriver.manage().window().maximize();
        Thread.sleep(500);

        WebElement javaScriptAlerts = chromeDriver.findElement(By.linkText(method.getJavaScriptAlertsLink()));
        javaScriptAlerts.click();
        Thread.sleep(500);

        try {
            WebElement clickForJSAlertButton = chromeDriver.findElementByXPath(method.getClickForJSAlertXPath());
            clickForJSAlertButton.click();
            Thread.sleep(500);
            chromeDriver.switchTo().alert().accept();
        } catch (TimeoutException e) {
            System.out.println("The alert hasn't appeared");
        }
        Thread.sleep(500);

        WebElement alertResult = chromeDriver.findElementByXPath(method.getAlertResult());
        Assert.assertEquals(alertResult.getText(), "You successfuly clicked an alert",
                "The Result text is wrong");

        try {
            WebElement clickForJSConfirm = chromeDriver.findElementByXPath(method.getClickForJSConfirm());
            clickForJSConfirm.click();
            Thread.sleep(500);
            chromeDriver.switchTo().alert().dismiss();
        } catch (TimeoutException e) {
            System.out.println("The alert hasn't appeared");
        }
        Thread.sleep(500);

        alertResult = chromeDriver.findElementByXPath(method.getAlertResult());
        Assert.assertEquals(alertResult.getText(), "You clicked: Cancel",
                "The Result text is wrong");

        try {
            WebElement clickForJSPrompt = chromeDriver.findElementByXPath(method.getClickForJSPrompt());
            clickForJSPrompt.click();
            Thread.sleep(500);
            Alert alert = chromeDriver.switchTo().alert();
            alert.sendKeys("asd");
            alert.accept();
        } catch (TimeoutException e) {
            System.out.println("The alert hasn't appeared");
        }
        Thread.sleep(500);

        alertResult = chromeDriver.findElementByXPath(method.getAlertResult());
        Assert.assertEquals(alertResult.getText(), "You entered: asd",
                "The Result text is wrong");
    }

    @Test
    public void test3() throws InterruptedException, IOException {

        chromeDriver.get(method.getHerokuAppAddress());
        chromeDriver.manage().window().maximize();
        Thread.sleep(500);

        WebElement frames = chromeDriver.findElementByXPath(method.getFramesLink());
        frames.click();
        Thread.sleep(500);
        WebElement iFrame = chromeDriver.findElementByXPath(method.getiFrameLink());
        iFrame.click();
        Thread.sleep(500);

        chromeDriver.switchTo().frame(method.getiFrameField());
        JavascriptExecutor js = chromeDriver;
        WebElement iFrameInput;
        js.executeScript("arguments[0].click();", iFrameInput = chromeDriver
                .findElement(By.xpath(method.getiFrameInputField())));
        iFrameInput.clear();
        Thread.sleep(500);

        String httpResponse = method.runHttpRequest();
        JSONArray httpResponseJSON = method.convertStringToJsonObject(httpResponse);
        List<String> titles = method.getTitles(httpResponseJSON);
        iFrameInput.sendKeys(String.join("\n", titles));
        Thread.sleep(500);
    }

    @Test
    public void test4() throws InterruptedException {

        chromeDriver.get(method.getHerokuAppAddress());
        chromeDriver.manage().window().maximize();
        Thread.sleep(500);

        WebElement fileUpload = chromeDriver.findElementByXPath(method.getFileUploadLink());
        fileUpload.click();
        Thread.sleep(500);

        WebElement fileUploadButton = chromeDriver.findElementByXPath(method.getFileUploadButton());
        fileUploadButton.sendKeys(method.getAbsoluteFilePath(
                "src/main/resources/files_for_upload/red.jpg"));
        Thread.sleep(500);

        WebElement fileSubmit = chromeDriver.findElementByXPath(method.getFileSubmitButton());
        fileSubmit.click();
        Thread.sleep(500);

        WebElement fileUploaded = chromeDriver.findElementByXPath(method.getFileUploadedConfirm());
        Assert.assertEquals(fileUploaded.getText(), "File Uploaded!",
                "Wasn't received message about successful file uploading");
        Thread.sleep(1000);
    }
}
