import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Random;

public class Chernovik {

//    public static BigInteger encryptThisString(String input) {
//        try {
//            MessageDigest md = MessageDigest.getInstance("SHA-1");
//            byte[] messageDigest = md.digest(input.getBytes());
//            BigInteger no = new BigInteger(1, messageDigest);
//
//            return no;
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//    }

    // VERIFY IF BIGINTEGER NUMBER IS PRIME
//    static boolean returnPrime(BigInteger number) {
//        if (!number.isProbablePrime(1))
//            return false;
//
//        BigInteger two = new BigInteger("2");
//        if (!two.equals(number) && BigInteger.ZERO.equals(number.mod(two)))
//            return false;
//
//        for (BigInteger i = new BigInteger("3"); i.multiply(i).compareTo(number) < 1; i = i.add(two)) {
//            System.out.println(i);
//            if (BigInteger.ZERO.equals(number.mod(i)))
//                return false;
//        }
//        return true;
//    }

    // ------------------------------------------------------------------------------------------------------

    // GENERATE BIGINTEGER PRIME NUMBER WITH CONSTRUCTOR
//        int bitLength = 159; // 512 bits
//        SecureRandom rnd = new SecureRandom();
//        int certainty = 90; // 1 - 1/2(90) certainty
//        System.out.println("BitLength : " + bitLength);
//        BigInteger mod = new BigInteger(bitLength, certainty, rnd);
//        BigInteger exponent = BigInteger.probablePrime(bitLength, rnd);
//        BigInteger n = BigInteger.probablePrime(bitLength, rnd);
//
//        BigInteger result = n.modPow(exponent, mod);
//        System.out.println("Number ^ Exponent MOD Modulus = Result");
//        System.out.println("Number");
//        System.out.println(n);
//        System.out.println("Exponent");
//        System.out.println(exponent);
//        System.out.println("Modulus");
//        System.out.println(mod);
//        System.out.println("Result");
//        System.out.println(result);
//
//        if( returnPrime(n) ) {
//            System.out.println("\n\nnumber " + n + "  is prime");
//        } else {
//            System.out.println("JOpa");
//        }

    //        BigInteger N = new BigInteger("7");
//        BigInteger A = new BigInteger("3404825447");
//        BigInteger root = nthRoot(A,N);
//        System.out.println(A + "th root of " + N + " is " + root);

//        // Generating alpha value
//        BigInteger g_value = new BigInteger("2");
//        while(true) {
//            BigInteger exponent = p_prime.subtract(BigInteger.ONE).divide(q_prime);
//            BigInteger temp = g_value.modPow(exponent, p_prime);
//            System.out.println("G = " + g_value + "  alpha = " + temp);
//            if( g_value.modPow(q_prime, p_prime).equals(BigInteger.ONE) && !temp.equals(BigInteger.ONE) ) {
//                alpha = temp;
//                System.out.println("alpha: " + alpha);
//                break;
//            }
//            g_value = g_value.add(BigInteger.ONE);
//        }

//    public static BigInteger randomize(BigInteger maxValue) {
//        Random rand = new Random();
//        BigInteger result = new BigInteger(maxValue.bitLength(), rand);
//        while( result.compareTo(maxValue) >= 0 ) {
//            result = new BigInteger(maxValue.bitLength(), rand);
//        }
//        return result;
//    }


    //        BigInteger q_prime;
//        BigInteger p_prime;
//        BigInteger alpha = null;
//        BigInteger x_private;
//        BigInteger y_public;
//        BigInteger r_value;
//        BigInteger k_value;
//        BigInteger s_value;
//        BigInteger k_inverse_value;
//        BigInteger w_value;
//        BigInteger u1_value;
//        BigInteger u2_value;
//        BigInteger v_value;
//
//        //Generation signature
//        // Message digest a message by SHA-1 hash function
//        String initial_message = "Hello World!";
//        BigInteger messageDigest = encryptThisString(initial_message).abs();
//        System.out.println("Hashed message " + initial_message + " : " + messageDigest);
//
//        while(true) {
//            // Generating k
//            k_value = generateRandomBigInteger( BigInteger.ONE, q_prime.subtract(BigInteger.ONE) );
//            BigInteger firstPart = alpha.modPow(k_value, p_prime);
//            // Calculating r
//            r_value = firstPart.mod( q_prime );
//            if(!r_value.equals(BigInteger.ZERO)) {
//                k_inverse_value = k_value.modInverse(q_prime);
//                BigInteger secondPart = r_value.multiply(x_private);
//                BigInteger thirdPart = messageDigest.add(secondPart);
//                s_value = k_inverse_value.multiply(thirdPart).mod(q_prime);
//                if(!s_value.equals(BigInteger.ZERO)) {
//                    System.out.println("k_value : " + k_value);
//                    System.out.println("r value : " + r_value);
//                    System.out.println("s value : " + s_value);
//                    break;
//                }
//            }
//        }
//
////        Verify signature
//        if(r_value.compareTo(BigInteger.ZERO) > 0 && r_value.compareTo(q_prime) < 0) {
//            if (s_value.compareTo(BigInteger.ZERO) > 0 && s_value.compareTo(p_prime) < 0) {
//                w_value = s_value.modInverse(q_prime);
//                u1_value = w_value.multiply(messageDigest).mod(q_prime);
//                u2_value = w_value.multiply(r_value).mod(q_prime);
//
//                System.out.println("w value : " + w_value);
//                System.out.println("u1 value : " + u1_value);
//                System.out.println("u2 value : " + u2_value);
//
//                BigInteger firstPart = alpha.modPow(u1_value, p_prime);
//                BigInteger secondPart = alpha.modPow(u2_value, p_prime);
//                BigInteger thirdPart = firstPart.multiply(secondPart).mod(p_prime);
//                v_value = thirdPart.mod(q_prime);
//
//                System.out.println("v value : " + v_value);
////                System.out.println("alpha^u1 : " + firstPart);
////                System.out.println("y^u2 : " + secondPart);
////                System.out.println("( alpha^u1 * y^u2 ) mod p : " + thirdPart);
//
//                if(v_value.equals(r_value)) {
//                    System.out.println("Signature is valid!");
//                } else {
//                    System.out.println("Signature is not valid!");
//                }
//            } else {
//                System.out.println("Signature (r,s)|(" + r_value + ", " + s_value + " is not valid");
//            }
//        }

