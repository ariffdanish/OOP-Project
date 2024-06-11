public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String category;

    Product(int pId, String n, String d, double p, int q, String c) {
        id = pId;
        name = n;
        description = d;
        price = p;
        quantity = q;
        category = c;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    
    public String displayInfo(){
       return "ID: "+id+" Name: "+name+" Description: "+description+" Price: "+price+" Quantity: "+quantity+" Category: "+category;
    }

    /*public void displayInfo(){
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Price: " + price);
        System.out.println("Quantity: " + quantity);
        System.out.println("Category: " + category);*/
}
