package Server.User;

public class AuthenticationProfile extends BaseProfile {
    private String token;

    public AuthenticationProfile(String username, String token) {
        super(username);
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
