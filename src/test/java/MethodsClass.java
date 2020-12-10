import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    private String framesLink = "//a[contains(text(),'Frames')]";
    private String iFrameLink = "//a[contains(text(),'iFrame')]";
    private String fileUploadLink = "//a[contains(text(), 'File Upload')]";
    private String fileUploadButton = "//input[@id='file-upload']";
    private String fileSubmitButton = "//*[@id='file-submit']";
    private String fileUploadedConfirm = "//h3[contains(text(),'File Uploaded!')]";
    private String iFrameField = "mce_0_ifr";
    private String iFrameInputField = "//body[@id='tinymce']";
    private String apiQuery = "http://jsonplaceholder.typicode.com/todos?_start=0&_limit=5";
    private OkHttpClient client = new OkHttpClient();

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

    public String getFramesLink() {
        return framesLink;
    }

    public String getiFrameLink() {
        return iFrameLink;
    }

    public String getFileUploadLink() {
        return fileUploadLink;
    }

    public String getFileUploadButton() {
        return fileUploadButton;
    }

    public String getFileSubmitButton() {
        return fileSubmitButton;
    }

    public String getFileUploadedConfirm() {
        return fileUploadedConfirm;
    }

    public String getiFrameField() {
        return iFrameField;
    }

    public String getiFrameInputField() {
        return iFrameInputField;
    }

    public String getAbsoluteFilePath(String relativeFilePath) {
        File file = new File(relativeFilePath);
        return file.getAbsolutePath();
    }

    public String runHttpRequest() throws IOException {
        Request request = new Request.Builder()
                .url(apiQuery)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + response);

        ResponseBody responseBody = response.body();
        if (responseBody != null) {
            return responseBody.string();
        } else throw new IOException("Body is null");
    }

    public JSONArray convertStringToJsonObject(String string) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(string);
        }catch (JSONException err){
            System.out.println("Error" + err.toString());
        }
        return jsonArray;
    }

    public List<String> getTitles(JSONArray jsonArray) {
        List<String> titlesList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String title = jsonObject.getString("title");
            titlesList.add(title);
        }
        return titlesList;
    }
}