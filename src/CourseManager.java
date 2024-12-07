import java.util.ArrayList;

public class CourseManager {
    private ArrayList<Course> courses;
    private static final String FILE_NAME = "courses.dat";

    // Constructor: Load courses from file and remove duplicates
    public CourseManager() {
        courses = new ArrayList<>();
        ArrayList<Course> loadedCourses = FileManager.loadFromFile(FILE_NAME);
        for (Course course : loadedCourses) {
            if (!courseExists(course.getCourseId())) {
                courses.add(course);
            }
        }
    }

    // Add a new course only if it doesn't exist
    public void addCourse(Course course) {
        if (!courseExists(course.getCourseId())) {
            courses.add(course);
            save();
        } else {
            System.out.println("Course with ID " + course.getCourseId() + " already exists.");
        }
    }

    // Remove a course by ID
    public boolean removeCourse(String courseId) {
        for (Course course : courses) {
            if (course.getCourseId().equals(courseId)) {
                courses.remove(course);
                save();
                return true;
            }
        }
        return false;
    }

    // Search for a course by ID
    public Course searchCourse(String courseId) {
        for (Course course : courses) {
            if (course.getCourseId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }

    // List all courses
    public ArrayList<Course> listCourses() {
        return courses;
    }

    // Save courses to file, it saves the arrayList all over to the file again
    private void save() {
        FileManager.saveToFile(FILE_NAME, courses);
    }

    // Check if a course exists by ID
    private boolean courseExists(String courseId) {
        for (Course course : courses) {
            if (course.getCourseId().equals(courseId)) {
                return true;
            }
        }
        return false;
    }
}
