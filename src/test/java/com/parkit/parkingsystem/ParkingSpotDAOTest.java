package com.parkit.parkingsystem;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.integration.service.DataBasePrepareService;
import com.parkit.parkingsystem.model.ParkingSpot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParkingSpotDAOTest {

  private static DataBaseTestConfig dataBaseTestConfig = new DataBaseTestConfig();
  private ParkingSpotDAO parkingSpotDAO;
  private static DataBasePrepareService dataBasePrepareService;

  @BeforeEach
  private void setUpPerTest() throws Exception {
    parkingSpotDAO = new ParkingSpotDAO();
    parkingSpotDAO.dataBaseConfig = dataBaseTestConfig;
    dataBasePrepareService = new DataBasePrepareService();
    dataBasePrepareService.clearDataBaseEntries();
  }

  @Test
  public void getNextAvailableSlotTest() {
   
    assertTrue(parkingSpotDAO.getNextAvailableSlot(ParkingType.CAR) > 0);
    assertTrue(parkingSpotDAO.getNextAvailableSlot(ParkingType.BIKE) > 0);
  }

  @Test
  public void updateParkingTest() {
  
    // ARRANGE: create 2 different parkingspot
    ParkingSpot parkingSpot = new ParkingSpot(4, ParkingType.CAR, false);
    ParkingSpot parkingSpot2 = new ParkingSpot(2, ParkingType.BIKE, false);
   
    // ACT: update parking
    parkingSpotDAO.updateParking(parkingSpot);
    parkingSpotDAO.updateParking(parkingSpot2);
   
    // ASSERT: compare parking
    assertEquals(4, parkingSpot.getId());
    assertEquals(2, parkingSpot2.getId());

    assertEquals(ParkingType.CAR, parkingSpot.getParkingType());
    assertEquals(ParkingType.BIKE, parkingSpot2.getParkingType());

    assertEquals(false, parkingSpot.isAvailable());
    assertEquals(false, parkingSpot2.isAvailable());

    assertNotEquals(parkingSpot2, parkingSpot);
  }

}
