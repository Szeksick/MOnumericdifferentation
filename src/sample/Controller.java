package sample;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
@FXML private Label filename;
@FXML private AnchorPane ap;
private ArrayList<Double> tabx;
private ArrayList<Double> taby;
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
            if (selectedFile == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Błąd kalkulatora");
                alert.setHeaderText(null);
                alert.setContentText("Nie wybrano pliku");
                alert.showAndWait();
            } else {
                Simplediff simplediff = new Simplediff(tabx, taby);
                Backwardsdiff backwardsdiff = new Backwardsdiff(tabx, taby);
                Stirling stirling = new Stirling(tabx, taby);
                resultout.appendText("[----"+selectedFile.getName()+"----]\n");
                for (Double tabx1 : tabx) {
                    resultout.appendText("<==== Obliczenia dla x0 =" + tabx1 + " ====>\n");
                    resultout.appendText("Metoda różnicy zwykłej: " + simplediff.calculate(tabx1) + "\n");
                    resultout.appendText("Metoda różnicy wstecznej: " + backwardsdiff.calculate(tabx1) + "\n");
                    resultout.appendText("Metoda różnic centralnych: " + stirling.calculate(tabx1) + "\n");
                }
            }
         }
    }



