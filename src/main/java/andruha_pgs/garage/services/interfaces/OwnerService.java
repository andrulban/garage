package andruha_pgs.garage.services.interfaces;

import andruha_pgs.garage.models.entities.Owner;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OwnerService {
    List<Owner> getAll(Pageable pageable);
    List<Owner> getOwnersByFullName(String fullName, Pageable pageable);
    Owner getOwnerById(Long id);
    void addOwner(Owner owner);
    void editOwner(Owner owner);
    void deleteOwner(Long id);

}
