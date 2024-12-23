package Views;

import java.util.Scanner;
import java.util.Date;
import java.util.Vector;

import DataBase.Data;
import People.*;
import Entities.*;

public class ManagerMenu {
    private Manager manager;
    private Scanner scanner;

    public ManagerMenu(Manager manager) {
        this.manager = manager;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        int choice;
        do {
            System.out.println("\n--- Manager Menu ---");
            System.out.println("1. Approve Student Registration");
            System.out.println("2. Create Statistical Reports");
            System.out.println("3. Assign Courses to Teachers");
            System.out.println("4. Post News");
            System.out.println("5. Delete News");
            System.out.println("6. View News");
            System.out.println("7. View Info About Students by GPA");
            System.out.println("8. View Info About Teachers");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    approveStudentRegistrationMenu();
                    break;
                case 2:
                    createStatisticalReportsMenu();
                    break;
                case 3:
                    assignCoursesToTeachersMenu();
                    break;
                case 4:
                    postNewsMenu();
                    break;
                case 5:
                    deleteNewsMenu();
                    break;
                case 6:
                    viewNewsMenu();
                    break;
                case 7:
                    viewInfoAboutStudentsByGPAMenu();
                    break;
                case 8:
                    viewInfoAboutTeachersMenu();
                    break;
                case 9:
                    System.out.println("Exiting Manager Menu.");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 9);
    }

    private void approveStudentRegistrationMenu() {
        System.out.println("\n--- Approve Student Registration ---");
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();

        Course course = Data.getCourses().stream().filter(c -> c.getCourseCode().equals(courseCode)).findFirst().orElse(null);
        Student student = Data.getStudents().stream().filter(s -> s.getId().equals(studentId)).findFirst().orElse(null);

        if (course != null && student != null) {
            if (manager.approveStudentRegistration(course, student)) {
                System.out.println("Student registration approved.");
            } else {
                System.out.println("Student registration denied.");
            }
        } else {
            System.out.println("Invalid course or student ID.");
        }
    }

    private void createStatisticalReportsMenu() {
        System.out.println("\n--- Create Statistical Reports ---");
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = Data.getStudents().stream().filter(s -> s.getId().equals(studentId)).findFirst().orElse(null);

        if (student != null) {
            manager.createStatisticalReports(student);
        } else {
            System.out.println("Student not found.");
        }
    }

    private void assignCoursesToTeachersMenu() {
        System.out.println("\n--- Assign Courses to Teachers ---");
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter teacher name: ");
        String teacherName = scanner.nextLine();

        Course course = Data.getCourses().stream().filter(c -> c.getCourseCode().equals(courseCode)).findFirst().orElse(null);
        Teacher teacher = Data.getTeacher().stream().filter(t -> t.getName().equals(teacherName)).findFirst().orElse(null);

        if (course != null && teacher != null) {
            manager.assignCoursesToTeachers(course, teacher);
            System.out.println("Course assigned to teacher successfully.");
        } else {
            System.out.println("Invalid course code or teacher name.");
        }
    }

    private void postNewsMenu() {
        System.out.println("\n--- Post News ---");
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter text: ");
        String text = scanner.nextLine();

        manager.postNews(new Date(), title, text);
        System.out.println("News posted successfully.");
    }

    private void deleteNewsMenu() {
        System.out.println("\n--- Delete News ---");
        System.out.print("Enter news title: ");
        String title = scanner.nextLine();

        News news = Data.getNews().stream().filter(n -> n.getTitle().equals(title)).findFirst().orElse(null);

        if (news != null) {
            manager.deleteNews(news);
            System.out.println("News deleted successfully.");
        } else {
            System.out.println("News not found.");
        }
    }

    private void viewNewsMenu() {
        System.out.println("\n--- View News ---");
        System.out.println(Data.getNews());
    }

    private void viewInfoAboutStudentsByGPAMenu() {
        System.out.println("\n--- View Info About Students by GPA ---");
        System.out.print("Sort ascending? (true/false): ");
        boolean ascending = Boolean.parseBoolean(scanner.nextLine());
        Vector<Student> students = manager.viewInfoAboutStudentsByGPA(ascending);

        for (Student student : students) {
            student.showInfo();
        }
    }

    private void viewInfoAboutTeachersMenu() {
        System.out.println("\n--- View Info About Teachers ---");
        for (Teacher teacher : Data.getTeacher()) {
            manager.viewInfoAboutTeachers(teacher);
        }
    }
}