    //                copy_u1 = entirePart1;
//                BigInteger entirePart2 = copy_u1.divide(max_int_value);
//                BigInteger restPart2 = copy_u1.mod(max_int_value);
//                System.out.println("entirePart : " + entirePart2 + "  |  restPart : " + restPart2);
//                copy_u1 = entirePart2;
//                BigInteger entirePart3 = copy_u1.divide(max_int_value);
//                BigInteger restPart3 = copy_u1.mod(max_int_value);
//                System.out.println("entirePart : " + entirePart3 + "  |  restPart : " + restPart3);
//                copy_u1 = entirePart3;
//                BigInteger entirePart4 = copy_u1.divide(max_int_value);
//                BigInteger restPart4 = copy_u1.mod(max_int_value);
//                System.out.println("entirePart : " + entirePart4 + "  |  restPart : " + restPart4);
//                copy_u1 = entirePart4;
//                BigInteger entirePart5 = copy_u1.divide(max_int_value);
//                BigInteger restPart5 = copy_u1.mod(max_int_value);
//                System.out.println("entirePart : " + entirePart5 + "  |  restPart : " + restPart5);

//                List<BigInteger> listOfDivisors = new ArrayList<>();
//                BigInteger copy_u1 = u1_value;
//                BigInteger divisor;
//                while(true) {
//                    BigInteger temp1 = max_int_value;
//                    while (true) {
//                        BigInteger temp2 = copy_u1.gcd(temp1);
//                        if (temp2.compareTo(BigInteger.ONE) > 0 || temp1.compareTo(max_int_value.multiply(BigInteger.TWO)) == 0) {
//                            System.out.println("cmmdc : " + temp2);
//                            divisor = copy_u1.divide(temp2);
//                            listOfDivisors.add(temp2);
//                            System.out.println("next_term : " + divisor);
//                            break;
//                        }
//                        temp1 = temp1.add(BigInteger.ONE);
//                    }
//                    copy_u1 = divisor;
//                    if(copy_u1.compareTo(max_int_value) < 0) {
//                        break;
//                    }
//                }

//                for (BigInteger i : listOfDivisors) {
//                    System.out.println("Divisor " + i);
//                }


    //                BigInteger copy_u1 = u1_value;
//                List<BigInteger> entirePart = new ArrayList<>();
//                List<BigInteger> restPart = new ArrayList<>();
//
//                while( copy_u1.divide(max_int_value).compareTo(BigInteger.ZERO) > 0 )  {
//                    BigInteger entirePartTemp = copy_u1.divide(max_int_value);
//                    BigInteger restPartTemp = copy_u1.mod(max_int_value);
//                    entirePart.add(entirePartTemp);
//                    restPart.add(restPartTemp);
//                    System.out.println("entirePart : " + entirePartTemp + "  |  restPart : " + restPartTemp);
//                    copy_u1 = entirePartTemp;
//                }

//                System.out.println(entirePart.get( entirePart.size() - 1 ).intValue());
//                BigInteger tmp = new BigInteger("11");
//                BigInteger finalValue = u1_value.pow(entirePart.get( entirePart.size() - 1 ).intValue());
//                for(int i = entirePart.size() - 1; i >=0; i++) {
//                    BigInteger powerTemp1 = finalValue.pow( max_int_value.intValue() );
//                    BigInteger powerTemp2 = u1_value.pow( restPart.get(i).intValue() );
//                    finalValue = powerTemp1.multiply(powerTemp2);
//                    tmp = tmp.multiply( max_int_value ).add( restPart.get(i) );
//                    System.out.println(tmp);
//                }

//                System.out.println(entirePart.get( entirePart.size() - 1 ).intValue());
//                BigInteger initialPower = u1_value.pow(entirePart.get( entirePart.size() - 1 ).intValue());
//                BigInteger maxPower1 = initialPower.pow( max_int_value.intValue() );
//                System.out.println(maxPower1);

//                System.out.println("jopa : " + new BigInteger("2").pow(max_int_value.divide(BigInteger.TWO).intValue()));

