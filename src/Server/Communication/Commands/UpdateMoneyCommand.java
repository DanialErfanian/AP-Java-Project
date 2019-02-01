package Server.Communication.Commands;

import Server.Communication.Results.BaseResult;
import Server.Server;
import Server.User.AuthenticationProfile;

public class UpdateMoneyCommand extends BaseCommand {
    final private boolean needsAuthentication = true;
    private int money;

    public UpdateMoneyCommand(AuthenticationProfile authenticationProfile, int money) {
        super(authenticationProfile);
        this.money = money;
    }

    public BaseResult run() {
        Server.getInstance().updateMoney(this.getAuthenticationProfile().getUsername(), money);
        return BaseResult.getOK();
    }
}
