public class GlassKey implements Key
{
    @Override
    public void unlock() {
        System.out.println("You unlocked a lock with Glass Key!");
    }
}