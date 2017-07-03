package vues.jfx.menu;

import controleur.Controleur;
import controleur.exceptions.PlaylistExistante;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;

/**
 * Created by YohanBoichut on 30/06/2017.
 */
public class ControleurAjouterPlaylist {


    public HBox getConteneur() {
        return conteneur;
    }

    public void setConteneur(HBox conteneur) {
        this.conteneur = conteneur;
    }

    public TextField getNomPlaylist() {
        return nomPlaylist;
    }

    public void setNomPlaylist(TextField nomPlaylist) {
        this.nomPlaylist = nomPlaylist;
    }

    public Controleur getControleur() {
        return controleur;
    }

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }



    public void ajouterPlaylist(ActionEvent actionEvent) {
        String nom = this.nomPlaylist.getText();
        if ( nom == null || nom.length() <=0) {
            Alert m = new Alert(Alert.AlertType.ERROR,"Une playlist a forcément un nom !!", ButtonType.OK);
            m.show();
        }

        try {
            controleur.creerPlaylist(nom);
        } catch (PlaylistExistante playlistExistante) {
            Alert m = new Alert(Alert.AlertType.ERROR,"Cette playlist existe déjà dans votre collection", ButtonType.OK);
            m.show();
        }

    }
    @FXML
    HBox conteneur;

    @FXML
    TextField nomPlaylist;

    Controleur controleur;

    public static ControleurAjouterPlaylist getInstance(Controleur controleur) {

        URL location = ControleurAjouterPlaylist.class.getResource("/vues/jfx/menu/AjouterPlaylist.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ControleurAjouterPlaylist vue = fxmlLoader.getController();
        vue.setControleur(controleur);

        return vue;
    }

    public void rendreActive() {
        this.nomPlaylist.setText("");
    }



}


