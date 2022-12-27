import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class FileStack {
    private final SortedSet<File> files = new TreeSet<File>();
    private final ArrayList<String> fileNames = new ArrayList<String>();

    public FileStack() {
    }

    public FileStack(File file) {
        addFile(file);
    }

    public void addFile(File file) {
        files.add(file);
        fileNames.add(file.getName());
    }

    public boolean containsFiles(ArrayList<String> fileName) {
        for (String name : fileName) {
            if (!fileNames.contains(name)) {
                return false;
            }
        }
        return true;
    }

    public FileStack unite(FileStack fileStack) {
        FileStack newFileStack = new FileStack();
        for (File file : files) {
            newFileStack.addFile(file);
        }
        for (File file : fileStack.files) {
            newFileStack.addFile(file);
        }
        return newFileStack;
    }

    public boolean isFull() {
        for (File file : files) {
            if (!containsFiles(file.getRequiredFiles())) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<String> getTopRequests() {
        return files.first().getRequiredFiles();
    }

    public String getFullText() {
        StringBuilder fullText = new StringBuilder();
        for (File file : files) {
            fullText.append(file.getText());
        }
        return fullText.toString();
    }
}
