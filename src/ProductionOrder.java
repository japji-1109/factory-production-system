import exceptions.InvalidQuantityException;
import exceptions.MachineNotAvailableException;
import java.time.LocalDate;

public class ProductionOrder {

    private int orderId;
    private Product product;
    private int quantity;
    private String priority;
    private Machine machine;
    private Operator operator;
    private LocalDate orderDate;
    private LocalDate dueDate;
    private OrderStatus status;

    public ProductionOrder(
            int orderId,
            Product product,
            int quantity,
            String priority,
            LocalDate orderDate,
            LocalDate dueDate)
            throws InvalidQuantityException {

        if (quantity <= 0) {
            throw new InvalidQuantityException(
                    "Quantity must be greater than 0"
            );
        }

        this.orderId = orderId;
        this.product = product;
        this.quantity = quantity;
        this.priority = priority;
        this.orderDate = orderDate;
        this.dueDate = dueDate;
        this.status = OrderStatus.PENDING;
    }
    public int getOrderId() {
        return orderId;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPriority() {
        return priority;
    }

    public Machine getMachine() {
        return machine;
    }

    public Operator getOperator() {
        return operator;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public OrderStatus getStatus() {
        return status;
    }
    public void assignMachine(Machine machine)
            throws MachineNotAvailableException {

        if (!machine.isAvailable()) {

            throw new MachineNotAvailableException(
                    "Machine " + machine.getMachineName()
                    + " is currently not available."
            );
        }

        this.machine = machine;
        machine.setAvailable(false);
    }
    public void assignOperator(Operator operator) {
        this.operator = operator;
    }

    public void updateStatus(OrderStatus status) {

        this.status = status;

        if (status == OrderStatus.COMPLETED ||
                status == OrderStatus.CANCELLED) {

            if (machine != null) {
                machine.setAvailable(true);
            }
        }
    }
    public String toString() {

        return "Order ID: " + orderId +
                ", Product: " + product.getProductName() +
                ", Quantity: " + quantity +
                ", Priority: " + priority +
                ", Machine: " +
                (machine != null
                        ? machine.getMachineName()
                        : "Not Assigned") +
                ", Operator: " +
                (operator != null
                        ? operator.getOperatorName()
                        : "Not Assigned") +
                ", Order Date: " + orderDate +
                ", Due Date: " + dueDate +
                ", Status: " + status;
    }
}