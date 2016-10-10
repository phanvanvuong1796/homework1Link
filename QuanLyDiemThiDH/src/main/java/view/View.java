package view;

import controller.ThiSinhController;
import entity.ThiSinh;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by bongm on 2016-09-19.
 */
public class View {
    private Scanner nhap;
    private ThiSinhController thiSinhController;

    public View(Scanner nhap) {
        this.nhap = nhap;
        thiSinhController = new ThiSinhController();
    }

    public void mainView() {
        System.out.println("1. Nhap du lieu sinh vien");
        System.out.println("2. Them, sua, xoa sinh vien");
        System.out.println("3. Tim Kiem sinh vien");
        System.out.println("4. Sap xep sinh vien");
        System.out.println("5. Thong ke");
        System.out.println("6. Hien thi danh sach");
        System.out.println("7. Thoat");
    }

    public int addView() {
        System.out.println("1. Nhap tu ban phim");
        System.out.println("2. Nhap tu file");
        System.out.println("3. Back");
        int chon = 0;
        System.out.print("Chon thao tac: ");
        chon = nhap.nextInt();
        return chon;
    }

    public int editView() {
        System.out.println("1. Them sinh vien");
        System.out.println("2. Sua sinh vien");
        System.out.println("3. Xoa sinh vien");
        System.out.println("4. Back");
        int chon = 0;
        System.out.print("Chon thao tac: ");
        chon = nhap.nextInt();
        return chon;
    }

    public int searchView() {
        System.out.println("1. Tong so diem");
        System.out.println("2. So bao danh");
        System.out.println("3. Ho va ten");
        System.out.println("4. Diem thanh phan");
        System.out.println("5. Back");
        int chon = 0;
        System.out.print("Chon thao tac: ");
        chon = nhap.nextInt();
        nhap = new Scanner(System.in);
        return chon;
    }

    public int sortView() {
        System.out.println("1. Sap xep so bao danh");
        System.out.println("2. Sap xep theo ten");
        System.out.println("3. Sap xep theo tong diem");
        System.out.println("4. Back");
        int chon = 0;
        System.out.print("Chon thao tac: ");
        chon = nhap.nextInt();
        return chon;
    }

    public void execOfAdd() {
        int chon = 0;
        List<ThiSinh> thiSinhList = null;
        do{
            chon = addView();
            switch (chon){
                case 1:{
                    thiSinhList = new ArrayList<ThiSinh>();
                    System.out.print("Nhap so luong sinh vien: ");
                    int n = nhap.nextInt();
                    ThiSinh thiSinh;
                    boolean check = true;
                    for (int i = 0; i < n; i++) {
                        thiSinh = new ThiSinh();
                        thiSinh.input();
                        thiSinhList.add(thiSinh);
                        try {
                            thiSinhController.execOfInsertElement(thiSinh);
                        } catch (Exception e) {
                            check = false;
                            e.printStackTrace();
                        }
                    }
                    if(check)
                        System.out.println("Ghi danh sách thành công!!!");
                    break;
                }
                case 2:{
                    thiSinhList = new ArrayList<ThiSinh>();
                    try {
                        thiSinhList = thiSinhController.execOfGetElement();
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                    if(thiSinhList.size()!=0)
                        System.out.println("Đọc danh sách từ file thành công!!!");
                    break;
                }
            }
            System.out.println("Danh sách sinh viên: \n");
            for (ThiSinh std :
                    thiSinhList) {
                std.display();
            }
        }while(chon < 3);
    }

    public void execOfEdit(){
        int chon = 0;
        do {
            chon = editView();
            switch (chon){
                case 1:{
                    ThiSinh thiSinh = new ThiSinh();
                    thiSinh.input();
                    boolean check = true;
                    try {
                        thiSinhController.execOfInsertElement(thiSinh);
                    } catch (Exception e) {
                        check = false;
                        e.printStackTrace();
                    }
                    if(check)
                        System.out.println("Thêm sinh viên thành công");
                    break;
                }
                case 2:{
                    ThiSinh thiSinh = new ThiSinh();
                    System.out.println("Nhập sô báo danh cần sửa thông tin: ");
                    String soBaoDanh = nhap.next();
                    thiSinh.setSoBaoDanh(soBaoDanh);
                    boolean check = true;
                    try {
                        thiSinhController.execOfUpdateElement(thiSinh);
                    } catch (Exception e) {
                        check = false;
                        e.printStackTrace();
                    }
                    if (check)
                        System.out.println("Sua thông tin thành công!!!");
                    break;
                }
                case 3:{
                    ThiSinh thiSinh = new ThiSinh();
                    System.out.println("Nhập sô báo danh cần xóa: ");
                    thiSinh.setSoBaoDanh(nhap.next());
                    boolean check = true;
                    try {
                        thiSinhController.execOfDeleteElement(thiSinh);
                    } catch (Exception e) {
                        check = false;
                        e.printStackTrace();
                    }
                    if(check)
                        System.out.println("Xoa thông tin thành công!!!");
                    break;
                }
            }
        }while(chon < 4);
    }

    public void execOfSearch(){
        nhap = new Scanner(System.in);
        List<ThiSinh> thiSinhList = new ArrayList<ThiSinh>();
        ThiSinh thiSinh = null;
        int chon = 0;
        do{
            chon = searchView();
            switch (chon){
                case 1:{
                    System.out.println("Nhap tong diem cua sinh vien: ");
                    double tongDiem = nhap.nextDouble();
                    thiSinh = new ThiSinh();
                    thiSinh.setTongDiem(tongDiem);
                    thiSinhList = thiSinhController.execOfFindElement(thiSinh, chon);
                    break;
                }
                case 2:{
                    System.out.println("Nhap so bao danh can tim: ");
                    String soBD = nhap.next();
                    thiSinh = new ThiSinh();
                    thiSinh.setSoBaoDanh(soBD);
                    thiSinhList = thiSinhController.execOfFindElement(thiSinh, chon);
                    break;
                }
                case 3:{
                    System.out.println("Nhap ho ten can tim: ");
                    String hoTen = nhap.nextLine();
                    thiSinh = new ThiSinh();
                    thiSinh.setHoVaTen(hoTen);
                    thiSinhList = thiSinhController.execOfFindElement(thiSinh, chon);
                    break;
                }
                case 4:{
                    System.out.println("Nhap diem thanh phan can tim; ");
                    double diemTP = nhap.nextDouble();
                    thiSinh = new ThiSinh();
                    thiSinh.setDiem(new double[]{diemTP, 0.0, 0.0});
                    thiSinhList = thiSinhController.execOfFindElement(thiSinh, chon);
                    break;
                }
            }
            if(chon<5){
                System.out.println("Danh sach ket qua tim kiem: ");
                for (ThiSinh std :
                        thiSinhList) {
                    std.display();
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
                            thiSinhController.execOfWriteResult(thiSinhList);
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
        List<ThiSinh> list = new ArrayList<ThiSinh>();
        do {
            chon = sortView();
            list = thiSinhController.execOfSortElement(chon);
            System.out.println("Danh sach sinh vien sau khi sap xep");
            for (ThiSinh std :
                    list) {
                std.display();
            }
        }while (chon<4);
    }

    public void execOfStatistic(){
        thiSinhController.execOfStatistic();
    }

    public void execOfDisplay(){
        try {
            List<ThiSinh> list = thiSinhController.execOfGetElement();
            for (ThiSinh std :
                    list) {
                std.display();
            }
            list.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
