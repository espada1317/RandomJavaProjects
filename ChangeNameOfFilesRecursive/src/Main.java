import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> volumeChapters = new ArrayList<>();
            volumeChapters.add(1);
            volumeChapters.add(9);
            volumeChapters.add(17);
            volumeChapters.add(25);
            volumeChapters.add(33);
            volumeChapters.add(42);
            volumeChapters.add(51);
            volumeChapters.add(60);
            volumeChapters.add(69);
            volumeChapters.add(78);
            volumeChapters.add(87);
            volumeChapters.add(96);
            volumeChapters.add(105);
            volumeChapters.add(114);
            volumeChapters.add(123);
            volumeChapters.add(132);
            volumeChapters.add(142);
            volumeChapters.add(152);

        int no_chapter = 0;

        for(int j = 1; j <= 162; j++)
        {
            if(volumeChapters.contains(j)) {
                no_chapter++;
            }

            String folder_path = "Z:\\Monster\\Monster Том " + no_chapter + " Глава " + j + " [mangalib.me]";
            File currentFolder = new File(folder_path);
            System.out.println(folder_path);

            File[] file_array = currentFolder.listFiles();
            for (int i = 0; i < file_array.length; i++)
            {
                if (file_array[i].isFile())
                {
                    File myfile = new File(folder_path + "\\" + file_array[i].getName());
                    String long_file_name = file_array[i].getName();
                    String[] tokens = long_file_name.split("\\.");
                    String new_file_name;

                    if(tokens[0].length() == 1) {
                        System.out.println(tokens[0] + " | " + tokens[1]);
                        new_file_name = "0" + tokens[0] + ".jpeg";
                        myfile.renameTo(new File(folder_path + "\\" + new_file_name));
                    }
//
//                    System.out.println(long_file_name);
//                    System.out.println(new_file_name);
//
                }
            }

        }
    }
}