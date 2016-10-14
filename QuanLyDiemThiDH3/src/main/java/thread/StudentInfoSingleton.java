package thread;

import entity.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by phanvuong on 10/12/16.
 */
public class StudentInfoSingleton {
    private HashMap<String, Student> studentList;
    private ReentrantLock lock;

    public StudentInfoSingleton() {
        this.studentList = new HashMap<String, Student>();
        this.lock = new ReentrantLock();
    }

    public List<Student> getStudentList() {
        Collection<Student> studentCollection = studentList.values();
        List<Student> list = new ArrayList<Student>();
        list.addAll(studentCollection);
        return list;
    }

    public void deleteStudentList(Student student){
        studentList.remove(student.getId());
    }

    private static class StudentInfoSingletonHolder {
        private static final StudentInfoSingleton INSTANCE = new StudentInfoSingleton();
    }

    public static StudentInfoSingleton getInstance() {
        return StudentInfoSingletonHolder.INSTANCE;
    }

    public void insertWithLock(List<Student> list){
        lock.lock();
        for (Student std :
                list) {
            studentList.put(std.getId(), std);
        }
        lock.unlock();
    }

    /*public void updateList(List<Student> listUpdate){
        for (Student std :
                listUpdate) {
            studentList.put(std.getId(), std);
        }
    }*/
}
