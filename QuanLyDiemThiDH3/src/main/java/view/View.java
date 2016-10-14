package view;

import controllers.StudentController;
import entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by phanvuong on 10/14/16.
 */
public class View {
    private Scanner nhap;
    private StudentController studentController;

    public View(Scanner nhap) {
        this.nhap = nhap;
        studentController = new StudentController();
    }

    public void mainView() {
        System.out.println("------------------------");
        System.out.println("1. Lấy dữ liệu sinh viên");
        System.out.println("2. Cập nhật, xóa thông tin sinh viên");
        System.out.println("3. Tim Kiem sinh vien");
        System.out.println("4. Sap xep sinh vien");
        System.out.println("5. Thong ke");
        System.out.println("6. Hien thi danh sach");
        System.out.println("7. Thoat");
        System.out.println("------------------------");
    }

    public int updateView() {
        System.out.println("---------------------");
        System.out.println("1. Cập nhật sinh viên");
        System.out.println("2. Xóa sinh viên");
        System.out.println("3. Back");
        System.out.println("---------------------");
        int chon = 0;
        System.out.print("Chon thao tac: ");
        chon = nhap.nextInt();
        return chon;
    }

    public int searchView() {
        System.out.println("------------------");
        System.out.println("1. Tong so diem");
        System.out.println("2. So bao danh");
        System.out.println("3. Ho va ten");
        System.out.println("4. Diem thanh phan");
        System.out.println("5. Back");
        System.out.println("------------------");
        int chon = 0;
        System.out.print("Chon thao tac: ");
        chon = nhap.nextInt();
        nhap = new Scanner(System.in);
        return chon;
    }

    public int sortView() {
        System.out.println("-------------------------");
        System.out.println("1. Sap xep so bao danh");
        System.out.println("2. Sap xep theo ten");
        System.out.println("3. Sap xep theo tong diem");
        System.out.println("4. Back");
        System.out.println("-------------------------");
        int chon = 0;
        System.out.print("Chon thao tac: ");
        chon = nhap.nextInt();
        return chon;
    }

    public void execOfAdd() {
        try {
            studentController.execOfGetElement("diem_thi_2016/");
        } catch (Exception e) {
            System.out.println("Lay du lieu bi loi!");
        }
        execOfDisplay();
    }

    public void execOfUpdate(){
        int chon = 0;
        do {
            List<Student> studentList = new ArrayList<Student>();
            Student student = new Student();
            chon = updateView();
            switch (chon){
                case 1:{
                    System.out.println("Nhập sô báo danh cần sửa thông tin: ");
                    String soBaoDanh = nhap.next();
                    student.setId(soBaoDanh);
                    studentList = studentController.execOfFindElement(student, 2);
                    if(studentList.isEmpty()){
                        System.out.println("Khong ton tai sinh vien nay");
                    }else{
                        boolean check = true;
                        student = updateInfo(studentList.get(0));
                        try {
                            studentController.execOfUpdateElement(student);
                        } catch (Exception e) {
                            check = false;
                            e.printStackTrace();
                        }
                        if (check)
                            System.out.println("Sua thông tin thành công!!!");
                    }
                    break;
                }
                case 2:{
                    System.out.println("Nhập sô báo danh cần xóa: ");
                    student.setId(nhap.next());
                    studentList = studentController.execOfFindElement(student, 2);
                    if(studentList.isEmpty())
                        System.out.println("Khong ton tai sinh vien nay");
                    else{
                        boolean check = true;
                        try {
                            studentController.execOfDeleteElement(studentList.get(0));
                        } catch (Exception e) {
                            check = false;
                            e.printStackTrace();
                        }
                        if(check)
                            System.out.println("Xoa thông tin thành công!!!");
                    }

                    break;
                }
            }
        }while(chon < 3);
    }

