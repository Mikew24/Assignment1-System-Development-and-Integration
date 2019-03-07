/*
Name:Michael Waldron
SID:100657864
Question:#4
 */
package main;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import java.util.*;
import java.io.*;
import java.math.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.BarChart;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ValueAxis;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.control.ComboBox;
import javax.swing.*;

public class Question4 extends Application{
    BorderPane p = new BorderPane();
    @Override
    public void start(Stage s){
        //creating textboxs and labels for user input and buttons
        TextField path = new TextField("File Path");
        Label pathL = new Label("Path");
        Button GO = new Button("FIND");
        //create flowpane to store the buttons and textboxes
        FlowPane f = new FlowPane();
        f.getChildren().addAll(pathL,path,GO);
        p.setPadding(new Insets(5, 5, 5, 5));
        p.setBottom(f);
        Scene a = new Scene(p,800   ,600);
        //action event for when the user tries to find file
        GO.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String a = path.getText();
                a=convert(a);
                getdata(a);
            }
        });
        //getdata("C:\\Users\\Waldinis PC\\Desktop\\abc.txt");
        s.setScene(a);
        s.setTitle("Question4");
        s.show();
//        GO.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public  void handle(ActionEvent actionEvent) {
//                String s  = path.getText();
//                B = getdata(s);
//
//            }
//        });

    }
    //will open file and count letter occurrences using a map if the file exists
    public void getdata(String target){
        String Alphabet="abcdefghijklmnopqrstuvwxyz";


        Map<String,Integer> Values =new HashMap<>();
        for(int y = 0; y<26;y++){
            Values.put(Character.toString(Alphabet.charAt(y)),0);
        }
        // setting up reader for file input
        BufferedReader in=null;
        //tries to find file
        try {
             in = new BufferedReader(new FileReader(target));
        }
        catch(Exception e ){
            System.out.println("File not found");

        }
        //grabbing the lines and seperating it to be stored in our map
        try {
            String line;
            while ((line = in.readLine()) != null){
                String words[]= line.split("\\s+");
                for(String word : words){
                    word = word.toLowerCase();
                    for(int x = 0 ; x<word.length();x++){
                        if(Character.isLetter(word.charAt(x))){
                            char temp = word.charAt(x);
                                int tempI=Values.get(Character.toString(temp));
                                tempI++;
                                Values.replace(Character.toString(temp),tempI);

                        }
                    }
                }
                in.close();
            }
            for(String key : Values.keySet()){
                System.out.println(key+" "+Values.get(key));
            }
        }catch (Exception e ){
            System.out.println("File Read Error");

        }
        //series to store our map data to be used in barchart
        XYChart.Series chart = new XYChart.Series();


        //setting up axises and barchart
        final CategoryAxis xAxis= new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> BC = new BarChart<String, Number>(xAxis,yAxis);
        BC.getYAxis().setTickLabelsVisible(true);

        BC.setHorizontalGridLinesVisible(true);
        BC.setVerticalGridLinesVisible(true);
        BC.setLegendVisible(false);
        //storing series with map data
        for(int x = 0; x<26;x++){
            char temp = Alphabet.charAt(x);
            String te = Character.toString(temp);
            System.out.println(temp+" value "+ Values.get(Character.toString(temp)));
            chart.getData().add(new XYChart.Data(Character.toString(temp),Values.get(Character.toString(temp))));
        }
        System.out.println(BC==null);
        //adding mapdata series to the barchart
        BC.getData().add(chart);
        //displaying barchart
        p.setCenter(BC);
        //return BC;
    }
    //trying to avoid file format errors
    public String convert(String a){
        if(a.contains("/")) {
            return a.replace('/', '\\');
        }else return a.replace('\\','\\');
    }
}
