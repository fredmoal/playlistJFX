package vues.jfx.menu;

import controleur.Controleur;
import controleur.evenements.NotificationPlaylists;
import controleur.exceptions.PlaylistExistante;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.HBox;
import modele.Playlist;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by YohanBoichut on 30/06/2017.
 */
public class ControleurAfficherPlaylists implements NotificationPlaylists{


    @FXML
    ListView mesPlaylists;

    public ListView getConteneur() {
        return mesPlaylists;
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

    public void afficherMenuContextuel(ContextMenuEvent contextMenuEvent) {

        final ContextMenu contextMenu = new ContextMenu();

        MenuItem item1 = new MenuItem("Lecture aléatoire");
        item1.setOnAction(e -> {
            for(String play : (ObservableList<String>) mesPlaylists.getSelectionModel().getSelectedItems()) {
                controleur.lancerLectureAleatoire(play);

            }
        });
        MenuItem item2 = new MenuItem("Lecture classique");
        item2.setOnAction(e -> {
            for(String play : (ObservableList<String>) mesPlaylists.getSelectionModel().getSelectedItems()) {
                controleur.lancerLectureClassique(play);
            }
        });
        contextMenu.getItems().addAll(item1, item2);
        contextMenu.show(mesPlaylists,contextMenuEvent.getScreenX(),contextMenuEvent.getScreenY());

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

    public static ControleurAfficherPlaylists getInstance(Controleur controleur) {

        URL location = ControleurAfficherPlaylists.class.getResource("/vues/jfx/menu/AfficherPlaylists.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ControleurAfficherPlaylists vue = fxmlLoader.getController();
        vue.setControleur(controleur);
        controleur.ajouterAbonnement(vue);
        vue.majPlaylists();

        return vue;
    }

    public void rendreActive() {
        this.nomPlaylist.setText("");
    }


    @Override
    public void majPlaylists() {
        ObservableList<String> listeNomMesPlaylists = mesPlaylists.getItems();
        listeNomMesPlaylists.setAll(getControleur().getPlaylists().stream().map(e -> e.getNomListe()).collect(Collectors.toCollection(ArrayList::new)));

        mesPlaylists.refresh();
    }
}


