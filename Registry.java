
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Feature 8: generic class (bounded type parameter) + generic methods.
 */
public class Registry<T extends Reportable> implements Iterable<T> {
    private final String title;
    private final List<T> items = new ArrayList<>();

    public Registry(String title) { this.title = title; }

    public void add(T item) { items.add(item); }
    public T get(int index) { return items.get(index); }
    public int size() { return items.size(); }

    public void printAll() {
        System.out.println("   Registry<" + title + "> holds " + items.size() + " item(s):");
        for (T t : items) {
            System.out.println("     - " + t.report());
        }
    }

    @Override
    public Iterator<T> iterator() { return items.iterator(); }

    // Generic static methods
    public static <E> E first(List<E> list) {
        return list.isEmpty() ? null : list.get(0);
    }

    public static <E extends Reportable> void printAll(Collection<E> c, String label) {
        System.out.println("   " + label + " (" + c.size() + "):");
        for (E e : c) System.out.println("     * " + e.report());
    }
}
