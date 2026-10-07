import exceptions.InvalidQuantityException;
import exceptions.MachineNotAvailableException;
import exceptions.ProductNotFoundException;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        try {

            FactoryManager manager = new FactoryManager();

            Product product = new Product(
                    101,
                    "Gear Box",
                    "Automobile Parts"
            );

            Machine machine = new Machine(
                    201,
                    "CNC Machine"
            );

            Operator operator = new Operator(
                    301,
                    "Rahul",
                    "CNC Operation"
            );

            manager.addProduct(product);
            manager.addMachine(machine);
            manager.addOperator(operator);

            ProductionOrder order = new ProductionOrder(
                    1001,
                    product,
                    500,
                    "HIGH",
                    LocalDate.now(),
                    LocalDate.now().plusDays(10)
            );

            order.assignMachine(machine);
            order.assignOperator(operator);

            manager.addOrder(order);

            manager.displayProducts();
            manager.displayMachines();
            manager.displayOperators();
            manager.displayOrders();

            System.out.println("\n===== SEARCH PRODUCT =====");

            Product foundProduct = manager.findProductById(101);
            System.out.println(foundProduct);

            System.out.println("\n===== STATUS UPDATE =====");

            System.out.println("Before: " + order.getStatus());

            order.updateStatus(OrderStatus.IN_PROGRESS);

            System.out.println("After: " + order.getStatus());

            order.updateStatus(OrderStatus.COMPLETED);

            System.out.println("Final Status: " + order.getStatus());
            System.out.println("Machine Available: " + machine.isAvailable());
            manager.productionSummary();

        } catch (InvalidQuantityException |
                 MachineNotAvailableException |
                 ProductNotFoundException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}