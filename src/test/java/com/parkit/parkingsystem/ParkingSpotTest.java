package com.parkit.parkingsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.ParkingSpot;

public class ParkingSpotTest {

  ParkingSpot parkingSpot;

  @BeforeEach
  public void setup() {
    parkingSpot = new ParkingSpot(0, ParkingType.CAR, true);
  }

  @Test
  public void setAndGetIdTest() {

    parkingSpot.setId(1);

    assertEquals(1, parkingSpot.getId());
  }

  @Test
  public void setAndGetParkingTypeTest() {

    parkingSpot.setParkingType(ParkingType.BIKE);

    assertEquals(ParkingType.BIKE, parkingSpot.getParkingType());
  }

  @Test
  public void setAndGetAvailable() {

    parkingSpot.setAvailable(true);

    assertEquals(true, parkingSpot.isAvailable());
  }

  @Test
  public void HashCodeTest() {

    assertEquals(0, parkingSpot.hashCode());
  }

}
