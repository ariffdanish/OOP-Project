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
        orditem.displayCart();
        System.out.println("Total Cost: " + tcost);
        System.out.println("Order ID: " + orderId);
    }
}
