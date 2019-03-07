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
        TextField path = new TextField("File Path");
        Label pathL = new Label("Path");
        Button GO = new Button("FIND");
        GridPane g = new GridPane();
        g.addRow(0,pathL,path);
        ComboBox ButandLabel = new ComboBox();
        //BarChart<String,Number> B=getdata("C:\\Users\\Waldinis PC\\Desktop\\abc.txt");
        FlowPane f = new FlowPane();
        f.getChildren().addAll(pathL,path,GO);
        p.setPadding(new Insets(5, 5, 5, 5));
        p.setBottom(f);
        Scene a = new Scene(p,800   ,600);
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

    public void getdata(String target){
        String Alphabet="abcdefghijklmnopqrstuvwxyz";


        Map<String,Integer> Values =new HashMap<>();
        for(int y = 0; y<26;y++){
            Values.put(Character.toString(Alphabet.charAt(y)),0);
        }
        BufferedReader in=null;
        try {
             in = new BufferedReader(new FileReader(target));
        }
        catch(Exception e ){
            System.out.println("File not found");

        }
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
        ObservableList<XYChart.Series<Character,Integer>> Data;
        ObservableList<XYChart.Data<Character,Integer>> a = null;
        XYChart.Series chart = new XYChart.Series();



        final CategoryAxis xAxis= new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> BC = new BarChart<String, Number>(xAxis,yAxis);
        BC.getYAxis().setTickLabelsVisible(true);
        //BC.getYAxis().setOpacity(0);
        BC.setHorizontalGridLinesVisible(true);
        BC.setVerticalGridLinesVisible(true);
        BC.setLegendVisible(false);
        for(int x = 0; x<26;x++){
            char temp = Alphabet.charAt(x);
            String te = Character.toString(temp);
            System.out.println(temp+" value "+ Values.get(Character.toString(temp)));
            chart.getData().add(new XYChart.Data(Character.toString(temp),Values.get(Character.toString(temp))));
        }
        System.out.println(BC==null);
        BC.getData().add(chart);
        p.setCenter(BC);
        //return BC;
    }
    public String convert(String a){
        if(a.contains("/")) {
            return a.replace('/', '\\');
        }else return a.replace('\\','\\');
    }
}
