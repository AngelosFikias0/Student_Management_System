import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class GUI extends JFrame {
    private static final long serialVersionUID = 1L;

    // Managers
    private final StudentManager studentManager = new StudentManager();
    private final CourseManager courseManager = new CourseManager();

    // Components for Student Management
    private JTextField studentIdField, studentNameField, studentAgeField, studentEmailField;
    private DefaultListModel<String> studentListModel = new DefaultListModel<>();
    private JList<String> studentList = new JList<>(studentListModel);

    // Components for Course Management
    private JTextField courseIdField, courseNameField, courseCreditsField;
    private DefaultListModel<String> courseListModel = new DefaultListModel<>();
    private JList<String> courseList = new JList<>(courseListModel);

    public GUI() {
        setupMainFrame();
        add(createTabbedPane(), BorderLayout.CENTER);
        setVisible(true);
    }

    // Set up the main frame
    private void setupMainFrame() {
        setTitle("Student and Course Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window
        setLayout(new BorderLayout());
    }

    // Create the tabbed pane
    private JTabbedPane createTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Manage Students", createStudentPanel());
        tabbedPane.addTab("Manage Courses", createCoursePanel());
        return tabbedPane;
    }

    // Create the Student Management Panel
    private JPanel createStudentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(createStudentFormPanel(), BorderLayout.NORTH);
        panel.add(createListPanel("Student List", studentList), BorderLayout.CENTER);
        return panel;
    }

    // Create the Course Management Panel
    private JPanel createCoursePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(createCourseFormPanel(), BorderLayout.NORTH);
        panel.add(createListPanel("Course List", courseList), BorderLayout.CENTER);
        return panel;
    }

    // Create a reusable form panel for students
    private JPanel createStudentFormPanel() {
        studentIdField = new JTextField();
        studentNameField = new JTextField();
        studentAgeField = new JTextField();
        studentEmailField = new JTextField();

        JPanel inputPanel = createInputPanel(new String[]{"Student ID:", "Name:", "Age:", "Email:"},
                new JTextField[]{studentIdField, studentNameField, studentAgeField, studentEmailField});

        JPanel buttonPanel = createButtonPanel(new JButton[]{
                createButton("Add Student", e -> addStudent()),
                createButton("List All Students", e -> listStudents()),
                createButton("Search Student", e -> searchStudent()),
                createButton("Remove Student", e -> removeStudent())
        });

        return combinePanels(inputPanel, buttonPanel);
    }

    // Create a reusable form panel for courses
    private JPanel createCourseFormPanel() {
        courseIdField = new JTextField();
        courseNameField = new JTextField();
        courseCreditsField = new JTextField();

        JPanel inputPanel = createInputPanel(new String[]{"Course ID:", "Course Name:", "Credits:"},
                new JTextField[]{courseIdField, courseNameField, courseCreditsField});

        JPanel buttonPanel = createButtonPanel(new JButton[]{
                createButton("Add Course", e -> addCourse()),
                createButton("List All Courses", e -> listCourses()),
                createButton("Search Course", e -> searchCourse()),
                createButton("Remove Course", e -> removeCourse())
        });

        return combinePanels(inputPanel, buttonPanel);
    }

    // Reusable method to create an input panel
    private JPanel createInputPanel(String[] labels, JTextField[] fields) {
        JPanel panel = new JPanel(new GridLayout(labels.length, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Input Details"));

        for (int i = 0; i < labels.length; i++) {
            panel.add(new JLabel(labels[i]));
            panel.add(fields[i]);
        }
        return panel;
    }

    // Reusable method to create a button panel
    private JPanel createButtonPanel(JButton[] buttons) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(Box.createHorizontalGlue());

        for (JButton button : buttons) {
            panel.add(button);
            panel.add(Box.createHorizontalStrut(10));
        }
        panel.add(Box.createHorizontalGlue());

        return panel;
    }

    // Combine input and button panels into a single panel
    private JPanel combinePanels(JPanel inputPanel, JPanel buttonPanel) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(inputPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

    // Create a reusable list panel
    private JScrollPane createListPanel(String title, JList<String> list) {
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBorder(BorderFactory.createTitledBorder(title));
        return scrollPane;
    }

    // Student-related methods
    private void addStudent() {
        try {
            String id = studentIdField.getText();
            String name = studentNameField.getText();
            int age = Integer.parseInt(studentAgeField.getText());
            String email = studentEmailField.getText();

            if (!Checker.isValidEmail(email)) {
                showErrorDialog("Invalid Email Format");
                return;
            }

            studentManager.addStudent(new Student(id, name, age, email));
            updateStudentList();
            clearFields(studentIdField, studentNameField, studentAgeField, studentEmailField);
            showInfoDialog("Student Added Successfully");
        } catch (NumberFormatException e) {
            showErrorDialog("Form must be completed correctly");
        }
    }

    private void listStudents() {
        updateList(studentListModel, studentManager.listStudents());
    }

    private void searchStudent() {
        String id = promptInput("Enter Student ID to search:");
        if (id != null) {
            Student student = studentManager.searchStudent(id);
            if (student != null) {
                showInfoDialog(student.toString());
            } else {
                showErrorDialog("Student not found");
            }
        }
    }

    private void removeStudent() {
        String id = promptInput("Enter Student ID to remove:");
        if (id != null && studentManager.removeStudent(id)) {
            updateStudentList();
            showInfoDialog("Student removed successfully");
        } else {
            showErrorDialog("Student not found");
        }
    }

    // Course-related methods
    private void addCourse() {
        try {
            String id = courseIdField.getText();
            String name = courseNameField.getText();
            int credits = Integer.parseInt(courseCreditsField.getText());

            courseManager.addCourse(new Course(id, name, credits));
            updateCourseList();
            clearFields(courseIdField, courseNameField, courseCreditsField);
            showInfoDialog("Course Added Successfully");
        } catch (NumberFormatException e) {
            showErrorDialog("Form must be completed correctly");
        }
    }

    private void listCourses() {
        updateList(courseListModel, courseManager.listCourses());
    }

    private void searchCourse() {
        String id = promptInput("Enter Course ID to search:");
        if (id != null) {
            Course course = courseManager.searchCourse(id);
            if (course != null) {
                showInfoDialog(course.toString());
            } else {
                showErrorDialog("Course not found");
            }
        }
    }

    private void removeCourse() {
        String id = promptInput("Enter Course ID to remove:");
        if (id != null && courseManager.removeCourse(id)) {
            updateCourseList();
            showInfoDialog("Course removed successfully");
        } else {
            showErrorDialog("Course not found");
        }
    }

    // Utility methods
    private void updateStudentList() {
        listStudents();
    }

    private void updateCourseList() {
        listCourses();
    }

    private <T> void updateList(DefaultListModel<String> listModel, ArrayList<T> items) {
        listModel.clear();
        if (items.isEmpty()) {
            showErrorDialog("The list is empty");
        } else {
            for (T item : items) {
                listModel.addElement(item.toString());
            }
        }
    }

    private void clearFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    private String promptInput(String message) {
        return JOptionPane.showInputDialog(this, message);
    }

    private void showInfoDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    private JButton createButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.addActionListener(action);
        return button;
    }

}

// Email validation utility
class Checker {
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }
}