package sample.methods;

import javafx.scene.control.Alert;
import java.util.ArrayList;
import java.util.Collections;


public class Backwardsdiff {
    private ArrayList<Double> tabx;
    private ArrayList<Double> taby;
    private Double h;
    private int n;

    public Backwardsdiff(ArrayList<Double> tabx, ArrayList<Double> taby) {
        this.tabx = tabx;
        this.taby = taby;
    }
    private void init(){
        try{
            this.h = this.tabx.get(1)- this.tabx.get(0);
            this.n = this.tabx.size();
        }catch(Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Błąd metody prostokatów z niedomiarem");
            alert.setHeaderText(null);
            alert.setContentText("Mniej niż 2 punkty pomiarowe");
            alert.showAndWait();
        }
    }
    private double calcsubresult(Double x0){
        int selected = tabx.indexOf(x0);
        Collections.reverse(tabx);
        Collections.reverse(taby);
        ArrayList<ArrayList<Double>> subresult = new ArrayList<>();
        for(int i = 0; i < n-1 ; i++){
            subresult.add(new ArrayList<>());
            if(i==0){
                for(int j = 0; j < n-1-i; j++){
                    subresult.get(i).add(taby.get(j) - taby.get(j+1));
                }
            }else{
                for(int j = 0; j < n-1-i ; j++) {
                    subresult.get(i).add(subresult.get(i - 1).get(j) - subresult.get(i - 1).get(j+1));
                }
            }

        }
        ArrayList<Double> templist = new ArrayList<>();
        for(int i = 0; i < subresult.size()-1; i++){
            if(subresult.get(i).size() > selected) {
                templist.add(subresult.get(i).get(selected));
            }
        }
        Double tempresult = 0.0;

        for(int i = 0; i < templist.size(); i++){
            if(i==0){
                tempresult = tempresult + (templist.get(i));
            }else{
                    tempresult = tempresult + ((1.0 / (i + 1.0)) * templist.get(i));
                 }
            }
        Collections.reverse(tabx);
        Collections.reverse(taby);
        return tempresult;
    }
    public double calculate(Double x){
        init();
        return (1 / h) * calcsubresult(x);
    }
}
