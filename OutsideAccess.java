/**
 * Feature 4: a separate class OUTSIDE AccessDemo.
 * It is a subclass, so it can use public, protected and default members,
 * but never the private ones directly.
 */
public class OutsideAccess extends AccessDemo {

    public void run() {
        // Via inheritance (this.)
        System.out.println("   [outside class, subclass]  " + publicField);
        System.out.println("   [outside class, subclass]  " + protectedField);
        System.out.println("   [outside class, subclass]  " + protectedMethod());

        // Via an object of the parent class
        AccessDemo other = new AccessDemo();
        System.out.println("   [outside class, object]    " + other.publicField);
        System.out.println("   [outside class, object]    private via getter -> " + other.getPrivateField());

        // This line would NOT compile (uncomment to see the error):
        // System.out.println(other.privateField);    // private to AccessDemo
    }
}
