import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        AppointmentDAO appointmentDAO = new AppointmentDAO();
        TestDetailsDAO testDetailsDAO = new TestDetailsDAO(); // DAO for handling test details
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline after nextInt()

            if (choice == 1) {
                // Registration
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                System.out.print("Enter email: ");
                String email = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                System.out.print("Enter phone: ");
                String phone = scanner.nextLine();

                User user = new User(name, email, password, phone);

                if (userDAO.registerUser(user)) {
                    System.out.println("Registration successful!");
                } else {
                    System.out.println("Registration failed.");
                }

            } else if (choice == 2) {
                // Login Menu
                System.out.println("Choose login type:");
                System.out.println("1. User Login");
                System.out.println("2. Admin Login");
                int loginChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (loginChoice == 1) {
                    // User Login
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    User user = userDAO.loginUser(email, password);
                    if (user != null) {
                        System.out.println("Login successful! Welcome, " + user.getName());

                        // After login, show menu to either book an appointment or update details
                        while (true) {
                            System.out.println("\nWhat would you like to do next?");
                            System.out.println("1. Update Personal Details");
                            System.out.println("2. Book Appointment");
                            System.out.println("3. Cancel Appointment");
                            System.out.println("4. Update Appointment");
                            System.out.println("5. Logout");
                            int postLoginChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline after nextInt()

                            if (postLoginChoice == 1) {
                                // Update Personal Details
                                while (true) {
                                    System.out.println("\nWhich detail would you like to update?");
                                    System.out.println("1. Update Name");
                                    System.out.println("2. Update Email");
                                    System.out.println("3. Update Password");
                                    System.out.println("4. Update Phone");
                                    System.out.println("5. Back to Main Menu");
                                    int updateChoice = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline

                                    if (updateChoice == 1) {
                                        System.out.print("Enter new name: ");
                                        String newName = scanner.nextLine();
                                        user.setName(newName);
                                        userDAO.updateUser(user);
                                        System.out.println("Name updated successfully!");
                                    } else if (updateChoice == 2) {
                                        System.out.print("Enter new email: ");
                                        String newEmail = scanner.nextLine();
                                        user.setEmail(newEmail);
                                        userDAO.updateUser(user);
                                        System.out.println("Email updated successfully!");
                                    } else if (updateChoice == 3) {
                                        System.out.print("Enter new password: ");
                                        String newPassword = scanner.nextLine();
                                        user.setPassword(newPassword);
                                        userDAO.updateUser(user);
                                        System.out.println("Password updated successfully!");
                                    } else if (updateChoice == 4) {
                                        System.out.print("Enter new phone: ");
                                        String newPhone = scanner.nextLine();
                                        user.setPhone(newPhone);
                                        userDAO.updateUser(user);
                                        System.out.println("Phone updated successfully!");
                                    } else if (updateChoice == 5) {
                                        break;
                                    } else {
                                        System.out.println("Invalid choice. Please select a valid option.");
                                    }
                                }

                            } else if (postLoginChoice == 2) {
                                // Book Appointment and Test Details
                                System.out.println("Please fill your appointment details:");

                                System.out.print("Enter patient name: ");
                                String patientName = scanner.nextLine();
                                System.out.print("Enter patient phone: ");
                                String patientPhone = scanner.nextLine();
                                System.out.print("Enter patient age: ");
                                int patientAge = scanner.nextInt();
                                scanner.nextLine(); // consume newline
                                System.out.print("Enter patient gender: ");
                                String patientGender = scanner.nextLine();
                                System.out.print("Enter blood group: ");
                                String bloodGroup = scanner.nextLine();
                                System.out.print("Enter appointment date (YYYY-MM-DD): ");
                                String appointmentDate = scanner.nextLine();
                                System.out.print("Enter appointment time (HH:MM): ");
                                String appointmentTime = scanner.nextLine();
                                System.out.print("Enter address: ");
                                String addr = scanner.nextLine();
                                System.out.print("Enter patient email: ");
                                String patientEmail = scanner.nextLine();
                                System.out.print("Enter doctor name: ");
                                String doctorName = scanner.nextLine();
                                System.out.print("Enter doctor phone: ");
                                String doctorPhone = scanner.nextLine();
                                System.out.print("Enter Test Name: ");
                                String testname = scanner.nextLine();

                                // Create an Appointment object
                                Appointment appointment = new Appointment(
                                        patientName, patientPhone, patientAge, patientGender, bloodGroup,
                                        appointmentDate, appointmentTime, addr, patientEmail,
                                        doctorName, doctorPhone, testname);

                                // Insert the appointment into the database
                                if (appointmentDAO.createAppointment(appointment)) {
                                    System.out.println("Appointment successfully booked!");

                                    // Fetch the last inserted appointment ID
                                    int appointmentId = appointmentDAO.getLastInsertedAppointmentId();

                                    // Now collect test details and store them in the testdetails table
                                    System.out.print("Enter test name: ");
                                    String testName = scanner.nextLine();
                                    System.out.print("Enter test price: ");
                                    double testPrice = scanner.nextDouble();
                                    scanner.nextLine(); // Consume newline
                                    System.out.print("Enter test date (YYYY-MM-DD): ");
                                    String testDate = scanner.nextLine();

                                    // Create a TestDetails object
                                    TestDetails testDetails = new TestDetails(testName, testPrice, testDate,
                                            appointmentId);

                                    // Insert the test details into the database
                                    if (testDetailsDAO.createTestDetails(testDetails)) {
                                        System.out.println("Test details successfully saved!");
                                    } else {
                                        System.out.println("Failed to save test details.");
                                    }

                                } else {
                                    System.out.println("Failed to book appointment.");
                                }

                            } else if (postLoginChoice == 3) {
                                System.out.print("Enter appointment ID to cancel: ");
                                int appointmentId = scanner.nextInt();
                                scanner.nextLine(); // Consume newline

                                if (appointmentDAO.cancelAppointment(appointmentId)) {
                                    System.out.println("Appointment successfully canceled!");
                                } else {
                                    System.out
                                            .println("Failed to cancel appointment. Please check the appointment ID.");
                                }
                            }else if (postLoginChoice == 4) {
                                // Update Appointment logic
                                System.out.print("Enter appointment ID to update: ");
                                int appointmentId = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                        
                                // Fetch the appointment details to update
                                Appointment appointment = appointmentDAO.getAppointmentById(appointmentId);
                                if (appointment != null) {
                                    System.out.println("Update Appointment Details:");
                                    System.out.print("Enter new appointment date (YYYY-MM-DD): ");
                                    String newDate = scanner.nextLine();
                                    System.out.print("Enter new appointment time (HH:MM): ");
                                    String newTime = scanner.nextLine();
                        
                                    appointment.setAppointmentDate(newDate);
                                    appointment.setAppointmentTime(newTime);
                        
                                    if (appointmentDAO.updateAppointment(appointment)) {
                                        System.out.println("Appointment updated successfully!");
                                    } else {
                                        System.out.println("Failed to update appointment.");
                                    }
                                } else {
                                    System.out.println("Appointment not found. Please check the appointment ID.");
                                }
                            } else if (postLoginChoice == 5) {
                                System.out.println("Logging out...");
                                break; // Exit post-login menu and go back to main menu
                            } else {
                                System.out.println("Invalid choice. Please select a valid option.");
                            }
                        }

                    } else {
                        System.out.println("Invalid email or password.");
                    }

                } else if (loginChoice == 2) {
                    // Admin Login (Add your admin login logic here)
                    AdminDAO adminDAO = new AdminDAO();
                    System.out.print("Enter admin email: ");
                    String adminEmail = scanner.nextLine();
                    System.out.print("Enter admin password: ");
                    String adminPassword = scanner.nextLine();

                    if (adminDAO.adminLogin(adminEmail, adminPassword)) {
                        System.out.println("Admin login successful!");

                        // Admin operations menu
                        while (true) {
                            System.out.println("\nAdmin Menu:");
                            System.out.println("1. Get User Details");
                            System.out.println("2. Appointment Details");
                            System.out.println("3. Logout");
                            int adminChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                        
                            if (adminChoice == 1) {
                                // Get User Details
                                List<User> users = adminDAO.getUserDetails();
                                if (users.isEmpty()) {
                                    System.out.println("No users found.");
                                } else {
                                    System.out.println("User Details:");
                                    for (User user : users) {
                                        System.out.println("Name: " + user.getName() +
                                                           ", Email: " + user.getEmail() +
                                                           ", Phone: " + user.getPhone());
                                    }
                                }
                        
                            } else if (adminChoice == 2) {
                                // Get Appointment Details
                                List<Appointment> appointments = appointmentDAO.getAllAppointments();
                                if (appointments.isEmpty()) {
                                    System.out.println("No appointments found.");
                                } else {
                                    System.out.println("Appointment Details:");
                                    for (Appointment appointment : appointments) {
                                        System.out.println("Appointment ID: " + appointment.getId() +
                                                           ", Patient Name: " + appointment.getPatientName() +
                                                           ", Appointment Date: " + appointment.getAppointmentDate() +
                                                           ", Appointment Time: " + appointment.getAppointmentTime() +
                                                           ", Doctor Name: " + appointment.getDoctorName());
                                    }
                                }
                        
                            } else if (adminChoice == 3) {
                                System.out.println("Logging out...");
                                break;
                            } else {
                                System.out.println("Invalid choice. Please select a valid option.");
                            }
                        }
                        
                        }
                    } else {
                        System.out.println("Invalid admin email or password.");
                    }

                } else {
                    System.out.println("Invalid choice. Please select either 1 or 2.");
                }
            }
        }
    }
