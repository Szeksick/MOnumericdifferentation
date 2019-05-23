package sample.methods;

import javafx.scene.control.Alert;

import java.util.ArrayList;

public class Stirling {
    private Double x0;
    private ArrayList<Double> tabx;
    private ArrayList<Double> taby;
    private Double h;
    private int n;

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
        System.out.println("h:"+h+"\n"+"n"+n+"\n"+"taby:"+taby+"\n"+"tabx:"+tabx);
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
            System.out.println("subresult["+i+"]="+subresult.get(i));
        }
        System.out.println("subresult size: "+String.valueOf(subresult.size()));
        int selected = tabx.indexOf(x0);
        ArrayList<Double> templist = new ArrayList<>();

        int iter = 0;
        for(int i = 0; i < subresult.size()-1; i++){
            if(i % 2 == 0) {
                templist.add(subresult.get(i).get((selected-iter)-1) + subresult.get(i).get(selected-iter));
                iter++;
            }
        }
        Double tempresult = 0.0;
        System.out.println("Templist size: "+ templist.size());
        System.out.println("Templist: "+String.valueOf(templist));
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
    public double calculate(){
        init();
        return (1 / h) * calcsubresult();
    }

}
