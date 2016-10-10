package models;

import org.codehaus.jackson.map.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bongm on 2016-09-23.
 */
public abstract class BaseModel<T> {

    protected List<T> list;
    protected ObjectMapper objectMapper;

    public BaseModel() {
        list = new ArrayList<T>();
        objectMapper = new ObjectMapper();
    }

    public abstract List<T> getElement() throws Exception;

    public abstract boolean insertElement(T t) throws Exception;

    public abstract boolean updateElement(T t) throws Exception;

    public abstract boolean deleteElement(T t) throws Exception;

    public abstract List<T> findElement(T t, int option);

    public abstract void statistic();

    public abstract List<T> sortElement(int option);

    public abstract boolean writeResult(List<T> list) throws Exception;

}
