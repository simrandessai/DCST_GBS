// NonAcademicActivity class with inheritance from Activity class
// This class represents a non-academic activity in the system. It extends the Activity class and adds specific attributes and behaviors related to non-academic activities.
public class NonAcademicActivity extends Activity {
    private static final long serialVersionUID = 1L;
    private final String organisedBy;

    public NonAcademicActivity(String name, String academicYear, String description, String organisedBy) {
        super(name, academicYear, description);
        this.organisedBy = organisedBy;
    }

    @Override
    public String category() {
        return "Non-Academic";
    }

    @Override
    public String execute() {
        return name + " is organised by " + organisedBy + " as a student-engagement event";
    }
}
