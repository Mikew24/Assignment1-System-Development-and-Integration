/*
Name:Michael Waldron
SID:100657864
Question:#3
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;

import java.awt.*;
import java.util.*;
import java.io.*;
import java.math.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.BarChart;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ValueAxis;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.control.ComboBox;

import javax.swing.*;
public class Question3 extends Application {
    //function to create the random points to start with
    public Point2D[] genRandomPoints(Circle c){
        double p1 = Math.random()*(2.0*Math.PI);
        System.out.println(p1);
        double p2 = Math.random()*(2.0*Math.PI);
        double p3 = Math.random()*(2.0*Math.PI);
        Point2D p1d = new Point2D(c.getCenterX()+c.getRadius()*Math.cos(p1),c.getCenterY()+c.getRadius()*Math.sin(p1));

        Point2D p2d = new Point2D(c.getCenterX()+c.getRadius()*Math.cos(p2),c.getCenterY()+c.getRadius()*Math.sin(p2));

        Point2D p3d = new Point2D(c.getCenterX()+c.getRadius()*Math.cos(p3),c.getCenterY()+c.getRadius()*Math.sin(p3));
        Point2D listp[] = {p1d,p2d,p3d};
        return listp;
    }

//THis code is really sloppy I was rushing to finish this question so i did not compartmentalize my code very well and mostly copy and pasting instead of opting in for methods/classes etc
    public void start(Stage s){
        //connecting lines
        Line A = new Line();
        Line B = new Line();
        Line C = new Line();
        Pane p = new Pane();
        //creating the drag circles and Main circle
        Circle Circles[] = {new Circle(400,50,10),new Circle(50,300,10),new Circle(600,300,10)};
        Circle MainC = new Circle();
        MainC.setFill(Color.WHITE);
        MainC.setStroke(Color.BLACK);
        MainC.setRadius(300);
        MainC.setCenterX(400);
        MainC.setCenterY(300);
        //getting points
        Point2D AP[] =genRandomPoints(MainC);
        //setting up circles to be connected
        Circles[0].setCenterX(AP[0].getX());
        Circles[1].setCenterX(AP[1].getX());
        Circles[2].setCenterX(AP[2].getX());

        Circles[0].setCenterY(AP[0].getY());
        Circles[1].setCenterY(AP[1].getY());
        Circles[2].setCenterY(AP[2].getY());
        // Starting points
        A.setStartX(AP[0].getX());
        B.setStartX(AP[1].getX());
        C.setStartX(AP[2].getX());

        A.setStartY(AP[0].getY());
        B.setStartY(AP[1].getY());
        C.setStartY(AP[2].getY());
        //End Points
        A.setEndX(AP[1].getX());
        B.setEndX(AP[2].getX());
        C.setEndX(AP[0].getX());

        A.setEndY(AP[1].getY());
        B.setEndY(AP[2].getY());
        C.setEndY(AP[0].getY());
        //getting angles to display
        double[] lines= new double[3];
        double[] Angles = new double[3];
        Text anglesT[] = {new Text(),new Text(),new Text()};
        lines[0]=AP[0].distance(AP[1]);
        lines[1]=AP[1].distance(AP[2]);
        lines[2]=AP[2].distance(AP[0]);
        Angles[0]=Math.acos((lines[0]*lines[0]-lines[1]*lines[1]-lines[2]*lines[2])/(-2*lines[1]*lines[2]));
        Angles[1]=Math.acos((lines[1]*lines[1]-lines[0]*lines[0]-lines[2]*lines[2])/(-2*lines[0]*lines[2]));
        Angles[2]=Math.acos((lines[2]*lines[2]-lines[1]*lines[1]-lines[0]*lines[0])/(-2*lines[0]*lines[1]));
        for(int x = 0 ; x<3 ;x++) {
            anglesT[x].setX(Circles[x].getCenterX()-Circles[x].getRadius());
            anglesT[x].setY(Circles[x].getCenterY()-Circles[x].getRadius());
            anglesT[x].setText(String.format("%.2f",Math.toDegrees(Angles[x])));

        }
        //basically just some of the code above to update the other points and angles where the user drags the circle. for all three set on mouse dragged
        Circles[2].setOnMouseDragged(e->{
            double mX=e.getX();
            double mY=e.getY();
            double ang = Math.atan2(mY,mX);
            System.out.println(ang);
            double test = Math.pow((MainC.getCenterX()-mX),2)+Math.pow((MainC.getCenterY()-mY),2);
            Point2D p1d = new Point2D(MainC.getCenterX()+MainC.getRadius()*Math.cos(ang),MainC.getCenterY()+MainC.getRadius()*Math.sin(ang));
            AP[2]=p1d;
            Circles[0].setCenterX(AP[0].getX());
            Circles[1].setCenterX(AP[1].getX());
            Circles[2].setCenterX(AP[2].getX());

            Circles[0].setCenterY(AP[0].getY());
            Circles[1].setCenterY(AP[1].getY());
            Circles[2].setCenterY(AP[2].getY());
            // Starting points
            A.setStartX(AP[0].getX());
            B.setStartX(AP[1].getX());
            C.setStartX(AP[2].getX());

            A.setStartY(AP[0].getY());
            B.setStartY(AP[1].getY());
            C.setStartY(AP[2].getY());
            //End Points
            A.setEndX(AP[1].getX());
            B.setEndX(AP[2].getX());
            C.setEndX(AP[0].getX());

            A.setEndY(AP[1].getY());
            B.setEndY(AP[2].getY());
            C.setEndY(AP[0].getY());

            lines[0]=AP[0].distance(AP[1]);
            lines[1]=AP[1].distance(AP[2]);
            lines[2]=AP[2].distance(AP[0]);
            Angles[0]=Math.acos((lines[0]*lines[0]-lines[1]*lines[1]-lines[2]*lines[2])/(-2*lines[1]*lines[2]));
            Angles[1]=Math.acos((lines[1]*lines[1]-lines[0]*lines[0]-lines[2]*lines[2])/(-2*lines[0]*lines[2]));
            Angles[2]=Math.acos((lines[2]*lines[2]-lines[1]*lines[1]-lines[0]*lines[0])/(-2*lines[0]*lines[1]));
            for(int x = 0 ; x<3 ;x++) {
                anglesT[x].setX(Circles[x].getCenterX()-Circles[x].getRadius());
                anglesT[x].setY(Circles[x].getCenterY()-Circles[x].getRadius());
                anglesT[x].setText(String.format("%.2f",Math.toDegrees(Angles[x])));

            }

        });
        Circles[1].setOnMouseDragged(e->{
            double mX=e.getX();
            double mY=e.getY();
            double ang = Math.atan2(mY,mX);
            System.out.println(ang);
            double test = Math.pow((MainC.getCenterX()-mX),2)+Math.pow((MainC.getCenterY()-mY),2);
            Point2D p1d = new Point2D(MainC.getCenterX()+MainC.getRadius()*Math.cos(ang),MainC.getCenterY()+MainC.getRadius()*Math.sin(ang));
            AP[1]=p1d;
            Circles[0].setCenterX(AP[0].getX());
            Circles[1].setCenterX(AP[1].getX());
            Circles[2].setCenterX(AP[2].getX());

            Circles[0].setCenterY(AP[0].getY());
            Circles[1].setCenterY(AP[1].getY());
            Circles[2].setCenterY(AP[2].getY());
            // Starting points
            A.setStartX(AP[0].getX());
            B.setStartX(AP[1].getX());
            C.setStartX(AP[2].getX());

            A.setStartY(AP[0].getY());
            B.setStartY(AP[1].getY());
            C.setStartY(AP[2].getY());
            //End Points
            A.setEndX(AP[1].getX());
            B.setEndX(AP[2].getX());
            C.setEndX(AP[0].getX());

            A.setEndY(AP[1].getY());
            B.setEndY(AP[2].getY());
            C.setEndY(AP[0].getY());
            lines[0]=AP[0].distance(AP[1]);
            lines[1]=AP[1].distance(AP[2]);
            lines[2]=AP[2].distance(AP[0]);
            Angles[0]=Math.acos((lines[0]*lines[0]-lines[1]*lines[1]-lines[2]*lines[2])/(-2*lines[1]*lines[2]));
            Angles[1]=Math.acos((lines[1]*lines[1]-lines[0]*lines[0]-lines[2]*lines[2])/(-2*lines[0]*lines[2]));
            Angles[2]=Math.acos((lines[2]*lines[2]-lines[1]*lines[1]-lines[0]*lines[0])/(-2*lines[0]*lines[1]));
            for(int x = 0 ; x<3 ;x++) {
                anglesT[x].setX(Circles[x].getCenterX()-Circles[x].getRadius());
                anglesT[x].setY(Circles[x].getCenterY()-Circles[x].getRadius());
                anglesT[x].setText(String.format("%.2f",Math.toDegrees(Angles[x])));

            }
        });
        Circles[0].setOnMouseDragged(e->{
            double mX=e.getX();
            double mY=e.getY();
            double ang = Math.atan2(mY,mX);
            System.out.println(ang);
            double test = Math.pow((MainC.getCenterX()-mX),2)+Math.pow((MainC.getCenterY()-mY),2);
            Point2D p1d = new Point2D(MainC.getCenterX()+MainC.getRadius()*Math.cos(ang),MainC.getCenterY()+MainC.getRadius()*Math.sin(ang));
            AP[0]=p1d;
            Circles[0].setCenterX(AP[0].getX());
            Circles[1].setCenterX(AP[1].getX());
            Circles[2].setCenterX(AP[2].getX());

            Circles[0].setCenterY(AP[0].getY());
            Circles[1].setCenterY(AP[1].getY());
            Circles[2].setCenterY(AP[2].getY());
            // Starting points
            A.setStartX(AP[0].getX());
            B.setStartX(AP[1].getX());
            C.setStartX(AP[2].getX());

            A.setStartY(AP[0].getY());
            B.setStartY(AP[1].getY());
            C.setStartY(AP[2].getY());
            //End Points
            A.setEndX(AP[1].getX());
            B.setEndX(AP[2].getX());
            C.setEndX(AP[0].getX());

            A.setEndY(AP[1].getY());
            B.setEndY(AP[2].getY());
            C.setEndY(AP[0].getY());
            lines[0]=AP[0].distance(AP[1]);
            lines[1]=AP[1].distance(AP[2]);
            lines[2]=AP[2].distance(AP[0]);
            Angles[0]=Math.acos((lines[0]*lines[0]-lines[1]*lines[1]-lines[2]*lines[2])/(-2*lines[1]*lines[2]));
            Angles[1]=Math.acos((lines[1]*lines[1]-lines[0]*lines[0]-lines[2]*lines[2])/(-2*lines[0]*lines[2]));
            Angles[2]=Math.acos((lines[2]*lines[2]-lines[1]*lines[1]-lines[0]*lines[0])/(-2*lines[0]*lines[1]));
            for(int x = 0 ; x<3 ;x++) {
                anglesT[x].setX(Circles[x].getCenterX()-Circles[x].getRadius());
                anglesT[x].setY(Circles[x].getCenterY()-Circles[x].getRadius());
                anglesT[x].setText(String.format("%.2f",Math.toDegrees(Angles[x])));

            }
                });
        //adding pane children to be displayed
        p.getChildren().addAll(MainC,A,B,C,Circles[0],Circles[1],Circles[2],anglesT[0],anglesT[1],anglesT[2]);
        //setting up scene
        Scene sc = new Scene(p,800,800);
        //displaying scene to user
        s.setScene(sc);
        s.setTitle("Question3");
        s.show();


    }

}
