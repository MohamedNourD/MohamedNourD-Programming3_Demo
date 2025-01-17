
import Users.*;

import java.io.IOException;

public class Main {
    public static void main (String[] args) throws IOException {
        System.out.println(UsersManagement.createCustomerAccount("Nour", "Diab", "n.diab@gmail.com", "123", "123").isDone());
    }
}
