package constants;

import java.util.ArrayList;

public class Constants
{
    public final static String CIPHER_CAESAR_REGEX = "(0?[1-9]|1[0-9]|2[0-6])";
    public final static String CIPHER_CARDAN_GRILLE_REGEX = "(X|_)*";
    public final static String CIPHER_TEXT_REGEX = "([a-z]|[A-Z]|[0-9])*";
    public final static String CIPHER_AES_REGEX = "(([a-z]|[A-Z]|[0-9]|\\/|\\+)*==)";
    public final static String CIPHER_DES_REGEX = "(([a-z]|[A-Z]|[0-9]|\\/|\\+)*=)";
    public final static String CIPHER_3DES_REGEX = "(([a-z]|[A-Z]|[0-9]|\\/|\\+)*)";
    public final static String CIPHER_RSA_PAIR_REGEX = CIPHER_AES_REGEX + "|" + CIPHER_3DES_REGEX + "|" + CIPHER_DES_REGEX;

    public final static String CIPHER_DES_MANUAL_REGEX = "^\\p{ASCII}*$";

    public final static String BINARY_STRING_REGEX = "([0|1]+)";

    // JFRAME CONSTANTS
    public final static int FRAME_WIDTH                    = 900;
    public final static int FRAME_HEIGHT                   = 1000;

    // VISUAL FRAME
    public static final ArrayList<String> cipherAlgorithms;
    static {
        cipherAlgorithms = new ArrayList<>();
        cipherAlgorithms.add(" - Choose desired algorithm - ");
        cipherAlgorithms.add("CaesarCipher");
        cipherAlgorithms.add("CardanoGrille");
        cipherAlgorithms.add("PolybiusSquare");
        cipherAlgorithms.add("AveMaria");
        cipherAlgorithms.add("VigenereCipher");
        cipherAlgorithms.add("RC4");
        cipherAlgorithms.add("AES");
        cipherAlgorithms.add("DES");
        cipherAlgorithms.add("3DES");
        cipherAlgorithms.add("RSA");
        cipherAlgorithms.add("DES_Manual");
    }

    public final static String DEFAULT_SELECTED_ALG_CBOX   = " - Choose desired algorithm - ";
    public static Boolean REMOVED_DEFAULT_VALUE_CBOX       = false;
    public static int DESCRIPTION_POSITION                 = Constants.DESC_PANEL_BOUND_Y + Constants.DESC_PANEL_BOUND_HEIGHT;

    // SELECT ALGORITHM PANEL BOUNDS
    public final static int CBOX_PANEL_BOUND_X             = 5;
    public final static int CBOX_PANEL_BOUND_Y             = 5;
    public final static int CBOX_PANEL_BOUND_WIDTH         = FRAME_WIDTH - 25;
    public final static int CBOX_PANEL_BOUND_HEIGHT        = 60;

    // SELECT ALGORITHM COMBO-BOX BOUNDS
    public final static int CBOX_BOUND_X                   = 10;
    public final static int CBOX_BOUND_Y                   = 20;
    public final static int CBOX_BOUND_WIDTH               = FRAME_WIDTH - 55;
    public final static int CBOX_BOUND_HEIGHT              = 30;

    // DESCRIPTION PANEL BOUNDS
    public final static int DESC_PANEL_BOUND_X             = 5;
    public final static int DESC_PANEL_BOUND_Y             = 65;
    public final static int DESC_PANEL_BOUND_WIDTH         = Constants.FRAME_WIDTH - 25;
    public final static int DESC_PANEL_BOUND_HEIGHT        = 130;

    // DESCRIPTION TEXTAREA BOUNDS
    public final static int DESC_TAREA_BOUND_X             = 10;
    public final static int DESC_TAREA_BOUND_Y             = 17;
    public final static int DESC_TAREA_BOUND_WIDTH         = Constants.FRAME_WIDTH - 45;
    public final static int DESC_TAREA_BOUND_HEIGHT        = 50;

