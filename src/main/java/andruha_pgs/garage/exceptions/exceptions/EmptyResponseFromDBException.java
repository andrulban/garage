package andruha_pgs.garage.exceptions.exceptions;

import java.util.LinkedHashSet;
import java.util.Set;

public class EmptyResponseFromDBException extends RuntimeException {
    private Set<String > search_params_set = new LinkedHashSet<>();
    private static final String MESSAGE = "Empty response from DB with such search parameters:";

    public EmptyResponseFromDBException() {
        super();
    }

    public EmptyResponseFromDBException(Set<String> search_params_set) {
        super();
        this.search_params_set.add(MESSAGE);
        this.search_params_set.addAll(search_params_set);
    }

    public Set<String> getSearch_params_set() {
        return search_params_set;
    }

    public void setSearch_params_set(Set<String> search_params_set) {
        this.search_params_set.add(MESSAGE);
        this.search_params_set.addAll(search_params_set);
    }
}
