package controller;

import entity.ThiSinh;
import models.ThiSinhModel;

import java.util.List;

/**
 * Created by bongm on 2016-09-23.
 */
public class ThiSinhController extends BaseController<ThiSinh> {


    private ThiSinhModel thiSinhModel;

    public ThiSinhController() {
        thiSinhModel = new ThiSinhModel();
    }

    public List<ThiSinh> execOfGetElement() throws Exception {
        List<ThiSinh> list;
        try{
            list = thiSinhModel.getElement();
        }catch (Exception e){
            throw e;
        }
        return list;
    }

    public boolean execOfInsertElement(ThiSinh thiSinh) throws Exception {
        boolean result = false;
        try{
            thiSinhModel.insertElement(thiSinh);
            result = true;
        }catch (Exception e){
            throw e;
        }
        return result;
    }

    public boolean execOfUpdateElement(ThiSinh thiSinh) throws Exception {
        boolean result = false;
        try{
            thiSinhModel.updateElement(thiSinh);
            result = true;
        }catch (Exception e){
            throw e;
        }
        return result;
    }

    public boolean execOfDeleteElement(ThiSinh thiSinh) throws Exception {
        boolean result = false;
        try{
            thiSinhModel.deleteElement(thiSinh);
            result = true;
        }catch (Exception e){
            throw e;
        }
        return result;
    }

    public List<ThiSinh> execOfFindElement(ThiSinh thiSinh, int option) {
        return thiSinhModel.findElement(thiSinh, option);
    }

    public void execOfStatistic() {
        thiSinhModel.statistic();
    }

    public List<ThiSinh> execOfSortElement(int option) {
        return thiSinhModel.sortElement(option);
    }

    public boolean execOfWriteResult(List<ThiSinh> list) throws Exception {
        boolean result = false;
        result = thiSinhModel.writeResult(list);
        return false;
    }

}
