public class GithubActivity {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Please provide a GitHub username");
            return;
        }

        String username = args[0];

        System.out.println("Fetching activity for: " + username);
    }
}