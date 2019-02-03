package Server.Communication.Commands;

import Server.Communication.Results.GetIpResult;

public class GetIpCommand extends BaseCommand {
    protected GetIpResult run() {
        return new GetIpResult();
    }
}
