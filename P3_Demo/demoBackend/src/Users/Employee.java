package Users;

public class Employee extends User {
    private int emplyeeId;

    public Employee(int emplyeeId, String firstName, String lastName, String email, String password, int userType) {
        super(firstName, lastName, email, password, userType);
        this.emplyeeId = emplyeeId;
    }

    public int getId() {return emplyeeId;}

    public String toString() {
        return emplyeeId + "," + getFirstName() + "," + getLastName() + "," + getEmail() + "," + getPassword() + "," + getUserType();
    }
    public static Employee fromString(String line) {
        String[] parts = line.split(",", 5);
        return new Employee(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5]));
    }
}















