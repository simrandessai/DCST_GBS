
/**
 * Feature 9: custom (checked) exception.
 */
public class InvalidMarksException extends Exception {
    private static final long serialVersionUID = 1L;
    private final int marks;

    public InvalidMarksException(int marks) {
        super("Invalid marks: " + marks + " (must be between 0 and 100)");
        this.marks = marks;
    }

    public int getMarks() { return marks; }
}
