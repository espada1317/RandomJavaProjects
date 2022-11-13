package proxy;

import java.io.FileWriter;
import java.io.IOException;

public class TempSaveService implements ThirdPartySaveState {

    @Override
    public void performSaveTempOperation(String nameCipher, String keyInputField, String privateKeyInputField, String encryptInput, String decryptInput) {
        try {
            FileWriter myWriter = new FileWriter("temp.txt");
            myWriter.write("CipherMethod: " + nameCipher + "\n");
            myWriter.write("PublicKey: " + keyInputField + "\n");
            myWriter.write("PrivateKey: " + privateKeyInputField + "\n");
            myWriter.write("Message: " + encryptInput + "\n");
            myWriter.write("EncryptedMessage: " + decryptInput);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
