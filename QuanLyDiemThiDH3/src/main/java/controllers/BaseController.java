package controllers;

import entity.Student;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * Created by bongm on 2016-09-23.
 */
public abstract class BaseController<T> {

    public abstract List<Student> execOfGetElement(String path) throws Exception;

    public abstract boolean execOfInsertElement(T t) throws Exception;

    public abstract boolean execOfUpdateElement(T t) throws Exception;

    public abstract boolean execOfDeleteElement(T t) throws Exception;

    public abstract List<T> execOfFindElement(T t, int option);

    public abstract void execOfStatistic();

    public abstract List<T> execOfSortElement(int option);

    public abstract boolean execOfWriteResult(List<T> list) throws Exception;

    public abstract List<T> execOfGetLastestData() throws Exception;
}
