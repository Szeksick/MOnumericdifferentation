package sample.methods;

import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.Collections;



public class Backwardsdiff {
    private Double x0;
    private ArrayList<Double> tabx, taby, templist;
    private ArrayList<ArrayList<Double>> subresult;
    private Double h, result, sum,tempresult;
    private int n, selected;

    public Backwardsdiff(ArrayList<Double> tabx, ArrayList<Double> taby, Double x0) {
        this.tabx = tabx;
        this.taby = taby;
        this.x0 = x0;
        init();
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
    private double calcsubresult(){
        Collections.reverse(this.tabx);
        Collections.reverse(this.taby);
        System.out.println("h:"+h+"\n"+"n"+n+"\n"+"taby:"+taby+"\n"+"tabx:"+tabx);
        ArrayList<ArrayList<Double>> subresult = new ArrayList<>();
        for(int i = 0; i < n-1 ; i++){
            subresult.add(new ArrayList<>());
            if(i==0){
                for(int j = 0; j < n-1-i; j++){
                    subresult.get(i).add(taby.get(j) - taby.get(j+1));
                    System.out.println(taby.get(j + 1)+" - " +taby.get(j)+" = "+subresult.get(i));
                }
            }else{
                for(int j = 0; j < n-1-i ; j++) {
                    subresult.get(i).add(subresult.get(i - 1).get(j) - subresult.get(i - 1).get(j+1));
                }
            }
            System.out.println("subresult["+i+"]="+subresult.get(i));
        }
        System.out.println("subresult size: "+String.valueOf(subresult.size()));
        selected = tabx.indexOf(x0);
        templist = new ArrayList<Double>();
        for(int i = 0; i < subresult.size()-1; i++){
            if(subresult.get(i).size() > selected) {
                templist.add(subresult.get(i).get(selected));
            }
        }
        System.out.println("Templist size: "+templist.size());
        System.out.println("Templist: "+String.valueOf(templist));
        tempresult = 0.0;

        for(int i=0; i < templist.size();i++){
            if(i==0){
                tempresult = tempresult + (templist.get(i));
            }else{
                    tempresult = tempresult + ((1.0 / (i + 1.0)) * templist.get(i));
                 }
            }
        return tempresult;
    }
    public double calculate(){
        init();
        result = (1/h)*calcsubresult();
        return result;
    }
}
