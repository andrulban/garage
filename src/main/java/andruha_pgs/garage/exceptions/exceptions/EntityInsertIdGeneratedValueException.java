package andruha_pgs.garage.exceptions.exceptions;

public class EntityInsertIdGeneratedValueException extends RuntimeException {
    private static final String MESSAGE = "Entity should not have id when it is inserted to DB";

    public EntityInsertIdGeneratedValueException() {
        super(MESSAGE);
    }
}
