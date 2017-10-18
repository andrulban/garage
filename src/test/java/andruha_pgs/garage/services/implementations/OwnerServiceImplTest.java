package andruha_pgs.garage.services.implementations;

import andruha_pgs.garage.exceptions.exceptions.EmptyResponseFromDBException;
import andruha_pgs.garage.exceptions.exceptions.EntityEditOrDeleteIdExistenceInDBException;
import andruha_pgs.garage.exceptions.exceptions.EntityInsertIdGeneratedValueException;
import andruha_pgs.garage.models.entities.Owner;
import andruha_pgs.garage.repositories.OwnerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OwnerServiceImplTest {

    @Mock
    private OwnerRepository ownerRepository;
    private PageRequest pageRequest;
    private OwnerServiceImpl ownerService;
    private Owner owner;

    @Before
    public void setUp() {
        ownerService = new OwnerServiceImpl();
        ownerService.setOwnerRepository(ownerRepository);
        pageRequest = new PageRequest(0,20);

        owner = new Owner();
        owner.setId(1L);
        owner.setFullName("Andrii Tytarchuk");
    }

    @Test(expected = EmptyResponseFromDBException.class)
    public void emptyResponseFromEmptyDB() {
        when(ownerRepository.findAll(pageRequest)).thenReturn(new PageImpl<Owner>(Collections.EMPTY_LIST));
        ownerService.getAll(pageRequest);
    }


    @Test(expected = EmptyResponseFromDBException.class)
    public void wrongFullNameEmptyResponse() {
        when(ownerRepository.getOwnersByFullNameIsContaining(owner.getFullName() ,pageRequest)).thenReturn(new PageImpl<Owner>(Collections.EMPTY_LIST));
        ownerService.getOwnersByFullName(owner.getFullName(), pageRequest);
    }

    @Test(expected = EmptyResponseFromDBException.class)
    public void wrongIdEmptyResponse() {
        when(ownerRepository.findOne(owner.getId())).thenReturn(null);
        ownerService.getOwnerById(owner.getId());
    }

    @Test(expected = EntityInsertIdGeneratedValueException.class)
    public void insertEntityInsertIdGeneratedValueException() {
        ownerService.addOwner(owner);
    }

    @Test(expected = EntityEditOrDeleteIdExistenceInDBException.class)
    public void editEntityEditOrDeleteIdExistenceInDBException() {
        when(ownerRepository.findOne(owner.getId())).thenReturn(null);
        ownerService.editOwner(owner);
    }

    @Test(expected = EntityEditOrDeleteIdExistenceInDBException.class)
    public void deleteEntityEditOrDeleteIdExistenceInDBException() {
        when(ownerRepository.findOne(owner.getId())).thenReturn(null);
        ownerService.deleteOwner(owner.getId());
    }

}
