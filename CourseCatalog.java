
import java.util.ArrayList;
import java.util.List;

/**
 * Courses of Semester I and II.
 * - MCA: taken from the Goa University MCA syllabus.
 * - MSC_AI, MSC_DS, BSC_MSC_DS: ASSUMED subjects (marked with * in the report).
 * Replace them with the real syllabus when available.
 */
public final class CourseCatalog {
    private CourseCatalog() {
    }

    public static List<Course> all() {
        List<Course> c = new ArrayList<>();
        mca(c);
        mscAi(c);
        mscDs(c);
        bscMscDs(c);
        return c;
    }

    private static void real(List<Course> c, String code, String title, int cr, Program p, int sem, String type) {
        c.add(new Course(code, title, cr, p, sem, type));
    }

    private static void assumed(List<Course> c, String code, String title, int cr, Program p, int sem, String type) {
        c.add(new Course(code, title, cr, p, sem, type, true));
    }

    // ---------------- MCA (official syllabus) ----------------
    private static void mca(List<Course> c) {
        Program p = Program.MCA;
        real(c, "CSA-1000", "Bridge Course for MCA", 2, p, 0, "Bridge");
        // Semester I
        real(c, "CSA-5000", "Operating Systems", 3, p, 1, "DSC");
        real(c, "CSA-5001", "Object Oriented Software Engineering", 4, p, 1, "DSC");
        real(c, "CSA-5002", "Internet Technology", 3, p, 1, "DSC");
        real(c, "CSA-5003", "Problem Solving and Programming Lab", 2, p, 1, "DSC");
        real(c, "CSA-5004", "Linux Lab", 2, p, 1, "DSC");
        real(c, "CSA-5005", "Object Oriented Technology Lab", 2, p, 1, "DSC");
        real(c, "CSA-5201", "Mathematics for Computer Science - I", 2, p, 1, "DSE");
        real(c, "CSA-5202", "Fundamentals of Data Science", 2, p, 1, "DSE");
        real(c, "CSA-5203", "Operations Research", 4, p, 1, "DSE");
        // Semester II
        real(c, "CSA-5006", "Data Structures and Algorithms", 2, p, 2, "DSC");
        real(c, "CSA-5007", "Database Management Systems", 3, p, 2, "DSC");
        real(c, "CSA-5008", "Web Development", 1, p, 2, "DSC");
        real(c, "CSA-5009", "Fundamentals of Artificial Intelligence", 2, p, 2, "DSC");
        real(c, "CSA-5010", "Data Structures and Algorithms Lab", 2, p, 2, "DSC");
        real(c, "CSA-5011", "Database Management Systems Lab", 2, p, 2, "DSC");
        real(c, "CSA-5012", "Web Development Lab", 2, p, 2, "DSC");
        real(c, "CSA-5013", "Fundamentals of Artificial Intelligence Lab", 2, p, 2, "DSC");
        real(c, "CSA-5204", "Mathematics for Computer Science - II", 2, p, 2, "DSE");
        real(c, "CSA-5205", "Secure Coding", 2, p, 2, "DSE");
        real(c, "CSA-5206", "Data Mining", 2, p, 2, "DSE");
    }

    // ---------------- M.Sc. AI (assumed) ----------------
    private static void mscAi(List<Course> c) {
        Program p = Program.MSC_AI;
        assumed(c, "AI-5101", "Python Programming for AI", 3, p, 1, "Core");
        assumed(c, "AI-5102", "Mathematics for AI", 4, p, 1, "Core");
        assumed(c, "AI-5103", "Machine Learning", 4, p, 1, "Core");
        assumed(c, "AI-5104", "Data Structures and Algorithms", 3, p, 1, "Core");
        assumed(c, "AI-5105", "Python and ML Lab", 2, p, 1, "Core");
        assumed(c, "AI-5106", "Probability and Statistics", 2, p, 1, "Elective");
        assumed(c, "AI-5107", "Research Methodology", 2, p, 1, "Elective");
        assumed(c, "AI-5201", "Deep Learning", 4, p, 2, "Core");
        assumed(c, "AI-5202", "Natural Language Processing", 3, p, 2, "Core");
        assumed(c, "AI-5203", "Computer Vision", 3, p, 2, "Core");
        assumed(c, "AI-5204", "Knowledge Representation and Reasoning", 3, p, 2, "Core");
        assumed(c, "AI-5205", "Deep Learning Lab", 2, p, 2, "Core");
        assumed(c, "AI-5206", "Ethical Use of AI", 2, p, 2, "Elective");
        assumed(c, "AI-5207", "Reinforcement Learning", 2, p, 2, "Elective");
    }

    // ---------------- M.Sc. DS (assumed) ----------------
    private static void mscDs(List<Course> c) {
        Program p = Program.MSC_DS;
        assumed(c, "DS-5101", "Statistics for Data Science", 4, p, 1, "Core");
        assumed(c, "DS-5102", "Programming for Data Science (Python/R)", 3, p, 1, "Core");
        assumed(c, "DS-5103", "Linear Algebra and Optimization", 3, p, 1, "Core");
        assumed(c, "DS-5104", "Database Management Systems", 3, p, 1, "Core");
        assumed(c, "DS-5105", "Data Wrangling Lab", 2, p, 1, "Core");
        assumed(c, "DS-5106", "Programming Lab", 2, p, 1, "Core");
        assumed(c, "DS-5107", "Fundamentals of Data Science", 3, p, 1, "Elective");
        assumed(c, "DS-5201", "Machine Learning", 4, p, 2, "Core");
        assumed(c, "DS-5202", "Big Data Technologies", 3, p, 2, "Core");
        assumed(c, "DS-5203", "Data Visualization", 2, p, 2, "Core");
        assumed(c, "DS-5204", "Statistical Inference and Time Series", 3, p, 2, "Core");
        assumed(c, "DS-5205", "Machine Learning Lab", 2, p, 2, "Core");
        assumed(c, "DS-5206", "Big Data Lab", 2, p, 2, "Core");
        assumed(c, "DS-5207", "Data Mining", 2, p, 2, "Elective");
    }

    // ---------------- B.Sc.-M.Sc. integrated DS (assumed, undergraduate years)
    // ----------------
    private static void bscMscDs(List<Course> c) {
        Program p = Program.BSC_MSC_DS;
        assumed(c, "BDS-1101", "Introduction to Programming (Python)", 4, p, 1, "Core");
        assumed(c, "BDS-1102", "Calculus I", 4, p, 1, "Core");
        assumed(c, "BDS-1103", "Discrete Mathematics", 3, p, 1, "Core");
        assumed(c, "BDS-1104", "Introduction to Data Science", 3, p, 1, "Core");
        assumed(c, "BDS-1105", "Statistics I", 4, p, 1, "Core");
        assumed(c, "BDS-1106", "Programming Lab", 2, p, 1, "Core");
        assumed(c, "BDS-1201", "Data Structures and Algorithms", 4, p, 2, "Core");
        assumed(c, "BDS-1202", "Linear Algebra", 4, p, 2, "Core");
        assumed(c, "BDS-1203", "Probability Theory", 4, p, 2, "Core");
        assumed(c, "BDS-1204", "Database Management Systems", 3, p, 2, "Core");
        assumed(c, "BDS-1205", "Data Structures Lab", 2, p, 2, "Core");
        assumed(c, "BDS-1206", "Communication Skills", 2, p, 2, "Elective");
    }
}
