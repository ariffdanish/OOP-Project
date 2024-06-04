public class Customer {

    private int cid;
    private String cname;
    private String email;
    private String address;
    private shoppingCart cart;
    private Order ord;
    Customer(int c,String n, String e, String a)
    {
        cid = c;
        cname = n;
        email = e;
        address = a;
    }

    public void displaycust()
    {
        System.out.println("Customer Information: ");
        System.out.println("ID: "+cid);
        System.out.println("Name: "+cname);
        System.out.println("Email: "+email);
        System.out.println("Address: "+address);
    }
    
}
