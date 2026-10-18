
import java.util.ArrayList;
import java.util.List;

public class Student extends Person implements Cloneable {
    private static final long serialVersionUID = 1L;

    private Program program;
    private int year;
    private Address address;
    private List<Integer> marks = new ArrayList<>();
    private transient String tempNote; // Feature 10: transient fields are NOT persisted

    // Feature 1: constructor initialises all attributes
    public Student(String name, Program program, int year, Address address) {
        super(name);
        this.program = program;
        this.year = year;
        this.address = address;
    }

    // Constructor overloading + chaining
    public Student(String name, Program program) {
        this(name, program, 1, new Address("Taleigao", "Goa", "403206"));
    }

    public Program getProgram() {
        return program;
    }

    public int getYear() {
        return year;
    }

    public void nextYear() {
        year++;
    }

    public Address getAddress() {
        return address;
    }

    public List<Integer> getMarks() {
        return marks;
    }

    public String getTempNote() {
        return tempNote;
    }

    public void setTempNote(String tempNote) {
        this.tempNote = tempNote;
    }

    // Feature 9: throws the custom exception
    public void addMark(int mark) throws InvalidMarksException {
        if (mark < 0 || mark > 100) {
            throw new InvalidMarksException(mark);
        }
        marks.add(mark);
    }

    public double average() {
        if (marks.isEmpty())
            return 0.0;
        int sum = 0;
        for (int m : marks)
            sum += m;
        return (double) sum / marks.size();
    }

    @Override
    public String getRole() {
        return "Student";
    }

    @Override
    public String report() {
        return String.format("[Student @%d] %s | %s | Year %d | Avg %.1f | %s",
                getMemberId(), name, program, year, average(), address);
    }

    // Feature 10: shallow clone - copies references, address & marks are SHARED
    @Override
    public Student clone() {
        try {
            return (Student) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    // Feature 10: deep clone - nested objects are copied too
    public Student deepClone() {
        Student copy = clone();
        copy.address = this.address.clone();
        copy.marks = new ArrayList<>(this.marks);
        return copy;
    }
}
