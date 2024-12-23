package Views;

import java.util.Scanner;
import Entities.*;
import People.*;
import DataBase.*;

public class StudentMenu {
    private Student student;
    private Scanner scanner;

    public StudentMenu(Student student) {
        this.student = student;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        int choice;
        do {
            System.out.println("\n--- Student Menu ---");
            System.out.println("1. View Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Transcript");
            System.out.println("5. Rate a Teacher");
            System.out.println("6. Join an Organization");
            System.out.println("7. Leave Organization");
            if (student instanceof BachelorStudent) {
                System.out.println("8. View Diploma Project");
            } else if (student instanceof GraduateStudent) {
                System.out.println("8. View Research Project");
            }
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    viewCoursesMenu();
                    break;
                case 2:
                    registerForCourseMenu();
                    break;
                case 3:
                    dropCourseMenu();
                    break;
                case 4:
                    viewTranscriptMenu();
                    break;
                case 5:
                    rateTeacherMenu();
                    break;
                case 6:
                    joinOrganizationMenu();
                    break;
                case 7:
                    leaveOrganizationMenu();
                    break;
                case 8:
                    if (student instanceof BachelorStudent) {
                        viewDiplomaProjectMenu();
                    } else if (student instanceof GraduateStudent) {
                        viewResearchProjectMenu();
                    }
                    break;
                case 9:
                    System.out.println("Exiting Student Menu.");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 9);
    }

    private void viewCoursesMenu() {
        System.out.println("\n--- View Courses ---");
        System.out.println(student.viewCourses());
    }

    private void registerForCourseMenu() {
        System.out.println("\n--- Register for a Course ---");
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();

        Course course = Data.getCourses().stream().filter(c -> c.getCourseCode().equals(courseCode)).findFirst().orElse(null);

        if (course != null) {
            student.registerForCourses(course);
            System.out.println("Successfully registered for the course.");
        } else {
            System.out.println("Course not found.");
        }
    }

    private void dropCourseMenu() {
        System.out.println("\n--- Drop a Course ---");
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();

        Course course = Data.getCourses().stream().filter(c -> c.getCourseCode().equals(courseCode)).findFirst().orElse(null);

        if (course != null) {
            student.dropCourse(course);
            System.out.println("Successfully dropped the course.");
        } else {
            System.out.println("Course not found.");
        }
    }

    private void viewTranscriptMenu() {
        System.out.println("\n--- View Transcript ---");
        System.out.println(student.getTranscript());
    }

    private void rateTeacherMenu() {
        System.out.println("\n--- Rate a Teacher ---");
        System.out.print("Enter teacher's name: ");
        String teacherName = scanner.nextLine();
        System.out.print("Enter rating (0-10): ");
        int rating = Integer.parseInt(scanner.nextLine());

        Teacher teacher = Data.getTeacher().stream().filter(t -> t.getName().equals(teacherName)).findFirst().orElse(null);

        if (teacher != null) {
            student.rateTeacher(teacher, rating);
        } else {
            System.out.println("Teacher not found.");
        }
    }

    private void joinOrganizationMenu() {
        System.out.println("\n--- Join an Organization ---");
        System.out.print("Enter organization name: ");
        String orgName = scanner.nextLine();

        Organization organization = new Organization(orgName, student);
        student.joinOrganization(organization);
        System.out.println("Successfully joined the organization.");
    }

    private void leaveOrganizationMenu() {
        System.out.println("\n--- Leave Organization ---");
        student.leaveOrganization();
        System.out.println("Successfully left the organization.");
    }

    private void viewDiplomaProjectMenu() {
        System.out.println("\n--- View Diploma Project ---");
        BachelorStudent bachelorStudent = (BachelorStudent) student;
        System.out.println("Diploma Project: " + bachelorStudent.getDiplomaProject());
    }

    private void viewResearchProjectMenu() {
        System.out.println("\n--- View Research Project ---");
        GraduateStudent graduateStudent = (GraduateStudent) student;
        System.out.println("Research Project: " + graduateStudent.getProject());
    }
}
