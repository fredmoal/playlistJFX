package vues;

import modele.Playlist;
import modele.Titre;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Created by YohanBoichut on 29/06/2017.
 */
public interface ControleurVueApplication {


    void goToConnexion();

    void goToMenu();

    void setDerniereConnexion();

    void lectureAleatoire(String playlist);

    void lectureClassique(String playlist);
}
