public class MagicKey implements Key
{
    @Override
    public void unlock() {
        System.out.println("You unlocked a lock with Magic Key!");
    }
}