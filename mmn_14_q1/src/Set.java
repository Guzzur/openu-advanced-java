import java.util.*;

/**
 * Generic Set
 * @param <T> represents generic type
 */
public class Set<T> {

    // holds the set members
    private ArrayList<T> members;

    /**
     * Default C'tor
     */
    public Set() {
        this.members = new ArrayList<T>();
    }

    /**
     * C'tor
     * @param elements holds the array of new elements to be stored as members
     */
    public Set(T[] elements) {
        this.members = new ArrayList<T>();
        for (T member : elements) {
            insert(member);
        }
    }

    /**
     * @return current set's size
     */
    public int size() {
        return this.members.size();
    }

    /**
     * Removes given element from set
     * @param element to be removed
     */
    public void delete(T element) {
        // call ArrayList.remove(element) that uses equals()
        this.members.remove(element);
    }

    /**
     * Checks existence of given element in the set
     * @param element to check for existence
     * @return true if found
     */
    public boolean isMember(T element) {
        // call ArrayList.contains(element) that uses equals()
        return this.members.contains(element);
    }

    /**
     * Inserts new element into the set
     * @param element to be inserted
     */
    public void insert(T element) {
        if (!isMember(element)) {
            this.members.add(element);
        }
    }

    /**
     * Union two sets into this
     * @param other set
     */
    public void union(Set<T> other) {
        if (other == null || other.size() == 0) {
            return;
        }

        Iterator<T> iter = other.iterator();
        while (iter.hasNext()) {
            this.insert(iter.next());
        }
    }

    /**
     * Creates the intersection within this
     * @param other set to intersect with
     */
    public void intersect(Set<T> other) {
        if (other == null || other.size() == 0) {
            return;
        }

        ArrayList<T> tempHolder = new ArrayList<T>();
        Iterator<T> iter = this.iterator();
        while (iter.hasNext()) {
            T element = iter.next();
            // check if the element exist in other set
            if (other.isMember(element)) {
                tempHolder.add(element);
            }
        }
        this.members = tempHolder;
    }

    /**
     * Checks if other set is this' subset
     * @param other set to check with
     * @return
     */
    public boolean isSubset(Set<T> other) {
        // an empty set is always a subset
        if (other == null || other.size() == 0) {
            return true;
        }

        Iterator<T> iter = other.iterator();
        while (iter.hasNext()) {
            if (!this.isMember(iter.next())) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return the iterator
     */
    public Iterator<T> iterator() { return members.iterator(); }

    /**
     * @return representation of set as a string
     */
    @Override
    public String toString() {
        String str = "{ ";
        for (int i = 0; i < this.members.size(); i++) {
            str += this.members.get(i).toString();

            // if not last element
            if (i < this.members.size() - 1) {
                str += ", ";
            }
        }
        return str + " }";
    }
}