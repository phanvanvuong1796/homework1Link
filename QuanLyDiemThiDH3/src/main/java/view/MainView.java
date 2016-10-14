package view;


import java.util.Scanner;

/**
 * Created by phanvuong on 10/12/16.
 */
public class MainView {
    public static void main(String[] args) {
        Scanner nhap = new Scanner(System.in);
        View view = new View(nhap);
        int chon = 0;
        do {

            view.mainView();
            System.out.print("Chon chuc nang: ");
            chon = nhap.nextInt();
            switch (chon){
                case 1:{
                    view.execOfAdd();
                    break;
                }
                case 2:{
                    view.execOfUpdate();
                    break;
                }
                case 3:{
                    view.execOfSearch();
                    break;
                }
                case 4:{
                    view.execOfSort();
                    break;
                }
                case 5:{
                    view.execOfStatistic();
                    break;
                }
                case 6:{
                    view.execOfDisplay();
                }
            }
        } while (chon < 7);
    }
}
