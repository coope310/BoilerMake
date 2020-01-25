package plants;

import java.math.BigDecimal;

public enum Plant {
    CORN (100, 2, 300, 0.5),
    WHEAT(75, 1, 150, 1),
    ;

    private int cost;
    private int waterCost;
    private int price;
    private double timeToGrow;

    Plant(int cost, int waterCost, int price, double timeToGrow) {
        this.cost = cost;
        this.waterCost = waterCost;
        this.price = price;
        this.timeToGrow = timeToGrow;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getWaterCost() {
        return waterCost;
    }

    public void setWaterCost(int waterCost) {
        this.waterCost = waterCost;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getTimeToGrow() {
        return timeToGrow;
    }

    public void setTimeToGrow(double timeToGrow) {
        this.timeToGrow = timeToGrow;
    }
}