public class Actor implements java.io.Serializable {
    String name;
    String role;

    public Actor(String name, String role) {
        this.name = name;
        this.role = role;
    }

    @Override
    public String toString() {
        return name + " - " + role;
    }
}
