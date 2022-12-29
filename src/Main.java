import java.util.Scanner;

/**
 * Главный класс программы.
 */
public class Main {
    /**
     * Главный метод программы.
     *
     * @param args Аргументы командной строки.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsoleUserInterface consoleUserInterface = new ConsoleUserInterface(scanner);
        while (true) {
            System.out.println("Введите 1 / 2 (1 - склеить файлы, 2 - выход)");
            String input = scanner.nextLine();
            if (input.equals("1")) {
                consoleUserInterface.concatenate();
            } else if (input.equals("2")) {
                break;
            } else {
                System.out.println("Неверный ввод");
            }
        }
    }
}