import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PizzaService implements PizzaInterface {
    private String pizzaNotFoundMessage;
    private PizzaStore pizzaStore;
    private List<Pizza> pizzas;

    public PizzaService(PizzaStore pizzaStore){
        this.pizzaStore = pizzaStore;
        this.pizzas = new ArrayList<>();

    }
    public Pizza addNewPizza(){
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("<=>Add New Pizza Menu<=>");
        System.out.println();
        System.out.println("Enter details as name of Topping, spice level(basic/mediate/full), description to add a new Topping...");
        String toppingsInput = scanner.nextLine();
        String[] toppingDetails = toppingsInput.split(",");
        if(toppingDetails.length==3){
            String toppingName = toppingDetails[0].trim();
            String spiceLevel = toppingDetails[1].trim();
            String description = toppingDetails[2].trim();
            Topping topping = new Topping(toppingName, spiceLevel, description);
            System.out.println("Enter Details as name of Base, type(thin/thick), description to add a new PizzaBase...");
            String pizzaBaseInput = scanner.nextLine().trim();
            String[] pizzaBaseDetails = pizzaBaseInput.split(",");

            if (pizzaBaseDetails.length == 3) {
                String baseName = pizzaBaseDetails[0].trim();
                String baseType = pizzaBaseDetails[1].trim();
                String baseDescription = pizzaBaseDetails[2].trim();
                PizzaBase pizzaBase = new PizzaBase(baseName, baseType, baseDescription);
                System.out.println("Enter Details as name of Pizza, price, size (small/Medium/Large) to add a new Pizza...");
                String pizzaInput = scanner.nextLine().trim();
                String[] pizzaDetails = pizzaInput.split(",");

                if (pizzaDetails.length == 3) {
                    String pizzaName = pizzaDetails[0].trim();
                    double price = Double.parseDouble(pizzaDetails[1].trim());
                    String size = pizzaDetails[2].trim();
                    int newPizzaId = pizzas.size() + 1;
                    Pizza pizza = new Pizza(newPizzaId, pizzaName, price, size, topping, pizzaBase);

                    pizzas.add(pizza);
                    System.out.println("New Pizza added successfully!!!");
                    System.out.println();
                    System.out.println("Pizza Details => ID : " + pizza.getPizzaId() +
                            ", Name: " + pizza.getPizzaName() +
                            ", Price: " + pizza.getPrice() +
                            ", Size: " + pizza.getSize());

                    System.out.println("\nToppings Details => Topping Name: " + pizza.getTopping().getToppingName() +
                            ", spiceLevel: " + pizza.getTopping().getSpiceLevel() +
                            ", Description: " + pizza.getTopping().getDescription());

                    System.out.println("\nPizza Base Details => BaseName: " + pizza.getPizzaBase().getBaseName() +
                            ", Type: " + pizza.getPizzaBase().getBaseType() +
                            ", Description: " + pizza.getPizzaBase().getDescription());
                    return pizza;
                } else {
                    System.out.println("Invalid input format for Pizza. Please try again!!!");
                }
            } else {
                System.out.println("Invalid input format for Pizza. Please try again!!!");
            }
        }
        else{
            System.out.println("Invalid input format. PLease try again!!!");
        }
        return null;
    }
    public void deletePizza(Scanner scanner) {
        System.out.println();
        System.out.println("<=>Delete Pizza Menu<=>");
        System.out.println();
        System.out.print("Enter Pizza name: ");

        String pizzaName = scanner.next();

        Pizza pizzaToDelete = null;
        for (Pizza pizza : pizzas) {
            if (pizza.getPizzaName().equalsIgnoreCase(pizzaName)) {
                pizzaToDelete = pizza;
                break;
            }
        }

        if (pizzaToDelete != null) {
            pizzas.remove(pizzaToDelete);
            System.out.println("Pizza '" + pizzaName + "' deleted successfully!");
        } else {
            System.out.println("Pizza '" + pizzaName + "' not found. Deletion failed.");
        }
    }


    public void updatePrice() throws PizzaNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("<=>Update Pizza Price Menu<=>");
        System.out.println();
        System.out.print("Enter Pizza name: ");
        String pizzaNameToUpdate = scanner.nextLine().trim();
        Pizza pizzaToUpdate = getPizzaByName(pizzaNameToUpdate);
        if (pizzaToUpdate != null) {
            System.out.print("Enter the new price for " + pizzaNameToUpdate + ": ");
            double newPrice = scanner.nextDouble();
            scanner.nextLine();

            if (newPrice >= 0) {
                pizzaToUpdate.setPrice(newPrice);
                System.out.println("Pizza '" + pizzaNameToUpdate + "' price updated successfully!");
            } else {
                System.out.println("Invalid price. Please enter a non-negative value.");
            }
        } else {
            System.out.println("Pizza '" + pizzaNameToUpdate + "' not found. Update failed.");
        }

    }

    public List<Pizza> getAllPizzas() {
        System.out.println("<=>View All Pizza Menu<=>");
        if(pizzas.size()!=0) {
            for (int i = 0; i < pizzas.size(); i++) {
                System.out.println("Pizza Number " + (i + 1) + ":");
                Pizza pizza = pizzas.get(i);
                System.out.println("Pizza Details => ID: " + pizza.getPizzaId() +
                        ", Name: " + pizza.getPizzaName() +
                        ", Price: " + pizza.getPrice() +
                        ", Size: " + pizza.getSize());

                System.out.println("Toppings Details => Topping Name: " + pizza.getTopping().getToppingName() +
                        ", Spice Level: " + pizza.getTopping().getSpiceLevel() +
                        ", Description: " + pizza.getTopping().getDescription());

                System.out.println("Pizza Base Details => Base Name: " + pizza.getPizzaBase().getBaseName() +
                        ", Type: " + pizza.getPizzaBase().getBaseType() +
                        ", Description: " + pizza.getPizzaBase().getDescription());
                System.out.println();
            }
        }
        else{
            System.out.println(" NO PIZZAS ADDED YET!!!");
        }


        return pizzas;
    }

    @Override
    public Pizza updatePrice(Pizza pizza, double newPrice) {
        return null;
    }

    @Override
    public void deletePizza(Pizza pizza) {

    }

    @Override
    public Pizza addNewPizza(Pizza pizza) {
        return null;
    }

    public Pizza orderNewPizza(Pizza pizza, Customer customer) {
        Order order = new Order();
        order.addPizza(pizza);
        customer.addOrder(order);
        System.out.println("Pizza ordered successfully!");
        return pizza;
    }

    @Override
    public Pizza getPizzaByName(String pizzaName) throws PizzaNotFoundException {
        for (Pizza pizza : pizzas) {
            if (pizza.getPizzaName().equalsIgnoreCase(pizzaName)) {
                return pizza;
            }
        }
        throw new PizzaNotFoundException("Pizza not found with name: " + pizzaName);
    }
    public Pizza getPizzaById(int pizzaId) throws PizzaNotFoundException {
        for (Pizza pizza : pizzas) {
            if (pizza.getPizzaId() == pizzaId) {
                return pizza;
            }
        }
        throw new PizzaNotFoundException("Pizza not found with ID: " + pizzaId);
    }

    @Override
    public Pizza getPizzaBySize(String size) throws PizzaNotFoundException {
        for (Pizza pizza : pizzas) {
            if (pizza.getSize().equalsIgnoreCase(size)) {
                return pizza;
            }
        }
        throw new PizzaNotFoundException("Pizza not found with size: " + size);
    }
    public void searchPizza() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("<=>Search Pizza Menu<=>");
        System.out.println("Choose your Search Type :");
        System.out.println("1) Search by Name");
        System.out.println("2) Search by ID");
        System.out.println("3) Search by Size");

        try {
            int searchType = scanner.nextInt();
            scanner.nextLine();

            switch (searchType) {
                case 1:
                    searchByName(scanner);
                    break;
                case 2:
                    searchById(scanner);
                    break;
                case 3:
                    searchBySize(scanner);
                    break;
                default:
                    System.out.println("Invalid search type. Please try again.");
            }
        } catch (InputMismatchException | PizzaNotFoundException e) {
            System.out.println("Invalid input. Please enter a valid option.");
            scanner.nextLine();
        }
    }

    private void searchByName(Scanner scanner) throws PizzaNotFoundException {
        System.out.print("Enter Pizza name to search: ");
        String pizzaName = scanner.nextLine().trim();
        Pizza foundPizza = getPizzaByName(pizzaName);

        if (foundPizza != null) {
            displaySinglePizza(foundPizza);
        } else {
            System.out.println("Pizza with name '" + pizzaName + "' not found.");
        }
    }

    private void searchById(Scanner scanner) throws PizzaNotFoundException {
        System.out.print("Enter Pizza ID to search: ");
        int pizzaId = scanner.nextInt();
        scanner.nextLine();
        Pizza foundPizza = getPizzaById(pizzaId);

        if (foundPizza != null) {
            displaySinglePizza(foundPizza);
        } else {
            System.out.println("Pizza with ID '" + pizzaId + "' not found.");
        }
    }

    private void searchBySize(Scanner scanner) throws PizzaNotFoundException {
        System.out.print("Enter Pizza size to search: ");
        String size = scanner.nextLine().trim();
        Pizza foundPizza = getPizzaBySize(size);

        if (foundPizza != null) {
            displaySinglePizza(foundPizza);
        } else {
            System.out.println("Pizza with size '" + size + "' not found.");
        }
    }

    private void displaySinglePizza(Pizza pizza) {
        System.out.println("Pizza found:");
        System.out.println("Pizza Details => ID: " + pizza.getPizzaId() +
                ", Name: " + pizza.getPizzaName() +
                ", Price: " + pizza.getPrice() +
                ", Size: " + pizza.getSize());

        System.out.println("Toppings Details => Topping Name: " + pizza.getTopping().getToppingName() +
                ", Spice Level: " + pizza.getTopping().getSpiceLevel() +
                ", Description: " + pizza.getTopping().getDescription());

        System.out.println("Pizza Base Details => Base Name: " + pizza.getPizzaBase().getBaseName() +
                ", Type: " + pizza.getPizzaBase().getBaseType() +
                ", Description: " + pizza.getPizzaBase().getDescription());
        System.out.println();
    }

}
