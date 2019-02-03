package Server.User;

public class ScoreboardProfile extends BaseProfile {
    private String name;
    private int money;

    ScoreboardProfile(String username, String name, int money) {
        super(username);
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }
}
