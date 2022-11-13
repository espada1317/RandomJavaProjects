package listeners;

import main.VisualFrame;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class SaveFileListener implements ActionListener {

    String typeOfOperation;

    public SaveFileListener(String typeOfOperation) {
        this.typeOfOperation = typeOfOperation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        int returnValue = jfc.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            if (selectedFile.getAbsolutePath().toLowerCase().contains(".png") || selectedFile.getAbsolutePath().toLowerCase().contains(".jpeg") || selectedFile.getAbsolutePath().toLowerCase().contains(".jpg")) {
                String data = "";
                if(typeOfOperation.equals("saveDecrypted")) {
                    data = VisualFrame.getInstance().getEncryptInput();
                } else if (typeOfOperation.equals("saveEncrypted")) {
                    data = VisualFrame.getInstance().getDecryptInput() ;
                }

                byte[] bytes = new byte[(int) Math.ceil(data.length() / 8.0)];
                for (int i = 0, j = 0, k = 7; i < data.length(); i++) {
                    byte bit = (byte) (data.charAt(i) - '0');
                    bytes[j] = (byte) (bytes[j] | bit << k);
                    if (k == 0) {
                        j++;
                        k = 7;
                    } else {
                        k--;
                    }
                }

                int i = 0;
                for (byte x : bytes) {
                    System.out.println("split[" + (i+1) + "] : " + x);
                    i++;
                }


//                String[] split = new String[data.length() / 8];
//                ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
//
//                byte c[] = outputStream.toByteArray( );
//                for (int i = 0; i < split.length; i++) {
//                    split[i] = data.substring(i * 8, i * 8 + 8);
//                    byte[] bval = new BigInteger(split[i], 2).toByteArray();
//                    System.out.println("split[" + (i+1) + "] : " + bval[0]);
//                    try {
//                        outputStream.write(bval);
//                    } catch (IOException ex) {
//                        throw new RuntimeException(ex);
//                    }
//                }
//                byte final_data[] = outputStream.toByteArray();
//
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(selectedFile);
                    fos.write(bytes);
                    fos.close();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                throw new RuntimeException(ex);
                }

            } else {
                BufferedWriter out = null;
                try {
                    out = new BufferedWriter(new FileWriter(selectedFile));

                    if(typeOfOperation.equals("saveDecrypted")) {
                        out.write(VisualFrame.getInstance().getEncryptInput());
                    } else if (typeOfOperation.equals("saveEncrypted")) {
                        out.write(VisualFrame.getInstance().getDecryptInput());
                    }

                    out.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
