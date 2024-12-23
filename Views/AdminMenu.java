package Views;

import java.util.Scanner;
import java.util.Date;
import People.*;
import DataBase.Data;

public class AdminMenu {
    private Admin admin;
    private Scanner scanner;

    public AdminMenu(Admin admin) {
        this.admin = admin;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        int choice;
        do {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add User");
            System.out.println("2. Remove User");
            System.out.println("3. View Logs");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addUserMenu();
                    Data.addAdmin(admin);
                    break;
                case 2:
                    removeUserMenu();
                    Data.removeAdmin(admin);
                    break;
                case 3:
                    admin.viewLogs();

                    break;
                case 4:
                    System.out.println("Exiting Admin Menu.");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 4);
    }

    private void addUserMenu() {
        System.out.println("\n--- Add User ---");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        User user = new User(username, password, new Date(), phoneNumber, email, name, surname);
        admin.addUser(user);
        System.out.println("User added successfully!");
    }

    private void removeUserMenu() {
        System.out.println("\n--- Remove User ---");
        System.out.print("Enter username of the user to remove: ");
        String username = scanner.nextLine();
        for (User user : Data.getUsers()) {
            if (user.getUsername().equals(username)) {
                admin.removeUser(user);
                System.out.println("User removed successfully!");
                return;
            }
        }
        System.out.println("User not found.");
    }
}
