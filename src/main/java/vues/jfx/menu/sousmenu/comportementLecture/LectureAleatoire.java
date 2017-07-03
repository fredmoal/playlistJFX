package vues.jfx.menu.sousmenu.comportementLecture;

import controleur.Controleur;
import modele.Titre;

import java.util.List;

/**
 * Created by YohanBoichut on 03/07/2017.
 */
public class LectureAleatoire implements ComportementLecture {
    @Override
    public List<Titre> execute(Controleur controleur, String playlist) {
        return controleur.getTitresAleatoirement(playlist);
    }


    private static ComportementLecture instance;

    private LectureAleatoire() {}


    public static  ComportementLecture getInstance() {
        if (instance == null) {
            instance = new LectureAleatoire();
        }
        return instance;
    }
}
