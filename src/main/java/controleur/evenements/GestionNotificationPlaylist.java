package controleur.evenements;

/**
 * Created by YohanBoichut on 03/07/2017.
 */
public interface GestionNotificationPlaylist {

    void ajouterAbonnement(NotificationPlaylist e);

    void sendNotificationPlaylist(String playlist);
}
