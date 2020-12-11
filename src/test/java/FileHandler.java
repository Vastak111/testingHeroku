import java.io.File;

public class FileHandler {
    public String getAbsoluteFilePath(String relativeFilePath) {
        File file = new File(relativeFilePath);
        return file.getAbsolutePath();
    }
}
