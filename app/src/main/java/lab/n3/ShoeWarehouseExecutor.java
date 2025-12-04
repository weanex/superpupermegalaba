package lab.n3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ShoeWarehouseExecutor {
    
    public static final List<String> PRODUCT_TYPES = List.of(
        "Motoboti", "Barhotnie tyagi", "Tuflya", "danil lox", 
        "Cargo", "Rickovens", "Balensochka", "Makasini"
    );
    
    private final List<Order> orders = new ArrayList<>();
    private final int MAX_CAPACITY = 15;
    private final ExecutorService fulfillmentService;
    
    public ShoeWarehouseExecutor() {
        
        this.fulfillmentService = Executors.newFixedThreadPool(3);
    }
    
    public synchronized void receiveOrder(Order order) {
        while (orders.size() >= MAX_CAPACITY) {
            try {
                System.out.println("[Executor] sklad polon! " + order);
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Producer prervan");
            }
        }
        
        orders.add(order);
        System.out.println("[Executor] poluchen zalaz: " + order + " | Vsego: " + orders.size());
        
        notifyAll();
    }
    
    public synchronized void processOrder() {
        while (orders.isEmpty()) {
            try {
                System.out.println("[Executor] Pusto!");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        
        Order order = orders.remove(0);
        System.out.println("[Executor] Nachalo obrabotki: " + order);
        
        
        fulfillmentService.submit(() -> fulfillOrderTask(order));
        
        notifyAll();
    }
    
    private void fulfillOrderTask(Order order) {
        try {
            
            Thread.sleep((long) (Math.random() * 600));
            System.out.println("[Executor] Vipolnen: " + order + " | Potok: " 
                + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public void shutdown() {
        fulfillmentService.shutdown();
        try {
            if (!fulfillmentService.awaitTermination(5, TimeUnit.SECONDS)) {
                fulfillmentService.shutdownNow();
            }
        } catch (InterruptedException e) {
            fulfillmentService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
    
    public synchronized boolean hasOrders() {
        return !orders.isEmpty();
    }
}