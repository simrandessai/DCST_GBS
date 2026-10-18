
import java.io.Serializable;

/**
 * Base class for every member of DCST (Student, Teaching Staff, Non-Teaching
 * Staff).
 * Feature 2: static counter + static method.
 * Feature 5: abstract class.
 */
public abstract class Person implements Serializable, Reportable {
    private static final long serialVersionUID = 1L;

    // Feature 2: counts every member created through a constructor
    private static int memberCount = 0;

    protected String name;
    private final int memberId;

    public Person(String name) {
        this.name = name;
        this.memberId = ++memberCount;
    }

    public static int getMemberCount() {
        return memberCount;
    }

    public static void displayMemberCount() {
        System.out.println("   >> Total DCST members created so far: " + memberCount);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMemberId() {
        return memberId;
    }

    // Feature 5: each subclass defines its own role
    public abstract String getRole();

    // Feature 7: overriding (subclasses override this)
    @Override
    public String report() {
        return "[" + getRole() + " @" + memberId + "] " + name;
    }

    // Feature 7: overloading (same name, different parameters)
    public String describe() {
        return report();
    }

    public String describe(boolean verbose) {
        return verbose ? report() + " | class=" + getClass().getSimpleName() : report();
    }
}
