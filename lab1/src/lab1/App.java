package lab1;

import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.*;


public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        Group root = new Group();
        Scene scene = new Scene(root, 300, 180);
        scene.setFill(Color.rgb(128, 255, 0));

        Rectangle tvBox = new Rectangle(70, 36, 160, 110);
        root.getChildren().add(tvBox);
        tvBox.setFill(Color.rgb(255, 166, 0));

        Rectangle tvScreen = new Rectangle(80, 46, 106, 90);
        tvScreen.setArcWidth(20);
        tvScreen.setArcHeight(20);
        root.getChildren().add(tvScreen);
        tvScreen.setFill(Color.rgb(129, 129, 129));

        Circle button1 = new Circle(212,90,3.5);
        root.getChildren().add(button1);
        Circle button2 = new Circle(212,110,3.5);
        root.getChildren().add(button2);
        Circle button3 = new Circle(212,130,3.5);
        root.getChildren().add(button3);

        Line line1 = new Line(150.0f, 36.0f, 175.0f, 10.0f);
        root.getChildren().add(line1);
        Line line2 = new Line(150.0f, 36.0f, 125.0f, 10.0f);
        root.getChildren().add(line2);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
