package sample.methods;

import javafx.scene.control.Alert;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class RectExc {
    private ArrayList<Double> tabx, taby, templist;
    private Double h, result, sum;
    int n;

    public RectExc(ArrayList<Double> tabx, ArrayList<Double> taby) {
        this.tabx = tabx;
        this.taby = taby;

    }
    private void init(){
        try{
            this.h = this.tabx.get(1)- this.tabx.get(0);
            this.n = this.tabx.size();
            this.sum = 0.0;
        }catch(Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Błąd metody prostokatów z niedomiarem");
            alert.setHeaderText(null);
            alert.setContentText("Mniej niż 2 punkty pomiarowe");
            alert.showAndWait();
        }
    }
    public double calculate() {
        init();
        for (int i = 1; i < n; i++){
            sum = sum+taby.get(i);
        }
        result = h*sum;
        return result;
    }
}
