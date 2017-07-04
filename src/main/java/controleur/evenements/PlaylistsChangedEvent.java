package controleur.evenements;

import modele.Playlist;

/**
 * Created by Fred on 03/07/2017.
 */
public class PlaylistsChangedEvent extends ModelChangedEvent {
    public PlaylistsChangedEvent(Object source) {
        super(source);
    }
}
