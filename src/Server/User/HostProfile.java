package Server.User;

import Utils.NetworkConfig;

public class HostProfile extends BaseProfile {
    private String name;
    private String token;
    private NetworkConfig netConf;
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

    public HostProfile(String username, String name, NetworkConfig netConf) {
        super(username);
        this.name = name;
        // TODO generate random token
        this.token = username;
        this.netConf = netConf;
    }

    public AuthenticationProfile toAuthenticationProfile() {
        return new AuthenticationProfile(this.getUsername(), this.getToken());
    }

    public ScoreboardProfile toScoreboardProfile() {
        return new ScoreboardProfile(this.getUsername(), this.getName(), this.getMoney());
    }

    public RegisterProfile toRegisterProfile() {
        return new RegisterProfile(this.getUsername(), this.getName());
    }

    public NetworkConfig getNetConf() {
        return netConf;
    }
}
