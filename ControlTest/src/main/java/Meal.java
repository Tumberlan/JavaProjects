import java.util.Random;

public class Meal {

    boolean isReady = false;
    int mealNumber;

    Random random = new Random();

    public Meal(int number){
        mealNumber = number;
    }
    public void makeMeal() throws InterruptedException {
        Thread.sleep(random.nextInt(500)+500);
        isReady = true;
        System.out.println("chef made meal " + mealNumber);
    }

    public void deliverMeal() throws InterruptedException {
        Thread.sleep(random.nextInt(500)+500);
        System.out.println("waiter delivered meal " + mealNumber);
    }
}
