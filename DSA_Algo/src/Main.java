public class Main {
    public static void main(String[] args) {
        DSAEncryptionManual dsa = DSAEncryptionManual.GetObject();
        String initialMessage = "Hello World!";
        Signature signature = dsa.signData(initialMessage);

        String checkMessage = "Hello World!";
        System.out.println("\nCheck if signature is same : " + dsa.verifySignature(checkMessage, signature));
    }
}