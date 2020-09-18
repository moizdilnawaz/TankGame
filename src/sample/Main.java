package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main extends Application {

    // launch the application
    Scene scene1, scene2;
    Stage window;

    public void start(Stage stage) {
        window = stage;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            setScene1();
            SetScene2();
            Launcher l = new Launcher();
            window.setScene(scene1);
            window.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void SetScene2() {
        Label game = new Label("You Are in PlayMode Mode");
        Button button2 = new Button("Go to Scene1");
        button2.setOnAction(e -> {
            window.setScene(scene1);
        });
        HBox layoutS2 = new HBox(game, button2);

        scene2 = new Scene(layoutS2, 700, 500);

        // set the scene
    }

    public void setScene1() {
        try {
            //Scene1--------------------------
            // set title for the stage
            window.setTitle("creating Background");

            // create a label
            Label label = new Label("Name : ");

            // create a text field
            TextField textfield = new TextField();

            // set preferred column count
            textfield.setPrefColumnCount(10);

            // create a button
            Button button = new Button("Play Game");
            button.setOnAction(e -> {
                System.out.println("Button pressed " + ((Button) e.getSource()).getText());
                System.out.println("Load New Scene");
                window.setScene(scene2);
            });

            // add the label, text field and button
            HBox hbox = new HBox(button);
            hbox.setMinWidth(500);

            // set spacing
            hbox.setSpacing(10);

            // set alignment for the HBox
            hbox.setAlignment(Pos.CENTER);

            // create a scene
            scene1 = new Scene(hbox, 700, 500);

            // create a input stream
            FileInputStream input = new FileInputStream("E:\\TankGame\\TankWarResources\\Title.bmp");

            // create a image
            Image image = new Image(input);

            // create a background image
            BackgroundImage backgroundimage = new BackgroundImage(image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);

            // create Background
            Background background = new Background(backgroundimage);

            // set background
            hbox.setBackground(background);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


}



