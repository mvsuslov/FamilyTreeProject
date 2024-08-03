import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FamilyTree {
    private List<Person> people;

    public FamilyTree() {
        this.people = new ArrayList<>();
    }

    public void addPerson(Person person) {
        this.people.add(person);
    }

    public Person findPersonById(int id) {
        for (Person person : people) {
            if (person.getId() == id) {
                return person;
            }
        }
        return null;
    }

    public List<Person> getAllChildren(Person person) {
        return person.getChildren();
    }

    public void inputPersonData() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine();

        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Person newPerson = new Person(id, name, gender, age);

        System.out.print("Enter Spouse ID (or -1 if none): ");
        int spouseId = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        if (spouseId != -1) {
            Person spouse = findPersonById(spouseId);
            if (spouse != null) {
                newPerson.setSpouse(spouse);
                spouse.setSpouse(newPerson);
            }
        }

        System.out.print("Enter Father ID (or -1 if unknown): ");
        int fatherId = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        if (fatherId != -1) {
            Person father = findPersonById(fatherId);
            if (father != null) {
                newPerson.setFather(father);
                father.addChild(newPerson);
            }
        }

        System.out.print("Enter Mother ID (or -1 if unknown): ");
        int motherId = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        if (motherId != -1) {
            Person mother = findPersonById(motherId);
            if (mother != null) {
                newPerson.setMother(mother);
                mother.addChild(newPerson);
            }
        }

        System.out.print("Enter number of children: ");
        int numberOfChildren = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        for (int i = 0; i < numberOfChildren; i++) {
            System.out.print("Enter Child ID: ");
            int childId = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            Person child = findPersonById(childId);
            if (child != null) {
                newPerson.addChild(child);
                if (newPerson.getGender().equalsIgnoreCase("Male")) {
                    child.setFather(newPerson);
                } else {
                    child.setMother(newPerson);
                }
            }
        }

        addPerson(newPerson);
    }
}
