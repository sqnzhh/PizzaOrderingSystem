import java.util.*;
import java.io.*;

public class Customer {
    private static final Random random = new Random();

    private List<Order> orders;
    private Address address;
    private long mobile;
    private String email;
    private String customerName;
    private int customerId;

    public Customer(String name, String email, String mobile, Address address) {
        this.customerId = generateRandomId();
        this.customerName = name;
        this.email = email;
        this.mobile = Long.parseLong(mobile);
        this.address = address;
        this.orders = new ArrayList<>();
    }
    private int generateRandomId() {
        return random.nextInt(100);
    }
    public void addOrder(Order order){
        orders.add(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "orders=" + orders +
                ", address=" + address +
                ", mobile=" + mobile +
                ", email='" + email + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerId=" + customerId +
                '}';
    }
}