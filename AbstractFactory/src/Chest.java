public class Chest
{
    private Lock lock;
    private Key key;

    public Chest(KeyLockFactory factory)
    {
        lock = factory.createLock();
        key = factory.createKey();
    }

    public void interact()
    {
        lock.lock();
        key.unlock();
    }
}
