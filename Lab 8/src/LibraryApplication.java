import dao.ClientDAO;
import dao.InMemoryClientDAO;
import dao.InMemoryLibraryItemDAO;
import dao.LibraryItemDAO;
import service.LibraryInputHandler;
import service.LibraryMenuManager;
import service.LibraryService;

public class LibraryApplication {
    public static void main(String[] args) {
        ClientDAO clientDAO = new InMemoryClientDAO();
        LibraryItemDAO libraryItemDAO = new InMemoryLibraryItemDAO();
        LibraryService libraryService = new LibraryService(clientDAO, libraryItemDAO);
        LibraryInputHandler inputHandler = new LibraryInputHandler();
        LibraryMenuManager menuManager = new LibraryMenuManager(inputHandler, libraryService);
        menuManager.run();
    }
}