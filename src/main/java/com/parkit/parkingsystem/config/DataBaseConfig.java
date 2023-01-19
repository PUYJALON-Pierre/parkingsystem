package com.parkit.parkingsystem.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataBaseConfig {

  private static final Logger logger = LogManager.getLogger("DataBaseConfig");

  public FileInputStream fileInputStream;

  public Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
    Connection connection = null;
    logger.info("Create DB connection");
    try {
      fileInputStream = new FileInputStream("src/main/resources/log4j2.properties");
      Properties properties = new Properties();
      properties.load(fileInputStream);

      String urlDb = properties.getProperty("urldbProd");
      String user = properties.getProperty("userdb");
      String password = properties.getProperty("userpassword");

      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection(urlDb, user, password);

    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    finally {
      fileInputStream.close();
    }

    return connection;
  }

  public void closeConnection(Connection con) {
    if (con != null) {
      try {
        con.close();
        logger.info("Closing DB connection");
      } catch (SQLException e) {
        logger.error("Error while closing connection", e);
      }
    }
  }

  public void closePreparedStatement(PreparedStatement ps) {
    if (ps != null) {
      try {
        ps.close();
        logger.info("Closing Prepared Statement");
      } catch (SQLException e) {
        logger.error("Error while closing prepared statement", e);
      }
    }
  }

  public void closeResultSet(ResultSet rs) {
    if (rs != null) {
      try {
        rs.close();
        logger.info("Closing Result Set");
      } catch (SQLException e) {
        logger.error("Error while closing result set", e);
      }
    }
  }
}
