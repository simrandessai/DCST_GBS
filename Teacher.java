
/**
 * Feature 3: first interface of the "diamond". Has an abstract method and a
 * default method (implementation inheritance).
 */
public interface Teacher {
    String teach();

    default String describeDuty() {
        return "Teacher: delivers lectures and labs";
    }
}
