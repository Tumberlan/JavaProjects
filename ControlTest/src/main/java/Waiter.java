import java.util.Random;
import java.util.concurrent.Exchanger;

public class Waiter implements Runnable{
    public Waiter(Exchanger<MealType> name){
        what = name;
        System.out.println("Waiter Took order");
    }
    Exchanger<MealType> what;
    MealType mealName;
    static int howMany = 0;
    public void run(){
        try {
            while (howMany < 10) {
                mealName = what.exchange(mealName);
                GiveMeal();
                howMany++;
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void GiveMeal() throws InterruptedException {
        Random random = new Random();
        int a = 0;
        for(int i = 0; i < random.nextInt(100)+10; i++){
            a++;
        }
        System.out.println("Waiter Want meal");
    }
}
