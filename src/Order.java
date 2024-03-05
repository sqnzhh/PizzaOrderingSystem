import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Order{
    private List<Pizza> pizzas;
    private String orderDescription;
    private double payableBillAmount;
    private String orderDate;
    private int orderId;
    public Scanner scanner = new Scanner(System.in);
    public Order(){
        this.pizzas = new ArrayList<>();
        this.orderDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(new Date());
    }
    public Order(int orderId, String orderDate, double payableBillAmount, String orderDescription){

        this.orderId = orderId;
        this.orderDate = orderDate;
        this.payableBillAmount = payableBillAmount;
        this.orderDescription = orderDescription;
    }

    public void addPizza(Pizza pizza){
        pizzas.add(pizza);
        recalculatePayableBillAmount();

    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public double getPayableBillAmount() {
        return payableBillAmount;
    }

    public void setPayableBillAmount(double payableBillAmount) {
        this.payableBillAmount = payableBillAmount;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    private void recalculatePayableBillAmount() {
        payableBillAmount = 0.0;
        for (Pizza pizza : pizzas) {
            payableBillAmount += pizza.getPrice();
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "pizzas=" + pizzas +
                ", orderDescription='" + orderDescription + '\'' +
                ", payableBillAmount=" + payableBillAmount +
                ", orderDate='" + orderDate + '\'' +
                ", orderId=" + orderId +
                '}';
    }
}