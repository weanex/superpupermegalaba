package lab.n1;

public class N1 {
    
    
    public static class EvenThread extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 1; i <= 10; i++) {
                    if (i % 2 == 0) {
                        System.out.println("Potok EvenThread (extends Thread): " + i);
                        Thread.sleep(100);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("EvenThread prervan");
            }
        }
    }
    
    public static void execute() {
        System.out.println("Zadanie 1\n");
        
        
        Thread evenThread = new EvenThread();
        
        
        Thread oddThread = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    if (i % 2 != 0) {
                        System.out.println("Potok OddThread (implements Runnable): " + i);
                        Thread.sleep(100);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("OddThread prervan");
            }
        });
        
        
        System.out.println("Zapusk potokov");
        evenThread.start();
        oddThread.start();
        
        
        try {
            evenThread.join();
            oddThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("\n ezz ");
        System.out.println("=".repeat(50) + "\n");
    }
    
    
    public static void main(String[] args) {
        execute();
    }
}