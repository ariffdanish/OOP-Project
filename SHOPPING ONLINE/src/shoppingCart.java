public class shoppingCart {

    private Product pList;
    private float totalCost;

    public void addProduct(Product p)
    {
        pList = new Product(p);
        
    }

    public void rmvProduct(Product p)
    {
        delete plist[p];
        
    }
    
}
