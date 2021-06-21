

public class Chef extends Thread {

    private Restaurant restaurant;

    public Chef(Restaurant rest){
        restaurant = rest;
    }

    public void run(){
        for (int i = 0; i < 10; i++){
            restaurant.meals[i] = new Meal(i+1);

            synchronized (restaurant.lock){
                try {
                    restaurant.meals[i].makeMeal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (restaurant.lock) {
                restaurant.lock.notify();
            }
            synchronized (restaurant.lock) {
                try {
                    restaurant.lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
