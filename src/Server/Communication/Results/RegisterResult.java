package Server.Communication.Results;

import Server.User.AuthenticationProfile;

public class RegisterResult extends BaseResult {
    private AuthenticationProfile authenticationProfile;

    public RegisterResult(AuthenticationProfile authenticationProfile) {
        super(200);
        this.authenticationProfile = authenticationProfile;
    }

    public RegisterResult(int statusCode, AuthenticationProfile authenticationProfile) {
        super(statusCode);
        this.authenticationProfile = authenticationProfile;
    }

    public AuthenticationProfile getAuthenticationProfile() {
        return authenticationProfile;
    }
}
