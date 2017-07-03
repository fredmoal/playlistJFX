package vues.jfx;

import controleur.Controleur;
import javafx.stage.Stage;
import modele.Playlist;
import modele.Titre;
import vues.ControleurVueApplication;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Created by YohanBoichut on 29/06/2017.
 */
public class ControleurVueApplicationJFX implements ControleurVueApplication {

    public static final int LARGEUR = 500;
    public static final int LONGUEUR = 800;

    public static final int LARGEURLECTEUR = 300;
    public static final int LONGUEURLECTEUR = 400;


    Stage stage = new Stage();


    ConnexionControleur connexionControleur;
    ControleurMenu controleurMenu;

    public Controleur getMonControleur() {
        return monControleur;
    }

    public void setMonControleur(Controleur monControleur) {
        this.monControleur = monControleur;
    }

    Controleur monControleur;


    public ControleurVueApplicationJFX(Controleur monControleur) {
        this.monControleur = monControleur;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    /**
     * Fonctions de navigation
     */



    @Override
    public void goToConnexion() {
        if (connexionControleur == null) {
            connexionControleur = ConnexionControleur.getInstance(getMonControleur());
        }
        connexionControleur.rendreActive(getStage());
        getStage().show();
    }


    @Override
    public void goToMenu(Collection<Playlist> playlists) {
        if (controleurMenu == null) {
            controleurMenu = ControleurMenu.getInstance(getMonControleur());
        }
        controleurMenu.majPlaylists(playlists);
        controleurMenu.rendreActive(getStage());
        getStage().show();
    }

    @Override
    public void setDerniereConnexion(LocalDateTime derniereConnexion) {
        if (controleurMenu == null) {
            controleurMenu = ControleurMenu.getInstance(getMonControleur());
        }
        controleurMenu.majDerniereCo(derniereConnexion);
    }

    @Override
    public void lectureAleatoire(String playlist, List<Titre> titresRandomize) {
        ControleurLectureTitres controleurLectureTitres = ControleurLectureTitres.getInstance(getMonControleur());
        Stage alea =new Stage();
        alea.setTitle("Lecture aleatoire");
        controleurLectureTitres.majTitrePlay(playlist);
        controleurLectureTitres.majTitres(titresRandomize);
        controleurLectureTitres.rendreActive(alea);
        alea.show();
    }

    @Override
    public void lectureClassique(String playlist, List<Titre> titres) {
        ControleurLectureTitres controleurLectureTitres = ControleurLectureTitres.getInstance(getMonControleur());
        Stage alea =new Stage();
        alea.setTitle("Lecture");
        controleurLectureTitres.majTitrePlay(playlist);
        controleurLectureTitres.majTitres(titres);
        controleurLectureTitres.rendreActive(alea);
        alea.show();
    }

}
