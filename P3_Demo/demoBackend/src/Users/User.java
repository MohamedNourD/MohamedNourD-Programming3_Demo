package Users;

import Execptions.Status;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int userType;
    public User(String firstName, String lastName, String email, String password, int userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public User() {}

    public String getName() {
        return firstName + " " + lastName;
    }
    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public int getUserType() {
        return userType;
    }

    public String toString() {
        return firstName + "," + lastName + "," + email + "," + password + "," + userType;
    }
    public static User fromString(String line) {
        String[] parts = line.split(",", 5);
        return new User(parts[0], parts[1], parts[2], parts[3], Integer.parseInt(parts[4]));
    }

}