package lab.n3;

import java.util.concurrent.Callable;

public class ConsumerTask implements Callable<Void> {
    
    private final ShoeWarehouseExecutor warehouse;
    private final int consumerId;
    
    public ConsumerTask(ShoeWarehouseExecutor warehouse, int consumerId) {
        this.warehouse = warehouse;
        this.consumerId = consumerId;
    }
    
    @Override
    public Void call() throws Exception {
        try {
            
            while (warehouse.hasOrders() || true) {
                warehouse.processOrder();
                Thread.sleep((long) (Math.random() * 400));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("[Consumer-" + consumerId + "] Zavershen");
        }
        return null;
    }
}