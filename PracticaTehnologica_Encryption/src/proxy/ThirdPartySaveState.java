package proxy;

public interface ThirdPartySaveState {
    public void performSaveTempOperation(String nameCipher, String keyInputField, String privateKeyInputField, String encryptInput, String decryptInput);
}