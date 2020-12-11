public class XPathClass {

    private final String HEROKU_APP_ADDRESS = "http://the-internet.herokuapp.com/";
    private final String REMOVE_ELEMENTS_ADDRESS = "//a[contains(text(),'Add/Remove Elements')]";
    private final String ADD_ELEMENT_BUTTON = "//button[contains(text(),'Add Element')]";
    private final String ADDED_MANUALLY = "//button[contains(text(),'Delete')]";
    private final String JAVASCRIPT_ALERTS_LINK = "//a[contains(text(),'JavaScript Alerts')]";
    private final String CLICK_FOR_JS_ALERT = "//button[contains(text(),'Click for JS Alert')]";
    private final String ALERT_RESULT = "//p[@id='result']";
    private final String CLICK_FOR_JS_CONFIRM = "//button[contains(text(),'Click for JS Confirm')]";
    private final String CLICK_FOR_JS_PROMPT = "//button[contains(text(),'Click for JS Prompt')]";
    private final String FRAMES_LINK = "//a[contains(text(),'Frames')]";
    private final String IFRAME_LINK = "//a[contains(text(),'iFrame')]";
    private final String fileUploadLink = "//a[contains(text(), 'File Upload')]";
    private final String FILE_UPLOAD_LINK = "//input[@id='file-upload']";
    private final String FILE_SUBMIT_BUTTON = "//*[@id='file-submit']";
    private final String FILE_UPLOADED_CONFIRM = "//h3[contains(text(),'File Uploaded!')]";
    private final String IFRAME_FIELD = "mce_0_ifr";
    private final String IFRAME_INPUT_FIELD = "//body[@id='tinymce']";
    private final String API_REQUEST_URL = "http://jsonplaceholder.typicode.com/todos?_start=0&_limit=5";
    private final String JS_CODE_IFRAME = "arguments[0].click();";

    public String getJSCodeIframe() {
        return JS_CODE_IFRAME;
    }

    public String getHerokuAppAddress() {
        return HEROKU_APP_ADDRESS;
    }

    public String getRemoveElementsAddress() {
        return REMOVE_ELEMENTS_ADDRESS;
    }

    public String getAddElementButton() {
        return ADD_ELEMENT_BUTTON;
    }

    public String getAddedManually() {
        return ADDED_MANUALLY;
    }

    public String getJavaScriptAlertsLink() {
        return JAVASCRIPT_ALERTS_LINK;
    }

    public String getClickForJSAlertXPath() {
        return CLICK_FOR_JS_ALERT;
    }

    public String getAlertResult() {
        return ALERT_RESULT;
    }

    public String getClickForJSConfirm() {
        return CLICK_FOR_JS_CONFIRM;
    }

    public String getClickForJSPrompt() {
        return CLICK_FOR_JS_PROMPT;
    }

    public String getFramesLink() {
        return FRAMES_LINK;
    }

    public String getiFrameLink() {
        return IFRAME_LINK;
    }

    public String getFileUploadLink() {
        return fileUploadLink;
    }

    public String getFileUploadButton() {
        return FILE_UPLOAD_LINK;
    }

    public String getFileSubmitButton() {
        return FILE_SUBMIT_BUTTON;
    }

    public String getFileUploadedConfirm() {
        return FILE_UPLOADED_CONFIRM;
    }

    public String getiFrameField() {
        return IFRAME_FIELD;
    }

    public String getiFrameInputField() {
        return IFRAME_INPUT_FIELD;
    }

    public String getApiRequestURL() {
        return API_REQUEST_URL;
    }
}