public class Scope implements Attachments
{
    private String name;

    @Override
    public void equip(String name) {
        this.name = name;
        System.out.println("Equipped new scope: " + name);
    }

    @Override
    public void detach() {
        if(name == null)
            System.out.println("Nothing to detach!");
        else {
            System.out.println("Detached old scope: " + name + "!");
            this.name = "-";
        }
    }

    @Override
    public String toString() {
        return "Scope{" +
                "name='" + name + '\'' +
                '}';
    }
}