public class Order extends shoppingCart {

    private int orderId;
    private float tcost;
    private shoppingCart orditem;

    public Order(int orderId, shoppingCart cart) {
        this.orderId = orderId;
        this.orditem = cart;
        this.tcost = orditem.getCost();
    }

    public void displayord() {
        System.out.println("OFFICIAL RECEIPT");
        orditem.displayCart();

        System.out.println("\nTotal Cost: " + tcost);
        System.out.println("Order ID: " + orderId);
    }
}
