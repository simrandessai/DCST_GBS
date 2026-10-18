// NonTeachingStaff class with inheritance from Person class
// This class represents a non-teaching staff member in the system. It extends the Person class and adds specific attributes and behaviors related to non-teaching staff members.
public class NonTeachingStaff extends Person {
    private static final long serialVersionUID = 1L;
    private final String post;

    public NonTeachingStaff(String name, String post) {
        super(name);
        this.post = post;
    }

    public String getPost() {
        return post;
    }

    @Override
    public String getRole() {
        return "Non-Teaching Staff";
    }

    @Override
    public String report() {
        return String.format("[Non-Teaching Staff @%d] %s | %s", getMemberId(), name, post);
    }
}
