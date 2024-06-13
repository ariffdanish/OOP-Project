import java.io.*;
import javax.swing.*;
import java.util.*;

class Admin {
    private int aId;
    private String aName;
    private String aEmail;
    private String aPhone;
    private ArrayList<Product> list;
    private Inventory inventory;
    private static File file_ = new File("inventory.txt");

    public Admin() {
        list = new ArrayList<>();
        inventory = new Inventory(list);
        loadProductsFromFile();
    }

    public Admin(int id, String n, String e, String p) {
        this();
        aId = id;
        aName = n;
        aEmail = e;
        aPhone = p;
    }

    public void setId(int id) {
        aId = id;
    }

    public void setName(String n) {
        aName = n;
    }

    public void setEmail(String e) {
        aEmail = e;
    }

    public void setPhone(String p) {
        aPhone = p;
    }

    public int getId() {
        return aId;
    }

    public String getName() {
        return aName;
    }

    public String getEmail() {
        return aEmail;
    }

    public String getPhone() {
        return aPhone;
    }

    public String displayAdmin() {
        return "Admin Information \n\nID: " + getId() + "\nName: " + getName() + "\nEmail: " + getEmail() + "\nPhone: " + getPhone();
    }

    public void addProduct(Product p) {
        inventory.addProduct(p);
        try (FileWriter writer = new FileWriter(file_, true)) {
            writer.write(p.displayInfo() + "\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error writing to file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateInventory(int productId) {
        Product product = inventory.findProductById(productId);
    
        if (product != null) {
            int id = product.getId();
            String name = product.getName();
            String description = product.getDescription();
            double price = product.getPrice();
            int quantity = product.getQuantity();
            String category = product.getCategory();
    
            while (true) {
                String idInput = JOptionPane.showInputDialog("Enter the product ID (default: " + id + "):");
                if (idInput == null || idInput.trim().isEmpty()) {
                    break;
                }
                try {
                    id = Integer.parseInt(idInput);
                    break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number for product ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
    
            while (true) {
                String nameInput = JOptionPane.showInputDialog("Enter the product Name (default: " + name + ")(No Spacing):");
                if (nameInput == null || nameInput.trim().isEmpty()) {
                    break;  
                }
                if (!nameInput.trim().isEmpty()) {
                    name = nameInput;
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Product name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
    
            while (true) {
                String descriptionInput = JOptionPane.showInputDialog("Enter the product Description (default: " + description + ")(No Spacing):");
                if (descriptionInput == null || descriptionInput.trim().isEmpty()) {
                    break; 
                }
                if (!descriptionInput.trim().isEmpty()) {
                    description = descriptionInput;
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Product description cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
    
            while (true) {
                String priceInput = JOptionPane.showInputDialog("Enter the product Price (default: " + price + "):");
                if (priceInput == null || priceInput.trim().isEmpty()) {
                    break;  
                }
                try {
                    price = Double.parseDouble(priceInput);
                    break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number for product price.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
    
            while (true) {
                String quantityInput = JOptionPane.showInputDialog("Enter the product Quantity (default: " + quantity + "):");
                if (quantityInput == null || quantityInput.trim().isEmpty()) {
                    break;  
                }
                try {
                    quantity = Integer.parseInt(quantityInput);
                    break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number for product quantity.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            product.setId(id);
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setQuantity(quantity);
            updateInventoryFile();
        } else {
            JOptionPane.showMessageDialog(null, "Product not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    public void removeProduct(int productId) {
        Product product = inventory.findProductById(productId);
        if (product != null) {
            inventory.removeProduct(product);
            updateInventoryFile();
        } else {
            JOptionPane.showMessageDialog(null, "Product not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateInventoryFile() {
        try (FileWriter writer = new FileWriter(file_)) {
            for (Product product : inventory.getProducts()) {
                writer.write(product.displayInfo() + "\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error writing to file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadProductsFromFile() {
        try (Scanner scanner = new Scanner(file_)) {
            while (scanner.hasNext()) {
                try {
                    scanner.next();
                        int id = scanner.nextInt();
                        scanner.next(); 
                        String name = scanner.next();
                        scanner.next(); 
                        String description = scanner.next();
                        scanner.next(); 
                        double price = scanner.nextDouble();
                        scanner.next(); 
                        int quantity = scanner.nextInt();
                        scanner.next(); 
                        String category = scanner.next();
                        Product product = new Product(id, name, description, price, quantity, category);
                        inventory.addProduct(product);
                 
                } catch (NoSuchElementException e) {
                    System.err.println("Error parsing product data: " + e.getMessage());
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File not found: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void viewProduct() {
        StringBuilder inventoryInfo = new StringBuilder("Inventory:\n");
        for (Product product : inventory.getProducts()) {
            inventoryInfo.append(product.displayInfo()).append("\n");
        }

        JTextArea textArea = new JTextArea(10, 60);
        textArea.setEditable(false);
        textArea.setText(inventoryInfo.toString());

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JOptionPane.showMessageDialog(null, scrollPane, "View Inventory", JOptionPane.PLAIN_MESSAGE);
    }
}
