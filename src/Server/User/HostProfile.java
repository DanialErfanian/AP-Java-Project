package Server.User;

public class HostProfile extends BaseProfile {
    private String name;
    private String token;
    private int transactions_count = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getTransactions_count() {
        return transactions_count;
    }

    public void setTransactions_count(int transactions_count) {
        this.transactions_count = transactions_count;
    }

    public HostProfile(String username, String name) {
        super(username);
        this.name = name;
        // TODO generate random token
        this.token = username;
    }

    public AuthenticationProfile toAuthenticationProfile() {
        return new AuthenticationProfile(this.getUsername(), this.getToken());
    }
}
