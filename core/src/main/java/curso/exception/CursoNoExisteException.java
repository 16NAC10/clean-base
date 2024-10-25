package curso.exception;

public class CursoNoExisteException extends RuntimeException {
    public CursoNoExisteException(String message) {
        super(message);
    }
}
