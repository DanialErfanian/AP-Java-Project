package Server.User;

import Utils.NetworkConfig;

public class RegisterProfile extends BaseProfile {
    private String name;
    private NetworkConfig netConf;

    public RegisterProfile(String username, String name, NetworkConfig netConf) {
        super(username);
        this.name = name;
        this.netConf = netConf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NetworkConfig getNetConf() {
        return netConf;
    }
}
