import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FamilyTreeApp {
    public static void main(String[] args) {
        FamilyTreeIO familyTreeIO = new FamilyTreeIOImpl();
        FamilyTree familyTree = new FamilyTree();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Person");
            System.out.println("2. Find and Display Person by ID");
            System.out.println("3. Display Family Tree by ID");
            System.out.println("4. Save Family Tree to File");
            System.out.println("5. Load Family Tree from File");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = promptForInt(scanner);

            switch (choice) {
                case 1:
                    familyTree.inputPersonData();
                    break;
                case 2:
                    System.out.print("Enter ID of person to find and display: ");
                    int id = promptForInt(scanner);
                    familyTree.displayPersonInfo(id);
                    break;
                case 3:
                    System.out.print("Enter ID of person to display family tree: ");
                    int treeId = promptForInt(scanner);
                    familyTree.displayFamilyTree(treeId);
                    break;
                case 4:
                    System.out.print("Enter filename to save: ");
                    String saveFilename = scanner.nextLine();
                    try {
                        familyTreeIO.saveFamilyTree(familyTree, saveFilename);
                        System.out.println("Family tree saved to " + saveFilename);
                    } catch (IOException e) {
                        System.out.println("Error saving family tree: " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.print("Enter filename to load: ");
                    String loadFilename = scanner.nextLine();
                    try {
                        familyTree = familyTreeIO.loadFamilyTree(loadFilename);
                        System.out.println("Family tree loaded from " + loadFilename);
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Error loading family tree: " + e.getMessage());
                    }
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static int promptForInt(Scanner scanner) {
        while (true) {
            try {
                int value = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // Clear invalid input
            }
        }
    }
}
