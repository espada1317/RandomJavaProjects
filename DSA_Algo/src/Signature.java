import java.math.BigInteger;

public class Signature {
    public BigInteger R;
    public BigInteger S;

    public Signature(BigInteger r, BigInteger s) {
        this.R = r;
        this.S = s;
    }

    public BigInteger getR() {
        return R;
    }

    public BigInteger getS() {
        return S;
    }
}
