package sample.methods;

import javafx.scene.control.Alert;

import java.util.ArrayList;

public class Stirling {
    private Double x0;
    private ArrayList<Double> tabx, taby, templist;
    private ArrayList<ArrayList<Double>> subresult;
    private Double h, result, sum,tempresult;
    private int n, selected;

    public Stirling(Double x0, ArrayList<Double> tabx, ArrayList<Double> taby) {
        this.x0 = x0;
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
            alert.setTitle("Błąd metody różnicy zwykłej");
            alert.setHeaderText(null);
            alert.setContentText("Mniej niż 2 punkty pomiarowe");
            alert.showAndWait();
        }
    }

    private double calcsubresult(){
        ArrayList<ArrayList<Double>> subresult = new ArrayList<>();
        for(int i = 0; i < n-1 ; i++){
            subresult.add(new ArrayList<>());
            if(i==0){
                for(int j = 0; j < n-1-i; j++){
                    subresult.get(i).add(taby.get(j + 1) - taby.get(j));
                }
            }else{
                for(int j = 0; j < n-1-i ; j++) {
                    subresult.get(i).add(subresult.get(i - 1).get(j + 1) - subresult.get(i - 1).get(j));
                }
            }
        }
        int selected = tabx.indexOf(x0);
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
                if (i % 2 == 0) {
                    tempresult = tempresult + ((1.0 / (i + 1.0)) * templist.get(i));
                } else {
                    tempresult = tempresult - ((1.0 / (i + 1.0)) * templist.get(i));
                }
            }
        }
        return tempresult;
    }
    public double calculate(){
        init();
        return (1 / h) * calcsubresult();
    }

}
