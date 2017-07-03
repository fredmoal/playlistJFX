package vues.jfx;

import controleur.Controleur;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.Playlist;
import vues.jfx.menu.ControleurAfficherPlaylists;
import vues.jfx.menu.ControleurAjouterPlaylist;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by YohanBoichut on 29/06/2017.
 */
public class ControleurMenu {


    @FXML
    Label derniereCo;



    @FXML
    VBox conteneur;

    @FXML
    Accordion volets;

    public VBox getConteneur() {
        return conteneur;
    }

    public Controleur getControleur() {
        return controleur;
    }

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    Controleur controleur;

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    Scene scene;


    ControleurAjouterPlaylist controleurAjouterPlaylist;

    ControleurAfficherPlaylists controleurAfficherPlaylists;

    ControleurAjouterTitre controleurAjouterTitre;

    public static ControleurMenu getInstance(Controleur controleur) {

        URL location = ControleurMenu.class.getResource("/vues/jfx/Menu.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ControleurMenu vue = fxmlLoader.getController();
        vue.setScene(new Scene(vue.getConteneur(),ControleurVueApplicationJFX.LONGUEUR,ControleurVueApplicationJFX.LARGEUR));
        vue.setControleur(controleur);
        vue.initialiserVolets();
        return vue;
    }



    public void initialiserVolets() {
        if (controleurAjouterPlaylist == null) {
            controleurAjouterPlaylist = ControleurAjouterPlaylist.getInstance(controleur);
        }

        if (controleurAfficherPlaylists == null) {
            controleurAfficherPlaylists = ControleurAfficherPlaylists.getInstance(controleur);
        }

        if (controleurAjouterTitre == null) {
            controleurAjouterTitre = ControleurAjouterTitre.getInstance(controleur);
        }

        TitledPane afficherPlayList = new TitledPane("Afficher mes playlists",controleurAfficherPlaylists.getConteneur());
        volets.getPanes().add(afficherPlayList);

        TitledPane ajoutPlayList = new TitledPane("Ajout d'une nouvelle playlist",controleurAjouterPlaylist.getConteneur());
        volets.getPanes().add(ajoutPlayList);

        TitledPane ajoutTitre = new TitledPane("Ajouter un titre à une playlist",controleurAjouterTitre.getConteneur());
        ajoutTitre.setOnMouseClicked(e -> {
            controleurAjouterTitre.majPlayLists(controleur.getPlaylists());
            controleurAjouterTitre.majTitres(controleur.getTitres());
        });
        volets.getPanes().add(ajoutTitre);


    }

    public void rendreActive(Stage stage) {
        this.controleurAjouterPlaylist.rendreActive();
        stage.setScene(getScene());
    }

    public void majDerniereCo(LocalDateTime date) {
        if (date == null) {
            derniereCo.setText("Première connexion");
        }
        else {
            derniereCo.setText("Dernière connexion : "+date.toString());
        }
    }

    public void majPlaylists(Collection<Playlist> playlists) {
        controleurAfficherPlaylists.majPlaylists(playlists);

    }




    public void deconnexion(ActionEvent actionEvent) {
        this.controleur.deconnexion();
    }


}
