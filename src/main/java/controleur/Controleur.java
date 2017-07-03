package controleur;

import com.sun.org.apache.xml.internal.serialize.LineSeparator;
import controleur.evenements.GestionNotificationPlaylist;
import controleur.evenements.GestionNotificationPlaylists;
import controleur.evenements.NotificationPlaylist;
import controleur.evenements.NotificationPlaylists;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by YohanBoichut on 29/06/2017.
 */
public class Controleur implements GestionNotificationPlaylist, GestionNotificationPlaylists {

    MonService monService = MonServiceImpl.getInstance();
    ControleurVueApplication controleurVueApplication;
    LocalDateTime derniereConnexion;
    String pseudo;
    List<NotificationPlaylist> abonnesNotifPlaylist;
    List<NotificationPlaylists> abonnesNotifPlaylists;


    public Controleur() {
        controleurVueApplication = new ControleurVueApplicationJFX(this);
        abonnesNotifPlaylist = new ArrayList<>();
        abonnesNotifPlaylists = new ArrayList<>();
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
            controleurVueApplication.setDerniereConnexion();
            goToMenu();
        } catch (CoupleUtilisateurMDPInconnuException e) {
            throw new IdentifiantsErrones();
        } catch (UtilisateurDejaConnecteException e) {
            throw  new DejaConnecte();
        }

    }

    public void creerPlaylist(String nom) throws PlaylistExistante {
        if (this.getMonService().creerPlayList(pseudo,nom)) {
            this.sendNotificationPlaylists();
            goToMenu();
        }
        else {
            throw new PlaylistExistante();
        }

    }


    public void goToMenu() {
        controleurVueApplication.goToMenu();
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
        controleurVueApplication.lectureAleatoire(play);

    }


    public void lancerLectureClassique(String play) {
        List<Titre> titresRandomize = getMonService().getPlaylist(pseudo,play).getListe();
        controleurVueApplication.lectureClassique(play);

    }

    public void enregistrerTitrePlaylist(String nomListe, long place) throws AjoutTitreAnnule {
        try {
            getMonService().ajouterTitreParPlaceDansPlaylist(pseudo,nomListe,place);
            this.sendNotificationPlaylist(nomListe);
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


    public List<Titre> getTitresAleatoirement(String playlist) {
        return getMonService().getPlaylistTitresOrdreAleatoire(pseudo,playlist);
    }


    public List<Titre> getTitresClassique(String playlist) {
        return getMonService().getPlaylist(pseudo,playlist).getListe();
    }





    @Override
    public void ajouterAbonnement(NotificationPlaylist e) {
        abonnesNotifPlaylist.add(e);
    }

    @Override
    public void sendNotificationPlaylist(String playlist) {
        for (NotificationPlaylist e : abonnesNotifPlaylist) {
            e.majPlayList(playlist);
        }
    }


    @Override
    public void ajouterAbonnement(NotificationPlaylists e) {
        abonnesNotifPlaylists.add(e);
    }

    @Override
    public void sendNotificationPlaylists() {
        for (NotificationPlaylists e : abonnesNotifPlaylists) {
            e.majPlaylists();
        }

    }


}
