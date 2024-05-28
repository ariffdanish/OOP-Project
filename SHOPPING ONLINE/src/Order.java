public class Order {

    private int orderId;
    private String productname;
    private String date;
    private String description;
    private float tcost;

    Order(int o, String p, String d, String desc, float c)
    {
        orderId = o;
        productname = p;
        date = d;
        description = desc;
        tcost = c;
    }
    
}
