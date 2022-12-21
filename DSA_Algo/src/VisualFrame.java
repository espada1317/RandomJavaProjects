import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;

public class VisualFrame extends JFrame implements ActionListener {

    private static VisualFrame instance;

    public static VisualFrame getInstance() {
        if (instance == null) {
            instance = new VisualFrame();
        }
        return instance;
    }

    JTextField q_generated_value;
    JTextField p_generated_value;
    JTextField g_generated_value;
    JTextField x_generated_value;
    JTextField y_generated_value;
    JButton generateKeysButton;
    JButton calculateSignatureButton;
    JButton verifySignatureButton;
    JTextField selected_file_value;
    JTextField selected_file_verify_value;
    JTextField r_calculated_value;
    JTextField s_calculated_value;
    JTextField hash_calculated_value;
    JTextField hash_verify_value;
    JTextField w_calculated_value;
    JTextField u1_calculated_value;
    JTextField u2_calculated_value;
    JTextField v_calculated_value;
    DSAEncryptionManual dsa;
    Signature signature;
    VerifySignature verifySignature;
    JLabel statusLabel;
    JButton checkSignatureButton;

    private VisualFrame() {
        JFrame frame = new JFrame();
        frame.setTitle("DSA Cipher Alg");
        frame.setSize(805, 700);
        frame.setLayout(null);
        frame.setBackground(new java.awt.Color(240,173,181));

        JPanel keyGenerationPanel = new JPanel();
        keyGenerationPanel.setLayout(null);
        keyGenerationPanel.setBounds(5,5,780,200);
        keyGenerationPanel.setBackground(new java.awt.Color(204,204,204));
        keyGenerationPanel.setBorder( BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Generate keys:") );

        JLabel generatedQvalue = new JLabel("Q value : ");
        generatedQvalue.setBounds(30,20,70,30);
        keyGenerationPanel.add(generatedQvalue);

        q_generated_value = new JTextField();
        q_generated_value.setBounds(95,20,550,30);
        q_generated_value.setVisible(true);
        q_generated_value.setEditable(false);
        keyGenerationPanel.add(q_generated_value);

        generateKeysButton = new JButton("Generate keys");
        generateKeysButton.setBounds(650, 20, 120, 30);
        generateKeysButton.addActionListener(this);
        keyGenerationPanel.add(generateKeysButton);

        JLabel generatedPvalue = new JLabel("P value : ");
        generatedPvalue.setBounds(30,55,70,30);
        keyGenerationPanel.add(generatedPvalue);

        p_generated_value = new JTextField();
        p_generated_value.setBounds(95,55,675,30);
        p_generated_value.setVisible(true);
        p_generated_value.setEditable(false);
        keyGenerationPanel.add(p_generated_value);

        JLabel generatedGvalue = new JLabel("G value : ");
        generatedGvalue.setBounds(30,90,70,30);
        keyGenerationPanel.add(generatedGvalue);

        g_generated_value = new JTextField();
        g_generated_value.setBounds(95,90,675,30);
        g_generated_value.setVisible(true);
        g_generated_value.setEditable(false);
        keyGenerationPanel.add(g_generated_value);

        JLabel generatedXvalue = new JLabel("X value : ");
        generatedXvalue.setBounds(30,125,70,30);
        keyGenerationPanel.add(generatedXvalue);

        x_generated_value = new JTextField();
        x_generated_value.setBounds(95,125,675,30);
        x_generated_value.setVisible(true);
        x_generated_value.setEditable(false);
        keyGenerationPanel.add(x_generated_value);

        JLabel generatedYvalue = new JLabel("Y value : ");
        generatedYvalue.setBounds(30,160,70,30);
        keyGenerationPanel.add(generatedYvalue);

        y_generated_value = new JTextField();
        y_generated_value.setBounds(95,160,675,30);
        y_generated_value.setVisible(true);
        y_generated_value.setEditable(false);
        keyGenerationPanel.add(y_generated_value);

        JPanel signatureCalculationPanel = new JPanel();
        signatureCalculationPanel.setLayout(null);
        signatureCalculationPanel.setBounds(5,205,780,165);
        signatureCalculationPanel.setBackground(new java.awt.Color(204,204,204));
        signatureCalculationPanel.setBorder( BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Calculate signature:") );

        JLabel selectedFileLabel = new JLabel("Selected file : ");
        selectedFileLabel.setBounds(30,20,100,30);
        signatureCalculationPanel.add(selectedFileLabel);

        selected_file_value = new JTextField();
        selected_file_value.setBounds(120,20,525,30);
        selected_file_value.setVisible(true);
        selected_file_value.setEditable(false);
        signatureCalculationPanel.add(selected_file_value);

        calculateSignatureButton = new JButton("Select file");
        calculateSignatureButton.setBounds(650, 20, 120, 30);
        calculateSignatureButton.addActionListener(this);
        signatureCalculationPanel.add(calculateSignatureButton);

        JLabel calculatedRvalue = new JLabel("R value : ");
        calculatedRvalue.setBounds(30,55,70,30);
        signatureCalculationPanel.add(calculatedRvalue);

        r_calculated_value = new JTextField();
        r_calculated_value.setBounds(95,55,675,30);
        r_calculated_value.setVisible(true);
        r_calculated_value.setEditable(false);
        signatureCalculationPanel.add(r_calculated_value);

        JLabel calculatedSvalue = new JLabel("S value : ");
        calculatedSvalue.setBounds(30,90,70,30);
        signatureCalculationPanel.add(calculatedSvalue);

        s_calculated_value = new JTextField();
        s_calculated_value.setBounds(95,90,675,30);
        s_calculated_value.setVisible(true);
        s_calculated_value.setEditable(false);
        signatureCalculationPanel.add(s_calculated_value);

        JLabel calculatedHashValue = new JLabel("Hash : ");
        calculatedHashValue.setBounds(30,125,70,30);
        signatureCalculationPanel.add(calculatedHashValue);

        hash_calculated_value = new JTextField();
        hash_calculated_value.setBounds(95,125,675,30);
        hash_calculated_value.setVisible(true);
        hash_calculated_value.setEditable(false);
        signatureCalculationPanel.add(hash_calculated_value);

        JPanel verificationSignaturePanel = new JPanel();
        verificationSignaturePanel.setLayout(null);
        verificationSignaturePanel.setBounds(5,370,780,235);
        verificationSignaturePanel.setBackground(new java.awt.Color(204,204,204));
        verificationSignaturePanel.setBorder( BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Verify signature:") );

        JLabel selectedFileForVerifyLabel = new JLabel("Selected file : ");
        selectedFileForVerifyLabel.setBounds(30,20,100,30);
        verificationSignaturePanel.add(selectedFileForVerifyLabel);

        selected_file_verify_value = new JTextField();
        selected_file_verify_value.setBounds(120,20,525,30);
        selected_file_verify_value.setVisible(true);
        selected_file_verify_value.setEditable(false);
        verificationSignaturePanel.add(selected_file_verify_value);

        verifySignatureButton = new JButton("Select file");
        verifySignatureButton.setBounds(650, 20, 120, 30);
        verifySignatureButton.addActionListener(this);
        verificationSignaturePanel.add(verifySignatureButton);

        JLabel hashVerifyLabel = new JLabel("Hash : ");
        hashVerifyLabel.setBounds(30,55,70,30);
        verificationSignaturePanel.add(hashVerifyLabel);

        hash_verify_value = new JTextField();
        hash_verify_value.setBounds(95,55,675,30);
        hash_verify_value.setVisible(true);
        hash_verify_value.setEditable(false);
        verificationSignaturePanel.add(hash_verify_value);

        JLabel calculatedWvalue = new JLabel("W value : ");
        calculatedWvalue.setBounds(30,90,70,30);
        verificationSignaturePanel.add(calculatedWvalue);

        w_calculated_value = new JTextField();
        w_calculated_value.setBounds(95,90,675,30);
        w_calculated_value.setVisible(true);
        w_calculated_value.setEditable(false);
        verificationSignaturePanel.add(w_calculated_value);

        JLabel calculatedU1value = new JLabel("U1 value : ");
        calculatedU1value.setBounds(30,125,70,30);
        verificationSignaturePanel.add(calculatedU1value);

        u1_calculated_value = new JTextField();
        u1_calculated_value.setBounds(95,125,675,30);
        u1_calculated_value.setVisible(true);
        u1_calculated_value.setEditable(false);
        verificationSignaturePanel.add(u1_calculated_value);

        JLabel calculatedU2value = new JLabel("U2 value : ");
        calculatedU2value.setBounds(30,160,70,30);
        verificationSignaturePanel.add(calculatedU2value);

        u2_calculated_value = new JTextField();
        u2_calculated_value.setBounds(95,160,675,30);
        u2_calculated_value.setVisible(true);
        u2_calculated_value.setEditable(false);
        verificationSignaturePanel.add(u2_calculated_value);

        JLabel calculatedVvalue = new JLabel("V value : ");
        calculatedVvalue.setBounds(30,195,70,30);
        verificationSignaturePanel.add(calculatedVvalue);

        v_calculated_value = new JTextField();
        v_calculated_value.setBounds(95,195,675,30);
        v_calculated_value.setVisible(true);
        v_calculated_value.setEditable(false);
        verificationSignaturePanel.add(v_calculated_value);

        JPanel statusSignature = new JPanel();
        statusSignature.setLayout(null);
        statusSignature.setBounds(5,605,780,50);
        statusSignature.setBackground(new java.awt.Color(204,204,204));
        statusSignature.setBorder( BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Status signature:") );

        checkSignatureButton = new JButton("Check signature");
        checkSignatureButton.setBounds(640, 13, 130, 30);
        checkSignatureButton.addActionListener(this);
        statusSignature.add(checkSignatureButton);

        statusLabel = new JLabel("Signature result not determined!");
        statusLabel.setBounds(325,10,200,30);
        statusSignature.add(statusLabel);

        frame.add(keyGenerationPanel);
        frame.add(signatureCalculationPanel);
        frame.add(verificationSignaturePanel);
        frame.add(statusSignature);

        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == generateKeysButton) {
            dsa = DSAEncryptionManual.GetObject();
            q_generated_value.setText(dsa.Q.toString());
            p_generated_value.setText(dsa.P.toString());
            g_generated_value.setText(dsa.G.toString());
            x_generated_value.setText(dsa.X.toString());
            y_generated_value.setText(dsa.Y.toString());
        }
        if(e.getSource() == calculateSignatureButton) {
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int returnValue = jfc.showOpenDialog(null);

            if(returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();
                File file = new File(selectedFile.toURI());

                try {
                    FileInputStream fis = new FileInputStream(file);
                    byte[] data = new byte[fis.available()];
                    fis.read(data);

                    signature = dsa.signData(data);
                    selected_file_value.setText(file.getAbsolutePath());
                    hash_calculated_value.setText( DSAEncryptionManual.hashMessage(data).toString(16) );
                    r_calculated_value.setText(signature.R.toString());
                    s_calculated_value.setText(signature.S.toString());

                    fis.close();
                } catch (Exception x) {
                    x.printStackTrace();
                }
            }
        }
        if(e.getSource() == verifySignatureButton) {
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int returnValue = jfc.showOpenDialog(null);

            if(returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();
                File file = new File(selectedFile.toURI());

                try {
                    FileInputStream fis = new FileInputStream(file);
                    byte[] data = new byte[fis.available()];
                    fis.read(data);

                    verifySignature = dsa.verifySignature(data, signature);
                    selected_file_verify_value.setText(file.getAbsolutePath());
                    hash_verify_value.setText( DSAEncryptionManual.hashMessage(data).toString(16) );
                    w_calculated_value.setText(verifySignature.W.toString());
                    u1_calculated_value.setText(verifySignature.U1.toString());
                    u2_calculated_value.setText(verifySignature.U2.toString());
                    v_calculated_value.setText(verifySignature.V.toString());

                    fis.close();
                } catch (Exception x) {
                    x.printStackTrace();
                }
            }
        }
        if(e.getSource() == checkSignatureButton) {
            try {
                verifySignature.setSignatureOk(verifySignature.V.equals(signature.R));

                if(verifySignature.signatureOk) {
                    statusLabel.setText("Signature is OK. Files are equal.");
                } else {
                    statusLabel.setText("Signature is FAILED.");
                }
            } catch (Exception x) {
                x.printStackTrace();
            }
        }
    }
}
