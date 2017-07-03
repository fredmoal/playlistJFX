package vues.jfx;

import controleur.Controleur;
import controleur.exceptions.AjoutTitreAnnule;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import modele.Playlist;
import modele.Titre;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;

/**
 * Created by YohanBoichut on 30/06/2017.
 */
public class ControleurAjouterTitre {

    @FXML
    ComboBox<Playlist> playlists;


    @FXML
    ComboBox<Titre> titres;

    @FXML
    VBox conteneur;

    Scene scene;
    Controleur controleur;


    public ComboBox<Playlist> getPlaylists() {
        return playlists;
    }


    public void majPlayLists(Collection<Playlist> playlists) {
        ObservableList<Playlist> listeNomMesPlaylists = this.playlists.getItems();
        listeNomMesPlaylists.setAll(playlists);

        this.playlists.setCellFactory(param -> new ListCell<Playlist>() {
            @Override
            protected void updateItem(Playlist item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null ) {
                    setText(null);
                } else {
                    setText(item.getNomListe());
                }
            }
        });

        this.playlists.setConverter(new StringConverter<Playlist>() {
            @Override
            public String toString(Playlist object) {
                return object.getNomListe();
            }

            @Override
            public Playlist fromString(String string) {
                return null;
            }
        });


        //getPlaylists().show();
    }


    public void majTitres(Collection<Titre> titres) {
        ObservableList<Titre> titresModele = this.titres.getItems();
        titresModele.setAll(titres);
        this.titres.setCellFactory(param -> new ListCell<Titre>() {
            @Override
            protected void updateItem(Titre item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null ) {
                    setText(null);
                } else {
                    setText(item.getChanson() + " - " + item.getGroupe());
                }
            }
        });

        this.titres.setConverter(new StringConverter<Titre>() {
            @Override
            public String toString(Titre object) {
                return object.getChanson() + " - "+ object.getGroupe();
            }

            @Override
            public Titre fromString(String string) {
                return null;
            }
        });

    }

    public void setPlaylists(ComboBox<Playlist> playlists) {
        this.playlists = playlists;
    }

    public ComboBox<Titre> getTitres() {
        return titres;
    }

    public void setTitres(ComboBox<Titre> titres) {
        this.titres = titres;
    }

    public VBox getConteneur() {
        return conteneur;
    }

    public void setConteneur(VBox conteneur) {
        this.conteneur = conteneur;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Controleur getControleur() {
        return controleur;
    }

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    public void enregistrerTitre(ActionEvent actionEvent) {

        Playlist p = this.getPlaylists().getValue();
        Titre t = this.getTitres().getValue();

        if (p == null || t ==null) {
            Alert m = new Alert(Alert.AlertType.ERROR,"Vous devez sélectionner une playlist et un titre !", ButtonType.OK);
            m.show();
        }

        try {
            getControleur().enregistrerTitrePlaylist(p.getNomListe(),t.getPlace());
            Alert m = new Alert(Alert.AlertType.INFORMATION,"Ajout du titre "+t.getChanson()+" dans la playlist "+p.getNomListe()+" effectué !", ButtonType.OK);
            m.show();
        } catch (AjoutTitreAnnule ajoutTitreAnnule) {
            Alert m = new Alert(Alert.AlertType.ERROR,"L'ajout du titre n'a pas pu se faire !", ButtonType.OK);
            m.show();
        }


    }




    public static ControleurAjouterTitre getInstance(Controleur controleur) {

        URL location = ControleurAjouterTitre.class.getResource("/vues/jfx/AjouterTitre.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ControleurAjouterTitre vue = fxmlLoader.getController();
        vue.setScene(new Scene(vue.getConteneur(),ControleurVueApplicationJFX.LONGUEUR,ControleurVueApplicationJFX.LARGEUR));
        vue.setControleur(controleur);
        return vue;
    }



}
