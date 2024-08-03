import java.util.List;
import java.util.Scanner;

public class FamilyTreeApp {
    public static void main(String[] args) {
        FamilyTree familyTree = new FamilyTree();

        familyTree.inputPersonData();  // Ввод данных о человеке

        System.out.print("Enter ID of person to find children: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        Person person = familyTree.findPersonById(id);

        if (person != null) {
            List<Person> children = familyTree.getAllChildren(person);
            System.out.println("Children of " + person.getName() + ":");
            for (Person child : children) {
                System.out.println(child);
            }
        } else {
            System.out.println("Person not found.");
        }
    }
}
