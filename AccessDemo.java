
/**
 * Feature 4: public, protected, default and private members.
 */
public class AccessDemo {
    public String publicField = "I am PUBLIC (visible everywhere)";
    protected String protectedField = "I am PROTECTED (package + subclasses)";
    String defaultField = "I am DEFAULT (same package only)";
    private String privateField = "I am PRIVATE (this class only)";

    public AccessDemo() { }

    public String getPrivateField() { return privateField; }

    protected String protectedMethod() { return "protectedMethod() called"; }

    private String privateMethod() { return "privateMethod() called"; }

    // Object created INSIDE the class -> everything is accessible
    public static void insideClassDemo() {
        AccessDemo obj = new AccessDemo();
        System.out.println("   [inside AccessDemo]  " + obj.publicField);
        System.out.println("   [inside AccessDemo]  " + obj.protectedField);
        System.out.println("   [inside AccessDemo]  " + obj.defaultField);
        System.out.println("   [inside AccessDemo]  " + obj.privateField);
        System.out.println("   [inside AccessDemo]  " + obj.privateMethod());
    }
}
