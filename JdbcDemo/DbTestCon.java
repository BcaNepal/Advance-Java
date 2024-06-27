import java.sql.*;

public class DbTestCon {
  public static void main(String[] args) throws SQLException, ClassNotFoundException {
    // Load the JDBC driver
    Class.forName("org.mariadb.jdbc.Driver");
    System.out.println("Driver loaded");

    // Try to connect
    Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost/Students", "root", "password");

    System.out.println("It works!");

    connection.close();
  }
}
