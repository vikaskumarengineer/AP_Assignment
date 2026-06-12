package com.onboarding;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CompleteMenuSystem {

    private static RegistrationService registrationService;
    private static Scanner scanner;
    private static List<UserRegistration> userDatabase;
    private static int registrationCounter = 0;
    private static int failedRegistrations = 0;

    public static void main(String[] args) {

        registrationService = new RegistrationService();
        scanner = new Scanner(System.in);
        userDatabase = new ArrayList<>();

        System.out.println("\n──────────────────────────────────────────────────");
        System.out.println("        USER ONBOARDING VALIDATION MODULE");
        System.out.println("──────────────────────────────────────────────────");

        while (true) {

            displayMainMenu();

            System.out.print("\nEnter your choice : ");
            int choice = getIntInput();

            switch (choice) {

                case 1:
                    registerNewUser();
                    break;

                case 2:
                    viewAllUsers();
                    break;

                case 3:
                    searchUserByEmail();
                    break;

                case 4:
                    viewStatistics();
                    break;

                case 5:
                    System.out.println("\n──────────────────────────────────────────────────");
                    System.out.println("Thank you for using Registration System");
                    System.out.println("Total Registrations : " + registrationCounter);
                    System.out.println("Goodbye!");
                    System.out.println("──────────────────────────────────────────────────");

                    scanner.close();
                    return;

                default:
                    System.out.println("\n⚠ Invalid choice! Please enter between 1 - 5");
            }

            System.out.print("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    private static void displayMainMenu() {

        System.out.println("\n──────────────────────────────────────────────────");
        System.out.println("                    MAIN MENU");
        System.out.println("──────────────────────────────────────────────────");
        System.out.println("1. Register New User");
        System.out.println("2. View All Registered Users");
        System.out.println("3. Search User by Email");
        System.out.println("4. View System Statistics");
        System.out.println("5. Exit");
        System.out.println("──────────────────────────────────────────────────");
    }

    private static void registerNewUser() {

        System.out.println("\n──────────────────────────────────────────────────");
        System.out.println("                REGISTRATION FORM");
        System.out.println("──────────────────────────────────────────────────");

        System.out.print("Enter Email Address : ");
        String email = scanner.nextLine().trim();

        if (email.isEmpty()) {
            System.out.println("\n⚠ Email cannot be empty!");
            failedRegistrations++;
            return;
        }

        // Duplicate email check
        for (UserRegistration user : userDatabase) {

            if (user.getEmail().equalsIgnoreCase(email)) {

                System.out.println("\n⚠ Email already registered!");
                failedRegistrations++;
                return;
            }
        }

        System.out.print("Enter Age           : ");
        int age = getIntInput();

        if (age < 0) {

            System.out.println("\n⚠ Age cannot be negative!");
            failedRegistrations++;
            return;
        }

        System.out.print("Full Name (optional): ");
        String fullName = scanner.nextLine().trim();

        if (fullName.isEmpty()) {
            fullName = "Not Provided";
        }

        System.out.print("Phone Number        : ");
        String phone = scanner.nextLine().trim();

        if (phone.isEmpty()) {
            phone = "Not Provided";
        }

        System.out.println("\nChecking validation rules...");

        try {

            boolean isValid = registrationService.registerUser(email, age);

            if (isValid) {

                registrationCounter++;

                UserRegistration user = new UserRegistration(
                        registrationCounter,
                        email,
                        age,
                        fullName,
                        phone
                );

                userDatabase.add(user);

                System.out.println("\n──────────────────────────────────────────────────");
                System.out.println("          ✔ REGISTRATION SUCCESSFUL");
                System.out.println("──────────────────────────────────────────────────");

                System.out.println("Registration ID : " + user.getId());
                System.out.println("Email           : " + user.getEmail());
                System.out.println("Age             : " + user.getAge());
                System.out.println("Full Name       : " + user.getFullName());
                System.out.println("Phone           : " + user.getPhone());
                System.out.println("Registered On   : " + user.getRegistrationTime());

                System.out.println("──────────────────────────────────────────────────");
                System.out.println("✔ Email format valid");
                System.out.println("✔ Age requirement satisfied");
                System.out.println("✔ Assertion check passed");
                System.out.println("──────────────────────────────────────────────────");
            }

        } catch (InvalidEmailException e) {

            failedRegistrations++;

            System.out.println("\n──────────────────────────────────────────────────");
            System.out.println("           ✖ REGISTRATION FAILED");
            System.out.println("──────────────────────────────────────────────────");

            System.out.println("Exception Type : InvalidEmailException");
            System.out.println("Invalid Email  : " + e.getInvalidEmail());
            System.out.println("Reason         : " + e.getMessage());

            System.out.println("\nValid Email Examples:");
            System.out.println("• user@example.com");
            System.out.println("• name@domain.co.in");
            System.out.println("• student123@gmail.com");

            System.out.println("──────────────────────────────────────────────────");

        } catch (UnderageException e) {

            failedRegistrations++;

            System.out.println("\n──────────────────────────────────────────────────");
            System.out.println("           ✖ REGISTRATION FAILED");
            System.out.println("──────────────────────────────────────────────────");

            System.out.println("Exception Type : UnderageException");
            System.out.println("Your Age       : " + e.getApplicantAge());
            System.out.println("Minimum Age    : " + e.getMinimumAge());
            System.out.println("Message        : " + e.getMessage());

            System.out.println("──────────────────────────────────────────────────");
        }
    }

    private static void viewAllUsers() {

        if (userDatabase.isEmpty()) {

            System.out.println("\n──────────────────────────────────────────────────");
            System.out.println("            NO REGISTERED USERS");
            System.out.println("──────────────────────────────────────────────────");

            return;
        }

        System.out.println("\n──────────────────────────────────────────────────");
        System.out.println("             ALL REGISTERED USERS");
        System.out.println("──────────────────────────────────────────────────");

        System.out.printf("%-4s %-28s %-6s %-20s\n",
                "ID", "EMAIL", "AGE", "NAME");

        System.out.println("──────────────────────────────────────────────────");

        for (UserRegistration user : userDatabase) {

            System.out.printf("%-4d %-28s %-6d %-20s\n",
                    user.getId(),
                    truncate(user.getEmail(), 28),
                    user.getAge(),
                    truncate(user.getFullName(), 20));
        }

        System.out.println("──────────────────────────────────────────────────");
        System.out.println("Total Users : " + userDatabase.size());
        System.out.println("──────────────────────────────────────────────────");
    }

    private static void searchUserByEmail() {

        if (userDatabase.isEmpty()) {

            System.out.println("\n⚠ No users available in database!");
            return;
        }

        System.out.print("\nEnter email to search : ");
        String searchEmail = scanner.nextLine().trim();

        UserRegistration foundUser = null;

        for (UserRegistration user : userDatabase) {

            if (user.getEmail().equalsIgnoreCase(searchEmail)) {

                foundUser = user;
                break;
            }
        }

        if (foundUser != null) {

            System.out.println("\n──────────────────────────────────────────────────");
            System.out.println("                  USER FOUND");
            System.out.println("──────────────────────────────────────────────────");

            System.out.println("Registration ID : " + foundUser.getId());
            System.out.println("Email           : " + foundUser.getEmail());
            System.out.println("Age             : " + foundUser.getAge());
            System.out.println("Full Name       : " + foundUser.getFullName());
            System.out.println("Phone           : " + foundUser.getPhone());
            System.out.println("Registered On   : " + foundUser.getRegistrationTime());

            System.out.println("──────────────────────────────────────────────────");

        } else {

            System.out.println("\n⚠ User not found!");
        }
    }

    private static void viewStatistics() {

        System.out.println("\n──────────────────────────────────────────────────");
        System.out.println("                SYSTEM STATISTICS");
        System.out.println("──────────────────────────────────────────────────");

        System.out.println("Successful Registrations : " + userDatabase.size());
        System.out.println("Failed Registrations     : " + failedRegistrations);
        System.out.println("Total Attempts           : "
                + (userDatabase.size() + failedRegistrations));

        if (!userDatabase.isEmpty()) {

            int totalAge = 0;
            int minAge = Integer.MAX_VALUE;
            int maxAge = Integer.MIN_VALUE;

            for (UserRegistration user : userDatabase) {

                totalAge += user.getAge();

                if (user.getAge() < minAge) {
                    minAge = user.getAge();
                }

                if (user.getAge() > maxAge) {
                    maxAge = user.getAge();
                }
            }

            double avgAge = (double) totalAge / userDatabase.size();

            System.out.println("──────────────────────────────────────────────────");

            System.out.printf("Average Age : %.1f\n", avgAge);
            System.out.println("Youngest    : " + minAge);
            System.out.println("Oldest      : " + maxAge);
            System.out.println("Minimum Age : " + RegistrationService.MINIMUM_AGE);
        }

        System.out.println("──────────────────────────────────────────────────");
    }

    private static int getIntInput() {

        while (true) {

            try {

                return Integer.parseInt(scanner.nextLine().trim());

            } catch (NumberFormatException e) {

                System.out.print("⚠ Invalid number! Enter again : ");
            }
        }
    }

    private static String truncate(String str, int length) {

        if (str.length() <= length) {
            return str;
        }

        return str.substring(0, length - 3) + "...";
    }
}


// UserRegistration Class
class UserRegistration {

    private int id;
    private String email;
    private int age;
    private String fullName;
    private String phone;
    private String registrationTime;

    public UserRegistration(int id,
                            String email,
                            int age,
                            String fullName,
                            String phone) {

        this.id = id;
        this.email = email;
        this.age = age;
        this.fullName = fullName;
        this.phone = phone;

        this.registrationTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public String getRegistrationTime() {
        return registrationTime;
    }
}