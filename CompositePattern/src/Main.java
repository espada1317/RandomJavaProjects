import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        Human child_1 = new Child("Denis", "Trifan");
        Human child_2 = new Child("Iaroslav", "Trifan");

        ParentForKids parent_1 = new ParentForKids("Diana", "Trifan");
        parent_1.addKid(child_1);
        parent_1.addKid(child_2);

        Human parent_2 = new Child("Cernit", "Roman");

        ParentForKids grandParent_1 = new ParentForKids("Cernit", "Antonina");
        grandParent_1.addKid(parent_1);
        grandParent_1.addKid(parent_2);

        ArrayList<Human> humanArrayList = new ArrayList<>();
        humanArrayList.add(child_1);
        humanArrayList.add(child_2);
        humanArrayList.add(parent_1);
        humanArrayList.add(parent_2);
        humanArrayList.add(grandParent_1);

        for(Human x: humanArrayList)
        {
            System.out.println("\n#################");
            x.printInfo();
            System.out.println("#################\n");
        }
    }
}