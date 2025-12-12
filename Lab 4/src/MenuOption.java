public enum MenuOption {
    ADD_STUDENT(1, "Add Student"),
    ADD_COURSE(2, "Add Course"),
    REGISTER_STUDENT(3, "Register Student for Course"),
    MULTIPLE_REGISTER(4, "Register Student for Multiple Courses"),
    PRINT_REPORT(5, "Print Student Report"),
    EXIT_APP(6, "Exit Application");

    private final int id;
    private final String description;

    MenuOption(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static MenuOption fromId(int id) {
        for (MenuOption option : values()) {
            if (option.id == id) {
                return option;
            }
        }

        return null;
    }
}