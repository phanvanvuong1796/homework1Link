package thread;

import entity.Student;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by phanvuong on 10/12/16.
 */
public class ThreadReadFiles extends Thread {
    private File file;
    private ObjectMapper objectMapper;
    private List<Student> studentList;

    public ThreadReadFiles(File file) {
        this.file = file;
        this.objectMapper = new ObjectMapper();
        this.studentList = new ArrayList<Student>();
    }

    @Override
    public void run() {
        try {
            studentList = objectMapper.readValue(file, new TypeReference<List<Student>>() {});

        } catch (IOException e) {
            e.printStackTrace();
        }
        StudentInfoSingleton.getInstance().insertWithLock(studentList);
        System.out.println("Doc xong "+file.getName());
    }
}
