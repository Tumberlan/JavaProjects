import java.util.Random;

public class Meal {
    Random random = new Random();
    public MealType ChooseMeal(){
        return GetMealName(random.nextInt(8));
    }
    public MealType GetMealName(int code){
        switch (code){
            case 0: return MealType.CESAR;
            case 1: return MealType.SOUP;
            case 2: return MealType.ROASTBEAF;
            case 3: return MealType.CAKE;
            case 4: return MealType.WHISKEY;
            case 5: return MealType.TEA;
            case 6: return MealType.HOTDOG;
            case 7: return MealType.FUAGRA;
        }
        return MealType.CESAR;
    }

}
