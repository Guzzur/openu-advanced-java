import java.util.*;

public class Main {
    private static final int MAX_RAND_RANGE = 100;
    private static final int INIT_ARRAY_SIZE = 10;

    public static void main(String[] args) {
        Random rand = new Random();
        Set<Integer> setInt1 = new Set<>();
        Set<Integer> setInt2 = new Set<>();
        Set<Integer> setInt3 = new Set<>();

        // generate values.
        for (int i = 0; i < INIT_ARRAY_SIZE; i++) {
            setInt1.insert(rand.nextInt(MAX_RAND_RANGE));
            setInt2.insert(rand.nextInt(MAX_RAND_RANGE));
            setInt3.insert(rand.nextInt(MAX_RAND_RANGE));
        }

        // print results
        System.out.println("setInt1: " + setInt1);
        System.out.println("setInt2: " + setInt2);
        System.out.println("setInt3: " + setInt3);

        // setInt1 = setInt1 union setInt2
        setInt1.union(setInt2);
        System.out.println("setInt1 union setInt2 = " + setInt1);

        // setInt1 = setInt1 intersect setInt3
        setInt1.intersect(setInt3);
        System.out.println("setInt1 intersect setInt3 = " + setInt1);

        // prepare to the user's input
        Set<Integer> setInt4 = new Set<>();
        Scanner scanner = new Scanner(System.in);

        // manipulations made by user
        boolean isSubset = false;
        System.out.println("Please insert the first element of setInt4:");
        setInt4.insert(scanner.nextInt());
        System.out.println("Please insert the second element of setInt4:");
        setInt4.insert(scanner.nextInt());

        // print result
        System.out.println("setInt4: " + setInt4);

        if (setInt1.isSubset(setInt4)) {
            isSubset = true;
            System.out.println("setInt4 is subset of setInt1");
        }
        if (setInt2.isSubset(setInt4)) {
            isSubset = true;
            System.out.println("setInt4 is subset of setInt2");
        }
        if (setInt3.isSubset(setInt4)) {
            isSubset = true;
            System.out.println("setInt4 is subset of setInt3");
        }
        if (!isSubset) {
            System.out.println("setInt4 is not a subset of any");
        }

        System.out.println("Please insert an integer:");
        Integer element = scanner.nextInt();
        String isMember = "";

        if (!setInt1.isMember(element)) {
            isMember = "not ";
        }
        System.out.println("Element " + element + " is " + isMember + "a member of setInt1");

        setInt2.insert(element);
        System.out.println("setInt2 after insertion: " + setInt2);
        setInt3.delete(element);
        System.out.println("setInt3 after deletion: " + setInt3);

        // find the youngest person
        Set<Person> setPersons = new Set<>();
        setPersons.insert(new Person("123", "Schwartz", 2));
        setPersons.insert(new Person("456", "Maggie", 1));
        setPersons.insert(new Person("789", "Sasha", 21));
        setPersons.insert(new Person("012", "Josh", 41));
        setPersons.insert(new Person("345", "Santa", 99));

        System.out.println("setPerson: " + setPersons);
        System.out.println("Youngest person is: " + MinimumSet.minimum(setPersons));
    }
}