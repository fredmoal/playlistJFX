package vues.jfx;

import controleur.Controleur;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.Titre;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by YohanBoichut on 30/06/2017.
 */
public class ControleurLectureTitres {

    @FXML
    VBox conteneur;

    @FXML
    ListView<Titre> titres;

    @FXML
    Label titreAlbum;


    public VBox getConteneur() {
        return conteneur;
    }

    public void setConteneur(VBox conteneur) {
        this.conteneur = conteneur;
    }

    public ListView getTitres() {
        return titres;
    }

    public void setTitres(ListView titres) {
        this.titres = titres;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    Scene scene;


    public void rendreActive(Stage stage) {
        stage.setScene(getScene());
    }

    public static ControleurLectureTitres getInstance(Controleur controleur) {

        URL location = ControleurLectureTitres.class.getResource("/vues/jfx/Lecture.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ControleurLectureTitres vue = fxmlLoader.getController();
        vue.setScene(new Scene(vue.getConteneur(),ControleurVueApplicationJFX.LONGUEUR,ControleurVueApplicationJFX.LARGEUR));
        return vue;
    }

    public void majTitrePlay(String playlist) {
        this.titreAlbum.setText(playlist);

    }


    public void majTitres(List<Titre> titresRandomize) {
        ObservableList<Titre> listeNomMesPlaylists = titres.getItems();
        listeNomMesPlaylists.setAll(titresRandomize);


        titres.setCellFactory(param -> new ListCell<Titre>() {
            @Override
            protected void updateItem(Titre item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null ) {
                    setText(null);
                } else {
                    setText(item.getPlace()+" | "+item.getChanson() + " - " + item.getGroupe());
                }
            }
        });
    }
}
