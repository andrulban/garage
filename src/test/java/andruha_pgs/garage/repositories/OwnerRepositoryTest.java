package andruha_pgs.garage.repositories;

import andruha_pgs.garage.models.entities.Owner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
@SpringBootTest
public class OwnerRepositoryTest {
    @Autowired
    private OwnerRepository ownerRepository;
    private Owner owner;

    @Before
    public void setUp() throws Exception {
        owner = new Owner();
        owner.setFullName("Owner Full Name");
        owner.setDateOfBirth(new Date());

    }
    @Test
    public void insertOwnerRepositoryTest() {
        Owner dbOwner;
        ownerRepository.save(owner);
        dbOwner=ownerRepository.findOne(owner.getId());
        Assert.assertNotNull(dbOwner);
        Assert.assertEquals(owner,dbOwner);
    }

    @Test
    public void updateOwnerRepositoryTest() {
        List<Owner> dbOwners = ownerRepository.findAll();
        Owner dbOwner = dbOwners.get(0);
        dbOwner.setFullName("Changed Owner Full Name");
        ownerRepository.saveAndFlush(dbOwner);
        Owner dbOwnerCopy = ownerRepository.findOne(dbOwner.getId());
        Assert.assertNotNull(dbOwnerCopy);
        Assert.assertEquals(dbOwner.getFullName(),dbOwnerCopy.getFullName());
    }

    @Test
    public void deleteOwnerRepositoryTest() {
        Owner dbOwner = ownerRepository.findOne(1L);
        ownerRepository.delete(dbOwner);
        Assert.assertNull(ownerRepository.findOne(1L));
    }

    @Test
    public void getOwnerRepositoryTest() {
        Assert.assertEquals(ownerRepository.findOne(1L),ownerRepository.findOne(1L));
    }

}