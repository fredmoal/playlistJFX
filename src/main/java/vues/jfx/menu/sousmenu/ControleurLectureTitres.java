package vues.jfx.menu.sousmenu;

import controleur.Controleur;
import controleur.evenements.NotificationPlaylist;
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
import vues.jfx.ControleurVueApplicationJFX;
import vues.jfx.menu.sousmenu.comportementLecture.ComportementLecture;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by YohanBoichut on 30/06/2017.
 */
public class ControleurLectureTitres implements NotificationPlaylist {

    @FXML
    VBox conteneur;

    @FXML
    ListView<Titre> titres;

    @FXML
    Label titreAlbum;


    Controleur controleur;


    public Controleur getControleur() {
        return controleur;
    }

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    public void setComportementLecture(ComportementLecture comportementLecture) {
        this.comportementLecture = comportementLecture;
    }

    ComportementLecture comportementLecture;


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

    public static ControleurLectureTitres getInstance(Controleur controleur, String playlist,ComportementLecture comportementLecture) {

        URL location = ControleurLectureTitres.class.getResource("/vues/jfx/menu/sousmenu/Lecture.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ControleurLectureTitres vue = fxmlLoader.getController();
        vue.setScene(new Scene(vue.getConteneur(), ControleurVueApplicationJFX.LONGUEUR,ControleurVueApplicationJFX.LARGEUR));
        vue.majTitrePlay(playlist);

        vue.setComportementLecture(comportementLecture);
        vue.setControleur(controleur);
        vue.majPlayList(playlist);
        controleur.ajouterAbonnement(vue);
        return vue;
    }

    public void majTitrePlay(String playlist) {
        this.titreAlbum.setText(playlist);

    }


    public void majTitres() {
        ObservableList<Titre> listeNomMesPlaylists = titres.getItems();
        listeNomMesPlaylists.setAll(comportementLecture.execute(getControleur(),this.titreAlbum.getText()));

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

    @Override
    public void majPlayList(String playlist) {
        if (playlist.equals(titreAlbum.getText())) {
            majTitres();
        }

    }
}
