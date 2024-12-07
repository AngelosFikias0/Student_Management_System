import java.util.ArrayList;

public class StudentManager {
    private ArrayList<Student> students;
    private static final String FILE_NAME = "students.dat";

    // Constructor: Load students from file and remove duplicates
    public StudentManager() {
        students = new ArrayList<>();
        ArrayList<Student> loadedStudents = FileManager.loadFromFile(FILE_NAME);
        for (Student student : loadedStudents) {
            if (!studentExists(student.getId())) {
                students.add(student);
            }
        }
    }

    // Add a new student only if they don't already exist
    public void addStudent(Student student) {
        if (!studentExists(student.getId())) {
            students.add(student);
            save();
        } else {
            System.out.println("Student with ID " + student.getId() + " already exists.");
        }
    }

    // Remove a student by ID
    public boolean removeStudent(String studentId) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                students.remove(student);
                save();
                return true;
            }
        }
        return false;
    }

    // Search for a student by ID
    public Student searchStudent(String studentId) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    // List all students
    public ArrayList<Student> listStudents() {
        return students;
    }

    // Save students to file, it saves the arrayList all over to the file again
    private void save() {
        FileManager.saveToFile(FILE_NAME, students);
    }

    // Check if a student exists by ID
    private boolean studentExists(String studentId) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                return true;
            }
        }
        return false;
    }
}
