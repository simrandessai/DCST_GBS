
/**
 * Programmes offered by DCST, GBS (Feature 2: Enum).
 */
public enum Program {
    MCA("Master of Computer Applications", 2),
    MSC_AI("M.Sc. Artificial Intelligence", 2),
    MSC_DS("M.Sc. Data Science", 2),
    BSC_MSC_DS("B.Sc.-M.Sc. Integrated Data Science", 5);

    private final String fullName;
    private final int years;

    Program(String fullName, int years) {
        this.fullName = fullName;
        this.years = years;
    }

    public String getFullName() { return fullName; }
    public int getYears() { return years; }
}
