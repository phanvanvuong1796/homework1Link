package view;

import entity.ThiSinh;
import thread.ThreadReadFile;
import thread.ThreadUpdate;

import java.io.File;
import java.util.*;

/**
 * Created by bongm on 2016-10-03.
 */
public class MainView {
    public static void main(String[] args) {
        HashMap<String, List<ThiSinh>> list = new HashMap<String, List<ThiSinh>>();

        File folder = new File("diem_thi_2016");
        File[] files = folder.listFiles();
        ThreadReadFile[] readFiles = new ThreadReadFile[files.length];
        for (int i = 0; i < files.length; i++) {
            readFiles[i] = new ThreadReadFile(files[i]);
            readFiles[i].start();
        }
        int count = 0, k = 0;
        while (count != readFiles.length) {
            if (k == readFiles.length)
                k = 0;
            if (!readFiles[k].isAlive() && !readFiles[k].isCheck()) {
                list.put(files[k].getName(), readFiles[k].getList());
                count++;
                readFiles[k].setCheck(true);
            }
            k++;
        }

        Collection<List<ThiSinh>> values = list.values();
        for (List<ThiSinh> listItem :
                values) {
            for (ThiSinh std :
                    listItem) {
                std.display();
            }
        }

        ThreadUpdate threadUpdate = new ThreadUpdate();
        threadUpdate.setDaemon(true);
        threadUpdate.start();


        int chon;
        Scanner nhap = new Scanner(System.in);
        HashMap<String, List<ThiSinh>> listUpdate = new HashMap<String, List<ThiSinh>>();
        do {
            chon = nhap.nextInt();
            if (threadUpdate.isCheckUpdate()) {
                listUpdate = threadUpdate.getListUpdate();
                if(listUpdate.size()!=0){
                    System.out.println(listUpdate.size());
                    Set<String> listKey = listUpdate.keySet();
                    for (String key :
                            listKey) {
                        list.put(key, listUpdate.get(key));
                    }
                }
            }
            values = list.values();
            for (List<ThiSinh> listItem : values) {
                for (ThiSinh std :
                        listItem) {
                    std.display();
                }
            }
        } while (chon != 0);
    }
}
