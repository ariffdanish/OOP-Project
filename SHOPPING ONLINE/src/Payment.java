import javax.swing.*;
import java.util.*;
public class Payment {
    private String paymentMethod;
    private double amount;

    public Payment(String paymentMethods) {
        this.paymentMethod = paymentMethods;
    }

    public void setPaymentMethods(String paymentMethods) {
        this.paymentMethod = paymentMethods;
    }

    public String getPaymentMethods() {
        return paymentMethod;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }


    public void paymentProcess(List<Product> cart) {
        String message = "Processing payment of amount: " + amount + " using " + paymentMethod + "\nPress OK to confirm payment and Press CANCEL to cancel the payment";
        int response = JOptionPane.showConfirmDialog(null, message, "Make Payment", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
    
        if (response == JOptionPane.OK_OPTION) {
            JOptionPane.showMessageDialog(null, "Payment successfully made.", "Payment Confirmation", JOptionPane.INFORMATION_MESSAGE);
            cart.clear();
        } else if (response == JOptionPane.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, "Payment canceled.", "Payment Canceled", JOptionPane.WARNING_MESSAGE);
        }
    }
}    
