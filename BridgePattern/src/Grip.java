public class Grip implements Attachments
{
    private String name;

    @Override
    public void equip(String name) {
        this.name = name;
        System.out.println("Equipped new grip: " + name);
    }

    @Override
    public void detach() {
        if(name == null)
            System.out.println("Nothing to detach!");
        else {
            System.out.println("Detached old grip: " + name + "!");
            this.name = "-";
        }
    }

    @Override
    public String toString() {
        return "Grip{" +
                "name='" + name + '\'' +
                '}';
    }
}