public class GithubActivity {

    public static void main(String[] args) throws Exception{

        if (args.length == 0) {
            System.out.println("Please provide a GitHub username");
            return;
        }

        String username = args[0];

        String json = GithubService.fetchEvent(username);

        System.out.println(json);
    }
}