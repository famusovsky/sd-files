import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class FileStack {
    private final SortedSet<FileCoin> files = new TreeSet<>();
    private final ArrayList<String> fileNames = new ArrayList<String>();

    public FileStack() {
    }

    public FileStack(FileCoin file) {
        addFile(file);
    }

    public void addFile(FileCoin file) {
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
        for (FileCoin file : files) {
            newFileStack.addFile(file);
        }
        for (FileCoin file : fileStack.files) {
            newFileStack.addFile(file);
        }
        return newFileStack;
    }

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

    public ArrayList<String> getTopRequests() {
        return files.first().getRequiredFiles();
    }

    public String getFullText() {
        StringBuilder fullText = new StringBuilder();
        for (FileCoin file : files) {
            fullText.append(file.getText());
        }
        return fullText.toString();
    }
}
