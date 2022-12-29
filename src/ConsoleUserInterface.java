import java.util.Scanner;

/**
 * Класс, представляющий консольный пользовательский интерфейс.
 */
public class ConsoleUserInterface {
    private final Scanner scanner;

    /**
     * Конструктор класса, представляющего консольный пользовательский интерфейс.
     *
     * @param scanner Сканнер, с помощью которого будет осуществляться ввод.
     */
    public ConsoleUserInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Метод, позволяющий пользователю осуществить конкатенацию файлов в некоторой директории и всех её поддиректориях.
     */
    public void concatenate() {
        DirectoryScanner directoryScanner;
        FileStacksCollection fileStacksCollection;
        while (true) {
            System.out.println("Введите путь к директории, в которой хранятся файлы");
            try {
                directoryScanner = new DirectoryScanner(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        fileStacksCollection = new FileStacksCollection(directoryScanner.getFileCoins());
        if (fileStacksCollection.normaliseStacks()) {
            System.out.println("Файлы успешно склеены, полученный текст:");
            for (String line : fileStacksCollection.getContents()) {
                System.out.println(line);
            }
            while (true) {
                System.out.println("Введите 1 / 2 (1 - сохранить результат в файл, 2 - не сохранять)");
                String input = scanner.nextLine();
                if (input.equals("1")) {
                    System.out.println("Введите путь к файлу, в который нужно сохранить результат");
                    try {
                        SimpleFileWriter simpleFileWriter = new SimpleFileWriter(scanner.nextLine());
                        simpleFileWriter.write(fileStacksCollection.getContents());
                        break;
                    } catch (Exception e) {
                        System.out.println("Неверный ввод, ошибка: " + e.getMessage());
                    }
                } else if (input.equals("2")) {
                    break;
                } else {
                    System.out.println("Неверный ввод");
                }
            }
        } else {
            System.out.println("Невозможно склеить файлы из-за ошибки в их зависимостях");
        }
    }
}
