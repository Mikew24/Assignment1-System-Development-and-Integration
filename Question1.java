package main;
/*
Name:Michael Waldron
SID:100657864
Question:#1
 */
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
public class Question1 extends Application {
    @Override
    public void start(Stage s){
        ArrayList<Image> cards=new ArrayList<>();
        for(int x = 1;x<=54;x++){
            String string=Integer.toString(x);
            Image adding = new Image("file:///G:/Program Files/School 2018/System Development and Integration/Assignment/Assignment/src/Cards/"+string+".png");
            cards.add(adding);
        }
        System.out.println("Done");
        Collections.shuffle(cards);
        HBox g = new HBox();
        ImageView a = new ImageView(cards.get(0));
        ImageView b = new ImageView(cards.get(1));
        ImageView c = new ImageView(cards.get(2));
        g.getChildren().addAll(a,b,c);
        Scene scene = new Scene(g);
        s.setScene(scene);
        s.show();

    }
}
