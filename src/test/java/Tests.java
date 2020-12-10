import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Iterator;
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

    @Test
    public void test3() throws InterruptedException, UnirestException {

        chromeDriver.get(method.getHerokuAppAddress());
        chromeDriver.manage().window().maximize();
        Thread.sleep(500);//waiting for element to appear

        WebElement frames = chromeDriver.findElementByXPath(method.getFramesLink());
        frames.click();
        Thread.sleep(500);//waiting for element to appear
        WebElement iFrame = chromeDriver.findElementByXPath(method.getiFrameLink());
        iFrame.click();
        Thread.sleep(500);//waiting for element to appear

        chromeDriver.switchTo().frame(method.getiFrameField());
        JavascriptExecutor js = chromeDriver;
        WebElement iFrameInput;
        js.executeScript("arguments[0].click();", iFrameInput = chromeDriver
                .findElement(By.xpath(method.getiFrameInputField())));
        iFrameInput.clear();

        JsonNode body = Unirest.get(method.getSearchQueryApi())
                .asJson()
                .getBody();

        Iterator arrayOfBodies = body.getArray().iterator();
        while (arrayOfBodies.hasNext()) {
            JSONObject element = (JSONObject) arrayOfBodies.next();
            iFrameInput.sendKeys(element.get("title") + "\n");
        }
    }

//
//    private OkHttpClient client = new OkHttpClient(); //this way is picked in Selenium documentation,
//    //but as far as I've found Request.Builder() is not included in it's standalone library, so it's not
    // completely native
//
//    public void run() throws Exception {
//        Request request = new Request.Builder()
//                .url("http://jsonplaceholder.typicode.com/todos?_start=0&_limit=5")
//                .build();
//
//        try (Response response = client.newCall(request).execute()) {
//            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//            Headers responseHeaders = response.headers();
//            for (int i = 0; i < responseHeaders.size(); i++) {
//                System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
//            }



    @Test
    public void test4() throws InterruptedException {

        chromeDriver.get(method.getHerokuAppAddress());
        chromeDriver.manage().window().maximize();

        WebElement fileUpload = chromeDriver.findElementByXPath(method.getFileUploadLink());
        fileUpload.click();
        Thread.sleep(500); //waiting for page loading

        WebElement fileUploadButton = chromeDriver.findElementByXPath(method.getFileUploadButton());
        fileUploadButton.sendKeys(method.getAbsoluteFilePath(
                "src/test/java/files_for_upload/red.jpg")); //insert here
        // relative address of file you want to upload
        Thread.sleep(500); //waiting for page loading

        WebElement fileSubmit = chromeDriver.findElementByXPath(method.getFileSubmitButton());
        fileSubmit.click();
        Thread.sleep(500); //waiting for page loading

        WebElement fileUploaded = chromeDriver.findElementByXPath(method.getFileUploadedConfirm());
        Assert.assertEquals(fileUploaded.getText(), "File Uploaded!",
                "Wasn't received message about successful file uploading");
    }
}
