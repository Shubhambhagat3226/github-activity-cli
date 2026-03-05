import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GithubActivity {

    public static void main(String[] args) throws Exception{

        if (args.length == 0) {
            System.out.println("Please provide a GitHub username");
            return;
        }

        String username = args[0];

        try {

            String json = GithubService.fetchEvents(username);
            printEvents(json);

        } catch (Exception e) {
            System.out.println("Error fetching activity: " + e.getMessage());
        }
    }

    public static void printEvents(String json) {

        Pattern eventPattern = Pattern.compile("\"type\":\"(.*?)\".*?\"repo\":\\{\"id\":.*?,\"name\":\"(.*?)\"");
        Matcher matcher = eventPattern.matcher(json);

        while (matcher.find()) {

            String type = matcher.group(1);
            String repo = matcher.group(2);

            switch (type) {

                case "PushEvent":
                    System.out.println("- Pushed commits to " + repo);
                    break;

                case "WatchEvent":
                    System.out.println("- Starred " + repo);
                    break;

                case "CreateEvent":
                    System.out.println("- Created repository or branch in " + repo);
                    break;

                default:
                    break;
            }
        }
    }
}