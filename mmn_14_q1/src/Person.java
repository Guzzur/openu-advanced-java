/**
 * Person implements the Comparable interface
 */
public class Person implements Comparable<Person> {
    // holds the ID
    private String id;

    // holds the name
    private String name;

    // holds the age
    private int age;

    /**
     * C'tor
     */
    public Person(String id, String name, int age) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    /**
     * @return ID
     */
    public String getId() { return id; }

    /**
     * @return name
     */
    public String getName() { return name; }

    /**
     * @return age
     */
    public int getAge() { return age; }

    /**
     * Overriding of Comparable compareTo()
     * @param other
     * @return -1 for "<", 0 for "==", 1 for ">"
     */
    @Override
    public int compareTo(Person other) {
        if (other == null || other.getAge() < getAge()) {
            return -1;
        }
        return other.getAge() > getAge() ? 1 : 0;
    }

    /**
     * Checks if persons are equal (by age)
     * @param other person to compare with
     * @return
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Person)) {
            return false;
        }
        return this.compareTo((Person)other) == 0;
    }

    /**
     * @return string representation of Person
     */
    @Override
    public String toString() {
        return String.format("(%s, %s, %d)", getId(), getName(), getAge());
    }
}