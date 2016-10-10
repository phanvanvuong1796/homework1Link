package models;

import entity.ThiSinh;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by bongm on 2016-09-23.
 */
public class ThiSinhModel extends BaseModel<ThiSinh> {

    public ThiSinhModel() {
        super();
    }

    public List<ThiSinh> getElement() throws Exception {

        File file = new File("SinhVien.json");

        try {

            list = objectMapper.readValue(file, new TypeReference<List<ThiSinh>>() {

            });
        } catch (Exception e) {
            throw e;
        }

        return list;
    }

    public boolean insertElement(ThiSinh thiSinh) throws Exception {
        boolean result = false;
        File file = new File("SinhVien.json");

        try {
            list.add(thiSinh);
            objectMapper.writeValue(file, list);
            result = true;
        } catch (Exception e) {
            throw e;
        }

        return result;
    }

    public boolean updateElement(ThiSinh thiSinh) throws Exception {
        boolean result = false;
        File file = new File("SinhVien.json");
        int i = 0;
        for (i = 0; i < list.size(); i++) {
            if (list.get(i).getSoBaoDanh().equals(thiSinh.getSoBaoDanh())) {
                thiSinh = list.get(i);
                break;
            }
        }
        thiSinh.edit();
        list.add(i, thiSinh);
        list.remove(i + 1);

        try {
            objectMapper.writeValue(file, list);
            result = true;
        } catch (Exception e) {
            throw e;
        }

        return result;
    }

    public boolean deleteElement(ThiSinh thiSinh) throws Exception {
        boolean result = false;
        File file = new File("SinhVien.json");
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getSoBaoDanh().equals(thiSinh.getSoBaoDanh())){
                list.remove(i);
                break;
            }
        }

        try {
            objectMapper.writeValue(file, list);
        } catch (Exception e) {
            throw e;
        }

        return result;
    }


    /***
     * Tìm kiếm thí sinh
     * @param thiSinh
     * @param option 1: tìm kiếm theo tổng điểm
     *               2: Tìm kiếm theo số báo danh
     *               3: Tìm kiếm theo Họ và tên
     *               4: Tìm kiếm theo điểm thành phần
     * @return list kết quả tìm kiếm
     */
    public List<ThiSinh> findElement(ThiSinh thiSinh, int option) {
        List<ThiSinh> listKetQua = new ArrayList<ThiSinh>();

        if (option == 1) {
            for (ThiSinh cThiSinh : list) {
                if (cThiSinh.getTongDiem() == thiSinh.getTongDiem()) {
                    listKetQua.add(cThiSinh);
                }
            }
        } else if (option == 2) {
            for (ThiSinh cThiSinh : list) {
                if (cThiSinh.getSoBaoDanh().equals(thiSinh.getSoBaoDanh())) {
                    listKetQua.add(cThiSinh);
                }
            }
        } else if (option == 3) {
            for (ThiSinh cThiSinh : list) {
                if (cThiSinh.getHoVaTen().equals(thiSinh.getHoVaTen())) {
                    listKetQua.add(cThiSinh);

                }
            }
        } else if (option == 4) {
            for (ThiSinh cThiSinh : list) {
                for (double cdiem : cThiSinh.getDiem()) {
                    if (thiSinh.getDiem()[0] == cdiem) {
                        listKetQua.add(cThiSinh);
                        break;
                    }
                }
            }
        }
        return listKetQua;
    }

    public void statistic() {
        int[] thongke = new int[]{0, 0, 0, 0};
        System.out.println("Tong so thi sinh du thi: " + list.size());
        for (ThiSinh std : list) {
            if (std.getTongDiem() < 15)
                thongke[0]++;
            else if (std.getTongDiem() >= 15 && std.getTongDiem() < 20)
                thongke[1]++;
            else if (std.getTongDiem() >= 20 && std.getTongDiem() < 25)
                thongke[2]++;
            else
                thongke[3]++;
        }
        int mucDiem = 0;
        for (int tongSV : thongke) {
            if (mucDiem == 0) {
                System.out.println("Muc diem tu 0 den 15: " + tongSV + " thi sinh");
                mucDiem += 15;
            } else {
                System.out.println("Muc diem tu " + mucDiem + " den " + (mucDiem + 5) + ": " + tongSV + "thi sinh");
                mucDiem += 5;
            }
        }
    }


    /***
     * Sắp xếp theo số báo danh, họ tên, tổng điểm
     * @param option 0-sắp xếp theo số báo danh, 1-theo Anphabe, 2-theo tổng điểm
     * @return danh sách kết quả
     */
    public List<ThiSinh> sortElement(int option) {
        if (option == 1) {
            Collections.sort(list, new Comparator<ThiSinh>() {
                public int compare(ThiSinh o1, ThiSinh o2) {
                    return o1.getSoBaoDanh().compareTo(o2.getSoBaoDanh());
                }
            });
        } else if (option == 2) {
            Collections.sort(list, new Comparator<ThiSinh>() {
                public int compare(ThiSinh o1, ThiSinh o2) {
                    return o1.reverName().compareTo(o2.reverName());
                }
            });
        } else {
            Collections.sort(list, new Comparator<ThiSinh>() {
                public int compare(ThiSinh o1, ThiSinh o2) {
                    if (o1.getTongDiem() < o2.getTongDiem())
                        return 1;
                    else if (o1.getTongDiem() == o2.getTongDiem())
                        return 0;
                    else
                        return -1;
                }
            });
        }
        return list;
    }

    public boolean writeResult(List<ThiSinh> list) throws Exception {
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


}
