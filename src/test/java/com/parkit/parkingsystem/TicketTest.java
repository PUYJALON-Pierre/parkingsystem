package com.parkit.parkingsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.parkit.parkingsystem.model.Ticket;

public class TicketTest {

  Ticket ticket;

  @BeforeEach
  public void setup() {
    ticket = new Ticket();
  }

  @Test
  public void setAndGetId() {
    ticket.setId(1);

    assertEquals(1, ticket.getId());

  }

}
