import java.io.*;
import java.util.*;

// Student Class
class Student implements Serializable {
    private String name;
    private String rollNumber;
    private String grade;

    public Student(String name, String rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    // Getters & Setters
    public String getName() { return name; }
    public String getRollNumber() { return rollNumber; }
    public String getGrade() { return grade; }

    public void setName(String name) { this.name = name; }
    public void setGrade(String grade) { this.grade = grade; }

    @Override
    public String toString() {
        return "Roll No: " + rollNumber + " | Name: " + name + " | Grade: " + grade;
    }
}

// Student Management System
class StudentManagementSystem {
    private List<Student> students;
    private final String FILE_NAME = "students.dat";

    public StudentManagementSystem() {
        students = new ArrayList<>();
        loadFromFile();
    }

    // Add Student
    public void addStudent(Student student) {
        students.add(student);
        saveToFile();
    }

    // Remove Student by Roll No
    public boolean removeStudent(String rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber().equals(rollNumber)) {
                students.remove(s);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    // Search Student
    public Student searchStudent(String rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber().equals(rollNumber)) {
                return s;
            }
        }
        return null;
    }

    // Display All Students
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    // Save Data to File
    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Load Data from File
    private void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (List<Student>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Ignore - first run
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}

// Main Class (User Interface)
public class StudentManagementApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();
        int choice;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            while (!sc.hasNextInt()) { // input validation
                System.out.println("Invalid input! Please enter a number (1-5).");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Roll Number: ");
                    String roll = sc.nextLine().trim();
                    if (roll.isEmpty()) {
                        System.out.println("Roll Number cannot be empty!");
                        break;
                    }

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine().trim();
                    if (name.isEmpty()) {
                        System.out.println("Name cannot be empty!");
                        break;
                    }

                    System.out.print("Enter Grade: ");
                    String grade = sc.nextLine().trim();
                    if (grade.isEmpty()) {
                        System.out.println("Grade cannot be empty!");
                        break;
                    }

                    sms.addStudent(new Student(name, roll, grade));
                    System.out.println("✅ Student added successfully!");
                    break;

                case 2:
                    System.out.print("Enter Roll Number to remove: ");
                    String removeRoll = sc.nextLine().trim();
                    if (sms.removeStudent(removeRoll)) {
                        System.out.println("✅ Student removed successfully!");
                    } else {
                        System.out.println("❌ Student not found!");
                    }
                    break;

                case 3:
                    System.out.print("Enter Roll Number to search: ");
                    String searchRoll = sc.nextLine().trim();
                    Student found = sms.searchStudent(searchRoll);
                    if (found != null) {
                        System.out.println("🔎 Found: " + found);
                    } else {
                        System.out.println("❌ Student not found!");
                    }
                    break;

                case 4:
                    System.out.println("\n📋 All Students:");
                    sms.displayAllStudents();
                    break;

                case 5:
                    System.out.println("🙏 Exiting... Data saved successfully!");
                    break;

                default:
                    System.out.println("⚠ Invalid choice. Please try again.");
            }
        } while (choice != 5);

        sc.close();
    }
}