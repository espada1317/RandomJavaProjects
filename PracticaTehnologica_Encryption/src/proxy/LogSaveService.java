package proxy;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class LogSaveService implements ThirdPartySaveState {
    private TempSaveService tempSaveService;

    public LogSaveService(TempSaveService tempSaveService) {
        this.tempSaveService = tempSaveService;
    }

    public Boolean logDataToFile(String nameCipher, String keyInputField, String privateKeyInputField, String encryptInput, String decryptInput) {
        try {
            FileWriter myWriter = new FileWriter("logs.txt", true);
            myWriter.write("========================================================================================\n");
            myWriter.write("DateTime: " + LocalDate.now() + " " + LocalTime.now() + "\n");
            myWriter.write("CipherMethod: " + nameCipher + "\n");
            myWriter.write("PublicKey: " + keyInputField + "\n");
            myWriter.write("PrivateKey: " + privateKeyInputField + "\n");
            myWriter.write("Message: " + encryptInput + "\n");
            myWriter.write("EncryptedMessage: " + decryptInput + "\n");
            myWriter.write("========================================================================================\n");
            myWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void performSaveTempOperation(String nameCipher, String keyInputField, String privateKeyInputField, String encryptInput, String decryptInput) {
        if(logDataToFile(nameCipher, keyInputField, privateKeyInputField, encryptInput, decryptInput)) {
            tempSaveService.performSaveTempOperation(nameCipher, keyInputField, privateKeyInputField, encryptInput, decryptInput);
        }
    }
}
