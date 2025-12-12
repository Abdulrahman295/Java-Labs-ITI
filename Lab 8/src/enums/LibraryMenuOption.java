package enums;

import java.util.Arrays;
import java.util.Optional;

public enum LibraryMenuOption {

    ADD_BOOK(1, "Add Book"),
    ADD_MAGAZINE(2, "Add Magazine"),
    VIEW_ALL_ITEMS(3, "View All Items"),
    VIEW_AVAILABLE_ITEMS(4, "View Available Items Only"),
    SEARCH_ITEM(5, "Search Item by ID (Details)"),
    UPDATE_ITEM(6, "Update Item"),
    DELETE_ITEM(7, "Delete Item"),

    ADD_CLIENT(8, "Add Client"),
    VIEW_ALL_CLIENTS(9, "View All Clients"),
    SEARCH_CLIENT(10, "Search Client by ID (Show History)"),
    UPDATE_CLIENT(11, "Update Client"),
    DELETE_CLIENT(12, "Delete Client"),

    BORROW_ITEM(13, "Borrow Item"),
    RETURN_ITEM(14, "Return Item"),

    EXIT_APP(15, "Exit Application");

    private final int id;
    private final String description;

    LibraryMenuOption(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static Optional<LibraryMenuOption> fromId(int id) {
        return Arrays.stream(values())
                .filter(option -> option.id == id)
                .findFirst();
    }
}