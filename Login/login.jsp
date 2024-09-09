<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection, java.sql.PreparedStatement, java.sql.ResultSet, java.sql.DriverManager, java.sql.SQLException" %>

<%
// Database connection details
String url = "jdbc:mariadb://localhost:3306/Login";
String username = "root";
String password = "root";

// Variables from form inputs
String enteredUsername = request.getParameter("username");
String enteredPassword = request.getParameter("password");

Connection con = null;
PreparedStatement ps = null;
ResultSet rs = null;

try {
    // Load the MariaDB driver
    Class.forName("org.mariadb.jdbc.Driver");
    // Establish connection
    con = DriverManager.getConnection(url, username, password);
    // SQL query to verify login
    String sql = "SELECT * FROM User WHERE username = ? AND password = ?";
    ps = con.prepareStatement(sql);
    ps.setString(1, enteredUsername);
    ps.setString(2, enteredPassword);
    // Execute the query
    rs = ps.executeQuery();
    if (rs.next()) {
        // Valid credentials: Redirect to success page or set session attributes
        session.setAttribute("username", enteredUsername);
        response.sendRedirect("studentdashboard.html");
    } else {
        // Invalid credentials: Redirect back to login page with error message
        response.sendRedirect("login.jsp?error=invalid");
    }
} catch (ClassNotFoundException | SQLException e) {
    out.println("Error: " + e.getMessage());
} finally {
    // Close resources
    try {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (con != null) {
            con.close();
        }
    } catch (SQLException e) {
        out.println("Error closing database connection: " + e.getMessage());
    }
}
%>
