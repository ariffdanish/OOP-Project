public class Order extends shoppingCart {

    private int orderId;
    private float tcost;
    private shoppingCart orditem;

    Order(int o, shoppingCart concart)
    {
        orderId = o;
        orditem  = concart;
        tcost = orditem.getcost();
    }
    public void displayord()
    {
        displayCart();
        System.out.println("Total Cost: "+tcost);
    }
    
}
