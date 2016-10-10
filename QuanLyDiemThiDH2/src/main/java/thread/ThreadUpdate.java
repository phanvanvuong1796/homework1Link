package thread;

import entity.ThiSinh;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import sun.rmi.runtime.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by bongm on 2016-10-04.
 */
public class ThreadUpdate extends Thread{
    private ObjectMapper objectMapper;
    private HashMap<String, List<ThiSinh>> listUpdate;
    private boolean checkUpdate;


    public ThreadUpdate() {
        this.listUpdate = new HashMap<String, List<ThiSinh>>();
        objectMapper = new ObjectMapper();
        checkUpdate = true;
    }

    @Override
    public void run() {
        File folder = new File("diem_thi_2016/");
        File[] files = folder.listFiles();
        HashMap<String , Long> fileState = new HashMap<String, Long>();
        List<ThiSinh> list = new ArrayList<ThiSinh>();
        for (File file :
                files) {
            fileState.put(file.getName(), file.lastModified());
        }
        while (true){
            files = folder.listFiles();
            try {
                Thread.sleep(30000);
                checkUpdate = false;
                System.out.println("Updating.....");
                listUpdate.clear();
                for (int i = 0; i < files.length; i++) {
                    list.clear();
                    if(fileState.containsKey(files[i].getName())){

                        if(files[i].lastModified() != fileState.get(files[i].getName())){
                            list = objectMapper.readValue(files[i], new TypeReference<List<ThiSinh>>() {});
                            fileState.put(files[i].getName(), files[i].lastModified());
                            listUpdate.put(files[i].getName(), list);
                        }
                    }else{
                        list = objectMapper.readValue(files[i], new TypeReference<List<ThiSinh>>() {});
                        fileState.put(files[i].getName(), files[i].lastModified());
                        listUpdate.put(files[i].getName(), list);
                    }

                }
                System.out.println("Update!!!");
                checkUpdate = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isCheckUpdate() {
        return checkUpdate;
    }

    public HashMap<String, List<ThiSinh>> getListUpdate() {
        return listUpdate;
    }
}
