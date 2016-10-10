package view;

import java.io.File;

/**
 * Created by bongm on 2016-10-07.
 */
public class TestCode {
    public static void main(String[] args) {
        File folder = new File("diem_thi_2016/");
        File[] listFiles = folder.listFiles();
        for (File file :
                listFiles) {
            System.out.println(""+file.lastModified());
        }
    }
}
