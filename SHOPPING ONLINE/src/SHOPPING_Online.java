import javax.swing.*;
public class SHOPPING_Online {

    private static Admin[] admins = {
        new Admin(123, "john", "John@gmail.com", "01123456"),
        new Admin(356, "brandon", "Brandon@gmail.com", "08765678")
    };

    private static Customer[] customers = {
        new Customer(678, "bolt", "Bolt@gmail.com", "No5, Jalan meranti,Taman meranti,4567 Johor Bahru"),
        new Customer(556, "jazz", "Jazz2@gmail.com", "No10, Jalan angkasa,Taman angkasa,4567 Johor Bahru")
    };

    public static void main(String[] args) {
       
        String username = JOptionPane.showInputDialog(null, "Enter your username:\n(Username for admin - john)\n(Username for customer - jazz)", "Login", JOptionPane.PLAIN_MESSAGE);

     
        if (username != null && !username.trim().isEmpty()) {
            if (isValidAdmin(username)) {
                JOptionPane.showMessageDialog(null, "Welcome, " + username + "!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                showAdminMainMenu(username);
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

    private static void showAdminMainMenu(String username) {
        Object[] options = {"Personal Info", "Inventory","Logout"};
        int choice = JOptionPane.showOptionDialog(null, "Select an option:", "Online Shopping - Admin",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                showAdminPersonalInfo(username);
                showAdminMainMenu(username); 
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "Inventory selected.", "Option Selected", JOptionPane.INFORMATION_MESSAGE);
                showAdminMainMenu(username); 
                break;
            case 2:
            String[] s=new String[1];
            main(s);
             break;
            default:
                JOptionPane.showMessageDialog(null, "No option selected.", "No Selection", JOptionPane.WARNING_MESSAGE);
                break;
        }
    }

    private static void showCustomerMainMenu(String username) {
        Object[] options = {"Personal Info", "Browse Products", "View Cart"};
        int choice = JOptionPane.showOptionDialog(null, "Select an option:", "Online Shopping - Customer",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                showCustomerPersonalInfo(username);
                showCustomerMainMenu(username);
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "Browse Products selected.", "Option Selected", JOptionPane.INFORMATION_MESSAGE);
                showCustomerMainMenu(username);
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "View Cart selected.", "Option Selected", JOptionPane.INFORMATION_MESSAGE);
                showCustomerMainMenu(username);
                break;
            default:
                JOptionPane.showMessageDialog(null, "No option selected.", "No Selection", JOptionPane.WARNING_MESSAGE);
                break;
        }
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
}