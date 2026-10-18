
import java.io.Serializable;

/**
 * Mutable helper object, used to show the difference between shallow and
 * deep cloning (Feature 10).
 */
public class Address implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    private String city;
    private String state;
    private String pin;

    public Address(String city, String state, String pin) {
        this.city = city;
        this.state = state;
        this.pin = pin;
    }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    @Override
    public Address clone() {
        try {
            return (Address) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    @Override
    public String toString() { return city + ", " + state + " - " + pin; }
}
