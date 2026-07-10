import java.util.ArrayList;
import java.util.Scanner;

class Student {
    int id;
    String name;
    double grade;

    Student(int id, String name, double grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    String getGradeCategory() {
        if (grade >= 90)
            return "A";
        else if (grade >= 80)
            return "B";
        else if (grade >= 70)
            return "C";
        else if (grade >= 60)
            return "D";
        else
            return "F";
    }
}

public class StudentGradeTracker {

    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n====== Student Grade Tracker ======");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Show Statistics");
            System.out.println("4. Search Student");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    showStatistics();
                    break;

                case 4:
                    searchStudent();
                    break;

                case 5:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }


    static void addStudent() {

        System.out.print("Enter student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        double grade;

        while (true) {
            System.out.print("Enter grade (0-100): ");
            grade = sc.nextDouble();

            if (grade >= 0 && grade <= 100) {
                break;
            }

            System.out.println("Invalid grade! Enter between 0 and 100.");
        }

        students.add(new Student(id, name, grade));

        System.out.println("Student added successfully.");
    }


    static void viewStudents() {

        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.println("\n========== STUDENT REPORT ==========");
        System.out.printf("%-10s %-20s %-10s %-10s\n",
                "ID", "Name", "Grade", "Category");


        for (Student s : students) {

            System.out.printf("%-10d %-20s %-10.2f %-10s\n",
                    s.id,
                    s.name,
                    s.grade,
                    s.getGradeCategory());
        }
    }


    static void showStatistics() {

        if (students.isEmpty()) {
            System.out.println("No data available.");
            return;
        }

        double total = 0;
        double highest = students.get(0).grade;
        double lowest = students.get(0).grade;


        for (Student s : students) {

            total += s.grade;

            if (s.grade > highest)
                highest = s.grade;

            if (s.grade < lowest)
                lowest = s.grade;
        }


        double average = total / students.size();


        System.out.println("\n====== Statistics ======");
        System.out.printf("Average Score : %.2f\n", average);
        System.out.printf("Highest Score : %.2f\n", highest);
        System.out.printf("Lowest Score  : %.2f\n", lowest);
    }


    static void searchStudent() {

        System.out.print("Enter student name to search: ");
        String search = sc.nextLine();


        for (Student s : students) {

            if (s.name.equalsIgnoreCase(search)) {

                System.out.println("\nStudent Found:");
                System.out.println("ID: " + s.id);
                System.out.println("Name: " + s.name);
                System.out.println("Grade: " + s.grade);
                System.out.println("Category: " + s.getGradeCategory());
                return;
            }
        }

        System.out.println("Student not found.");
    }
}