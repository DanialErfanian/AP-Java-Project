package Server.Communication.Results;

import Server.User.HostProfile;

public class GetProfileResult extends BaseResult {
    private HostProfile hostProfile;

    public GetProfileResult(HostProfile hostProfile) {
        super(200);
        this.hostProfile = hostProfile;
    }

    public HostProfile getHostProfile() {
        return hostProfile;
    }
}
