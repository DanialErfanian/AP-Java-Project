package Server.User;

public class HostProfile extends BaseProfile {
    private String name;
    private String token;
    private int transactionsCount = 0;
    private int money = 0;

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

    public int getTransactionsCount() {
        return transactionsCount;
    }

    public void setTransactionsCount(int transactionsCount) {
        this.transactionsCount = transactionsCount;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
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

    public ScoreboardProfile toScoreboardProfile() {
        return new ScoreboardProfile(this.getUsername(), this.getName(), this.getMoney());
    }
}
