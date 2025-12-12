package dao;

import model.LibraryItem;
import util.IdGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryLibraryItemDAO implements  LibraryItemDAO {
    private final Map<Integer, LibraryItem> items;
    private final IdGenerator idGenerator;

    public InMemoryLibraryItemDAO(){
        items = new HashMap<>();
        idGenerator = new IdGenerator();
    }

    @Override
    public List<LibraryItem> getAllItems() {
        return items.values().stream()
                .map(LibraryItem::copy)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<LibraryItem> getItemById(int id) {
        LibraryItem item = items.get(id);

        if (item == null) {
            return Optional.empty();
        }

        return Optional.of(item.copy());
    }

    @Override
    public boolean  addItem(LibraryItem item) {
        if(item == null || items.containsKey(item.getId())){
            return false;
        }

        item.setId(idGenerator.generate());
        items.put(item.getId(), item.copy());
        return true;
    }

    @Override
    public void addItems(List<? extends LibraryItem> newItems) {
        if (newItems == null) return;

        newItems.forEach(this::addItem);
    }

    @Override
    public boolean updateItem(LibraryItem item) {
        if (item == null || !items.containsKey(item.getId())) {
            return false;
        }

        items.put(item.getId(), item.copy());
        return true;
    }

    @Override
    public boolean deleteItem(int id) {
        return items.remove(id) != null;
    }
}
