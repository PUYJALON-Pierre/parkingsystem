package com.parkit.parkingsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.parkit.parkingsystem.model.Ticket;

public class TicketTest {

  Ticket ticket;

  @Test
  public void setAndGetId() {
    ticket = new Ticket();
    ticket.setId(1);

    assertEquals(1, ticket.getId());

  }

}
