package sample.methods;

import javafx.scene.control.Alert;

import java.util.ArrayList;

public class Stirling {
    private ArrayList<Double> tabx;
    private ArrayList<Double> taby;
    private Double h;
    private int n;

    public Stirling(ArrayList<Double> tabx, ArrayList<Double> taby) {
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
                    subresult.get(i).add(taby.get(j) - taby.get(j+1));
                }
            }else{
                for(int j = 0; j < n-1-i ; j++) {
                    subresult.get(i).add(subresult.get(i - 1).get(j) - subresult.get(i - 1).get(j+1));
                }
            }
        }
        ArrayList<Double> templist = new ArrayList<>();

        int iter = 0;
        for(int i = 0; i < subresult.size()-1; i++){
            if(i % 2 == 0) {
                templist.add(subresult.get(i).get((selected-iter)-1) + subresult.get(i).get(selected-iter));
                iter++;
            }
        }
        double tempresult = 0.0;
        for(int i = 0; i < templist.size(); i++){
            if(i==0){
                tempresult = tempresult + (templist.get(i)/2);
            }else{
                if (i % 2 == 0) {
                    tempresult = tempresult + (templist.get(i)/60);
                } else {
                    tempresult = tempresult - (templist.get(i)/12);
                }
            }
        }
        return -tempresult;
    }
    public double calculate(Double x){
        init();
        try {
            return (1 / h) * calcsubresult(x);
        }catch (Exception f){
            return 0.0;
        }
    }

}
