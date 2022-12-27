import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class File implements Comparable<File> {
    private final String name;
    private final String text;
    private final ArrayList<String> requiredFiles;

    public File(String name, String text) {
        this.name = name;
        this.text = text;
        this.requiredFiles = new ArrayList<>();
        String regex = "require\\s['‘’][^'‘’]*[^'‘’]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String requiredFile = matcher.group();
            requiredFile = requiredFile.substring(9);
            requiredFiles.add(requiredFile);
        }
    }
    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public ArrayList<String> getRequiredFiles() {
        return requiredFiles;
    }
    @Override
    public int compareTo(File o) {
        if (this.getRequiredFiles().contains(o.getName())) {
            return 1;
        } else if (o.getRequiredFiles().contains(this.getName())) {
            return -1;
        } else {
            return 0;
        }
    }
}
