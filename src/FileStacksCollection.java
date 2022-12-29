import java.util.ArrayList;

/**
 * Класс, представляющий коллекцию стопок файлов, имеющих зависимости.
 */
public class FileStacksCollection {
    private final ArrayList<FileStack> fileStacks = new ArrayList<FileStack>();

    /**
     * Конструктор класса по умолчанию, представляющего коллекцию стопок файлов, имеющих зависимости.
     */
    public FileStacksCollection() {
    }

    /**
     * Конструктор класса, представляющего коллекцию стопок файлов, имеющих зависимости.
     *
     * @param fileCoin Стопка файлов, имеющих зависимости, которую необходимо добавить в коллекцию.
     */
    public FileStacksCollection(FileCoin fileCoin) {
        addFile(fileCoin);
    }

    /**
     * Конструктор класса, представляющего коллекцию стопок файлов, имеющих зависимости.
     *
     * @param fileCoins Стопки файлов, имеющих зависимости, которые необходимо добавить в коллекцию.
     */
    public FileStacksCollection(ArrayList<FileCoin> fileCoins) {
        for (FileCoin fileCoin : fileCoins) {
            addFile(fileCoin);
        }
    }

    /**
     * Метод, добавляющий файл, имеющий зависимости, в коллекцию.
     *
     * @param file Файл, имеющий зависимости, который необходимо добавить в коллекцию.
     */
    public void addFile(FileCoin file) {
        for (FileStack fileStack : fileStacks) {
            if (file.getRequiredFiles().size() != 0 && fileStack.containsFiles(file.getRequiredFiles())) {
                fileStack.addFile(file);
                return;
            }
        }
        FileStack fileStack = new FileStack(file);
        fileStacks.add(fileStack);
    }

    /**
     * Метод, по возможности объединяющий стопки файлов, имеющих зависимости, в коллекции.
     *
     * @return true если во всех полученных стопках файлов, имеющих зависимости, соблюдены все зависимости, иначе false.
     */
    public boolean normaliseStacks() {
        for (int i = 0; i < fileStacks.size(); ++i) {
            for (int j = i + 1; j < fileStacks.size(); ++j) {
                if (fileStacks.get(i).containsFiles(fileStacks.get(j).getTopRequests())) {
                    fileStacks.set(i, fileStacks.get(i).unite(fileStacks.get(j)));
                    fileStacks.remove(j);
                    --j;
                } else if (fileStacks.get(j).containsFiles(fileStacks.get(i).getTopRequests())) {
                    fileStacks.set(j, fileStacks.get(j).unite(fileStacks.get(i)));
                    fileStacks.remove(i);
                    --i;
                    break;
                }
            }
        }
        for (FileStack fileStack : fileStacks) {
            if (!fileStack.isFull()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Метод, возвращающий набор полных текстов всех стопок файлов, имеющих зависимости, в коллекции.
     *
     * @return Набор полных текстов всех стопок файлов, имеющих зависимости, в коллекции.
     */
    public ArrayList<String> getContents() {
        ArrayList<String> contents = new ArrayList<>();
        for (FileStack fileStack : fileStacks) {
            contents.add(fileStack.getFullText());
        }
        return contents;
    }

    /**
     * Метод, возвращающий все стопки файлов, имеющих зависимости, в коллекции.
     *
     * @return Все стопки файлов, имеющих зависимости, в коллекции.
     */
    public ArrayList<FileStack> getFileStacks() {
        return fileStacks;
    }
}
