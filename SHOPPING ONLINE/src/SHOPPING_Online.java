import java.util.ArrayList;
import java.util.List;

import javax.swing.*;


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
    List<Product> cart = new ArrayList<>();

    while (isCustomerRunning) {
        Object[] options = {"Personal Info", "Browse Products", "View Cart", "Logout", "Exit"};
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
                isCustomerRunning = false;
                break;
            case 4:
                System.exit(0);
            default:
                JOptionPane.showMessageDialog(null, "No option selected.", "No Selection", JOptionPane.WARNING_MESSAGE);
                break;
        }
    }
}

private static void browseProducts(List<Product> cart) {
    Admin a = new Admin();
    // Read products from inventory file
    List<Product> products = a.getInventory();


    // Display products to customer
    String productList = "";
    for (int i = 0; i < products.size(); i++) {
        productList += (i + 1) + ". " + products.get(i).getName() + " - " + products.get(i).getDescription() + " - $" + products.get(i).getPrice() + "\n";
    }

    String input = JOptionPane.showInputDialog(null, "Select a product to add to cart:\n" + productList, "Browse Products", JOptionPane.PLAIN_MESSAGE);

    if (input!= null) {
        int selection = Integer.parseInt(input) - 1;
        if (selection >= 0 && selection < products.size()) {
            Product selectedProduct = products.get(selection);
            cart.add(selectedProduct);
            JOptionPane.showMessageDialog(null, "Product added to cart: " + selectedProduct.getName(), "Product Added", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid selection.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

private static void viewCart(List<Product> cart) {
    if (cart.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Your cart is empty.", "Cart Empty", JOptionPane.INFORMATION_MESSAGE);
    } else {
        String cartList = "";
        for (Product product : cart) {
            cartList += product.getName() + " - $" + product.getPrice() + "\n";
        }
        JOptionPane.showMessageDialog(null, "Your cart:\n" + cartList, "View Cart", JOptionPane.INFORMATION_MESSAGE);
    }
}

private static List<Product> readProductsFromInventory() {
    List<Product> products = new ArrayList<>();
    products.add(new Product(1, "Apple", "Fresh apple", 1.99, 10, "Food"));
    products.add(new Product(2, "T-Shirt", "White T-Shirt", 19.99, 20, "Apparel"));
    products.add(new Product(3, "Headphones", "Wireless headphones", 49.99, 15, "Accessory"));
    return products;
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
                    showInventory(username, admin);
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

