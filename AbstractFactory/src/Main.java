public class Main
{
    private static Chest configureLockKey(String type)
    {
        Chest chest = null;
        KeyLockFactory keyLockFactory;

        if(type.equals("magic"))
        {
            keyLockFactory = new MagicSecurityFactory();
            chest = new Chest(keyLockFactory);
        }
        else if(type.equals("glass"))
        {
            keyLockFactory = new GlassSecurityFactory();
            chest = new Chest(keyLockFactory);
        }
        else if(type.equals("steel"))
        {
            keyLockFactory = new SteelSecurityFactory();
            chest = new Chest(keyLockFactory);
        }

        return chest;
    }

    public static void main(String[] args)
    {
        Chest chest = configureLockKey("magic");
        chest.interact();

        chest = configureLockKey("glass");
        chest.interact();

        chest = configureLockKey("steel");
        chest.interact();
    }
}