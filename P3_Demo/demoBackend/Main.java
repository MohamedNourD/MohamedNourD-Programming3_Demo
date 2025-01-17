import Meals.Meal;
import Meals.MealsManagment;
import Users.*;

import java.io.IOException;

public class Main {
    public static void main (String[] args) throws IOException{
        System.out.println(MealsManagment.updateMeal(1, new Meal(1, "Burger", "Tomato", 65, "Bug")).getMsg());
    }
}
