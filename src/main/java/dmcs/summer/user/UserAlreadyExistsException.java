package dmcs.summer.user;

public class UserAlreadyExistsException extends RuntimeException {
    public static final String USER_ALREADY_EXISTS_CODE = "Użytkownik już istnieje.";

    public UserAlreadyExistsException() {
        super(USER_ALREADY_EXISTS_CODE);
    }
}
