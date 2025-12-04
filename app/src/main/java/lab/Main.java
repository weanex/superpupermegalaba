package lab;

import lab.n1.N1;
import lab.n2.MainN2;
import lab.n3.MainN3;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("laba");
        System.out.println("=".repeat(50));
        
        
        System.out.println("\nZapusk \n");
        
        
        N1.execute();
        
        
        MainN2.execute();
        
        
        MainN3.execute();
        
        System.out.println("\nEzzz");
    }
}