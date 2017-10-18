package andruha_pgs.garage.exceptions.exceptions;

public class EntityInsertIdExistenceInDBException extends RuntimeException {

    private static final String MESSAGE = "There is entity in DB with this id:";

    public EntityInsertIdExistenceInDBException(String message) {
        super(MESSAGE+message);
    }
}
