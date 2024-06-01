import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Product> products;

    public Inventory(){
        
    }
   Inventory(List<Product> products) {
        this.products = new ArrayList<Product>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public int getProductQuantity(int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                return product.getQuantity();
            }
        }
        return 0;
    }

    public boolean checkAvailabilityProduct(int productId){
        for (Product product : products) {
            if (product.getId() == productId) {
                return product.getQuantity()>0;
            }
        } return false;
    }

    public void updateProduct(Product product){
        for (int i=0; i<products.size(); i++){
            if (products.get(i).getId() == product.getId()){
                products.set(i, product);
                break;
            }
        }
    }

    public void displayInventory(){
        for (Product product: products){
            product.displayInfo();
            System.out.println();
        }
    }


}
