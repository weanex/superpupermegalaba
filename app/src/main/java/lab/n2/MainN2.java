package lab.n2;

public class MainN2 {
    
    public static void execute() {
        System.out.println("Zadanie 2 \n");
        
        
        ShoeWarehouse warehouse = new ShoeWarehouse();
        int totalOrders = 20; 
        
        
        Thread producerThread = new Thread(new Producer(warehouse, totalOrders), "Producer");
        producerThread.start();
        
        
        int consumerCount = totalOrders / 5; 
        Thread[] consumerThreads = new Thread[consumerCount];
        
        for (int i = 0; i < consumerCount; i++) {
            consumerThreads[i] = new Thread(new Consumer(warehouse, 5), "Consumer-" + (i + 1));
            consumerThreads[i].start();
        }
        
        
        try {
            producerThread.join();
            for (Thread consumerThread : consumerThreads) {
                consumerThread.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("\nObrabotano ");
        System.out.println("=".repeat(50) + "\n");
    }
    
    
    public static void main(String[] args) {
        execute();
    }
}