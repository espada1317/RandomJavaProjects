package config;

import java.util.Random;

public class UserConfig {
    public static final String USER_LOGIN = "trifan.denis1999@gmail.com";
    public static final String USER_PASSW = "nikitaOneLove";

    public static final int PASSW_GENERATED_LENGTH = 10;

    public static String generateRandomPassword() {
        int len = PASSW_GENERATED_LENGTH;
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%&";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }
}
