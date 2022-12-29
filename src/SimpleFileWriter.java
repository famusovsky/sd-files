import java.io.FileWriter;
import java.io.IOException;
import java.util.AbstractList;

/**
 * Класс, представляющий запись в файл.
 */
public class SimpleFileWriter {
    private final String path;

    /**
     * Конструктор класса, представляющего запись в файл.
     *
     * @param path Путь к файлу.
     */
    public SimpleFileWriter(String path) {
        this.path = path;
    }

    /**
     * Метод, записывающий в файл.
     *
     * @param text Текстовая строка, которую нужно записать в файл.
     * @throws IOException Исключение, которое может возникнуть при записи в файл.
     */
    public void write(String text) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write(text);
        fileWriter.close();
    }

    /**
     * Метод, записывающий в файл.
     *
     * @param text Набор текстовых строк, которые нужно записать в файл.
     * @throws IOException Исключение, которое может возникнуть при записи в файл.
     */
    public void write(AbstractList<String> text) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        for (String line : text) {
            fileWriter.write(line);
        }
        fileWriter.close();
    }
}
