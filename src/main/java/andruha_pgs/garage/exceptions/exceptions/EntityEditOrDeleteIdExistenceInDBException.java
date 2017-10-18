package andruha_pgs.garage.exceptions.exceptions;

public class EntityEditOrDeleteIdExistenceInDBException extends RuntimeException {
    private static final String MESSAGE = "There is no entity in DB with this id:";

    public EntityEditOrDeleteIdExistenceInDBException(String message) {
        super(MESSAGE+message);
    }
}
