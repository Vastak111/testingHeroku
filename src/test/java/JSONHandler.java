import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class JSONHandler {
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
