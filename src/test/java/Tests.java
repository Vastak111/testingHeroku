import org.json.JSONArray;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

public class Tests extends Basic {
    private XPathClass xPath = new XPathClass();
    private FileHandler fileHandler = new FileHandler();
    private JSONHandler jsonHandler = new JSONHandler();

    @Test
    public void test1() {

        Actions.INSTANCE.openSite(getChromeDriver(), xPath.getHerokuAppAddress());

        click(xPath.getRemoveElementsAddress());

        for (int i = 0; i < 3; i++) {
            click(xPath.getAddElementButton());
        }

        List<WebElement> deleteButtons = getChromeDriver().findElementsByXPath(xPath.getAddedManually());
        Assert.assertEquals(deleteButtons.size(), 3, "There are NOT 3 delete buttons");

        click(xPath.getAddedManually());
        deleteButtons = getChromeDriver().findElementsByXPath(xPath.getAddedManually());
        Assert.assertEquals(deleteButtons.size(), 2, "There are NOT 2 elements left");
    }

    @Test
    public void test2() throws InterruptedException {

        Actions.INSTANCE.openSite(getChromeDriver(), xPath.getHerokuAppAddress());

        WebElement javaScriptAlerts = getChromeDriver().findElementByXPath(xPath.getJavaScriptAlertsLink());
        javaScriptAlerts.click();

        try {
            click(xPath.getClickForJSAlertXPath());
            getChromeDriver().switchTo().alert().accept();
        } catch (TimeoutException e) {
            Assert.fail("The alert hasn't appeared");
        }

        WebElement alertResult = getChromeDriver().findElementByXPath(xPath.getAlertResult());
        Assert.assertEquals(alertResult.getText(), "You successfuly clicked an alert",
                "The Result text is wrong");

        try {
            click(xPath.getClickForJSConfirm());
            getChromeDriver().switchTo().alert().dismiss();
        } catch (TimeoutException e) {
            Assert.fail("The alert hasn't appeared");
        }

        alertResult = getChromeDriver().findElementByXPath(xPath.getAlertResult());
        Assert.assertEquals(alertResult.getText(), "You clicked: Cancel",
                "The Result text is wrong");

        try {
            click(xPath.getClickForJSPrompt());
            Alert alert = getChromeDriver().switchTo().alert();
            alert.sendKeys("asd");
            alert.accept();
        } catch (TimeoutException e) {
            Assert.fail("The alert hasn't appeared");
        }

        alertResult = getChromeDriver().findElementByXPath(xPath.getAlertResult());
        Assert.assertEquals(alertResult.getText(), "You entered: asd",
                "The Result text is wrong");
    }

    @Test
    public void test3() throws InterruptedException, IOException {

        Actions.INSTANCE.openSite(getChromeDriver(), xPath.getHerokuAppAddress());

        click(xPath.getFramesLink());
        click(xPath.getiFrameLink());

        getChromeDriver().switchTo().frame(xPath.getiFrameField());
        JavascriptExecutor js = getChromeDriver();
        WebElement iFrameInput;
        js.executeScript(xPath.getJSCodeIframe(), iFrameInput = getChromeDriver()
                .findElement(By.xpath(xPath.getiFrameInputField())));
        iFrameInput.clear();

        String httpResponse = HttpClient.INSTANCE.runHttpRequest(xPath.getApiRequestURL());
        JSONArray httpResponseJSON = new JSONArray(httpResponse);
        List<String> titles = jsonHandler.getTitles(httpResponseJSON);
        iFrameInput.sendKeys(String.join("\n", titles));
    }

    @Test
    public void test4() throws InterruptedException {

        Actions.INSTANCE.openSite(getChromeDriver(), xPath.getHerokuAppAddress());

        WebElement fileUpload = getChromeDriver().findElementByXPath(xPath.getFileUploadLink());
        fileUpload.click();

        WebElement fileUploadButton = getChromeDriver().findElementByXPath(xPath.getFileUploadButton());
        fileUploadButton.sendKeys(fileHandler.getAbsoluteFilePath(
                "src/main/resources/files_for_upload/red.jpg"));

        WebElement fileSubmit = getChromeDriver().findElementByXPath(xPath.getFileSubmitButton());
        fileSubmit.click();

        WebElement fileUploaded = getChromeDriver().findElementByXPath(xPath.getFileUploadedConfirm());
        Assert.assertEquals(fileUploaded.getText(), "File Uploaded!",
                "Wasn't received message about successful file uploading");
    }
}
