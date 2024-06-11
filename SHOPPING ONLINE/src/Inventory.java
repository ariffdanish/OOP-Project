import java.util.List;

class Inventory {
    private List<Product> products;

    public Inventory(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
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

    public boolean checkAvailabilityProduct(int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                return product.getQuantity() > 0;
            }
        }
        return false;
    }

    public void updateProduct(Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == product.getId()) {
                products.set(i, product);
                break;
            }
        }
    }

    public Product findProductById(int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    public void displayInventory() {
        for (Product product : products) {
            System.out.println(product.displayInfo());
        }
    }
}
