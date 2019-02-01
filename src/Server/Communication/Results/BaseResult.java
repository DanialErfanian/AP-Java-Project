package Server.Communication.Results;

public class BaseResult {
    private static BaseResult OK = new BaseResult(200);
    private static BaseResult NOK = new BaseResult(400);

    private int statusCode;

    public BaseResult() {
    }

    public BaseResult(int statusCode) {
        this.statusCode = statusCode;
    }

    public static BaseResult getOK() {
        return OK;
    }

    public static BaseResult getNOK() {
        return NOK;
    }
}
