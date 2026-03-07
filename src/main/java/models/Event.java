public class Event {

    private String type;
    private Repo repo;
    private Payload payload;

    public Event() {}

    public String getType() {
        return this.type;
    }

    public Repo getRepo() {
        return this.repo;
    }

    public Payload getPayload() {
        return this.payload;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }
}