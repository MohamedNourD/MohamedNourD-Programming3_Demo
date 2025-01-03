package Users;

import Execptions.Status;

public abstract class User {
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getUserType() {return userType;}
    public abstract String toString();

}