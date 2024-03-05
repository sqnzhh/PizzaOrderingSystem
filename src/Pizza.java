public class Pizza{

    private PizzaBase pizzaBase;
    private Topping topping;
    private String size;
    private double price;
    private String pizzaName;
    private int pizzaId;
    public Pizza(int pizzaId, String pizzaName, double price, String size, Topping topping, PizzaBase pizzaBase ){
        this.pizzaId = pizzaId;
        this.pizzaName = pizzaName;
        this.price = price;
        this.size = size;
        this.topping = topping;
        this.pizzaBase = pizzaBase;
    }

    public PizzaBase getPizzaBase() {
        return pizzaBase;
    }

    public void setPizzaBase(PizzaBase pizzaBase) {
        this.pizzaBase = pizzaBase;
    }

    public Topping getTopping() {
        return topping;
    }

    public void setTopping(Topping topping) {
        this.topping = topping;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    @Override
    public String toString() {
        return "Pizza Details => ID: " + getPizzaId() +
                ", Name: " + getPizzaName() +
                ", Price: " + getPrice() +
                ", Size: " + getSize();
    }

}