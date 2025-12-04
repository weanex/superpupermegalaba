package lab.n2;

public class Consumer implements Runnable {
    
    private final ShoeWarehouse warehouse;
    private final int ordersToProcess;
    
    public Consumer(ShoeWarehouse warehouse, int ordersToProcess) {
        this.warehouse = warehouse;
        this.ordersToProcess = ordersToProcess;
    }
    
    @Override
    public void run() {
        try {
            int processed = 0;
            while (processed < ordersToProcess) {
                Order order = warehouse.fulfillOrder();
                if (order != null) {
                    processed++;
                    
                    Thread.sleep((long) (Math.random() * 500));
                }
            }
            System.out.println(Thread.currentThread().getName() + " obrabotal " + processed + " zakazov");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Consumer prervan");
        }
    }
}