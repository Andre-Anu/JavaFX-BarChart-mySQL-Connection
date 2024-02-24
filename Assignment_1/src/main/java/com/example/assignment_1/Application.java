package com.example.assignment_1;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {

        FileInputStream iconStream = new FileInputStream("kirby.png");
        Image icon = new Image(iconStream);
        stage.getIcons().add(icon);

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("UI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Charts!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}