public class Order extends shoppingCart {

    private int orderId;
    
    private String date;
    
    private float tcost;
    private shoppingCart concart;

    Order(int o,  String d, float c)
    {
        orderId = o;
        
        date = d;
        
        tcost = c;
    }

    public void displayord()
    {
        displayCart();
        System.out.println("Total Cost: "+confirmord());
    }
    
}
