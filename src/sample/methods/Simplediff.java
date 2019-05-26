package sample.methods;

import javafx.scene.control.Alert;
import java.util.ArrayList;


public class Simplediff {
    private ArrayList<Double> tabx;
    private ArrayList<Double> taby;
    private Double h;
    private int n;

    public Simplediff(ArrayList<Double> tabx, ArrayList<Double> taby) {
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

    private double calcsubresult(Double x0){
        int selected = tabx.indexOf(x0);
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
    public double calculate(Double x){
        init();
        return (1 / h) * calcsubresult(x);
    }
}
