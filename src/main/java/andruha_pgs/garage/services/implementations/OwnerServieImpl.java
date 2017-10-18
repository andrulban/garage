package andruha_pgs.garage.services.implementations;

import andruha_pgs.garage.exceptions.exceptions.EmptyResponseFromDBException;
import andruha_pgs.garage.exceptions.exceptions.EntityEditOrDeleteIdExistenceInDBException;
import andruha_pgs.garage.exceptions.exceptions.EntityInsertIdGeneratedValueException;
import andruha_pgs.garage.models.entities.Owner;
import andruha_pgs.garage.repositories.OwnerRepository;
import andruha_pgs.garage.services.interfaces.OwnerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OwnerServieImpl implements OwnerService {
    private OwnerRepository ownerRepository;
    private static final Logger LOGGER = LogManager.getLogger(OwnerServieImpl.class);

    @Autowired
    public void setOwnerRepository(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public List<Owner> getAll(Pageable pageable) throws EmptyResponseFromDBException {
        LOGGER.debug("Calls repository to findAll");
        List<Owner> owners = ownerRepository.findAll(pageable).getContent();
        if (!owners.isEmpty()) {
            return owners;
        } else {
            throw new EmptyResponseFromDBException();
        }
    }

    @Override
    public List<Owner> getOwnersByFullName(String fullName, Pageable pageable) throws EmptyResponseFromDBException {
        LOGGER.debug("Calls repository to getOwnersByFullNameIsContaining, fullName {}", fullName);
        List<Owner> owners = ownerRepository.getOwnersByFullNameIsContaining(fullName, pageable).getContent();
        if (!owners.isEmpty()) {
            return owners;
        } else {
            throw new EmptyResponseFromDBException(Collections.singleton("fullName:"+fullName));
        }
    }

    @Override
    public Owner getOwnerById(Long id) throws EmptyResponseFromDBException {
        LOGGER.debug("Calls repository to getOne, id {}", id);
        Owner owner = ownerRepository.getOne(id);
        if (owner!=null) {
            return owner;
        } else {
            throw new EmptyResponseFromDBException(Collections.singleton("id:"+id));
        }
    }

    @Override
    public void addOwner(Owner owner) throws EntityInsertIdGeneratedValueException {
        if (owner.getId()==null||owner.getId()==0) {
            LOGGER.debug("Calls repository to insert");
            ownerRepository.save(owner);
            LOGGER.debug("{} with id {} has been saved", owner.getClass(), owner.getId());
        }
        else {
            throw new EntityInsertIdGeneratedValueException();
        }
    }

    @Override
    public void editOwner(Owner owner) throws EntityEditOrDeleteIdExistenceInDBException {
        if (isOwnerIdInDB(owner.getId())) {
            LOGGER.debug("Calls repository to update");
            ownerRepository.saveAndFlush(owner);
            LOGGER.debug("{} with id {} has been updated", owner.getClass(), owner.getId());
        }
        else {
            throw new EntityEditOrDeleteIdExistenceInDBException(owner.getId().toString());
        }
    }

    @Override
    public void deleteOwner(Long id) throws EntityEditOrDeleteIdExistenceInDBException {
        if (isOwnerIdInDB(id)) {
            LOGGER.debug("Calls repository to delete");
            ownerRepository.delete(id);
            LOGGER.debug("{} with id {} has been deleted", Owner.class, id);
        }
        else {
            throw new EntityEditOrDeleteIdExistenceInDBException(id.toString());
        }
    }

    private boolean isOwnerIdInDB(Long id) {
        return ownerRepository.findOne(id) != null;
    }
}
