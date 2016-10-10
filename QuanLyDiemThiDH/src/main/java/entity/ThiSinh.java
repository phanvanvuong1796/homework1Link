package entity;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Created by bongm on 2016-09-16.
 */
public class ThiSinh implements Serializable{
    private String SoBaoDanh;
    private String hoVaTen;
    private String diaChi;
    private boolean gioiTinh;
    private double[] diem;
    private double tongDiem;


    public ThiSinh() {
        diem = new double[3];
    }


    public ThiSinh(String soBaoDanh, String hoVaTen, String diaChi, boolean gioiTinh, double[] diem) {
        this.SoBaoDanh = soBaoDanh;
        this.hoVaTen = hoVaTen;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.diem = diem;
        this.tongDiem = diem[0]+diem[1]+diem[2];
    }

    public String getSoBaoDanh() {
        return SoBaoDanh;
    }

    public void setSoBaoDanh(String soBaoDanh) {
        SoBaoDanh = soBaoDanh;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public double[] getDiem() {
        return diem;
    }

    public void setDiem(double[] diem) {
        this.diem = diem;
        this.tongDiem = diem[0]+diem[1]+diem[2];
    }

    public void setTongDiem(double tongDiem) {
        this.tongDiem = tongDiem;
    }

    public double getTongDiem(){
        return tongDiem;
    }

    public void input(){
        Scanner nhap = new Scanner(System.in);
        System.out.print("Nhap so bao danh: ");
        setSoBaoDanh(nhap.nextLine());
        System.out.print("Nhap ten thi sinh: ");
        setHoVaTen(nhap.nextLine());
        System.out.print("Nhap dia chi thi sinh: ");
        setDiaChi(nhap.nextLine());
        System.out.println("Chon gioi tinh thi sinh: ");
        System.out.println("0. Nam\n1. Nu");
        int gioiTinh;
        do {
            System.out.print("Chon: ");
            gioiTinh = nhap.nextInt();
            if(gioiTinh != 0 && gioiTinh != 1){
                System.out.println("Chon sai!!!");
            }else{
                setGioiTinh(gioiTinh==0 ? true:false);
            }
        }while (gioiTinh != 0 && gioiTinh != 1);

        System.out.println("Nhap diem Toan - Ly - Hoa: ");
        for (int j = 0; j < 3; j++) {
            diem[j] = nhap.nextDouble();
        }
        setDiem(diem);
    }

    public void display(){
        System.out.println("So bao danh: "+getSoBaoDanh());
        System.out.println("Ho va ten: "+getHoVaTen());
        System.out.println("Dia chi: "+getDiaChi());
        System.out.println("Gioi tinh: "+(isGioiTinh() ? "Nam":"Nu"));
        System.out.printf("Diem Toan - Ly - Hoa: %f %f %f", diem[0], diem[1], diem[2]);
        System.out.println("");
    }

    public void edit(){
        System.out.println("Sua thong tin thi sinh: ");
        Scanner nhap = new Scanner(System.in);
        System.out.println("So bao danh: "+getSoBaoDanh());
        System.out.print("Sua ten thi sinh "+getHoVaTen()+": ");
        setHoVaTen(nhap.nextLine());
        System.out.print("Sua dia chi thi sinh: "+getDiaChi()+": ");
        setDiaChi(nhap.nextLine());
        System.out.println("Sua gioi tinh thi sinh: ");
        System.out.println("0. Nam\n1. Nu");
        int gioiTinh;
        do {
            System.out.print("Chon: ");
            gioiTinh = nhap.nextInt();
            if(gioiTinh != 0 && gioiTinh != 1){
                System.out.println("Chon sai!!!");
            }else{
                setGioiTinh(gioiTinh==0 ? true:false);
            }
        }while (gioiTinh != 0 && gioiTinh != 1);
        System.out.println("Sua diem Toan - Ly - Hoa: ");
        for (int j = 0; j < 3; j++) {
            diem[j] = nhap.nextDouble();
        }
        setDiem(diem);
    }

    public String reverName(){
        String[] arrayName = getHoVaTen().split(" ");
        String reverName = "";
        for (int i = arrayName.length-1 ; i>=0; i--){
            reverName += arrayName[i];
        }
        return reverName;
    }
}

