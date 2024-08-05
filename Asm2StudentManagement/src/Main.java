import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Select an option:");
            System.out.println("1 - Add a new student");
            System.out.println("2 - Display student list");
            System.out.println("3 - Remove student by code");
            System.out.println("4 - Sort students by grade in descending order");
            System.out.println("5 - Find student by code or name");
            System.out.println("6 - Filter students by grade");
            System.out.println("0 - Exit");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    input();
                    break;
                case 2:
                    output();
                    break;
                case 3:
                    System.out.println("Enter the student code to remove:");
                    String codeToRemove = scanner.nextLine();
                    removeByCode(codeToRemove);
                    break;
                case 4:
                    sortByGradeDesc();
                    break;
                case 5:
                    System.out.println("Enter student code or name to find:");
                    String keyword = scanner.nextLine();
                    Student foundStudent = findByCodeOrName(keyword);
                    if (foundStudent != null) {
                        System.out.println(foundStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 6:
                    System.out.println("Enter the minimum grade to filter:");
                    float minGrade = scanner.nextFloat();
                    List<Student> filteredStudents = filterByGrade(minGrade);
                    for (Student student : filteredStudents) {
                        System.out.println(student);
                    }
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 0);
    }

    // Add a new student
    public static void input() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter student information:");
        System.out.print("Code: ");
        String code = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Gender (1 for male, 0 for female): ");
        int gender = scanner.nextInt();
        System.out.print("Grade: ");
        float grade = scanner.nextFloat();

        Student student = new Student(code, name, age, email, phone, gender, grade);
        studentList.add(student);
    }

    // Display student list
    public static void output() {
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    // Remove student by code
    public static void removeByCode(String code) {
        Student toRemove = null;
        for (Student student : studentList) {
            if (student.getCode().equals(code)) {
                toRemove = student;
                break;
            }
        }
        if (toRemove != null) {
            studentList.remove(toRemove);
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    // Sort students by grade in descending order
    public static void sortByGradeDesc() {
        studentList.sort(Comparator.comparing(Student::getGrade).reversed());
        System.out.println("Students sorted by grade in descending order.");
    }

    // Find student by code or name
    public static Student findByCodeOrName(String keyword) {
        for (Student student : studentList) {
            if (student.getCode().equalsIgnoreCase(keyword) || student.getName().equalsIgnoreCase(keyword)) {
                return student;
            }
        }
        return null;
    }

    // Filter students by grade
    public static List<Student> filterByGrade(float minGrade) {
        List<Student> filteredStudents = new ArrayList<>();
        for (Student student : studentList) {
            if (student.getGrade() >= minGrade) {
                filteredStudents.add(student);
            }
        }
        return filteredStudents;
    }
}

class Student {
    private String code;
    private String name;
    private int age;
    private String email;
    private String phone;
    private int gender;
    private float grade;

    public Student(String code, String name, int age, String email, String phone, int gender, float grade) {
        this.code = code;
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.grade = grade;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getGender() {
        return gender;
    }

    public float getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", gender=" + (gender == 1 ? "Male" : "Female") +
                ", grade=" + grade +
                '}';
    }
}