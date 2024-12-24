package Users;

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

    public String getPassword() {
        return password;
    }

    public boolean createAccount(String firstName, String lastName, String email, String password1, String password2, int userType) {
        try {
            if (firstName.isEmpty())
                throw new EmptyString();

            else
                this.firstName=firstName;
        }
        catch (EmptyString e) {
            System.out.println("The first name is empty!");
            return false;
        }
        try {
            if (lastName.isEmpty())
                throw new EmptyString();
            else
                this.lastName=lastName;
        }
        catch (EmptyString e) {
            System.out.println("The last name is empty!");
            return false;
        }
        try {
            if (email.isEmpty())
                throw new EmptyString();
            else {
                if (email.contains("@") && email.contains("."))
                    this.email = email;
                else
                    throw new NotValidEmail();
            }
        }
        catch (EmptyString e) {
            System.out.println("The email is empty!");
            return false;
        }
        catch (NotValidEmail e) {
            System.out.println("The email is Invalid!");
            return false;
        }

        try {
            if (password1.isEmpty())
                throw new EmptyString();
            else {
                if (password1.equals(password2))
                    this.password = password1;
                else
                    throw new PasswordMismatching();
            }
        }
        catch (EmptyString e) {
            System.out.println("The password is empty!");
            return false;
        }
        catch (PasswordMismatching e) {
            System.out.println("Password mismatching!");
            return false;
        }
        this.userType = userType;
        return true;
    }

    public String toString() {
        return firstName + "," + lastName + "," + email + "," + password + "," + userType;
    }
    public static User fromString(String line) {
        String[] parts = line.split(",", 5);
        return new User(parts[0], parts[1], parts[2], parts[3], Integer.parseInt(parts[4]));
    }

}