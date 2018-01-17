import java.util.*;

/**
 * MinimumSet implements the Comparable interface and return the minimum element in set
 */
public class MinimumSet {

    /**
     * @param set to work on
     * @return the minimum in set
     */
    public static <T extends Comparable<T>> T minimum(Set<T> set) {
        T min = null;
        Iterator<T> iter = set.iterator();
        while (iter.hasNext()) {
            T next = iter.next();
            if (min == null || min.compareTo(next) == -1) {
                min = next;
            }
        }
        return min;
    }
}