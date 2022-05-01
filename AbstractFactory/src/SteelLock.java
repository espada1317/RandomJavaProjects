public class SteelLock implements Lock
{
    @Override
    public void lock() {
        System.out.println("You locked the Steel Lock!");
    }
}