    //    public static BigInteger powExponent(BigInteger number, BigInteger exponent) {
//        BigInteger result = BigInteger.ONE;
//        for(BigInteger i = BigInteger.ONE; i.compareTo(exponent) < 0; i = i.add(BigInteger.ONE)) {
//            result = result.multiply(number);
//        }
//        return result;
//    }

//    public static BigInteger powExponent(BigInteger base, BigInteger exponent) {
//        BigInteger result = BigInteger.ONE;
//        while (exponent.signum() > 0) {
//            if (exponent.testBit(0)) result = result.multiply(base);
//            base = base.multiply(base);
//            exponent = exponent.shiftRight(1);
//        }
//        return result;
//    }



    //        // Generating Q prime value
//        BigInteger base = new BigInteger("2");
//        int exponent_Q = 159;
//        BigInteger minQValue = base.pow(exponent_Q).add(base.pow(exponent_Q - 1));
//        q_prime = minQValue.nextProbablePrime();
//        System.out.println("Q prime: " + q_prime);
//
//        // Generating P prime value
//        int exponent_P = 1023;
//        BigInteger minPValue = base.pow(exponent_P);
//        while(true){
//            BigInteger tempValue = q_prime.multiply(minPValue).add(BigInteger.ONE);
//            if( tempValue.mod(q_prime).equals(BigInteger.ONE) && tempValue.isProbablePrime(10)){
//                p_prime = tempValue;
//                System.out.println("P prime: " + p_prime);
//                break;
//            }
//            minPValue = minPValue.add(BigInteger.TWO);
//        }


//        // Generating alpha value
//        BigInteger temp = BigInteger.TWO;
//        while(true) {
////            BigInteger underRoot = temp.multiply(p_prime).add(BigInteger.ONE);
////            BigInteger possible_G = nthRoot(underRoot, q_prime);
//            BigInteger exponent = p_prime.subtract(BigInteger.ONE).divide(q_prime);
//            BigInteger possible_alpha = temp.modPow(exponent, p_prime);
////            System.out.println("under root " + underRoot + "  possible root " + possible_G);
////            System.out.println("G maybe: " + possible_G + " ;  alpha : " + possible_alpha);
////            if( possible_G.modPow(q_prime, p_prime).equals(BigInteger.ONE)) {
//            if( !possible_alpha.equals(BigInteger.ONE) ) {
//                //!possible_alpha.equals(BigInteger.ONE);
//                alpha = possible_alpha;
//                System.out.println("Alpha: " + alpha);
//                break;
//            }
//            temp = temp.add(BigInteger.ONE);
//        }

    // Generating x value
//        x_private = generateRandomBigInteger( BigInteger.ONE, q_prime.subtract(BigInteger.ONE) );
//        System.out.println("X private key : " + x_private);

//        // Calculating y value
//        y_public = alpha.modPow(x_private, p_prime);
//        System.out.println("Y public key : " + y_public);

    public static BigInteger nthRoot(BigInteger order, BigInteger base) {
        BigDecimal xPre = new BigDecimal( Math.random() % 10 );
        BigDecimal eps = new BigDecimal( "0.00001" );
        BigDecimal delX = new BigDecimal("2").pow(4096);
        BigDecimal xK = new BigDecimal("0.0");
        MathContext m = new MathContext(10);

        while(delX.compareTo(eps) > 0) {
            BigDecimal firstPart = new BigDecimal(base).subtract( new BigDecimal("1.0") ).multiply(xPre);  // (base - 1.0) * xPre
            BigDecimal secondPart = xPre.pow( base.intValue() - 1 );                                        // Math.pow(xPre, base - 1)
            BigDecimal thirdPart = new BigDecimal(order).divide(secondPart, m);                                // order / Math.pow(xPre, base - 1)
            BigDecimal fourthPart = firstPart.add(thirdPart);                                                  // (base - 1.0) * xPre + order / Math.pow(xPre, base - 1)
            xK = fourthPart.divide(new BigDecimal(base), m);
            BigDecimal temp = xK.subtract(xPre);
            delX = temp.abs();
            xPre = xK;
        }
        xK = xK.multiply(new BigDecimal("1000.0")).divide(new BigDecimal("1000.0"));

        return xK.round(m).toBigInteger();
    }

}
