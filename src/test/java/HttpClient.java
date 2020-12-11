import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import java.io.IOException;

public enum HttpClient {
    INSTANCE;

    public String runHttpRequest(String apiUrl) throws IOException {
        Request request = new Request.Builder()
                .url(apiUrl)
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + response);

        ResponseBody responseBody = response.body();
        if (responseBody != null) {
            return responseBody.string();
        } else throw new IOException("Body is null");
    }
}
