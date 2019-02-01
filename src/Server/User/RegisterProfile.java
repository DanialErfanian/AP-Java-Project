package Server.User;

public class RegisterProfile extends BaseProfile {
    private String name;

    public RegisterProfile(String username, String name) {
        super(username);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
