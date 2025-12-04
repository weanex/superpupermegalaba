package lab.n2;

import java.util.ArrayList;
import java.util.List;

public class ShoeWarehouse {
    
    
    public static final List<String> PRODUCT_TYPES = List.of(
        "Motoboti", "Barhotnie tyagi", "Tuflya", "danil lox", 
        "Cargo", "Rickovens", "Balensochka", "Makasini"
    );
    
    
    private final List<Order> orders = new ArrayList<>();
    private final int MAX_CAPACITY = 10;
    
    
    public synchronized void receiveOrder(Order order) {
        
        while (orders.size() >= MAX_CAPACITY) {
            try {
                System.out.println("Sklad polon " + order);
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Producer prervan");
            }
        }
        
        
        orders.add(order);
        System.out.println("Poluchen zakaz: " + order + " | Vsego zakazov: " + orders.size());
        
        
        notifyAll();
    }
    
    
    public synchronized Order fulfillOrder() {
        
        while (orders.isEmpty()) {
            try {
                System.out.println("Sklad pust " + Thread.currentThread().getName() + " jdet zakaz");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Consumer prervan");
                return null;
            }
        }
        
        
        Order order = orders.remove(0);
        System.out.println("Zakaz vipolnen: " + order + " | Ostalos zakazov: " + orders.size());
        
        
        notifyAll();
        
        return order;
    }
    
    
    public synchronized boolean hasOrders() {
        return !orders.isEmpty();
    }
}