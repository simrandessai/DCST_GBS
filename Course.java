
import java.io.Serializable;

public class Course implements Serializable, Reportable {
    private static final long serialVersionUID = 1L;

    private final String code;
    private final String title;
    private final int credits;
    private final Program program;
    private final int semester;      // 0 = bridge course
    private final String type;       // DSC / DSE / Bridge / Core / Elective
    private final boolean assumed;   // true = not from the official syllabus

    public Course(String code, String title, int credits, Program program,
                  int semester, String type, boolean assumed) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.program = program;
        this.semester = semester;
        this.type = type;
        this.assumed = assumed;
    }

    // Constructor overloading: official-syllabus course
    public Course(String code, String title, int credits, Program program,
                  int semester, String type) {
        this(code, title, credits, program, semester, type, false);
    }

    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    public Program getProgram() { return program; }
    public int getSemester() { return semester; }
    public String getType() { return type; }
    public boolean isAssumed() { return assumed; }

    @Override
    public String report() {
        return String.format("%-9s %-46s %d cr  %-8s%s",
                code, title, credits, type, assumed ? " *" : "");
    }
}
