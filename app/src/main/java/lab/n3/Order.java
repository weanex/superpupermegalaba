package lab.n3;

public record Order(long orderId, String shoeType, int quantity) {
    
    @Override
    public String toString() {
        return "Zakaz #" + orderId + ": " + shoeType + " x" + quantity;
    }
}