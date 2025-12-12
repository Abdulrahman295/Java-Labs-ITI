package service;

import enums.LibraryMenuOption;
import model.Book;
import model.Client;
import model.LibraryItem;
import model.Magazine;

import java.util.EnumMap;
import java.util.Map;

public class LibraryMenuManager {
    private final LibraryInputHandler inputHandler;
    private final LibraryService libraryService;
    private final Map<LibraryMenuOption, LibraryMenuAction> menuActions;

    public LibraryMenuManager(LibraryInputHandler inputHandler, LibraryService libraryService) {
        this.inputHandler = inputHandler;
        this.libraryService = libraryService;
        this.menuActions = new EnumMap<>(LibraryMenuOption.class);
        initializeActions();
    }

    public void run() {
        LibraryMenuOption option = null;
        do {
            printMenu();
            option = inputHandler.getMenuOption();
            handleOption(option);
        } while (option != LibraryMenuOption.EXIT_APP);
    }

    private void initializeActions(){
        menuActions.put(LibraryMenuOption.ADD_BOOK, this::addBook);
        menuActions.put(LibraryMenuOption.ADD_MAGAZINE, this::addMagazine);
        menuActions.put(LibraryMenuOption.VIEW_ALL_ITEMS, this::viewAllItems);
        menuActions.put(LibraryMenuOption.VIEW_AVAILABLE_ITEMS, this::viewAvailableItems);
        menuActions.put(LibraryMenuOption.SEARCH_ITEM, this::searchItem);
        menuActions.put(LibraryMenuOption.UPDATE_ITEM, this::updateItem);
        menuActions.put(LibraryMenuOption.DELETE_ITEM, this::deleteItem);
        menuActions.put(LibraryMenuOption.ADD_CLIENT, this::addClient);
        menuActions.put(LibraryMenuOption.VIEW_ALL_CLIENTS, this::viewAllClients);
        menuActions.put(LibraryMenuOption.SEARCH_CLIENT, this::searchClient);
        menuActions.put(LibraryMenuOption.UPDATE_CLIENT, this::updateClient);
        menuActions.put(LibraryMenuOption.DELETE_CLIENT, this::deleteClient);
        menuActions.put(LibraryMenuOption.BORROW_ITEM, this::borrowItem);
        menuActions.put(LibraryMenuOption.RETURN_ITEM, this::returnItem);
    }

    private void printMenu(){
        System.out.println("\n=== LIBRARY MENU ===");
        for (LibraryMenuOption option : LibraryMenuOption.values()) {
            System.out.println(option.getId() + ". " + option.getDescription());
        }
    }

    private void handleOption(LibraryMenuOption option){
        try {
            LibraryMenuAction action = menuActions.get(option);
            if (action != null) {
                action.execute();
            }
        } catch (Exception e) {
            System.out.println("Operation Failed: " + e.getMessage());
        }
    }

    private void addBook(){
        System.out.println("\n--- Add New Book ---");
        String title = inputHandler.getStringInput("Enter Book Title: ");
        Integer quantity = inputHandler.getPositiveIntegerInput("Enter Quantity: ");
        String author = inputHandler.getStringInput("Enter Author Name: ");
        Integer pageCount = inputHandler.getPositiveIntegerInput("Enter Page Count: ");

        Book book = new Book(title, quantity, author, pageCount);
        LibraryItem savedBook = libraryService.addLibraryItem(book);

        System.out.println("Book added successfully!");
        System.out.println("ID: " + savedBook.getId());
        System.out.println("Title: " + savedBook.getTitle());
    }

    private void addMagazine(){
        System.out.println("\n--- Add New Magazine ---");
        String title = inputHandler.getStringInput("Enter Magazine Title: ");
        Integer quantity = inputHandler.getPositiveIntegerInput("Enter Quantity: ");

        Magazine magazine = new Magazine(title, quantity);
        LibraryItem savedMagazine = libraryService.addLibraryItem(magazine);

        System.out.println("Magazine added successfully!");
        System.out.println("ID: " + savedMagazine.getId());
        System.out.println("Title: " + savedMagazine.getTitle());
    }

    private void viewAllItems(){
        System.out.println("\n--- All Library Items ---");
        libraryService.getAllLibraryItems().forEach(LibraryItem::getItemDetails);
    }

