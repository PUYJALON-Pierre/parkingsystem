package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {

  public void calculateFare(Ticket ticket) {
    if ((ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime()))) {
      throw new IllegalArgumentException(
          "Out time provided is incorrect:" + ticket.getOutTime().toString());
    }

    double inHour = ticket.getInTime().getTime();
    double outHour = ticket.getOutTime().getTime();

    double duration = (outHour - inHour);
    duration = (duration / (1000 * 60 * 60)); // Convert time from milliseconds into hour

    /* UserStory #1 free for 30 min */
    if (duration <= 0.5) {
      duration = 0;
    }
    
    switch (ticket.getParkingSpot().getParkingType()) {
      case CAR: {
      //Add if statement for recurringUser
        if (ticket.getRecurringUser() == true) {
          ticket.setPrice(0.95 * (duration * Fare.CAR_RATE_PER_HOUR));
        } else {
          ticket.setPrice(duration * Fare.CAR_RATE_PER_HOUR); 
        }
        break;
      }
      case BIKE: {
        if (ticket.getRecurringUser() == true) {
          ticket.setPrice(0.95 * (duration * Fare.BIKE_RATE_PER_HOUR));
        } else {
          ticket.setPrice(duration * Fare.BIKE_RATE_PER_HOUR);
        }
        break;
      }
      default:
        throw new IllegalArgumentException("Unkown Parking Type");
    }
  }
}