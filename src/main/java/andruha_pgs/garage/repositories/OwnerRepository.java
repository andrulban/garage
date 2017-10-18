package andruha_pgs.garage.repositories;

import andruha_pgs.garage.models.entities.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Page<Owner> findAll(Pageable pageable);
    Page<Owner> getOwnersByFullNameIsContaining(String fullName, Pageable pageable);
}
