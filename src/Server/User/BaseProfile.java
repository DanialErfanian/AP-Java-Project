package Server.User;

public abstract class BaseProfile {
    private String username;

    public BaseProfile() {
    }

    public BaseProfile(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
