package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.methods.RectExc;
import sample.methods.RectIns;
import sample.methods.Simpson;
import sample.methods.Trapeze;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Controller {
@FXML private TextArea resultout;
@FXML private Label filename;
@FXML private AnchorPane ap;
private ArrayList<Double> tabx, taby, templist;
private ArrayList<ArrayList<Double>> subresult;
private Double h;
int n;


    public void calculate(ActionEvent e) {
        Stage stage = (Stage) ap.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        filename.setText(selectedFile.getName());
        if (selectedFile == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Błąd kalkulatora");
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrano pliku");
            alert.showAndWait();
        } else {
            try {
                templist = new ArrayList<Double>();
                try (Scanner scanner = new Scanner(selectedFile)) {
                        while (scanner.hasNext())
                            templist.add(Double.parseDouble(scanner.next()));
                    } catch (FileNotFoundException fileexept) {
                        fileexept.printStackTrace();
                }
                filename.setText("Wybrany plik: "+selectedFile.getName());
                tabx = new ArrayList<Double>(templist.subList(0, (templist.size()/2)));
                taby = new ArrayList<Double>(templist.subList(templist.size()/2,templist.size()));
                templist.clear();
                RectExc rectExc = new RectExc(tabx, taby);
                RectIns rectIns = new RectIns(tabx, taby);
                Simpson simpson = new Simpson(tabx, taby);
                Trapeze trapeze = new Trapeze(tabx, taby);

                resultout.setText("Metoda prostokątów z niedomiarem: "+String.valueOf(rectIns.calculate())+"\n");
                resultout.appendText("Metoda prostokątów z nadmiarem: "+String.valueOf(rectExc.calculate())+"\n");
                resultout.appendText("Metoda trapezów: "+String.valueOf(trapeze.calculate())+"\n");
                resultout.appendText("Metoda Simpsona: "+String.valueOf(simpson.calculate())+"\n");
            } catch (Exception exept) {
                exept.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Błąd kalkulatora");
                alert.setHeaderText(null);
                alert.setContentText("Niewłaściwy plik");
                alert.showAndWait();
                }
            }
        }
    }



