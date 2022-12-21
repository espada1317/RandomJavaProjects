import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class DSAEncryptionManual {
    public BigInteger Q;
    public BigInteger P;
    public BigInteger G;
    public BigInteger X;
    public BigInteger Y;

    static final int confidence = 10;
    static final BigInteger minQ_value = BigInteger.TWO.pow(159).add(BigInteger.ONE);
    static final BigInteger maxQ_value = BigInteger.TWO.pow(160).subtract(BigInteger.ONE);
    static final BigInteger minP_value = BigInteger.TWO.pow(512);
    static final BigInteger maxP_value = BigInteger.TWO.pow(1024).subtract(BigInteger.ONE);

    public DSAEncryptionManual(BigInteger q, BigInteger p, BigInteger g, BigInteger x, BigInteger y) {
        this.Q = q;
        this.P = p;
        this.G = g;
        this.X = x;
        this.Y = y;
    }

    public static DSAEncryptionManual GetObject() {
        BigInteger q_value = generateQ();
        BigInteger p_value = generateP(q_value);
        BigInteger g_value = generateG(q_value, p_value);
        BigInteger x_value = generateX(q_value);
        BigInteger y_value = generateY(g_value, x_value, p_value);

        System.out.println("Generating keys >");
        System.out.println("Q : " + q_value);
        System.out.println("P : " + p_value);
        System.out.println("G : " + g_value);
        System.out.println("X : " + x_value);
        System.out.println("Y : " + y_value);

        return new DSAEncryptionManual(q_value, p_value, g_value, x_value, y_value);
    }

    public Signature signData(byte[] messageArray) {
        BigInteger k_value = generateK(this.Q);
        BigInteger hash = hashMessage(messageArray);
        BigInteger r_value = generateR(this.G, k_value, this.Q, this.P);
        BigInteger s_value = generateS(k_value, this.Q, this.X, r_value, messageArray);

        System.out.println("\nGenerating signature >");
        System.out.println("R : " + r_value);
        System.out.println("S : " + s_value);
        System.out.println("Hash : " + hash);

        return new Signature(r_value, s_value);
    }

    public VerifySignature verifySignature(byte[] messageArray, Signature signature) {
        BigInteger r_value = signature.R;
        BigInteger s_value = signature.S;

        if(r_value.compareTo(BigInteger.ZERO) <= 0 && r_value.compareTo(this.Q) >= 0) {
            return null;
        }
        if(s_value.compareTo(BigInteger.ZERO) <= 0 && s_value.compareTo(this.Q) >= 0) {
            return null;
        }

        BigInteger hash = hashMessage(messageArray);
        BigInteger w_value = s_value.modInverse(this.Q);
        BigInteger u1_value = hash.multiply(w_value).mod(this.Q);
        BigInteger u2_value = r_value.multiply(w_value).mod(this.Q);
        BigInteger temp1 = this.G.modPow(u1_value, this.P);
        BigInteger temp2 = this.Y.modPow(u2_value, this.P);
        BigInteger temp3 = temp1.multiply(temp2).mod(this.P);
        BigInteger v_value = temp3.mod(this.Q);

        System.out.println("\nVerify signature >");
        System.out.println("W : " + w_value);
        System.out.println("Hash : " + hash);
        System.out.println("U1 : " + u1_value);
        System.out.println("U2 : " + u2_value);
        System.out.println("V : " + v_value);

        return new VerifySignature(w_value, u1_value, u2_value, v_value);
    }

    public static BigInteger generateQ() {
        return generateRandomBigInteger(minQ_value, maxQ_value).nextProbablePrime();
    }

    public static BigInteger generateP(BigInteger q) {
        BigInteger result;
        BigInteger minPValue = maxP_value;
        while(true){
            BigInteger tempValue = q.multiply(minPValue).add(BigInteger.ONE);
            if( tempValue.mod(q).equals(BigInteger.ONE) && tempValue.isProbablePrime(confidence)){
                result = tempValue;
                break;
            }
            minPValue = minPValue.subtract(BigInteger.ONE);
        }
        return result;
    }

    public static BigInteger generateG(BigInteger q, BigInteger p) {
        BigInteger result;
        BigInteger pReduced = p.subtract(BigInteger.ONE);
        BigInteger qReduced = pReduced.divide(q);

        do {
            result = generateRandomBigInteger(minP_value, maxP_value).nextProbablePrime();
        } while ( result.compareTo(pReduced) >= 0 && result.compareTo(BigInteger.ONE) <= 0 );

        return result.modPow(qReduced, p);
    }

    public static BigInteger generateX(BigInteger q) {
        return generateRandomBigInteger( BigInteger.ONE, q.subtract(BigInteger.ONE) );
    }

    public static BigInteger generateY(BigInteger g, BigInteger x, BigInteger p) {
        return g.modPow(x, p);
    }

    public static BigInteger generateK(BigInteger q){
        return generateRandomBigInteger(BigInteger.ONE, q.subtract(BigInteger.ONE));
    }

    public static BigInteger generateR(BigInteger g, BigInteger k, BigInteger q, BigInteger p) {
        BigInteger temp = g.modPow(k, p);
        return temp.mod(q);
    }

    public static BigInteger generateS(BigInteger k, BigInteger q, BigInteger x, BigInteger r, byte[] messageArray) {
        BigInteger modInverseK = k.modInverse(q);
        BigInteger temp = x.multiply(r).add(hashMessage(messageArray));
        return modInverseK.multiply(temp).mod(q);
    }

    public static BigInteger generateRandomBigInteger(BigInteger minLimit, BigInteger maxLimit) {
        BigInteger bigInteger = maxLimit.subtract(minLimit);
        SecureRandom randNum = new SecureRandom();
        int len = maxLimit.bitLength();
        BigInteger res = new BigInteger(len, randNum);
        if (res.compareTo(minLimit) < 0)
            res = res.add(minLimit);
        if (res.compareTo(bigInteger) >= 0)
            res = res.mod(bigInteger).add(minLimit);
        return res;
    }

    public static BigInteger hashMessage(byte[] messageArray) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(messageArray);

            return new BigInteger(1, messageDigest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}