package fastfoodnetwork.query.dto;

public class OrderStatisticsDTO {
    private int totalOrders;
    private int completedOrders;
    private double averageDishesPerOrder;

    @Override
    public String toString() {
        return "OrderStatisticsDTO{" +
                "totalOrders=" + totalOrders +
                ", completedOrders=" + completedOrders +
                ", averageDishesPerOrder=" + averageDishesPerOrder +
                '}';
    }

    public OrderStatisticsDTO(int totalOrders, int completedOrders, double averageDishesPerOrder) {
        this.totalOrders = totalOrders;
        this.completedOrders = completedOrders;
        this.averageDishesPerOrder = averageDishesPerOrder;
    }

    public int getTotalOrders() { return totalOrders; }
    public int getCompletedOrders() { return completedOrders; }
    public double getAverageDishesPerOrder() { return averageDishesPerOrder; }
}