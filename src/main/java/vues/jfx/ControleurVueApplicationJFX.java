package vues.jfx;

import controleur.Controleur;
import javafx.stage.Stage;
import vues.ControleurVueApplication;
import vues.jfx.menu.sousmenu.ControleurLectureTitres;
import vues.jfx.menu.sousmenu.comportementLecture.LectureAleatoire;
import vues.jfx.menu.sousmenu.comportementLecture.LectureClassique;

/**
 * Created by YohanBoichut on 29/06/2017.
 */
public class ControleurVueApplicationJFX implements ControleurVueApplication {

    public static final int LARGEUR = 500;
    public static final int LONGUEUR = 800;

    public static final int LARGEURLECTEUR = 300;
    public static final int LONGUEURLECTEUR = 400;


    Stage stage = new Stage();


    ControleurConnexion connexionControleur;
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
            connexionControleur = ControleurConnexion.getInstance(getMonControleur());
        }
        connexionControleur.rendreActive(getStage());
        getStage().show();
    }


    @Override
    public void goToMenu() {
        if (controleurMenu == null) {
            controleurMenu = ControleurMenu.getInstance(getMonControleur());
        }
        controleurMenu.rendreActive(getStage());
        getStage().show();
    }

    @Override
    public void setDerniereConnexion() {
        if (controleurMenu == null) {
            controleurMenu = ControleurMenu.getInstance(getMonControleur());
        }
        controleurMenu.majDerniereCo(getMonControleur().getDerniereCo());
    }

    @Override
    public void lectureAleatoire(String playlist) {
        ControleurLectureTitres controleurLectureTitres = ControleurLectureTitres.getInstance(getMonControleur(),playlist, LectureAleatoire.getInstance());
        Stage alea =new Stage();
        alea.setTitle("Lecture aleatoire");
        controleurLectureTitres.rendreActive(alea);
        alea.show();
    }

    @Override
    public void lectureClassique(String playlist) {
        ControleurLectureTitres controleurLectureTitres = ControleurLectureTitres.getInstance(getMonControleur(),playlist, LectureClassique.getInstance());
        Stage alea =new Stage();
        alea.setTitle("Lecture");
        controleurLectureTitres.rendreActive(alea);
        alea.show();
    }

}
