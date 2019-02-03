package Server.Exceptions;

import java.io.IOException;

public class BadServerException extends IOException {
    public BadServerException(IOException e) {
        super(e);
    }
}
