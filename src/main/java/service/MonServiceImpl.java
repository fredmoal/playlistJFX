package service;

import facade.ServiceImpl;
import facade.erreurs.*;
import modele.Playlist;
import modele.Titre;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Created by YohanBoichut on 29/06/2017.
 */
public class    MonServiceImpl implements MonService {
    ServiceImpl monService = new ServiceImpl();

    private static MonService instance;


    public static MonService getInstance() {
        if (instance == null)  {
            instance = new MonServiceImpl();
        }
        return instance;
    }



    @Override
    public LocalDateTime login(String pseudo, String mdp) throws CoupleUtilisateurMDPInconnuException, UtilisateurDejaConnecteException {
        return monService.login(pseudo,mdp);
    }

    @Override
    public void logout(String pseudo) throws UtilisateurNonConnecteException {
        monService.logout(pseudo);
    }

    @Override
    public void enregistrerEtLogin(String pseudo, String mdp) throws UtilisateurDejaExistantException {
        monService.enregistrerEtLogin(pseudo,mdp);
    }

    @Override
    public LocalDateTime lastLogin(String pseudo) {
        return monService.lastLogin(pseudo);
    }

    @Override
    public void refresh(String pseudo, String mdp) throws CoupleUtilisateurMDPInconnuException, UtilisateurNonConnecteException {
            monService.refresh(pseudo,mdp);
    }

    @Override
    public void ajouterTitre(String groupe, String chanson, long place) throws PlaceDuTitreDejaOccupeeException {
        monService.ajouterTitre(groupe,chanson,place);
    }

    @Override
    public Collection<Titre> getAllTitres() {
        return monService.getAllTitres();
    }

    @Override
    public Titre getTitre(String groupe, String titre) throws TitreNotFoundException {
        return monService.getTitre(groupe, titre);
    }

    @Override
    public Titre getTitre(long place) throws TitreNotFoundException {
        return monService.getTitre(place);
    }

    @Override
    public void ajouterTitreDansPlaylist(String pseudo, String nomPlayList, String groupe, String chanson) throws TitreNotFoundException {
        monService.ajouterTitreDansPlaylist(pseudo, nomPlayList, groupe, chanson);
    }

    @Override
    public void ajouterTitreParPlaceDansPlaylist(String pseudo, String nomPlayList, long place) throws TitreNotFoundException {
        monService.ajouterTitreParPlaceDansPlaylist(pseudo, nomPlayList, place);
    }

    @Override
    public void supprimerTitreDePlaylist(String pseudo, String nomPlaylist, long place) {
        monService.supprimerTitreDePlaylist(pseudo, nomPlaylist, place);
    }

    @Override
    public boolean creerPlayList(String pseudo, String nomPlayList) {
        return monService.creerPlayList(pseudo, nomPlayList);
    }

    @Override
    public void copierPlayList(String pseudo, String pseudoUserACopier, String nomPlayListACopier) {
        monService.copierPlayList(pseudo, pseudoUserACopier, nomPlayListACopier);
    }

    @Override
    public Collection<Playlist> getAllPlaylist() {
        return monService.getAllPlaylist();
    }

    @Override
    public Collection<Playlist> getAllPlaylistUtilisateur(String pseudo) {
        return monService.getAllPlaylistUtilisateur(pseudo);
    }

    @Override
    public Collection<Playlist> getAllPlaylistDepuisDate(LocalDateTime dateTime) {
        return monService.getAllPlaylistDepuisDate(dateTime);
    }

    @Override
    public Playlist getPlaylist(String pseudo, String nomPlaylist) {
        return monService.getPlaylist(pseudo, nomPlaylist);
    }

    @Override
    public List<Titre> getPlaylistTitresOrdreAleatoire(String pseudo, String nomPlaylist) {
        return monService.getPlaylistTitresOrdreAleatoire(pseudo, nomPlaylist);
    }

    @Override
    public Collection<Playlist> getAllPlaylistAvecCeTitre(Titre titre) {
        return monService.getAllPlaylistAvecCeTitre(titre);
    }
}
