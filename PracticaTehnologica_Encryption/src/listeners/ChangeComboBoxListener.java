package listeners;

import ciphers.CipherClass;
import constants.Constants;
import factories.CipherFactory;
import main.VisualFrame;
import tools.BackLogic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeComboBoxListener implements ActionListener {
    JComboBox selectAlgComboBox;
    CipherClass workCipher;
    JPanel descriptionPanel;
    JTextArea descriptionArea;
    JPanel keyPanel;
    JPanel textTransformationPanel;
    JTextField keyInputField;
    JTextField privateKeyInputField;
    JButton generatedKeyButton;

    public ChangeComboBoxListener(JComboBox selectAlgComboBox, CipherClass workCipher, JPanel descriptionPanel, JTextArea descriptionArea, JPanel keyPanel,
                                  JPanel textTransformationPanel, JTextField keyInputField, JTextField privateKeyInputField, JButton generatedKeyButton) {
        this.selectAlgComboBox = selectAlgComboBox;
        this. workCipher = workCipher;
        this.descriptionArea = descriptionArea;
        this.descriptionPanel = descriptionPanel;
        this.keyInputField = keyInputField;
        this.keyPanel = keyPanel;
        this.textTransformationPanel = textTransformationPanel;
        this.privateKeyInputField = privateKeyInputField;
        this.generatedKeyButton = generatedKeyButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!Constants.REMOVED_DEFAULT_VALUE_CBOX)
        {
            int position = Constants.cipherAlgorithms.indexOf(Constants.DEFAULT_SELECTED_ALG_CBOX);
            Constants.cipherAlgorithms.remove(Constants.DEFAULT_SELECTED_ALG_CBOX);
            selectAlgComboBox.removeItemAt(position);
            Constants.REMOVED_DEFAULT_VALUE_CBOX = true;
        }

        String comboBoxText = (String) selectAlgComboBox.getSelectedItem();
        workCipher = CipherFactory.createCipher(comboBoxText);
        String descriptionText = workCipher.getDescription();
        descriptionArea.setSize(Constants.FRAME_WIDTH - 45, ( ( descriptionText.length() / Constants.JTEXTAREA_COLS )  + 1 ) * 18 );
        descriptionArea.setText( BackLogic.parseTextAreaDescription( descriptionText ));
        keyPanel.setBounds(Constants.KEY_PANEL_BOUND_X, Constants.DESCRIPTION_POSITION, Constants.KEY_PANEL_BOUND_WIDTH, Constants.KEY_PANEL_BOUND_HEIGHT);
        textTransformationPanel.setBounds(Constants.TRANSF_PANEL_BOUND_X, Constants.DESCRIPTION_POSITION + Constants.KEY_PANEL_BOUND_HEIGHT, Constants.TRANSF_PANEL_BOUND_WIDTH, Constants.FRAME_HEIGHT - ( Constants.DESCRIPTION_POSITION + Constants.KEY_PANEL_BOUND_HEIGHT ) - 43);

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

        VisualFrame.getInstance().performCipherChange(workCipher);
    }
}