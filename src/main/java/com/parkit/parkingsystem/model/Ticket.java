package com.parkit.parkingsystem.model;

import java.util.Date;

public class Ticket {
  private int id;
  private ParkingSpot parkingSpot;
  private String vehicleRegNumber;
  private double price;
  private Date inTime;
  private Date outTime;

  //Create variable for identifying recurring users
  private boolean recurringUser = false;

  public boolean getRecurringUser() {
    return recurringUser;
  }

  public void setRecurringUser(boolean recurringUser) {
    this.recurringUser = recurringUser;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public ParkingSpot getParkingSpot() {
    if (parkingSpot != null) {

      return new ParkingSpot(parkingSpot.getId(), parkingSpot.getParkingType(),
          parkingSpot.isAvailable());
    } else {
      return null;
    }
  }

  public void setParkingSpot(ParkingSpot parkingSpot) {
    if (parkingSpot != null) {

      this.parkingSpot = new ParkingSpot(parkingSpot.getId(), parkingSpot.getParkingType(),
          parkingSpot.isAvailable());

    } else {
      this.parkingSpot = null;
    }
  }

  public String getVehicleRegNumber() {
    return vehicleRegNumber;
  }

  public void setVehicleRegNumber(String vehicleRegNumber) {
    this.vehicleRegNumber = vehicleRegNumber;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public Date getInTime() {
    if (inTime != null) {

      return new Date(inTime.getTime());
    } else {
      return null;
    }
  }

  public void setInTime(Date inTime) {
    if (inTime != null) {

      this.inTime = new Date(inTime.getTime());
    } else {
      this.inTime = null;

    }
  }

  public Date getOutTime() {
    if (outTime != null) {

      return new Date(outTime.getTime());
    } else {
      return null;
    }
  }

  public void setOutTime(Date outTime) {
    if (outTime != null) {

      this.outTime = new Date(outTime.getTime());
    } else {
      this.outTime = null;

    }
  }
}
