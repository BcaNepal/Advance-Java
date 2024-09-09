import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddStudentServlet extends HttpServlet {
    private static final String URL = "jdbc:mariadb://localhost:3306/Login";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentName = req.getParameter("studentName");
        String studentEmail = req.getParameter("studentEmail");
        String studentBirthDate = req.getParameter("studentBirthDate");
        String studentAddress = req.getParameter("studentAddress");
        String studentPhone = req.getParameter("studentPhone");
        String studentGender = req.getParameter("studentGender");
        String studentClass = req.getParameter("studentClass");

        if (addStudent(studentName, studentEmail, studentBirthDate, studentAddress, studentPhone, studentGender, studentClass)) {
            resp.sendRedirect("studentdashboard.html");
        } else {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to add student. Please try again later.");
        }
    }

    private boolean addStudent(String studentName, String studentEmail, String studentBirthDate, String studentAddress, String studentPhone, String studentGender, String studentClass) {
        try (
    Class.forName("org.mariadb.jdbc.Driver");
    Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
      // QUERY
            String sql = "INSERT INTO Students (name, email, birth_date, address, phone, gender, class) VALUES (?, ?, ?, ?, ?, ?, ?)";
      //
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, studentName);
                stmt.setString(2, studentEmail);
                stmt.setString(3, studentBirthDate); // Ensure studentBirthDate is in YYYY-MM-DD format
                stmt.setString(4, studentAddress);
                stmt.setString(5, studentPhone);
                stmt.setString(6, studentGender);
                stmt.setString(7, studentClass);
                int rows = stmt.executeUpdate();
                return rows > 0;
            }
        } catch (SQLException e) {
            // Log exception for debugging purposes
            getServletContext().log("SQL Exception occurred", e);
            // Print stack trace (optional)
            e.printStackTrace();
            return false;
        }
    }
}
