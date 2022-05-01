import java.util.ArrayList;
import java.util.Scanner;

public class Rifle extends Weapons
{
    private static ArrayList<Attachments> attachments = new ArrayList<>();

    @Override
    public void equipAttachments()
    {
        attachments.clear();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give name for scope: ");
        String scopeName = scanner.nextLine();
        System.out.println("Give name for ammo magazine: ");
        String magazineName = scanner.nextLine();
        System.out.println("Give name for grip: ");
        String gripName = scanner.nextLine();

        Scope scope = new Scope();
        scope.equip(scopeName);
        attachments.add(scope);
        AmmoMagazine ammoMagazine = new AmmoMagazine();
        ammoMagazine.equip(magazineName);
        attachments.add(ammoMagazine);
        Grip grip = new Grip();
        grip.equip(gripName);
        attachments.add(grip);
    }

    @Override
    public void detachAttachments() {
        for( Attachments x: attachments )
        {
            x.detach();
        }
    }

    @Override
    public void showAttachments()
    {
        System.out.println("\nCurrent rifle Attachments:\n=======Info=====");
        for( Attachments x: attachments )
        {
            System.out.println(x.toString());
        }
        System.out.println("================\n");
    }
}