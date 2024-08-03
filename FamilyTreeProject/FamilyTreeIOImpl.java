import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FamilyTreeIOImpl implements FamilyTreeIO {
    @Override
    public void saveFamilyTree(FamilyTree familyTree, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Person person : familyTree.getPeople()) {
                writer.write(person.getId() + "," + person.getName() + "," + person.getGender() + "," + person.getAge());
                if (person.getSpouse() != null) {
                    writer.write("," + person.getSpouse().getId());
                } else {
                    writer.write(",null");
                }
                if (person.getFather() != null) {
                    writer.write("," + person.getFather().getId());
                } else {
                    writer.write(",null");
                }
                if (person.getMother() != null) {
                    writer.write("," + person.getMother().getId());
                } else {
                    writer.write(",null");
                }
                writer.write(",");
                for (Person child : person.getChildren()) {
                    writer.write(child.getId() + " ");
                }
                writer.newLine();
            }
        }
    }

    @Override
    public FamilyTree loadFamilyTree(String filename) throws IOException {
        FamilyTree familyTree = new FamilyTree();
        Map<Integer, Person> personMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String gender = data[2];
                int age = Integer.parseInt(data[3]);

                Person person = new Person(id, name, gender, age);
                personMap.put(id, person);
                familyTree.addPerson(person);
            }
        }

        // Second pass to link relationships
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                Person person = personMap.get(id);

                int spouseId = data[4].equals("null") ? -1 : Integer.parseInt(data[4]);
                if (spouseId != -1) {
                    person.setSpouse(personMap.get(spouseId));
                }

                int fatherId = data[5].equals("null") ? -1 : Integer.parseInt(data[5]);
                if (fatherId != -1) {
                    person.setFather(personMap.get(fatherId));
                }

                int motherId = data[6].equals("null") ? -1 : Integer.parseInt(data[6]);
                if (motherId != -1) {
                    person.setMother(personMap.get(motherId));
                }

                if (data.length > 7 && !data[7].isEmpty()) {
                    String[] childrenIds = data[7].split(" ");
                    for (String childIdStr : childrenIds) {
                        int childId = Integer.parseInt(childIdStr);
                        person.addChild(personMap.get(childId));
                    }
                }
            }
        }

        return familyTree;
    }
}
