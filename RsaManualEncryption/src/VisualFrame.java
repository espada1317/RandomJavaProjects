import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;

public class VisualFrame extends JFrame implements Action {

    private static VisualFrame instance;
    JTextField pInputField;
    JTextField qInputField;
    JTextField nInputField;
    JTextField fiInputField;
    JTextField eInputField;
    JTextField dInputField;
    JButton checkAndCalculate;
    JTextArea encryptInput;
    JTextArea decryptInput;
    JButton encryptButton;
    JButton decryptButton;
    JButton openFileEncrypt;
    JButton openFileDecrypt;
    JButton openImageEncrypt;
    JButton openImageDecrypt;

    public static VisualFrame getInstance() {
        if (instance == null) {
            instance = new VisualFrame();
        }
        return instance;
    }

    private VisualFrame() {
        JFrame frame = new JFrame();
        frame.setTitle("RSA Cipher Alg");
        frame.setSize(600, 465);
        frame.setLayout(null);
        frame.setBackground(new java.awt.Color(240, 173, 181));

        JPanel keyGenerationPanel = new JPanel();
        keyGenerationPanel.setLayout(null);
        keyGenerationPanel.setBounds(5, 5, 575, 130);
        keyGenerationPanel.setBackground(new java.awt.Color(204, 204, 204));
        keyGenerationPanel.setBorder( BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Generate keys:") );

        JLabel givePvalue = new JLabel("Give P value");
        givePvalue.setBounds(30, 20, 70, 30);
        keyGenerationPanel.add(givePvalue);

        pInputField = new JTextField();
        pInputField.setBounds(105, 20, 100, 30);
        pInputField.setVisible(true);
        keyGenerationPanel.add(pInputField);

        JLabel giveQvalue = new JLabel("Give Q value");
        giveQvalue.setBounds(230, 20, 70, 30);
        keyGenerationPanel.add(giveQvalue);

        qInputField = new JTextField();
        qInputField.setBounds(330, 20, 100, 30);
        qInputField.setVisible(true);
        keyGenerationPanel.add(qInputField);

        checkAndCalculate = new JButton("Calculate");
        checkAndCalculate.setBounds(450, 20, 100, 30);
        checkAndCalculate.addActionListener(this);
        keyGenerationPanel.add(checkAndCalculate);

        JLabel nValue = new JLabel("N = p * q");
        nValue.setBounds(30, 55, 100, 30);
        keyGenerationPanel.add(nValue);

        nInputField = new JTextField();
        nInputField.setBounds(105, 55, 100, 30);
        nInputField.setVisible(true);
        nInputField.setEditable(false);
        keyGenerationPanel.add(nInputField);

        JLabel fiValue = new JLabel("Fi(n) = (p-1)(q-1)");
        fiValue.setBounds(230, 55, 100, 30);
        keyGenerationPanel.add(fiValue);

        fiInputField = new JTextField();
        fiInputField.setBounds(330, 55, 100, 30);
        fiInputField.setVisible(true);
        fiInputField.setEditable(false);
        keyGenerationPanel.add(fiInputField);

        JLabel eValue = new JLabel("E value");
        eValue.setBounds(30, 90, 100, 30);
        keyGenerationPanel.add(eValue);

        eInputField = new JTextField();
        eInputField.setBounds(105, 90, 100, 30);
        eInputField.setVisible(true);
        eInputField.setEditable(false);
        keyGenerationPanel.add(eInputField);

        JLabel dValue = new JLabel("D value");
        dValue.setBounds(230, 90, 100, 30);
        keyGenerationPanel.add(dValue);

        dInputField = new JTextField();
        dInputField.setBounds(330, 90, 100, 30);
        dInputField.setVisible(true);
        dInputField.setEditable(false);
        keyGenerationPanel.add(dInputField);

        JPanel operationalPanel = new JPanel();
        operationalPanel.setLayout(null);
        operationalPanel.setBounds(5, 135, 575, 290);
        operationalPanel.setBackground(new java.awt.Color(204, 204, 204));
        operationalPanel.setBorder( BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Encryption/Decryption:") );

        encryptInput = new JTextArea(10, 20);
        encryptInput.setBounds(15, 25, 220, 125);
        encryptInput.setVisible(true);
        operationalPanel.add(encryptInput);

        decryptInput = new JTextArea(10, 20);
        decryptInput.setBounds(340, 25, 220, 125);
        decryptInput.setVisible(true);
        operationalPanel.add(decryptInput);

        encryptButton = new JButton("Encrypt ->");
        encryptButton.setBounds(240, 25, 95, 60);
        encryptButton.setVisible(true);
        encryptButton.addActionListener(this);
        operationalPanel.add(encryptButton);

        decryptButton = new JButton("<- Decrypt");
        decryptButton.setBounds(240, 90, 95, 60);
        decryptButton.setVisible(true);
        decryptButton.addActionListener(this);
        operationalPanel.add(decryptButton);

        openFileEncrypt = new JButton("Open File for Encrypt");
        openFileEncrypt.setBounds(25, 155, 200, 60);
        openFileEncrypt.setVisible(true);
        openFileEncrypt.addActionListener(this);
        operationalPanel.add(openFileEncrypt);

        openFileDecrypt = new JButton("Open File for Decrypt");
        openFileDecrypt.setBounds(350, 155, 200, 60);
        openFileDecrypt.setVisible(true);
        openFileDecrypt.addActionListener(this);
        operationalPanel.add(openFileDecrypt);

        openImageEncrypt = new JButton("Open Image for Encrypt");
        openImageEncrypt.setBounds(25, 220, 200, 60);
        openImageEncrypt.setVisible(true);
        openImageEncrypt.addActionListener(this);
        operationalPanel.add(openImageEncrypt);

        openImageDecrypt = new JButton("Open Image for Decrypt");
        openImageDecrypt.setBounds(350, 220, 200, 60);
        openImageDecrypt.setVisible(true);
        openImageDecrypt.addActionListener(this);
        operationalPanel.add(openImageDecrypt);

        frame.add(keyGenerationPanel);
        frame.add(operationalPanel);

        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public boolean isPrime(int number) {
        for (int i = 2; i <= number / 2; ++i) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static int cmmdc(int a, int b)
    {
        if (b == 0)
            return a;

        return cmmdc(b, a % b);
    }

    public static int[] encrypt(int[] message, int e, int n) {
        int[] result = new int[message.length];

        for(int i = 0; i < message.length; i++) {
            BigInteger m = BigInteger.valueOf(message[i]);
            BigInteger N = BigInteger.valueOf(n);
            BigInteger c = (m.pow(e).mod(N));
            result[i] = c.intValue();
        }

        return result;
    }

    public static int[] decrypt(int[] message, int d, int n) {
        int[] result = new int[message.length];

        for(int i = 0; i < message.length; i++) {
            BigInteger c = BigInteger.valueOf(message[i]);
            BigInteger N = BigInteger.valueOf(n);
            BigInteger msgback = (c.pow(d)).mod(N);
            result[i] = msgback.intValue();
        }

        return result;
    }

    public static int[] CharArrayToIntArray(char[] input_array) {
        int[] result = new int[input_array.length];

        for(int i = 0; i < input_array.length; i++) {
            result[i] = input_array[i];
        }
        return result;
    }

    public static char[] IntArrayToCharArray(int[] input_array) {
        char[] result = new char[input_array.length];

        for(int i = 0; i < input_array.length; i++) {
            result[i] = (char) input_array[i];
        }
        return result;
    }

    public static int[] ByteArrayToIntArray(byte[] input_array) {
        int[] result = new int[input_array.length];

        for(int i = 0; i < input_array.length; i++) {
//            System.out.print(input_array[i] + " ");
            result[i] = (int) input_array[i] & 0xff;
        }
        return result;
    }

    public static byte[] IntArrayToByteArray(int[] input_array) {
        byte[] result = new byte[input_array.length];

        for(int i = 0; i < input_array.length; i++) {
            result[i] = (byte) input_array[i];
        }
        return result;
    }

    public static String[] SplitStringHexTo5Chars(String input) {
        String[] result = new String[input.length()/5];

//        System.out.println(input.length());

        int j = 0;
        for (int i = 0; i < input.length(); i += 5) {
            result[j] = input.substring(i, Math.min(input.length(), i + 5));
//            System.out.println(input + " " + i + " part: " + result[j]);
            j++;
        }

        return result;
    }

    public static String[] IntArrayToHexArray(int[] input_array) {
        String[] result = new String[input_array.length];

        for(int i = 0; i < input_array.length; i++) {
            String temp = Integer.toHexString( input_array[i] );
            while (temp.length() < 5) {
                temp = "0" + temp;
            }
            result[i] = temp;
        }
        return result;
    }

    public static int[] HexArrayToIntArray(String[] input_array) {
        int[] result = new int[input_array.length];

        for(int i = 0; i < input_array.length; i++) {
            result[i] = Integer.parseInt(input_array[i], 16);
//            System.out.println(input_array[i] + " -> " + result[i]);
        }
        return result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkAndCalculate) {
            if(pInputField.getText().length() != 0 && qInputField.getText().length() != 0) {
                int p =  Integer.parseInt( pInputField.getText().toString().trim() );
                int q =  Integer.parseInt( qInputField.getText().toString().trim() );

                if( !isPrime(p) || !isPrime(q) ) {
                    JOptionPane.showMessageDialog(null, "P or Q are not prime", "InfoBox: Value error", JOptionPane.INFORMATION_MESSAGE);
                }

                int n = p * q;
                nInputField.setText( String.valueOf(n) );
                int fi_n = (p - 1) * (q - 1);
                fiInputField.setText( String.valueOf(fi_n) );

                int enc;
                for (enc = 2; enc < fi_n; enc++) {
                    if (cmmdc(enc, fi_n) == 1) {
                        break;
                    }
                }
                eInputField.setText( String.valueOf(enc) );

                BigInteger en = BigInteger.valueOf(enc);
                BigInteger fiN = BigInteger.valueOf(fi_n);
                BigInteger dec = en.modInverse(fiN);

                dInputField.setText( String.valueOf(dec) );

            } else {
                JOptionPane.showMessageDialog(null, "Complete fields", "InfoBox: Value error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if(e.getSource() == encryptButton) {
            String message = encryptInput.getText().toString();
            int n = Integer.parseInt( nInputField.getText() );
            int enc = Integer.parseInt( eInputField.getText() );

            char[] char_message = message.trim().toCharArray();
            int[] int_message = CharArrayToIntArray(char_message);
            int[] int_result = encrypt(int_message, enc, n);
//            char[] char_result = IntArrayToCharArray(int_result);

            String[] string_result = IntArrayToHexArray(int_result);

            String result = String.join("", string_result);
            decryptInput.setText( result );
        }
        if(e.getSource() == decryptButton) {
            String message = decryptInput.getText().toString();
            int n = Integer.parseInt( nInputField.getText() );
            int dec = Integer.parseInt( dInputField.getText() );

            String[] temp_array = SplitStringHexTo5Chars(message);
            int[] int_message = HexArrayToIntArray(temp_array);

//            char[] char_message = message.trim().toCharArray();
//            int[] int_message = CharArrayToIntArray(char_message);
            int[] int_result = decrypt(int_message, dec, n);
            char[] char_result = IntArrayToCharArray(int_result);

            String result = String.valueOf(char_result);
            encryptInput.setText( result );
        }

        if(e.getSource() == openFileEncrypt) {
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int returnValue = jfc.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();
                File file = new File(selectedFile.toURI());

                if (selectedFile.getAbsolutePath().toLowerCase().contains(".txt")) {
                    String message = "";

                    try {
                        Scanner sc = null;
                        try {
                            sc = new Scanner(file);
                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }

                        String result = "";
                        while (sc.hasNextLine()) {
                            String temp = sc.nextLine();
                            result += temp;
//                            System.out.println(temp);
                        }

                        int n = Integer.parseInt( nInputField.getText() );
                        int enc = Integer.parseInt( eInputField.getText() );
                        char[] char_message = result.toCharArray();
                        int[] int_message = CharArrayToIntArray(char_message);
                        int[] int_result = encrypt(int_message, enc, n);
//                        char[] char_result = IntArrayToCharArray(int_result);
                        String[] string_result = IntArrayToHexArray(int_result);

                        jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                        returnValue = jfc.showSaveDialog(null);

                        if (returnValue == JFileChooser.APPROVE_OPTION) {
                            selectedFile = jfc.getSelectedFile();
                            BufferedWriter out = null;
                            try {
                                out = new BufferedWriter(new FileWriter(selectedFile));
                                out.write( String.join("", string_result) );
                                out.close();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }

                    } catch (Exception x) {
                        x.printStackTrace();
                    }
                }
            }
        }

        if(e.getSource() == openFileDecrypt) {
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int returnValue = jfc.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();
                File file = new File(selectedFile.toURI());

                if (selectedFile.getAbsolutePath().toLowerCase().contains(".txt")) {
                    String message = "";

                    try {
                        Scanner sc = null;
                        try {
                            sc = new Scanner(file);
                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }

                        String result = "";
                        while (sc.hasNextLine()) {
                            String temp = sc.nextLine();
                            result += temp;
//                            System.out.println(temp);
                        }

                        int n = Integer.parseInt( nInputField.getText() );
                        int dec = Integer.parseInt( dInputField.getText() );

                        String[] temp_array = SplitStringHexTo5Chars(result);
                        int[] int_message = HexArrayToIntArray(temp_array);
//                        char[] char_message = result.toCharArray();
//                        int[] int_message = CharArrayToIntArray(char_message);
                        int[] int_result = decrypt(int_message, dec, n);
                        char[] char_result = IntArrayToCharArray(int_result);

                        jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                        returnValue = jfc.showSaveDialog(null);

                        if (returnValue == JFileChooser.APPROVE_OPTION) {
                            selectedFile = jfc.getSelectedFile();
                            BufferedWriter out = null;
                            try {
                                out = new BufferedWriter(new FileWriter(selectedFile));
                                out.write( String.valueOf(char_result) );
                                out.close();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }

                    } catch (Exception x) {
                        x.printStackTrace();
                    }
                }
            }
        }

        if(e.getSource() == openImageEncrypt) {

            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int returnValue = jfc.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();
                File file = new File(selectedFile.toURI());

                if (selectedFile.getAbsolutePath().toLowerCase().contains(".jpg") || selectedFile.getAbsolutePath().toLowerCase().contains(".png")) {
                    String message = "";

                    try {
                        FileInputStream fis = new FileInputStream(file);

                        byte[] data = new byte[fis.available()];
                        fis.read(data);

                        int n = Integer.parseInt( nInputField.getText() );
                        int enc = Integer.parseInt( eInputField.getText() );

                        int[] int_data = ByteArrayToIntArray(data);
                        int[] int_result = encrypt(int_data, enc, n);
                        String[] string_result = IntArrayToHexArray(int_result);

                        jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                        returnValue = jfc.showSaveDialog(null);

                        if (returnValue == JFileChooser.APPROVE_OPTION) {
                            selectedFile = jfc.getSelectedFile();

                            BufferedWriter out = null;
                            try {
                                out = new BufferedWriter(new FileWriter(selectedFile));
                                out.write( String.join("", string_result) );
                                out.close();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }

                        }

                    } catch (Exception x) {
                        x.printStackTrace();
                    }
                }
            }
        }

        if(e.getSource() == openImageDecrypt) {

            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int returnValue = jfc.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();
                File file = new File(selectedFile.toURI());
                String result = "";

                if (selectedFile.getAbsolutePath().toLowerCase().contains(".txt")) {
                    String message = "";

                    try {
                        Scanner sc = null;
                        try {
                            sc = new Scanner(file);
                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }

                        while (sc.hasNextLine()) {
                            String temp = sc.nextLine();
                            result += temp;
//                            System.out.println(temp);
                        }

                        int n = Integer.parseInt(nInputField.getText());
                        int dec = Integer.parseInt(dInputField.getText());

                        String[] temp_array = SplitStringHexTo5Chars(result);
                        int[] int_message = HexArrayToIntArray(temp_array);
//                        int[] int_data = ByteArrayToIntArray(data);
                        int[] int_result = decrypt(int_message, dec, n);
                        byte[] byte_result = IntArrayToByteArray(int_result);

                        jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                        returnValue = jfc.showSaveDialog(null);

                        if (returnValue == JFileChooser.APPROVE_OPTION) {
                            selectedFile = jfc.getSelectedFile();

                            FileOutputStream fos = null;
                            try {
                                fos = new FileOutputStream(selectedFile);
                                fos.write(byte_result);
                                fos.close();
                            } catch (FileNotFoundException ex) {
                                throw new RuntimeException(ex);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }

                    } catch (Exception x) {
                        x.printStackTrace();
                    }

                }
            }
        }
    }

    @Override
    public Object getValue(String key) {
        return null;
    }

    @Override
    public void putValue(String key, Object value) {

    }
}