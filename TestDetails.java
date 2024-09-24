public class TestDetails {
      private int tid;           
      private String testName;    
      private double price;       
      private String testDate;    
      private int appointmentId;  
  
      // Constructor
      public TestDetails(String testName, double price, String testDate, int appointmentId) {
          this.testName = testName;
          this.price = price;
          this.testDate = testDate;
          this.appointmentId = appointmentId;
      }
  
      // Getters and setters
      public int getTid() {
          return tid;
      }
  
      public void setTid(int tid) {
          this.tid = tid;
      }
  
      public String getTestName() {
          return testName;
      }
  
      public void setTestName(String testName) {
          this.testName = testName;
      }
  
      public double getPrice() {
          return price;
      }
  
      public void setPrice(double price) {
          this.price = price;
      }
  
      public String getTestDate() {
          return testDate;
      }
  
      public void setTestDate(String testDate) {
          this.testDate = testDate;
      }
  
      public int getAppointmentId() {
          return appointmentId;
      }
  
      public void setAppointmentId(int appointmentId) {
          this.appointmentId = appointmentId;
      }
  
  }
  