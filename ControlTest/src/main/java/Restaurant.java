import java.util.concurrent.Exchanger;

public class Restaurant {

    public void work(){
        Exchanger<MealType> swap = new Exchanger<MealType>();
        new Thread((Runnable) new Chef(swap)).start();
        new Thread((Runnable) new Waiter(swap)).start();
    }
}
