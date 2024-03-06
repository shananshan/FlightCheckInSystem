import java.util.ArrayList;
import java.util.List;

public class AggregateException extends Exception {
    private List<Exception> exceptions = new ArrayList<>();

    public AggregateException(String message) {
        super(message);
    }

    public void addException(Exception exception) {
        exceptions.add(exception);
    }

    public List<Exception> getExceptions() {
        return exceptions;
    }

    public boolean hasExceptions() {
        return !exceptions.isEmpty();
    }
}
