import java.util.ArrayList;

public class FileStacksCollection {
    private final ArrayList<FileStack> fileStacks = new ArrayList<FileStack>();

    public FileStacksCollection() {
    }

    public FileStacksCollection(FileCoin fileCoin) {
        addFile(fileCoin);
    }

    public FileStacksCollection(ArrayList<FileCoin> fileCoins) {
        for (FileCoin fileCoin : fileCoins) {
            addFile(fileCoin);
        }
    }

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
        return (fileStacks.size() == 1 && fileStacks.get(0).isFull());
    }

    public String getContents() {
        StringBuilder contents = new StringBuilder();
        for (FileStack fileStack : fileStacks) {
            contents.append(fileStack.getFullText());
        }
        return contents.toString();
    }

    public ArrayList<FileStack> getFileStacks() {
        return fileStacks;
    }
}
