package model;

public class Book extends LibraryItem {
    private String author;
    private Integer pageCount;

    public Book(String title, Integer quantity, String author, Integer pageCount) {
        super(title, quantity);
        setAuthor(author);
        setPageCount(pageCount);
    }

    protected Book(Book other) {
        super(other.getId(), other.getTitle(), other.getQuantity());
        this.author = other.author;
        this.pageCount = other.pageCount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if(author == null || author.isEmpty()){
            throw new IllegalArgumentException("Author cannot be null or empty");
        }

        this.author = author;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        if(pageCount == null || pageCount <= 0){
            throw new IllegalArgumentException("Page count must be a positive integer");
        }

        this.pageCount = pageCount;
    }

    @Override
    public LibraryItem copy() {
        return new Book(this);
    }

    @Override
    public void getItemDetails() {
        super.getItemDetails();
        System.out.println("Author: " + author);
        System.out.println("Page Count: " + pageCount);
    }
}
