package model;

public abstract class LibraryItem {
    private Integer id;
    private String title;
    private Integer quantity;

    public LibraryItem(String title, Integer quantity) {
        setTitle(title);
        setQuantity(quantity);
    }

    public LibraryItem(Integer id, String title, Integer quantity) {
        setId(id);
        setTitle(title);
        setQuantity(quantity);
    }

    public abstract LibraryItem copy();

    public Integer getId() {
        return id;
    }

    public void  setId(Integer id) {
        if(id == null){
            throw new IllegalArgumentException("ID cannot be null");
        }

        if(id <= 0){
            throw new IllegalArgumentException("ID must be a positive integer");
        }

        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title == null || title.isEmpty()){
            throw new IllegalArgumentException("Title cannot be null or empty");
        }

        this.title = title;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        if(quantity < 0){
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        this.quantity = quantity;
    }

    public void decreaseQuantity() {
        if (this.quantity <= 0) {
            throw new IllegalStateException("Item is out of stock!");
        }
        this.quantity--;
    }

    public void increaseQuantity() {
        this.quantity++;
    }

    public boolean isAvailable() {
        return this.quantity > 0;
    }

    public void getItemDetails(){
        System.out.println("IDs: " + id);
        System.out.println("Title: " + title);
        System.out.println("Quantity: " + quantity);
    }
}
