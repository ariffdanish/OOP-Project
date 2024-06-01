public class Payment {
    private String paymentMethod;
    private int amount;
    private String paymentStatus;

    public Payment(String paymentMethods, int amount, String paymentStatus) {
        this.paymentMethod = paymentMethods;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
    }

    public void setPaymentMethods(String paymentMethods) {
        this.paymentMethod = paymentMethods;
    }

    public String getPaymentMethods() {
        return paymentMethod;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void paymentProcess() {
        System.out.println("Processing payment of amount: " + amount + " using " + paymentMethod);
        this.paymentStatus = "Completed";
        System.out.println("Payment status: " + paymentStatus);
    }
}
