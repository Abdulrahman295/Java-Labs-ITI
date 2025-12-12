package model;

public class Magazine extends LibraryItem {

    public Magazine(String title, Integer quantity) {
        super(title, quantity);
    }

    protected Magazine(Magazine other) {
        super(other.getId(), other.getTitle(), other.getQuantity());
    }

    @Override
    public LibraryItem copy() {
        return new Magazine(this);
    }

    @Override
    public void getItemDetails() {
        super.getItemDetails();
    }
}