    public void execOfSearch(){
        nhap = new Scanner(System.in);
        List<Student> studentList = new ArrayList<Student>();
        Student student = new Student();
        int chon = 0;
        do{
            chon = searchView();
            switch (chon){
                case 1:{
                    System.out.println("Nhap tong diem cua sinh vien: ");
                    double tongDiem = nhap.nextDouble();
                    student.setTotalMark(tongDiem);
                    break;
                }
                case 2:{
                    System.out.println("Nhap so bao danh can tim: ");
                    String soBD = nhap.next();
                    student.setId(soBD);
                    break;
                }
                case 3:{
                    System.out.println("Nhap ho ten can tim: ");
                    String hoTen = nhap.nextLine();
                    student.setName(hoTen);
                    break;
                }
                case 4:{
                    System.out.println("Nhap diem thanh phan can tim; ");
                    double diemTP = nhap.nextDouble();
                    student.setMark(new double[]{diemTP, 0.0, 0.0});
                    break;
                }
            }
            studentList = studentController.execOfFindElement(student, chon);
            if(chon<5){
                System.out.println("Danh sach ket qua tim kiem: ");
                for (Student std :
                        studentList) {
                    displayInfo(std);
                }
                System.out.println("Ban co muon ghi kết quả ra file ko: (y/n)");
                System.out.println("0. Co");
                System.out.println("1. Khong");
                int ghiFile;
                boolean check = true;
                do {
                    ghiFile = nhap.nextInt();
                    if(ghiFile==0){
                        try {
                            studentController.execOfWriteResult(studentList);
                        } catch (Exception e) {
                            check = false;
                            e.printStackTrace();
                        }
                    }
                }while (ghiFile>1);
                if(check)
                    System.out.println("Ghi kết quả xuống file thành công");
            }
        }while(chon<5);
    }

    public void execOfSort(){
        int chon = 0;
        List<Student> list = new ArrayList<Student>();
        do {
            chon = sortView();
            list = studentController.execOfSortElement(chon);
            System.out.println("Danh sach sinh vien sau khi sap xep");
            for (Student std :
                    list) {
                displayInfo(std);
            }
        }while (chon<4);
    }

    public void execOfStatistic(){
        studentController.execOfStatistic();
    }

    public void execOfDisplay(){
        try {
            List<Student> list = studentController.execOfGetLastestData();
            for (Student std :
                    list) {
                displayInfo(std);
            }
            list.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayInfo(Student std){
        System.out.print(" "+std.getId()+"       ");
        System.out.print(" "+std.getName()+"      ");
        System.out.print(" "+std.getAddress()+"       ");
        System.out.print(" "+(std.isGender() ? "Nam":"Nu")+"      ");
        double[] mark = std.getMark();
        System.out.printf(" %f %f %f        ", mark[0], mark[1], mark[2]);
        System.out.println("");
    }

    public Student inputInfo(){
        Student std = new Student();
        System.out.print("Nhap so bao danh: ");
        std.setId(nhap.nextLine());
        System.out.print("Nhap ten thi sinh: ");
        std.setName(nhap.nextLine());
        System.out.print("Nhap dia chi thi sinh: ");
        std.setAddress(nhap.nextLine());
        System.out.println("Chon gioi tinh thi sinh: ");
        System.out.println("0. Nam\n1. Nu");
        int gioiTinh;
        do {
            System.out.print("Chon: ");
            gioiTinh = nhap.nextInt();
            if(gioiTinh != 0 && gioiTinh != 1){
                System.out.println("Chon sai!!!");
            }else{
                std.setGender(gioiTinh==0 ? true:false);
            }
        }while (gioiTinh != 0 && gioiTinh != 1);

        System.out.println("Nhap diem Toan - Ly - Hoa: ");
        double[] diem = new double[3];
        for (int j = 0; j < 3; j++) {
            diem[j] = nhap.nextDouble();
        }
        std.setMark(diem);
        return std;
    }

    public Student updateInfo(Student student){
        System.out.println("Sua thong tin thi sinh: ");
        System.out.println("So bao danh: "+student.getId());
        System.out.print("Sua ten thi sinh "+student.getName()+": ");
        nhap = new Scanner(System.in);
        student.setName(nhap.nextLine());
        System.out.print("Sua dia chi thi sinh: "+student.getAddress()+": ");
        student.setAddress(nhap.nextLine());
        System.out.println("Sua gioi tinh thi sinh: ");
        System.out.println("0. Nam\n1. Nu");
        int gioiTinh;
        do {
            System.out.print("Chon: ");
            gioiTinh = nhap.nextInt();
            if(gioiTinh != 0 && gioiTinh != 1){
                System.out.println("Chon sai!!!");
            }else{
                student.setGender(gioiTinh==0 ? true:false);
            }
        }while (gioiTinh != 0 && gioiTinh != 1);
        System.out.println("Sua diem Toan - Ly - Hoa: ");
        double[] diem = new double[3];
        for (int j = 0; j < 3; j++) {
            diem[j] = nhap.nextDouble();
        }
        student.setMark(diem);
        student.setStateChange(true);
        return student;
    }
}
