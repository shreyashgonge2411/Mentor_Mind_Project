public class Appointment {
      private int id; // Auto-Increment Primary Key
      private String patientName;
      private String patientPhone;
      private int patientAge;
      private String patientGender;
      private String bloodGroup;
      private String appointmentDate;
      private String appointmentTime;
      private String addr;
      private String patientEmail;
      private String doctorName;
      private String doctorPhone;
      private String testName;

      // Default constructor
      public Appointment() {
      }

      // Parameterized constructor
      public Appointment(String patientName, String patientPhone, int patientAge, String patientGender,
                  String bloodGroup,
                  String appointmentDate, String appointmentTime, String addr, String patientEmail,
                  String doctorName, String doctorPhone,String testName) {
            this.patientName = patientName;
            this.patientPhone = patientPhone;
            this.patientAge = patientAge;
            this.patientGender = patientGender;
            this.bloodGroup = bloodGroup;
            this.appointmentDate = appointmentDate;
            this.appointmentTime = appointmentTime;
            this.addr = addr;
            this.patientEmail = patientEmail;
            this.doctorName = doctorName;
            this.doctorPhone = doctorPhone;
            this.testName = testName;
      }

      // Getter and Setter methods
      public int getId() {
            return id;
      }

      public void setId(int id) {
            this.id = id;
      }

      public String getPatientName() {
            return patientName;
      }

      public void setPatientName(String patientName) {
            this.patientName = patientName;
      }

      public String getPatientPhone() {
            return patientPhone;
      }

      public void setPatientPhone(String patientPhone) {
            this.patientPhone = patientPhone;
      }

      public int getPatientAge() {
            return patientAge;
      }

      public void setPatientAge(int patientAge) {
            this.patientAge = patientAge;
      }

      public String getPatientGender() {
            return patientGender;
      }

      public void setPatientGender(String patientGender) {
            this.patientGender = patientGender;
      }

      public String getBloodGroup() {
            return bloodGroup;
      }

      public void setBloodGroup(String bloodGroup) {
            this.bloodGroup = bloodGroup;
      }

      public String getAppointmentDate() {
            return appointmentDate;
      }

      public void setAppointmentDate(String appointmentDate) {
            this.appointmentDate = appointmentDate;
      }

      public String getAppointmentTime() {
            return appointmentTime;
      }

      public void setAppointmentTime(String appointmentTime) {
            this.appointmentTime = appointmentTime;
      }

      public String getAddr() {
            return addr;
      }

      public void setAddr(String addr) {
            this.addr = addr;
      }

      public String getPatientEmail() {
            return patientEmail;
      }

      public void setPatientEmail(String patientEmail) {
            this.patientEmail = patientEmail;
      }

      public String getDoctorName() {
            return doctorName;
      }

      public void setDoctorName(String doctorName) {
            this.doctorName = doctorName;
      }

      public String getDoctorPhone() {
            return doctorPhone;
      }

      public void setDoctorPhone(String doctorPhone) {
            this.doctorPhone = doctorPhone;
      }

      public String getTestName() {
            return testName;
      }

      public void setTestName(String testName) {
            this.testName = testName;
      }
}
