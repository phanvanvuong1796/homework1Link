package controllers;

import entity.Student;
import models.StudentModel;

import java.io.File;
import java.util.List;

/**
 * Created by phanvuong on 10/12/16.
 */
public class StudentController extends BaseController<Student> {

    private StudentModel studentModel;

    public StudentController() {
        this.studentModel = new StudentModel();
    }

    public List<Student> execOfGetElement(String path) throws Exception {
        return studentModel.getElement(path);
    }


    public boolean execOfInsertElement(Student student) throws Exception {
        return false;
    }

    public boolean execOfUpdateElement(Student student) throws Exception {
        return studentModel.updateElement(student);
    }

    public boolean execOfDeleteElement(Student student) throws Exception {
        return studentModel.deleteElement(student);
    }

    public List<Student> execOfFindElement(Student student, int option) {
        return studentModel.findElement(student, option);
    }

    public void execOfStatistic() {
        studentModel.statistic();
    }

    public List<Student> execOfSortElement(int option) {
        return null;
    }

    public boolean execOfWriteResult(List<Student> list) throws Exception {
        return studentModel.writeResult(list);
    }

    public List<Student> execOfGetLastestData() throws Exception {
        return studentModel.getLastestData();
    }

}
