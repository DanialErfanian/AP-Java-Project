package Server.Communication.Commands;

import Server.Communication.Results.BaseResult;
import Server.Server;
import Server.User.AuthenticationProfile;

public class UpdateMoneyCommand extends BaseCommand {
    private int money;

    protected boolean needsAuthentication() {
        return true;
    }

    public UpdateMoneyCommand(AuthenticationProfile authenticationProfile, int money) {
        super(authenticationProfile);
        this.money = money;
    }

    protected BaseResult run() {
        Server.getInstance().updateMoney(this.getAuthenticationProfile().getUsername(), money);
        return BaseResult.getOK();
    }
}
