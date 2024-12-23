package Views;

import java.util.Scanner;
import Entities.*;
import People.*;
import DataBase.Data;

public class TeacherMenu {
    private Teacher teacher;
    private Scanner scanner;

    public TeacherMenu(Teacher teacher) {
        this.teacher = teacher;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        int choice;
        do {
            System.out.println("\n--- Teacher Menu ---");
            System.out.println("1. View Courses");
            System.out.println("2. Add Course");
            System.out.println("3. Remove Course");
            System.out.println("4. View Students in a Course");
            System.out.println("5. Assign Marks to Students");
            System.out.println("6. View Complaints");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    viewCoursesMenu();
                    break;
                case 2:
                    addCourseMenu();
                    break;
                case 3:
                    removeCourseMenu();
                    break;
                case 4:
                    viewStudentsInCourseMenu();
                    break;
                case 5:
                    assignMarksMenu();
                    break;
                case 6:
                    viewComplaintsMenu();
                    break;
                case 7:
                    System.out.println("Exiting Teacher Menu.");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 7);
    }

    private void viewCoursesMenu() {
        System.out.println("\n--- View Courses ---");
        for (Course course : teacher.getCourses()) {
            System.out.println(course.getCourseName());
        }
    }

    private void addCourseMenu() {
        System.out.println("\n--- Add Course ---");
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter credits: ");
        int credits = Integer.parseInt(scanner.nextLine());

        Course course = new Course(credits, "Description", courseName, courseCode);
        teacher.addCourse(course);
        Data.getCourses().add(course);
        System.out.println("Course added successfully.");
    }

    private void removeCourseMenu() {
        System.out.println("\n--- Remove Course ---");
        System.out.print("Enter course name to remove: ");
        String courseName = scanner.nextLine();

        Course course = teacher.getCourses().stream().filter(c -> c.getCourseName().equals(courseName)).findFirst().orElse(null);

        if (course != null) {
            teacher.removeCourse(course);
            Data.getCourses().remove(course);
            System.out.println("Course removed successfully.");
        } else {
            System.out.println("Course not found.");
        }
    }

    private void viewStudentsInCourseMenu() {
        System.out.println("\n--- View Students in a Course ---");
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();

        Course course = teacher.getCourses().stream().filter(c -> c.getCourseName().equals(courseName)).findFirst().orElse(null);

        if (course != null) {
            System.out.println(course.viewCurrentStudents());
        } else {
            System.out.println("Course not found.");
        }
    }

    private void assignMarksMenu() {
        System.out.println("\n--- Assign Marks to Students ---");
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter marks: ");
        double marks = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter type of marks (1 for Attestation 1, 2 for Attestation 2, 3 for Final): ");
        int typeOfPoints = Integer.parseInt(scanner.nextLine());

        Course course = teacher.getCourses().stream().filter(c -> c.getCourseName().equals(courseName)).findFirst().orElse(null);
        Student student = Data.getStudents().stream().filter(s -> s.getId().equals(studentId)).findFirst().orElse(null);

        if (course != null && student != null) {
            teacher.putMarks(student, course, marks, typeOfPoints);
            System.out.println("Marks assigned successfully.");
        } else {
            System.out.println("Invalid course or student ID.");
        }
    }

    private void viewComplaintsMenu() {
        System.out.println("\n--- View Complaints ---");
        System.out.println(teacher.sendCompliant());
    }
}
