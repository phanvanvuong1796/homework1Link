package models;

import entity.Student;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by bongm on 2016-09-23.
 */
public abstract class BaseModel<T> {

    protected List<T> listStudent;
    protected ObjectMapper objectMapper;

    public BaseModel() {
        listStudent = new ArrayList<T>();
        objectMapper = new ObjectMapper();
    }

    public abstract List<Student> getElement(String path) throws Exception;

    public abstract boolean insertElement(T t) throws Exception;

    public abstract boolean updateElement(T t) throws Exception;

    public abstract boolean deleteElement(T t) throws Exception;

    public abstract List<T> findElement(T t, int option);

    public abstract void statistic();

    public abstract List<T> sortElement(int option);

    public abstract boolean writeResult(List<T> list) throws Exception;

    public abstract List<Student> getLastestData() throws Exception;
}
