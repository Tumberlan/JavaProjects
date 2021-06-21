public class Waiter extends Thread {

    private Restaurant restaurant;

    public Waiter(Restaurant rest) {
        restaurant = rest;
    }

    public void run(){
        for (int i = 0; i < 10; i++){
            synchronized (restaurant.lock) {
                try {
                    restaurant.lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (restaurant.lock){
                try {
                    restaurant.meals[i].deliverMeal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (restaurant.lock) {
                restaurant.lock.notify();
            }
        }
    }
}
