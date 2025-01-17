package Users;

import Execptions.Status;

import java.io.*;
import java.util.*;

public class UsersManagement {
    private static void addCustomer(Customer customer) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\FolderProject_in\\Project-Programming3_Demo\\Files\\users.txt", true))) {
            writer.write(customer.toString());
            writer.newLine();
        }
    }
    default static List<Customer> getCustomers() throws IOException {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Project-Programming3_Demo\\Files\\users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                customers.add(Customer.fromString(line));
            }
        }
        return customers;
    }

    private static void addEmployee(Employee employee) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Project-Programming3_Demo\\Files\\users.txt", true))) {
            writer.write(employee.toString());
            writer.newLine();
        }
    }

    default static List<Employee> getEmployees() throws IOException {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Project-Programming3_Demo\\Files\\users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                employees.add(Employee.fromString(line));
            }
        }
        return employees;
    }

    default static User getUserByEmail(String email) throws IOException {
        List<Customer> customers = getCustomers();
        List<Employee> employees = getEmployees();

        List<User> users = new ArrayList<>();
        users.addAll(customers);
        users.addAll(employees);

        System.out.println("Searching for email: " + email);

        for (User user : users) {
            System.out.println("Checking user: " + user.getEmail());
            if (user.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Match found! Returning password.");
                return user;
            }
        }

        System.out.println("Email not found: " + email);
        return null;
    }

    public Status createCustomerAccount(String firstName, String lastName, String email, String password1, String password2, int userType) {
        try {
            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password1.isEmpty() || password2.isEmpty()) {
                throw new Exception("Not all fields are complete.");
            }
            else if (!email.contains("@") || !email.contains(".")) {
                throw new Exception("The email is not valid.");
            }
            else if (!password1.equals(password2)) {
                throw new Exception("Password mismatching!");
            }
            else {
                List<Customer> customers = getCustomers();
                int nextCustomerId = customers.isEmpty() ? 1 : customers.get(customers.size() - 1).getId() + 1;

                Customer customer = new Customer(nextCustomerId, firstName, lastName, email, password1, 1);
                addCustomer(customer);
                Notification n = new Notififcation("Welcome", "Your acconunt has been created sucessfuly");
                n.run();
                return new Status();
            }
        }
        catch (Exception e) {
            return new Status(e.getMessage());
        }
    }

    public Status createEmployeeAccount(String firstName, String lastName, String email, String password1, String password2, int userType) {
        try {
            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password1.isEmpty() || password2.isEmpty()) {
                throw new Exception("Not all fields are complete.");
            }
            else if (!email.contains("@") || !email.contains(".")) {
                throw new Exception("The email is not valid.");
            }
            else if (!password1.equals(password2)) {
                throw new Exception("Password mismatching!");
            }
            else {
                List<Employee> employees = getEmployees();
                int nextEmployeeId = employees.isEmpty() ? 1 : employees.get(employees.size() - 1).getId() + 1;

                Employee employee = new Employee(nextEmployeeId, firstName, lastName, email, password1, 2);
                addEmployee(employee);
                return new Status();
            }
        }
        catch (Exception e) {
            return new Status(e.getMessage());
        }
    }

    public static Status logIn (String email, String password)  {
        System.out.println(email);
        System.out.println(password);
        try {
            User currentUser = getUserByEmail(email);
            if (currentUser == null)
                throw new Exception("There is no user with that email!");
            else if (email.isEmpty()) {
                throw new Exception("The email is empty!");
            } else if (!email.contains("@") || !email.contains(".")) {
                throw new Exception("The email is Invalid!");
            } else if (!currentUser.getPassword().equals(password)) {
                throw new Exception("Password isn't correct");
            } else {
                System.out.println("Oh hi " + currentUser.getName() + "!");
                return new Status();
            }
        }
        catch (Exception e) {
            return new Status(e.getMessage());
        }
    }

    public static String loyalCustomer() throws IOException {
        List<Customer> customers = getCustomers();
        String loyalCustomer;
        int max;

        loyalCustomer = customers.get(0).getName();
        max = customers.get(0).getCntOrders();

        for (Customer customer : customers) {
            if(max < customer.getCntOrders()) {
                loyalCustomer = customer.getName();
                max = customer.getCntOrders();
            }
        }

        if (max == 0)
            return "No one";
        else
            return loyalCustomer;
    }

    
}