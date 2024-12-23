import DataBase.Data;
import ResearcherStuff.ResearchPaper;
import Views.*;
import People.*;
import Entities.*;
import Enumerators.*;


import java.util.Date;

public class Driver {
    public static void main(String[] args) {
        // Initialize data and objects
        Admin admin = new Admin("admin1", "password", new Date(), "123456789", "admin@uni.edu", "Admin", "User", 100000, new Date());
        Manager manager = new Manager();
        Teacher teacher = new Teacher("teacher1", "password", new Date(), "987654321", "teacher@uni.edu", "John", "Smith", 80000, new Date(), TeacherType.PROFESSOR);
        Student student = new BachelorStudent("student1", "password", new Date(), "123456789", "student@uni.edu", "Alice", "Doe", "B12345", 2, 3.5, 30, Faculty.FIT, "CS");
        Researcher researcher = new Researcher("researcher1", "password", "Eve", "Brown");

        Course course = new Course(3, "Introduction to Programming", "Programming 101", "CS101");
        DiplomaProject diplomaProject = new DiplomaProject("AI in Healthcare", null);

        // Add data to the database
        Data.addAdmin(admin);
        Data.addManager(manager);
        Data.addTeacher(teacher);
        Data.addStudent(student);
        Data.addCourse(course);

        // Admin menu testing
        System.out.println("\n=== Testing Admin Menu ===");
        AdminMenu adminMenu = new AdminMenu(admin);
        adminMenu.displayMenu();

        // Manager menu testing
        System.out.println("\n=== Testing Manager Menu ===");
        ManagerMenu managerMenu = new ManagerMenu(manager);
        managerMenu.displayMenu();

        // Teacher menu testing
        System.out.println("\n=== Testing Teacher Menu ===");
        TeacherMenu teacherMenu = new TeacherMenu(teacher);
        teacherMenu.displayMenu();

        // Student menu testing
        System.out.println("\n=== Testing Student Menu ===");
        StudentMenu studentMenu = new StudentMenu(student);
        studentMenu.displayMenu();

        // Researcher menu testing
        System.out.println("\n=== Testing Researcher Menu ===");
        ResearcherMenu researcherMenu = new ResearcherMenu(researcher);
        researcherMenu.displayMenu();

        // Test ResearchPaper creation
        System.out.println("\n=== Testing Research Paper Creation ===");
        ResearchPaper paper = new ResearchPaper(
                "Deep Learning Applications",
                new Date(),
                researcher,
                120,
                "Springer",
                "100 Citations",
                diplomaProject
        );
        researcher.addResearchPaper(paper);
        researcher.listResearchPapers();
    }
}
