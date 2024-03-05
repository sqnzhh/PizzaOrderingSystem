import java.util.*;
import java.io.*;

public class PizzaDashboard {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\033[1;34m<<<WELCOME TO SDU PIZZA SPOT>>>\033[0m");
        PizzaService pizzaService = new PizzaService(new PizzaStore());
        while (true) {
            try {
                System.out.println("\u001B[33mChoose your Role to Enter the System:\u001B[0m");
                System.out.println("\u001B[33m1. Admin\u001B[0m");
                System.out.println("\u001B[33m2. Customer\u001B[0m");


                int role = scanner.nextInt();
                scanner.nextLine();

                if (role == 1) {
                    openAdminRole(scanner, pizzaService);
                } else if (role == 2) {
                    openCustomerRole(scanner, pizzaService);
                } else {
                    System.out.println();
                    System.out.println("Invalid option, Please choose 1 or 2 to Enter ");
                    System.out.println();
                }
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid option, Please choose 1 or 2 to Enter ");
                System.out.println();
                scanner.nextLine();
            }
        }
    }

    public static void openAdminRole(Scanner scanner, PizzaService pizzaService) {
        while (true) {
            try {
                System.out.println();
                System.out.println("\033[1;35m<<Welcome to ADMIN console !!!>>\033[0m");
                System.out.println("\033[1;36mChoose an Option:\033[0m");
                System.out.println("\033[1;32m1) Add Pizza\033[0m");
                System.out.println("\033[1;33m2) Update Price\033[0m");
                System.out.println("\033[1;31m3) Delete Pizza\033[0m");
                System.out.println("\033[1;34m4) View All Pizza\033[0m");
                System.out.println("\033[1;37m5) Search Pizza\033[0m");
                System.out.println("\033[1;31m6) Exit\033[0m");


                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        pizzaService.addNewPizza();
                        break;
                    case 2:
                        pizzaService.updatePrice();
                        break;
                    case 3:
                        pizzaService.deletePizza(scanner);

                        break;
                    case 4:
                        pizzaService.getAllPizzas();
                        break;
                    case 5:
                        pizzaService.searchPizza();
                        break;
                    case 6:
                        System.out.println("Exiting from Admin Console...");
                        return;
                    default:
                        System.out.println();
                        System.out.println("Invalid option. Please try again!!!");
                        System.out.println();
                }
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("!!!InputMismatchException!!!");
                System.out.println();
                scanner.nextLine();
            } catch (PizzaNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void openCustomerRole(Scanner scanner, PizzaService pizzaService) {
        System.out.println();
        System.out.println("<<Welcome to CUSTOMER console !!!>>");

        System.out.println("Enter your doorNumber, street, city, district, state...");
        String addressInput = scanner.nextLine().trim();
        String[] addressDetails = addressInput.split(",");
        if (addressDetails.length == 5) {
            String doorNumber = addressDetails[0].trim();
            String street = addressDetails[1].trim();
            String city = addressDetails[2].trim();
            String district = addressDetails[3].trim();
            String state = addressDetails[4].trim();

            System.out.println("Enter your Details as name, email, mobile...");
            String customerDetailsInput = scanner.nextLine().trim();
            String[] customerDetails = customerDetailsInput.split(",");
            if (customerDetails.length == 3) {

                String name = customerDetails[0].trim();
                String email = customerDetails[1].trim();
                String mobile = customerDetails[2].trim();

                Customer customer = new Customer(name, email, mobile, new Address(doorNumber, street, city, district, state));
                System.out.println("We added you as our New customer...");
                System.out.println("Customer Details => ID : " + customer.getCustomerId() +
                        ", Name: " + customer.getCustomerName() +
                        ", Email: " + customer.getEmail() +
                        ", Mobile: " + customer.getMobile());
                System.out.println("Address => Door Number: " + doorNumber +
                        ", Street: " + street +
                        ", City: " + city +
                        ", District: " + district +
                        ", State: " + state);


                while (true) {
                    try {
                        System.out.println();
                        System.out.println("Choose an Option:");
                        System.out.println("1) Order Pizza");
                        System.out.println("2) Pay Bill");
                        System.out.println("3) View All Pizza");
                        System.out.println("4) View your Orders");
                        System.out.println("5) Search Pizza");
                        System.out.println("6) Exit");

                        int option = scanner.nextInt();
                        scanner.nextLine();

                        switch (option) {
                            case 1:
                                List<Pizza> availablePizzas = pizzaService.getAllPizzas();
                                if (!availablePizzas.isEmpty()) {
                                    System.out.println("<=> View All Pizza Menu <=>");
                                    for (Pizza pizza : availablePizzas) {
                                        System.out.println("[Pizza Details => ID: " + pizza.getPizzaId() +
                                                ", Name: " + pizza.getPizzaName() +
                                                ", Price: " + pizza.getPrice() +
                                                ", Size: " + pizza.getSize());
                                    }

                                    System.out.println("Enter Pizza Name to Place your Order : ");
                                    String pizzaName = scanner.nextLine().trim();

                                    try {
                                        Pizza selectedPizza = pizzaService.getPizzaByName(pizzaName);
                                        if (selectedPizza != null) {
                                            System.out.println("Ordered Pizza: \n" + selectedPizza);

                                            Order order = new Order();
                                            order.addPizza(selectedPizza);
                                            customer.addOrder(order);
                                        } else {
                                            System.out.println("Pizza not found. Please enter a valid pizza name.");
                                        }
                                    } catch (PizzaNotFoundException e) {
                                        System.out.println("Pizza not found. Please enter a valid pizza name.");
                                    }
                                } else {
                                    System.out.println("No pizzas available to order. Please add pizzas first.");
                                }
                                break;

                            case 2:
                                System.out.println();
                                System.out.println("<=> Check your order's Bill Amount here... <=>");
                                List<Order> customerOrders = customer.getOrders();

                                double totalBillAmount = 0.0;
                                for (Order order : customerOrders) {
                                    totalBillAmount += order.getPayableBillAmount();
                                }

                                System.out.println("Your Payable Bill Amount for all your orders is Rs: " + totalBillAmount + "$");
                                break;
                            case 3:

                                pizzaService.getAllPizzas();
                                break;
                            case 4:
                                System.out.println("Your Orders:");

                                List<Order> orders = customer.getOrders();

                                if (!orders.isEmpty()) {
                                    for (Order order : orders) {
                                        System.out.println("Order Details: Id: " + order.getOrderId() +
                                                ", Date: " + order.getOrderDate() +
                                                ", Bill Amount: " + order.getPayableBillAmount());

                                        List<Pizza> pizzasInOrder = order.getPizzas();

                                        if (pizzasInOrder != null && !pizzasInOrder.isEmpty()) {
                                            System.out.println("Pizzas in this specific Order:");

                                            for (Pizza pizza : pizzasInOrder) {
                                                System.out.println("[Pizza Details => ID: " + pizza.getPizzaId() +
                                                        ", Name: " + pizza.getPizzaName() +
                                                        ", Price: " + pizza.getPrice() +
                                                        ", Size: " + pizza.getSize());
                                                System.out.println("Toppings Details => " +
                                                        "Topping Name: " + pizza.getTopping().getToppingName() +
                                                        ", Spice Level: " + pizza.getTopping().getSpiceLevel() +
                                                        ", Description: " + pizza.getTopping().getDescription());
                                                System.out.println("Pizza Base Details => " +
                                                        "Base Name: " + pizza.getPizzaBase().getBaseName() +
                                                        ", Type: " + pizza.getPizzaBase().getBaseType() +
                                                        ", Description: " + pizza.getPizzaBase().getDescription());
                                            }
                                        } else {
                                            System.out.println("No pizzas in this order.");
                                        }
                                    }
                                } else {
                                    System.out.println("No orders found for this customer.");
                                }

                                break;

                            case 5:
                                pizzaService.searchPizza();
                                break;
                            case 6:
                                System.out.println("Exiting Customer Console");
                                return;
                            default:
                                System.out.println("Invalid option. Please try again!!!");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("!!!InputMismatchException!!!");
                        scanner.nextLine();
                    }
                }
            } else {
                System.out.println("Invalid input format for customer details. Exiting Customer Console.");
            }
        } else {
            System.out.println("Invalid input format for address. Exiting Customer Console.");
        }
    }
}

interface PizzaInterface {
    Pizza getPizzaBySize(String size) throws PizzaNotFoundException;
    Pizza getPizzaById(int pizzaId) throws PizzaNotFoundException;
    Pizza getPizzaByName(String pizzaName) throws PizzaNotFoundException;

    Pizza orderNewPizza(Pizza pizza, Customer customer);
    List<Pizza> getAllPizzas();
    Pizza updatePrice(Pizza pizza, double newPrice);
    void deletePizza(Pizza pizza);
    Pizza addNewPizza(Pizza pizza);
}
