public class GlassSecurityFactory implements KeyLockFactory
{
    @Override
    public Key createKey() {
        return new GlassKey();
    }

    @Override
    public Lock createLock() {
        return new GlassLock();
    }
}