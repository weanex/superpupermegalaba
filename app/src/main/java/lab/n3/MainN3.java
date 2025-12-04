package lab.n3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainN3 {
    
    public static void execute() {
        System.out.println("Zadanie 3\n");
        
        
        ShoeWarehouseExecutor warehouse = new ShoeWarehouseExecutor();
        int totalOrders = 30;
        
        
        ExecutorService producerService = Executors.newFixedThreadPool(2);
        
        
        producerService.submit(new ProducerTask(warehouse, totalOrders / 2, 1));
        producerService.submit(new ProducerTask(warehouse, totalOrders / 2, 2));
        
        
        ExecutorService consumerService = Executors.newFixedThreadPool(4);
        
        
        for (int i = 0; i < 4; i++) {
            consumerService.submit(new ConsumerTask(warehouse, i + 1));
        }
        
        
        try {
            Thread.sleep(8000); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        
        producerService.shutdown();
        consumerService.shutdownNow();
        warehouse.shutdown();
        
        try {
            if (!producerService.awaitTermination(3, TimeUnit.SECONDS)) {
                producerService.shutdownNow();
            }
            if (!consumerService.awaitTermination(3, TimeUnit.SECONDS)) {
                consumerService.shutdownNow();
            }
        } catch (InterruptedException e) {
            producerService.shutdownNow();
            consumerService.shutdownNow();
            Thread.currentThread().interrupt();
        }
        
        System.out.println("\n ezzz");
        System.out.println("=".repeat(50));
    }
    
    
    public static void main(String[] args) {
        execute();
    }
}