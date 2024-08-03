import java.io.IOException;

public interface FamilyTreeIO {
    void saveFamilyTree(FamilyTree familyTree, String filename) throws IOException;
    FamilyTree loadFamilyTree(String filename) throws IOException, ClassNotFoundException;
}
