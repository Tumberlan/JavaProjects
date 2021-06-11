import java.util.Random;
import java.util.concurrent.Exchanger;

public class Chef implements Runnable {

    public Chef(Exchanger<MealType> name){
        what = name;
        System.out.println("Chef Get order");
    }
    Exchanger<MealType> what;
    Random random = new Random();
    Meal meal = new Meal();
    MealType mealName;
    private void ChefChooseMeal(){
        mealName = meal.ChooseMeal();
    }
    static int howMany = 0;
    public void run() {
        try {
            while (howMany < 10) {
                what.exchange(mealName);
                if (mealName == MealType.NOTREADY) {
                    ChefChooseMeal();
                    int time = random.nextInt(8);
                    while (mealName != meal.GetMealName(time)) {
                        time = random.nextInt(8);
                    }
                    howMany++;
                    System.out.println("Chef Done meal "+howMany);
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }



}
