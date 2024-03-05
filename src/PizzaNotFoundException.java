public class PizzaNotFoundException extends Exception {
    public PizzaNotFoundException() {
        super("Pizza not found");
    }

    public PizzaNotFoundException(String message) {
        super(message);
    }
}
