public class SteelSecurityFactory implements KeyLockFactory
{
    @Override
    public Key createKey() {
        return new SteelKey();
    }

    @Override
    public Lock createLock() {
        return new SteelLock();
    }
}