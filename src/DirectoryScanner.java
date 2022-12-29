import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Класс, представляющий сканер директории.
 */
public class DirectoryScanner {
    private final File directory;

    /**
     * Конструктор класса, представляющего сканер директории.
     *
     * @param path Путь к директории.
     * @throws IllegalArgumentException Ошибка, возникающая при некорректном пути к директории.
     */
    public DirectoryScanner(String path) throws IllegalArgumentException {
        directory = new File(path);
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("По указанному пути не найдена директория");
        }
    }

    /**
     * Метод, возвращающий список файлов в заданной директории и её поддиректориях.
     *
     * @return Список файлов в заданной директории и её поддиректориях.
     */
    public ArrayList<FileCoin> getFileCoins() {
        ArrayList<FileCoin> fileCoins = new ArrayList<>();
        try {
            scanDirectory(directory, fileCoins);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return fileCoins;
    }

    /**
     * Метод, возвращающий путь к заданной директории.
     *
     * @return Путь к заданной директории.
     */
    public String getDirectoryPath() {
        return directory.getAbsolutePath();
    }

    private void scanDirectory(File directory, ArrayList<FileCoin> fileCoins) throws FileNotFoundException, SecurityException {
        try {
            for (File item : Objects.requireNonNull(directory.listFiles())) {
                if (item.isDirectory()) {
                    scanDirectory(item, fileCoins);
                } else {
                    if (!item.getName().endsWith(".txt")) {
                        continue;
                    }
                    try (FileReader fileReader = new FileReader(item)) {
                        StringBuilder fileText = new StringBuilder();
                        StringBuilder fileName = new StringBuilder();
                        int ch;
                        while ((ch = fileReader.read()) != -1) {
                            fileText.append((char) ch);
                        }
                        fileName.append(item.getAbsolutePath());
                        fileName.delete(0, getDirectoryPath().length() + 1);
                        fileName.delete(fileName.length() - 4, fileName.length());
                        fileCoins.add(new FileCoin(fileName.toString(), fileText.toString()));
                    } catch (IOException e) {
                        throw new FileNotFoundException("Произошла ошибка при чтении файла");
                    }
                }
            }
        } catch (NullPointerException e) {
            throw new FileNotFoundException("Произошла ошибка при чтении файла");
        } catch (SecurityException e) {
            throw new SecurityException("Произошла ошибка доступа к файлу");
        }
    }
}
