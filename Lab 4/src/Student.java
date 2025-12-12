import java.util.ArrayList;
import java.util.List;

public class Student {
    private Integer studentId;
    private String studentName;
    private List<CourseRegistration> courseRegistrations;

    public Student(Integer studentId, String studentName) {
        if(studentId == null || studentName == null){
            throw new IllegalArgumentException("Student ID and Name cannot be null");
        }

        if(studentId <= 0){
            throw new IllegalArgumentException("Student ID must be a positive integer");
        }

        if(studentName.trim().isEmpty()){
            throw new IllegalArgumentException("Student Name cannot be empty");
        }

        this.studentId = studentId;
        this.studentName = studentName;
        this.courseRegistrations = new ArrayList<>();
    }

    public Integer getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public List<CourseRegistration> getCourseRegistrations() {
        return courseRegistrations;
    }

    public void registerCourse(Course course) {
        registerCourse(course, 0.0);
    }

    public void registerCourse(Course course, Double grade) {
        CourseRegistration registration = new CourseRegistration(course, grade);
        courseRegistrations.add(registration);
    }

    public void printReport(){
        StringBuilder report = new StringBuilder();

        report.append("Report for Student: ").append(studentName).append(" (ID: ").append(studentId).append(")\n");
        report.append("--------------------------------------------------\n");
        for(CourseRegistration reg : courseRegistrations){
            report.append("Course: ").append(reg.getCourse().getCourseName())
                  .append(" | Credit Hours: ").append(reg.getCourse().getCreditHours())
                  .append(" | Grade: ").append(reg.getGrade()).append("\n");
        }

        System.out.println(report.toString());
    }



    private class CourseRegistration {
        private Course course;
        private Double grade;

        public CourseRegistration(Course course, Double grade) {
            if(course == null){
                throw new IllegalArgumentException("Course cannot be null");
            }

            if(grade < 0.0 || grade > 100.0){
                throw new IllegalArgumentException("Grade must be between 0.0 and 100.0");
            }

            this.course = course;
            this.grade = grade;
        }

        public Course getCourse() {
            return course;
        }

        public Double getGrade() {
            return grade;
        }
    }
}
