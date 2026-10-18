
import java.io.Serializable;

/**
 * Feature 5: abstract class. Academic and non-academic activities derive from
 * it and implement category() / execute() differently.
 */
public abstract class Activity implements Serializable, Reportable {
    private static final long serialVersionUID = 1L;

    protected final String name;
    protected final String academicYear;
    protected final String description;

    protected Activity(String name, String academicYear, String description) {
        this.name = name;
        this.academicYear = academicYear;
        this.description = description;
    }

    public String getName() { return name; }
    public String getAcademicYear() { return academicYear; }

    public abstract String category();
    public abstract String execute();

    @Override
    public String report() {
        return "[" + category() + "] " + name + " (" + academicYear + ") - " + description;
    }
}
