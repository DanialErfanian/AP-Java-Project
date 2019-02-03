package Server.Exceptions;

public class StatusCodeException extends Exception {
    private int code;

    public StatusCodeException(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
