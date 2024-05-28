public class shoppingCart {

    private Product[] pList = new Product[25];
    private float totalCost = 0;
    private int t = 0;

    public void addProduct(Product p) {
        if (t < pList.length) {
            pList[t] = p;
            totalCost += p.getPrice(); 
            t++;
        } else {
            System.out.println("Cart is full!");
        }
    }

    public void rmvProduct(Product p) {
        for (int i = 0; i < t; i++) {
            if (pList[i].equals(p)) {
                totalCost -= pList[i].getPrice(); 
                for (int j = i; j < t - 1; j++) {
                    pList[j] = pList[j + 1];
                }
                pList[t - 1] = null; 
                t--;
                break; 
            }
        }
    }
}