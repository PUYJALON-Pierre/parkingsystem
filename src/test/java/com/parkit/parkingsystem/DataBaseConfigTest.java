package com.parkit.parkingsystem;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.jupiter.api.Test;

import com.parkit.parkingsystem.config.DataBaseConfig;

public class DataBaseConfigTest {

  @Test
  public void parkingSQLRequestTest() {
    DataBaseConfig dataBaseConfig = new DataBaseConfig();

    Connection con = null;
    try {
      con = dataBaseConfig.getConnection();

      PreparedStatement ps = con.prepareStatement("select * from parking;");
      ps.execute();
      ResultSet rs = ps.executeQuery();

      dataBaseConfig.closePreparedStatement(ps);
      dataBaseConfig.closeResultSet(rs);

    } catch (RuntimeException ex) {
      assertTrue(false);

    } catch (Exception ex) {
      assertTrue(false);

    } finally {
      dataBaseConfig.closeConnection(con);
    }
  }

  @Test
  public void ticketSQLRequestTest() {
    DataBaseConfig dataBaseConfig = new DataBaseConfig();

    Connection con = null;
    try {
      con = dataBaseConfig.getConnection();

      PreparedStatement ps = con.prepareStatement("select * from ticket;");
      ps.execute();
      ResultSet rs = ps.executeQuery();

      dataBaseConfig.closePreparedStatement(ps);
      dataBaseConfig.closeResultSet(rs);

    } catch (RuntimeException ex) {
      assertTrue(false);

    } catch (Exception ex) {
      assertTrue(false);

    } finally {
      dataBaseConfig.closeConnection(con);
    }
  }
}