    // KEY PANEL BOUNDS
    public final static int KEY_PANEL_BOUND_X              = 5;
    public final static int KEY_PANEL_BOUND_Y              = DESCRIPTION_POSITION;
    public final static int KEY_PANEL_BOUND_WIDTH          = Constants.FRAME_WIDTH - 25;
    public final static int KEY_PANEL_BOUND_HEIGHT         = 50;

    // KEY TEXT FIELD BOUNDS
    public final static int KEY_TFIELD_BOUND_X             = 10;
    public final static int KEY_TFIELD_BOUND_Y             = 17;
    public final static int KEY_TFIELD_BOUND_WIDTH         = Constants.FRAME_WIDTH - 45;
    public final static int KEY_TFIELD_BOUND_HEIGHT        = 25;

    // TEXT TRANSFORMATION BOUNDs
    public final static int TRANSF_PANEL_BOUND_X           = 5;
    public final static int TRANSF_PANEL_BOUND_Y           = KEY_PANEL_BOUND_Y + KEY_PANEL_BOUND_HEIGHT;
    public final static int TRANSF_PANEL_BOUND_WIDTH       = Constants.FRAME_WIDTH - 25;
    public final static int TRANSF_PANEL_BOUND_HEIGHT      = FRAME_HEIGHT - TRANSF_PANEL_BOUND_Y - 43;

    // ENCRYPT LABEL
    public final static int ENCR_LABEL_BOUND_X             = 10;
    public final static int ENCR_LABEL_BOUND_Y             = 30;
    public final static int ENCR_LABEL_BOUND_WIDTH         = FRAME_WIDTH / 2;
    public final static int ENCR_LABEL_BOUND_HEIGHT        = 20;

    // ENCRYPT TEXT AREA
    public final static int ENCR_TAREA_BOUND_X             = 10;
    public final static int ENCR_TAREA_BOUND_Y             = ENCR_LABEL_BOUND_Y + 25;
    public final static int ENCR_TAREA_BOUND_WIDTH         = FRAME_WIDTH / 2 - ( ENCR_LABEL_BOUND_X + TRANSF_PANEL_BOUND_X ) - 15;
    public final static int ENCR_TAREA_BOUND_HEIGHT        = 600 - ENCR_TAREA_BOUND_Y;

    // DECRYPT LABEL
    public final static int DECR_LABEL_BOUND_X             = FRAME_WIDTH / 2 - 10;
    public final static int DECR_LABEL_BOUND_Y             = 30;
    public final static int DECR_LABEL_BOUND_WIDTH         = FRAME_WIDTH / 2;
    public final static int DECR_LABEL_BOUND_HEIGHT        = 20;

    // DECRYPT TEXT AREA
    public final static int DECR_TAREA_BOUND_X             = DECR_LABEL_BOUND_X;
    public final static int DECR_TAREA_BOUND_Y             = DECR_LABEL_BOUND_Y + 25;
    public final static int DECR_TAREA_BOUND_WIDTH         = FRAME_WIDTH / 2 - 25;
    public final static int DECR_TAREA_BOUND_HEIGHT        = 600 - DECR_TAREA_BOUND_Y;

    // ENCRYPT BUTTON BOUNDS
    public final static int ENCR_BUTT_BOUND_X               = 10;
    public final static int ENCR_BUTT_BOUND_Y               = ENCR_TAREA_BOUND_Y + ENCR_TAREA_BOUND_HEIGHT + 10;
    public final static int ENCR_BUTT_BOUND_WIDTH           = ENCR_TAREA_BOUND_WIDTH;
    public final static int ENCR_BUTT_BOUND_HEIGHT          = 50;

    // DECRYPT BUTTON BOUNDS
    public final static int DECR_BUTT_BOUND_X               = FRAME_WIDTH / 2 - 10;
    public final static int DECR_BUTT_BOUND_Y               = ENCR_TAREA_BOUND_Y + ENCR_TAREA_BOUND_HEIGHT + 10;
    public final static int DECR_BUTT_BOUND_WIDTH           = FRAME_WIDTH / 2 - 25;
    public final static int DECR_BUTT_BOUND_HEIGHT          = 50;

