import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {
    private Connection connection;

    public AppointmentDAO() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/anp_7699", "root", "Shreyash@2411");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve the last inserted appointment ID
    public int getLastInsertedAppointmentId() {
        int lastAppointmentId = -1;
        String query = "SELECT aid FROM appointment ORDER BY aid DESC LIMIT 1"; // Retrieves the last inserted appointment ID

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                lastAppointmentId = rs.getInt("aid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastAppointmentId;
    }

    // Method to create a new appointment
    public boolean createAppointment(Appointment appointment) {
        try {
            String query = "INSERT INTO appointment (patientName, patientPhone, patientAge, patientGender, bloodGroup, "
                    + "appointmentDate, appointmentTime, addr, patientEmail, doctorName, doctorPhone, testName) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, appointment.getPatientName());
            pstmt.setString(2, appointment.getPatientPhone());
            pstmt.setInt(3, appointment.getPatientAge());
            pstmt.setString(4, appointment.getPatientGender());
            pstmt.setString(5, appointment.getBloodGroup());
            pstmt.setString(6, appointment.getAppointmentDate());
            pstmt.setString(7, appointment.getAppointmentTime());
            pstmt.setString(8, appointment.getAddr());
            pstmt.setString(9, appointment.getPatientEmail());
            pstmt.setString(10, appointment.getDoctorName());
            pstmt.setString(11, appointment.getDoctorPhone());
            pstmt.setString(12, appointment.getTestName());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to cancel an appointment by ID
    public boolean cancelAppointment(int appointmentId) {
      String deleteTestDetailsQuery = "DELETE FROM testdetails WHERE appointmentId = ?";
      String deleteAppointmentQuery = "DELETE FROM appointment WHERE aid = ?";
      
      try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/anp_7699", "root", "Shreyash@2411");
           PreparedStatement deleteTestDetailsStmt = conn.prepareStatement(deleteTestDetailsQuery);
           PreparedStatement deleteAppointmentStmt = conn.prepareStatement(deleteAppointmentQuery)) {
  
          // Delete test details related to the appointment first
          deleteTestDetailsStmt.setInt(1, appointmentId);
          deleteTestDetailsStmt.executeUpdate();
  
          // Then delete the appointment itself
          deleteAppointmentStmt.setInt(1, appointmentId);
          int rowsAffected = deleteAppointmentStmt.executeUpdate();
          
          return rowsAffected > 0;
      } catch (SQLException e) {
          e.printStackTrace();
          return false;
      }
  }
  

    // Method to update an appointment
    public boolean updateAppointment(Appointment appointment) {
        String query = "UPDATE appointment SET appointmentDate = ?, appointmentTime = ? WHERE aid = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, appointment.getAppointmentDate());
            pstmt.setString(2, appointment.getAppointmentTime());
            pstmt.setInt(3, appointment.getId()); // Assuming you have an appointment ID in the Appointment object
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Returns true if appointment is successfully updated
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to fetch an appointment by ID
    public Appointment getAppointmentById(int appointmentId) {
        String query = "SELECT * FROM appointment WHERE aid = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, appointmentId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Appointment appointment = new Appointment(
                        rs.getString("patientName"),
                        rs.getString("patientPhone"),
                        rs.getInt("patientAge"),
                        rs.getString("patientGender"),
                        rs.getString("bloodGroup"),
                        rs.getString("appointmentDate"),
                        rs.getString("appointmentTime"),
                        rs.getString("addr"),
                        rs.getString("patientEmail"),
                        rs.getString("doctorName"),
                        rs.getString("doctorPhone"),
                        rs.getString("testName")
                );
                appointment.setId(rs.getInt("aid")); // Setting appointment ID
                return appointment;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if no appointment is found
    }

    // Method to retrieve all appointments
    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM appointment"; // Adjust table name if needed

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Appointment appointment = new Appointment(
                        rs.getString("patientName"),
                        rs.getString("patientPhone"),
                        rs.getInt("patientAge"),
                        rs.getString("patientGender"),
                        rs.getString("bloodGroup"),
                        rs.getString("appointmentDate"),
                        rs.getString("appointmentTime"),
                        rs.getString("addr"),
                        rs.getString("patientEmail"),
                        rs.getString("doctorName"),
                        rs.getString("doctorPhone"),
                        rs.getString("testName")
                );
                appointment.setId(rs.getInt("aid")); // Setting appointment ID
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }
}
