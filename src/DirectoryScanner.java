import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class DirectoryScanner {
    File directory;

    public DirectoryScanner(String path) throws IllegalArgumentException {
        directory = new File(path);
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("По указанному пути не найдена директория");
        }
    }

    public ArrayList<FileCoin> getFileCoins() {
        ArrayList<FileCoin> fileCoins = new ArrayList<>();
        try {
            scanDirectory(directory, fileCoins);
        } catch (FileNotFoundException e) {
            System.out.println("Произошла ошибка при чтении файла");
        } catch (SecurityException e) {
            System.out.println("Произошла ошибка доступа к файлу, из-за этого некоторые файлы могли быть не прочитаны");
        }
        return fileCoins;
    }

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
                        int ch;
                        while ((ch = fileReader.read()) != -1) {
                            fileText.append((char) ch);
                        }
                        fileCoins.add(new FileCoin(item.getAbsolutePath(), fileText.toString()));
                    } catch (IOException e) {
                        throw new FileNotFoundException();
                    }
                }
            }
        } catch (NullPointerException e) {
            throw new FileNotFoundException();
        } catch (SecurityException e) {
            throw new SecurityException();
        }
    }
}
