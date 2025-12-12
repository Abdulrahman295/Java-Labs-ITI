public class Course {
    private Integer courseId;
    private String courseName;
    private Integer creditHours;

    public Course(Integer courseId, String courseName, Integer creditHours) {
        if(courseId == null || courseName == null || creditHours == null){
            throw new IllegalArgumentException("Course ID, Name, and Credit Hours cannot be null");
        }

        if(courseId <= 0){
            throw new IllegalArgumentException("Course ID must be a positive integer");
        }

        if(courseName.trim().isEmpty()){
            throw new IllegalArgumentException("Course Name cannot be empty");
        }

        if(creditHours <= 0){
            throw new IllegalArgumentException("Credit Hours must be a positive integer");
        }

        this.courseId = courseId;
        this.courseName = courseName;
        this.creditHours = creditHours;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public Integer getCreditHours() {
        return creditHours;
    }
}
