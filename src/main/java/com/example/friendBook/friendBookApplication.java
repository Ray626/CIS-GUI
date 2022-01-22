package com.example.friendBook;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class friendBookApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(friendBookApplication.class.getResource("FriendBook.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600,400);
        stage.setTitle("friendBook");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
