package vues.jfx.menu.sousmenu.comportementLecture;

import controleur.Controleur;
import modele.Titre;

import java.util.List;

/**
 * Created by YohanBoichut on 03/07/2017.
 */
public class LectureClassique implements ComportementLecture {


    private static ComportementLecture instance;


    private LectureClassique() {
    }


    public static  ComportementLecture getInstance() {
        if (instance == null) {
            instance = new LectureClassique();
        }
        return instance;
    }


    @Override
    public List<Titre> execute(Controleur controleur, String playlist) {
        return controleur.getTitresClassique(playlist);
    }
}
