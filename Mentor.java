
/**
 * Feature 3: second interface of the "diamond". Its default method clashes
 * with Teacher.describeDuty(), so the implementing class must resolve it.
 */
public interface Mentor {
    String mentor();

    default String describeDuty() {
        return "Mentor: guides a batch of students";
    }
}
