import javax.swing.*;

public class Payment {
    private String paymentMethod;
    private double amount;

    public Payment(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

<<<<<<< HEAD

    public void paymentProcess(List<Product> cart) {
        String message = "Processing payment of amount: $" + amount + " using " + paymentMethod + "\nPress OK to confirm payment and Press CANCEL to cancel the payment";
=======
    public void paymentProcess(shoppingCart cart) {
        String message = "Processing payment of amount: " + amount + " using " + paymentMethod + "\nPress OK to confirm payment and Press CANCEL to cancel the payment";
>>>>>>> e52e46e0fc4b5b8874ca0ae54b75110180078ed5
        int response = JOptionPane.showConfirmDialog(null, message, "Make Payment", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
    
        if (response == JOptionPane.OK_OPTION) {
            Order order = new Order(generateOrderId(), cart);
            order.displayord();
            JOptionPane.showMessageDialog(null, "Payment successfully made.", "Payment Confirmation", JOptionPane.INFORMATION_MESSAGE);
            cart.clear();
        } else if (response == JOptionPane.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, "Payment canceled.", "Payment Canceled", JOptionPane.WARNING_MESSAGE);
        }
    }

    private int generateOrderId() {
        return (int) (Math.random() * 100000); // Generates a random order ID
    }
}
