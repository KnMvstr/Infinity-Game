package InfinityGaming.exception;

import lombok.Getter;

@Getter
public class NotFoundCapEntException extends RuntimeException {

    private final String type;

    private final String field;

    private final Object value;

    public NotFoundCapEntException(String type, String field, Object value, String s) {
        super("Entity not found");
        this.type = type;
        this.field = field;
        this.value = value;
    }
}
