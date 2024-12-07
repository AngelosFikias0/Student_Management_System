import java.io.*;
import java.util.ArrayList;

public class FileManager {

    // Save data to a file
    public static <T> void saveToFile(String fileName, ArrayList<T> data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(data);
            System.out.println("Data saved successfully to " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }

    // Load data from a file
    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (ArrayList<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading from file: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}