package Server.User;

import java.io.Serializable;

public abstract class BaseProfile implements Serializable {
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
