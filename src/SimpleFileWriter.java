import java.io.FileWriter;
import java.io.IOException;

public class SimpleFileWriter {
    private final String path;
    private final String text;

    public SimpleFileWriter(String path, String text) {
        this.path = path;
        this.text = text;
    }

    public void write() throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write(text);
        fileWriter.close();
    }
}
