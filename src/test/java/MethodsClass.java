public class MethodsClass {

    private String herokuAppAddress = "http://the-internet.herokuapp.com/";
    private String removeElementsAddress = "Add/Remove Elements";
    private String addElementButton = "//button[contains(text(),'Add Element')]";
    private String addedManually = "//button[contains(text(),'Delete')]";
    private String javaScriptAlertsLink = "JavaScript Alerts";
    private String clickForJSAlertXPath = "//button[contains(text(),'Click for JS Alert')]";
    private String alertResult = "//p[@id='result']";
    private String clickForJSConfirm = "//button[contains(text(),'Click for JS Confirm')]";
    private String clickForJSPrompt = "//button[contains(text(),'Click for JS Prompt')]";

    public String getHerokuAppAddress() {
        return herokuAppAddress;
    }

    public String getRemoveElementsAddress() {
        return removeElementsAddress;
    }

    public String getAddElementButton() {
        return addElementButton;
    }

    public String getAddedManually() {
        return addedManually;
    }

    public String getJavaScriptAlertsLink() {
        return javaScriptAlertsLink;
    }

    public String getClickForJSAlertXPath() {
        return clickForJSAlertXPath;
    }
    public String getAlertResult() {
        return alertResult;
    }

    public String getClickForJSConfirm() {
        return clickForJSConfirm;
    }

    public String getClickForJSPrompt() {
        return clickForJSPrompt;
    }
}
