
import Users.*;

import java.io.IOException;

public class Main {

    public static void main (String[] args) throws IOException{
        System.out.println(UsersManagement.logIn("s.khaled@gmail.com", "123").getMsg());
    }


}
