public class MagicLock implements Lock
{
    @Override
    public void lock() {
        System.out.println("You locked the Magic Lock!");
    }
}