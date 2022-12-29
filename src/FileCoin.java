import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс, представляющий файл, имеющий зависимости от других файлов.
 */
public class FileCoin implements Comparable<FileCoin> {
    private final String name;
    private final String text;
    private final ArrayList<String> requiredFiles;

    /**
     * Конструктор класса, представляющего файл, имеющий зависимости от других файлов.
     *
     * @param name Имя файла.
     * @param text Текст файла.
     */
    public FileCoin(String name, String text) {
        this.name = name;
        this.text = text;
        this.requiredFiles = new ArrayList<>();
        String regex = "require\\s['‘’][^'‘’]*['‘’]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String requiredFile = matcher.group();
            requiredFile = requiredFile.substring(9, requiredFile.length() - 1);
            requiredFiles.add(requiredFile);
        }
    }

    /**
     * Метод, возвращающий имя файла.
     *
     * @return Имя файла.
     */
    public String getName() {
        return name;
    }

    /**
     * Метод, возвращающий текст файла.
     *
     * @return Текст файла.
     */
    public String getText() {
        return text;
    }

    /**
     * Метод, возвращающий список зависимостей файла.
     *
     * @return Список зависимостей файла.
     */
    public ArrayList<String> getRequiredFiles() {
        return requiredFiles;
    }

    /**
     * Метод, сравнивающий файлы по их зависимостям.
     *
     * @param o Файл, с которым сравнивается текущий файл.
     * @return Результат сравнения.
     */
    @Override
    public int compareTo(FileCoin o) {
        if (this.name.equals(o.name)) {
            return 0;
        }
        if (this.getRequiredFiles().contains(o.getName())) {
            return 1;
        } else {
            return -1;
        }
    }
}
