package listeners;

import main.VisualFrame;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class OpenFileListener implements ActionListener {

    String typeOfOperation;

    public OpenFileListener(String typeOfOperation) {
        this.typeOfOperation = typeOfOperation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());

            File file = new File(selectedFile.toURI());
            String result = "";

            if (selectedFile.getAbsolutePath().toLowerCase().contains(".png") || selectedFile.getAbsolutePath().toLowerCase().contains(".jpeg") || selectedFile.getAbsolutePath().toLowerCase().contains(".jpg")) {
                result = OperateWithImage(file);
            } else {

                Scanner sc = null;
                try {
                    sc = new Scanner(file);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                while (sc.hasNextLine()) {
                    String temp = sc.nextLine();
                    result += temp;
                    System.out.println(temp);
                }
            }

            if (typeOfOperation.equals("openDecrypted")) {
                VisualFrame.getInstance().setEncryptInput(result);
            } else if (typeOfOperation.equals("openEncrypted")) {
                VisualFrame.getInstance().setDecryptInput(result);
            }

        }
    }

    public String OperateWithImage(File file) {
        String message = "";

        try {
            FileInputStream fis = new FileInputStream(file);

            byte[] data = new byte[fis.available()];
            fis.read(data);

            int i = 0;
            for(byte x : data) {
                System.out.println( "image_data[" + (i+1) + "] : " +  x );
                i++;
                String s = String.format("%8s", Integer.toBinaryString(x & 0xFF)).replace(' ', '0');
                message += s;
            }

//            System.out.println(message);

            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return message;
    }
}

//    public String ConvertByteToStringByte(byte[] data) {
//        String result = "";
//
//        for(int i : data) {
//            System.out.println(i);
//            result += String.valueOf(i);
//        }
//
//        return result;
//    }