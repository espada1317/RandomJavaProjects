package ciphers;

public abstract class CipherClass {
    public abstract String getDescription();
    public abstract Boolean getRequireKey();
    public abstract Boolean getRequireGeneratedKey();
    public abstract Boolean getRequirePrivateKey();
    public abstract String getNameCipher();
    public abstract String encrypt(String message, String key) throws Exception;
    public abstract String decrypt(String message, String key) throws Exception;
}