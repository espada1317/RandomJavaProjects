import java.util.ArrayList;

public class ParentForKids implements Human
{
    private String firstName;
    private String lastName;
    private ArrayList<Human> humanArrayList;

    public ParentForKids(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.humanArrayList = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public void printInfo() {
        System.out.println("======Info======");
        System.out.println("First Name: " + getFirstName());
        System.out.println("Last Name: " + getLastName());
        System.out.println("Show child: ");
        for(Human x: humanArrayList)
        {
            x.printAsChild();
        }
    }

    @Override
    public void printAsChild() {
        System.out.println("   - " + firstName + " " + lastName);
    }

    public void addKid(Human human)
    {
        humanArrayList.add(human);
    }

    public void deleteKid(Human human)
    {
        humanArrayList.remove(human);
    }
}