package vues;

import controleur.Controller;
import controleur.evenements.ModelChangedEvent;
import controleur.evenements.ModelListener;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import modele.Playlist;
import modele.Titre;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PlaylistView implements ModelListener {
    private Scene scene;
    private Stage stage;
    private StackPane root = new StackPane();
    private ObservableList<Titre> data;
    private TableView<Titre> table;
    private Playlist playlist;
    private Controller controller;

	public PlaylistView(Controller controller, Playlist playlist){
		this.controller = controller;
		controller.addModelListener(this);
		this.playlist = playlist;
		buildFrame();
	}

    public Controller getController() {
        return controller;
    }

    private void buildFrame() {
        List<Titre> liste = playlist.getListe();
        data = FXCollections.observableList(liste);

        table = new TableView<>();
        table.itemsProperty().setValue(data);

        TableColumn<Titre, String> groupeColumn = new TableColumn<>("Groupe");
        TableColumn<Titre, String> chansonColumn = new TableColumn<>("Chanson");
        groupeColumn.setCellValueFactory((e) -> new ReadOnlyStringWrapper(e.getValue().getGroupe()).getReadOnlyProperty());
        chansonColumn.setCellValueFactory((e) -> new ReadOnlyStringWrapper(e.getValue().getChanson()).getReadOnlyProperty());

        table.getColumns().addAll(chansonColumn, groupeColumn);

        root.getChildren().add(table);
        scene = new Scene(root);
	}

	public void close() {
		stage.close();
	}

	public void display(Stage fenetrePrincipale) {
	    stage = fenetrePrincipale;
        stage.setScene(scene);
        stage.setTitle("Playlist "+playlist.getNomListe());
        stage.show();
	}
    public void display() {
	    display(new Stage());
    }

	public void modelChanged(ModelChangedEvent event) {
        table.refresh();
	}
}
