package Users;

import Execptions.Status;
import Notifications.Notification;
import FilesProcessing.addToFile;

import java.io.*;
import java.util.*;

public class UsersManagement {
    private static void addCustomer(Customer customer) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("FilesProcessing\\customers.txt", true))) {
            writer.write(customer.toString());
            writer.newLine();
        }
    }

    static List<Customer> getCustomers() throws IOException {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("FilesProcessing\\customers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                customers.add(Customer.fromString(line));
            }
        }
        return customers;
    }

    private static void addEmployee(Employee employee) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("FilesProcessing\\employees.txt", true))) {
            writer.write(employee.toString());
            writer.newLine();
        }
    }

    static List<Employee> getEmployees() throws IOException {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("FilesProcessing\\employees.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                employees.add(Employee.fromString(line));
            }
        }
        return employees;
    }

    public static Customer getCustomerById(int customerId) throws IOException {
        List<Customer> customers = getCustomers();

        for (Customer customer : customers) {
            if (customer.getId() == customerId) {
                return customer;
            }
        }
        return null;
    }

    public static User getUserByEmail(String email) throws IOException {
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

    public static Customer getCustomerByEmail(String email) throws IOException {
        List<Customer> customers = getCustomers();

        System.out.println("Searching for email: " + email);

        for (Customer customer : customers) {
            System.out.println("Checking user: " + customer.getEmail());
            if (customer.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Match found! Returning password.");
                return customer;
            }
        }

        System.out.println("Email not found: " + email);
        return null;
    }

    private static Boolean emailIsExist(String email) throws IOException {
        List<Customer> customers = getCustomers();
        List<Employee> employees = getEmployees();

        List<User> users = new ArrayList<>();
        users.addAll(customers);
        users.addAll(employees);

        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }

        return false;
    }

    public static Status createCustomerAccount(String firstName, String lastName, String email, String password1, String password2) {
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
            else if (emailIsExist(email)){
                throw new Exception("Email already exists");
            }
            else {
                List<Customer> customers = getCustomers();
                int nextCustomerId = customers.isEmpty() ? 1 : customers.get(customers.size() - 1).getId() + 1;

                Customer customer = new Customer(nextCustomerId, firstName, lastName, email, password1, 1);

                addToFile<Customer> file = new addToFile<>(customer);
                file.start();

                Notification n = new Notification("Welcome!", "Your account has been created successfully ❤️");
                n.start();

                return new Status();
            }
        }
        catch (Exception e) {
            return new Status(e.getMessage());
        }
    }

    public static Status createEmployeeAccount(String firstName, String lastName, String email, String password1, String password2) {
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
            else if (emailIsExist(email)){
                throw new Exception("Email already exists");
            }
            else {
                List<Employee> employees = getEmployees();
                int nextEmployeeId = employees.isEmpty() ? 1 : employees.get(employees.size() - 1).getId() + 1;

                Employee employee = new Employee(nextEmployeeId, firstName, lastName, email, password1, 2);
                addToFile<Employee> file = new addToFile<>(employee);
                file.start();

                Notification n = new Notification("Done!", "The account has been created successfully ❤️");
                n.start();

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
            System.out.println("found");
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

                if (currentUser.getUserType() == 1) {
                    Notification n = new Notification("Welcome back!", "We miss you " + currentUser.getFirstName() + " \nready for a delicious meal? 😋");
                    n.start();
                }
                else {
                    Notification n = new Notification("Welcome back!", "Wish you a nice day 😎");
                    n.start();
                }
                return new Status();
            }
        }
        catch (Exception e) {
            return new Status(e.getMessage());
        }
    }

    public static String loyalCustomer() throws IOException {
        List<Customer> customers = getCustomers();
        return Collections.max(customers, Comparator.comparingInt(Customer::getCntOrders)).getName();
    }

    public static Status updateCustomer(int customerID, Customer updatedCustomer) {
        List<Customer> customers;

        try {
            customers = getCustomers();
        } catch (IOException e) {
            return new Status(e.getMessage());
        }

        boolean customerFound = false;

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == customerID) {
                customers.set(i, updatedCustomer);
                customerFound = true;
                break;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("FilesProcessing\\customers.txt", false))) {
            for (Customer customer : customers) {
                writer.write(customer.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            return new Status(e.getMessage());
        }

        Notification n = new Notification("Done!", "The meal has been added to the customer account");
        n.start();

        return new Status();
    }
    
}