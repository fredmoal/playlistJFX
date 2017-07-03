package application;

import controleur.Controleur;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by YohanBoichut on 29/06/2017.
 */
public class ProgrammePrincipal extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        new Controleur();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
