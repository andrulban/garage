package andruha_pgs.garage.repositories;

import andruha_pgs.garage.models.entities.Owner;
import andruha_pgs.garage.models.entities.Vehicle;
import andruha_pgs.garage.models.enums.BodyType;
import andruha_pgs.garage.models.enums.Color;
import andruha_pgs.garage.models.enums.FuelType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
@SpringBootTest
public class VehicleRepositoryTest {
    @Autowired
    private VehicleRepository vehicleRepository;
    private Vehicle vehicle;

    @Before
    public void setUp() throws Exception {
        Set<Owner> owners = Collections.singleton(new Owner());

        vehicle = new Vehicle();
        vehicle.setNumberPlate("AA32823");
        vehicle.setWeightKg(1500);
        vehicle.setBrand("Subaru");
        vehicle.setBrandLine("Forester");
        vehicle.setYear(2007);
        vehicle.setFuelType(FuelType.GASOLINE);
        vehicle.setEngineCapacityCm3(2500);
        vehicle.setMileage(140000);
        vehicle.setColor(Color.RED);
        vehicle.setBodyType(BodyType.OFFROAD_4X4);
        vehicle.setOwners(owners);
    }
    @Test
    public void insertVehicleRepositoryTest() {
        Vehicle dbVehicle;
        vehicleRepository.save(vehicle);
        dbVehicle= vehicleRepository.findOne(vehicle.getNumberPlate());
        Assert.assertNotNull(dbVehicle);
        Assert.assertEquals(vehicle,dbVehicle);
    }

    @Test
    public void updateVehicleRepositoryTest() {
        List<Vehicle> dbVehicles = vehicleRepository.findAll();
        Vehicle dbVehicle = dbVehicles.get(0);
        dbVehicle.setBrandLine("Changed Brand Line");
        vehicleRepository.saveAndFlush(dbVehicle);
        Vehicle dbVehicleCopy = vehicleRepository.findOne(dbVehicle.getNumberPlate());
        Assert.assertNotNull(dbVehicleCopy);
        Assert.assertEquals(dbVehicle.getNumberPlate(),dbVehicleCopy.getNumberPlate());
    }

    @Test
    public void deleteVehicleRepositoryTest() {
        Vehicle dbVehicle = vehicleRepository.findOne("RZ32823");
        vehicleRepository.delete(dbVehicle);
        Assert.assertNull(vehicleRepository.findOne("RZ32823"));
    }

    @Test
    public void getVehicleRepositoryTest() {
        Assert.assertEquals(vehicleRepository.findOne("RZ32823"), vehicleRepository.findOne("RZ32823"));
    }
}