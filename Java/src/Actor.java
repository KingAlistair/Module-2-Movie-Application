public class Actor implements java.io.Serializable {
   private String name;
   private String role;

    public Actor(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " - " + role;
    }
}
