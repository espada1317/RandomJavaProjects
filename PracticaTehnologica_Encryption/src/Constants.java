public class Constants
{
    final static String CIPHER_CAESAR_REGEX = "(0?[1-9]|1[0-9]|2[0-6])";
    final static String CIPHER_CARDAN_GRILLE_REGEX = "(X|_)*";
    final static String CIPHER_TEXT_REGEX = "([a-z]|[A-Z])*";
    final static String CIPHER_AES_REGEX = "(([a-z]|[A-Z]|[0-9]|\\/|\\+)*==)";
    final static String CIPHER_DES_REGEX = "(([a-z]|[A-Z]|[0-9]|\\/|\\+)*=)";
    final static String CIPHER_3DES_REGEX = "(([a-z]|[A-Z]|[0-9]|\\/|\\+)*)";
    final static String CIPHER_RSA_PAIR_REGEX = CIPHER_AES_REGEX + "|" + CIPHER_3DES_REGEX + "|" + CIPHER_DES_REGEX;

    // JFRAME CONSTANTS
    final static int FRAME_WIDTH                    = 900;
    final static int FRAME_HEIGHT                   = 1000;

    // SELECT ALGORITHM PANEL BOUNDS
    final static int CBOX_PANEL_BOUND_X             = 5;
    final static int CBOX_PANEL_BOUND_Y             = 5;
    final static int CBOX_PANEL_BOUND_WIDTH         = FRAME_WIDTH - 25;
    final static int CBOX_PANEL_BOUND_HEIGHT        = 60;

    // SELECT ALGORITHM COMBO-BOX BOUNDS
    final static int CBOX_BOUND_X                   = 10;
    final static int CBOX_BOUND_Y                   = 20;
    final static int CBOX_BOUND_WIDTH               = FRAME_WIDTH - 55;
    final static int CBOX_BOUND_HEIGHT              = 30;

    // DESCRIPTION PANEL BOUNDS
    final static int DESC_PANEL_BOUND_X             = 5;
    final static int DESC_PANEL_BOUND_Y             = 65;
    final static int DESC_PANEL_BOUND_WIDTH         = Constants.FRAME_WIDTH - 25;
    final static int DESC_PANEL_BOUND_HEIGHT        = 77;

    // DESCRIPTION TEXTAREA BOUNDS
    final static int DESC_TAREA_BOUND_X             = 10;
    final static int DESC_TAREA_BOUND_Y             = 17;
    final static int DESC_TAREA_BOUND_WIDTH         = Constants.FRAME_WIDTH - 45;
    final static int DESC_TAREA_BOUND_HEIGHT        = 50;

    // KEY PANEL BOUNDS
    final static int KEY_PANEL_BOUND_X              = 5;
    final static int KEY_PANEL_BOUND_Y              = VisualFrame.DESCRIPTION_POSITION;
    final static int KEY_PANEL_BOUND_WIDTH          = Constants.FRAME_WIDTH - 25;
    final static int KEY_PANEL_BOUND_HEIGHT         = 50;

    // KEY TEXT FIELD BOUNDS
    final static int KEY_TFIELD_BOUND_X             = 10;
    final static int KEY_TFIELD_BOUND_Y             = 17;
    final static int KEY_TFIELD_BOUND_WIDTH         = Constants.FRAME_WIDTH - 45;
    final static int KEY_TFIELD_BOUND_HEIGHT        = 25;

    // TEXT TRANSFORMATION BOUNDs
    final static int TRANSF_PANEL_BOUND_X           = 5;
    final static int TRANSF_PANEL_BOUND_Y           = KEY_PANEL_BOUND_Y + KEY_PANEL_BOUND_HEIGHT;
    final static int TRANSF_PANEL_BOUND_WIDTH       = Constants.FRAME_WIDTH - 25;
    final static int TRANSF_PANEL_BOUND_HEIGHT      = FRAME_HEIGHT - TRANSF_PANEL_BOUND_Y - 43;

    // ENCRYPT LABEL
    final static int ENCR_LABEL_BOUND_X             = 10;
    final static int ENCR_LABEL_BOUND_Y             = 30;
    final static int ENCR_LABEL_BOUND_WIDTH         = FRAME_WIDTH / 2;
    final static int ENCR_LABEL_BOUND_HEIGHT        = 20;

    // ENCRYPT TEXT AREA
    final static int ENCR_TAREA_BOUND_X             = 10;
    final static int ENCR_TAREA_BOUND_Y             = ENCR_LABEL_BOUND_Y + 25;
    final static int ENCR_TAREA_BOUND_WIDTH         = FRAME_WIDTH / 2 - ( ENCR_LABEL_BOUND_X + TRANSF_PANEL_BOUND_X ) - 15;
    final static int ENCR_TAREA_BOUND_HEIGHT        = 600 - ENCR_TAREA_BOUND_Y;

    // DECRYPT LABEL
    final static int DECR_LABEL_BOUND_X             = FRAME_WIDTH / 2 - 10;
    final static int DECR_LABEL_BOUND_Y             = 30;
    final static int DECR_LABEL_BOUND_WIDTH         = FRAME_WIDTH / 2;
    final static int DECR_LABEL_BOUND_HEIGHT        = 20;

    // DECRYPT TEXT AREA
    final static int DECR_TAREA_BOUND_X             = DECR_LABEL_BOUND_X;
    final static int DECR_TAREA_BOUND_Y             = DECR_LABEL_BOUND_Y + 25;
    final static int DECR_TAREA_BOUND_WIDTH         = FRAME_WIDTH / 2 - 25;
    final static int DECR_TAREA_BOUND_HEIGHT        = 600 - DECR_TAREA_BOUND_Y;

    // ENCRYPT BUTTON BOUNDS
    final static int ENCR_BUTT_BOUND_X              = 10;
    final static int ENCR_BUTT_BOUND_Y              = ENCR_TAREA_BOUND_Y + ENCR_TAREA_BOUND_HEIGHT + 10;
    final static int ENCR_BUTT_BOUND_WIDTH          = ENCR_TAREA_BOUND_WIDTH;
    final static int ENCR_BUTT_BOUND_HEIGHT         = 50;

    // DECRYPT BUTTON BOUNDS
    final static int DECR_BUTT_BOUND_X              = FRAME_WIDTH / 2 - 10;
    final static int DECR_BUTT_BOUND_Y              = ENCR_TAREA_BOUND_Y + ENCR_TAREA_BOUND_HEIGHT + 10;
    final static int DECR_BUTT_BOUND_WIDTH          = FRAME_WIDTH / 2 - 25;
    final static int DECR_BUTT_BOUND_HEIGHT         = 50;

    // KEY NOT NEED LABEL BOUNDS
    final static int KEYNN_LABEL_BOUND_X             = Constants.FRAME_WIDTH / 2 - 100;
    final static int KEYNN_LABEL_BOUND_Y             = 17;
    final static int KEYNN_LABEL_BOUND_WIDTH         = Constants.FRAME_WIDTH / 2;
    final static int KEYNN_LABEL_BOUND_HEIGHT        = 25;

    final static int JTEXTAREA_COLS                 = 155;
    final static int JTEXTAREA_ENCDEC_LENGTH        = 59;
}