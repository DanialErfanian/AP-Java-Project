package Server.Communication.Results;

public class GetIpResult extends BaseResult {
    private String ip;

    public GetIpResult() {
        super(200);
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
