package com.parkit.parkingsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.parkit.parkingsystem.constants.ParkingType;

import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.integration.service.DataBasePrepareService;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;

public class TicketDAOTest {

  private static DataBaseTestConfig dataBaseTestConfig = new DataBaseTestConfig();
  private static TicketDAO ticketDAO;
  private static DataBasePrepareService dataBasePrepareService;

  @BeforeEach
  private void setUpPerTest() throws Exception {
    ticketDAO = new TicketDAO();
    ticketDAO.dataBaseConfig = dataBaseTestConfig;
    dataBasePrepareService = new DataBasePrepareService();
    dataBasePrepareService.clearDataBaseEntries();
  }

  @Test
  public void saveTicketTest() {
    // ARRANGE: create ticket for a car at parking spot 1 with number WXYZ
    Ticket ticketToSave = new Ticket();
    ticketToSave.setParkingSpot(new ParkingSpot(1, ParkingType.CAR, false));
    ticketToSave.setVehicleRegNumber("WXYZ");
    ticketToSave.setPrice(50);
    ticketToSave.setInTime(new Date(System.currentTimeMillis() / (1000 * 60 * 60)));

    // ACT: Save ticket
    boolean saveResult = ticketDAO.saveTicket(ticketToSave);
    Ticket ticketSave = ticketDAO.getTicket("WXYZ");

    // ASSERT that ticketToSave is equal to to ticket retrieve from DB with vehicleRegNumber
    assertFalse(saveResult);
    assertEquals(ticketToSave.getParkingSpot(), ticketSave.getParkingSpot());
    assertEquals(ticketToSave.getVehicleRegNumber(), ticketSave.getVehicleRegNumber());
    assertEquals(ticketToSave.getPrice(), ticketSave.getPrice());
    assertNotNull(ticketSave.getInTime());
  }

  @Test
  public void getTicketCountTest0() {
    // ARRANGE: Unknown plate number from DB
    String VehicleNumber = "LMNOP";
    int occurenceInReal = 0;

    // ACT
    int occurenceInDB = ticketDAO.getTicketCount(VehicleNumber);

    //ASSERT
    assertEquals(occurenceInReal, occurenceInDB);
  }

  @Test
  public void getTicketCountTestMoreThan0() {
    // ARRANGE: Save ticket in DB and save it Two times
    String VehicleNumberKnowFromDB = "HIJKF";

    Ticket ticket = new Ticket();
    ticket.setParkingSpot(new ParkingSpot(1, ParkingType.CAR, false));
    ticket.setVehicleRegNumber(VehicleNumberKnowFromDB);
    ticket.setPrice(0);
    ticket.setInTime(new Date(System.currentTimeMillis() / (1000 * 60 * 60)));

    ticketDAO.saveTicket(ticket);
    ticketDAO.saveTicket(ticket);

    int occurenceInReal = 2;

    // ACT
    int occurenceInDB = ticketDAO.getTicketCount(VehicleNumberKnowFromDB);
    //ASSERT
    assertEquals(occurenceInReal, occurenceInDB);
  }
}
