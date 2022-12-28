import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileCoin implements Comparable<FileCoin> {
    private final String name;
    private final String text;
    private final ArrayList<String> requiredFiles;

    public FileCoin(String name, String text) {
        this.name = name;
        this.text = text;
        this.requiredFiles = new ArrayList<>();
        String regex = "require\\s['‘’][^'‘’]*['‘’]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String requiredFile = matcher.group();
            requiredFile = requiredFile.substring(9, requiredFile.length() - 1);
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
    public int compareTo(FileCoin o) {
        if (this.name.equals(o.name)) {
            return 0;
        }
        if (this.getRequiredFiles().contains(o.getName())) {
            return 1;
        } else {
            return -1;
        }
    }
}
