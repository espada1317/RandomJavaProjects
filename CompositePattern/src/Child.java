public class Child implements Human
{
    private String firstName;
    private String lastName;

    public Child(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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
    }

    @Override
    public void printAsChild() {
        System.out.println("   - " + firstName + " " + lastName);
    }
}