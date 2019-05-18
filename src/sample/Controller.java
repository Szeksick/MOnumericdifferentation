package sample;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.methods.Simplediff;
import sample.methods.Backwardsdiff;
import sample.methods.Stirling;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Controller {
@FXML private TextArea resultout;
@FXML private TextField x0;
@FXML private Label filename;
@FXML private AnchorPane ap;
private ArrayList<Double> tabx;
private ArrayList<Double> taby;
private Double x;
private File selectedFile;


    public void init() {
        Stage stage = (Stage) ap.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Błąd kalkulatora");
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrano pliku");
            alert.showAndWait();
        } else {
            try {
                filename.setText(selectedFile.getName());
                ArrayList<Double> templist = new ArrayList<>();
                try (Scanner scanner = new Scanner(selectedFile)) {
                        while (scanner.hasNext())
                            templist.add(Double.parseDouble(scanner.next()));
                    } catch (FileNotFoundException fileexept) {
                        fileexept.printStackTrace();
                }
                filename.setText("Wybrany plik: "+selectedFile.getName());
                tabx = new ArrayList<>(templist.subList(0, (templist.size()/2)));
                taby = new ArrayList<>(templist.subList(templist.size()/2, templist.size()));
                templist.clear();
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
        public void calculate() {
          try {
              if (x0.getText().isEmpty()) throw new Exception();
              x = Double.parseDouble(x0.getText());
          }catch(Exception e){
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setTitle("Błąd kalkulatora");
                  alert.setHeaderText(null);
                  alert.setContentText("Nie podałeś x0");
                  alert.showAndWait();
          }
            if (selectedFile == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Błąd kalkulatora");
                alert.setHeaderText(null);
                alert.setContentText("Nie wybrano pliku");
                alert.showAndWait();
            } else {
                Simplediff simplediff = new Simplediff(tabx, taby, x);
                Backwardsdiff backwardsdiff = new Backwardsdiff(tabx, taby, x);
                Stirling stirling = new Stirling(x, tabx, taby);
                resultout.appendText("<==== Obliczenia dla x0 ="+x+" ====>\n");
                resultout.appendText("Metoda różnicy zwykłej: " + String.valueOf(simplediff.calculate()) + "\n");
                resultout.appendText("Metoda różnicy wstecznej: " + String.valueOf(backwardsdiff.calculate()) + "\n");
                resultout.appendText("Metoda różnic centralnych: " + String.valueOf(stirling.calculate()) + "\n");
            }
         }
    }



