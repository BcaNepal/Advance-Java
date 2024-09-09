import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterServlet extends HttpServlet {
    private static final String URL = "jdbc:mariadb://localhost/Login";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
            if (registerUser(username, password)) {
                resp.sendRedirect("login.html");
            } else {
                resp.setContentType("text/html");
                PrintWriter out = resp.getWriter();
                out.println("<html><body>");
                out.println("<script>");
                out.println("alert('Failed to register user. Please try again');");
                out.println("window.location.href = 'register.html';");
                out.println("</script>");
                out.println("</body></html>");
            }
        } else {
            resp.sendRedirect("register.html"); // Redirect to registration page if username and/or password are empty
        }
    }

    private boolean registerUser(String username, String password) {
        String sql = "INSERT INTO User (username, password) VALUES (?,?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Connection getConnection() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
