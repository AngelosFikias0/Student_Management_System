import java.io.Serializable;

// Represents a Student
public class Student implements Serializable {
    private static final long serialVersionUID = 1L; // Serialization ID for file handling
    
    private String id;
    private String name;
    private int age;
    private String email;

    // Constructor
    public Student(String id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("Student: [ID: %s, Name: %s, Age: %d, Email: %s]", id, name, age, email);
    }
}