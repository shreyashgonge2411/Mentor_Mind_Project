import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestDetailsDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/anp_7699"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "Shreyash@2411";

    // Method to add a new TestDetail
    public boolean createTestDetails(TestDetails testDetails) {
        String query = "INSERT INTO testdetails (testName, price, testdate, appointmentId) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, testDetails.getTestName());
            stmt.setDouble(2, testDetails.getPrice());
            stmt.setString(3, testDetails.getTestDate());
            stmt.setInt(4, testDetails.getAppointmentId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
