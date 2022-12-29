import java.util.AbstractList;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Класс, представляющий стопку файлов, имеющих зависимости.
 */
public class FileStack {
    private final SortedSet<FileCoin> files = new TreeSet<>();
    private final ArrayList<String> fileNames = new ArrayList<>();

    /**
     * Конструктор класса по усмолчанию, представляющего стопку файлов, имеющих зависимости.
     */
    public FileStack() {
    }

    /**
     * Конструктор класса, представляющего стопку файлов, имеющих зависимости.
     *
     * @param file Файл, имеющий зависимости, который необходимо добавить в стопку.
     */
    public FileStack(FileCoin file) {
        addFile(file);
    }

    /**
     * Метод, добавляющий файл в стопку.
     *
     * @param file Файл, имеющий зависимости, который необходимо добавить в стопку.
     */
    public void addFile(FileCoin file) {
        files.add(file);
        fileNames.add(file.getName());
    }

    /**
     * Метод, возвращающий информацию о том, содержатся ли данные файлы в стопке.
     *
     * @param fileNames Cписок имен файлов, которые необходимо проверить на наличие в стопке.
     * @return true, если все файлы содержатся в стопке, иначе false.
     */
    public boolean containsFiles(AbstractList<String> fileNames) {
        for (String name : fileNames) {
            if (!this.fileNames.contains(name)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Метод, объединяющий две стопки файлов, имеющих зависимости.
     *
     * @param fileStack Стопка файлов, имеющих зависимости, которую необходимо объединить с текущей.
     * @return Объединенная стопка файлов, имеющих зависимости.
     */
    public FileStack unite(FileStack fileStack) {
        FileStack newFileStack = new FileStack();
        for (FileCoin file : files) {
            newFileStack.addFile(file);
        }
        for (FileCoin file : fileStack.files) {
            newFileStack.addFile(file);
        }
        return newFileStack;
    }

    /**
     * Метод, возвращающий информацию о том, соблюдены ли все зависимости файлов в стопке.
     *
     * @return true, если все зависимости соблюдены, иначе false.
     */
    public boolean isFull() {
        ArrayList<String> requests = new ArrayList<>();
        for (FileCoin file : files) {
            requests.addAll(file.getRequiredFiles());
            if (requests.contains(file.getName())) {
                return false;
            }
        }
        return containsFiles(requests);
    }

    /**
     * Метод, возвращающий список зависимостей верхнего файла в стопке.
     *
     * @return Список зависимостей верхнего файла в стопке.
     */
    public ArrayList<String> getTopRequests() {
        return files.first().getRequiredFiles();
    }

    /**
     * Метод, возвращающий полный текст всех файлов в стопке.
     *
     * @return Полный текст всех файлов в стопке.
     */
    public String getFullText() {
        StringBuilder fullText = new StringBuilder();
        for (FileCoin file : files) {
            fullText.append(file.getText());
            fullText.append("\n");
        }
        return fullText.toString();
    }
}