    // KEY NOT NEED LABEL BOUNDS
    public final static int KEYNN_LABEL_BOUND_X             = Constants.FRAME_WIDTH / 2 - 100;
    public final static int KEYNN_LABEL_BOUND_Y             = 17;
    public final static int KEYNN_LABEL_BOUND_WIDTH         = Constants.FRAME_WIDTH / 2;
    public final static int KEYNN_LABEL_BOUND_HEIGHT        = 25;

    public final static int JTEXTAREA_COLS                  = 155;
    public final static int JTEXTAREA_ENCDEC_LENGTH         = 59;

    // SAVE CURRENT STATE BUTTON
    public final static int SAVE_BUTTON_BOUND_X             = Constants.FRAME_WIDTH / 2 - 120;
    public final static int SAVE_BUTTON_BOUND_Y             = Constants.FRAME_HEIGHT / 2 + 165;
    public final static int SAVE_BUTTON_BOUND_WIDTH         = 200;
    public final static int SAVE_BUTTON_BOUND_HEIGHT        = 40;

    // NEXT BUTTON STATE
    public final static int NEXT_STATE_BOUND_X              = Constants.FRAME_WIDTH / 2 + 100;
    public final static int NEXT_STATE_BOUND_Y              = Constants.FRAME_HEIGHT / 2 + 165;
    public final static int NEXT_STATE_BOUND_WIDTH          = 100;
    public final static int NEXT_STATE_BOUND_HEIGHT         = 40;

    // PREC BUTTON STATE
    public final static int PREC_STATE_BOUND_X              = Constants.FRAME_WIDTH / 2 - 240;
    public final static int PREC_STATE_BOUND_Y              = Constants.FRAME_HEIGHT / 2 + 165;
    public final static int PREC_STATE_BOUND_WIDTH          = 100;
    public final static int PREC_STATE_BOUND_HEIGHT         = 40;

    // OPEN DECRYPTED FILE
    public final static int OPEN_DECR_FILE_BOUND_X          = 10;
    public final static int OPEN_DECR_FILE_BOUND_Y          = Constants.FRAME_HEIGHT / 2 + 165;
    public final static int OPEN_DECR_FILE_BOUND_WIDTH      = DECR_BUTT_BOUND_WIDTH / 2;
    public final static int OPEN_DECR_FILE_BOUND_HEIGHT     = 40;

    // SAVE DECRYPTED FILE
    public final static int SAVE_DECR_FILE_BOUND_X          = OPEN_DECR_FILE_BOUND_WIDTH + 15;
    public final static int SAVE_DECR_FILE_BOUND_Y          = Constants.FRAME_HEIGHT / 2 + 165;
    public final static int SAVE_DECR_FILE_BOUND_WIDTH      = DECR_BUTT_BOUND_WIDTH / 2 - 10;
    public final static int SAVE_DECR_FILE_BOUND_HEIGHT     = 40;

    // OPEN ENCRYPTED FILE
    public final static int OPEN_ENCR_FILE_BOUND_X          = FRAME_WIDTH / 2 - 10;
    public final static int OPEN_ENCR_FILE_BOUND_Y          = Constants.FRAME_HEIGHT / 2 + 165;
    public final static int OPEN_ENCR_FILE_BOUND_WIDTH      = DECR_BUTT_BOUND_WIDTH / 2 + 5;
    public final static int OPEN_ENCR_FILE_BOUND_HEIGHT     = 40;

    // SAVE ENCRYPTED FILz
    public final static int SAVE_ENCR_FILE_BOUND_X          = FRAME_WIDTH / 2 + DECR_BUTT_BOUND_WIDTH / 2;
    public final static int SAVE_ENCR_FILE_BOUND_Y          = Constants.FRAME_HEIGHT / 2 + 165;
    public final static int SAVE_ENCR_FILE_BOUND_WIDTH      = DECR_BUTT_BOUND_WIDTH / 2 - 10;
    public final static int SAVE_ENCR_FILE_BOUND_HEIGHT     = 40;

}