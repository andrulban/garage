package andruha_pgs.garage.exceptions.exceptions;

import java.util.Map;

public class EntityValidationException extends RuntimeException {
    private Map<String, String> param_message_map;

    public EntityValidationException() {
        super();
    }

    public EntityValidationException(Map<String, String > param_message_map) {
        super();
        this.param_message_map = param_message_map;
    }

    public Map<String, String> getParam_message_map() {
        return param_message_map;
    }

    public void setParam_message_map(Map<String, String> param_message_map) {
        this.param_message_map = param_message_map;
    }
}
