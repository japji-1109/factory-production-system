import exceptions.InvalidQuantityException;
import exceptions.MachineNotAvailableException;
import exceptions.ProductNotFoundException;
import java.awt.*;
import java.time.LocalDate;
import javax.swing.*;

public class FactoryGUI extends JFrame {

    private FactoryManager manager;

    private JTextField productIdField;
    private JTextField productNameField;
    private JTextField categoryField;

    private JTextField machineIdField;
    private JTextField machineNameField;

    private JTextField operatorIdField;
    private JTextField operatorNameField;
    private JTextField skillField;

    private JTextField orderIdField;
    private JTextField orderProductIdField;
    private JTextField quantityField;
    private JTextField dueDateField;

    private JComboBox<String> priorityBox;
    private JComboBox<Integer> orderBox;
    private JComboBox<Integer> machineBox;
    private JComboBox<Integer> operatorBox;

    private JTextArea outputArea;

    public FactoryGUI() {

        manager = FileManager.loadData();

        setTitle("Factory Production Order System");
        setSize(950, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createGUI();
        refreshAssignmentBoxes();
    }

    private void createGUI() {

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        JLabel title = new JLabel(
                "Factory Production Order System",
                SwingConstants.CENTER
        );

        title.setFont(new Font("Arial", Font.BOLD, 24));

        mainPanel.add(title, BorderLayout.NORTH);

        JTabbedPane tabs = new JTabbedPane();

        tabs.addTab("Products", createProductPanel());
        tabs.addTab("Machines", createMachinePanel());
        tabs.addTab("Operators", createOperatorPanel());
        tabs.addTab("Orders", createOrderPanel());
        tabs.addTab("Assignment", createAssignmentPanel());

        mainPanel.add(tabs, BorderLayout.CENTER);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setPreferredSize(new Dimension(900, 190));

        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    private JPanel createProductPanel() {

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        productIdField = new JTextField(15);
        productNameField = new JTextField(15);
        categoryField = new JTextField(15);

        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Product ID:"), gbc);

        gbc.gridx = 1;
        panel.add(productIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Product Name:"), gbc);

        gbc.gridx = 1;
        panel.add(productNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Category:"), gbc);

        gbc.gridx = 1;
        panel.add(categoryField, gbc);

        JButton addButton = new JButton("Add Product");
        JButton showButton = new JButton("Show Products");
        JButton searchButton = new JButton("Search Product");

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(addButton, gbc);

        gbc.gridx = 1;
        panel.add(showButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(searchButton, gbc);

        addButton.addActionListener(e -> addProduct());
        showButton.addActionListener(e -> showProducts());
        searchButton.addActionListener(e -> searchProduct());

        return panel;
    }

    private JPanel createMachinePanel() {

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        machineIdField = new JTextField(15);
        machineNameField = new JTextField(15);

        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Machine ID:"), gbc);

        gbc.gridx = 1;
        panel.add(machineIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Machine Name:"), gbc);

        gbc.gridx = 1;
        panel.add(machineNameField, gbc);

        JButton addButton = new JButton("Add Machine");
        JButton showButton = new JButton("Show Machines");

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(addButton, gbc);

        gbc.gridx = 1;
        panel.add(showButton, gbc);

        addButton.addActionListener(e -> addMachine());
        showButton.addActionListener(e -> showMachines());

        return panel;
    }

    private JPanel createOperatorPanel() {

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        operatorIdField = new JTextField(15);
        operatorNameField = new JTextField(15);
        skillField = new JTextField(15);

        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Operator ID:"), gbc);

        gbc.gridx = 1;
        panel.add(operatorIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Operator Name:"), gbc);

        gbc.gridx = 1;
        panel.add(operatorNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Skill:"), gbc);

        gbc.gridx = 1;
        panel.add(skillField, gbc);

        JButton addButton = new JButton("Add Operator");
        JButton showButton = new JButton("Show Operators");

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(addButton, gbc);

        gbc.gridx = 1;
        panel.add(showButton, gbc);

        addButton.addActionListener(e -> addOperator());
        showButton.addActionListener(e -> showOperators());

        return panel;
    }

    private JPanel createOrderPanel() {

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        orderIdField = new JTextField(15);
        orderProductIdField = new JTextField(15);
        quantityField = new JTextField(15);
        dueDateField = new JTextField(15);

        priorityBox = new JComboBox<>(
                new String[]{"LOW", "MEDIUM", "HIGH"}
        );

        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Order ID:"), gbc);

        gbc.gridx = 1;
        panel.add(orderIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Product ID:"), gbc);

        gbc.gridx = 1;
        panel.add(orderProductIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Quantity:"), gbc);

        gbc.gridx = 1;
        panel.add(quantityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Priority:"), gbc);

        gbc.gridx = 1;
        panel.add(priorityBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Due Date:"), gbc);

        gbc.gridx = 1;
        panel.add(dueDateField, gbc);

        JButton createButton = new JButton("Create Order");
        JButton showButton = new JButton("Show Orders");
        JButton searchButton = new JButton("Search Order");
        JButton startButton = new JButton("Start Order");
        JButton completeButton = new JButton("Complete Order");
        JButton cancelButton = new JButton("Cancel Order");
        JButton summaryButton = new JButton("Summary");
        JButton saveButton = new JButton("Save Data");
        JButton loadButton = new JButton("Load Data");

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(createButton, gbc);

        gbc.gridx = 1;
        panel.add(showButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(searchButton, gbc);

        gbc.gridx = 1;
        panel.add(startButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(completeButton, gbc);

        gbc.gridx = 1;
        panel.add(cancelButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(summaryButton, gbc);

        gbc.gridx = 1;
        panel.add(saveButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        panel.add(loadButton, gbc);

        createButton.addActionListener(e -> createOrder());
        showButton.addActionListener(e -> showOrders());
        searchButton.addActionListener(e -> searchOrder());
        startButton.addActionListener(e -> startOrder());
        completeButton.addActionListener(e -> completeOrder());
        cancelButton.addActionListener(e -> cancelOrder());
        summaryButton.addActionListener(e -> showSummary());
        saveButton.addActionListener(e -> saveData());
        loadButton.addActionListener(e -> loadData());

        return panel;
    }

    private JPanel createAssignmentPanel() {

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        orderBox = new JComboBox<>();
        machineBox = new JComboBox<>();
        operatorBox = new JComboBox<>();

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Order:"), gbc);

        gbc.gridx = 1;
        panel.add(orderBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Machine:"), gbc);

        gbc.gridx = 1;
        panel.add(machineBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Operator:"), gbc);

        gbc.gridx = 1;
        panel.add(operatorBox, gbc);

        JButton assignButton = new JButton("Assign Machine & Operator");
        JButton refreshButton = new JButton("Refresh");
        JButton showButton = new JButton("Show Assignment");

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(assignButton, gbc);

        gbc.gridx = 1;
        panel.add(refreshButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(showButton, gbc);

        assignButton.addActionListener(e -> assignMachineAndOperator());
        refreshButton.addActionListener(e -> refreshAssignmentBoxes());
        showButton.addActionListener(e -> showAssignment());

        return panel;
    }

    private void addProduct() {

        try {

            int id = Integer.parseInt(productIdField.getText());

            for (Product product : manager.getProducts()) {

                if (product.getProductId() == id) {
                    showError("Product ID already exists.");
                    return;
                }
            }

            String name = productNameField.getText().trim();
            String category = categoryField.getText().trim();

            if (name.isEmpty() || category.isEmpty()) {
                showError("Enter all product details.");
                return;
            }

            Product product = new Product(id, name, category);

            manager.addProduct(product);

            outputArea.setText(
                    "Product added successfully.\n\n" + product
            );

        } catch (NumberFormatException e) {

            showError("Product ID must be a number.");
        }
    }

    private void showProducts() {

        outputArea.setText("");

        if (manager.getProducts().isEmpty()) {
            outputArea.setText("No products found.");
            return;
        }

        for (Product product : manager.getProducts()) {
            outputArea.append(product + "\n");
        }
    }

    private void searchProduct() {

        try {

            int id = Integer.parseInt(productIdField.getText());

            Product product = manager.findProductById(id);

            outputArea.setText(product.toString());

        } catch (NumberFormatException e) {

            showError("Enter a valid product ID.");

        } catch (ProductNotFoundException e) {

            showError(e.getMessage());
        }
    }

    private void addMachine() {

        try {

            int id = Integer.parseInt(machineIdField.getText());

            for (Machine machine : manager.getMachines()) {

                if (machine.getMachineId() == id) {
                    showError("Machine ID already exists.");
                    return;
                }
            }

            String name = machineNameField.getText().trim();

            if (name.isEmpty()) {
                showError("Enter machine name.");
                return;
            }

            Machine machine = new Machine(id, name);

            manager.addMachine(machine);

            refreshAssignmentBoxes();

            outputArea.setText(
                    "Machine added successfully.\n\n" + machine
            );

        } catch (NumberFormatException e) {

            showError("Machine ID must be a number.");
        }
    }

    private void showMachines() {

        outputArea.setText("");

        if (manager.getMachines().isEmpty()) {
            outputArea.setText("No machines found.");
            return;
        }

        for (Machine machine : manager.getMachines()) {
            outputArea.append(machine + "\n");
        }
    }

    private void addOperator() {

        try {

            int id = Integer.parseInt(operatorIdField.getText());

            for (Operator operator : manager.getOperators()) {

                if (operator.getOperatorId() == id) {
                    showError("Operator ID already exists.");
                    return;
                }
            }

            String name = operatorNameField.getText().trim();
            String skill = skillField.getText().trim();

            if (name.isEmpty() || skill.isEmpty()) {
                showError("Enter all operator details.");
                return;
            }

            Operator operator =
                    new Operator(id, name, skill);

            manager.addOperator(operator);

            refreshAssignmentBoxes();

            outputArea.setText(
                    "Operator added successfully.\n\n" + operator
            );

        } catch (NumberFormatException e) {

            showError("Operator ID must be a number.");
        }
    }

    private void showOperators() {

        outputArea.setText("");

        if (manager.getOperators().isEmpty()) {
            outputArea.setText("No operators found.");
            return;
        }

        for (Operator operator : manager.getOperators()) {
            outputArea.append(operator + "\n");
        }
    }

    private void createOrder() {

        try {

            int orderId =
                    Integer.parseInt(orderIdField.getText());

            for (ProductionOrder order : manager.getOrders()) {

                if (order.getOrderId() == orderId) {
                    showError("Order ID already exists.");
                    return;
                }
            }

            int productId =
                    Integer.parseInt(orderProductIdField.getText());

            int quantity =
                    Integer.parseInt(quantityField.getText());

            String priority =
                    (String) priorityBox.getSelectedItem();

            LocalDate dueDate =
                    LocalDate.parse(dueDateField.getText());

            Product product =
                    manager.findProductById(productId);

            ProductionOrder order =
                    new ProductionOrder(
                            orderId,
                            product,
                            quantity,
                            priority,
                            LocalDate.now(),
                            dueDate
                    );

            manager.addOrder(order);

            refreshAssignmentBoxes();

            outputArea.setText(
                    "Order created successfully.\n\n" + order
            );

        } catch (NumberFormatException e) {

            showError("Enter valid numbers.");

        } catch (ProductNotFoundException e) {

            showError(e.getMessage());

        } catch (InvalidQuantityException e) {

            showError(e.getMessage());

        } catch (Exception e) {

            showError("Enter the date in YYYY-MM-DD format.");
        }
    }

    private void showOrders() {

        outputArea.setText("");

        if (manager.getOrders().isEmpty()) {
            outputArea.setText("No orders found.");
            return;
        }

        for (ProductionOrder order : manager.getOrders()) {
            outputArea.append(order + "\n\n");
        }
    }

    private void searchOrder() {

        try {

            int id =
                    Integer.parseInt(orderIdField.getText());

            ProductionOrder order =
                    manager.findOrderById(id);

            if (order == null) {
                showError("Order not found.");
                return;
            }

            outputArea.setText(order.toString());

        } catch (NumberFormatException e) {

            showError("Enter a valid order ID.");
        }
    }

    private void assignMachineAndOperator() {

        try {

            if (orderBox.getSelectedItem() == null) {
                showError("No order available.");
                return;
            }

            if (machineBox.getSelectedItem() == null) {
                showError("No machine available.");
                return;
            }

            if (operatorBox.getSelectedItem() == null) {
                showError("No operator available.");
                return;
            }

            int orderId =
                    (Integer) orderBox.getSelectedItem();

            int machineId =
                    (Integer) machineBox.getSelectedItem();

            int operatorId =
                    (Integer) operatorBox.getSelectedItem();

            ProductionOrder order =
                    manager.findOrderById(orderId);

            if (order == null) {
                showError("Order not found.");
                return;
            }

            if (order.getMachine() != null) {
                showError("A machine is already assigned to this order.");
                return;
            }

            Machine selectedMachine = null;

            for (Machine machine : manager.getMachines()) {

                if (machine.getMachineId() == machineId) {
                    selectedMachine = machine;
                    break;
                }
            }

            Operator selectedOperator = null;

            for (Operator operator : manager.getOperators()) {

                if (operator.getOperatorId() == operatorId) {
                    selectedOperator = operator;
                    break;
                }
            }

            if (selectedMachine == null) {
                showError("Machine not found.");
                return;
            }

            if (selectedOperator == null) {
                showError("Operator not found.");
                return;
            }

            order.assignMachine(selectedMachine);
            order.assignOperator(selectedOperator);

            outputArea.setText(
                    "Machine and operator assigned successfully.\n\n"
                    + order
            );

            refreshAssignmentBoxes();

        } catch (MachineNotAvailableException e) {

            showError(e.getMessage());
        }
    }

    private void showAssignment() {

        if (orderBox.getSelectedItem() == null) {
            showError("No order available.");
            return;
        }

        int orderId =
                (Integer) orderBox.getSelectedItem();

        ProductionOrder order =
                manager.findOrderById(orderId);

        if (order == null) {
            showError("Order not found.");
            return;
        }

        String machine;

        if (order.getMachine() == null) {
            machine = "Not Assigned";
        } else {
            machine = order.getMachine().getMachineName();
        }

        String operator;

        if (order.getOperator() == null) {
            operator = "Not Assigned";
        } else {
            operator = order.getOperator().getOperatorName();
        }

        outputArea.setText(
                "ORDER ASSIGNMENT\n\n" +
                "Order ID: " + order.getOrderId() + "\n" +
                "Product: " + order.getProduct().getProductName() + "\n" +
                "Machine: " + machine + "\n" +
                "Operator: " + operator + "\n" +
                "Status: " + order.getStatus()
        );
    }

    private void startOrder() {

        try {

            int id =
                    Integer.parseInt(orderIdField.getText());

            ProductionOrder order =
                    manager.findOrderById(id);

            if (order == null) {
                showError("Order not found.");
                return;
            }

            if (order.getMachine() == null) {
                showError("Assign a machine before starting the order.");
                return;
            }

            if (order.getOperator() == null) {
                showError("Assign an operator before starting the order.");
                return;
            }

            if (order.getStatus() == OrderStatus.COMPLETED) {
                showError("Completed order cannot be started.");
                return;
            }

            if (order.getStatus() == OrderStatus.CANCELLED) {
                showError("Cancelled order cannot be started.");
                return;
            }

            order.updateStatus(OrderStatus.IN_PROGRESS);

            outputArea.setText(
                    "Order started successfully.\n\n" + order
            );

        } catch (NumberFormatException e) {

            showError("Enter a valid order ID.");
        }
    }

    private void completeOrder() {

        try {

            int id =
                    Integer.parseInt(orderIdField.getText());

            ProductionOrder order =
                    manager.findOrderById(id);

            if (order == null) {
                showError("Order not found.");
                return;
            }

            if (order.getStatus() != OrderStatus.IN_PROGRESS) {
                showError("Only an order in progress can be completed.");
                return;
            }

            order.updateStatus(OrderStatus.COMPLETED);

            outputArea.setText(
                    "Order completed successfully.\n\n" + order
            );

            refreshAssignmentBoxes();

        } catch (NumberFormatException e) {

            showError("Enter a valid order ID.");
        }
    }

    private void cancelOrder() {

        try {

            int id =
                    Integer.parseInt(orderIdField.getText());

            ProductionOrder order =
                    manager.findOrderById(id);

            if (order == null) {
                showError("Order not found.");
                return;
            }

            if (order.getStatus() == OrderStatus.COMPLETED) {
                showError("Completed order cannot be cancelled.");
                return;
            }

            order.updateStatus(OrderStatus.CANCELLED);

            outputArea.setText(
                    "Order cancelled successfully.\n\n" + order
            );

            refreshAssignmentBoxes();

        } catch (NumberFormatException e) {

            showError("Enter a valid order ID.");
        }
    }

    private void showSummary() {

        int pending = 0;
        int inProgress = 0;
        int completed = 0;
        int cancelled = 0;

        for (ProductionOrder order : manager.getOrders()) {

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

        String summary =
                "PRODUCTION SUMMARY\n\n" +
                "Total Products: " +
                manager.getProducts().size() + "\n" +
                "Total Machines: " +
                manager.getMachines().size() + "\n" +
                "Total Operators: " +
                manager.getOperators().size() + "\n" +
                "Total Orders: " +
                manager.getOrders().size() + "\n\n" +
                "Pending Orders: " + pending + "\n" +
                "In Progress Orders: " + inProgress + "\n" +
                "Completed Orders: " + completed + "\n" +
                "Cancelled Orders: " + cancelled;

        outputArea.setText(summary);
    }

    private void saveData() {

        FileManager.saveData(manager);

        outputArea.setText("Data saved successfully.");
    }

    private void loadData() {

        manager = FileManager.loadData();

        refreshAssignmentBoxes();

        outputArea.setText("Data loaded successfully.");
    }

    private void refreshAssignmentBoxes() {

        if (orderBox == null ||
                machineBox == null ||
                operatorBox == null) {
            return;
        }

        orderBox.removeAllItems();
        machineBox.removeAllItems();
        operatorBox.removeAllItems();

        for (ProductionOrder order : manager.getOrders()) {
            orderBox.addItem(order.getOrderId());
        }

        for (Machine machine : manager.getMachines()) {

            if (machine.isAvailable()) {
                machineBox.addItem(machine.getMachineId());
            }
        }

        for (Operator operator : manager.getOperators()) {
            operatorBox.addItem(operator.getOperatorId());
        }
    }

    private void showError(String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            FactoryGUI gui = new FactoryGUI();

            gui.setVisible(true);
        });
    }
}