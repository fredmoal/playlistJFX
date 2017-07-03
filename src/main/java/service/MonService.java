package service;

import facade.erreurs.*;
import modele.Playlist;
import modele.Titre;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Created by YohanBoichut on 29/06/2017.
 */
public interface MonService {


    /**
     *  Connection au service
     * @param pseudo    : pseudo de l'utilisateur
     * @param mdp       : mot de passe de l'utilisateur
     *
     * @return la date et l'heure du précédent dernier login de l'utilisateur
     *
     * @throws CoupleUtilisateurMDPInconnuException mauvais couple login/mote de passe.
     * @throws UtilisateurDejaConnecteException     l'utilisateur est déjà connecté.
     */
    LocalDateTime login(String pseudo, String mdp) throws CoupleUtilisateurMDPInconnuException, UtilisateurDejaConnecteException;

    /**
     *  Déconnection au service
     * @param pseudo    : pseudo de l'utilisateur
     *
     * @throws UtilisateurNonConnecteException     l'utilisateur n'est pas connecté.
     */
    void logout(String pseudo) throws UtilisateurNonConnecteException;

    /**
     *  Enregistrement d'un nouvel utilisateur et connection de celui-ci
     * @param pseudo    : pseudo de l'utilisateur
     * @param mdp       : mot de passe de l'utilisateur
     *
     * @throws UtilisateurDejaExistantException     Un utilisateur utilise déjà ce pseudo.
     */
    void enregistrerEtLogin(String pseudo, String mdp) throws UtilisateurDejaExistantException;

    /**
     *  Récupération de la date+heure du dernier login de l'utilisateur
     * @param pseudo    : pseudo de l'utilisateur
     */
    LocalDateTime lastLogin(String pseudo);

    /**
     *  reconnection au service : l'utilisateur se reconnecte au service avant le temps limite
     *  Attention : non utlisable si l'utilisateur s'est déconnecté
     *
     * @param pseudo    : pseudo de l'utilisateur
     * @param mdp       : mot de passe de l'utilisateur
     *
     * @throws CoupleUtilisateurMDPInconnuException mauvais couple login/mote de passe.
     * @throws UtilisateurDejaConnecteException     l'utilisateur est déjà connecté.
     */
    void refresh(String pseudo, String mdp) throws CoupleUtilisateurMDPInconnuException, UtilisateurNonConnecteException;

    void ajouterTitre(String groupe, String chanson, long place) throws PlaceDuTitreDejaOccupeeException;
    Collection<Titre> getAllTitres();
    Titre getTitre(String groupe, String titre) throws TitreNotFoundException;
    Titre getTitre(long place) throws TitreNotFoundException;

    // gestion des playlists
    void ajouterTitreDansPlaylist(String pseudo, String nomPlayList, String groupe, String chanson) throws TitreNotFoundException;
    void ajouterTitreParPlaceDansPlaylist(String pseudo, String nomPlayList, long place) throws TitreNotFoundException;
    void supprimerTitreDePlaylist(String pseudo, String nomPlaylist, long place);
    boolean creerPlayList(String pseudo, String nomPlayList);
    // copie dans les playlists de pseudo la playlist nomPlayListACopier de l'utilisateur pseudoUserACopier
    void copierPlayList(String pseudo, String pseudoUserACopier, String nomPlayListACopier);


    Collection<Playlist> getAllPlaylist();
    Collection<Playlist> getAllPlaylistUtilisateur(String pseudo);
    Collection<Playlist> getAllPlaylistDepuisDate(LocalDateTime dateTime);
    Playlist getPlaylist(String pseudo, String nomPlaylist);
    List<Titre> getPlaylistTitresOrdreAleatoire(String pseudo, String nomPlaylist);
    Collection<Playlist> getAllPlaylistAvecCeTitre(Titre titre);
}
