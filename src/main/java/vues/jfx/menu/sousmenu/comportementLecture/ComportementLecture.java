package vues.jfx.menu.sousmenu.comportementLecture;

import controleur.Controleur;
import modele.Titre;

import java.util.List;

/**
 * Created by YohanBoichut on 03/07/2017.
 */
public interface ComportementLecture {

    List<Titre> execute(Controleur controleur, String playlist);
}
