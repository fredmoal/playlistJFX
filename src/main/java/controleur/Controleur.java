package controleur;

import com.sun.org.apache.xml.internal.serialize.LineSeparator;
import controleur.exceptions.AjoutTitreAnnule;
import controleur.exceptions.DejaConnecte;
import controleur.exceptions.IdentifiantsErrones;
import controleur.exceptions.PlaylistExistante;
import facade.erreurs.CoupleUtilisateurMDPInconnuException;
import facade.erreurs.TitreNotFoundException;
import facade.erreurs.UtilisateurDejaConnecteException;
import facade.erreurs.UtilisateurNonConnecteException;
import modele.Playlist;
import modele.Titre;
import service.MonService;
import service.MonServiceImpl;
import vues.ControleurVueApplication;
import vues.jfx.ControleurVueApplicationJFX;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Created by YohanBoichut on 29/06/2017.
 */
public class Controleur {

    MonService monService = MonServiceImpl.getInstance();
    ControleurVueApplication controleurVueApplication;
    LocalDateTime derniereConnexion;
    String pseudo;


    public Controleur() {
        controleurVueApplication = new ControleurVueApplicationJFX(this);
        goToConnexion();

    }

    public void goToConnexion() {
        controleurVueApplication.goToConnexion();
    }

    public LocalDateTime getDerniereCo() {
        return derniereCo;
    }

    public void setDerniereCo(LocalDateTime derniereCo) {
        this.derniereCo = derniereCo;
    }

    LocalDateTime derniereCo;


    public MonService getMonService() {
        return monService;
    }

    public void login(String pseudoText, String motDePasseText) throws IdentifiantsErrones, DejaConnecte {
        try {
            derniereConnexion = getMonService().login(pseudoText, motDePasseText);
            this.pseudo = pseudoText;
            controleurVueApplication.setDerniereConnexion(derniereConnexion);
            goToMenu();
        } catch (CoupleUtilisateurMDPInconnuException e) {
            throw new IdentifiantsErrones();
        } catch (UtilisateurDejaConnecteException e) {
            throw  new DejaConnecte();
        }

    }

    public void creerPlaylist(String nom) throws PlaylistExistante {
        if (this.getMonService().creerPlayList(pseudo,nom)) {
            goToMenu();
        }
        else {
            throw new PlaylistExistante();
        }

    }


    public void goToMenu() {
        controleurVueApplication.goToMenu(getMonService().getAllPlaylistUtilisateur(pseudo));
    }

    public void deconnexion() {
        try {
            getMonService().logout(pseudo);
            goToConnexion();
        } catch (UtilisateurNonConnecteException e) {
            e.printStackTrace();
        }

    }


    public void lancerLectureAleatoire(String play) {
        List<Titre> titresRandomize = getMonService().getPlaylistTitresOrdreAleatoire(pseudo,play);
        controleurVueApplication.lectureAleatoire(play,titresRandomize);

    }


    public void lancerLectureClassique(String play) {
        List<Titre> titresRandomize = getMonService().getPlaylist(pseudo,play).getListe();
        controleurVueApplication.lectureClassique(play,titresRandomize);

    }

    public void enregistrerTitrePlaylist(String nomListe, long place) throws AjoutTitreAnnule {
        try {
            getMonService().ajouterTitreParPlaceDansPlaylist(pseudo,nomListe,place);
        } catch (TitreNotFoundException e) {
            throw new AjoutTitreAnnule();
        }
    }

    public Collection<Playlist> getPlaylists() {
        return getMonService().getAllPlaylistUtilisateur(pseudo);
    }

    public Collection<Titre> getTitres() {
        return getMonService().getAllTitres();
    }
}
