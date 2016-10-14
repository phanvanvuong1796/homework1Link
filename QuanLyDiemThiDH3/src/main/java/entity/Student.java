package entity;

/**
 * Created by phanvuong on 10/10/16.
 */
public class Student {
    private String id;
    private String name;
    private String address;
    private boolean gender;
    private double[] mark;
    private double totalMark;
    private String srcFile;
    private boolean stateChange;

    public Student() {
        mark = new double[3];
        stateChange = false;
    }

    public Student(String id, String name, String address, boolean gender, double[] mark, String srcFile) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.mark = mark;
        this.totalMark = mark[0] + mark[1] + mark[2];
        this.srcFile = srcFile;
        this.stateChange = false;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public double[] getMark() {
        return mark;
    }

    public void setMark(double[] mark) {
        this.mark = mark;
        this.totalMark = mark[0] + mark[1] + mark[2];
    }

    public double getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(double totalMark) {
        this.totalMark = totalMark;
    }

    public String reverName(){
        String[] arrayName = getName().split(" ");
        String reverName = "";
        for (int i = arrayName.length-1 ; i>=0; i--){
            reverName += arrayName[i];
        }
        return reverName;
    }

    public String getSrcFile() {
        return srcFile;
    }

    public void setSrcFile(String srcFile) {
        this.srcFile = srcFile;
    }

    public boolean isStateChange() {
        return stateChange;
    }

    public void setStateChange(boolean stateChange) {
        this.stateChange = stateChange;
    }
}
