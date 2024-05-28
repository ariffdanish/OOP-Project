public class Category {
    private int categoryId;
    private String name;
    private String description;

    Category(int id, String n, String desc)
    {
        categoryId = id;
        name = n;
        description = desc;
    }

    public int getCategoryId(){
        return categoryId;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    
}
