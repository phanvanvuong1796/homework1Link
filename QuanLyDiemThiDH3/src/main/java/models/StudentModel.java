package models;

import entity.Student;
import org.codehaus.jackson.type.TypeReference;
import thread.StudentInfoSingleton;
import thread.ThreadReadFiles;
import thread.ThreadUpdateStudent;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by phanvuong on 10/12/16.
 */
public class StudentModel extends BaseModel<Student> {

    public List<Student> getElement(String path) throws Exception {
        File folder  = new File(path);
        File[] listFiles = folder.listFiles();
        ThreadReadFiles[] threadReadFiles = new ThreadReadFiles[listFiles.length];
        for (int i = 0; i < threadReadFiles.length; i++) {
            threadReadFiles[i] = new ThreadReadFiles(listFiles[i]);
            threadReadFiles[i].start();
        }
        for (int i = 0; i < threadReadFiles.length; i++) {
            threadReadFiles[i].join();
        }
        listStudent = StudentInfoSingleton.getInstance().getStudentList();
        ThreadUpdateStudent threadUpdateStudent = new ThreadUpdateStudent(path);
        threadUpdateStudent.setDaemon(true);
        threadUpdateStudent.start();
        return listStudent;
    }

    public boolean insertElement(Student student) throws Exception {
        return false;
    }

    public boolean updateElement(Student student) throws Exception {
        boolean result = false;
        File file = new File("diem_thi_2016/"+student.getSrcFile());
        List<Student> list = objectMapper.readValue(file, new TypeReference<List<Student>>() {});
        for (int i = 0; i < list.size(); i++) {
            if(student.getId().equals(list.get(i).getId())){
                list.add(i, student);
                list.remove(i+1);
                break;
            }
        }
        try {
            objectMapper.writeValue(file, list);
            result = true;
        }catch (Exception e){
            throw e;
        }
        return result;
    }

    public boolean deleteElement(Student student) throws Exception {
        boolean result = false;
        File file = new File("diem_thi_2016/" + student.getSrcFile());
        List<Student> list = objectMapper.readValue(file, new TypeReference<List<Student>>() {});
        for (int i = 0; i < listStudent.size(); i++) {
            if(list.get(i).getId().equals(student.getId())){
                list.remove(i);
                StudentInfoSingleton.getInstance().deleteStudentList(student);
                break;
            }
        }

        try {
            objectMapper.writeValue(file, list);
            result = true;
        } catch (Exception e) {
            throw e;
        }

        return result;
    }

    public List<Student> findElement(Student student, int option) {
        List<Student> resultList = new ArrayList<Student>();
        listStudent = StudentInfoSingleton.getInstance().getStudentList();
        if (option == 1) {
            for (Student cStd : listStudent) {
                if (cStd.getTotalMark() == student.getTotalMark()) {
                    resultList.add(cStd);
                }
            }
        } else if (option == 2) {
            for (Student cStd : listStudent) {
                if (cStd.getId().equals(student.getId())) {
                    resultList.add(cStd);
                }
            }
        } else if (option == 3) {
            for (Student cStd : listStudent) {
                if (cStd.getName().equals(student.getName())) {
                    resultList.add(cStd);

                }
            }
        } else if (option == 4) {
            for (Student cStd : listStudent) {
                for (double cMark : cStd.getMark()) {
                    if (student.getMark()[0] == cMark) {
                        resultList.add(cStd);
                        break;
                    }
                }
            }
        }
        return resultList;
    }

    public void statistic() {
        int[] statistic = new int[]{0, 0, 0, 0};
        listStudent = StudentInfoSingleton.getInstance().getStudentList();
        System.out.println("Tong so thi sinh du thi: " + listStudent.size());
        for (Student std : listStudent) {
            if (std.getTotalMark() < 15)
                statistic[0]++;
            else if (std.getTotalMark() >= 15 && std.getTotalMark() < 20)
                statistic[1]++;
            else if (std.getTotalMark() >= 20 && std.getTotalMark() < 25)
                statistic[2]++;
            else
                statistic[3]++;
        }
        int mucDiem = 0;
        for (int tongSV : statistic) {
            if (mucDiem == 0) {
                System.out.println("Muc diem tu 0 den 15: " + tongSV + " thi sinh");
                mucDiem += 15;
            } else {
                System.out.println("Muc diem tu " + mucDiem + " den " + (mucDiem + 5) + ": " + tongSV + "thi sinh");
                mucDiem += 5;
            }
        }
    }

    public List<Student> sortElement(int option) {
        listStudent = StudentInfoSingleton.getInstance().getStudentList();
        if (option == 1) {
            Collections.sort(listStudent, new Comparator<Student>() {
                public int compare(Student o1, Student o2) {
                    return o1.getId().compareTo(o2.getId());
                }
            });
        } else if (option == 2) {
            Collections.sort(listStudent, new Comparator<Student>() {
                public int compare(Student o1, Student o2) {
                    return o1.reverName().compareTo(o2.reverName());
                }
            });
        } else {
            Collections.sort(listStudent, new Comparator<Student>() {
                public int compare(Student o1, Student o2) {
                    if (o1.getTotalMark() < o2.getTotalMark())
                        return 1;
                    else if (o1.getTotalMark() == o2.getTotalMark())
                        return 0;
                    else
                        return -1;
                }
            });
        }
        return listStudent;
    }

    public boolean writeResult(List<Student> list) throws Exception {
        File file = new File("KetQua.json");
        boolean check = false;
        try {
            objectMapper.writeValue(file, list);
            check = true;
        }catch (Exception e){
            throw e;
        }
        return check;
    }

    public List<Student> getLastestData() throws Exception {
        listStudent = StudentInfoSingleton.getInstance().getStudentList();
        return listStudent;
    }

}
