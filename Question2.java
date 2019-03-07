/*
Name:Michael Waldron
SID:100657864
Question:#2
 */
package main;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import java.math.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import javafx.stage.Stage;
import javafx.scene.image.Image;

import javax.swing.*;

public class Question2 extends Application {
    @Override
    public void start(Stage s){
        Button cal = new Button("Calculate");
        TextField investAmt = new TextField();
        TextField years = new TextField();
        TextField futureV = new TextField();
        futureV.setEditable(false);

        String style ="-fx-background-color: transparent";
        futureV.setStyle(style);
        TextField interstR = new TextField();
        // Labels
        Label investAmtL = new Label("Investment Amount");
        Label yearsL = new Label("Years");
        Label futureVL = new Label("Future Value");
        Label interestRL = new Label("Interest Rate");
        // Gridpane Initialization
        GridPane InvestCalc= new GridPane();
        InvestCalc.addRow(0,investAmtL,investAmt);
        InvestCalc.addRow(1,yearsL,years);
        InvestCalc.addRow(2,interestRL,interstR);
        InvestCalc.addRow(3,futureVL,futureV);
        InvestCalc.add(cal,1,4);
        s.setTitle("Question2");
        //button event so if the input is incorrect it will catch it and not just crash the program but also produce
        //the right output if given a valid input
        cal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               try {
                   double investamtD = Double.parseDouble(investAmt.getText());
                   int yearsD = Integer.parseInt(years.getText());
                   double interestRD = Double.parseDouble(interstR.getText());
                   double futureVD = investamtD * Math.pow((1 + (interestRD/1200)), (yearsD * 12));
                   futureV.setText(Double.toString(futureVD));
               }catch(Exception a){
                   futureV.setText("Incorrect input try again");
                }
            }
        });
        //setting scene and displaying
       Scene news = new Scene(InvestCalc);
       s.setScene(news);
       s.show();
    }
}
