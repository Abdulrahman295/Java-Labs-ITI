package dao;

import model.Client;
import model.LibraryItem;

import java.util.List;
import java.util.Optional;

public interface LibraryItemDAO {
    List<LibraryItem> getAllItems();

    Optional<LibraryItem> getItemById(int id);

    boolean addItem(LibraryItem item);

    void addItems(List<? extends LibraryItem> items);

    boolean updateItem(LibraryItem item);

    boolean deleteItem(int id);
}
