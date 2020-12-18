package com.github.cc3002.finalreality.gui;

import com.github.cc3002.finalreality.Controller;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Main entry point for the application.
 * <p>
 * <Complete here with the details of the implemented application>
 *
 * @author Ignacio Slater Muñoz.
 * @author Vicente Daie Pinilla
 */
public class FinalReality extends Application {

  private final Controller controller = new Controller();

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Final reality");

    Label label = new Label("This will be a game sometime");
    label.setAlignment(Pos.CENTER);

    // This sets the size of the Scene to be 400px wide, 200px high
    Scene scene = new Scene(label, 400, 200);
    primaryStage.setScene(scene);

    primaryStage.show();
  }
}