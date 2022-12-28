import java.util.Scanner;

public class ConsoleUserInterface {
    private final Scanner scanner;

    public ConsoleUserInterface(Scanner scanner) {
        this.scanner = scanner;
    }

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
            System.out.println(fileStacksCollection.getContents());
        } else {
            System.out.println("Невозможно склеить файлы из-за ошибки в их зависимостях");
        }
    }
}
