import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistrationApp implements AutoCloseable {
    private final Map<Integer, Student> students;
    private final Map<Integer, Course> courses;
    private final RegistrationInputHandler inputHandler;
    private final StringBuilder menu;

    public RegistrationApp() {
        students = new HashMap<>();
        courses = new HashMap<>();
        inputHandler = new RegistrationInputHandler();
        menu = new StringBuilder();
        for (MenuOption option : MenuOption.values()) {
            menu.append(option.getId())
                    .append(". ")
                    .append(option.getDescription())
                    .append("\n");
        }
    }

    public void run() {
        MenuOption choice = null;

        do {
            printMenu();
            choice = inputHandler.getMenuOption();

            switch (choice) {
                case ADD_STUDENT -> addStudent();
                case ADD_COURSE -> addCourse();
                case REGISTER_STUDENT -> registerCourseForStudent();
                case MULTIPLE_REGISTER  -> registerMultipleCourses();
                case PRINT_REPORT -> printStudentReport();
                case EXIT_APP -> System.out.println("Exiting...");
                case null, default -> System.out.println("Invalid choice.");
            }

        } while (choice != MenuOption.EXIT_APP);
    }

    private void printMenu() {
        System.out.print(menu.toString());
    }

    private void addStudent() {
        System.out.println("--- Add Student ---");

        Integer id = inputHandler.getIntegerInput("Enter Student ID: ");
        if (id == null) return;

        if (students.containsKey(id)) {
            System.out.println("Student already exists...");
            return;
        }

        String name = inputHandler.getStringInput("Enter Student Name: ");
        if (name == null) return;

        try {
            Student s = new Student(id, name);
            students.put(s.getStudentId(), s);
            System.out.println("Student added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addCourse() {
        System.out.println("--- Add Course ---");

        Integer id = inputHandler.getIntegerInput("Enter Course ID: ");
        if (id == null) return;

        if (courses.containsKey(id)) {
            System.out.println("Course already exists...");
            return;
        }

        String name = inputHandler.getStringInput("Enter Course Name: ");
        if (name == null) return;

        Integer creditHours = inputHandler.getIntegerInput("Enter Credit Hours: ");
        if (creditHours == null) return;

        try {
            Course c = new Course(id, name, creditHours);
            courses.put(c.getCourseId(), c);
            System.out.println("Course added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void registerCourseForStudent() {
        System.out.println("--- Register Course For Student ---");

        Integer studentId = inputHandler.getIntegerInput("Enter Student ID: ");
        if (studentId == null) return;

        Integer courseId = inputHandler.getIntegerInput("Enter Course ID: ");
        if (courseId == null) return;

        if(!studentAndCourseExist(studentId, courseId)){
            System.out.println("Student and Course must exist...");
            return;
        }

        Double grade = inputHandler.getDoubleInput("Enter Grade: ");
        if (grade == null) return;

        try {
            Student std = students.get(studentId);
            Course crs = courses.get(courseId);
            std.registerCourse(crs,grade);
            System.out.println("Course registered successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void registerMultipleCourses() {
        System.out.println("--- Register Multiple Courses ---");
        Integer studentId = inputHandler.getIntegerInput("Enter Student ID: ");
        if (studentId == null) return;

        Student student = students.get(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        List<Integer> courseIds = inputHandler.getIntegerListInput("Enter Course IDs (comma separated): ");
        if (courseIds == null || courseIds.isEmpty()) {
            System.out.println("No valid IDs found.");
            return;
        }

        for (Integer courseId : courseIds) {
            if (!courses.containsKey(courseId)) {
                System.out.println("Course ID " + courseId + " not found. Skipping...");
                continue;
            }

            try {
                Course course = courses.get(courseId);
                student.registerCourse(course);
                System.out.println("Registered for Course ID " + courseId + " successfully!");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

    }

    private void printStudentReport() {
        Integer studentId = inputHandler.getIntegerInput("Enter Student ID: ");
        if (studentId == null) return;

        Student student = students.get(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        student.printReport();
    }

    private boolean studentAndCourseExist(Integer studentId, Integer courseId){
        return students.containsKey(studentId) && courses.containsKey(courseId);
    }

    @Override
    public void close() {
        inputHandler.close();
    }
}
