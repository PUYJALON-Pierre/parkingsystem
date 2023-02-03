package com.parkit.parkingsystem.integration;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.integration.service.DataBasePrepareService;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.service.ParkingService;
import com.parkit.parkingsystem.util.InputReaderUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class ParkingDataBaseIT {

  private static DataBaseTestConfig dataBaseTestConfig = new DataBaseTestConfig();
  private static ParkingSpotDAO parkingSpotDAO;
  private static TicketDAO ticketDAO;
  private static DataBasePrepareService dataBasePrepareService;

  @Mock
  private static InputReaderUtil inputReaderUtil;

  @BeforeAll
  private static void setUp() throws Exception {
    parkingSpotDAO = new ParkingSpotDAO();
    parkingSpotDAO.dataBaseConfig = dataBaseTestConfig;
    ticketDAO = new TicketDAO();
    ticketDAO.dataBaseConfig = dataBaseTestConfig;
    dataBasePrepareService = new DataBasePrepareService();
  }

  @BeforeEach
  private void setUpPerTest() throws Exception {
    when(inputReaderUtil.readSelection()).thenReturn(1);
    when(inputReaderUtil.readVehicleRegistrationNumber()).thenReturn("ABCDEF");
    dataBasePrepareService.clearDataBaseEntries();
  }

  @AfterAll
  private static void tearDown() {

  }

  @Test
  public void testParkingACar() {
    ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
    parkingService.processIncomingVehicle();
    
    // Check that a ticket is actually saved in DB (getTicket is not null) and
    // Parking table is updated with availability (Check if next available spot is
    // different than 1)
    Ticket ticket = ticketDAO.getTicket("ABCDEF");
    int nextAvailableSpot = parkingSpotDAO.getNextAvailableSlot(ParkingType.CAR);

    assertNotNull(ticket);
    assertNotEquals(1, nextAvailableSpot);

  }

  @Test
  public void testParkingLotExit() {
    testParkingACar();
    ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
   
    // Check that the fare generated and out time are populated correctly in
    // the database
    // Create ticket with info 
    Ticket initialTicket = new Ticket();
    initialTicket.setParkingSpot(new ParkingSpot(1, ParkingType.CAR, false));
    initialTicket.setId(1);
    initialTicket.setVehicleRegNumber("ABCDEF");
    initialTicket.setPrice(0);
    initialTicket.setInTime(new Date(System.currentTimeMillis() / (1000 * 60 * 60)));
    initialTicket.setOutTime(null);

    ticketDAO.saveTicket(initialTicket);
    Ticket ticket = ticketDAO.getTicket("ABCDEF");

    // add 1 hour to get exit time
    Date exitTimeDate = new Date((System.currentTimeMillis() + 1) / (1000 * 60 * 60));
    ticket.setOutTime(exitTimeDate);
    double exitTime = ticket.getOutTime().getTime() / (1000 * 60 * 60);

    //Update ticket
    ticketDAO.updateTicket(ticket);

    // Check that ticket is retrieve while exiting with good informations
    parkingService.processExitingVehicle();

    assertEquals((exitTime * Fare.CAR_RATE_PER_HOUR), ticket.getPrice());
    assertEquals(exitTimeDate, ticket.getOutTime());
    
  }

}