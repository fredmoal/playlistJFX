package vues;

import controleur.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Fred on 04/07/2017.
 */
public class AddPlaylistView {
    @FXML
    private TextField nomPlaylist;
    @FXML
    private Parent top;

    private Controller controller;
    private Scene scene;
    private Stage stage;

    public Parent getTop() {
        return top;
    }

    public Controller getController() {
        return controller;
    }

    public Scene getScene() {
        return scene;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public static AddPlaylistView getInstance(Controller controleur) {
        URL location = AddPlaylistView.class.getResource("/vues/AddPlaylist.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AddPlaylistView vue = fxmlLoader.getController();
        vue.setScene(new Scene(vue.getTop()));
        vue.setController(controleur);
        return vue;
    }
    public void ajouterPlaylist(ActionEvent actionEvent) {
        // récupération de la saisie du nom de playslist
        String nom = nomPlaylist.getText();
        if ( nom == null || nom.length()<5 ) {
            Alert m = new Alert(Alert.AlertType.ERROR,"La playlist doit au moins contenir 4 lettres", ButtonType.OK);
            m.show();
            return;
        }
        getController().ajouterPlaylist(this, nom);
        Alert m = new Alert(Alert.AlertType.INFORMATION,"La playlist "+nom+" vient d'être ajoutée", ButtonType.OK);
        m.show();
    }

    public void display(Stage stage) {
        this.stage = stage;
        stage.setScene(getScene());
        stage.show();
    }

    public void display() {
        display(new Stage());
    }

    public void close() {
        stage.close();
    }

}
