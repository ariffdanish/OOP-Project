public class Admin{
    private int aId;
    private String aName;
    private String aEmail;
    private int aPhone;
    private String password;
    private Inventory inventory = new Inventory();

    public Admin(int id, String n, String e, int p, String pwd, Inventory i){
        aId=id;
        aName=n;
        aEmail=e;
        aPhone=p;
        password=pwd;
        inventory=i;
    }

    public void setId(int id){
        aId=id;
    }

    public void setName(String n){
        aName=n;
    }

    public void setEmail(String e){
        aEmail=e;
    }

    public void setPhone(int p){
        aPhone=p;
    }

    public void setPassword(String pwd){
        password=pwd;
    }

    public int getId(){
        return aId;
    }

    public String getName(){
        return aName;
    }

    public String getEmail(){
        return aEmail;
    }

    public int getPhone(){
        return aPhone;
    }

    public void displayAdmin(){
        System.out.println("Admin Infomation: ");
        System.out.println("ID: "+aId);
        System.out.println("Name: "+aName);
        System.out.println("Email: "+aEmail);
        System.out.println("Phone Number: "+aPhone);
    }

    public void addProduct(int id, String name, String description, double price, int quantity, String category) {
        Product product = new Product(id, name, description, price, quantity, category);
        inventory.addProduct(product);
        System.out.println("Product added successfully.");
    }

    public void removeProduct(Product p) {
        inventory.removeProduct(p);
        System.out.println("Product removed successfully.");
    }

    public void editProduct(Product p) {
        inventory.updateProduct(p);
        System.out.println("Product updated successfully.");
    }

}