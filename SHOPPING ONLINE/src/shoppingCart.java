public class shoppingCart {

    private Product[] pList = new Product[25];
    private float totalCost;
    public t = 0;

    public void addProduct(Product p)
    {
        pList[t] = new Product(p);
        
    }

    public void rmvProduct(Product p)
    {
        for (int i = 0; i<t;i++)
        delete plist[i] = p;
        
    }
    
}
