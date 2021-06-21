
public class Restaurant {

    public Meal[] meals = new Meal[10];

    public final Object lock = new Object();

    public Waiter waiter = new Waiter(this);

    public Chef chef = new Chef(this);

    public void run(){
        chef.start();
        waiter.start();
    }
}
