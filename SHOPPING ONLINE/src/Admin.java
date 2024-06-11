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
        try (BufferedReader reader = new BufferedReader(new FileReader(file_))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(" ");
                    if (parts.length == 12) {
                        try {
                            int id = Integer.parseInt(parts[1]);
                            String name = parts[3];
                            String description = parts[5];
                            double price = Double.parseDouble(parts[7]);
                            int quantity = Integer.parseInt(parts[9]);
                            String category = parts[11];
                            Product product = new Product(id, name, description, price, quantity, category);
                            inventory.addProduct(product);
                        } catch (NumberFormatException e) {
                            System.err.println("Error parsing product data: " + line);
                        }
                    } else {
                        System.err.println("Invalid product data: " + line);
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void viewProduct() {
        StringBuilder inventoryInfo = new StringBuilder("Inventory:\n");
        for (Product product : inventory.getProducts()) {
            inventoryInfo.append(product.displayInfo()).append("\n");
        }

        JTextArea textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        textArea.setText(inventoryInfo.toString());

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JOptionPane.showMessageDialog(null, scrollPane, "View Inventory", JOptionPane.PLAIN_MESSAGE);
    }
}
