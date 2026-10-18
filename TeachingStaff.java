
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Feature 3: Java allows only ONE parent class (Person) but MANY interfaces
 * (Teacher, Mentor, Reportable via Person).
 */
public class TeachingStaff extends Person implements Teacher, Mentor {
    private static final long serialVersionUID = 1L;

    public enum Designation {
        PROFESSOR, ASSOCIATE_PROFESSOR, ASSISTANT_PROFESSOR
    }

    private final Designation designation;
    private final List<String> subjects = new ArrayList<>();

    public TeachingStaff(String name, Designation designation, String... subjects) {
        super(name);
        this.designation = designation;
        this.subjects.addAll(Arrays.asList(subjects));
    }

    public Designation getDesignation() {
        return designation;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    @Override
    public String getRole() {
        return "Teaching Staff";
    }

    @Override
    public String teach() {
        return name + " teaches " + subjects;
    }

    @Override
    public String mentor() {
        return name + " mentors a batch of students";
    }

    // Both interfaces supply a default describeDuty() -> we MUST resolve the clash
    @Override
    public String describeDuty() {
        return Teacher.super.describeDuty() + "  +  " + Mentor.super.describeDuty();
    }

    @Override
    public String report() {
        return String.format("[Teaching Staff @%d] %s | %s | %s",
                getMemberId(), name, designation, subjects);
    }
}
