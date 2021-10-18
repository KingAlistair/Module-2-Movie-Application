public class Actor {
    String name;
    String role;

    public Actor(String name) {
        this.name = name;
    }

    public Actor(String name, String role) {
        this.name = name;
        this.role = role;
    }

    @Override
    public String toString() {
        return name + " - " + role;
    }
}
