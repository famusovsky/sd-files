import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileCoin {
    private final String name;
    private final String text;
    private final ArrayList<String> requiredFiles;

    public FileCoin(String name, String text) {
        this.name = name;
        this.text = text;
        this.requiredFiles = new ArrayList<>();
        String regex = "require\\s\\'\\S+\\'";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String requiredFile = matcher.group();
            requiredFile = requiredFile.substring(8, requiredFile.length() - 1);
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

    //TODO: implement comparison of files
}
