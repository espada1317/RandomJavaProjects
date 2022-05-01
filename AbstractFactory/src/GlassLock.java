public class GlassLock implements Lock
{
    @Override
    public void lock() {
        System.out.println("You locked the Glass Lock!");
    }
}