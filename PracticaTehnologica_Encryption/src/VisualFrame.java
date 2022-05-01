import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

public class VisualFrame extends JFrame implements ActionListener {

    JFrame frame                    = new JFrame();
    JPanel selectedAlgorithmPanel   = new JPanel();
    JPanel descriptionPanel         = new JPanel();
    JPanel keyPanel                 = new JPanel();
    JPanel textTransformationPanel  = new JPanel();
    JComboBox selectAlgComboBox;
    JTextArea descriptionArea;
    JTextArea encryptInput;
    JTextArea decryptInput;
    JTextField keyInputField;
    JTextField privateKeyInputField;
    JLabel encryptTextLabel;
    JLabel decryptTextLabel;
    JButton encryptButton;
    JButton decryptButton;
    CipherClass workCipher;
    JButton generatedKeyButton;

    static final ArrayList<String> cipherAlgorithms;
    static {
        cipherAlgorithms = new ArrayList<>();
        cipherAlgorithms.add(" - Choose desired algorithm - ");
        cipherAlgorithms.add("Caesar Cipher");
        cipherAlgorithms.add("Cardano Grille");
        cipherAlgorithms.add("Polybius Square");
        cipherAlgorithms.add("Trithemius Ave Maria");
        cipherAlgorithms.add("Vigenere Cipher");
        cipherAlgorithms.add("RC4");
        cipherAlgorithms.add("AES");
        cipherAlgorithms.add("DES");
        cipherAlgorithms.add("3DES");
        cipherAlgorithms.add("RSA");
    }

    final static String DEFAULT_SELECTED_ALG_CBOX   = " - Choose desired algorithm - ";
    static Boolean REMOVED_DEFAULT_VALUE_CBOX       = false;
    public static int DESCRIPTION_POSITION          = Constants.DESC_PANEL_BOUND_Y + Constants.DESC_PANEL_BOUND_HEIGHT;