    private void viewAvailableItems(){
        System.out.println("\n--- Available Library Items ---");
        libraryService.getAvailableLibraryItems().forEach(LibraryItem::getItemDetails);
    }

    private void searchItem(){
        System.out.println("\n--- Search Library Item ---");
        Integer itemId = inputHandler.getPositiveIntegerInput("Enter Item ID: ");

        LibraryItem item = libraryService.getLibraryItemById(itemId);
        System.out.println("Item found:");
        item.getItemDetails();
    }

    private void updateItem(){
        System.out.println("\n--- Update Library Item ---");
        Integer itemId = inputHandler.getPositiveIntegerInput("Enter Item ID: ");

        LibraryItem item = libraryService.getLibraryItemById(itemId);

        String newTitle = inputHandler.getStringInputOrDefault("Enter Title", item.getTitle());
        item.setTitle(newTitle);

        Integer newQty = inputHandler.getIntegerInputOrDefault("Enter Quantity", item.getQuantity());
        item.setQuantity(newQty);


        if(item instanceof Book book){
            String newAuthor = inputHandler.getStringInputOrDefault("Enter Author", book.getAuthor());
            book.setAuthor(newAuthor);

            Integer newPages = inputHandler.getIntegerInputOrDefault("Enter Page Count", book.getPageCount());
            book.setPageCount(newPages);
        }

        libraryService.updateLibraryItem(item);
        System.out.println("Item updated successfully.");
    }

    private void deleteItem(){
        System.out.println("\n--- Delete Library Item ---");
        Integer itemId = inputHandler.getPositiveIntegerInput("Enter Item ID: ");
        libraryService.deleteLibraryItem(itemId);
        System.out.println("Item deleted successfully.");

    }

    private void addClient(){
        System.out.println("\n--- Add New Client ---");
        String name = inputHandler.getStringInput("Enter Client Name: ");
        String email = inputHandler.getStringInput("Enter Client Email: ");

        Client client = new Client(name, email);
        Client savedClient = libraryService.addClient(client);

        System.out.println("Client added successfully!");
        System.out.println("ID: " + savedClient.getClientId());
        System.out.println("Name: " + savedClient.getName());
    }

    private void viewAllClients(){
        System.out.println("\n--- All Clients ---");
        libraryService.getAllClients().forEach(Client::getClientInfo);
    }

    private void searchClient(){
        System.out.println("\n--- Search Client ---");
        Integer clientId = inputHandler.getPositiveIntegerInput("Enter Client ID: ");

        Client client = libraryService.getClientById(clientId);
        System.out.println("Client found:");
        client.getClientInfo();
    }

    private void updateClient(){
        System.out.println("\n--- Update Client ---");
        Integer clientId = inputHandler.getPositiveIntegerInput("Enter Client ID: ");
        Client client = libraryService.getClientById(clientId);

        String newName = inputHandler.getStringInputOrDefault("Enter Name", client.getName());
        client.setName(newName);

        String newEmail = inputHandler.getStringInputOrDefault("Enter Email", client.getEmail());
        client.setEmail(newEmail);

        libraryService.updateClient(client);
        System.out.println("Client updated successfully.");
    }

    private void deleteClient(){
        System.out.println("\n--- Delete Client ---");
        Integer clientId = inputHandler.getPositiveIntegerInput("Enter Client ID: ");
        libraryService.deleteClient(clientId);
        System.out.println("Client deleted successfully.");

    }

    private void borrowItem(){
        System.out.println("\n--- Borrow Library Item ---");
        Integer clientId = inputHandler.getPositiveIntegerInput("Enter Client ID: ");
        Integer itemId = inputHandler.getPositiveIntegerInput("Enter Item ID: ");
        libraryService.borrowItem(clientId, itemId);
        System.out.println("Item borrowed successfully.");
    }

    private void returnItem(){
        System.out.println("\n--- Return Library Item ---");
        Integer clientId = inputHandler.getPositiveIntegerInput("Enter Client ID: ");
        Integer itemId = inputHandler.getPositiveIntegerInput("Enter Item ID: ");
        libraryService.returnItem(clientId, itemId);
        System.out.println("Item returned successfully.");
    }


}
