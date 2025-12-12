package service;

import dao.ClientDAO;
import dao.LibraryItemDAO;
import exception.ItemNotFoundException;
import model.Client;
import model.LibraryItem;
import java.util.List;

public class LibraryService {
    private final ClientDAO clientDAO;
    private final LibraryItemDAO libraryItemDAO;

    public LibraryService(ClientDAO clientDAO, LibraryItemDAO libraryItemDAO) {
        this.clientDAO = clientDAO;
        this.libraryItemDAO = libraryItemDAO;
    }

    public List<Client> getAllClients() {
        return clientDAO.getAllClients();
    }

    public List<LibraryItem> getAllLibraryItems() {
        return libraryItemDAO.getAllItems();
    }

    public List<LibraryItem> getAvailableLibraryItems() {
        return libraryItemDAO.getAllItems().stream()
                .filter(LibraryItem::isAvailable)
                .toList();
    }

    public Client getClientById(int id) {
        return clientDAO.getClientById(id).orElseThrow(() -> new ItemNotFoundException("Client with ID " + id + " not found."));
    }

    public LibraryItem getLibraryItemById(int id) {
        return libraryItemDAO.getItemById(id).orElseThrow(() -> new ItemNotFoundException("Library item with ID " + id + " not found."));
    }

    public Client addClient(Client client) {
        if (client == null) throw new IllegalArgumentException("Client cannot be null");

        boolean success = clientDAO.addClient(client);

        if (!success) {
            throw new IllegalStateException("Client could not be added.");
        }

        return client;
    }

    public LibraryItem addLibraryItem(LibraryItem item) {
        if (item == null) throw new IllegalArgumentException("Library item cannot be null");

        boolean success = libraryItemDAO.addItem(item);

        if (!success) {
            throw new IllegalStateException("Library item could not be added.");
        }

        return item;
    }

    public void addLibraryItems(List<? extends LibraryItem> items) {
        libraryItemDAO.addItems(items);
    }

    public void updateClient(Client client) {
        if (!clientDAO.updateClient(client)) {
            throw new ItemNotFoundException("Cannot update: Client ID " + client.getClientId() + " not found.");
        }
    }

    public void updateLibraryItem(LibraryItem item) {
        if(!libraryItemDAO.updateItem(item)) {
            throw new ItemNotFoundException("Cannot update: Library item ID " + item.getId() + " not found.");
        }
    }

    public void deleteClient(int id) {
        if(!clientDAO.deleteClient(id)) {
            throw new ItemNotFoundException("Cannot delete: Client ID " + id + " not found.");
        }
    }

    public void deleteLibraryItem(int id) {
        if(!libraryItemDAO.deleteItem(id)){
            throw new ItemNotFoundException("Cannot delete: Library item ID " + id + " not found.");
        }
    }

    public void borrowItem(int clientId, int itemId) {
        Client client = getClientById(clientId);
        LibraryItem item = getLibraryItemById(itemId);

        if (!item.isAvailable()) {
            throw new IllegalStateException("Item is out of stock!");
        }

        if(client.hasBorrowedItem(itemId)) {
            throw new IllegalStateException("Client has already borrowed this item.");
        }

        item.decreaseQuantity();
        client.addItem(itemId);

        updateLibraryItem(item);
        updateClient(client);
    }

    public void returnItem(int clientId, int itemId) {
        Client client = getClientById(clientId);
        LibraryItem item = getLibraryItemById(itemId);

        if (!client.hasBorrowedItem(itemId)) {
            throw new IllegalStateException("Client has not borrowed this item.");
        }

        item.increaseQuantity();
        client.removeItem(itemId);

        updateLibraryItem(item);
        updateClient(client);
    }
}