    public VisualFrame()
    {
        frame.setTitle("Cipher Algorithms");
        frame.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        frame.setLayout(null);
        frame.setBackground(new java.awt.Color(240, 173, 181));

        // -----------------------------------------------------------  Select Algorithm Panel

        selectedAlgorithmPanel.setLayout(null);
        selectedAlgorithmPanel.setBounds(Constants.CBOX_PANEL_BOUND_X,Constants.CBOX_PANEL_BOUND_Y, Constants.CBOX_PANEL_BOUND_WIDTH, Constants.CBOX_PANEL_BOUND_HEIGHT);
        selectedAlgorithmPanel.setBackground(new java.awt.Color(204, 204, 204));
        selectedAlgorithmPanel.setBorder( BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Select algorithm:") );
        selectAlgComboBox = new JComboBox(cipherAlgorithms.toArray());
        selectAlgComboBox.setBounds(Constants.CBOX_BOUND_X, Constants.CBOX_BOUND_Y, Constants.CBOX_BOUND_WIDTH, Constants.CBOX_BOUND_HEIGHT);
        selectAlgComboBox.setSelectedItem(DEFAULT_SELECTED_ALG_CBOX);
        selectAlgComboBox.addActionListener(this);
        selectedAlgorithmPanel.add(selectAlgComboBox);

        // -----------------------------------------------------------  Select Algorithm Panel

        // -----------------------------------------------------------  Description Panel

        descriptionPanel.setLayout(null);
        descriptionPanel.setBounds(Constants.DESC_PANEL_BOUND_X, Constants.DESC_PANEL_BOUND_Y, Constants.DESC_PANEL_BOUND_WIDTH, Constants.DESC_PANEL_BOUND_HEIGHT);
        descriptionPanel.setBackground(new java.awt.Color(204, 204, 204));
        descriptionPanel.setBorder( BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Description:") );
        descriptionArea = new JTextArea();
        descriptionArea.setBackground(new java.awt.Color(204, 204, 204));
        descriptionArea.setColumns(Constants.JTEXTAREA_COLS);
        descriptionArea.setBounds(Constants.DESC_TAREA_BOUND_X, Constants.DESC_TAREA_BOUND_Y, Constants.DESC_TAREA_BOUND_WIDTH, Constants.DESC_TAREA_BOUND_HEIGHT);
        descriptionArea.setVisible(true);
        descriptionArea.setEditable(false);
        descriptionPanel.add(descriptionArea);

        // -----------------------------------------------------------  Description Panel

        // -----------------------------------------------------------  Key Panel

        keyPanel.setLayout(null);
        keyPanel.setBounds(Constants.KEY_PANEL_BOUND_X, Constants.KEY_PANEL_BOUND_Y, Constants.KEY_PANEL_BOUND_WIDTH, Constants.KEY_PANEL_BOUND_HEIGHT);
        keyPanel.setBackground(new java.awt.Color(204, 204, 204));
        keyPanel.setBorder( BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Key:") );
        keyInputField = new JTextField();
        keyInputField.setBounds(Constants.KEY_TFIELD_BOUND_X, Constants.KEY_TFIELD_BOUND_Y, Constants.KEY_TFIELD_BOUND_WIDTH / 2 - 55, Constants.KEY_TFIELD_BOUND_HEIGHT);
        keyInputField.setVisible(false);
        privateKeyInputField = new JTextField();
        privateKeyInputField.setBounds((Constants.KEY_TFIELD_BOUND_WIDTH ) / 2 - 40, Constants.KEY_TFIELD_BOUND_Y, Constants.KEY_TFIELD_BOUND_WIDTH / 2 - 65, Constants.KEY_TFIELD_BOUND_HEIGHT);
        privateKeyInputField.setVisible(false);
        generatedKeyButton = new JButton("Generate Key");
        generatedKeyButton.setBounds(Constants.KEY_TFIELD_BOUND_WIDTH - 100, Constants.KEY_TFIELD_BOUND_Y, 110, Constants.KEY_TFIELD_BOUND_HEIGHT);
        generatedKeyButton.setVisible(false);
        generatedKeyButton.addActionListener(this);
        keyPanel.add(keyInputField);
        keyPanel.add(privateKeyInputField);
        keyPanel.add(generatedKeyButton);

        // -----------------------------------------------------------  Key Panel

        // -----------------------------------------------------------  Text Transformation Panel

        textTransformationPanel.setLayout(null);
        textTransformationPanel.setBounds(Constants.TRANSF_PANEL_BOUND_X, Constants.TRANSF_PANEL_BOUND_Y, Constants.TRANSF_PANEL_BOUND_WIDTH, Constants.TRANSF_PANEL_BOUND_HEIGHT);
        textTransformationPanel.setBackground(new java.awt.Color(204, 204, 204));
        textTransformationPanel.setBorder( BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Data manipulation:") );

        encryptTextLabel = new JLabel("Decrypted Message:");
        encryptTextLabel.setBounds(Constants.ENCR_LABEL_BOUND_X, Constants.ENCR_LABEL_BOUND_Y, Constants.ENCR_LABEL_BOUND_WIDTH, Constants.ENCR_LABEL_BOUND_HEIGHT);
        encryptInput = new JTextArea(10, 20);
        encryptInput.setBounds(Constants.ENCR_TAREA_BOUND_X, Constants.ENCR_TAREA_BOUND_Y, Constants.ENCR_TAREA_BOUND_WIDTH, Constants.ENCR_TAREA_BOUND_HEIGHT);
        encryptInput.setVisible(true);
        encryptInput.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if ( str == null || (encryptInput.getText().length() % Constants.JTEXTAREA_ENCDEC_LENGTH)  == Constants.JTEXTAREA_ENCDEC_LENGTH - 1  ) {
                    encryptInput.setText(encryptInput.getText() + "\n");
                }
                super.insertString(offs, str, a);
            }
        });

        decryptTextLabel = new JLabel("Encrypted Message:");
        decryptTextLabel.setBounds(Constants.DECR_LABEL_BOUND_X, Constants.DECR_LABEL_BOUND_Y, Constants.DECR_LABEL_BOUND_WIDTH, Constants.DECR_LABEL_BOUND_HEIGHT);
        decryptInput = new JTextArea(10, 20);
        decryptInput.setBounds(Constants.DECR_TAREA_BOUND_X, Constants.DECR_TAREA_BOUND_Y, Constants.DECR_TAREA_BOUND_WIDTH, Constants.DECR_TAREA_BOUND_HEIGHT);
        decryptInput.setVisible(true);
        decryptInput.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if ( str == null || (decryptInput.getText().length() % Constants.JTEXTAREA_ENCDEC_LENGTH)  == Constants.JTEXTAREA_ENCDEC_LENGTH - 1  ) {
                    decryptInput.setText(decryptInput.getText() + "\n");
                }
                super.insertString(offs, str, a);
            }
        });

        encryptButton = new JButton("Encrypt ->");
        encryptButton.setBounds(Constants.ENCR_BUTT_BOUND_X, Constants.ENCR_BUTT_BOUND_Y, Constants.ENCR_BUTT_BOUND_WIDTH, Constants.ENCR_BUTT_BOUND_HEIGHT);
        encryptButton.addActionListener(this);

        decryptButton = new JButton("<- Decrypt");
        decryptButton.setBounds(Constants.DECR_BUTT_BOUND_X, Constants.DECR_BUTT_BOUND_Y, Constants.DECR_BUTT_BOUND_WIDTH, Constants.DECR_BUTT_BOUND_HEIGHT);
        decryptButton.addActionListener(this);

        textTransformationPanel.add(encryptTextLabel);
        textTransformationPanel.add(encryptInput);
        textTransformationPanel.add(decryptTextLabel);
        textTransformationPanel.add(decryptInput);
        textTransformationPanel.add(encryptButton);
        textTransformationPanel.add(decryptButton);

        // -----------------------------------------------------------  Text Transformation Panel

        frame.add(selectedAlgorithmPanel);
        frame.add(descriptionPanel);
        frame.add(keyPanel);
        frame.add(textTransformationPanel);

        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == selectAlgComboBox)
        {
            if(!REMOVED_DEFAULT_VALUE_CBOX)
            {
                int position = cipherAlgorithms.indexOf(DEFAULT_SELECTED_ALG_CBOX);
                cipherAlgorithms.remove(DEFAULT_SELECTED_ALG_CBOX);
                selectAlgComboBox.removeItemAt(position);
                REMOVED_DEFAULT_VALUE_CBOX = true;
            }

            String comboBoxText = (String) selectAlgComboBox.getSelectedItem();
            workCipher = BackLogic.choseObject(comboBoxText);
            String descriptionText = workCipher.getDescription();
            int heightDesc = ( ( descriptionText.length() / Constants.JTEXTAREA_COLS )  + 2 ) * 20;
            DESCRIPTION_POSITION = 65 + heightDesc;
            descriptionPanel.setSize(Constants.FRAME_WIDTH - 25, heightDesc);
            descriptionArea.setSize(Constants.FRAME_WIDTH - 45, ( ( descriptionText.length() / Constants.JTEXTAREA_COLS )  + 1 ) * 18 );
            descriptionArea.setText(BackLogic.parseTextAreaDescription(descriptionText));
            keyPanel.setBounds(Constants.KEY_PANEL_BOUND_X, DESCRIPTION_POSITION, Constants.KEY_PANEL_BOUND_WIDTH, Constants.KEY_PANEL_BOUND_HEIGHT);
            textTransformationPanel.setBounds(Constants.TRANSF_PANEL_BOUND_X, DESCRIPTION_POSITION + Constants.KEY_PANEL_BOUND_HEIGHT, Constants.TRANSF_PANEL_BOUND_WIDTH, Constants.FRAME_HEIGHT - ( DESCRIPTION_POSITION + Constants.KEY_PANEL_BOUND_HEIGHT ) - 43);

            if (workCipher.getRequirePrivateKey() && workCipher.getRequireKey())
            {
                keyInputField.setVisible(true);
                privateKeyInputField.setVisible(true);
                generatedKeyButton.setVisible(true);
                keyInputField.setBounds(Constants.KEY_TFIELD_BOUND_X, Constants.KEY_TFIELD_BOUND_Y, Constants.KEY_TFIELD_BOUND_WIDTH / 2 - 55, Constants.KEY_TFIELD_BOUND_HEIGHT);
                privateKeyInputField.setBounds((Constants.KEY_TFIELD_BOUND_WIDTH ) / 2 - 40, Constants.KEY_TFIELD_BOUND_Y, Constants.KEY_TFIELD_BOUND_WIDTH / 2 - 65, Constants.KEY_TFIELD_BOUND_HEIGHT);
                generatedKeyButton.setBounds(Constants.KEY_TFIELD_BOUND_WIDTH - 100, Constants.KEY_TFIELD_BOUND_Y, 110, Constants.KEY_TFIELD_BOUND_HEIGHT);
            } else if(workCipher.getRequireKey())
            {
                keyInputField.setVisible(true);
                keyInputField.setBounds(Constants.KEY_TFIELD_BOUND_X, Constants.KEY_TFIELD_BOUND_Y, Constants.KEY_TFIELD_BOUND_WIDTH, Constants.KEY_TFIELD_BOUND_HEIGHT);
                privateKeyInputField.setVisible(false);

                if(workCipher.getRequireGeneratedKey())
                {
                    keyInputField.setBounds(Constants.KEY_TFIELD_BOUND_X, Constants.KEY_TFIELD_BOUND_Y, Constants.KEY_TFIELD_BOUND_WIDTH - 115, Constants.KEY_TFIELD_BOUND_HEIGHT);
                    generatedKeyButton.setVisible(true);
                }
            } else {
                keyInputField.setVisible(false);
                generatedKeyButton.setVisible(false);
                privateKeyInputField.setVisible(false);
                JLabel keyNotRequire = new JLabel("Key is not required for this method!");
                keyNotRequire.setBounds(Constants.KEYNN_LABEL_BOUND_X, Constants.KEYNN_LABEL_BOUND_Y, Constants.KEYNN_LABEL_BOUND_WIDTH, Constants.KEYNN_LABEL_BOUND_HEIGHT);
                keyPanel.add(keyNotRequire);
            }

            revalidate();
        }

        if(e.getSource() == encryptButton)
        {
            String typedKey = "";
            String typedMessage = "";

            if(workCipher.getRequireKey())
            {
                if(keyInputField.getText() == "" || encryptInput.getText() == "")
                {
                    JOptionPane.showMessageDialog(frame,"Decrypted message or key is missing!");
                }
                if(keyInputField.getText() != "")
                {
                    if(workCipher.getRequireKey())
                    {
                        typedKey = keyInputField.getText();
                    }
                    if (!BackLogic.checkForREGEX(workCipher, typedKey))
                    {
                        JOptionPane.showMessageDialog(frame,"Key is incorrect!");
                        keyInputField.setText("");
                        return;
                    }
                }
            }

            if(encryptInput.getText() != "")
            {
                typedMessage = encryptInput.getText();
            }

            decryptInput.setText("");
            String[] encryptSplit;
            try {
                encryptSplit = workCipher.encrypt(typedMessage, typedKey).split("");
            } catch (NoSuchPaddingException ex) {
                throw new RuntimeException(ex);
            } catch (NoSuchAlgorithmException ex) {
                throw new RuntimeException(ex);
            } catch (InvalidKeyException ex) {
                throw new RuntimeException(ex);
            } catch (IllegalBlockSizeException ex) {
                throw new RuntimeException(ex);
            } catch (BadPaddingException ex) {
                throw new RuntimeException(ex);
            } catch (InvalidAlgorithmParameterException ex) {
                throw new RuntimeException(ex);
            } catch (InvalidKeySpecException ex) {
                throw new RuntimeException(ex);
            }
            for (String x: encryptSplit)
            {
                decryptInput.setText( decryptInput.getText() + x );
            }
        }

        if(e.getSource() == decryptButton)
        {
            String typedKey = "";
            String typedMessage = "";

            if(workCipher.getRequireKey())
            {
                if(keyInputField.getText() == "" || decryptInput.getText() == "" || (workCipher.getRequirePrivateKey() && privateKeyInputField.getText() == ""))
                {
                    JOptionPane.showMessageDialog(frame,"Encrypted message or key is missing!");
                }
                if(keyInputField.getText() != "")
                {
                    if(workCipher.getRequirePrivateKey())
                    {
                        typedKey = privateKeyInputField.getText();
                    } else {
                        typedKey = keyInputField.getText();
                    }
                    if (!BackLogic.checkForREGEX(workCipher, typedKey))
                    {
                        JOptionPane.showMessageDialog(frame,"Key is incorrect!");
                        keyInputField.setText("");
                        return;
                    }
                }
            }

            if(decryptInput.getText() != "")
            {
                typedMessage = decryptInput.getText();
            }

            encryptInput.setText("");
            String[] encryptSplit;
            try {
                encryptSplit = workCipher.decrypt(typedMessage, typedKey).split("");
            } catch (NoSuchPaddingException ex) {
                throw new RuntimeException(ex);
            } catch (NoSuchAlgorithmException ex) {
                throw new RuntimeException(ex);
            } catch (InvalidAlgorithmParameterException ex) {
                throw new RuntimeException(ex);
            } catch (InvalidKeyException ex) {
                throw new RuntimeException(ex);
            } catch (IllegalBlockSizeException ex) {
                throw new RuntimeException(ex);
            } catch (BadPaddingException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (InvalidKeySpecException ex) {
                throw new RuntimeException(ex);
            }
            for (String x: encryptSplit)
            {
                encryptInput.setText( encryptInput.getText() + x );
            }
        }

        if (e.getSource() == generatedKeyButton)
        {
            if(workCipher.getNameCipher().equals("CardanoGrille"))
            {
                keyInputField.setText( ((CardanGrille) workCipher).generateMask(encryptInput.getText(), 80));
            }
            if(workCipher.getNameCipher().equals("AES"))
            {
                try {
                    keyInputField.setText( ((AesCipher) workCipher).generateKey() );
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
            if(workCipher.getNameCipher().equals("DES"))
            {
                try {
                    keyInputField.setText( ((DesCipher) workCipher).generateKey() );
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if(workCipher.getNameCipher().equals("3DES"))
            {
                try {
                    keyInputField.setText( ((TripleDesCipher) workCipher).generateKey() );
                } catch (InvalidKeyException ex) {
                    throw new RuntimeException(ex);
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                } catch (InvalidKeySpecException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if(workCipher.getNameCipher().equals("RSA"))
            {
                String[] keyPairs;
                try {
                    keyPairs = ((RsaCipher) workCipher).generateKey();
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                }
                keyInputField.setText( keyPairs[0] );
                privateKeyInputField.setText( keyPairs[1] );
            }
        }
    }
}