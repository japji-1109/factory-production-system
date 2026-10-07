import exceptions.ProductNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class FactoryManager {

    private List<Product> products;
    private List<Machine> machines;
    private List<Operator> operators;
    private List<ProductionOrder> orders;
    public FactoryManager() {
        products = new ArrayList<>();
        machines = new ArrayList<>();
        operators = new ArrayList<>();
        orders = new ArrayList<>();
    }
    public void addProduct(Product product) {
        products.add(product);
    }
    public void addMachine(Machine machine) {
        machines.add(machine);
    }
    public void addOperator(Operator operator) {
        operators.add(operator);
    }
    public void addOrder(ProductionOrder order) {
        orders.add(order);
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public List<Operator> getOperators() {
        return operators;
    }

    public List<ProductionOrder> getOrders() {
        return orders;
    }

    public Product findProductById(int productId)
            throws ProductNotFoundException {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        throw new ProductNotFoundException(
                "Product with ID " + productId + " not found."
        );
    }

    public ProductionOrder findOrderById(int orderId) {

        for (ProductionOrder order : orders) {

            if (order.getOrderId() == orderId) {
                return order;
            }
        }

        return null;
    }

    public void displayProducts() {

        System.out.println("\n===== PRODUCTS =====");

        for (Product product : products) {
            System.out.println(product);
        }
    }
    public void displayMachines() {

        System.out.println("\n===== MACHINES =====");

        for (Machine machine : machines) {
            System.out.println(machine);
        }
    }

    public void displayOperators() {

        System.out.println("\n===== OPERATORS =====");

        for (Operator operator : operators) {
            System.out.println(operator);
        }
    }
    public void displayOrders() {

        System.out.println("\n===== PRODUCTION ORDERS =====");

        for (ProductionOrder order : orders) {
            System.out.println(order);
        }
    }

    public void productionSummary() {

    int pending = 0;
    int inProgress = 0;
    int completed = 0;
    int cancelled = 0;

    for (ProductionOrder order : orders) {

        if (order.getStatus() == OrderStatus.PENDING) {
            pending++;
        } else if (order.getStatus() == OrderStatus.IN_PROGRESS) {
            inProgress++;
        } else if (order.getStatus() == OrderStatus.COMPLETED) {
            completed++;
        } else if (order.getStatus() == OrderStatus.CANCELLED) {
            cancelled++;
        }
    }

    System.out.println("\n===== PRODUCTION SUMMARY =====");
    System.out.println("Total Products: " + products.size());
    System.out.println("Total Machines: " + machines.size());
    System.out.println("Total Operators: " + operators.size());
    System.out.println("Total Orders: " + orders.size());
    System.out.println("Pending Orders: " + pending);
    System.out.println("In Progress Orders: " + inProgress);
    System.out.println("Completed Orders: " + completed);
    System.out.println("Cancelled Orders: " + cancelled);
}
}