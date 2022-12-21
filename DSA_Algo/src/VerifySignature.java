import java.math.BigInteger;

public class VerifySignature {
    public BigInteger W;
    public BigInteger U1;
    public BigInteger U2;
    public BigInteger V;
    public Boolean signatureOk;

    public VerifySignature(BigInteger w, BigInteger u1, BigInteger u2, BigInteger v) {
        this.W = w;
        this.U1 = u1;
        this.U2 = u2;
        this.V = v;
    }

    public void setSignatureOk(Boolean signatureOk) {
        this.signatureOk = signatureOk;
    }
}
