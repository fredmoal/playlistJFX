package controleur.evenements;

import modele.Playlist;
import modele.User;

/**
 * Created by Fred on 03/07/2017.
 */
public class PlaylistChangedEvent extends ModelChangedEvent {
    private Playlist playlist;

    public PlaylistChangedEvent(Object source, Playlist playlist) {
        super(source);
        this.playlist = playlist;
    }

    public Playlist getPlaylist() {
        return playlist;
    }
}
