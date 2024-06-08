import java.io.*;
import javax.swing.*;
import java.util.*;

public class Admin{
    private int aId;
    private String aName;
    private String aEmail;
    private String aPhone;
    private ArrayList<Product> list;
    private Inventory inventory = new Inventory(list);

    private static File file_=new File("inventory.txt");

    public Admin(){

    }

    public Admin(int id, String n, String e, String p){
        aId=id;
        aName=n;
        aEmail=e;
        aPhone=p;
    }

    public void setId(int id){
        aId=id;
    }

    public void setName(String n){
        aName=n;
    }

    public void setEmail(String e){
        aEmail=e;
    }

    public void setPhone(String p){
        aPhone=p;
    }

    public int getId(){
        return aId;
    }

    public String getName(){
        return aName;
    }

    public String getEmail(){
        return aEmail;
    }

    public String getPhone(){
        return aPhone;
    }

    public String displayAdmin(){

        return "Admin Information \n\nID: " + getId() + "\nName: " + getName() + "\nEmail: " + getEmail() + "\nPhone: " + getPhone();
    }

    public void addProduct(Product p) {
        inventory.addProduct(p);
        try (FileWriter writer = new FileWriter(file_, true)) {
            String productInfo = p.displayInfo();
            writer.write(productInfo+"\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error writing to file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void removeProduct(Product p) {
        inventory.removeProduct(p);
        
    }

    public void editProduct(Product p) {
        inventory.updateProduct(p);
       
    }

    public void viewProduct(){
         StringBuilder inventoryInfo = new StringBuilder("Inventory:\n");

         try (BufferedReader reader = new BufferedReader(new FileReader(file_))) {
            String line;
            while ((line = reader.readLine()) != null) {
                inventoryInfo.append(line).append("\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTextArea textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        textArea.setText(inventoryInfo.toString());

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JOptionPane.showMessageDialog(null, scrollPane, "View Inventory", JOptionPane.PLAIN_MESSAGE);
    }
    }
