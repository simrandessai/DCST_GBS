// Academic class with inheritance from Activity class
// This class represents an academic activity in the system. It extends the Activity class and adds specific attributes and behaviors related to academic activities.
public class AcademicActivity extends Activity {
    private static final long serialVersionUID = 1L;
    private final int participants;

    public AcademicActivity(String name, String academicYear, String description, int participants) {
        super(name, academicYear, description);
        this.participants = participants;
    }

    @Override
    public String category() {
        return "Academic";
    }

    @Override
    public String execute() {
        return name + " is conducted as per the academic calendar for about " + participants + " students";
    }
}
