package controleur;


import controleur.evenements.ModelChangedEvent;
import controleur.evenements.ModelListener;
import controleur.evenements.PlaylistChangedEvent;
import controleur.evenements.PlaylistsChangedEvent;
import facade.LoginService;
import facade.PlaylistService;
import facade.ServiceImpl;
import facade.erreurs.CoupleUtilisateurMDPInconnuException;
import facade.erreurs.UtilisateurDejaConnecteException;
import javafx.stage.Stage;
import modele.Playlist;
import vues.AddPlaylistView;
import vues.ConnexionView;
import vues.PlaylistView;
import vues.PlaylistsView;

import java.util.ArrayList;
import java.util.List;

public class Controller {
	// les facades du modele
	private PlaylistService playlistService;
	private LoginService loginService;
	// l'utilisateur connecté
	private String pseudo;

	// la fenetre principale
	private Stage fenetrePrincipale;

	// les écouteurs s'enregistrent auprès du controleur
	private List<ModelListener> listeners;

	public Controller(Stage fenetrePrincipale){
		// init du service
		ServiceImpl service = new ServiceImpl();
		this.playlistService = service;
		this.loginService = service;

		this.fenetrePrincipale = fenetrePrincipale;
		listeners = new ArrayList<ModelListener>();
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public PlaylistService getPlaylistService() {
		return playlistService;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void start(){
		// lancement : affichage de la fenetre de connexion
		afficheConnexionView();
	}

	public void notifyModelChanged(ModelChangedEvent event){
		for(ModelListener listener : listeners){
			listener.modelChanged(event);
		}
	}

	public void addModelListener(ModelListener listener){
		listeners.add(listener);
	}

	public void removeModelListener(ModelListener listener){
		listeners.remove(listener);
	}

	// affichage des vues
	public void afficheConnexionView() {
		ConnexionView view = ConnexionView.getInstance(this);
		view.display(fenetrePrincipale);
	}

	public void affichePlaylistsView() {
		PlaylistsView view = new PlaylistsView(this);
		view.display(fenetrePrincipale);
	}

	public void afficheUnePlaylistView(Playlist playlist) {
		PlaylistView view = new PlaylistView(this, playlist);
		view.display();
	}

	public void afficheAjoutPlaylistView() {
		AddPlaylistView view = AddPlaylistView.getInstance(this);
		view.display();
	}

	// interface avec les services
	public void login(String pseudo, String password) throws CoupleUtilisateurMDPInconnuException, UtilisateurDejaConnecteException {
			loginService.login(pseudo,password);
			setPseudo(pseudo);
			affichePlaylistsView();
	}

	public void ajouterPlaylist(Object source, String nom) {
		playlistService.creerPlayList(pseudo,nom);
		notifyModelChanged(new PlaylistsChangedEvent(source));
	}
}
