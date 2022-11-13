package main;

import ciphers.*;
import constants.Constants;
import keyGeneration.KeyGeneric;
import listeners.*;
import tools.BackLogic;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualFrame extends JFrame implements ActionListener {

    CipherClass workCipher;
    KeyGeneric keyGeneric;
    private static VisualFrame instance;

    // -----------------------------------------------------------   GLOBAL COMPONENTS
    JTextArea descriptionArea;
    // -----------------------------------------------------------   GLOBAL COMPONENTS

    JComboBox selectAlgComboBox;
    JTextArea encryptInput;
    JTextArea decryptInput;
    JTextField keyInputField;
    JTextField privateKeyInputField;
    JButton encryptButton;
    JButton decryptButton;
    JButton generatedKeyButton;
    JButton saveTempProgress;
    JButton nextState;
    JButton precedentState;
    JButton openDecryptedFile;
    JButton saveDecryptedFile;
    JButton openEncryptedFile;
    JButton saveEncryptedFile;

    public static VisualFrame getInstance() {
        if (instance == null) {
            instance = new VisualFrame();
        }
        return instance;
    }

    private VisualFrame()
    {
        JFrame frame = new JFrame();
        frame.setTitle("Cipher Algorithms");
        frame.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        frame.setLayout(null);
        frame.setBackground(new java.awt.Color(240, 173, 181));

        // -----------------------------------------------------------  Select Algorithm Panel

            JPanel selectedAlgorithmPanel = new JPanel();
            selectedAlgorithmPanel.setLayout(null);
            selectedAlgorithmPanel.setBounds(Constants.CBOX_PANEL_BOUND_X,Constants.CBOX_PANEL_BOUND_Y, Constants.CBOX_PANEL_BOUND_WIDTH, Constants.CBOX_PANEL_BOUND_HEIGHT);
            selectedAlgorithmPanel.setBackground(new java.awt.Color(204, 204, 204));
            selectedAlgorithmPanel.setBorder( BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Select algorithm:") );
            selectAlgComboBox = new JComboBox(Constants.cipherAlgorithms.toArray());
            selectAlgComboBox.setBounds(Constants.CBOX_BOUND_X, Constants.CBOX_BOUND_Y, Constants.CBOX_BOUND_WIDTH, Constants.CBOX_BOUND_HEIGHT);
            selectAlgComboBox.setSelectedItem(Constants.DEFAULT_SELECTED_ALG_CBOX);
            selectedAlgorithmPanel.add(selectAlgComboBox);

        // -----------------------------------------------------------  Select Algorithm Panel

        // -----------------------------------------------------------  Description Panel

        JPanel descriptionPanel = new JPanel();
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

        JPanel keyPanel = new JPanel();
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
        keyPanel.add(keyInputField);
        keyPanel.add(privateKeyInputField);
        keyPanel.add(generatedKeyButton);

        // -----------------------------------------------------------  Key Panel

        // -----------------------------------------------------------  Text Transformation Panel

        JPanel textTransformationPanel  = new JPanel();
        textTransformationPanel.setLayout(null);
        textTransformationPanel.setBounds(Constants.TRANSF_PANEL_BOUND_X, Constants.TRANSF_PANEL_BOUND_Y, Constants.TRANSF_PANEL_BOUND_WIDTH, Constants.TRANSF_PANEL_BOUND_HEIGHT);
        textTransformationPanel.setBackground(new java.awt.Color(204, 204, 204));
        textTransformationPanel.setBorder( BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Data manipulation:") );

        JLabel encryptTextLabel = new JLabel("Decrypted Message:");
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

        JLabel decryptTextLabel = new JLabel("Encrypted Message:");
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
        decryptButton = new JButton("<- Decrypt");
        decryptButton.setBounds(Constants.DECR_BUTT_BOUND_X, Constants.DECR_BUTT_BOUND_Y, Constants.DECR_BUTT_BOUND_WIDTH, Constants.DECR_BUTT_BOUND_HEIGHT);

        textTransformationPanel.add(encryptTextLabel);
        textTransformationPanel.add(encryptInput);
        textTransformationPanel.add(decryptTextLabel);
        textTransformationPanel.add(decryptInput);
        textTransformationPanel.add(encryptButton);
        textTransformationPanel.add(decryptButton);

        // -----------------------------------------------------------  Text Transformation Panel

//        // -----------------------------------------------------------  Save Button
//        saveTempProgress = new JButton("Save Current State");
//        saveTempProgress.setBounds(Constants.SAVE_BUTTON_BOUND_X, Constants.SAVE_BUTTON_BOUND_Y, Constants.SAVE_BUTTON_BOUND_WIDTH, Constants.SAVE_BUTTON_BOUND_HEIGHT);
//        saveTempProgress.setVisible(true);
//        textTransformationPanel.add(saveTempProgress);
//        // -----------------------------------------------------------  Save Button
//
//        // -----------------------------------------------------------  State modification buttons
//        nextState = new JButton("-->");
//        nextState.setBounds(Constants.NEXT_STATE_BOUND_X, Constants.NEXT_STATE_BOUND_Y, Constants.NEXT_STATE_BOUND_WIDTH, Constants.NEXT_STATE_BOUND_HEIGHT);
//        nextState.setVisible(true);
//        textTransformationPanel.add(nextState);
//
//        precedentState = new JButton("<--");
//        precedentState.setBounds(Constants.PREC_STATE_BOUND_X, Constants.PREC_STATE_BOUND_Y, Constants.PREC_STATE_BOUND_WIDTH, Constants.PREC_STATE_BOUND_HEIGHT);
//        precedentState.setVisible(true);
//        textTransformationPanel.add(precedentState);
//        // -----------------------------------------------------------  State modification buttons

        openDecryptedFile = new JButton("Open Decrypted File");
        openDecryptedFile.setBounds(Constants.OPEN_DECR_FILE_BOUND_X, Constants.OPEN_DECR_FILE_BOUND_Y, Constants.OPEN_DECR_FILE_BOUND_WIDTH, Constants.OPEN_DECR_FILE_BOUND_HEIGHT);
        openDecryptedFile.setVisible(true);
        textTransformationPanel.add(openDecryptedFile);

        saveDecryptedFile = new JButton("Save Decrypted File");
        saveDecryptedFile.setBounds(Constants.SAVE_DECR_FILE_BOUND_X, Constants.SAVE_DECR_FILE_BOUND_Y, Constants.SAVE_DECR_FILE_BOUND_WIDTH, Constants.SAVE_DECR_FILE_BOUND_HEIGHT);
        saveDecryptedFile.setVisible(true);
        textTransformationPanel.add(saveDecryptedFile);

        openEncryptedFile = new JButton("Open Encrypted File");
        openEncryptedFile.setBounds(Constants.OPEN_ENCR_FILE_BOUND_X, Constants.OPEN_ENCR_FILE_BOUND_Y, Constants.OPEN_ENCR_FILE_BOUND_WIDTH, Constants.OPEN_ENCR_FILE_BOUND_HEIGHT);
        openEncryptedFile.setVisible(true);
        textTransformationPanel.add(openEncryptedFile);

        saveEncryptedFile = new JButton("Save Encrypted File");
        saveEncryptedFile.setBounds(Constants.SAVE_ENCR_FILE_BOUND_X, Constants.SAVE_ENCR_FILE_BOUND_Y, Constants.SAVE_ENCR_FILE_BOUND_WIDTH, Constants.SAVE_ENCR_FILE_BOUND_HEIGHT);
        saveEncryptedFile.setVisible(true);
        textTransformationPanel.add(saveEncryptedFile);

        // -----------------------------------------------------------  Listeners
        selectAlgComboBox.addActionListener(new ChangeComboBoxListener(selectAlgComboBox, workCipher, descriptionPanel, descriptionArea, keyPanel, textTransformationPanel,
                keyInputField, privateKeyInputField, generatedKeyButton));
        generatedKeyButton.addActionListener(this);
        encryptButton.addActionListener(this);
        decryptButton.addActionListener(this);
//        saveTempProgress.addActionListener(new SaveButtonListener());
//        nextState.addActionListener(new NextStateButtonListener());
//        precedentState.addActionListener(new PrecStateButtonListener());
        openDecryptedFile.addActionListener(new OpenFileListener("openDecrypted"));
        openEncryptedFile.addActionListener(new OpenFileListener("openEncrypted"));
        saveDecryptedFile.addActionListener(new SaveFileListener("saveDecrypted"));
        saveEncryptedFile.addActionListener(new SaveFileListener("saveEncrypted"));
        // -----------------------------------------------------------  Listeners

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
        if(e.getSource() == generatedKeyButton) {
            String[] args = {"", ""};
            try {
                keyGeneric = BackLogic.generateKeyObject(workCipher.getNameCipher(), encryptInput.getText());
                String generatedKey = keyGeneric.generateKey();
                args = generatedKey.split("\\\\\\\\\\\\");
                keyInputField.setText( args[0] );
                if(args.length > 1){
                    privateKeyInputField.setText( args[1] );
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        if(e.getSource() == encryptButton) {
            String typedKey = "";
            String typedMessage = "";

            if(workCipher.getRequireKey())
            {
                if(keyInputField.getText() == "" || encryptInput.getText() == "")
                {
                    JOptionPane.showMessageDialog(keyInputField,"Decrypted message or key is missing!");
                }
                if(keyInputField.getText() != "")
                {
                    if(workCipher.getRequireKey())
                    {
                        typedKey = keyInputField.getText();
                    }
                    if (!BackLogic.checkForREGEX(workCipher, typedKey))
                    {
                        JOptionPane.showMessageDialog(keyInputField,"Key is incorrect!");
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
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            for (String x: encryptSplit)
            {
                decryptInput.setText( decryptInput.getText() + x );
            }
        }

        if(e.getSource() == decryptButton) {
            String typedKey = "";
            String typedMessage = "";

            if(workCipher.getRequireKey())
            {
                if(keyInputField.getText() == "" || decryptInput.getText() == "" || (workCipher.getRequirePrivateKey() && privateKeyInputField.getText() == ""))
                {
                    JOptionPane.showMessageDialog(keyInputField,"Encrypted message or key is missing!");
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
                        JOptionPane.showMessageDialog(keyInputField,"Key is incorrect!");
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
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            for (String x: encryptSplit)
            {
                encryptInput.setText( encryptInput.getText() + x );
            }
        }
    }

    public void performCipherChange(CipherClass workCipher) {
        this.workCipher = workCipher;
    }

    public CipherClass getCurrentClass() {
        return workCipher;
    }

    public String getPublicKey() {
        return keyInputField.getText();
    }

    public String getPrivateKey() {
        return privateKeyInputField.getText();
    }

    public String getEncryptInput() {
        return  encryptInput.getText();
    }

    public String getDecryptInput() {
        return decryptInput.getText();
    }

    public JComboBox getSelectAlgComboBox() {
        return selectAlgComboBox;
    }

    public void setKeyInputField(String keyInputField) {
        this.keyInputField.setText(keyInputField);
    }

    public void setPrivateKeyInputField(String privateKeyInputField) {
        this.privateKeyInputField.setText(privateKeyInputField);
    }

    public void setEncryptInput(String encryptInput) {
        this.encryptInput.setText(encryptInput);
    }

    public void setDecryptInput(String decryptInput) {
        this.decryptInput.setText(decryptInput);
    }
}