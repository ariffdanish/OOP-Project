import javax.swing.JOptionPane;

public class SHOPPING_Online {

     private static Admin[] admins = {
            new Admin(123, "admin1", "Admin1@gmail.com", "01123456"),
            new Admin(356, "admin2", "Admin2@gmail.com", "08765678")
        };
    
        private static Customer[] customers = {
            new Customer(678, "customer1", "customer1@gmail.com", "No5, Jalan meranti,Taman meranti,4567 Johor Bahru"),
            new Customer(556, "customer2", "customer2@gmail.com", "No10, Jalan angkasa,Taman angkasa,4567 Johor Bahru")
        };
    
        public static void main(String[] args) {
       
            String username = JOptionPane.showInputDialog(null, "Enter your username:", "Login", JOptionPane.PLAIN_MESSAGE);
    
          
            if (username != null && !username.trim().isEmpty()) {
                if (isValidAdmin(username)) {
                    JOptionPane.showMessageDialog(null, "Welcome, " + username + "!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                    showAdminMainMenu();
                } else if (isValidCustomer(username)) {
                    JOptionPane.showMessageDialog(null, "Welcome, " + username + "!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                    showCustomerMainMenu();
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
    
        private static void showAdminMainMenu() {
            Object[] options = {"Inventory", "Info", "Product"};
            int choice = JOptionPane.showOptionDialog(null, "Select an option:", "Online Shopping - Admin",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
    
            switch (choice) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Inventory selected.", "Option Selected", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "Info selected.", "Option Selected", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Product selected.", "Option Selected", JOptionPane.INFORMATION_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "No option selected.", "No Selection", JOptionPane.WARNING_MESSAGE);
                    break;
            }
        }
    
        private static void showCustomerMainMenu() {
            Object[] options = {"Browse Products", "View Cart", "Order History"};
            int choice = JOptionPane.showOptionDialog(null, "Select an option:", "Online Shopping - Customer",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
    
            switch (choice) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Browse Products selected.", "Option Selected", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "View Cart selected.", "Option Selected", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Order History selected.", "Option Selected", JOptionPane.INFORMATION_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "No option selected.", "No Selection", JOptionPane.WARNING_MESSAGE);
                    break;
            }
        }
    }

    

