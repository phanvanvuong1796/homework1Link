package thread;

import entity.ThiSinh;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bongm on 2016-10-03.
 */
public class ThreadReadFile extends Thread {
    private File file;
    private ObjectMapper objectMapper;
    private List<ThiSinh> list;
    private boolean check;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public ThreadReadFile(File file){
        this.file = file;
        objectMapper = new ObjectMapper();
        list = new ArrayList<ThiSinh>();
        check = false;
    }

    @Override
    public void run() {

        try {
            list = objectMapper.readValue(file, new TypeReference<List<ThiSinh>>() {});
            System.out.println("Doc "+file.getName()+" xong!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<ThiSinh> getList() {
        return list;
    }
}
