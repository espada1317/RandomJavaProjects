public class MagicSecurityFactory implements KeyLockFactory
{
    @Override
    public Key createKey() {
        return new MagicKey();
    }

    @Override
    public Lock createLock() {
        return new MagicLock();
    }
}