package andruha_pgs.garage.exceptions.exception_handlers;

import andruha_pgs.garage.exceptions.exceptions.EmptyResponseFromDBException;
import andruha_pgs.garage.exceptions.exceptions.EntityEditOrDeleteIdExistenceInDBException;
import andruha_pgs.garage.exceptions.exceptions.EntityInsertIdExistenceInDBException;
import andruha_pgs.garage.exceptions.exceptions.EntityInsertIdGeneratedValueException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RestControllersExceptionHandlerAdvice {
    private static final Logger LOGGER = LogManager.getLogger(RestControllersExceptionHandlerAdvice.class);

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<ErrorInfo> handleConstraintViolation(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        Set<String> messages = new HashSet<>(constraintViolations.size()+2);
        messages.add("Validation failure.");
        messages.add("Those parameters has erros:");
        messages.addAll(constraintViolations.stream()
                .map(constraintViolation -> String.format("%s has value '%s'. %s", constraintViolation.getPropertyPath(),
                        constraintViolation.getInvalidValue(), constraintViolation.getMessage()))
                .collect(Collectors.toList()));
        LOGGER.error("ConstraintViolationException has been handled. Exception message: {}", messages);
        return new ResponseEntity<>(new ErrorInfo(new Date(), HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), messages), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResponseFromDBException.class)
    public ResponseEntity<ErrorInfo> handleEmptyResponseFromDBException(EmptyResponseFromDBException ex) {
        LOGGER.error("EmptyResponseFromDBException has been handled. Exception message: {}", ex.getSearch_params_set());
        return new ResponseEntity<>(new ErrorInfo(new Date(), HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getSearch_params_set()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityEditOrDeleteIdExistenceInDBException.class)
    ResponseEntity<ErrorInfo> handleEntityEditOrDeleteIdExistenceInDBException(EntityEditOrDeleteIdExistenceInDBException ex) {
        LOGGER.error("EntityIdExistenceInDBException has been handled. Exception message: {}", ex.getMessage());
        return new ResponseEntity<>(new ErrorInfo(new Date(), HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), Collections.singleton(ex.getMessage())), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityInsertIdExistenceInDBException.class)
    ResponseEntity<ErrorInfo> handleEntityInsertIdExistenceInDBException(EntityInsertIdExistenceInDBException ex) {
        LOGGER.error("EntityIdInsertionException has been handled. Exception message: {}", ex.getMessage());
        return new ResponseEntity<>(new ErrorInfo(new Date(), HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), Collections.singleton(ex.getMessage())), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityInsertIdGeneratedValueException.class)
    ResponseEntity<ErrorInfo> handleEntityInsertIdGeneratedValueException(EntityInsertIdGeneratedValueException ex) {
        LOGGER.error("EntityIdInsertionException has been handled. Exception message: {}", ex.getMessage());
        return new ResponseEntity<>(new ErrorInfo(new Date(), HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), Collections.singleton(ex.getMessage())), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(DataIntegrityViolationException.class)
//    ResponseEntity<ErrorInfo> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
//        LOGGER.error("DataIntegrityViolationException has been handled. Exception message: {}", ex.getMostSpecificCause().getLocalizedMessage());
//        return new ResponseEntity<>(new ErrorInfo(new Date(), HttpStatus.BAD_REQUEST.value(),
//                HttpStatus.BAD_REQUEST.getReasonPhrase(), Collections.singleton(ex.getMostSpecificCause().getLocalizedMessage())), HttpStatus.BAD_REQUEST);
//    }


    private class ErrorInfo {

        private Date timestamp;
        private int statusCode;
        private String error;
        private Set<String> messageSet;

        public ErrorInfo() {
        }

        public ErrorInfo(Date timestamp, int statusCode, String error, Set<String> messageSet) {
            this.timestamp = timestamp;
            this.statusCode = statusCode;
            this.error = error;
            this.messageSet = messageSet;
        }

        public Date getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Date timestamp) {
            this.timestamp = timestamp;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public Set<String> getMessageSet() {
            return messageSet;
        }

        public void setMessageSet(Set<String> messageSet) {
            this.messageSet = messageSet;
        }

    }
}

