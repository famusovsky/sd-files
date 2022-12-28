import java.io.FileWriter;
import java.io.IOException;
import java.util.AbstractList;

public class SimpleFileWriter {
    private final String path;

    public SimpleFileWriter(String path) {
        this.path = path;
    }

    public void write(String text) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write(text);
        fileWriter.close();
    }

    public void write(AbstractList<String> text) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        for (String line : text) {
            fileWriter.write(line);
        }
        fileWriter.close();
    }
}
