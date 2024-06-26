import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SHOPPING_Online {
    private static Admin[] admins = {
        new Admin(123, "JOHN", "John@gmail.com", "01123456"),
        new Admin(356, "BRANDON", "Brandon@gmail.com", "08765678")
    };

    private static Customer[] customers = {
        new Customer(678, "IMAN", "Bolt@gmail.com", "No5, Jalan meranti,Taman meranti,4567 Johor Bahru"),
        new Customer(556, "KANE", "Jazz2@gmail.com", "No10, Jalan angkasa,Taman angkasa,4567 Johor Bahru")
    };

    private static Category[] category = {
        new Category(100,"Food", "All the food product will in this category"),
        new Category(200,"Apparel", "All the cloth product will in this category"),
        new Category(300,"Accessory", "All the Accessories product will in this category")
    };

    public static void main(String[] args) {
        
        boolean isRunning = true;

        while (isRunning) {
            String username = JOptionPane.showInputDialog(null, "       ENTER REGISTERED USERNAME : \n         Please enter correct username !", "WELCOME TO THE NOVARIA SHOP", JOptionPane.PLAIN_MESSAGE);

            if (username == null) {
                System.exit(0);
            } else if (!username.trim().isEmpty()) {
                if (isValidAdmin(username)) {
                    for (Admin admin : admins) {
                        if (admin.getName().equals(username)) {
                            JOptionPane.showMessageDialog(null, "Welcome, " + username + "!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                            showAdminMainMenu(username, admin);
                        }
                    }
                } else if (isValidCustomer(username)) {
                    JOptionPane.showMessageDialog(null, "Welcome, " + username + "!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                    showCustomerMainMenu(username);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Username cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static boolean isValidAdmin(String username) {
        for (Admin admin : admins) {
            if (admin.getName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValidCustomer(String username) {
        for (Customer customer : customers) {
            if (customer.getName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private static void showAdminMainMenu(String username, Admin admin) {
        boolean isAdminRunning = true;

        while (isAdminRunning) {
            Object[] options = {"Personal Info", "Inventory", "Logout", "Exit"};
            int choice = JOptionPane.showOptionDialog(null, "Select an option:", "Online Shopping - Admin",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (choice == JOptionPane.CLOSED_OPTION) {
                System.exit(0);
            }

            switch (choice) {
                case 0:
                    showAdminPersonalInfo(username);
                    break;
                case 1:
                    showInventory(username, admin);
                    break;
                case 2:
                    isAdminRunning = false;
                    break;
                case 3:
                    System.exit(0);
                default:
                    JOptionPane.showMessageDialog(null, "No option selected.", "No Selection", JOptionPane.WARNING_MESSAGE);
                    break;
            }
        }
    }

    private static void showCustomerMainMenu(String username) {
    boolean isCustomerRunning = true;
    shoppingCart cart = new shoppingCart();

    while (isCustomerRunning) {
        Object[] options = {"Personal Info", "Browse Products", "View Cart","Payment", "Logout", "Exit"};
        int choice = JOptionPane.showOptionDialog(null, "Select an option:", "Online Shopping - Customer",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == JOptionPane.CLOSED_OPTION) {
            System.exit(0);
        }

        switch (choice) {
            case 0:
                showCustomerPersonalInfo(username);
                break;
            case 1:
                browseProducts(cart);
                break;
            case 2:
                viewCart(cart);
                break;
            case 3:
                payment(cart);
                break;
            case 4:
                isCustomerRunning = false;
                break;
            case 5:
                System.exit(0);
            default:
                JOptionPane.showMessageDialog(null, "No option selected.", "No Selection", JOptionPane.WARNING_MESSAGE);
                break;
        }
    }
}

private static void browseProducts(shoppingCart cart) {
    Admin a = new Admin();
    // Read products from inventory file
    List<Product> products = a.getInventory();

    // Display products to customer
    StringBuilder productList = new StringBuilder();
    for (int i = 0; i < products.size(); i++) {
        productList.append(i + 1)
                   .append(". ")
                   .append(products.get(i).getName())
                   .append(" - ")
                   .append(products.get(i).getDescription())
                   .append(" - $")
                   .append(products.get(i).getPrice())
                   .append("\n");
    }

    if (products.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No products available.", "Empty Inventory", JOptionPane.WARNING_MESSAGE);
        return;
    }

    String input = JOptionPane.showInputDialog(null, "Select a product to add to cart:\n" + productList.toString(), "Browse Products", JOptionPane.PLAIN_MESSAGE);
    if (input == null || input.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "No product selected.", "No Selection", JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        int selection = Integer.parseInt(input.trim()) - 1;
        if (selection >= 0 && selection < products.size()) {
            Product selectedProduct = products.get(selection);
            cart.addProduct(selectedProduct);
            JOptionPane.showMessageDialog(null, "Product added to cart: " + selectedProduct.getName(), "Product Added", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid selection. Please choose a number between 1 and " + products.size() + ".", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}


private static void viewCart(shoppingCart cart) {
    if (cart.getTotalProducts() == 0) {
        JOptionPane.showMessageDialog(null, "Your cart is empty.", "Cart Empty", JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    JPanel panel = new JPanel(new GridLayout(cart.getTotalProducts(), 1));

    Product[] products = cart.getProducts();
    for (int i = 0; i < cart.getTotalProducts(); i++) {
        String productName = products[i].getName();
        double productPrice = products[i].getPrice();

        JPanel productPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel nameLabel = new JLabel("Product: " + productName + " - $" + productPrice);
        productPanel.add(nameLabel);

        JButton removeButton = new JButton("Remove");
        int indexToRemove = i; // Capture index to remove in ActionListener

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cart.rmvProduct(products[indexToRemove]);
                panel.removeAll(); // Clear panel content
                viewCart(cart); // Refresh the cart view after removal
            }
        });
        productPanel.add(removeButton);

        panel.add(productPanel);
    }

    int option = JOptionPane.showConfirmDialog(null, panel, "View Cart", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (option == JOptionPane.OK_OPTION) {
        // User clicked OK, perform any actions if needed
    } else {
        // User clicked Cancel or closed the dialog, perform cleanup or additional actions if needed
    }
}




private static void payment(shoppingCart cart) {
    double total = cart.getCost();
    Payment[] paymentMethods = {
        new Payment("TNG"),
        new Payment("Bank Islam"),
        new Payment("RHB Bank"),
        new Payment("CIMB Bank"),
        new Payment("Public Bank")
    };
    boolean paymentRunning = true;
    while (paymentRunning) {
        Object[] options = {
            paymentMethods[0].getPaymentMethods(), 
            paymentMethods[1].getPaymentMethods(), 
            paymentMethods[2].getPaymentMethods(), 
            paymentMethods[3].getPaymentMethods(), 
            paymentMethods[4].getPaymentMethods(), 
            "Back"
        };
        int choice = JOptionPane.showOptionDialog(null, "Select an option:", "Online Shopping - Customer",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                TNG(total, cart, paymentMethods);
                paymentRunning = false;
                break;
            case 1:
                bankIslam(total, cart, paymentMethods);
                paymentRunning = false;
                break;
            case 2:
                RhbBank(total, cart, paymentMethods);
                paymentRunning = false;
                break;
            case 3:
                CimbBank(total, cart, paymentMethods);
                paymentRunning = false;
                break;
            case 4:
                PublicBank(total, cart, paymentMethods);
                paymentRunning = false;
                break;
            case 5:
                paymentRunning = false;
                break;
            default:
                paymentRunning = false;
                break;
        }
    }
}

private static void TNG(double total, shoppingCart cart, Payment[] payment) {
    payment[0].setAmount(total);
    payment[0].paymentProcess(cart);
}

private static void bankIslam(double total, shoppingCart cart, Payment[] payment) {
    payment[1].setAmount(total);
    payment[1].paymentProcess(cart);
}

private static void RhbBank(double total, shoppingCart cart, Payment[] payment) {
    payment[2].setAmount(total);
    payment[2].paymentProcess(cart);
}

private static void CimbBank(double total, shoppingCart cart, Payment[] payment) {
    payment[3].setAmount(total);
    payment[3].paymentProcess(cart);
}

private static void PublicBank(double total, shoppingCart cart, Payment[] payment) {
    payment[4].setAmount(total);
    payment[4].paymentProcess(cart);
}



    private static void showAdminPersonalInfo(String username) {
        for (Admin admin : admins) {
            if (admin.getName().equals(username)) {
                String info = admin.displayAdmin();
                JOptionPane.showMessageDialog(null, info, "Personal Info - Admin", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
    }

    private static void showCustomerPersonalInfo(String username) {
        for (Customer customer : customers) {
            if (customer.getName().equals(username)) {
                String info = "Customer Information \n\nID: " + customer.getId() + "\nName: " + customer.getName() + "\nEmail: " + customer.getEmail() + "\nAddress: " + customer.getAddress();
                JOptionPane.showMessageDialog(null, info, "Personal Info - Customer", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
    }

    private static void showInventory(String username, Admin admin) {
        boolean isInventoryRunning = true;

        while (isInventoryRunning) {
            Object[] options = {"View Inventory", "Add Product", "Update Inventory", "Remove Product", "Back"};
            int choice = JOptionPane.showOptionDialog(null, "Select an option:", "Online Shopping - Admin",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (choice == JOptionPane.CLOSED_OPTION) {
                System.exit(0);
            }

            switch (choice) {
                case 0:
                    viewInventory(admin);
                    break;
                case 1:
                    addProduct(username,admin);
                   
                    break;
                case 2:
                    updateInventory(admin);
                   
                    break;
                case 3:
                    removeProduct(admin);
                   
                    break;
                case 4:
                    isInventoryRunning = false;
                
                    break;
                default:
                    return;
            }
        }
    }

    private static void addProduct(String username, Admin admin){
        boolean isAddProductRunning = true;

        while (isAddProductRunning) {
            Object[] options = {"Add Food", "Add Apparel", "Add Accessory", "Back"};
            int choice = JOptionPane.showOptionDialog(null, "Select the category to add:", "Online Shopping - Admin",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (choice == JOptionPane.CLOSED_OPTION) {
                System.exit(0);
            }

            switch (choice) {
                case 0:
                    addFood(admin);
                   
                    break;
                case 1:
                    addCloth(admin);
                  
                    break;
                case 2:
                    addAccessories(admin);
                  
                    break;
                case 3:
                    isAddProductRunning = false;
               
                    break;
                default:
                    return;
            }
        }
    }

    private static void addFood(Admin admin) {
        int id = 0;
        String name = null;
        String description = null;
        double price = 0.0;
        int quantity = 0;
        String cg = category[0].getName();

        while (true) {
            String idInput = JOptionPane.showInputDialog("Enter the product ID:");
            if (idInput == null) {
                return;
            }
            try {
                id = Integer.parseInt(idInput);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number for product ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        while (true) {
            name = JOptionPane.showInputDialog("Enter the product Name(No Spacing):");
            if (name == null) {
                return;
            }
            if (!name.trim().isEmpty()) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Product name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        while (true) {
            description = JOptionPane.showInputDialog("Enter the product Description(No Spacing):");
            if (description == null) {
                return;
            }
            if (!description.trim().isEmpty()) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Product description cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        while (true) {
            String priceInput = JOptionPane.showInputDialog("Enter the product Price:");
            if (priceInput == null) {
                return;
            }
            try {
                price = Double.parseDouble(priceInput);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number for product price.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        while (true) {
            String quantityInput = JOptionPane.showInputDialog("Enter the product Quantity:");
            if (quantityInput == null) {
                return;
            }
            try {
                quantity = Integer.parseInt(quantityInput);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number for product quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        Product pd = new Product(id, name, description, price, quantity, cg);
        admin.addProduct(pd);
    }

    private static void addCloth(Admin admin) {
        int id = 0;
        String name = null;
        String description = null;
        double price = 0.0;
        int quantity = 0;
        String cg = category[1].getName();

        while (true) {
            String idInput = JOptionPane.showInputDialog("Enter the product ID:");
            if (idInput == null) {
                return;
            }
            try {
                id = Integer.parseInt(idInput);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number for product ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        while (true) {
            name = JOptionPane.showInputDialog("Enter the product Name(No Spacing):");
            if (name == null) {
                return;
            }
            if (!name.trim().isEmpty()) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Product name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        while (true) {
            description = JOptionPane.showInputDialog("Enter the product Description(No Spacing):");
            if (description == null) {
                return;
            }
            if (!description.trim().isEmpty()) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Product description cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        while (true) {
            String priceInput = JOptionPane.showInputDialog("Enter the product Price:");
            if (priceInput == null) {
                return;
            }
            try {
                price = Double.parseDouble(priceInput);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number for product price.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        while (true) {
            String quantityInput = JOptionPane.showInputDialog("Enter the product Quantity:");
            if (quantityInput == null) {
                return;
            }
            try {
                quantity = Integer.parseInt(quantityInput);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number for product quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        Product pd = new Product(id, name, description, price, quantity, cg);
        admin.addProduct(pd);
    }

    private static void addAccessories(Admin admin) {
        int id = 0;
        String name = null;
        String description = null;
        double price = 0.0;
        int quantity = 0;
        String cg = category[2].getName();

        while (true) {
            String idInput = JOptionPane.showInputDialog("Enter the product ID:");
            if (idInput == null) {
                return;
            }
            try {
                id = Integer.parseInt(idInput);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number for product ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        while (true) {
            name = JOptionPane.showInputDialog("Enter the product Name(No Spacing):");
            if (name == null) {
                return;
            }
            if (!name.trim().isEmpty()) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Product name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        while (true) {
            description = JOptionPane.showInputDialog("Enter the product Description(No Spacing):");
            if (description == null) {
                return;
            }
            if (!description.trim().isEmpty()) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Product description cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        while (true) {
            String priceInput = JOptionPane.showInputDialog("Enter the product Price:");
            if (priceInput == null) {
                return;
            }
            try {
                price = Double.parseDouble(priceInput);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number for product price.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        while (true) {
            String quantityInput = JOptionPane.showInputDialog("Enter the product Quantity:");
            if (quantityInput == null) {
                return;
            }
            try {
                quantity = Integer.parseInt(quantityInput);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number for product quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        Product pd = new Product(id, name, description, price, quantity, cg);
        admin.addProduct(pd);
    }

    private static void removeProduct(Admin admin) {
        int productId = 0;

        while (true) {
            String idInput = JOptionPane.showInputDialog("Enter the product ID to remove:");
            if (idInput == null) {
                return;
            }
            try {
                productId = Integer.parseInt(idInput);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number for product ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        admin.removeProduct(productId);
    }

    private static void updateInventory(Admin admin){
        int productId = 0;

        while (true) {
            String idInput = JOptionPane.showInputDialog("Enter the product ID to update the product information:");
            if (idInput == null) {
                return;
            }
            try {
                productId = Integer.parseInt(idInput);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number for product ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        admin.updateInventory(productId);
    }

    private static void viewInventory(Admin admin) {
        admin.viewProduct();
    }
}