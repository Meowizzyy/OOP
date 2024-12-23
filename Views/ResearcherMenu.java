package Views;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import Entities.DiplomaProject;
import People.User;
import ResearcherStuff.ResearchPaper;
import People.Researcher;

public class ResearcherMenu {
    private Researcher researcher;
    private Scanner scanner;

    public ResearcherMenu(Researcher researcher) {
        this.researcher = researcher;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        int choice;
        do {
            System.out.println("\n--- Researcher Menu ---");
            System.out.println("1. Add Research Paper");
            System.out.println("2. View Research Papers");
            System.out.println("3. List Research Papers");
            System.out.println("4. Check Supervisor Eligibility");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addResearchPaperMenu();
                    break;
                case 2:
                    viewResearchPapersMenu();
                    break;
                case 3:
                    listResearchPapersMenu();
                    break;
                case 4:
                    checkSupervisorEligibilityMenu();
                    break;
                case 5:
                    System.out.println("Exiting Researcher Menu.");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 5);
    }

    private void addResearchPaperMenu() {
        System.out.println("\n--- Add Research Paper ---");
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter date published (YYYY-MM-DD): ");
        String datePublishedInput = scanner.nextLine();
        Date datePublished;
        try {
            datePublished = new SimpleDateFormat("yyyy-MM-dd").parse(datePublishedInput);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Using current date instead.");
            datePublished = new Date();
        }

        System.out.print("Enter author username: ");
        String authorUsername = scanner.nextLine();
        System.out.print("Enter author password: ");
        String authorPassword = scanner.nextLine();
        System.out.print("Enter author name: ");
        String authorName = scanner.nextLine();
        System.out.print("Enter author surname: ");
        String authorSurname = scanner.nextLine();
        User author = new User(authorUsername, authorPassword, null, null, null, authorName, authorSurname);

        System.out.print("Enter number of pages: ");
        int pages = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter publisher: ");
        String publisher = scanner.nextLine();

        System.out.print("Enter citation: ");
        String citation = scanner.nextLine();

        // Если у вас есть объект DiplomaProject, используйте его
        DiplomaProject diplomaProject = null; // Или создайте объект DiplomaProject, если требуется

        ResearchPaper paper = new ResearchPaper(title, datePublished, author, pages, publisher, citation, diplomaProject);

        researcher.addResearchPaper(paper);
        System.out.println("Research paper added successfully.");
    }

    private void viewResearchPapersMenu() {
        System.out.println("\n--- View Research Papers ---");
        System.out.print("Enter comparator type (1 for date, 2 for pages): ");
        String comparatorType = scanner.nextLine();
        System.out.println(researcher.viewResearchPapers(comparatorType));
    }

    private void listResearchPapersMenu() {
        System.out.println("\n--- List Research Papers ---");
        researcher.listResearchPapers();
    }

    private void checkSupervisorEligibilityMenu() {
        System.out.println("\n--- Check Supervisor Eligibility ---");
        if (researcher.canBeSupervisor()) {
            System.out.println("The researcher is eligible to be a supervisor.");
        } else {
            System.out.println("The researcher is not eligible to be a supervisor.");
        }
    }
}
