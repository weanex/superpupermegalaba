package lab.n2;

public class Producer implements Runnable {
    
    private final ShoeWarehouse warehouse;
    private final int orderCount;
    
    public Producer(ShoeWarehouse warehouse, int orderCount) {
        this.warehouse = warehouse;
        this.orderCount = orderCount;
    }
    
    @Override
    public void run() {
        try {
            for (int i = 1; i <= orderCount; i++) {
                
                String shoeType = ShoeWarehouse.PRODUCT_TYPES.get(
                    (int) (Math.random() * ShoeWarehouse.PRODUCT_TYPES.size())
                );
                int quantity = (int) (Math.random() * 5) + 1;
                
                Order order = new Order(i, shoeType, quantity);
                warehouse.receiveOrder(order);
                
                
                Thread.sleep((long) (Math.random() * 300));
            }
            System.out.println("Producer Zavershil " + orderCount + " zakaz");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Producer prervan");
        }
    }
}