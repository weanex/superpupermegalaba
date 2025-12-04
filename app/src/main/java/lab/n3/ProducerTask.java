package lab.n3;

import java.util.concurrent.Callable;

public class ProducerTask implements Callable<Void> {
    
    private final ShoeWarehouseExecutor warehouse;
    private final int orderCount;
    private final int producerId;
    
    public ProducerTask(ShoeWarehouseExecutor warehouse, int orderCount, int producerId) {
        this.warehouse = warehouse;
        this.orderCount = orderCount;
        this.producerId = producerId;
    }
    
    @Override
    public Void call() throws Exception {
        try {
            for (int i = 1; i <= orderCount; i++) {
                String shoeType = ShoeWarehouseExecutor.PRODUCT_TYPES.get(
                    (int) (Math.random() * ShoeWarehouseExecutor.PRODUCT_TYPES.size())
                );
                int quantity = (int) (Math.random() * 5) + 1;
                
                long orderId = producerId * 1000L + i;
                Order order = new Order(orderId, shoeType, quantity);
                warehouse.receiveOrder(order);
                
                Thread.sleep((long) (Math.random() * 200));
            }
            System.out.println("[Producer-" + producerId + "] Sozdal " + orderCount + " zakazov");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return null;
    }
}