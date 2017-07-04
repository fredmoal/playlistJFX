package vues;

import controleur.Controller;
import controleur.evenements.ModelChangedEvent;
import controleur.evenements.ModelListener;
import controleur.evenements.PlaylistsChangedEvent;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import modele.Playlist;

import java.util.*;

public class PlaylistsView implements ModelListener {
    private Scene scene;
    private Stage stage;
    private BorderPane root = new BorderPane();
    private ObservableList<Playlist> data;
    private TableView<Playlist> table;
    private String pseudo;
    private Controller controller;

	public PlaylistsView(Controller controller){
		this.controller = controller;
		pseudo = controller.getPseudo();
		controller.addModelListener(this);
		buildFrame();
	}

    public Controller getController() {
        return controller;
    }

    private void buildFrame() {
	    // la table d'affichage des playlists
        Collection<Playlist> playlists = getController().getPlaylistService().getAllPlaylistUtilisateur(pseudo);
        data = FXCollections.observableList(new ArrayList<>(playlists));

        table = new TableView();
        table.itemsProperty().setValue(data);

        TableColumn<Playlist, Number> nbColumn = new TableColumn<>("Nb titres");
        TableColumn<Playlist, String> nomColumn = new TableColumn<>("Nom");
        nbColumn.setCellValueFactory((e) -> new ReadOnlyIntegerWrapper(e.getValue().getListe().size()).getReadOnlyProperty());
        nomColumn.setCellValueFactory((e) -> new ReadOnlyStringWrapper(e.getValue().getNomListe()).getReadOnlyProperty());

        table.getColumns().addAll(nomColumn, nbColumn);

        table.setOnMouseClicked(e -> getController().afficheUnePlaylistView(data.get(table.getSelectionModel().getSelectedCells().get(0).getRow())));

        // le bouton d'ajout d'une playlist
        Button addButton = new Button("Ajouter une playlist");
        addButton.setOnAction(e->getController().afficheAjoutPlaylistView());

        root.setTop(addButton);
        root.setCenter(table);
        scene = new Scene(root);
	}

	public void close() {
		stage.close();
	}

	public void display(Stage fenetrePrincipale) {
	    stage = fenetrePrincipale;
        stage.setScene(scene);
        stage.setTitle("Toutes les playlists");
        stage.show();
	}
    public void display() {
	    display(new Stage());
    }

	public void modelChanged(ModelChangedEvent event) {
	    if (event instanceof PlaylistsChangedEvent) {
	        // actualise la liste des playlists
            data.setAll(getController().getPlaylistService().getAllPlaylistUtilisateur(pseudo));
            table.refresh();
        }
	}
}
