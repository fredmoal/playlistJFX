package application;

import controleur.Controleur;
import controleur.Controller;
import facade.PlaylistService;
import facade.ServiceImpl;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by YohanBoichut on 29/06/2017.
 */
public class ProgrammePrincipal extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller(primaryStage);
        controller.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
