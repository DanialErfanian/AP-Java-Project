package Server.Exceptions;

public class NonuniqueUsernameException extends StatusCodeException {
    public NonuniqueUsernameException(int code) {
        super(code);
    }
}
